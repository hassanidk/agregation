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

import com.github.andrewoma.dexx.collection.HashMap;

import es.usc.citius.hipster.algorithm.Hipster;
import es.usc.citius.hipster.graph.GraphSearchProblem;
import es.usc.citius.hipster.model.problem.SearchProblem;
import fr.univ_lyon1.ter.utilitaire.CompareSite;
import fr.univ_lyon1.ter.utilitaire.Utils;

public class Aggregation {
	//Need : Horaire TCL et Sites
	//Need : La durée de visite
	private Calendar arrivee;
	private Calendar depart;
	private ArrayList<String>preferences;
	private HashMap<String, ArrayList<Site>> cheminSite;
	private ArrayList<Site> listeSite;
	
	public Aggregation(Calendar arrivee, Calendar depart, ArrayList<String> preferences){
		this.arrivee = arrivee;
		this.depart = depart;
		this.preferences = preferences;
		this.cheminSite = new HashMap<String, ArrayList<Site>>();
		this.listeSite = new ArrayList<Site>(); 
	}
	
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
	// Méthode qui permet d'avoir les sites que l'utilisateurs souhaite visiter
	public void getSites(){
		// On charges tous les sites
		getAllSites();
		// Si y'a 0 preferences, on garde tous les sites
		// SI y'a preferences, on supprimes les sites site qui ne sont pas dans preferences
		if (preferences.size()!=0){
			Iterator<Site> it = listeSite.iterator();
			while (it.hasNext()){
				Site site = it.next();
				for (String pref : preferences){
					if (!pref.equals(site.getTypeSite()))
						it.remove();
				}
			}
		}
		// ON reorganise avec la pertinence du site
		Collections.sort(listeSite, new CompareSite());	
	}
	/**
	 * L'objectif est de partir de la Part Dieu, visiter les sites possibles,
	 * pus revenir à la part-dieu avant l'heure où l'utilisateur quitte la ville
	 */
	public void getItineraire(){
		String arretDepart = "Part-Dieu";
		String arretArrivee ="";
		String jour = Utils.getDay(arrivee);
		Iterator<Site> it = listeSite.iterator();
	
		while(it.hasNext()){
			Site site = it.next();
			int horaire = site.getHorraireOuverture(jour);
			if (horaire!=5000){
				SearchProblem p = GraphSearchProblem
			              .startingFrom(arretDepart)
			              .in(Utils.reseauTCL)
			              .takeCostsFromEdges()
			              .build();
				arretArrivee = site.getStationTCL();
				System.out.println(arretDepart);
				// Tu fais dijkstra
				System.out.println(Hipster.createDijkstra(p).search(arretArrivee));
				//Tu verifie que tu peux retourner a part dieu avant l'heure de depart
				// (Tu prend le temps de parcours * 2 et tu compare a l'heure de depart
				// SI oui tu l'ajoute aux site à visiter
				// Si non tu supprimes le site et t
				arretDepart = arretArrivee;
			
			}else{
				it.remove();
			}
			
		}
	}

}
