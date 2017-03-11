package fr.univ_lyon1.dijkstra;

import java.util.ArrayList;

//now we must create graph object and implement dijkstra algorithm
public class Graph {

	private Arret[] arrets;
	private int nbArrets;
	private Chemin[] chemins;
	private int nbChemins;

	public Graph(Chemin[] chemins) {
		this.chemins = chemins;

		// create all nodes ready to be updated with the edges
		this.nbArrets = getNbArrets(chemins);
		this.arrets = new Arret[this.nbArrets];

		for (int n = 0; n < this.nbArrets; n++) {
			this.arrets[n] = new Arret();
		}

		// add all the edges to the nodes, each edge added to two nodes (to and
		// from)
		this.nbChemins = chemins.length;

		for (int cheminAjout = 0; cheminAjout < this.nbChemins; cheminAjout++) {
			this.arrets[chemins[cheminAjout].getFromNodeIndex()].getChemins().add(chemins[cheminAjout]);
			this.arrets[chemins[cheminAjout].getToNodeIndex()].getChemins().add(chemins[cheminAjout]);
		}

	}

	private int getNbArrets(Chemin[] chemins) {
		int nbArret = 0;

		for (Chemin e : chemins) {
			if (e.getToNodeIndex() > nbArret)
				nbArret = e.getToNodeIndex();
			if (e.getFromNodeIndex() > nbArret)
				nbArret = e.getFromNodeIndex();
		}
		nbArret++;
		return nbArret;
	}

	// next video to implement the Dijkstra algorithm !!!
	public void calculateShortestDistances() {
		// node 0 as source
		this.arrets[0].setDistanceFromSource(0);
		int arretSuivant = 0;

		// visit every node
		for (int i = 0; i < this.arrets.length; i++) {
			// loop around the edges of current node
			ArrayList<Chemin> currentNodeEdges = this.arrets[arretSuivant].getChemins();

			for (int joinedEdge = 0; joinedEdge < currentNodeEdges.size(); joinedEdge++) {
				int neighbourIndex = currentNodeEdges.get(joinedEdge).getNeighbourIndex(arretSuivant);

				// only if not visited
				if (!this.arrets[neighbourIndex].isVisitee()) {
					int tentative = this.arrets[arretSuivant].getDistanceFromSource()
							+ currentNodeEdges.get(joinedEdge).getLength();

					if (tentative < arrets[neighbourIndex].getDistanceFromSource()) {
						arrets[neighbourIndex].setDistanceFromSource(tentative);
					}
				}
			}

			// all neighbours checked so node visited
			arrets[arretSuivant].setVisitee(true);

			// next node must be with shortest distance
			arretSuivant = getNodeShortestDistanced();

		}
	}

	// now we're going to implement this method in next part !
	private int getNodeShortestDistanced() {
		int storedNodeIndex = 0;
		int storedDist = Integer.MAX_VALUE;

		for (int i = 0; i < this.arrets.length; i++) {
			int currentDist = this.arrets[i].getDistanceFromSource();

			if (!this.arrets[i].isVisitee() && currentDist < storedDist) {
				storedDist = currentDist;
				storedNodeIndex = i;
			}
		}

		return storedNodeIndex;
	}

	// display result
	public void printResult() {
		String output = "Number of nodes = " + this.nbArrets;
		output += "\nNumber of edges = " + this.nbChemins;

		for (int i = 0; i < this.arrets.length; i++) {
			output += ("\nThe shortest distance from node 0 to node " + i + " is " + arrets[i].getDistanceFromSource());
		}

		System.out.println(output);
	}

	public Arret[] getNodes() {
		return arrets;
	}

	public int getNoOfNodes() {
		return nbArrets;
	}

	public Chemin[] getEdges() {
		return chemins;
	}

	public int getNoOfEdges() {
		return nbChemins;
	}

}