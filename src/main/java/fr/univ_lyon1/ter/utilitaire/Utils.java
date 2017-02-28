package fr.univ_lyon1.ter.utilitaire;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
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
	static final ArrayList<String> _b_vers_jean_mace = new ArrayList<String>() {{
		add("part_dieu");
		add("place_guichard");
		add("saxe_gambetta");
		add("jean_mace");
	
	}};
	/**
	 * _b_vers_part_dieu: contient la liste des arrêts du métro b 
	 * 					  allant de jean-mace à la part dieu
	 */
	@SuppressWarnings("serial")
	static final ArrayList<String> _b_vers_part_dieu = new ArrayList<String>(){{
		add("jean_mace");
		add("saxe_gambetta");
		add("place_guichard");
		add("part_dieu");
		
	}};
	
	/**
	 * _d_vers_*: ArrayLists contenant la liste des arrêts du métro d
	 */
	@SuppressWarnings("serial")
	static final ArrayList<String> _d_vers_garibaldi = new ArrayList<String>() {{
		add("guillotiere");
		add("saxe_gambetta");
		add("garibaldi");
	
	}};
	
	@SuppressWarnings("serial")
	static final ArrayList<String> _d_vers_guillotiere = new ArrayList<String>() {{
		add("garibaldi");
		add("saxe_gambetta");
		add("guillotiere");
	
	}};
	
	/**
	 * 
	 * @param metro, la ligne de métro dont on souhaite l'horraire
	 * @param i, le i eme arrêt de la ligne de metro dont on souhaite l'horraire à partir du depart (Ou terminus)
	 * @return le temps de parcours du départ à la station i
	 */
	public static int timeByMetro(String metro,int i){
		if (metro.contains("metro_b")){
			if (metro.contains("vers_jean_mace")){
				return temps_parcours_b[i];
			}else{
				return Math.abs(temps_parcours_b[temps_parcours_b.length-1] - temps_parcours_b[i]) ;
			}
		}
		
		if (metro.contains("metro_d")){
			if (metro.contains("vers_garibaldi")){
				return temps_parcours_d[i];
			}else{
				return Math.abs(temps_parcours_d[temps_parcours_d.length-1] - temps_parcours_d[i]) ;
			}
		}
		
		
		return 0;
			
		
			
	}
	// Temps de parcours de la part-dieu à jean macé (2 unités de temps por aller de part dieu à saxe
	static final int[] temps_parcours_b = new int[] {0,1,2,3};
	// Temps de parcours de garibaldi a guilotiere
	static final int[] temps_parcours_d = new int[] {0,1,2};
	;

	/**
	 * Permet d'avoir la topologie du réseau
	 */
	@SuppressWarnings("serial")
	static final HashMap<String, ArrayList<String>> listeArret = new HashMap<String,ArrayList<String>>(){{
		put("metro_b_vers_jean_mace", _b_vers_jean_mace);
		put("metro_b_vers_part_dieu", _b_vers_part_dieu);
		put("metro_d_vers_garibaldi", _d_vers_garibaldi);
		put("metro_d_vers_guillotiere", _d_vers_guillotiere);
			
	}};
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
