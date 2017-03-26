package fr.univ_lyon1.ter.modele;

public class Site {
	private String nom;
	
	private int ouvertureLundi;
	private int ouvertureMardi;
	private int ouvertureMercredi;
	private int ouvertureJeudi;
	private int ouvertureVendredi;
	private int ouvertureSamedi;
	private int ouvertureDimanche;
	
	private int fermetureLundi;
	private int fermetureMardi;
	private int fermetureMercredi;
	private int fermetureJeudi;
	private int fermetureVendredi;
	private int fermetureSamedi;
	private int fermetureDimanche;
	
	private int dureeVisiteMoyenne;
	private String stationTCL;
	private String typeSite;
	private int pertinence;
	
	
	public void setProperty(int i, String val){
		String value = val.replaceAll( "[\":]","");
		if (value.equals("Fermé")){
			value = "5000";
		}
			
		switch (i){
			case 1: setNom(value); break;
			case 2: setOuvertureLundi(Integer.valueOf(value));break;
			case 3: setFermetureLundi(Integer.valueOf(value));break;
			case 4: setOuvertureMardi(Integer.valueOf(value));break;
			case 5: setFermetureMardi(Integer.valueOf(value));break;
			case 6: setOuvertureMercredi(Integer.valueOf(value));break;
			case 7: setFermetureMercredi(Integer.valueOf(value));break;
			case 8: setOuvertureJeudi(Integer.valueOf(value));break;

			case 9: setFermetureJeudi(Integer.valueOf(value));break;
			case 10: setOuvertureVendredi(Integer.valueOf(value));break;
			case 11: setFermetureVendredi(Integer.valueOf(value));break;
			case 12: setOuvertureSamedi(Integer.valueOf(value));break;
			case 13: setFermetureSamedi(Integer.valueOf(value));break;
			case 14: setOuvertureDimanche(Integer.valueOf(value));break;
			case 15: setFermetureDimanche(Integer.valueOf(value));break;
	
			case 16: setDureeVisiteMoyenne(Integer.valueOf(value));break;
			case 17: setStationTCL(value);break;
			case 18: setTypeSite(value);break;
			case 19: setPertinence(Integer.parseInt(value));break;
			
			default: System.out.println("Erreur parsing");break;
		}
			
	}
	
	public int getHorraireOuverture(String jour){
		switch(jour){
			case "lundi": return getOuvertureLundi();
			case "mardi": return getOuvertureMardi();
			case "mercredi": return getOuvertureMercredi();
			case "jeudi": return getOuvertureJeudi();
			case "vendredi": return getOuvertureVendredi();
			case "samedi": return getOuvertureSamedi();
			case "dimanche": return getOuvertureDimanche();
			default: return 5000;
		}
	}
	
	
	

	@Override
	public String toString() {
		return "Site [nom=" + nom + ", ouvertureLundi=" + ouvertureLundi + ", ouvertureMardi=" + ouvertureMardi
				+ ", ouvertureMercredi=" + ouvertureMercredi + ", ouvertureJeudi=" + ouvertureJeudi
				+ ", ouvertureVendredi=" + ouvertureVendredi + ", ouvertureSamedi=" + ouvertureSamedi
				+ ", ouvertureDimanche=" + ouvertureDimanche + ", fermetureLundi=" + fermetureLundi
				+ ", fermetureMardi=" + fermetureMardi + ", fermetureMercredi=" + fermetureMercredi
				+ ", fermetureJeudi=" + fermetureJeudi + ", fermetureVendredi=" + fermetureVendredi
				+ ", fermetureSamedi=" + fermetureSamedi + ", fermetureDimanche=" + fermetureDimanche
				+ ", dureeVisiteMoyenne=" + dureeVisiteMoyenne + ", stationTCL=" + stationTCL + ", typeSite=" + typeSite
				+ ", pertinence=" + pertinence + "]";
	}

	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public int getOuvertureLundi() {
		return ouvertureLundi;
	}


	public void setOuvertureLundi(int ouvertureLundi) {
		this.ouvertureLundi = ouvertureLundi;
	}


	public int getOuvertureMardi() {
		return ouvertureMardi;
	}


	public void setOuvertureMardi(int ouvertureMardi) {
		this.ouvertureMardi = ouvertureMardi;
	}


	public int getOuvertureMercredi() {
		return ouvertureMercredi;
	}


	public void setOuvertureMercredi(int ouvertureMercredi) {
		this.ouvertureMercredi = ouvertureMercredi;
	}


	public int getOuvertureJeudi() {
		return ouvertureJeudi;
	}


	public void setOuvertureJeudi(int ouvertureJeudi) {
		this.ouvertureJeudi = ouvertureJeudi;
	}


	public int getOuvertureVendredi() {
		return ouvertureVendredi;
	}


	public void setOuvertureVendredi(int ouvertureVendredi) {
		this.ouvertureVendredi = ouvertureVendredi;
	}


	public int getOuvertureSamedi() {
		return ouvertureSamedi;
	}


	public void setOuvertureSamedi(int ouvertureSamedi) {
		this.ouvertureSamedi = ouvertureSamedi;
	}


	public int getOuvertureDimanche() {
		return ouvertureDimanche;
	}


	public void setOuvertureDimanche(int ouvertureDimanche) {
		this.ouvertureDimanche = ouvertureDimanche;
	}


	public int getFermetureLundi() {
		return fermetureLundi;
	}


	public void setFermetureLundi(int fermetureLundi) {
		this.fermetureLundi = fermetureLundi;
	}


	public int getFermetureMardi() {
		return fermetureMardi;
	}


	public void setFermetureMardi(int fermetureMardi) {
		this.fermetureMardi = fermetureMardi;
	}


	public int getFermetureMercredi() {
		return fermetureMercredi;
	}


	public void setFermetureMercredi(int fermetureMercredi) {
		this.fermetureMercredi = fermetureMercredi;
	}


	public int getFermetureJeudi() {
		return fermetureJeudi;
	}


	public void setFermetureJeudi(int fermetureJeudi) {
		this.fermetureJeudi = fermetureJeudi;
	}


	public int getFermetureVendredi() {
		return fermetureVendredi;
	}


	public void setFermetureVendredi(int fermetureVendredi) {
		this.fermetureVendredi = fermetureVendredi;
	}


	public int getFermetureSamedi() {
		return fermetureSamedi;
	}


	public void setFermetureSamedi(int fermetureSamedi) {
		this.fermetureSamedi = fermetureSamedi;
	}


	public int getFermetureDimanche() {
		return fermetureDimanche;
	}


	public void setFermetureDimanche(int fermetureDimanche) {
		this.fermetureDimanche = fermetureDimanche;
	}


	public int getDureeVisiteMoyenne() {
		return dureeVisiteMoyenne;
	}


	public void setDureeVisiteMoyenne(int dureeVisiteMoyenne) {
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
