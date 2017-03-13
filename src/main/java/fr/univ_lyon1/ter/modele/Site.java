package fr.univ_lyon1.ter.modele;

import java.util.Calendar;

public class Site {
	private String nom;
	
	private String ouvertureLundi;
	private String ouvertureMardi;
	private String ouvertureMercredi;
	private String ouvertureJeudi;
	private String ouvertureVendredi;
	private String ouvertureSamedi;
	private String ouvertureDimanche;
	
	private String fermetureLundi;
	private String fermetureMardi;
	private String fermetureMercredi;
	private String fermetureJeudi;
	private String fermetureVendredi;
	private String fermetureSamedi;
	private String fermetureDimanche;
	
	private String dureeVisiteMoyenne;
	private String stationTCL;
	private String typeSite;
	private int pertinence;
	
	
	public void setPropery(int i, String val){
		String value = val.replaceAll("\"", "");
		switch (i){
			case 1: setNom(value); break;
			case 2: setOuvertureLundi(value);break;
			case 3: setOuvertureMardi(value);break;
			case 4: setOuvertureMercredi(value);break;
			case 5: setOuvertureJeudi(value);break;
			case 6: setOuvertureVendredi(value);break;
			case 7: setOuvertureSamedi(value);break;
			case 8: setOuvertureDimanche(value);break;
	
			case 9: setFermetureLundi(value);break;
			case 10: setFermetureMardi(value);break;
			case 11: setFermetureMercredi(value);break;
			case 12: setFermetureJeudi(value);break;
			case 13: setFermetureVendredi(value);break;
			case 14: setFermetureSamedi(value);break;
			case 15: setFermetureDimanche(value);break;
	
			case 16: setDureeVisiteMoyenne(value);break;
			case 17: setStationTCL(value);break;
			case 18: setTypeSite(value);break;
			case 19: setPertinence(Integer.parseInt(value));break;
			
			default: System.out.println("Erreur parsing");break;
		}
			
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getOuvertureLundi() {
		return ouvertureLundi;
	}

	public void setOuvertureLundi(String ouvertureLundi) {
		this.ouvertureLundi = ouvertureLundi;
	}

	public String getOuvertureMardi() {
		return ouvertureMardi;
	}

	public void setOuvertureMardi(String ouvertureMardi) {
		this.ouvertureMardi = ouvertureMardi;
	}

	public String getOuvertureMercredi() {
		return ouvertureMercredi;
	}

	public void setOuvertureMercredi(String ouvertureMercredi) {
		this.ouvertureMercredi = ouvertureMercredi;
	}

	public String getOuvertureJeudi() {
		return ouvertureJeudi;
	}

	public void setOuvertureJeudi(String ouvertureJeudi) {
		this.ouvertureJeudi = ouvertureJeudi;
	}

	public String getOuvertureVendredi() {
		return ouvertureVendredi;
	}

	public void setOuvertureVendredi(String ouvertureVendredi) {
		this.ouvertureVendredi = ouvertureVendredi;
	}

	public String getOuvertureSamedi() {
		return ouvertureSamedi;
	}

	public void setOuvertureSamedi(String ouvertureSamedi) {
		this.ouvertureSamedi = ouvertureSamedi;
	}

	public String getOuvertureDimanche() {
		return ouvertureDimanche;
	}

	public void setOuvertureDimanche(String ouvertureDimanche) {
		this.ouvertureDimanche = ouvertureDimanche;
	}

	public String getFermetureLundi() {
		return fermetureLundi;
	}

	public void setFermetureLundi(String fermetureLundi) {
		this.fermetureLundi = fermetureLundi;
	}

	public String getFermetureMardi() {
		return fermetureMardi;
	}

	public void setFermetureMardi(String fermetureMardi) {
		this.fermetureMardi = fermetureMardi;
	}

	public String getFermetureMercredi() {
		return fermetureMercredi;
	}

	public void setFermetureMercredi(String fermetureMercredi) {
		this.fermetureMercredi = fermetureMercredi;
	}

	public String getFermetureJeudi() {
		return fermetureJeudi;
	}

	public void setFermetureJeudi(String fermetureJeudi) {
		this.fermetureJeudi = fermetureJeudi;
	}

	public String getFermetureVendredi() {
		return fermetureVendredi;
	}

	public void setFermetureVendredi(String fermetureVendredi) {
		this.fermetureVendredi = fermetureVendredi;
	}

	public String getFermetureSamedi() {
		return fermetureSamedi;
	}

	public void setFermetureSamedi(String fermetureSamedi) {
		this.fermetureSamedi = fermetureSamedi;
	}

	public String getFermetureDimanche() {
		return fermetureDimanche;
	}

	public void setFermetureDimanche(String fermetureDimanche) {
		this.fermetureDimanche = fermetureDimanche;
	}

	public String getDureeVisiteMoyenne() {
		return dureeVisiteMoyenne;
	}

	public void setDureeVisiteMoyenne(String dureeVisiteMoyenne) {
		this.dureeVisiteMoyenne = dureeVisiteMoyenne;
	}

	public String getStationTCL() {
		return stationTCL;
	}

	public void setStationTCL(String stationTCL) {
		this.stationTCL = stationTCL;
	}

	public String getTypeSite() {
		return typeSite;
	}

	public void setTypeSite(String typeSite) {
		this.typeSite = typeSite;
	}

	public int getPertinence() {
		return pertinence;
	}

	public void setPertinence(int pertinence) {
		this.pertinence = pertinence;
	}

	
	
	
}
