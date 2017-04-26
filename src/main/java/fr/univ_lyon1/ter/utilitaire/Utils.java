package fr.univ_lyon1.ter.utilitaire;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.jsonldjava.core.Context;

import es.usc.citius.hipster.graph.GraphBuilder;
import es.usc.citius.hipster.graph.HipsterGraph;
/**
 * 
 * @author loki
 * Classe utilitaire permettant d'eviter la duplication de variable
 *
 */
public class Utils {
	
	public static final String file_horraire_tcl = System.getProperty("user.dir") + "/resources/HorraireTCL.nt";
	public static final String file_sites_lyon = System.getProperty("user.dir") + "/resources/SitesLyon.nt";
	public static boolean _DEBUG_MODE = false;
	
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
	 */

	public static HipsterGraph<String,Double> reseauTCL = 
			GraphBuilder.<String,Double>create()
			.connect("Perrache").to("Ampère Victor Hugo").withEdge(1d)
			.connect("Ampère Victor Hugo").to("Bellecour").withEdge(1d)
			.connect("Bellecour").to("Cordeliers").withEdge(1d)
			.connect("Cordeliers").to("Hôtel de Ville").withEdge(1d)
			.connect("Hôtel de Ville").to("Foch").withEdge(1d)
			.connect("Foch").to("Massena").withEdge(1d)
			.connect("Massena").to("Charpennes").withEdge(1d)
			.connect("Charpennes").to("République").withEdge(1d)
			.connect("République").to("Gratte-Ciel").withEdge(1d)
			.connect("Gratte-Ciel").to("Flachet").withEdge(1d)
			.connect("Flachet").to("Cusset").withEdge(1d)
			.connect("Cusset").to("Laurent Bonnevay").withEdge(1d)
			.connect("Laurent Bonnevay").to("Vaulx La Soie").withEdge(1d)

			.connect("Charpennes").to("Brotteaux").withEdge(1d)
			.connect("Brotteaux").to("Part Dieu").withEdge(1d)
			.connect("Part Dieu").to("Place Guichard").withEdge(1d)
			.connect("Place Guichard").to("Saxe Gambetta").withEdge(1d)
			.connect("Saxe Gambetta").to("Jean Macé").withEdge(1d)
			.connect("Jean Macé").to("Place Jean-Jaures").withEdge(1d)
			.connect("Place Jean-Jaures").to("Debourg").withEdge(1d)
			.connect("Debourg").to("Stade de Gerland").withEdge(1d)
			.connect("Stade de Gerland").to("Gare d Oullins").withEdge(1d)

			.connect("Hôtel de Ville").to("Croix-Paquet").withEdge(1d)
			.connect("Croix-Paquet").to("Croix-Rousse").withEdge(1d)
			.connect("Croix-Rousse").to("Henon").withEdge(1d)
			.connect("Henon").to("Cuire").withEdge(1d)

			.connect("Gare de Vaise").to("Valmy").withEdge(1d)
			.connect("Valmy").to("Gorge de Loup").withEdge(1d)
			.connect("Gorge de Loup").to("Vieux lyon").withEdge(1d)
			.connect("Vieux lyon").to("Bellecour").withEdge(1d)
			.connect("Bellecour").to("Guillotiere").withEdge(1d)
			.connect("Guillotiere").to("Saxe Gambetta").withEdge(1d)
			.connect("Saxe Gambetta").to("Garibaldi").withEdge(1d)
			.connect("Garibaldi").to("Sans-Souci").withEdge(1d)
			.connect("Sans-Souci").to("Monplaisir-Lumiere").withEdge(1d)
			.connect("Monplaisir-Lumiere").to("Grange-Blache").withEdge(1d)
			.connect("Grange-Blache").to("Laennec").withEdge(1d)
			.connect("Laennec").to("Mermoz-Pinel").withEdge(1d)
			.connect("Mermoz-Pinel").to("Parilly").withEdge(1d)
			.connect("Parilly").to("Gare de Venissieux").withEdge(1d)

