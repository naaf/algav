package m1.algav.briandais;

import java.util.ArrayList;
import java.util.List;

public class ArbreBRD {
	static final char FIN_MOT = '#';
	static final char MOT_NULL = '\0';
	static final char SEPERATOR_VER = '|';
	static final char SEPERATOR_HOR = '-';

	public static NoeudBRD constArbreBRD(String m) {
		if ("".equals(m))
			return new NoeudBRD();
		NoeudBRD root = new NoeudBRD(m.charAt(0), null, null);
		NoeudBRD p = root;
		for (int i = 1; i < m.length(); i++) {
			p.setFils(new NoeudBRD(m.charAt(i), null, null));
			p = p.getFils();
		}
		p.setFils(new NoeudBRD(FIN_MOT, null, null));
		return root;
	}

	public static NoeudBRD fils(NoeudBRD abr) {
		if (abr == null)
			return null;
		return abr.getFils();
	}

	public static int nbMaxFrere(NoeudBRD abr) {
		if (abr == null) {
			return 0;
		}
		return Math.max(nbMaxFrere(abr.getFils()), 1 + nbMaxFrere(abr.getFrere()) + nbMaxFrere(fils(abr.getFrere())));

	}

	private static void printHorizontal(NoeudBRD abr, int largeur) {
		if (abr == null) {
			System.out.print(MOT_NULL);
			return;
		}
		System.out.print(abr.getCle());
		for (int i = 0; i < largeur; i++) {
			System.out.print(SEPERATOR_HOR);
		}

	}

	private static void printVertical(List<Integer> nbMaxFreres, int nb) {
		int nSpace;
		System.out.println("");
		for (int i = 0; i < nb; i++) {

			System.out.print(SEPERATOR_VER);
			nSpace = nbMaxFreres.get(i);
			for (int j = 0; j < nSpace; j++) {
				System.out.print(" ");
			}
		}
		System.out.println("");
	}

	public static void affiche(NoeudBRD racine) {
		if (racine == null) {
			System.out.println("NULL");
			return;
		}
		List<NoeudBRD> fileBRD = new ArrayList<>();
		List<Integer> nbMaxFreres = new ArrayList<>();
		int i = 0;
		int nb = 0;
		NoeudBRD p = racine;

		do {

			if (p == null) {
				printVertical(nbMaxFreres, nb);
				nb = 0;
				p = fils(fileBRD.remove(0));
				nbMaxFreres.remove(0);
				i--;

			} else {

				fileBRD.add(p);
				nb++;
				nbMaxFreres.add(nbMaxFrere(p));

				printHorizontal(fileBRD.get(i), nbMaxFreres.get(i));
				p = p.getFrere();
				i++;
			}

		} while (!fileBRD.isEmpty() || p != null);
	}

	public static NoeudBRD ajouterBRD(NoeudBRD abr, String m) {
		if ("".equals(m)) {
			return abr;
		}
		if (abr == null) {
			return constArbreBRD(m);
		}

		if (m.charAt(0) == abr.getCle()) {
			abr.setFils(ajouterBRD(abr.getFils(), m.substring(1)));
			return abr;
		}
		if (m.charAt(0) > abr.getCle()) {
			abr.setFrere(ajouterBRD(abr.getFrere(), m));
		} else {
			return new NoeudBRD(m.charAt(0), constArbreBRD(m.substring(1)), abr);
		}

		return abr;
	}

}
