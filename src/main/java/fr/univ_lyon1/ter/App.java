package fr.univ_lyon1.ter;

import java.util.Calendar;
import java.util.GregorianCalendar;

import fr.univ_lyon1.ter.utilitaire.GenerateurRDF;

public class App {
	public static void main(String... argv)  {
		Calendar arrivee = new GregorianCalendar(2017,2,17,13,0,00);
		Calendar depart = new GregorianCalendar(2017,2,17,15,30,00);
		
		GenerateurRDF generateur = new GenerateurRDF();
		generateur.mise_a_jour_horraire(arrivee, depart);
	}
}
