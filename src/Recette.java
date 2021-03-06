import java.io.Serializable;
import java.util.ArrayList;

public class Recette implements Serializable{
	
	String Nom;
	int TempsPreparation, TempsCuisson;
	ArrayList<Ingredient> Ingredients;
	ArrayList<Etape> Etapes;
	String Image;
	String Type1, Type2;
	int Personnes;
	
	public Recette() {}
	
	public Recette(String type1, String type2 , String nom,int TempsP, int TempsC,int perso, ArrayList<Ingredient> Ingre, ArrayList<Etape> Etape, String Image) {
		
		this.Type1=type1; //
		this.Type2=type2; //
		this.Nom= nom; //
		this.TempsPreparation = TempsP; //
		this.TempsCuisson=TempsC; //
		this.Ingredients=Ingre;
		this.Etapes=Etape;
		this.Image=Image;
		this.Personnes=perso; //
		
	}


	public String getType1() {
		return Type1;
	}

	public void setType1(String type1) {
		Type1 = type1;
	}

	public String getType2() {
		return Type2;
	}

	public void setType2(String type2) {
		Type2 = type2;
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


	public int getPersonnes() {
		return Personnes;
	}


	public void setPersonnes(int personnes) {
		Personnes = personnes;
	}
	
	
	public String toString() {
		return this.Nom + " " + this.Etapes.size();
		
		
	}
	
	@Override
	public boolean equals(Object o) {
		return (this.Nom.equals(((Recette) o).getNom()));
	}
	
	public String afficherIngredients(int nbr) {
		String s = "Liste des ingr?dients pour "+nbr+" personnes : \n";
		Ingredient ing;
		float qte;
		int arr;
		for (int i=0; i<this.Ingredients.size(); i++) {
			ing = this.Ingredients.get(i);
			qte=(float) ing.getQuantite()*nbr/this.getPersonnes();
			arr = (int) Math.ceil(qte);
			s=s+"- "+arr+ing.getMesure()+" "+ing.getIngredient()+"\n";
		}
		return s;
	}
	
	public String afficherIngredientsHTML(int nbr) {
		String s = "<html><span>";
		Ingredient ing;
		float qte;
		int arr;
		for (int i=0; i<this.Ingredients.size(); i++) {
			ing = this.Ingredients.get(i);
			qte=(float) ing.getQuantite()*nbr/this.getPersonnes();
			arr = (int) Math.ceil(qte);
			s=s+"- "+arr+ing.getMesure()+" "+ing.getIngredient()+"</br>";
		}
		s=s+"</span></html>";
		return s;
	}
	
	public void changeEtape(int nbr_etp, String txt) {
		Etape etp = new Etape(nbr_etp+1, txt);
		this.Etapes.set(nbr_etp, etp);
		
	}

}
