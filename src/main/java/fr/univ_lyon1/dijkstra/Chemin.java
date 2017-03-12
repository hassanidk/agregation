package fr.univ_lyon1.dijkstra;

public class Chemin {

	private int arretPrecedent;
	private int arretSuivant;
	private int distance;
	
	public Chemin(int arretPrecedent, int toNodeIndex, int distance) {
		this.arretPrecedent = arretPrecedent;
		this.arretSuivant = toNodeIndex;
		this.distance = distance;
	}

	public int getFromNodeIndex() {
		return arretPrecedent;
	}

	public int getToNodeIndex() {
		return arretSuivant;
	}

	public int getLength() {
		return distance;
	}

	// determines the neighbouring node of a supplied node, based on the two
	// nodes connected by this edge
	public int getNeighbourIndex(int nodeIndex) {
		if (this.arretPrecedent == nodeIndex) {
			return this.arretSuivant;
		} else {
			return this.arretPrecedent;
		}
	}

}
