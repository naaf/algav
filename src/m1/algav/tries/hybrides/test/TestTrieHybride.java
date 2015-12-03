package m1.algav.tries.hybrides.test;

import java.util.ArrayList;
import java.util.List;

import m1.algav.tries.hybrides.FacadeTrieHybride;
import m1.algav.tries.hybrides.ITrieHybride;

public class TestTrieHybride {
	
	public static void main(String[] args){
		ITrieHybride abr = FacadeTrieHybride.AjouteMot(null, "to");
		abr = FacadeTrieHybride.AjouteMot(abr, "tata");
		abr = FacadeTrieHybride.AjouteMot(abr, "zata");
	//	abr = FacadeTrieHybride.AjouteMot(abr, "wala");
	//	abr = FacadeTrieHybride.AjouteMot(abr, "vala");
	//	abr = FacadeTrieHybride.AjouteMot(abr, "Val");
		//FacadeTrieHybride.AfficheTrieHybride(abr);
		
		//abr=FacadeTrieHybride.Suppression(abr, "to");
		
		//FacadeTrieHybride.AfficheTrieHybride(abr);
		FacadeTrieHybride.AfficheDansFichier(abr, "test");
	//	System.out.println(FacadeTrieHybride.ListeMots(abr).toString());
	//	System.out.println(FacadeTrieHybride.ComptageMot(abr));
	//	System.out.println(FacadeTrieHybride.ComptageNil(abr));
	//	System.out.println(FacadeTrieHybride.Recherche(abr, "tata"));
	//	System.out.println(FacadeTrieHybride.Hauteur(abr));
	//	System.out.println(FacadeTrieHybride.ProfondeurMoyenne(abr));
	//	System.out.println(FacadeTrieHybride.Prefixe(abr, "val"));
		
		
		 
		  
	}
	
	


}
