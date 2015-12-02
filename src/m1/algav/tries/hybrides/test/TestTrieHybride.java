package m1.algav.tries.hybrides.test;

import java.util.ArrayList;
import java.util.List;

import m1.algav.tries.hybrides.FacadeTrieHybride;
import m1.algav.tries.hybrides.ITrieHybride;

public class TestTrieHybride {
	
	public static void main(String[] args){
		ITrieHybride abr = FacadeTrieHybride.AjouteMot(null, "to");
		abr = FacadeTrieHybride.AjouteMot(abr, "tata");
		FacadeTrieHybride.AfficheTrieHybride(abr);

		 
		  
	}
	
	public static void ajoutElem(List<ITrieHybride> l, int i){
		l.add(null);
		if(i>0)
			ajoutElem(l, --i);
	}
	


}
