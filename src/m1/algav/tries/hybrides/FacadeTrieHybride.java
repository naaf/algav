package m1.algav.tries.hybrides;

import java.util.ArrayList;
import java.util.List;

import com.sun.xml.internal.bind.v2.runtime.output.C14nXmlOutput;

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
	
	
	/*ajout d'un mot dans un arbre*/
	public static void AjouteMot(ITrieHybride abr, String mot){
		char tete, noeudChar;
		String queu;
		if(FacadeMot.EstMotVide(mot))
			return;
		tete=FacadeMot.teteMot(mot);
		queu=FacadeMot.queuMot(mot);
		if(EstTrieHyprideVide(abr)){
			abr=TrieHyprideNoeud(tete);
			AjouteMot(abr.getFilsEqual(), queu);
			return;
		}else{
			noeudChar=abr.getCaractere();
			if( noeudChar == tete){
				AjouteMot(abr.getFilsEqual(), queu);
			}else{
				if( noeudChar > tete){
					AjouteMot(abr.getFilsInf(), mot);	
				}else{
					AjouteMot(abr.getFilsSup(), mot);	
					
				}
			}
		}
		
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
	public static int ComptageMot(ITrieHybride abr){
		int n=0;
		if(EstTrieHyprideVide(abr))
			return 0;
		if(ValeurNoeud(abr) > 0)
			n++;
		return n + ComptageMot(abr.getFilsEqual())
				 + ComptageMot(abr.getFilsInf())
				 + ComptageMot(abr.getFilsSup());
	}
	
	/* liste mots*/
	
	public static List<String> ListeMots(ITrieHybride abr){
		return ListeMotsRec(abr, "");
	}
	
	private static List<String> ListeMotsRec(ITrieHybride abr, String prefix){
		String mot;
		List<String> resultList=new ArrayList<String>();
		if(EstTrieHyprideVide(abr))
			return new ArrayList<String>();
		
		mot=prefix+abr.getCaractere();
		resultList.addAll(ListeMotsRec(abr.getFilsInf(), prefix));
		if(abr.getValeur()>0){
		resultList.add(mot);	
		}
		resultList.addAll(ListeMotsRec(abr.getFilsEqual(), mot));
		resultList.addAll(ListeMotsRec(abr.getFilsSup(), prefix));
		return resultList;
	}
	
	
	/*Comptage des null**/
	
	public static int ComptageNil(ITrieHybride abr){
		if(EstTrieHyprideVide(abr))
			return 1;
		return ComptageNil(abr.getFilsInf())+ComptageNil(abr.getFilsEqual())+ComptageNil(abr.getFilsSup());
	
	}
	
	/*Hauteur d'un arbre*/
	
	public static int Hauteur(ITrieHybride abr){
		int inf, eq, sup;
		if(EstTrieHyprideVide(abr))
			return 0;
		inf=Hauteur(abr.getFilsInf());
		eq=Hauteur(abr.getFilsEqual());
		sup=Hauteur(abr.getFilsSup());
		return 1 + Math.max(Math.max(inf,eq), sup);
	}
	
	/*Profondeur moyen*/
	public static int ProfondeurMoyenne(ITrieHybride abr){
		CouplePronfodeurFeuille couple = new CouplePronfodeurFeuille(0, 0);
		couple = NombreProfondeurFeuille(abr, couple);
		if(couple.getNbFeuille()<1)
			return 0;
		return couple.getProfondeur()/couple.getNbFeuille();
	}
	
	
	private static CouplePronfodeurFeuille NombreProfondeurFeuille(ITrieHybride abr, CouplePronfodeurFeuille couple){
		CouplePronfodeurFeuille coupletmp;
		if(EstTrieHyprideVide(abr))
			return couple;
		coupletmp=couple;	
		couple.upProfondeur();
		couple = NombreProfondeurFeuille(abr.getFilsInf(), couple);
		couple = NombreProfondeurFeuille(abr.getFilsEqual(), couple);
		couple = NombreProfondeurFeuille(abr.getFilsSup(), couple);
		
		if(coupletmp.equals(couple))
			couple.upNbFeuille();
		return couple;
			
	}
}

