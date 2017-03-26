package fr.univ_lyon1.ter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import es.usc.citius.hipster.algorithm.Hipster;
import es.usc.citius.hipster.graph.GraphSearchProblem;
import es.usc.citius.hipster.model.problem.SearchProblem;
import fr.univ_lyon1.ter.modele.Aggregation;
import fr.univ_lyon1.ter.utilitaire.GenerateurRDF;
import fr.univ_lyon1.ter.utilitaire.Utils;

public class App {
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String... argv)  {
		Calendar arrivee = new GregorianCalendar(2017,3,20,13,0,00);
		Calendar depart = new GregorianCalendar(2017,3,20,15,30,00);
			
		GenerateurRDF generateur = new GenerateurRDF();
		generateur.mise_a_jour_horraire(arrivee, depart);
//		
//		SearchProblem p = GraphSearchProblem
//                .startingFrom("Perrache")
//                .in(Utils.reseauTCL)
//                .takeCostsFromEdges()
//                .build();
//		System.out.println(Hipster.createDijkstra(p).search("Part-Dieu").getOptimalPaths());
		ArrayList<String> preferences =new ArrayList<String>();
		preferences.add("Musée");
		Aggregation agg = new Aggregation(arrivee,depart,preferences);
		
		agg.affichageResultat();
		
		System.out.println("fini");
	}
}
