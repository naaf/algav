package m1.algav.tries.hybrides;

public interface ITrieHybride {

	public char getCaractere();
	public void setCaractere(char caractere);
	public int getValeur();
	public void setValeur(int valeur);
	public ITrieHybride getFilsEqual();
	public void setFilsEqual(ITrieHybride filsEqual);
	public ITrieHybride getFilsInf();
	public void setFilsInf(ITrieHybride filsInf);
	public ITrieHybride getFilsSup();
	public void setFilsSup(ITrieHybride filsSup);


	
}
