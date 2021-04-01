import java.io.Serializable;
import java.util.ArrayList;

public class Recette implements Serializable{
	
	String Nom;
	int TempsPreparation, TempsCuisson;
	ArrayList<Ingredient> Ingredients;
	ArrayList<Etape> Etapes;
	String Image;
	String Type;
	int Personnes;
	
	
	public Recette(String type, String nom,int TempsP, int TempsC,int perso, ArrayList<Ingredient> Ingre, ArrayList<Etape> Etape, String Image) {
		
		this.Type=type;
		this.Nom= nom;
		this.TempsPreparation = TempsP;
		this.TempsCuisson=TempsC;
		this.Ingredients=Ingre;
		this.Etapes=Etape;
		this.Image=Image;
		this.Personnes=perso;
		
		
	}


	public String getNom() {
		return Nom;
	}


	public void setNom(String nom) {
		Nom = nom;
	}


	public int getTempsPreparation() {
		return TempsPreparation;
	}


	public void setTempsPreparation(int tempsPreparation) {
		TempsPreparation = tempsPreparation;
	}


	public int getTempsCuisson() {
		return TempsCuisson;
	}


	public void setTempsCuisson(int tempsCuisson) {
		TempsCuisson = tempsCuisson;
	}


	public ArrayList<Ingredient> getIngredients() {
		return Ingredients;
	}


	public void setIngredients(ArrayList<Ingredient> ingredients) {
		Ingredients = ingredients;
	}


	public ArrayList<Etape> getEtapes() {
		return Etapes;
	}


	public void setEtapes(ArrayList<Etape> etapes) {
		Etapes = etapes;
	}


	public String getImage() {
		return Image;
	}


	public void setImage(String image) {
		Image = image;
	}


	public String getType() {
		return Type;
	}


	public void setType(String type) {
		Type = type;
	}


	public int getPersonnes() {
		return Personnes;
	}


	public void setPersonnes(int personnes) {
		Personnes = personnes;
	}

}
