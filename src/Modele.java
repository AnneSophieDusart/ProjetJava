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
	int indexRecetteSelectionnee=0;
	boolean modeClair = true;
	
	
	public Modele() {
		
		this.LRecette=new ArrayList<>();
		ArrayList<Ingredient> LIngre=new ArrayList<>();
		ArrayList<Etape> LEtape=new ArrayList<>();
		LIngre.add(new Ingredient (5 ,"Rhum", "cl"));
		LIngre.add(new Ingredient (300 ,"Farine", "g"));
		LIngre.add(new Ingredient (3 ,"Oeufs", " "));
		LIngre.add(new Ingredient (2 ,"Huile", "c�s"));
		LIngre.add(new Ingredient (50 ,"Beurre fondu", "g"));
		LIngre.add(new Ingredient (60 ,"Lait", "cl"));
		
		LEtape.add(new Etape (1,"Mettre la farine dans une terrine et former un puits."));
		LEtape.add(new Etape (2,"Y d�poser les oeufs entiers, le sucre, l'huile et le beurre."));
		LEtape.add(new Etape (3,"M�langer d�licatement avec un fouet en ajoutant au fur et � mesure le lait. La p�te ainsi obtenue doit avoir une consistance d'un liquide l�g�rement �pais."));
		LEtape.add(new Etape (4,"Parfumer de rhum."));
		LEtape.add(new Etape (5,"Faire chauffer une po�le antiadh�sive et la huiler tr�s l�g�rement. Y verser une louche de p�te, la r�partir dans la po�le puis attendre qu'elle soit cuite d'un c�t� avant de la retourner. Cuire ainsi toutes les cr�pes � feu doux."));
		
		Recette crepe = new Recette("Dessert","Traditionnelle","Cr�pe",10,20,2,LIngre, LEtape,"ImageRecette/crepe.jpg");
		
		this.LRecette.add(crepe);
		
		/*this.enregistrer();*/
		this.charger();
		System.out.println(this.LRecette);

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
			throw new RuntimeException("Ecriture des donn�es impossible");
		}finally {
			if (encoder != null) encoder.close();
		}
	}
	
	public void charger() {
		XMLDecoder decoder = null;
		try{
			FileInputStream fis = new FileInputStream(fichier);
			BufferedInputStream bis = new BufferedInputStream(fis);
			decoder = new XMLDecoder(bis);
			
			this.LRecette = (ArrayList<Recette>)decoder.readObject();			
			
		}catch (Exception e){
			throw new RuntimeException("Chargement des donn�es impossible");
		} finally {
			if (decoder != null) decoder.close();
		}

	}
	
	public void selectionRecette(int item) {
		this.indexRecetteSelectionnee = item;
		this.setChanged();
		this.notifyObservers(this.indexRecetteSelectionnee);
	}
	
	public void changeQuantite(Integer n) {
		ArrayList<Integer> l = new ArrayList<>();
		for (int i=0; i<LRecette.get(indexRecetteSelectionnee).Ingredients.size() ; i++){
			int personne=LRecette.get(indexRecetteSelectionnee).Personnes;
			float nouvelleDose=(float)(LRecette.get(indexRecetteSelectionnee).Ingredients.get(i).quantite)/personne*n;	
			l.add((int)Math.ceil(nouvelleDose));
		}
		//System.out.println(l);
		this.setChanged();
		this.notifyObservers(l);
		
	}
	
	public void changeEtape (Recette recette,int etape) {
		//System.out.println(recette.Etapes.get(etape));
		this.setChanged();
		this.notifyObservers(recette.Etapes.get(etape));
	}

}
