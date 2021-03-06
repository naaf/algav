package m1.algav.briandais;

public class NoeudBRD {
	static final char MOT_NULL = '\0';
	private char cle;
	private NoeudBRD fils;
	private NoeudBRD frere;

	public NoeudBRD() {
		this.cle = MOT_NULL;
		this.fils = null;
		this.frere = null;
	}

	public NoeudBRD(char cle, NoeudBRD fils, NoeudBRD frere) {
		super();
		this.cle = cle;
		this.fils = fils;
		this.frere = frere;
	}

	public NoeudBRD(char cle) {
		super();
		this.cle = cle;
		this.fils = null;
		this.frere = null;
	}

	public char getCle() {
		return cle;
	}

	public void setCle(char cle) {
		this.cle = cle;
	}

	public NoeudBRD getFils() {
		return fils;
	}

	public void setFils(NoeudBRD fils) {
		this.fils = fils;
	}

	public NoeudBRD getFrere() {
		return frere;
	}

	public void setFrere(NoeudBRD frere) {
		this.frere = frere;
	}

	@Override
	public String toString() {
		return "NoeudBRD [cle=" + cle + ", fils=" + fils + ", frere=" + frere + "]";
	}

}
