import java.util.ArrayList;

public class Recette {
	
	String Nom;
	int TempsPreparation, TempsCuisson;
	String[] Ingredients, Etapes;
	String Image;
	String Type;
	int Personnes;
	
	
	public Recette(String type, String nom,int TempsP, int TempsC,int perso, String[] Ingre, String[] Etape, String Image) {
		
		this.Type=type;
		this.Nom= nom;
		this.TempsPreparation = TempsP;
		this.TempsCuisson=TempsC;
		this.Ingredients=Ingre;
		this.Etapes=Etape;
		this.Image=Image;
		this.Personnes=perso;
		
		
	}

}
