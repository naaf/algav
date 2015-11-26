package m1.algav.briandais.test;

import static m1.algav.briandais.ArbreBRD.affiche;
import static m1.algav.briandais.ArbreBRD.ajouterBRD;
import static m1.algav.briandais.ArbreBRD.comptageMots;
import static m1.algav.briandais.ArbreBRD.nbMaxFrere;
import static m1.algav.briandais.ArbreBRD.prefixe;

import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import m1.algav.briandais.ArbreBRD;
import m1.algav.briandais.NoeudBRD;

public class TestArbreBRD {

	static public NoeudBRD root;

	@BeforeClass
	public static void initData() {
		root = ArbreBRD.constArbreBRD("Ashraf");
		root = ajouterBRD(root, "Ash");
		root = ajouterBRD(root, "zak");
		root = ajouterBRD(root, "zag");
		root = ajouterBRD(root, "zig");
		root = ajouterBRD(root, "sinto");
		root = ajouterBRD(root, "voila");
		
	}

	@After
	public void before() {
		System.out.println("**************************");
	}

	@Test
	public void testConstArbreBRD() {
		NoeudBRD n = ArbreBRD.constArbreBRD("Ashraf");
		System.out.println(n);
	}

	@Test
	public void testNbMaxFrere() {
		
		System.out.println("nbMaxFrere" + nbMaxFrere(root));
	}

	@Test
	public void testAffiche() {
		NoeudBRD n = ArbreBRD.constArbreBRD("Ashraf");
		affiche(n);
	}

	@Test
	public void testAjouterBRD() {
		affiche(root);
	}

	@Test
	public void testComptageMots() {
		int c = comptageMots(root);
		System.out.println("nb mots : " + c);
	}

	@Test
	public void testPrefixe() {
		int c = prefixe(root, "za");
		System.out.println("nb prefixe za: " + c);
	}
	
	@Test
	public void testRecherche() {
		System.out.println("recherche");
		Assert.assertTrue("ash", ArbreBRD.recherche(root, "Ash"));
		Assert.assertTrue("zak", ArbreBRD.recherche(root, "zak"));
		Assert.assertFalse("zak", ArbreBRD.recherche(root, "sou"));
	}
	
	@Test
	public void testHauteur() {
		int c = ArbreBRD.hauteur(root);
		System.out.println("nb hauteur: " + c);
	}
	
	@Test
	public void testTaille() {
		int c = ArbreBRD.taille(root);
		System.out.println("nb taille: " + c);
	}

	@Test
	public void testListeMots() {
		System.out.println("listMots: " + ArbreBRD.listeMots(root));
	}
	
	

}
