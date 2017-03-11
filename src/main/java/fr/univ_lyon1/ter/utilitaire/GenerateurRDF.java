package fr.univ_lyon1.ter.utilitaire;

import java.util.Map;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;

public class GenerateurRDF {
	
	/**
	 * 
	 * @param arrivee Date à laquelle l'utilisateur arrive à la gare de la Part-Dieu
	 * @param depart  Date à laquelle l'utilisateur doit partir de la gare de la Part-Dieu
	 */
	public void mise_a_jour_horraire(Calendar arrivee, Calendar depart){
		
		try{	
			int i;
			int temps = arrivee.get(Calendar.HOUR_OF_DAY)*100 + arrivee.get(Calendar.MINUTE);
			int depart_precedent = temps;  
			
			int periode = Utils.getPeriode(arrivee) ; // Semaine / Week End / Vacances
			int heure_depart = depart.get(Calendar.HOUR_OF_DAY)*100 + depart.get(Calendar.MINUTE);
			
			//boolean station_bloque = false;  Sera utile pour couper momentanément les stations (Incident ..)
			PrintWriter writer = new PrintWriter(Utils.file_horraire_tcl, "UTF-8");
			
			// Parcours de l'ensemble du réseau
			for (Map.Entry<String, ArrayList<String>> entry : Utils.listeArret.entrySet()){
			
				temps = arrivee.get(Calendar.HOUR_OF_DAY)*100 + arrivee.get(Calendar.MINUTE);
				depart_precedent = temps;
				i = 1;
				String metro = entry.getKey();
				ArrayList<String> ligne_metro = entry.getValue();
				while ( temps < heure_depart){
					// Parcours de chaque arrêt de metro par ligne
					for (String arret_metro : ligne_metro ){
						if (ligne_metro.get(0).equals(arret_metro)){
							temps = depart_precedent+1 +(int) (Math.random() * periode);
							temps = Utils.checkHour(temps);
							depart_precedent = temps;
						}else{
							temps = temps + 5;// Utils.timeByMetro(metro, i);
							temps = Utils.checkHour(temps);
						}						
						writer.println("<http://ter/ligne/"+metro+"> <http://ter/property/arret/"+ arret_metro+ "/horraire_"+i+"> \""+ Utils.getHour(temps)+":"+Utils.getMin(temps)+"\".");		
					}
					i++;
					writer.println("\n");
					
				}
			}
				writer.close();
				System.out.println("----Génération terminée.");
		} catch (IOException e) {
			
		}
		
	}
}
