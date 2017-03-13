package fr.univ_lyon1.ter.utilitaire;

import java.util.Map;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import static fr.univ_lyon1.ter.utilitaire.ListeInverse.inverse;

public class GenerateurRDF {
	
	/**
	 * 
	 * @param arrivee Date à laquelle l'utilisateur arrive à la gare de la Part-Dieu
	 * @param depart  Date à laquelle l'utilisateur doit partir de la gare de la Part-Dieu
	 */
	public void mise_a_jour_horraire(Calendar arrivee, Calendar depart){
		
		try{	
			int i;
			int indice;
			int heure_actuelle = arrivee.get(Calendar.HOUR_OF_DAY)*100 + arrivee.get(Calendar.MINUTE);
			int depart_precedent = heure_actuelle;  
			boolean sens = true; // True = A vers B || False B vers A
			int periode = Utils.getPeriode(arrivee) ; // Semaine / Week End / Vacances
			int heure_depart = depart.get(Calendar.HOUR_OF_DAY)*100 + depart.get(Calendar.MINUTE);
			
			//boolean station_bloque = false;  Sera utile pour couper momentanément les stations (Incident ..)
			PrintWriter writer = new PrintWriter(Utils.file_horraire_tcl, "UTF-8");
			
			int j = 0; // Permet de parcours les lignes dans les deux sens
			String lien;
			while (j!=2){
				for (Map.Entry<String, ArrayList<String>> entry : Utils.listeArret.entrySet()){
					heure_actuelle = arrivee.get(Calendar.HOUR_OF_DAY)*100 + arrivee.get(Calendar.MINUTE);
					depart_precedent = heure_actuelle;
					i = 1;
					String metro = entry.getKey();
					ArrayList<String> ligne_metro = entry.getValue();
					while ( heure_actuelle < heure_depart){
						// Parcours de chaque arrêt de metro par ligne
						if (j ==0){
							indice = -1;
							lien="";
						}else{
							indice = ligne_metro.size();
							lien = "-sens_inverse";
						}
						for (String arret_metro : ligne_metro ){
							if (ligne_metro.get(0).equals(arret_metro)){
								heure_actuelle = depart_precedent+1 +(int) (Math.random() * periode);
								heure_actuelle = Utils.checkHour(heure_actuelle);
								depart_precedent = heure_actuelle;
							}else{
								heure_actuelle = heure_actuelle +Utils.timeByMetro(sens,metro, indice);
								heure_actuelle = Utils.checkHour(heure_actuelle);
							}						
							writer.println("<http://ter/ligne/"+metro+lien+"> <http://ter/property/arret/"+ arret_metro+ "/horraire_"+i+"> \""+ Utils.getHour(heure_actuelle)+":"+Utils.getMin(heure_actuelle)+"\".");		
							if (j==0){
								indice++;
							}else{
								indice--;
							}
						}
						i++;
						writer.println("\n");
					}
				}
				// On réinitialise les variables
				sens = false;
				heure_actuelle = arrivee.get(Calendar.HOUR_OF_DAY)*100 + arrivee.get(Calendar.MINUTE);
				j++;
			}

			System.out.println("----Génération terminée----");
		} catch (IOException e) {
			
		}
		
	}
}
