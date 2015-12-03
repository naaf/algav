package m1.algav.tries.hybrides;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
		if(abr == null)
			return '_';
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
	public static String AfficheTrieHybride(ITrieHybride abr){
		int hauteur=0;
		StringBuffer bf= new StringBuffer();
		List<ITrieHybride> list = ConstListPourAffichage(abr);
		if(list.size()==0){
			return "TrieHybride Vide";
			
		}
		for(int i=list.size(); i>2; i=i/3){
			hauteur++;
		}
		
		for(int i=hauteur; i>-1; i--){
			bf.append(AfficheNoeud(list, i, hauteur-i));
		}
		
		return bf.toString();
		
	}
	
	private static StringBuffer AfficheNoeud(List<ITrieHybride> list, int deg, int hauteur){
		StringBuffer bf= new StringBuffer();
		int pos=0;
		int nbNoeud=(int) Math.pow(3, hauteur);
		int nbSpaceNoeud=0;
		int nbspacetrait=0;
		
		
		String strSpace="";
		String strTraitG="", strTraitD="";
		ITrieHybride abrCur;
		
		for(int i=0; i<hauteur; i++){	
			pos = pos*3 +1;
			
		}
		for(int i=0; i<deg; i++){
			nbspacetrait=nbSpaceNoeud;
			nbSpaceNoeud = nbSpaceNoeud*3 +3;
		}
		for(int i=0; i<nbSpaceNoeud; i++){
			strSpace += " ";
			if(i > nbspacetrait){
				strTraitG += "_";
				strTraitD = "_" + strTraitD;
			}else{
				strTraitG += " ";
				strTraitD += " ";
				
			}
				
			
		}
		bf.setLength(0);
		nbNoeud += pos;
		for(int i=pos; i<nbNoeud; i++){
			bf.append(strSpace);
			abrCur=list.get(i);
			if(EstTrieHyprideVide(abrCur)){
				bf.append("   ");
			}else{
				bf.append(abrCur.getCaractere()+","+abrCur.getValeur());
			}
			bf.append(strSpace);
		}
		bf.append("\n");
		
		for(int i=pos; i<nbNoeud; i++){
			
			if(i==pos || EstTrieHyprideVide(list.get(i)) ){
				bf.append(strSpace);
			}else{
				if(EstTrieHyprideVide(list.get(i).getFilsInf())){
					bf.append(strSpace);
				}else{	
					bf.append(strTraitG);	
				}
			
			}
	
			if(EstTrieHyprideVide(list.get(i))){
				bf.append("   ");
			}else{
				bf.append("_|_");
			}
					
			if(EstTrieHyprideVide(list.get(i))){
				bf.append(strSpace);
			}else{
				if(EstTrieHyprideVide(list.get(i).getFilsSup())){
					bf.append(strSpace);
				}else{	
					bf.append(strTraitD);	
				}
			}
		}
		bf.append("\n");
		
		return bf;
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
		System.out.println(nbNoeud);
		for (int i = 0; i < nbNoeud; i++) {
			  list.add(null);
		}
		
		if(EstTrieHyprideVide(abr))
			return list;
		list.set(0, abr);
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
		mot=mot.toLowerCase();
		return AjouteMotRec(abr, mot);
	}
	private static ITrieHybride AjouteMotRec(ITrieHybride abr, String mot){
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
				abr.setFilsEqual(AjouteMotRec(null, queu));
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
				abr.setFilsEqual(AjouteMotRec(abr.getFilsEqual(), queu));
			}else{
				if( noeudChar > tete){
					abr.setFilsInf(AjouteMotRec(abr.getFilsInf(), mot));	
				}else{
					abr.setFilsSup(AjouteMotRec(abr.getFilsSup(), mot));	
					
				}
			}
		}
		return abr;
		
	}
	
	/*Recherche d'un mot dans l'arbre*/
	public static boolean Recherche(ITrieHybride abr, String mot){
		mot=mot.toLowerCase();
		return RechercheRec(abr, mot);
	}
	
	private static boolean RechercheRec(ITrieHybride abr, String mot){
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
			return RechercheRec(abr.getFilsEqual(), queu);
		}
		if(noeudChar < tete)
			return RechercheRec(abr.getFilsSup(), mot);
		return RechercheRec(abr.getFilsInf(), mot);
			
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
		//System.out.println(couple);
		coupletmpInf = NombreProfondeurFeuille(abr.getFilsInf(), new CouplePronfodeurFeuille(couple));
		coupletmpEqual = NombreProfondeurFeuille(abr.getFilsEqual(), new CouplePronfodeurFeuille(couple));
		coupletmpSup = NombreProfondeurFeuille(abr.getFilsSup(), new CouplePronfodeurFeuille(couple));
		
		if(coupletmpInf.estVide() && coupletmpEqual.estVide() && coupletmpSup.estVide() ){
			couple.upNbFeuille();
			return couple;
		}
		//System.out.println(coupletmpInf.toString() + coupletmpEqual.toString() + coupletmpSup.toString());
		sommeProf = coupletmpInf.getProfondeur() + coupletmpEqual.getProfondeur() + coupletmpSup.getProfondeur();
		nbFeuille = coupletmpInf.getNbFeuille() + coupletmpEqual.getNbFeuille() + coupletmpSup.getNbFeuille();
		couple.setProfondeur(sommeProf);
		couple.setNbFeuille(nbFeuille);
		return couple;
			
	}
	
	
	
	/*prefixe arbre mot*/
	public static int Prefixe(ITrieHybride abr, String mot){
		
		char tete, noeudChar;
		String queu;
		if(EstTrieHyprideVide(abr) || FacadeMot.EstMotVide(mot))
			return 0;
		tete = FacadeMot.teteMot(mot);
		queu = FacadeMot.queuMot(mot);
		noeudChar = CharNoeud(abr);
		if(noeudChar == tete){
			if(queu == null)
				return ComptageMot(abr);
		return Prefixe(abr.getFilsEqual(), queu);
		}if(noeudChar < tete)
			return Prefixe(abr.getFilsSup(), mot);
		return Prefixe(abr.getFilsInf(), mot);
	}
	
	/*supression mot*/
	public static ITrieHybride Suppression(ITrieHybride abr, String mot){
		char tete, noeudChar;
		String queu;
		if(EstTrieHyprideVide(abr) || FacadeMot.EstMotVide(mot))
			return abr;
		tete = FacadeMot.teteMot(mot);
		queu = FacadeMot.queuMot(mot);
		noeudChar = CharNoeud(abr);	
		if(noeudChar == tete){
			abr = SuppressionRec(abr, mot);
			//System.out.println("supp " + abr);
		}else{
			if(noeudChar > tete){
				abr.setFilsInf(Suppression(abr.getFilsInf(), mot));
			}else{
				abr.setFilsSup(Suppression(abr.getFilsSup(), mot));
			}
		}
		return abr;
		
	}
	
	private static ITrieHybride SuppressionRec(ITrieHybride abr, String mot){
		//System.out.println("supp rec -");
		char tete, noeudChar, teteQueu;
		String queu;
		if(EstTrieHyprideVide(abr))
			return abr;
		tete = FacadeMot.teteMot(mot);
		queu = FacadeMot.queuMot(mot);
		noeudChar = CharNoeud(abr);
		if(queu == null){
			abr.setValeur(0);
			if(EstTrieHyprideVide(abr.getFilsEqual()))
				return SuppressionChar(abr);
			return abr;
		}
		if(EstTrieHyprideVide(abr.getFilsEqual()))
			return abr;
		if(abr.getFilsEqual().getCaractere() == FacadeMot.teteMot(queu)){
			abr.setFilsEqual(SuppressionRec(abr.getFilsEqual(), (queu)));
			//System.out.println("fils equal " + abr );
		}else{
			if(abr.getFilsEqual().getCaractere() > FacadeMot.teteMot(queu)){
				abr.setFilsInf(SuppressionRec(abr.getFilsEqual().getFilsInf(), queu));
			}else{
				abr.setFilsSup(SuppressionRec(abr.getFilsEqual().getFilsSup(), queu));
			}
				
		}
		//System.out.println("supp rec " + abr );
		return abr;
	}
	
	private static ITrieHybride SuppressionChar(ITrieHybride abr){
		//System.out.println("suppression char");
		ITrieHybride abrDroit = abr.getFilsSup();
		ITrieHybride abrGauche = abr.getFilsInf();
		return ReorganiseDroit(abrGauche, abrDroit);
		
		
		
	}
	
	private static ITrieHybride ReorganiseDroit(ITrieHybride abrGauche, ITrieHybride abrDroit){
		//System.out.println("reorganise" + abrGauche + abrDroit);
		if(EstTrieHyprideVide(abrGauche))
			return abrDroit;
		 abrGauche.setFilsSup(ReorganiseDroit(abrGauche.getFilsSup(), abrDroit));
		 return abrGauche;
	}
	
	private static ITrieHybride ReorganiseGauche(ITrieHybride abrGauche, ITrieHybride abrDroit){
		//System.out.println("reorganise" + abrGauche + abrDroit);
		if(EstTrieHyprideVide(abrDroit))
			return abrGauche;
		 abrDroit.setFilsInf(ReorganiseGauche(abrGauche.getFilsInf(), abrDroit));
		 return abrDroit;
	}
	
	public static void AfficheDansFichier(ITrieHybride abr, String nom){
		   BufferedWriter bwr;
		try {
			bwr = new BufferedWriter(new FileWriter(new File(nom)));
			bwr.write(AfficheTrieHybride(abr));
			bwr.flush();
			bwr.close();
			System.out.println("Fichier creer");
		} catch (IOException e) {
			System.out.println("Fichier non creer");
			e.printStackTrace();
		}
      
           
	}
	
	public static void AjouteMotEquilibre(){
		
	}
}

