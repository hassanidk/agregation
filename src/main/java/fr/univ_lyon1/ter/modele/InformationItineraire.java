package fr.univ_lyon1.ter.modele;

import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.cglib.core.DebuggingClassWriter;

import fr.univ_lyon1.ter.utilitaire.Debug;
import fr.univ_lyon1.ter.utilitaire.Utils;

public class InformationItineraire {
	private int heureItineraire;
	private String nomSite;
	private String arretFrom;
	private String arretSite;
	private List<String> cheminItineraire;
	private int hourExitSite;
	private int dureeVisite;
	
	/**
	 * 
	 * @param heureItineraire, heure de depart à laquelle on souhaite se rendre vers le site I
	 * @param nomSite , arret dusite touristique
	 * @param cheminItineraire chemin de l'itineraire entre un point X de depart et le site touristique
	 * @param hourExitSite cout (heure/minute) de l'itineraire + dureedevisite de site (Correspond a l'heure ou on quitte le site)
	 */
	public InformationItineraire(int heureItineraire,String nomSite,String arretFrom, String arretSite, List<List<String>> cheminItineraire, int hourExitSite, int dureeVisite) {
		
		this.heureItineraire = heureItineraire;
		this.nomSite = nomSite;
		this.arretFrom = arretFrom;
		this.arretSite =arretSite;
		this.cheminItineraire = cheminItineraire.get(0);
		this.hourExitSite = hourExitSite;
		this.dureeVisite = dureeVisite;
	}

	public int getDureeVisite() {
		return dureeVisite;
	}

	public void setDureeVisite(int dureeVisite) {
		this.dureeVisite = dureeVisite;
	}

	public int getHeureItineraire() {
		return heureItineraire;
	}

	public String getArretFrom() {
		return arretFrom;
	}

	public void setArretFrom(String arretFrom) {
		this.arretFrom = arretFrom;
	}

	public String getNomSite() {
		return nomSite;
	}

	public void setNomSite(String nomSite) {
		this.nomSite = nomSite;
	}

	public void setHeureItineraire(int heureItineraire) {
		this.heureItineraire = heureItineraire;
	}

	public String getArretSite() {
		return arretSite;
	}

	public void setArretSite(String arretSite) {
		this.arretSite = arretSite;
	}

	public List<String> getCheminItineraire() {
		return cheminItineraire;
	}

	public void setCheminItineraire(List<String> cheminItineraire) {
		this.cheminItineraire = cheminItineraire;
	}

	public int getHourExitSite() {
		return hourExitSite;
	}

	public void setHourExitSite(int hourExitSite) {
		this.hourExitSite = hourExitSite;
	}

	@Override
	public String toString() {
		String txt="Pour vous rendre au site : "+nomSite;
		int i = 0;
		for (String arret : cheminItineraire){
			if (arret.contains("|")){
				String[] parts = arret.split("[|]");
				if (i>0)
					txt = txt+"\nDescendez à l'arret "+parts[0];
				txt = txt+"\n Prenez le"+parts[1]+" à l'arret "+parts[0]+parts[2]+"à"+parts[4];
				
			}
			if (arret.contains("Arrivée :")){
				txt = txt+"\n Descendez à l'arret "+arret+"."+nomSite+" se trouve à quelques minutes...";
			}
			i++;
		}
		//return txt;
		return "InformationItineraire [heureItineraire=" + heureItineraire + ", nomSite=" + nomSite + ", arretFrom="
				+ arretFrom + ", arretSite=" + arretSite + ", cheminItineraire=" + cheminItineraire
				+ ", hourExitSite=" + hourExitSite + "]";
	}
	

	public String setJSON(){
		
		try {
			// Initialisation variables
			JSONObject jobjroot = new JSONObject();
			int duree = dureeVisite-20;
			int size = cheminItineraire.size();
			int j = 0;
			boolean first = true;
			duree = Utils.checkHour(duree);
			
			String metro = "";
			String depart = "";
			String direction ="";
			String tempMetro ="";
			String tempDepart ="";
			String tempDirection= "";
			String arrivee ="";
			String destination = "";

			int heureDepart = 0;
			int heureArrivee =0;
			int tempHeureDepart = 0;
			
			jobjroot.put("nomSite", nomSite);
			jobjroot.put("dureeVisite", duree);
			
			if (cheminItineraire.size() == 3){
				String arrets= cheminItineraire.get(0);
				String [] parts = arrets.split("[|]");
				destination = cheminItineraire.get(1);
				String[] part = destination.split("Arrivée :");
				JSONArray jarr = Utils.setJSONArray(parts[1], Utils.getDirection(parts[2]), parts[0], 
						Integer.parseInt(parts[4].trim()), part[1], Integer.parseInt(cheminItineraire.get(2)));
				jobjroot.put("itineraire"+j, jarr);
			}else{
				for (String arret : cheminItineraire){
					if (arret.contains("|")){
						String[] parts = arret.split("[|]");
						depart = parts[0];
						metro = parts[1];
						direction = Utils.getDirection(parts[2]);
						heureDepart = Integer.parseInt(parts[4].trim());
						if (first){
							first = false;
						}else{
							heureArrivee = Integer.parseInt(parts[3].trim());
							arrivee = parts[0];
							JSONArray jarr = Utils.setJSONArray(tempMetro, tempDirection, tempDepart, tempHeureDepart, arrivee, heureArrivee);
							jobjroot.put("itineraire"+j, jarr);
							j++;
						}
						tempMetro = metro;
						tempDepart = depart;
						tempDirection = direction;
						tempHeureDepart = heureDepart;
					}
				}
				destination = cheminItineraire.get(size-2);
				String[] part = destination.split("Arrivée :");
				
				
				JSONArray jarr = Utils.setJSONArray(tempMetro, tempDirection, tempDepart, tempHeureDepart, 
						part[1], Integer.parseInt(cheminItineraire.get(size-1)));
				jobjroot.put("itineraire"+j, jarr);
			}
			return jobjroot.toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error parsing";
		}
		
		
		
	}

	
	
	
	
	
}
