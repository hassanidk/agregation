package fr.univ_lyon1.ter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import fr.univ_lyon1.ter.modele.Aggregation;
import fr.univ_lyon1.ter.utilitaire.Utils;

public class App {
	
	public static void main(String... argv)  {
		Calendar arrivee = new GregorianCalendar(2017,3,20,13,0,00);
		Calendar depart = new GregorianCalendar(2017,3,20,15,30,00);
			
		
		ArrayList<String> preferences =new ArrayList<String>();
		
		
		
		Aggregation agg = new Aggregation(arrivee,depart,preferences);
		
		agg.affichageResultat();
		//System.out.println(agg.affichageResultatWeb());
		//System.out.println(LecteurRDF.getHeure(1300, "perrache", "metro_a", true));
//		try {
//			FreGraPaD.getEtoile();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		System.out.println("fini");
	}
}
