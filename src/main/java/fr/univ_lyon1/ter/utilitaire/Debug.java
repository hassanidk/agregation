package fr.univ_lyon1.ter.utilitaire;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class Debug {
	private static String text;
	public static final String file_json_test = System.getProperty("user.dir") +"/resources/test";
	
	public static void addDebug(String nameVariable, Object value){
		text = text+ nameVariable+" : "+String.valueOf(value)+"\n";
	}
	
	@Override
	public String toString(){
		return text;
	}
	
	public static void printDebug(){
		System.out.println(text);
	}
	
	/**
	 * Fonction qui a permis de lier les différents arrêts du réseau
	 * NOTE : Tomcat étant lent au demarrage, les différents test réseau ont
	 * été fait sur un serveur local en PHP.
	 * Il a fallu recréer les différentes resources (Itineraire à prendre, topologie du réseau ....)
	 * pour débug rapidement 
	 */
	public static void constructTest(){
		int arrets[] = {
				1,
				2,
				3,
				4,
				5,
				6,
				7,
				8,
				9,
				10,
				11,
				12,
				13,
					
				15,
				16,
				17,
				18,
				19,
				20,
				21,
				22,
				23,
					
				25,
				26,
				27,
					
				29,
				30,
				31,
				32,
				33,
				34,
				35,
				36,
				37,
				38,
				39,
				40,
				41,
				42,


		};
		String part1 = "{\"data\": { \"id\": \"";
		String part2 = "\", \"source\": \"";
		String part3 = "\", \"target\": \"";
		String part4 = "\", \"metro\": \"";
		String part5 = "\" }, \"selected\": false },";
		String metro = "Metro A";
		try {
			int j =0;
			PrintWriter writer = new PrintWriter(file_json_test, "UTF-8");
			for (int i = 0; i< arrets.length;++i){
				if (i!=13 && i!=23 && i!=27){
					if (i==14)
						metro = "Metro B";
					if (i==24)
						metro = "Metro C";
					if (i==28)
						metro = "Metro D";
					writer.write(part1+j+part2+i+part3+arrets[j]+part4+metro+part5);
					j++;
				}else{
					System.out.print("Val i "+i);
				}
				
				
			}
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
