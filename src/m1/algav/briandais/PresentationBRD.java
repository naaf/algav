package m1.algav.briandais;

import static m1.algav.briandais.ArbreBRD.ajouterBRD;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;

import m1.algav.tools.Input;
import m1.algav.tools.InputFromFile;
import m1.algav.tries.hybrides.ITrieHybride;

public class PresentationBRD {

	protected static String REPERTOIRE = "shakespear";
	protected static int TAILLE_TAB = 20;

	public static void main(String[] args) throws IOException, InterruptedException {

		System.out.println("\n*********************Exemple de Base***************************");
		exo1_3();

		System.out.println("\n*******************Construction de Shakespear*******************************");
		NoeudBRD root = null;
		root = shakespear();
		System.out.println("nombre mots (ajout) dans shakespear : " + ArbreBRD.comptageMots(root));
		System.out
				.println("temps en milliseconde ajoutBRD successifs (shakespear): " + shakespearParAddtionSuccessif());
		System.out.println("HauteurBRD de (shakespear) : " + ArbreBRD.hauteur(root));
		System.out.println("profondeur Moyenne BRD de (shakespear) : " + ArbreBRD.profondeurMoyenne(root));

		System.out.println("\ntemps en milliseconde fusionBRD (shakespear): " + shakespearParFusion());
		System.out.println("\ntemps en milliseconde fusionThreadBRD (shakespear): " + shakespearParFusionThread());
		System.out.println("\ntemps en nanoseconde conversion (TrieToBRD) " + convert());

		System.out.println("\n**********************Ajout de mots dans shakespear**************************");
		exo5_12();
	}

	public static void exo1_3() {
		String phrase = "A quel genial professeur de dactylographie sommes nous "
				+ "redevables de la superbe phrase ci dessous, un modele du genre,"
				+ " que toute dactylo connait par coeur puisque elle fait appel a"
				+ " chacune des touches du clavier de la machine a ecrire";

		String[] ts = phrase.split(" ");
		NoeudBRD root = null;
		long debut = System.nanoTime();
		for (String s : ts) {
			root = ajouterBRD(root, s);
		}
		long temps = System.nanoTime() - debut;
		System.out.println(ArbreBRD.afficheBRD(root));
		System.out.println("temps en nanoseconde ajout successifs : " + temps);
	}

	public static void exo5_12() throws IOException {
		NoeudBRD root = null;
		long shakespearTemps = shakespearParAddtionSuccessif();
		Input input = new InputFromFile(new File("jeu_test2.txt"));
		String[] jeuTestMots = input.getText().split("\n");
		long mesureTimes[] = new long[jeuTestMots.length];
		long debut = 0;

		System.out.println("temps en milliseconde ajout successifs shakespear : " + shakespearTemps);
		System.out.println("************************************************");
		System.out.println(Arrays.asList(jeuTestMots));

		// ajout
		long total = System.nanoTime();
		for (int i = 0; i < jeuTestMots.length; i++) {
			debut = System.nanoTime();
			ArbreBRD.ajouterBRD(root, jeuTestMots[i]);
			mesureTimes[i] = System.nanoTime() - debut;
		}
		total = System.nanoTime() - total;

		System.out.println("temps en nanoseconde ajout successifs mots (mot,temps) :");
		for (int i = 0; i < jeuTestMots.length; i++) {
			System.out.println("[" + jeuTestMots[i] + "," + mesureTimes[i] + "]");
			mesureTimes[i] = System.nanoTime() - debut;
		}
		System.out.println("temps en nanoseconde ajout successifs 20 mots " + total);
		System.out.println("temps moyenne d'un ajout en nanoseconde ==> " + total / jeuTestMots.length);

		System.out.println("\n*******************Recherche dans Shakespear*****************************");
		// recherche
		total = System.nanoTime();
		for (int i = 0; i < jeuTestMots.length; i++) {
			debut = System.nanoTime();
			ArbreBRD.rechercheBRD(root, jeuTestMots[i]);
			mesureTimes[i] = System.nanoTime() - debut;
		}
		total = System.nanoTime() - total;

		for (int i = 0; i < jeuTestMots.length; i++) {
			System.out.println("[" + jeuTestMots[i] + "," + mesureTimes[i] + "]");
			mesureTimes[i] = System.nanoTime() - debut;
		}
		System.out.println("temps en nanoseconde (recherche) successifs 20 mots " + total);
		System.out.println("temps moyenne d'un (recherche) en nanoseconde ==> " + total / jeuTestMots.length);

		// Suppression
		System.out.println("\n*******************suppression dans Shakespear*****************************");

		total = System.nanoTime();
		for (int i = 0; i < mesureTimes.length; i++) {
			debut = System.nanoTime();
			ArbreBRD.ajouterBRD(root, jeuTestMots[i]);
			mesureTimes[i] = System.nanoTime() - debut;
		}
		total = System.nanoTime() - total;
		for (int i = 0; i < jeuTestMots.length; i++) {
			System.out.println("[" + jeuTestMots[i] + "," + mesureTimes[i] + "]");
			mesureTimes[i] = System.nanoTime() - debut;
		}
		System.out.println("temps en nanoseconde (suppression) successifs 20 mots " + total);
		System.out.println("temps moyenne d'un (suppression) en nanoseconde ==> " + total / jeuTestMots.length);
		System.out.println("************************************************");

	}

