import java.util.ArrayList;

public class Modele {
	
	ArrayList <Recette> LRecette;
	
	public Modele() {
		
		this.LRecette=new ArrayList<>();
		String[] Icrepe = {"5 cl de Rhum","300g de Farine","3 oeufs","3 c�s de sucre","2 c�s d'huile","50g de beurre fondu","60 cl de lait"};
		String[] Ecrepe = {"Mettre la farine dans une terrine et former un puits.","Y d�poser les oeufs entiers, le sucre, l'huile et le beurre.","M�langer d�licatement avec un fouet en ajoutant au fur et � mesure le lait. La p�te ainsi obtenue doit avoir une consistance d'un liquide l�g�rement �pais." ,"Parfumer de rhum.","Faire chauffer une po�le antiadh�sive et la huiler tr�s l�g�rement. Y verser une louche de p�te, la r�partir dans la po�le puis attendre qu'elle soit cuite d'un c�t� avant de la retourner. Cuire ainsi toutes les cr�pes � feu doux."};
		Recette crepe = new Recette("Dessert","Cr�pe",10,20,2,Icrepe, Ecrepe,"ImageRecette/crepe.jpg");
		this.LRecette.add(crepe);
	}

}
