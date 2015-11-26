package m1.algav.briandais.test;

import org.junit.Test;

import m1.algav.briandais.ArbreBRD;
import m1.algav.briandais.NoeudBRD;
import static m1.algav.briandais.ArbreBRD.*;

public class TestArbreBRD {

	@Test
	public void testConstArbreBRD() {
		NoeudBRD n  = ArbreBRD.constArbreBRD("Ashraf");
		System.out.println(n);
	}
	
	@Test
	public void testNbMaxFrere(){
		NoeudBRD n  = ArbreBRD.constArbreBRD("Ashraf");
		System.out.println(nbMaxFrere(n));
	}
	
	@Test
	public void testAffiche(){
		NoeudBRD n  = ArbreBRD.constArbreBRD("Ashraf");
		affiche(n);
	}
}