	/**
	 * 
	 * @param root
	 * @return
	 * @throws IOException
	 */
	public static long shakespearParAddtionSuccessif() throws IOException {
		File repertoire = new File(REPERTOIRE);
		final File[] files = repertoire.listFiles();
		NoeudBRD root = null;
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
			root = ajouterBRD(root, s);
		}
		return System.currentTimeMillis() - debut;
	}

	/**
	 * 
	 * @param root
	 * @return
	 * @throws IOException
	 */
	public static long shakespearParFusion() throws IOException {
		File repertoire = new File(REPERTOIRE);
		final File[] files = repertoire.listFiles();
		Input input;
		NoeudBRD root = null;
		String textes[][] = new String[files.length][];
		for (int i = 0; i < files.length; i++) {
			input = new InputFromFile(files[i]);
			textes[i] = input.getText().split("\n");
		}

		long debut = System.currentTimeMillis();
		for (int i = 0; i < files.length; i++) {
			for (String s : textes[i]) {
				root = ArbreBRD.fusionBRD(root, ArbreBRD.constArbreBRD(s));
			}

		}
		long temps = System.currentTimeMillis() - debut;
		System.out.println("nombre mots dans shakespear (fusion): " + ArbreBRD.comptageMots(root));
		return temps;

	}

	public static long shakespearParFusionThread() throws IOException, InterruptedException {
		File repertoire = new File(REPERTOIRE);
		final File[] files = repertoire.listFiles();
		NoeudBRD roots[] = new NoeudBRD[files.length];
		Thread threads[] = new Thread[files.length];
		Input input;
		NoeudBRD root = null;
		String textes[][] = new String[files.length][];
		for (int i = 0; i < files.length; i++) {
			input = new InputFromFile(files[i]);
			textes[i] = input.getText().split("\n");
		}

		long debut = System.currentTimeMillis();

		threads[0] = new Thread(() -> {
			int taille = roots.length / 3;
			int init = 0;
			for (int i = init; i < taille; i++) {
				for (String s : textes[i]) {
					roots[i] = ajouterBRD(roots[i], s);
				}
			}
		});
		threads[0].start();
		threads[1] = new Thread(() -> {
			int taille = 2 * roots.length / 3;
			int init = 1 + roots.length / 3;
			for (int i = init; i < taille; i++) {
				for (String s : textes[i]) {
					roots[i] = ajouterBRD(roots[i], s);
				}
			}
		});
		threads[1].start();

		threads[2] = new Thread(() -> {
			int taille = roots.length;
			int init = 1 + 2 * roots.length / 3;
			for (int i = init; i < taille; i++) {
				for (String s : textes[i]) {
					roots[i] = ajouterBRD(roots[i], s);
				}
			}
		});
		threads[2].start();

		int taille = roots.length / 3;
		int init = 0;
		threads[0].join();
		for (int i = init; i < taille; i++) {
			root = ArbreBRD.fusionBRD(root, roots[i]);
		}

		taille = 2 * roots.length / 3;
		init = 1 + roots.length / 3;
		threads[1].join();
		for (int i = init; i < taille; i++) {
			root = ArbreBRD.fusionBRD(root, roots[i]);
		}

		taille = roots.length;
		init = 1 + 2 * roots.length / 3;
		threads[2].join();
		for (int i = init; i < taille; i++) {
			root = ArbreBRD.fusionBRD(root, roots[i]);
		}

		return System.currentTimeMillis() - debut;

	}

	public static NoeudBRD shakespear() throws IOException, InterruptedException {
		File repertoire = new File(REPERTOIRE);
		final File[] files = repertoire.listFiles();
		NoeudBRD root = null;
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
			root = ajouterBRD(root, s);
		}
		File f = new File("affiche.txt");
		FileOutputStream fs = new FileOutputStream(f);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fs));
		String aff = ArbreBRD.afficheBRD(root).toString();
		bw.write(aff);
		bw.flush();
		bw.close();
		return root;

	}

	public static long convert() {
		ITrieHybride root = PresentationTrie.phrase();
		long debut = System.nanoTime();
		ArbreBRD.trieToBRD(root);
		long temps = System.nanoTime() - debut;
		return temps;
	}

}
