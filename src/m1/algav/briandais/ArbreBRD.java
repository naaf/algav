package m1.algav.briandais;

import java.util.ArrayList;
import java.util.List;

public class ArbreBRD {
	static final char FIN_MOT = '#';
	static final char MOT_NULL = '\0';
	static final char SEPERATOR_VER = '|';
	static final String SEPERATOR_HOR = "-";
	static final int SEPERATOR_UNIT = 2;
	static final String SPACE = " ";

	public static NoeudBRD constArbreBRD(String m) {
		if ("".equals(m))
			return new NoeudBRD(FIN_MOT, null, null);

		m = m.toLowerCase();
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

	public static int largeur(NoeudBRD abr) {
		if (abr == null)
			return 0;
		int n1 = 0, n2 = 0;
		if (abr.getFrere() != null)
			n1 = 1 + largeur(abr.getFrere());
		n2 = largeur(abr.getFils());
		return Math.max(n1 +n2, n2);
	}

	private static void printHorizontal(NoeudBRD abr, int pos, int pointeur, int l) {
		if (abr == null) {
			// System.out.print(MOT_NULL);
			return;
		}
		// traitement de position
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < pos - pointeur; i++) {
			sb.append(SPACE);
		}
		System.out.print(sb);
		// affiche cle
		System.out.print(abr.getCle());

		String c = abr.getFrere() != null ? SEPERATOR_HOR : SPACE;
		sb = new StringBuffer();
		for (int i = 0; i < l; i++) {
			sb.append(c);
		}
		// traitement de lien avec frere
		System.out.print(sb);
	}

	private static void printVertical(List<Integer> largeur, int nb) {
		int pos;
		int pointeur = 0;
		System.out.println("");
		StringBuffer sb;
		for (int i = 0; i < nb; i++) {
			pos = largeur.get(i);
			sb = new StringBuffer();
			for (int j = 0; j < pos - pointeur; j++) {
				sb.append(SPACE);
			}
			System.out.print(sb.toString());
			System.out.print(SEPERATOR_VER);
			pointeur = pos + 1;
		}
		System.out.println("");
	}

	public static void afficheBRD(NoeudBRD racine) {
		if (racine == null) {
			System.out.println("NULL");
			return;
		}

		List<NoeudBRD> fileBRD = new ArrayList<>();
		List<Integer> positions = new ArrayList<>();
		int i = 0;
		int nb = 0;
		int pnb = 1;
		NoeudBRD p = racine;

		int l = 0, pos = 0, pointeur = 0;

		do {

			if (p == null) {
				pnb--;
				if (pnb == 0) {
					printVertical(positions, nb);
					pnb = nb;
					nb = 0;
					pointeur = 0;
				}

				p = fils(fileBRD.remove(0));
				pos = positions.remove(0);
				i--;

			} else {

				fileBRD.add(p);
				positions.add(pos);
				nb++;
				l = largeur(fils(p));
				l = l + SEPERATOR_UNIT + (l * SEPERATOR_UNIT);
				pos = pos + l + 1;

				printHorizontal(fileBRD.get(i), positions.get(i), pointeur, l);
				p = p.getFrere();
				pointeur = pos;
				i++;
			}

		} while (!fileBRD.isEmpty() || p != null);
	}

	public static NoeudBRD ajouterBRD(NoeudBRD abr, String m) {
		if ("".equals(m) || abr == null)
			return abr;
		return ajouter(abr, m.toLowerCase());
	}

	private static NoeudBRD ajouter(NoeudBRD abr, String m) {
		if ("".equals(m)) {
			if (abr != null && abr.getCle() == FIN_MOT)
				return abr;
			return new NoeudBRD(FIN_MOT, null, abr);
		}
		if (abr == null) {
			return constArbreBRD(m);
		}

		if (m.charAt(0) == abr.getCle()) {
			abr.setFils(ajouter(abr.getFils(), m.substring(1)));
			return abr;
		}
		if (m.charAt(0) > abr.getCle()) {
			abr.setFrere(ajouter(abr.getFrere(), m));
		} else {
			return new NoeudBRD(m.charAt(0), constArbreBRD(m.substring(1)), abr);
		}

		return abr;
	}

	public static int comptageNil(NoeudBRD abr) {
		if (abr == null) {
			return 1;
		}
		return comptageNil(abr.getFils()) + comptageNil(abr.getFrere());
	}

	public static int comptageMots(NoeudBRD abr) {
		if (abr == null) {
			return 0;
		}
		if (abr.getCle() == FIN_MOT) {
			return 1 + comptageMots(abr.getFrere());
		}
		return comptageMots(abr.getFils()) + comptageMots(abr.getFrere());
	}

	public static int prefixeBRD(NoeudBRD abr, String m) {
		if ("".equals(m) || abr == null)
			return 0;
		return prefixe(abr, m.toLowerCase());
	}

	private static int prefixe(NoeudBRD abr, String m) {
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

	public static boolean rechercheBRD(NoeudBRD abr, String m) {
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

	public static int sommeHauteur(NoeudBRD abr) {
		if (abr == null)
			return 0;
		List<NoeudBRD> l = new ArrayList<>();
		int s = 0, h = 0;
		NoeudBRD p = abr;
		int size = 0;
		l.add(p);
		while (!l.isEmpty()) {
			s = s + h * l.size();
			size = l.size();
			for (int i = 0; i < size; i++) {
				p = l.remove(0);
				if (p.getFils() != null)
					l.add(p.getFils());
				if (p.getFrere() != null)
					l.add(p.getFrere());
			}
			h++;
		}

		return s;

	}

	public static int profondeurMoyenne(NoeudBRD abr) {
		if (abr == null)
			return 0;
		return sommeHauteur(abr) / taille(abr);
	}

	public static NoeudBRD supprimerBRD(NoeudBRD abr, String m) {
		if ("".equals(m) || abr == null)
			return null;
		return supprimer(abr, m.toLowerCase());
	}

	private static NoeudBRD supprimer(NoeudBRD abr, String m) {
		if (abr == null)
			return abr;

		if ("".equals(m)) {
			if (abr.getFils() == null)
				return abr.getFrere();
			return abr;
		}
		if (abr.getCle() == m.charAt(0)) {
			abr.setFils(supprimer(abr.getFils(), m.substring(1)));
			if (abr.getFils() == null) {
				return abr.getFrere();
			}
			return abr;
		}

		if (abr.getCle() < m.charAt(0)) {
			abr.setFrere(supprimer(abr.getFrere(), m));
		}
		return abr;

	}

	public static NoeudBRD fusionBRD(NoeudBRD a, NoeudBRD b) {
		if (a == null || a == b)
			return b;
		if (b == null)
			return a;

		NoeudBRD bFrere;
		if (a.getCle() == b.getCle()) {
			a.setFils(fusionBRD(a.getFils(), b.getFils()));
			a.setFrere(fusionBRD(a.getFrere(), b.getFrere()));
			return a;
		} else if (a.getCle() > b.getCle()) {
			bFrere = b.getFrere();
			b.setFrere(a);
			a.setFrere(fusionBRD(a.getFrere(), bFrere));
			return b;
		} else {
			if (a.getFrere() == null) {
				a.setFrere(b);
				return a;
			}
			if (a.getFrere().getCle() > b.getCle()) {
				bFrere = b.getFrere();
				b.setFrere(a.getFrere());
				a.setFrere(b);
				b.setFrere(fusionBRD(b.getFrere(), bFrere));
				return a;
			} else {
				a.setFrere(fusionBRD(a.getFrere(), b));
				return a;
			}
		}

	}

}
