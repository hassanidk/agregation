package fr.univ_lyon1.ter.utilitaire;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.jena.graph.Triple;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.riot.lang.PipedRDFIterator;
import org.apache.jena.riot.lang.PipedRDFStream;
import org.apache.jena.riot.lang.PipedTriplesStream;

public class LecteurRDF {
	private static String file_rdf = Utils.file_horraire_tcl;
	
	/**
	 * 
	 * @param horraire
	 * @param arret
	 * @param metro
	 * @param sens
	 * @return l'horaire de passage du metro après l'horraire passé en paramètre
	 */
	public static int getHeure(int horraire, String arret, String metro, boolean sens){
		PipedRDFIterator<Triple> iter = new PipedRDFIterator<Triple>();
		PipedRDFStream<Triple> inputStream = new PipedTriplesStream(iter);
		ExecutorService executor = Executors.newSingleThreadExecutor();
		Runnable parser = new Runnable() {
			public void run() {
				// Call the parsing process.
				try{
					RDFDataMgr.parse(inputStream, file_rdf);
				}catch(Exception e){
					System.out.println("Ce n'est pas thread safe pour le moment ...");
				}
			}
		};
		int heure = 0;
		executor.submit(parser);
		while (iter.hasNext()) {
			Triple next = iter.next();
		
			if (next.getSubject().toString().contains(metro)){
				if (next.getPredicate().toString().contains(arret)){
					heure = Utils.getIntHour(next.getObject().toString());
					if (heure >= horraire){
						break;
						
					
					}
				}
			}
		}
		executor.shutdown();
		return heure;
	}
}
