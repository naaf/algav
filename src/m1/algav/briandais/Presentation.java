package m1.algav.briandais;

import static m1.algav.briandais.ArbreBRD.ajouterBRD;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import m1.algav.tools.Input;
import m1.algav.tools.InputFromFile;

public class Presentation {

	protected static String REPERTOIRE = "shakespear";

	public static void main(String[] args) throws IOException {
		// exo1_3();
		shakespear();
	}

	public static void exo1_3() {
		String phrase = "A quel genial professeur de dactylographie sommes nous redevables de la superbe phrase ci dessous, un modele du genre, que toute dactylo connait par coeur puisque elle fait appel a chacune des touches duclavier de la machine a ecrire";
		String[] ts = phrase.split(" ");
		NoeudBRD root = null;
		long debut = System.currentTimeMillis();
		for (String s : ts) {
			root = ajouterBRD(root, s);
		}
		long temps = System.currentTimeMillis() - debut;
		System.out.println(ArbreBRD.afficheBRD(root));
		System.out.println("temps en milliseconde ajout successifs : " + temps);
	}

	public static void shakespear() throws IOException {
		File repertoire = new File(REPERTOIRE);
		final File[] files = repertoire.listFiles();
		NoeudBRD root = null;
		long debut = System.currentTimeMillis();
		for (File file : files) {
			Input input = new InputFromFile(file);
			for (String s : input.getText().split("\n")) {
				root = ajouterBRD(root, s);
			}
		}
		long temps = System.currentTimeMillis() - debut;

		File f = new File("affiche.txt");
		FileOutputStream fs = new FileOutputStream(f);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fs));
		String aff = ArbreBRD.afficheBRD(root).toString();
		bw.write(aff);
		bw.flush();
		bw.close();
		System.out.println("temps en milliseconde ajout successifs : " + temps);

	}
}
