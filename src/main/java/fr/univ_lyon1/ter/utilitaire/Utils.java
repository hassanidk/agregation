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
	
	@SuppressWarnings("serial")
	static final ArrayList<String> metro_a = new ArrayList<String>() {{
		add("perrache");
		add("ampere");
		add("bellecour");
		add("cordeliers");
		add("hotel_de_ville");
		add("foch");
		add("massena");
		add("charpennes");
		add("republique");
		add("gratte_ciel");
		add("flachet");
		add("cusset");
		add("laurent_bonnevay");
		add("vaux_la_soie");
	}};
	
	@SuppressWarnings("serial")
	static final ArrayList<String> metro_b = new ArrayList<String>() {{
		add("charpennes");
		add("brotteaux");
		add("part_dieu");
		add("place_guichard");
		add("saxe_gambetta");
		add("jean_mace");
		add("place_jean_jaures");
		add("debourg");
		add("stade_gerland");
		add("gare_oullins");
	
	}};
	@SuppressWarnings("serial")
	static final ArrayList<String> metro_c = new ArrayList<String>() {{
		add("hotel_de_ville");
		add("croix_paquet");
		add("croix_rousse");
		add("henon");
		add("cuire");
	}};
	@SuppressWarnings("serial")
	static final ArrayList<String> metro_d = new ArrayList<String>() {{
		add("gare_vaise");
		add("valmy");
		add("gorge_de_loup");
		add("vieux_lyon");
		add("bellecour");
		add("guillotiere");
		add("saxe_gambetta");
		add("garibaldi");
		add("sans_souci");
		add("monplaisir_lumiere");
		add("grange_blache");
		add("laennec");
		add("mermoz_pinel");
		add("parilly");
		add("gare_venissieux");
	
	}};


	// Explications tableaux dans dossiers ressources
	static final int[][] distance_horaire_a = new int[][]{
		{0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0}

	};
	static final int[][] distance_horaire_b = new int[][] {
		{0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
		{1, 0, 1, 0, 0, 0, 0, 0, 0, 0},
		{0, 1, 0, 1, 0, 0, 0, 0, 0, 0},
		{0, 0, 1, 0, 1, 0, 0, 0, 0, 0},
		{0, 0, 0, 1, 0, 1, 0, 0, 0, 0},
		{0, 0, 0, 0, 1, 0, 1, 0, 0, 0},
		{0, 0, 0, 0, 0, 1, 0, 1, 0, 0},
		{0, 0, 0, 0, 0, 0, 1, 0, 1, 0},
		{0, 0, 0, 0, 0, 0, 0, 1, 0, 1},
		{0, 0, 0, 0, 0, 0, 0, 0, 1, 0}

	};
	
	static final int[][] distance_horaire_c = new int[][]{
		{0, 1, 0, 0, 0},
		{1, 0, 1, 0, 0},
		{0, 1, 0, 1, 0},
		{0, 0, 1, 0, 1},
		{0, 0, 0, 1, 0}

	};
	
	static final int[][] distance_horaire_d = new int[][]{
		{0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0}

	};
		
	/**
	 * Permet d'avoir la topologie du réseau
	 */
	@SuppressWarnings("serial")
	static final HashMap<String, ArrayList<String>> listeArret = new HashMap<String,ArrayList<String>>(){{
		put("metro_a", metro_a);
		put("metro_b", metro_b);
		put("metro_c", metro_c);
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
			case "metro_a":
				return distance_horaire_a[i][j];
			case "metro_b":
				return distance_horaire_b[i][j];
			case "metro_c":
				return distance_horaire_c[i][j];
			case "metro_d":
				return distance_horaire_d[i][j];
			default:
				return -1;
		}		
	}
	/**
	 * Modelisation du reseau TCL par un graph 
	 * 0 = Perrache, 1=AmpereVictorHugo, 2=Bellcour...
	 * NOTE : Utiliser le tableau des horaires à la place de mettre 1 !!!
	 */

	public static final Chemin[] listeArretDK  = {
			//2 = Bellcour
			//7 = Charpennes
			//17 = Saxe Gambetta
			
			// Metro A
			new Chemin(0,1,1),
			new Chemin(1,2,1),
			new Chemin(2,3,1),
			new Chemin(3,4,1),
			new Chemin(4,5,1),
			new Chemin(5,6,1),
			new Chemin(6,7,1),
			new Chemin(7,8,1),
			new Chemin(8,9,1),
			new Chemin(9,10,1),
			new Chemin(10,11,1),
			new Chemin(11,12,1),
			new Chemin(12,13,1),
			//Metro B
			
			new Chemin(7,14,1),
			new Chemin(14,15,1),
			new Chemin(15,16,1),
			new Chemin(16,17,1),
			new Chemin(17,18,1),
			new Chemin(18,19,1),
			new Chemin(19,20,1),
			new Chemin(20,21,1),
			new Chemin(21,22,1),
			//MEtro C
			new Chemin(4,23,1),
			new Chemin(23,24,1),
			new Chemin(24,25,1),
			new Chemin(25,26,1),
			
			//Metro D
			new Chemin(27,28,1),
			new Chemin(28,29,1),
			new Chemin(29,30,1),
			new Chemin(30,2,1),
			new Chemin(2,31,1),
			new Chemin(31,17,1),
			new Chemin(17,32,1),
			new Chemin(32,33,1),
			new Chemin(33,34,1),
			new Chemin(34,35,1),
			new Chemin(35,36,1),
			new Chemin(36,37,1),
			new Chemin(37,38,1),
			new Chemin(38,39,1),

	};
	// On associe le nom d'un arrêt à un indice
	public static final String[] nomArret = new String[] 
			{"Perrache", "Ampère - Victor Hugo", "Bellecour", "Cordeliers", "Hôtel de Ville - Louis Pradel",
					"Foch", "Massena", "Charpennes", " République", "Gratte_ciel", "Flachet",
					"Cusset", "Laurent Bonnevay", "Vaulx-en-Velin - La Soie",
			//Ligne B (Sans doublons)
				"Brotteaux", "Part-Dieu", "Place Guichard", "Saxe-Gambetta",
			 		"Jean-Macé", "Place Jean-Jaures", "Debourg", "Stade de Gerland", "Gare d'Oullins",
			//Ligne C(Sans doublons)
			 	"Croix-Paquet", "Croix-Rousse", "Henon", "Cuire",
			//Ligne D(Sans doublons)
			 	"Gare de Vaise", "Valmy", "Gorge de Loup", "Vieux lyon", "Guillotiere", "Garibaldi",
			 	"Sans-Souci", "Monplaisir-Lumiere", "Grange-Blache", "Laennec", "Mermoz-Pinel", "Parilly", "Gare de Venissieux"
			};

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
