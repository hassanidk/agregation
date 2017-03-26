package fr.univ_lyon1.ter.modele;

import java.util.List;

public class InformationItineraire {
	private int heureItineraire;
	private String nomSite;
	private String arretFrom;
	private String arretSite;
	private List<String> cheminItineraire;
	private int coutItineraire;
	
	/**
	 * 
	 * @param heureItineraire, heure de depart à laquelle on souhaite se rendre vers le site I
	 * @param nomSite , arret dusite touristique
	 * @param cheminItineraire chemin de l'itineraire entre un point X de depart et le site touristique
	 * @param coutItineraire cout (heure/minute) de l'itineraire + dureedevisite de site (Correspond a l'heure ou on quitte le site)
	 */
	public InformationItineraire(int heureItineraire,String nomSite,String arretFrom, String arretSite, List<List<String>> cheminItineraire, int coutItineraire) {
		
		this.heureItineraire = heureItineraire;
		this.nomSite = nomSite;
		this.arretFrom = arretFrom;
		this.arretSite =arretSite;
		this.cheminItineraire = cheminItineraire.get(0);
		this.coutItineraire = coutItineraire;
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
		return "InformationItineraire [heureItineraire=" + heureItineraire + ", nomSite=" + nomSite + ", arretFrom="
				+ arretFrom + ", arretSite=" + arretSite + ", cheminItineraire=" + cheminItineraire
				+ ", coutItineraire=" + coutItineraire + "]";
	}

	
	
	
	
	
}
