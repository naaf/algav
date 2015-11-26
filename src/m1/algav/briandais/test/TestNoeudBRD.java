package m1.algav.briandais.test;

import org.junit.Test;

import m1.algav.briandais.NoeudBRD;

public class TestNoeudBRD {

	@Test
	public void test() {
		NoeudBRD n = new NoeudBRD();
		n.setFrere(new NoeudBRD());
		NoeudBRD d = n.getFrere();
		System.out.println(d);
	}

}
