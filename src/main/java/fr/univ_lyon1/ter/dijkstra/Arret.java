package fr.univ_lyon1.ter.dijkstra;

import java.util.ArrayList;

public class Arret {

	private int distanceFromSource = Integer.MAX_VALUE;
	private boolean visitee;
	private boolean utilisee;
	private ArrayList<Chemin> chemins = new ArrayList<Chemin>(); 

	public int getDistanceFromSource() {
		return distanceFromSource;
	}

	public void setDistanceFromSource(int distanceFromSource) {
		this.distanceFromSource = distanceFromSource;
	}

	public boolean isVisitee() {
		return visitee;
	}
	public boolean isUtilisee(){
		return utilisee;
	}
	
	public void setVisitee(boolean visitee) {
		this.visitee = visitee;
	}
	
	public void setUtilisee(boolean utilisee) {
		this.utilisee = utilisee;
	}

	public ArrayList<Chemin> getChemins() {
		return chemins;
	}

	public void setChemins(ArrayList<Chemin> chemins) {
		this.chemins = chemins;
	}

}