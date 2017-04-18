package fr.univ_lyon1.ter;


import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.jena.graph.Triple;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.riot.lang.PipedRDFIterator;
import org.apache.jena.riot.lang.PipedRDFStream;
import org.apache.jena.riot.lang.PipedTriplesStream;
import org.rdfhdt.hdt.util.StopWatch;

//import com.hp.hpl.jena.graph.Triple;

public class FreGraPaD {

	public static void getEtoile() throws IOException {
		BigInteger GraphId = new BigInteger("0");
		Map<BigInteger, Integer> GraphTable = new HashMap<BigInteger, Integer>(); //GHT : hastable des graphes
		Map<String, Integer> PredicatTable = new HashMap<String, Integer>(); //PHT : hastable des Predicats
		int NbreGraph = 0 , Min = 100, Max = 0 , Tmp = 0;
		String subject = "", predicat = "" , predicatName = "", SubjectName = "", ObjectName = "", resultat = "";
		Integer PredicatIndice = -1;
		
		final String currentPath = System.getProperty("user.dir")+ "/resources/";
		final String filename = currentPath + "mix.nt"; // mettre le chemin absolu du jeu données
		// Create a PipedRDFStream to accept input and a PipedRDFIterator to consume it
		// You can optionally supply a buffer size here for the
		// PipedRDFIterator, see the documentation for details about recommended buffer sizes
		StopWatch sw = new StopWatch(); // initialiser le chrono
		PipedRDFIterator<Triple> iter = new PipedRDFIterator<Triple>();
		final PipedRDFStream<Triple> inputStream = new PipedTriplesStream(iter);
		// PipedRDFStream and PipedRDFIterator need to be on different threads
		ExecutorService executor = Executors.newSingleThreadExecutor();
		// Create a runnable for our parser thread
		Runnable parser = new Runnable() {
			
			public void run() {
				// Call the parsing process.
				try{
					RDFDataMgr.parse(inputStream, filename);
				}catch(Exception e){
					System.out.println(e.getMessage());
				}
			}
		};
		// Start the parser on another thread
		executor.submit(parser);
		// We will consume the input on the main thread here
		// We can now iterate over data as it is parsed, parsing only runs as
		// far ahead of our consumption as the buffer size allows
		Long NbreTriples = (long) 1;
		Triple previous = iter.next(); // premier toujours égal à lui meme.
//		System.out.println("sujet : " + previous.getSubject());
//		System.out.println("--> iter  -  : " + previous.toString());
		while (iter.hasNext()) {
			Triple next = iter.next();
//			System.out.println("--> iter     : " + iter.toString());
//			System.out.println("--> ter next : " + iter.next().toString());
//			predicatName = next.getPredicate().getLocalName();
			predicatName = previous.getPredicate().getLocalName();
			predicat = predicatName;
			if (!PredicatTable.containsKey(predicat)) { 		// Si nouveau prédicat 
				PredicatIndice++;
				PredicatTable.put(predicat, PredicatIndice); 	// l'inserer dans la hashtable	
				GraphId = GraphId.setBit(PredicatIndice);	// Mettre à 1 le bit correspondant dans l'ID du graphe
//			System.out.println("Nouveau Predicat -> " + predicat + "  indice : " + PredicatIndice);
			} else {
				GraphId = GraphId.setBit(PredicatTable.get(predicat));
				
//				System.out.println("Ancient Predicat -> " + predicat + "  indice : " + PredicatTable.get(predicat));
			}

			if (!(previous.getSubject().toString().equalsIgnoreCase(next.getSubject().toString()))) {
//				System.out.println(" prev : " + previous.getSubject().toString());
//				System.out.println(" actu : " + next.getSubject().toString());
//				System.out.println(" egaux ? : " + previous.getSubject().toString().equalsIgnoreCase(next.getSubject().toString())); 
				NbreGraph++;
				if ((!GraphTable.containsKey(GraphId)) && (GraphId.bitCount() != 0)){ // Nouveau graph detecté
					GraphTable.put(GraphId, 1);
//					System.out.println(" *** Nouveau Graphe ->  Id : " + GraphId  + " Count : "+ GraphId.bitCount() + " Length : " + GraphId.bitLength() + " Id (2) : "  + GraphId.toString(2) );
//					System.out.println(" ------------- ");
//					System.out.println(" ------------- " + previous.getSubject());
//					System.out.println(" ------------- " + next.getSubject());
//					System.out.println(" ------------- " + "GraphId " + GraphId); 
//			    	GraphId = new BigInteger("0");
					GraphId = BigInteger.ZERO;
/*			    	System.out.println(" ------------- " + "GraphIint minn = Collections.min(GraphTable.values());
	    d " + GraphId);*/
			    	
				} else {
/*					System.out.println(" *** Ancien Graphe ->  Id : " + GraphId  + " Id (2) : "  + GraphId.toString(2) );*/
					GraphTable.put(GraphId, (GraphTable.get(GraphId)+1));
					Tmp = GraphTable.get(GraphId)+1;
					GraphId = BigInteger.ZERO;
/*					System.out.println(" prev : " + previous.getSubject().toString());
					System.out.println(" actu : " + next.getSubject().toString());
					System.out.println(" egaux ? : " + previous.getSubject().toString().equalsIgnoreCase(next.getSubject().toString()));*/
				}
				
	//			System.out.println("*sujet : " + next.getSubject());
	//			System.out.println("   " + next.getPredicate() + "  " + next.getObject());
			}
			
			
			//Node S1 = next.getSubject();
			NbreTriples++;
			// Do something with each triple
			previous = next;
// **********			
//		    System.out.println(" Tr: " + NbreTriples +" Gr: " + NbreGraph + " - Pat: " + GraphTable.size());
//			resultat = " Tr: " + NbreTriples +" Gr: " + NbreGraph + " Pre : " + PredicatTable.size() + " - Pat: " + GraphTable.size();
//			Files.write(Paths.get("/media/belghaou/Data/ERI DataSet/dbpediaResults.txt"), resultat.getBytes());
//			System.out.println(resultat);
			
// ********* juste pour dbpedia (trop large fichier)			
		}
//	    System.gc();

/*		resultat = "\n - Parsing Time sw : " + sw.stopAndShow() + 
		" \n--------------------------------------------------" + 
		" \n Nombre de triplets traités     : " + NbreTriples + 
		" \n Nombre de Graphes traités      : " + NbreGraph + 
		" \n Nombre de Graphes   (patterns) : " + GraphTable.size() +
		" \n Nombre de Predicats (patterns) : " + PredicatTable.size(); */
		
//		Files.write(Paths.get("/media/belghaou/Data/ERI DataSet/dbpediaResults.txt"), resultat.getBytes());

		
		System.out.println("\n - Parsing Time sw : " + sw.stopAndShow());
	    System.out.println("\n--------------------------------------------------");
	    System.out.println(" Nombre de triplets traités     : " + NbreTriples);
	    System.out.println(" Nombre de Graphes traités      : " + NbreGraph);
	    System.out.println(" Nombre de Graphes   (patterns) : " + GraphTable.size());
	    System.out.println(" Nombre de Predicats (patterns) : " + PredicatTable.size());
//	    System.out.println(" Liste des graphes et frequences : " + GraphTable);
	    System.out.println("Affichage des différents tableaux");
	    for (Map.Entry<BigInteger, Integer> entry : GraphTable.entrySet()){
	    	System.out.println(entry.getKey().toString() +" - "+entry.getValue());
	    }
	    System.out.println("--------------");
	   
	    for (Map.Entry<String, Integer> entry : PredicatTable.entrySet()){
	    	System.out.println(entry.getKey() +" - "+entry.getValue());
	    }
//	    System.out.println(" Liste des Predicats  : " + PredicatTable.keySet());
//	    Liste des Graphes  : " + GraphTable.keySet());
//	    int minn = Collections.min(GraphTable.values());
	    int maxx = Collections.max(GraphTable.values());
	    
    System.out.println(" Max " + maxx);
//	    System.out.println(" Min " + minn);
	    
	    
	}

	
}
