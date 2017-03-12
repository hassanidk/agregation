package fr.univ_lyon1.ter.utilitaire;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import fr.univ_lyon1.dijkstra.Chemin;
import fr.univ_lyon1.dijkstra.Chemin;
/**
 * 
 * @author loki
 * Classe utilitaire permettant d'eviter la duplication de variable
 *
 */
public class Utils {
	
	static final String file_horraire_tcl = System.getProperty("user.dir")+ "/resources/HorraireTCL.nt";
	/**
	 * _b_vers_jean_mace : contient la liste des arrêts du métro b 
	 * 					   allant de la part-dieu à jean mace
	 */
	@SuppressWarnings("serial")
	static final ArrayList<String> metro_d = new ArrayList<String>() {{
		add("guillotiere");
		add("saxe_gambetta");
		add("garibaldi");
	
	}};
	
	@SuppressWarnings("serial")
	static final ArrayList<String> metro_b = new ArrayList<String>() {{
		add("part_dieu");
		add("place_guichard");
		add("saxe_gambetta");
		add("jean_mace");
	
	}};

	// Explications tableaux dans readme
	static final int[][] distance_horaire_b = new int[][] {
		{0,1,0,0},
		{1,0,1,0},
		{0,1,0,1},
		{0,0,1,0}
	};
	
	static final int[][] distance_horaire_d = new int[][]{
		{0,1,0},
		{1,0,1},
		{0,1,0}
	};
		
	/**
	 * Permet d'avoir la topologie du réseau
	 */
	@SuppressWarnings("serial")
	static final HashMap<String, ArrayList<String>> listeArret = new HashMap<String,ArrayList<String>>(){{
		put("metro_b", metro_b);
		put("metro_d", metro_d);
	}};
	

	
	/**
	 * 
	 * @param sens , correspond au 
	 * @param metro
	 * @param i
	 * @return la duree entre deux stations
	 */
	public static int timeByMetro(boolean sens, String metro,int i){
		
		int j = i;
		if (sens){	
			j++;
		}else{
			j--;
		}
		switch (metro){
			case "metro_b":
				return distance_horaire_b[i][j];
			case "metro_d":
				return distance_horaire_d[i][j];
			default:
				return -1;
		}		
	}
	/**
	 * Modelisation du reseau TCL par un graph 
	 * 0 = Part-Dieu, 1= Place-Guichard , 2 =Saxe-Gambetta, 3 = Jean-Mace, 
	 * 4=Garibaldi, 5=Guilotiere, 6 ...
	 * 
	 */

	public static final Chemin[] listeArretDK  = {
			new Chemin(0,1,1),
			new Chemin(1,2,1),
			new Chemin(2,3,1),
			new Chemin(2,4,1),
			new Chemin(2,5,1)
	};
	
	public static final String[] nomArret = new String[] 
			{"Part-Dieu", "Place-Guichard", "Saxe-Gambetta",
					"Jean-Mace", "Garibaldi","Guilotiere"};
	/**
	 * 
	 * @param arrivee, correspond à la date d'arrivée de la personne en gare
	 * @return 5 si il s'agit d'un jour de semaine, 10 week end
	 */
	public static int getPeriode(Calendar arrivee){
		int jour = arrivee.get(Calendar.DAY_OF_WEEK);
		if (jour >= Calendar.FRIDAY && jour <= Calendar.MONDAY)
			return 5;
		return 10;
	}
	
	public static int checkHour(int hours){
		int heure = hours/100;
		int minute = hours %100;
		
		if (minute >=60){
			minute = minute -60;
			heure++;
			if (heure>=23){
				heure = 0;
			}
		}
		return heure*100+minute;
	}
	
	public static int getHour(int hours){
		return hours/100;
	}
	
	public static int getMin(int hours){
		return hours%100;
	}
	
	
	
	
}
