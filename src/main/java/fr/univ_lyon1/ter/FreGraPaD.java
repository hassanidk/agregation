package fr.univ_lyon1.ter;


import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
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

import fr.univ_lyon1.ter.utilitaire.GenerateurRDF;

//import com.hp.hpl.jena.graph.Triple;

public class FreGraPaD {

	public void get_etoile() throws IOException {
		BigInteger GraphId = new BigInteger("0");
		Map<BigInteger, Integer> GraphTable = new HashMap<BigInteger, Integer>(); //GHT : hastable des graphes
		Map<String, Integer> PredicatTable = new HashMap<String, Integer>(); //PHT : hastable des Predicats
		int NbreGraph = 0 , Min = 100, Max = 0 , Tmp = 0;
		String subject = "", predicat = "" , predicatName = "", SubjectName = "", ObjectName = "", resultat = "";
		Integer PredicatIndice = -1;
		
		final String currentPath = System.getProperty("user.dir")+ "/resources/";
		final String filename = currentPath + "SitesLyon.nt"; // mettre le chemin absolu du jeu données
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
			if (previous.getPredicate().toString().equals("http://ter/property/nom"))
				System.out.println(previous.getObject());
			
			// Do something with each triple
			previous = next;	
		}

	    
	
	}

	
}

