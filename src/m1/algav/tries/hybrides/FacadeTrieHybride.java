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
	
	/*Partie Affichage---------------*/
	/*calucul de nombre de noeud*/
	private static String AfficheTrieHybride(ITrieHybride abr){
		int hauteur=0;
		List<ITrieHybride> list = ConstListPourAffichage(abr);
		if(list.size()==0)
			return "TrieHybride Vide";
		for(int i=list.size(); i>2; i=i/3){
			hauteur++;
		}
		
	}
	
	
	private static int NbNoeud(ITrieHybride abr){
		int n = Hauteur(abr);
		int nbNoeud=0;
		if(n==0)
			return 0;
		for(int i=0; i<n; i++){/*n appartien à [1,N] et i a [0,N-1] */
			nbNoeud += Math.pow(3, i);
		}
		return nbNoeud;
	}
	
	
	
	/*construction d'une liste des noeu*/
	private static List<ITrieHybride> ConstListPourAffichage(ITrieHybride abr){
		List<ITrieHybride> list= new ArrayList<ITrieHybride>();
		int nbNoeud = NbNoeud(abr);
		for (int i = 0; i < nbNoeud; i++) {
			  list.add(null);
		}
		if(EstTrieHyprideVide(abr))
			return list;
		list.add(0, abr);
		ConstListRec(abr, list, 0);
		return list;
		
	}
	
	/*construction liste en rec*/
	private static void ConstListRec(ITrieHybride abr, List<ITrieHybride> list, int postion ){
		ITrieHybride abrInf = abr.getFilsInf();
		ITrieHybride abrEqual = abr.getFilsEqual();
		ITrieHybride abrSup = abr.getFilsSup();
		
		if(!EstTrieHyprideVide(abrInf)){
			list.set((postion*3)+1, abrInf);
			ConstListRec(abrInf, list, list.indexOf(abrInf) );
		}
		
		if(!EstTrieHyprideVide(abrEqual)){
			list.set((postion*3)+2, abrEqual);
			ConstListRec(abrEqual, list, list.indexOf(abrEqual) );
		}
		
		if(!EstTrieHyprideVide(abrSup)){
			list.set((postion*3)+3, abrSup);
			ConstListRec(abrSup, list, list.indexOf(abrSup) );
		}
	}
	
	/*ajout d'un mot dans un arbre*/
	public static ITrieHybride AjouteMot(ITrieHybride abr, String mot){
		char tete, noeudChar;
		String queu;
		if(FacadeMot.EstMotVide(mot))
			return abr;
		tete=FacadeMot.teteMot(mot);
		queu=FacadeMot.queuMot(mot);
		if(EstTrieHyprideVide(abr)){
			//System.out.println("est vide");
			abr=TrieHyprideNoeud(tete);
			if(queu == null){
				abr.setValeur(1);
			}else{
				abr.setFilsEqual(AjouteMot(null, queu));
			}
			//System.out.println(abr.toString());
			return abr;
		}else{
			noeudChar=abr.getCaractere();
			if( noeudChar == tete){
				if(queu == null){
					abr.setValeur(1);
					return abr;
				}
				abr.setFilsEqual(AjouteMot(abr.getFilsEqual(), queu));
			}else{
				if( noeudChar > tete){
					abr.setFilsInf(AjouteMot(abr.getFilsInf(), mot));	
				}else{
					abr.setFilsSup(AjouteMot(abr.getFilsSup(), mot));	
					
				}
			}
		}
		return abr;
		
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
		if(noeudChar == tete){
			if(queu == null && abr.getValeur()>0)
				return true;
			return Recherche(abr.getFilsEqual(), queu);
		}
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
		CouplePronfodeurFeuille couple = new CouplePronfodeurFeuille();
		couple = NombreProfondeurFeuille(abr, couple);
		if(couple.estVide())
			return 0;
		return couple.getProfondeur()/couple.getNbFeuille();
	}
	
	
	private static CouplePronfodeurFeuille NombreProfondeurFeuille(ITrieHybride abr, CouplePronfodeurFeuille couple){
		CouplePronfodeurFeuille coupletmpInf, coupletmpEqual, coupletmpSup;
		int sommeProf=0;
		int nbFeuille=0;
		if(EstTrieHyprideVide(abr))
			return new CouplePronfodeurFeuille();/*renvoi couple (0,0)*/
	
		couple.upProfondeur();
		coupletmpInf = NombreProfondeurFeuille(abr.getFilsInf(), couple);
		coupletmpEqual = NombreProfondeurFeuille(abr.getFilsEqual(), couple);
		coupletmpSup = NombreProfondeurFeuille(abr.getFilsSup(), couple);
		
		if(coupletmpInf.estVide() && coupletmpEqual.estVide() && coupletmpSup.estVide() ){
			couple.upNbFeuille();
			return couple;
		}
		sommeProf = coupletmpInf.getProfondeur() + coupletmpEqual.getProfondeur() + coupletmpSup.getProfondeur();
		nbFeuille = coupletmpInf.getNbFeuille() + coupletmpEqual.getNbFeuille() + coupletmpSup.getNbFeuille();
		couple.setProfondeur(sommeProf);
		couple.setNbFeuille(nbFeuille);
		
		return couple;
			
	}
	
	
	
	/*prefixe arbre mot*/
	public static int Prefixe(ITrieHybride abr, String mot){
		return 0;
		
	}
}

