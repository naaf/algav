package m1.algav.tries.hybrides;

import m1.algav.mot.FacadeMot;

public class FacadeTrieHybride {
	
	public static ITrieHybride TrieHyprideNoeud(char elt) {
		return new TrieHybride(elt,null,null,null);
	}
	public static boolean EstTrieHyprideVide(ITrieHybride abr) {
		return (abr == null);
	}
	public static int ValeurNoeud(ITrieHybride abr) {
		return ((TrieHybride)abr).getValeur();
	}
	public static char CharNoeud(ITrieHybride abr) {
		return ((TrieHybride)abr).getCaractere();
	}
	
	
	
	public static ITrieHybride AjoutFilsEqual(ITrieHybride abr, ITrieHybride fils) {
		// TODO Auto-generated method stub
		return null;
	}
	public static ITrieHybride AjoutFilsInf(ITrieHybride abr, ITrieHybride fils) {
		// TODO Auto-generated method stub
		return null;
	}
	public static ITrieHybride AhoutFilsSup(ITrieHybride abr, ITrieHybride fils) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*methodes de recuperation des fils*/
	public static ITrieHybride RecupFilsEqual(ITrieHybride abr) {
		return ((TrieHybride)abr).getFilsEqual();
	}
	public static ITrieHybride RecupFilsInf(ITrieHybride abr) {
		return ((TrieHybride)abr).getFilsInf();
	}
	public static ITrieHybride RecupFilsSup(ITrieHybride abr) {
		return ((TrieHybride)abr).getFilsSup();
	} 
	
	
	/*Recherche d'un mot dans l'arbre*/
	public static boolean Recherche(ITrieHybride abr, String mot){
		char tete, noeudChar;
		String queu;
		if(EstTrieHyprideVide(abr) || FacadeMot.EstMotVide(mot))
			return false;
		tete=FacadeMot.teteMot(mot);
		queu=FacadeMot.queuMot(mot);
		noeudChar = CharNoeud(abr);
		if(noeudChar == tete)
			return Recherche(abr.getFilsEqual(), queu);
		if(noeudChar > tete)
			return Recherche(abr.getFilsSup(), mot);
		return Recherche(abr.getFilsInf(), mot);
			
	}
	
	/*Compte le nombre de mot dans l'arbre*/
	int ComptageMot(ITrieHybride abr){
		int n=0;
		if(EstTrieHyprideVide(abr))
			return 0;
		if(ValeurNoeud(abr) > 0)
			n++;
		return n + ComptageMot(abr.getFilsEqual())
				 + ComptageMot(abr.getFilsInf())
				 + ComptageMot(abr.getFilsSup());
	}
	
	/* */
	
}

