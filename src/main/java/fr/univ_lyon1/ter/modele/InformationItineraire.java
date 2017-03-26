package fr.univ_lyon1.ter.modele;

import java.util.List;

import fr.univ_lyon1.ter.utilitaire.Utils;

public class InformationItineraire {
	private int heureItineraire;
	private String nomSite;
	private String arretFrom;
	private String arretSite;
	private List<String> cheminItineraire;
	private int coutItineraire;
	private int dureeVisite;
	
	/**
	 * 
	 * @param heureItineraire, heure de depart à laquelle on souhaite se rendre vers le site I
	 * @param nomSite , arret dusite touristique
	 * @param cheminItineraire chemin de l'itineraire entre un point X de depart et le site touristique
	 * @param coutItineraire cout (heure/minute) de l'itineraire + dureedevisite de site (Correspond a l'heure ou on quitte le site)
	 */
	public InformationItineraire(int heureItineraire,String nomSite,String arretFrom, String arretSite, List<List<String>> cheminItineraire, int coutItineraire, int dureeVisite) {
		
		this.heureItineraire = heureItineraire;
		this.nomSite = nomSite;
		this.arretFrom = arretFrom;
		this.arretSite =arretSite;
		this.cheminItineraire = cheminItineraire.get(0);
		this.coutItineraire = coutItineraire;
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

	public int getCoutItineraire() {
		return coutItineraire;
	}

	public void setCoutItineraire(int coutItineraire) {
		this.coutItineraire = coutItineraire;
	}

	@Override
	public String toString() {
		String txt="Pour vous rendre au site: "+nomSite+"\n";
		boolean verifArret = false;
		boolean firstElem = true;
		String elemPrec ="";
		for (String elem : cheminItineraire){
			if (verifArret){
				if (firstElem){
					txt = txt+" à l'arret "+elem;
					firstElem = false;
				}else{
					
				}
				verifArret = false;
			}
			if (elem.contains("Direction")){
				if (firstElem){
					txt = txt+ "Prenez le "+elem;
				}else{
					txt = txt+"\nDescendez à l'arrêt "+elemPrec+" et prenez le "+elem;
				}
				if (firstElem ==false)
					txt = txt+" à l'arret "+elemPrec;
			}
			if (elem.matches(".*\\d+.*")){
				int heures = Integer.valueOf(elem);
				txt = txt+ " à "+Utils.getHour(heures)+"h"+ Utils.getMin(heures);
				verifArret = true;
			}
			elemPrec = elem;
		}
		if (dureeVisite !=0){
			txt = txt+"\nDescendez à l'arret "+arretSite+". Le site est à quelques minutes de l'arrêt de métro.\nNous vous conseillons d'y rester "+ dureeVisite+" minutes, afin de profiter au maximum de la Ville de Lyon";
		}else{
			txt = txt+"\nDescendez à la Gare-Part Dieu. Nous vous souhaitons un agréable voyage, et esperons que vous revoir au plus vite";
		}
		return txt+"\n";
//		return "InformationItineraire [heureItineraire=" + heureItineraire + ", nomSite=" + nomSite + ", arretFrom="
//				+ arretFrom + ", arretSite=" + arretSite + ", cheminItineraire=" + cheminItineraire
//				+ ", coutItineraire=" + coutItineraire + "]";
	}

	
	
	
	
	
}
