package fr.univ_lyon1.ter.modele;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.jena.graph.Triple;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.riot.lang.PipedRDFIterator;
import org.apache.jena.riot.lang.PipedRDFStream;
import org.apache.jena.riot.lang.PipedTriplesStream;
import org.codehaus.jettison.json.JSONException;

import es.usc.citius.hipster.algorithm.Algorithm.SearchResult;
import es.usc.citius.hipster.algorithm.Hipster;
import es.usc.citius.hipster.graph.GraphSearchProblem;
import es.usc.citius.hipster.model.problem.SearchProblem;
import fr.univ_lyon1.ter.override.CompareSite;
import fr.univ_lyon1.ter.override.ResultatRecherche;
import fr.univ_lyon1.ter.utilitaire.Debug;
import fr.univ_lyon1.ter.utilitaire.GenerateurRDF;
import fr.univ_lyon1.ter.utilitaire.Utils;

public class Aggregation {
	//Need : Horaire TCL et Sites
	//Need : La durée de visite
	private int arrivee;
	private int depart;
	private String jour; // Permet de savoir sur quel jour on fait la requete
	private ArrayList<String>preferences;
	private ArrayList<InformationItineraire> itineraire;
	private ArrayList<Site> listeSite;
	private String partDieu = "Part Dieu";
	private int tolerance_depart = 30;
	GenerateurRDF generateur;
	
	public Aggregation(Calendar arrivee, Calendar depart, ArrayList<String> preferences){
		this.arrivee = Utils.getHeure(arrivee);
		this.depart = Utils.getHeure(depart);
		this.preferences = preferences;
		this.jour = Utils.getDay(arrivee);
		this.itineraire = new ArrayList<InformationItineraire>();
		this.listeSite = new ArrayList<Site>(); 
		this.generateur = new GenerateurRDF(arrivee, depart);
	}
	/**
	 * Permet de récuperer l'ensemble des sites situés sur le fichier de type RDF
	 * Crée nos objets en java
	 */
	private void getAllSites(){
		int i = 0;
		PipedRDFIterator<Triple> iter = new PipedRDFIterator<Triple>();
		final PipedRDFStream<Triple> inputStream = new PipedTriplesStream(iter);
		ExecutorService executor = Executors.newSingleThreadExecutor();
		// Create a runnable for our parser thread
		Runnable parser = new Runnable() {
			public void run() {
				try{
					RDFDataMgr.parse(inputStream, Utils.file_sites_lyon);
				}catch(Exception e){
					System.out.println(e.getMessage());
				}
			}
		};
		executor.submit(parser);
		Triple previous = iter.next();
		Site site = new Site();
		while (iter.hasNext()) {
			Triple next = iter.next();
			if (previous.getPredicate().toString().equals("http://ter/property/nom")){
				i = 1;
				listeSite.add(site);
				site = new Site();	
			}
			site.setProperty(i, previous.getObject().toString());
			i++;
			previous = next;		
		}
		// On ajoute le dernier site, et on supprime le premier site (vide)
		listeSite.add(site);
		listeSite.remove(0);
		executor.shutdown();
		
		
	}
	
