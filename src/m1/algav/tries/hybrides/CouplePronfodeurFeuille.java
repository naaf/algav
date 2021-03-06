package m1.algav.tries.hybrides;

public class CouplePronfodeurFeuille {
	
	private int profondeur;
	private int nbFeuille;
	
	
	public CouplePronfodeurFeuille() {
		super();
		this.profondeur = 0;
		this.nbFeuille = 0;
	}
	
	public CouplePronfodeurFeuille(CouplePronfodeurFeuille p) {
		super();
		this.profondeur = p.profondeur;
		this.nbFeuille = p.nbFeuille;
	}
	
	public CouplePronfodeurFeuille(int profondeur, int nbFeuille) {
		super();
		this.profondeur = profondeur;
		this.nbFeuille = nbFeuille;
	}
	
	public int getProfondeur() {
		return profondeur;
	}
	public void setProfondeur(int profondeur) {
		this.profondeur = profondeur;
	}
	public void upProfondeur() {
		this.profondeur++;
	}
	public int getNbFeuille() {
		return nbFeuille;
	}
	public void setNbFeuille(int nbFeuille) {
		this.nbFeuille = nbFeuille;
	}
	public void upNbFeuille() {
		this.nbFeuille++;
	}

	
	public boolean estVide() {
		if(this.profondeur == 0 && this.nbFeuille == 0)
			return true;
		return false;
	} 
	
	@Override
	public String toString() {
		return "["+this.profondeur +","+this.nbFeuille+"]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + nbFeuille;
		result = prime * result + profondeur;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CouplePronfodeurFeuille other = (CouplePronfodeurFeuille) obj;
		if (nbFeuille != other.nbFeuille)
			return false;
		if (profondeur != other.profondeur)
			return false;
		return true;
	}
	
	

}
