import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Observable;


public class Modele extends Observable{
	
	ArrayList <Recette> LRecette;
	File fichier = new File("Recette.xml");
	
	
	public Modele() {
		
		this.LRecette=new ArrayList<>();
		ArrayList<Ingredient> LIngre=new ArrayList<>();
		ArrayList<Etape> LEtape=new ArrayList<>();
		LIngre.add(new Ingredient (5 ,"Rhum", "cl"));
		LIngre.add(new Ingredient (300 ,"Farine", "g"));
		LIngre.add(new Ingredient (3 ,"Oeufs", " "));
		LIngre.add(new Ingredient (2 ,"Huile", "càs"));
		LIngre.add(new Ingredient (50 ,"Beurre fondu", "g"));
		LIngre.add(new Ingredient (60 ,"Lait", "cl"));
		
		LEtape.add(new Etape (1,"Mettre la farine dans une terrine et former un puits."));
		LEtape.add(new Etape (2,"Y déposer les oeufs entiers, le sucre, l'huile et le beurre."));
		LEtape.add(new Etape (3,"Mélanger délicatement avec un fouet en ajoutant au fur et à mesure le lait. La pâte ainsi obtenue doit avoir une consistance d'un liquide légèrement épais."));
		LEtape.add(new Etape (4,"Parfumer de rhum."));
		LEtape.add(new Etape (5,"Faire chauffer une poêle antiadhésive et la huiler très légèrement. Y verser une louche de pâte, la répartir dans la poêle puis attendre qu'elle soit cuite d'un côté avant de la retourner. Cuire ainsi toutes les crêpes à feu doux."));
		
		Recette crepe = new Recette("Dessert","Crêpe",10,20,2,LIngre, LEtape,"ImageRecette/crepe.jpg");
		
		this.LRecette.add(crepe);
		
		XMLDecoder decoder = null;
		try{
			FileInputStream fis = new FileInputStream(fichier);
			BufferedInputStream bis = new BufferedInputStream(fis);
			decoder = new XMLDecoder(bis);
			
			this.LRecette = (ArrayList<Recette>)decoder.readObject();			
			
		}catch (Exception e){
			throw new RuntimeException("Chargement des données impossible");
		} finally {
			if (decoder != null) decoder.close();
		}

	}
	
	public void enregistrer (){
		XMLEncoder encoder = null;
		try{
			FileOutputStream fos = new FileOutputStream(fichier);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			encoder = new XMLEncoder(bos);
			
			encoder.writeObject(this.LRecette);
			encoder.flush();
			
		} catch (final java.io.IOException e){
			throw new RuntimeException("Ecriture des données impossible");
		}finally {
			if (encoder != null) encoder.close();
		}
		
	}

}
