package m1.algav.mot;

public class FacadeMot {
	
	public static char teteMot(String mot){
		return mot.charAt(0);
	}
	
	public static String queuMot(String mot){
		if(mot.length()>1)
			return mot.substring(1, mot.length());
		return null;
	}
	
	public static boolean EstMotVide(String mot){
		return (mot == null) || (mot == "");
	}

}
