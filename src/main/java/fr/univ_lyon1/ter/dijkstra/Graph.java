package fr.univ_lyon1.ter.dijkstra;

import java.util.ArrayList;

import fr.univ_lyon1.ter.utilitaire.Utils;

public class Graph {

	private Arret[] arrets;
	private int nbArrets;
	private Chemin[] chemins;
	private int nbChemins;
	/**
	 * 
	 * @param chemins liste de tous les liens du réseau
	 */
	public Graph(Chemin[] chemins) {
		this.chemins = chemins;
		this.nbChemins = chemins.length;
		this.nbArrets = getNbArrets(chemins);
		this.arrets = new Arret[this.nbArrets];
		
		//On crée les différents arrêts
		for (int n = 0; n < nbArrets; n++) {
			arrets[n] = new Arret();
		}
		//ON relie les différents arrêts
		for (int cheminAjout = 0; cheminAjout < nbChemins; cheminAjout++) {
			arrets[chemins[cheminAjout].getFromNodeIndex()].getChemins().add(chemins[cheminAjout]);
			arrets[chemins[cheminAjout].getToNodeIndex()].getChemins().add(chemins[cheminAjout]);
		}

	}
	
	/**
	 * 
	 * @param chemins, la liste de tous les liens du réseau
	 * @return le nombre d'arrêts du réseau de transport
	 */
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

	/**
	 * 
	 * @param arretDepart : Sommet sur lequel on souhaite utiliser l'algorithme
	 */
	public void plusCourtChemin(int arretDepart, int arretArrivee) {
		// On vérifie si arretDepart existe

		if (arretDepart< nbArrets || arretDepart >0){
			arrets[arretDepart].setDistanceFromSource(0);
			arrets[arretDepart].setUtilisee(true);
			int arretSuivant = arretDepart;
	
			// Parcours de tous les sommets(arrets)
			for (int i = 0; i < this.arrets.length; i++) {
				// Boucle sur tous les liens de l'arret actuel
				ArrayList<Chemin> currentNodeEdges = arrets[arretSuivant].getChemins();
				for (int joinedEdge = 0; joinedEdge < currentNodeEdges.size(); joinedEdge++) {
					int neighbourIndex = currentNodeEdges.get(joinedEdge).getNeighbourIndex(arretSuivant);
					if (!arrets[neighbourIndex].isVisitee()) {
						int tentative = arrets[arretSuivant].getDistanceFromSource()
								+ currentNodeEdges.get(joinedEdge).getLength();
						if (tentative < arrets[neighbourIndex].getDistanceFromSource()){
							arrets[neighbourIndex].setDistanceFromSource(tentative);
						}
						
					}
				}
				// Tous les voisins ont été visités, donc le sommet(arret) est visité
				arrets[arretSuivant].setVisitee(true);
		
				// On souhaite se rendre a l'arretSuivant avec la plus petite distance
				
				arretSuivant = getNodeShortestDistanced();
				
			}
			
			printResult(arretDepart, arretArrivee);
			
		}else{
			System.out.println("Erreur numéro arrêt");
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

	// Affichage résultat du plus court chemin entre A et D
	private void printResult(int arretDepart, int arretArrivee) {
		String output = "Nombre d'arrêts = " + nbArrets;
		for (int i = 0; i<nbChemins;++i){
		//	output += ("\nLa plus courte distance de l'arrêt "+Utils.nomArret[arretDepart]+" à l'arret " + Utils.nomArret[i] + " est de " + arrets[arretArrivee].getDistanceFromSource());
			System.out.println(output);
		}
	
		
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