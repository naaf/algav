package m1.algav.briandais;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import m1.algav.tools.Input;
import m1.algav.tools.InputFromFile;
import m1.algav.tries.hybrides.FacadeTrieHybride;
import m1.algav.tries.hybrides.ITrieHybride;

public class PresentationTrie {

	protected static String REPERTOIRE = "shakespear";
	protected static int TAILLE_TAB = 20;

	public static void main(String[] args) throws IOException {

		System.out.println("\n*********************Exemple de Base***************************");
		ITrieHybride root = exo1_3();
		System.out.println("\n**********************Affichage********************************");

		//FacadeTrieHybride.AfficheTrieHybride(root);
		System.out.println(""); // TODO affichage Trie root
		
		System.out.println("\n*******************Construction de Shakespear*******************************");

		// shakespear
		root = shakespear();
		System.out.println("Temps en milliseconde ajoutTrie successifs (shakespear): " + shakespearParAddtionSuccessif());
		System.out.println("HauteurTrie de (shakespear) : " + FacadeTrieHybride.Hauteur(root));
		System.out.println("profondeur Moyenne Trie de (shakespear) : " + FacadeTrieHybride.ProfondeurMoyenne(root));
		
		System.out.println("\n**********************Ajout de mots dans shakespear**************************");

		exo5_12();

	}

	public static ITrieHybride phrase() {
		String phrase = "A quel genial professeur de dactylographie sommes nous "
				+ "redevables de la superbe phrase ci dessous, un modele du genre,"
				+ " que toute dactylo connait par coeur puisque elle fait appel a"
				+ " chacune des touches du clavier de la machine a ecrire";

		String[] ts = phrase.split(" ");
		ITrieHybride root = null;
		for (String s : ts) {
			root = FacadeTrieHybride.AjouteMot(root, s);
		}
		return root;
	}

	public static ITrieHybride exo1_3() {
		String phrase = "A quel genial professeur de dactylographie sommes nous "
				+ "redevables de la superbe phrase ci dessous, un modele du genre,"
				+ " que toute dactylo connait par coeur puisque elle fait appel a"
				+ " chacune des touches du clavier de la machine a ecrire";

		String[] ts = phrase.split(" ");
		ITrieHybride root = null;
		long debut = System.nanoTime();
		for (String s : ts) {
			root = FacadeTrieHybride.AjouteMot(root, s);
		}
		long temps = System.nanoTime() - debut;
		
		System.out.println("temps en nanoseconde ajout successifs (tries) : " + temps);

		return root;
	}

	public static void exo5_12() throws IOException {
		ITrieHybride root = shakespear();

		Input input = new InputFromFile(new File("jeu_test2.txt"));
		String[] jeuTestMots = input.getText().split("\n");
		long mesureTimes[] = new long[jeuTestMots.length];
		long debut = 0;

		//System.out.println(Arrays.asList(jeuTestMots));

		// ajout
		long total = System.nanoTime();
		for (int i = 0; i < jeuTestMots.length; i++) {
			debut = System.nanoTime();
			FacadeTrieHybride.AjouteMot(root, jeuTestMots[i]);
			mesureTimes[i] = System.nanoTime() - debut;
		}
		total = System.nanoTime() - total;
		System.out.println("temps en nanoseconde ajout successifs mots (mot,temps) :" );
		for (int i = 0; i < jeuTestMots.length; i++) {
			 System.out.println("["+jeuTestMots[i]+","+mesureTimes[i]+"]");
			mesureTimes[i] = System.nanoTime() - debut;
		}
		
		System.out.println("temps en nanoseconde ajout successifs 20 mots (Trie) " + total);
		System.out.println("temps moyenne d'un ajout en nanoseconde (Trie) ==> " + total / jeuTestMots.length);
		System.out.println("\n*******************Recherche dans Shakespear*****************************");

		// recherche
		total = System.nanoTime();
		for (int i = 0; i < jeuTestMots.length; i++) {
			debut = System.nanoTime();
			FacadeTrieHybride.Recherche(root, jeuTestMots[i]);
			mesureTimes[i] = System.nanoTime() - debut;
		}
		total = System.nanoTime() - total;
		System.out.println("temps en nanoseconde (recherche) successifs mots (Trie) : " + Arrays.toString(mesureTimes));
		System.out.println("temps en nanoseconde (recherche) successifs 20 mots (Trie) " + total);
		System.out.println("temps moyenne d'un (recherche) en nanoseconde (Trie) ==> " + total / jeuTestMots.length);
		System.out.println("************************************************");

		// Suppression TODO

		// total = System.nanoTime();
		// for (int i = 0; i < mesureTimes.length; i++) {
		// debut = System.nanoTime();
		// ArbreBRD.(root, jeuTestMots[i]);
		// mesureTimes[i] = System.nanoTime() - debut;
		// }
		// total = System.nanoTime() - total;
		// System.out.println("temps en nanoseconde (suppression) successifs
		// mots (Trie) : " + Arrays.toString(mesureTimes));
		// System.out.println("temps en nanoseconde (suppression) successifs 20
		// mots (Trie) " + total);
		// System.out.println("temps moyenne d'un (suppression) en nanoseconde
		// ==> (Trie) " + total / jeuTestMots.length);
		// System.out.println("************************************************");

	}

	public static ITrieHybride shakespear() throws IOException {
		File repertoire = new File(REPERTOIRE);
		final File[] files = repertoire.listFiles();
		ITrieHybride root = null;
		StringBuffer sb = new StringBuffer();
		Input input;
		for (File file : files) {
			input = new InputFromFile(file);
			for (String s : input.getText().split("\n")) {
				sb.append(s);
				sb.append(" ");
			}
		}
		String[] mots = sb.toString().split(" ");
		for (String s : mots) {
			root = FacadeTrieHybride.AjouteMot(root, s);
		}
		return root;
	}

	public static long shakespearParAddtionSuccessif() throws IOException {
		File repertoire = new File(REPERTOIRE);
		final File[] files = repertoire.listFiles();
		ITrieHybride root = null;
		StringBuffer sb = new StringBuffer();
		Input input;
		for (File file : files) {
			input = new InputFromFile(file);
			for (String s : input.getText().split("\n")) {
				sb.append(s);
				sb.append(" ");
			}
		}
		String[] mots = sb.toString().split(" ");
		long debut = System.currentTimeMillis();
		for (String s : mots) {
			root = FacadeTrieHybride.AjouteMot(root, s);
		}
		return System.currentTimeMillis() - debut;
	}

}
