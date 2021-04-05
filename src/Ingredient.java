import java.io.Serializable;
import java.util.ArrayList;

public class Ingredient implements Serializable{
	
	int quantite;
	String ingredient, mesure;
	
	public Ingredient() {}
	
	public int getQuantite() {
		return quantite;
	}


	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}


	public String getIngredient() {
		return ingredient;
	}


	public void setIngredient(String ingredient) {
		this.ingredient = ingredient;
	}


	public String getMesure() {
		return mesure;
	}


	public void setMesure(String mesure) {
		this.mesure = mesure;
	}

	
	public Ingredient (int Quantite, String Ingre, String Mesure ) {
		
		this.quantite=Quantite;
		this.ingredient = Ingre;
		this.mesure=Mesure;
		
	}
	
	
	public String toString() {
		String out = this.ingredient + " : " + String.valueOf(this.quantite) + " " + this.mesure ;
		return out;
	}


}
