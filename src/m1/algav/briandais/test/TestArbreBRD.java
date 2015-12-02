package m1.algav.briandais.test;

import static m1.algav.briandais.ArbreBRD.ajouterBRD;
import static m1.algav.briandais.ArbreBRD.comptageMots;
import static m1.algav.briandais.ArbreBRD.largeur;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import m1.algav.briandais.ArbreBRD;
import m1.algav.briandais.NoeudBRD;
import m1.algav.tries.hybrides.FacadeTrieHybride;
import m1.algav.tries.hybrides.ITrieHybride;

public class TestArbreBRD {

	static public NoeudBRD root;

	@BeforeClass
	public static void initData() {

		root = ArbreBRD.constArbreBRD("Armure");
		root = ajouterBRD(root, "epee");
		root = ajouterBRD(root, "bouclier");
		root = ajouterBRD(root, "sac");
		root = ajouterBRD(root, "valise");
		root = ajouterBRD(root, "calculatrice");
		root = ajouterBRD(root, "plateau");
		root = ajouterBRD(root, "plat");

	}

	@After
	public void before() {
		System.out.println("**************************");
	}

	@Test
	public void testLargeur() {
		NoeudBRD n = ArbreBRD.constArbreBRD("ashraf");
		n = ajouterBRD(n, "ash");
		System.out.println("testLargeur : " + largeur(n));
	}

	@Test
	public void testAffiche() {

		System.out.println(ArbreBRD.afficheBRD(root));
	}

	@Test
	public void testAjouterBRD() {
		System.out.println("listMots: " + ArbreBRD.listeMots(root));
		root = ajouterBRD(root, "nouveau");
		root = ajouterBRD(root, "nouveaute");
		System.out.println("testAjouterBRD: (nouveau et nouveaute) ");
		System.out.println("listMots: " + ArbreBRD.listeMots(root));
	}

	@Test
	public void testComptageMots() {
		int c = comptageMots(root);
		System.out.println("nb mots : " + c);
	}

	@Test
	public void testPrefixe() {
		Assert.assertEquals(1, ArbreBRD.prefixeBRD(root, "bouclier"));
		Assert.assertEquals(2, ArbreBRD.prefixeBRD(root, "plat"));
		Assert.assertEquals(0, ArbreBRD.prefixeBRD(root, "inexistant"));
	}

	@Test
	public void testRecherche() {
		System.out.println("recherche");
		Assert.assertTrue("bouclier", ArbreBRD.rechercheBRD(root, "bouclier"));
		Assert.assertFalse("sac", ArbreBRD.rechercheBRD(root, "zac"));
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

	@Test
	public void testComptageNil() {
		System.out.println("compatge Nil: " + ArbreBRD.comptageNil(root));
	}

	@Test
	public void testSommeHauteur() {
		System.out.println("testSommeHauteur: " + ArbreBRD.sommeHauteur(root));
	}

	@Test
	public void testProfondeurMoyenne() {
		System.out.println("testProfondeurMoyenne: " + ArbreBRD.profondeurMoyenne(root));
	}

	@Test
	public void testSupprimmerBRD() {
		System.out.println("listMots: " + ArbreBRD.listeMots(root));
		root = ArbreBRD.supprimerBRD(root, "epee");
		root = ArbreBRD.supprimerBRD(root, "plateau");
		System.out.println("testSupprimmerBRD: (epee et plateau) ");
		System.out.println("listMots: " + ArbreBRD.listeMots(root));
	}

	@Test
	public void testFusionBRD() {
		System.out.println("testFusionBRD: root avant" + ArbreBRD.listeMots(root));
		NoeudBRD n = ArbreBRD.constArbreBRD("bas");

		n = ajouterBRD(n, "basse");
		n = ajouterBRD(n, "bas");
		n = ajouterBRD(n, "arcle");
		n = ajouterBRD(n, "regle");
		n = ajouterBRD(n, "gare");
		n = ajouterBRD(n, "table");
		n = ajouterBRD(n, "zebre");
		int a = ArbreBRD.comptageMots(n);
		int b = ArbreBRD.comptageMots(root);
		System.out.println("testFusionBRD: n avant" + ArbreBRD.listeMots(n));
		root = ArbreBRD.fusionBRD(root, n);
		System.out.println("testFusionBRD: n + root" + ArbreBRD.listeMots(root));
		Assert.assertTrue(ArbreBRD.comptageMots(root) == a + b);
		ArbreBRD.afficheBRD(root);
	}

	@Test
	public void testTrieToBDR() {
		ITrieHybride abr = FacadeTrieHybride.AjouteMot(null, "cellule");
		abr = FacadeTrieHybride.AjouteMot(abr, "cartable");
		abr = FacadeTrieHybride.AjouteMot(abr, "arcle");
		abr = FacadeTrieHybride.AjouteMot(abr, "table");
		abr = FacadeTrieHybride.AjouteMot(abr, "crayon");
		abr = FacadeTrieHybride.AjouteMot(abr, "carton");
		List<String> l = FacadeTrieHybride.ListeMots(abr);
		NoeudBRD n = ArbreBRD.trieToBRD(abr, null);
		System.out.println(ArbreBRD.afficheBRD(n));
		System.out.println(l);
	}

}
