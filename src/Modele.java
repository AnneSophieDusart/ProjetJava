import java.util.ArrayList;

public class Modele {
	
	ArrayList <Recette> LRecette;
	
	public Modele() {
		
		this.LRecette=new ArrayList<>();
		String[] Icrepe = {"5 cl de Rhum","300g de Farine","3 oeufs","3 càs de sucre","2 càs d'huile","50g de beurre fondu","60 cl de lait"};
		String[] Ecrepe = {"Mettre la farine dans une terrine et former un puits.","Y déposer les oeufs entiers, le sucre, l'huile et le beurre.","Mélanger délicatement avec un fouet en ajoutant au fur et à mesure le lait. La pâte ainsi obtenue doit avoir une consistance d'un liquide légèrement épais." ,"Parfumer de rhum.","Faire chauffer une poêle antiadhésive et la huiler très légèrement. Y verser une louche de pâte, la répartir dans la poêle puis attendre qu'elle soit cuite d'un côté avant de la retourner. Cuire ainsi toutes les crêpes à feu doux."};
		Recette crepe = new Recette("Dessert","Crêpe",10,20,2,Icrepe, Ecrepe,"ImageRecette/crepe.jpg");
		this.LRecette.add(crepe);
	}

}