			.createUndirectedGraph();

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
	public static int getIntHour(String hours){
		String value = hours.replaceAll( "[\"]","");
		String[] parts = value.split(":");
		int hour = Integer.valueOf(parts[0]);
		int min = Integer.valueOf(parts[1]);
		return hour*100+min;
		
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
	
	public static String getDay(Calendar date){
		int jour = date.get(Calendar.DAY_OF_WEEK);
		switch (jour){
			case Calendar.MONDAY: return "lundi";
			case Calendar.TUESDAY: return "mardi";
			case Calendar.WEDNESDAY: return "mercredi";
			case Calendar.THURSDAY: return "jeudi";
			case Calendar.FRIDAY: return "vendredi";
			case Calendar.SATURDAY: return "samedi";
			case Calendar.SUNDAY: return "dimanche";
			default: return "Erreur parsing";
			
		}
	}
	
	public static int getHeure(Calendar date){
		return date.get(Calendar.HOUR_OF_DAY)*100 + date.get(Calendar.MINUTE);
	}
	/**
	 * 
	 * @param arretPrec
	 * @param arretActuel
	 * @return la ligne de métro utilisée
	 */
	public static String chkMetro(String arretPrec, String arretActuel){
		arretPrec = convertToURLStyle(arretPrec);
		arretActuel = convertToURLStyle(arretActuel);
		switch(arretPrec){
			case "saxe_gambetta": 	if(arretActuel.equals("place_guichard") || arretActuel.equals("jean_mace")){
										return "Metro B";
									}else{
										return "Metro D";
									}
			case "bellecour":		if(arretActuel.equals("ampere_victor_hugo") || arretActuel.equals("cordeliers")){
										return "Metro A";
									}else{
										return "Metro D";
									}
			case "hotel_de_ville":	if(arretActuel.equals("cordeliers") || arretActuel.equals("foch")){
										return "Metro A";
									}else{
										return "Metro C";
									}
			case "charpennes":		if(arretActuel.equals("massena") || arretActuel.equals("republique")){
										return "Metro A";
									}else{
										return "Metro B";
									}
			default: break;
		}
		for (Map.Entry<String, ArrayList<String>> entry : listeArret.entrySet()) {
		    String nomMetro = entry.getKey();
		    ArrayList<String> arretsMetro = entry.getValue();
		    if (arretsMetro.contains(arretPrec))
		    	return convertFromURLStyle(nomMetro);
		}


				
		return "error";
	}
	/**
	 * 
	 * @param metro
	 * @param arretPrec
	 * @param arretSuiv
	 * @return la direction du metro
	 */
	public static String getDirectionMetro(String metro, String arretPrec, String arretSuiv){
		String metroURL = convertToURLStyle(metro);
		arretPrec = convertToURLStyle(arretPrec);
		arretSuiv = convertToURLStyle(arretSuiv);
		int index;
		switch(metroURL){
			case "metro_a": index = metro_a.indexOf((String) arretPrec);
							if (metro_a.get(index+1).equals(arretSuiv) || index > metro_a.size()){
								return "Vaulx La Soie";
							}else{
								return "Perrache";
							}
			case "metro_b": index = metro_b.indexOf((String) arretPrec );
							if (metro_b.get(index+1).equals(arretSuiv) || index > metro_b.size()){
								return "Gare d Oullins";
							}else{
								return "Charpennes";
							}
			case "metro_c": index = metro_c.indexOf((String) arretPrec);
							if (metro_c.get(index+1).equals(arretSuiv) || index > metro_c.size()){
								return "Cuire";
							}else{
								return "Hôtel de Ville";
							}
			case "metro_d": index = metro_d.indexOf((String) arretPrec);
							if (metro_d.get(index+1).equals(arretSuiv) || index > metro_d.size()){
								return "Gare de Venissieux";
							}else{
								return "Gare de Vaise";
							}
			default: return "error parsing method";
		}
	}
	/**
	 * 
	 * @param arret
	 * @return une chaine sans accent, sous forme URL like
	 */
	public static String convertToURLStyle(String arret){
		String arretURL = arret.toLowerCase();
		arretURL = StringUtils.strip(arretURL);
		arretURL = arretURL.replaceAll("[ ]", "_");
		arretURL = arretURL.replaceAll("-", "_");
		arretURL = arretURL.replaceAll("é", "e");
		arretURL = arretURL.replaceAll("ô", "o");
		return arretURL;
	}
	
	public static String convertFromURLStyle(String metro){
		switch(metro){
			case "metro_a": return "Metro A";
			case "metro_b": return "Metro B";
			case "metro_c": return "Metro C";
			case "metro_d": return "Metro D";
			default: return "Error convertion";
		}
	}
	/**
	 * 
	 * Renvoie une arrayList contenant l'itineraireMEtro avec les directions
	 */
	public static List<String> getItineraireMetro(int heure, List<String> arretsMetro){
		String arretPrec =arretsMetro.get(0);
		List<String> arretMetro = new ArrayList<String>();
		String metro;
		String metroTemp="";
		String direction="";
		int horraire = heure;
		boolean sens = true;
		boolean firstElem = true;
		// MAJ
		if (arretsMetro.size() == 1){
			arretMetro.add( arretPrec);
			return arretMetro;
		}
		//FIN MAJ
		for (String arret : arretsMetro){
			if (firstElem){
				metro = chkMetro(arret, arretsMetro.get(1));
				metroTemp = metro;
				direction = getDirectionMetro(metro, arret, arretsMetro.get(1));
				direction = " Direction "+direction;
				sens = getSens(metro + direction);
				arretMetro.add(metro + direction);
				horraire = LecteurRDF.getHeure(horraire, convertToURLStyle(arret), convertToURLStyle(metro), sens);
				arretMetro.add(String.valueOf(horraire));
				firstElem = false;
			}else{
				metro = chkMetro(arretPrec, arret);
				
				if (!metro.equals(metroTemp)){
					direction = getDirectionMetro(metro, arretPrec, arret);
					metroTemp = metro;
					direction = " Direction "+direction;
					sens = getSens(metro + direction);
					horraire = LecteurRDF.getHeure(horraire, convertToURLStyle(arret), convertToURLStyle(metro), sens);
					arretMetro.add(metro + direction);
					arretMetro.add(String.valueOf(horraire));
				}else{
					horraire = LecteurRDF.getHeure(horraire, convertToURLStyle(arret), convertToURLStyle(metro), sens);
				}
			}
			arretMetro.add(arret);
			arretPrec = arret;
		}
		
		return arretMetro;
	}
	/**
	 * 
	 * @param direction
	 * @return true si dans le meme sens qu'array list, false sinon
	 */
	public static boolean getSens(String direction){
		String[] parts = direction.split(" Direction ");
		switch(parts[1]){
			case "Vaulx La Soie": return true;
			case "Gare d Oullins": return true;
			case "Cuire": return true;
			case "Gare de Venissieux": return true;
			default: return false;
		}
	}
	
	public static String getMetro(String direction){
		String[] parts = direction.split(" Direction ");
		String metroURL = convertToURLStyle(parts[0]);
		return metroURL;
	}
	
	public static String getDirection(String direction){
		String[] parts = direction.split(" Direction ");
		return parts[1];
	}
	
	
	public static String getMetroJSON(String direction){
		return convertFromURLStyle(getMetro(direction));
	}
	
	public static Calendar constructCalendar(String[] journee, String[] heure){
		int annee = Integer.parseInt(journee[0]);
		int mois = Integer.parseInt(journee[1]);
		int jour = Integer.parseInt(journee[2]);
		int hour = Integer.parseInt(heure[0]);
		int min = Integer.parseInt(heure[1]);
		Calendar day = new GregorianCalendar(annee,mois,jour,hour,min,0);
		return day;
		
	}
	

	
	public static JSONArray setJSONArray(String _metro, String _direction, String _depart,int _heureDepart, String _arrivee,
			int _heureArrivee){
		JSONArray jarr = new JSONArray();
		JSONObject metro = new JSONObject();
		JSONObject direction = new JSONObject();
		JSONObject depart = new JSONObject();
		JSONObject heureDepart = new JSONObject();
		JSONObject arrivee = new JSONObject();
		JSONObject heureArrivee = new JSONObject();
		
		try {
			metro.put("metro", _metro);
			direction.put("direction", _direction);
			
			depart.put("depart", _depart);
			heureDepart.put("heureDepart", _heureDepart);
			arrivee.put("arrivee", _arrivee);
			heureArrivee.put("heureArrivee", _heureArrivee);
			jarr.put(metro);
			jarr.put(heureDepart);
			jarr.put(depart);
			jarr.put(direction);
			
			jarr.put(heureArrivee);
			jarr.put(arrivee);
			
		} catch (JSONException e) {
			System.out.println(e.getMessage());
		}
		
		return jarr;
		
	}
	

	
	
	
}
