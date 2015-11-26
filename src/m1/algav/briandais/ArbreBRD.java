package m1.algav.briandais;

import java.util.ArrayList;
import java.util.List;

public class ArbreBRD {
	static final char FIN_MOT = '#';
	static final char MOT_NULL = '\0';
	static final char SEPERATOR_VER = '|';
	static final String SEPERATOR_HOR = "--";
	static final String SPACE = "  ";

	public static NoeudBRD constArbreBRD(String m) {
		if ("".equals(m))
			return new NoeudBRD(FIN_MOT, null, null);
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
			// System.out.print(MOT_NULL);
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
				System.out.print(SPACE);
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
		int pnb = 1;
		NoeudBRD p = racine;

		do {

			if (p == null) {
				pnb--;
				if (pnb == 0) {
					printVertical(nbMaxFreres, nb);
					pnb = nb;
					nb = 0;
				}

				p = fils(fileBRD.remove(0));
				nbMaxFreres.remove(0);
				i--;

			} else {

				fileBRD.add(p);
				nb++;
				nbMaxFreres.add(nbMaxFrere(fils(p)));

				printHorizontal(fileBRD.get(i), nbMaxFreres.get(i));
				p = p.getFrere();
				i++;
			}

		} while (!fileBRD.isEmpty() || p != null);
	}

	/**
	 * 
	 * @param abr
	 * @param m
	 * @return precondition m non vide
	 */
	public static NoeudBRD ajouterBRD(NoeudBRD abr, String m) {
		if ("".equals(m)) {
			return new NoeudBRD(FIN_MOT, null, abr);
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

	public static int comptageMots(NoeudBRD abr) {
		if (abr == null) {
			return 0;
		}
		if (abr.getCle() == FIN_MOT) {
			return 1 + comptageMots(abr.getFrere());
		}
		return  comptageMots(abr.getFils()) + comptageMots(abr.getFrere());
	}

	/**
	 * 
	 * @param abr
	 * @param m
	 * @return precondition m non vide
	 */
	public static int prefixe(NoeudBRD abr, String m) {
		if (abr == null) {
			return 0;
		}
		if ("".equals(m)) {
			return comptageMots(abr);
		}
		if (abr.getCle() == m.charAt(0)) {
			return prefixe(abr.getFils(), m.substring(1));
		}
		if (abr.getCle() < m.charAt(0)) {
			return prefixe(abr.getFrere(), m);
		} else {
			return 0;
		}
	}

	public static boolean recherche(NoeudBRD abr, String m) {
		if (abr == null || "".equals(m)) {
			return false;
		}
		int i = 0;
		NoeudBRD p = abr;
		while (p != null && i < m.length()) {
			if (p.getCle() == m.charAt(i)) {
				i++;
				p = p.getFils();
			} else if (p.getCle() < m.charAt(i)) {
				p = p.getFrere();
			} else {
				return false;
			}
		}
		if (p == null)
			return false;
		return true;
	}

	public static int hauteur(NoeudBRD abr) {
		int a = 0, b = 0;
		if (abr.getFils() == null && abr.getFrere() == null) {
			return 0;
		}
		if (abr.getFils() != null)
			a = hauteur(abr.getFils());
		if (abr.getFrere() != null)
			b = hauteur(abr.getFrere());
		return Math.max(a, b) + 1;
	}

	public static int taille(NoeudBRD abr) {
		if (abr == null) {
			return 0;
		}
		return 1 + taille(abr.getFils()) + taille(abr.getFrere());
	}

	private static List<String> liste(NoeudBRD abr, StringBuffer sb, List<String> l) {
		if (abr == null)
			return null;
		if (abr.getCle() == FIN_MOT)
			l.add(sb.toString());

		StringBuffer sb2 = new StringBuffer(sb);

		sb.append(abr.getCle());
		liste(abr.getFils(), sb, l);
		liste(abr.getFrere(), sb2, l);

		return l;
	}

	public static List<String> listeMots(NoeudBRD abr) {
		return liste(abr, new StringBuffer(), new ArrayList<String>());
	}
}
