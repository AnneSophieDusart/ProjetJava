import java.io.Serializable;

public class Etape implements Serializable{
	
	int NumEtape;
	String Instrution;	
	String Commentaire;
	
	public int getNumEtape() {
		return NumEtape;
	}


	public void setNumEtape(int numEtape) {
		NumEtape = numEtape;
	}


	public String getInstrution() {
		return Instrution;
	}


	public void setInstrution(String instrution) {
		Instrution = instrution;
	}
	
	public String getCommentaire() {
		return Commentaire;
	}



	public Etape() {}
	
	public Etape (int N, String I) {
		
		this.NumEtape=N;
		this.Instrution = I;
		
	}

}
