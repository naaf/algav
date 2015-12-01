package m1.algav.tries.hybrides;

public class TrieHybride implements ITrieHybride {
	
	private char caractere;
	private int valeur;
	
	private ITrieHybride FilsEqual; 
	private ITrieHybride FilsInf; 
	private ITrieHybride FilsSup;
	
	
	public TrieHybride(char caractere, ITrieHybride filsEqual,
			ITrieHybride filsInf, ITrieHybride filsSup) {
		super();
		this.caractere = caractere;
		FilsEqual = filsEqual;
		FilsInf = filsInf;
		FilsSup = filsSup;
	}
	public char getCaractere() {
		return caractere;
	}
	public void setCaractere(char caractere) {
		this.caractere = caractere;
	}
	public int getValeur() {
		return valeur;
	}
	public void setValeur(int valeur) {
		this.valeur = valeur;
	}
	public ITrieHybride getFilsEqual() {
		return FilsEqual;
	}
	public void setFilsEqual(ITrieHybride filsEqual) {
		FilsEqual = filsEqual;
	}
	public ITrieHybride getFilsInf() {
		return FilsInf;
	}
	public void setFilsInf(ITrieHybride filsInf) {
		FilsInf = filsInf;
	}
	public ITrieHybride getFilsSup() {
		return FilsSup;
	}
	public void setFilsSup(ITrieHybride filsSup) {
		FilsSup = filsSup;
	}
	@Override
	public String toString() {
		return "["+caractere + "," + valeur +"]"+ FilsEqual + FilsInf + FilsSup;
	}
	
	
	

	

}
