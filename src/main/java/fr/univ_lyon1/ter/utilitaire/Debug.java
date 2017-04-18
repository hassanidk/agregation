package fr.univ_lyon1.ter.utilitaire;

public class Debug {
	private static String text;
	
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
}
