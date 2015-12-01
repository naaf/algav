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

public class Presentation {

	protected static String REPERTOIRE = "shakespear";
	protected static int TAILLE_TAB = 20;

	public static void main(String[] args) throws IOException, InterruptedException {
		// exo1_3();
		// exo5_12();

		System.out.println("temps en milliseconde ajout successifs : " + shakespearParAddtionSuccessif());
//		System.out.println("temps en milliseconde fusion successifs : " + shakespearParFusion());
//		System.out.println("temps en milliseconde fusionThread successifs : " + shakespearParFusionThread(root));
	}

	public static void exo1_3() {
		String phrase = "A quel genial professeur de dactylographie sommes nous redevables de la superbe phrase ci dessous, un modele du genre, que toute dactylo connait par coeur puisque elle fait appel a chacune des touches duclavier de la machine a ecrire";
		String[] ts = phrase.split(" ");
		NoeudBRD root = null;
		long debut = System.nanoTime();
		for (String s : ts) {
			root = ajouterBRD(root, s);
		}
		long temps = System.nanoTime() - debut;
		System.out.println(ArbreBRD.afficheBRD(root));
		System.out.println("temps en milliseconde ajout successifs : " + temps);
	}

	public static void exo5_12() throws IOException {
		NoeudBRD root = null;
		long shakespearTemps = shakespearParAddtionSuccessif();
		Input input = new InputFromFile(new File("jeu_test2.txt"));
		String[] tests = input.getText().split("\n");
		long tabTemps[] = new long[tests.length];
		long debut = 0;
		long total = System.nanoTime();
		for (int i = 0; i < tabTemps.length; i++) {
			debut = System.nanoTime();
			ArbreBRD.ajouterBRD(root, tests[i]);
			tabTemps[i] = System.nanoTime() - debut;
		}
		total = System.nanoTime() - total;
		System.out.println("temps en milliseconde ajout successifs shakespear : " + shakespearTemps);
		System.out.println(Arrays.asList(tests));
		System.out.println("temps en nanoseconde ajout successifs mots : " + Arrays.toString(tabTemps));
		System.out.println("temps en nanoseconde ajout successifs 20 mots " + total);

		// Suppression

		total = System.nanoTime();
		for (int i = 0; i < tabTemps.length; i++) {
			debut = System.nanoTime();
			ArbreBRD.ajouterBRD(root, tests[i]);
			tabTemps[i] = System.nanoTime() - debut;
		}
		total = System.nanoTime() - total;
		System.out.println("temps en nanoseconde suppression successifs mots : " + Arrays.toString(tabTemps));
		System.out.println("temps en nanoseconde suppression successifs 20 mots " + total);

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
		long temps = System.currentTimeMillis() - debut;

		File f = new File("affiche.txt");
		FileOutputStream fs = new FileOutputStream(f);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fs));
		String aff = ArbreBRD.afficheBRD(root).toString();
		bw.write(aff);
		bw.flush();
		bw.close();

		return temps;
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
		NoeudBRD roots[] = new NoeudBRD[files.length];
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
				roots[i] = ajouterBRD(roots[i], s);
			}
			root = ArbreBRD.fusionBRD(root, roots[i]);
		}
		long temps = System.currentTimeMillis() - debut;

		File f = new File("affiche.txt");
		FileOutputStream fs = new FileOutputStream(f);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fs));
		String aff = ArbreBRD.afficheBRD(root).toString();
		bw.write(aff);
		bw.flush();
		bw.close();

		return temps;

	}

	public static long shakespearParFusionThread(NoeudBRD root) throws IOException, InterruptedException {
		File repertoire = new File(REPERTOIRE);
		final File[] files = repertoire.listFiles();
		NoeudBRD roots[] = new NoeudBRD[files.length];
		Thread threads[] = new Thread[files.length];
		Input input;
		String textes[][] = new String[files.length][];
		for (int i = 0; i < files.length; i++) {
			input = new InputFromFile(files[i]);
			textes[i] = input.getText().split("\n");
		}

		long debut = System.currentTimeMillis();
		for (int i = 0; i < roots.length; i++) {
			final int c = i;
			threads[c] = new Thread(() -> {
				for (String s : textes[c]) {
					roots[c] = ajouterBRD(roots[c], s);
				}
			});
			threads[c].start();
		}
		NoeudBRD root1[] = new NoeudBRD[2];

		new Thread(() -> {
			for (int i = 0; i < roots.length / 2; i++) {
				try {
					threads[i].join();
					root1[0] = ArbreBRD.fusionBRD(root1[0], roots[i]);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		new Thread(() -> {
			for (int i = 1 + roots.length / 2; i < roots.length; i++) {
				try {
					threads[i].join();
					root1[1] = ArbreBRD.fusionBRD(root1[1], roots[i]);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		root = ArbreBRD.fusionBRD(root1[0], root1[1]);
		long temps = System.currentTimeMillis() - debut;

		File f = new File("affiche.txt");
		FileOutputStream fs = new FileOutputStream(f);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fs));
		String aff = ArbreBRD.afficheBRD(root).toString();
		bw.write(aff);
		bw.flush();
		bw.close();

		return temps;

	}

}