	// Méthode qui permet d'avoir les sites que l'utilisateur souhaite visiter
	private void getSites(){
		// On charge tous les sites
		getAllSites();
		
		int i;
		// Si y'a 0 preferences, on garde tous les sites
		// SI y'a preferences, on supprime les sites qui ne sont pas dans preferences
		if (preferences.size()!=0){
			Iterator<Site> it = listeSite.iterator();
			while (it.hasNext()){
				Site site = it.next();
				i = 0;
				for (String pref: preferences){
					if (!pref.equals(site.getTypeSite())){
						i--;
					}else{
						i++;
					}
				}
				if (i<0)
					it.remove();
			}
		}
		// ON reorganise avec la pertinence du site
		if (Utils._DEBUG_MODE){
			Debug.addDebug("preference", preferences.size());
			Debug.addDebug("listeSite de getSite() au debut", listeSite.size());
		}
		Collections.sort(listeSite, new CompareSite());	
		if (Utils._DEBUG_MODE){
			Debug.addDebug("listeSite de getSites()", listeSite.size());
		}
		
	}
	/**
	 * L'objectif est de partir de la Part Dieu, visiter les sites possibles,
	 * pus revenir à la part-dieu avant l'heure où l'utilisateur quitte la ville
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void getItineraire(){
		String arretDepart = partDieu;
		String arretArrivee ="";
		int heureActuelle = arrivee;
		int heureTemp = 0;
		int hourExitSite = 0;
		int heureDepart = depart;
		Iterator<Site> it = listeSite.iterator();
		boolean premiereIteration = true;
	
		while(it.hasNext()){
			Site site = it.next();
			int horaire = site.getHorraireOuverture(jour);
			
			if (horaire!=5000){
				
				SearchProblem p = GraphSearchProblem
			              .startingFrom(arretDepart)
			              .in(Utils.reseauTCL)
			              .takeCostsFromEdges()
			              .build();
				SearchProblem q = GraphSearchProblem
			              .startingFrom(arretDepart)
			              .in(Utils.reseauTCL)
			              .takeCostsFromEdges()
			              .build();
						
				arretArrivee = site.getStationTCL();
				// Création de deux dijstra. 
				// Calcule le plus court chemin vers site touristique
				SearchResult versSiteTouristique = Hipster.createDijkstra(p).search(arretArrivee);
				// Calcule le plus court chemin vers part dieu
				SearchResult versPartDieu = Hipster.createDijkstra(q).search(partDieu);
				//Permet d'avoir la durée d'un intineraire
				int t_vers_site  = (int)Double.parseDouble(new ResultatRecherche(versSiteTouristique.getGoalNode()).toString());
				int t_vers_part_dieu = (int)Double.parseDouble(new ResultatRecherche(versPartDieu.getGoalNode()).toString());
				
				t_vers_site = Utils.checkHour(t_vers_site);
				t_vers_part_dieu = Utils.checkHour(t_vers_part_dieu);
				
				// Check le temps_itineraire 
				if(premiereIteration){
					heureTemp = heureActuelle + 2*t_vers_site + site.getDureeVisiteMoyenne();
					premiereIteration = false;
				}else{
					heureTemp = heureActuelle + t_vers_site + t_vers_part_dieu + site.getDureeVisiteMoyenne();
				}
				hourExitSite = heureActuelle + t_vers_site + site.getDureeVisiteMoyenne();
				
				heureTemp = Utils.checkHour(heureTemp);
				hourExitSite = Utils.checkHour(hourExitSite);
				if (heureTemp < heureDepart-tolerance_depart){ // Rajouter une tolérance au départ
					itineraire.add(new InformationItineraire(heureActuelle,site.getNom(),arretDepart, arretArrivee, versSiteTouristique.getOptimalPaths(), hourExitSite, site.getDureeVisiteMoyenne()));
					heureActuelle = hourExitSite ;
					
					arretDepart = arretArrivee;
				}else{
					it.remove();
				}	
			}else{
				it.remove();
			}
		} // Fin while
		
		// On rajoute un dernier chemin. Celui qui permet de se rendre à la Part-Dieu
		
		if (itineraire.size()!=0){
			String arretDernierSite = itineraire.get(itineraire.size()-1 ).getArretSite();
			SearchProblem p = GraphSearchProblem
		              .startingFrom(arretDernierSite)
		              .in(Utils.reseauTCL)
		              .takeCostsFromEdges()
		              .build();
			SearchResult versPartDieu = Hipster.createDijkstra(p).search(partDieu);
			int t_vers_part_dieu = (int)Double.parseDouble(new ResultatRecherche(versPartDieu.getGoalNode()).toString());
			t_vers_part_dieu = Utils.checkHour(heureActuelle + t_vers_part_dieu );
			itineraire.add(new InformationItineraire(heureActuelle,"Gare Part-Dieu",arretDernierSite, partDieu, versPartDieu.getOptimalPaths(), t_vers_part_dieu, 20));
		}
		
		
	}
	
	// Méthode qui permet de faire correspondre les temps avec les temps TCL
	private void calculResultat(){
		generateur.mise_a_jour_horraire();
		getSites();
		getItineraire();
		int size = 0;
		int heureExit = 0;
		int heureDepart = 0;
		boolean first = true;
		for (InformationItineraire i: itineraire){
			if (first){
				i.setCheminItineraire(Utils.getAllItineraire(i.getHeureItineraire(),i.getCheminItineraire()));
				size = i.getCheminItineraire().size()-1;
				heureExit = Integer.parseInt(i.getCheminItineraire().get(size)) + i.getDureeVisite();
				heureExit = Utils.checkHour(heureExit);
				i.setHourExitSite(heureExit);
				first = false;
			}else{
				i.setHeureItineraire(heureExit);
				i.setCheminItineraire(Utils.getAllItineraire(i.getHeureItineraire(),i.getCheminItineraire()));
				size = i.getCheminItineraire().size()-1;
				heureExit = Integer.parseInt(i.getCheminItineraire().get(size)) + i.getDureeVisite();
				heureExit = Utils.checkHour(heureExit);
				i.setHourExitSite(heureExit);
			}
		
				
//			
//			size = i.getCheminItineraire().size()-1;
//			heureExit = Integer.parseInt(i.getCheminItineraire().get(size)) + i.getDureeVisite();
//			heureExit = Utils.checkHour(heureExit);
//			
//			heureDepart = heureExit;
//			
//			i.setHourExitSite(heureExit);
//			
//			i.setCheminItineraire(Utils.getAllItineraire(i.getHeureItineraire(),i.getCheminItineraire()));
			
		}
		
		
		
			
		
		
	}
	// Méthode pour afficher en mode Console. Run like Java Application
	public void affichageResultat(){
		calculResultat();
		System.out.println("Nous vous proposons le circuit suivant : ");
		for (InformationItineraire i : itineraire){
			if (i.getNomSite()!="Gare Part-Dieu")
				System.out.println("-"+i.getNomSite());
		}
		System.out.println(" ---- ");
		for (InformationItineraire i: itineraire)
			System.out.println(i);	
		if (Utils._DEBUG_MODE)
			Debug.printDebug();
		
	}
	
	public String affichageResultatWeb(){
		calculResultat();
		String result ="[";
		for (InformationItineraire i: itineraire){
			//result = result+ i.getJSONItineraire();
			result = result+ i.setJSON();
			result = result+",";
		}
		result = result.substring(0, result.length()-1);
		result = result +"]";
		return result;
	}
	
	
	
	public void changementHorraire(Calendar arrivee, Calendar depart){
		generateur.setArrivee(arrivee);
		generateur.setDepart(depart);
	}

}
