package m1.algav.tries.hybrides.test;

import java.util.ArrayList;
import java.util.List;

import m1.algav.tries.hybrides.FacadeTrieHybride;
import m1.algav.tries.hybrides.ITrieHybride;

public class TestTrieHybride {
	
	public static void main(String[] args){
		ITrieHybride abr = FacadeTrieHybride.AjouteMot(null, "to");
		abr = FacadeTrieHybride.AjouteMot(abr, "tata");
		//System.out.println(abr.toString());
		
		List<ITrieHybride> list= new ArrayList<ITrieHybride>(30);
		ajoutElem(list, 3);
		;
		
		 System.out.println(list);
		 System.out.println(Math.log(40)/Math.log(3));
		 
		  
	}
	
	public static void ajoutElem(List<ITrieHybride> l, int i){
		l.add(null);
		if(i>0)
			ajoutElem(l, --i);
	}
	


}
