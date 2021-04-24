import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import javax.swing.JList;


public class Modele extends Observable{
	
	ArrayList <Recette> LRecette;
	File fichier = new File("Recette.xml");
	int indexRecetteSelectionnee=0;
	boolean modeClair = true;
	Map<String, ArrayList<String>> dicoType;
	
	
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
		
		Recette crepe = new Recette("Dessert","Traditionnelle","Crêpe",10,20,2,LIngre, LEtape,"ImageRecette/crepe.jpg");
		
		this.LRecette.add(crepe);
		
		/*this.enregistrer();*/
		this.charger();
		System.out.println(this.LRecette);
		this.dicoType = new HashMap<>();
		
		ArrayList<String> listeEntree = new ArrayList<String>();
		ArrayList<String> listePlat = new ArrayList<String>();
		ArrayList<String> listeDessert = new ArrayList<String>();	
		for (int i=0;i<LRecette.size();i++) {
			if ((LRecette.get(i).Type1).equals("Entrée")) {
				listeEntree.add(LRecette.get(i).Nom);
			}
			else if ((LRecette.get(i).Type1).equals("Plat")) {
				listePlat.add(LRecette.get(i).Nom);
			}
			else{
				listeDessert.add(LRecette.get(i).Nom);
			}
			}
		this.dicoType.put("Entrée",listeEntree);
		this.dicoType.put("Plat",listePlat);
		this.dicoType.put("Dessert",listeDessert);
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
	
	public void charger() {
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
	
	public void selectionRecette(int item) {
		this.indexRecetteSelectionnee = item;
		this.setChanged();
		this.notifyObservers(this.indexRecetteSelectionnee);
	}
	
	/*public void changeQuantite(Integer n) {
		ArrayList<Integer> l = new ArrayList<>();
		for (int i=0; i<LRecette.get(indexRecetteSelectionnee).Ingredients.size() ; i++){
			int personne=LRecette.get(indexRecetteSelectionnee).Personnes;
			float nouvelleDose=(float)(LRecette.get(indexRecetteSelectionnee).Ingredients.get(i).quantite)/personne*n;	
			l.add((int)Math.ceil(nouvelleDose));
		}
		//System.out.println(l);
		this.setChanged();
		this.notifyObservers(l);
		
	}*/
	
	public void changeEtape (Recette recette,int etape) {
		//System.out.println(recette.Etapes.get(etape));
		this.setChanged();
		this.notifyObservers(recette.Etapes.get(etape));
	}

	public void listeRechercheNom(String text, String[] tableau) {
		// TODO Auto-generated method stub
        String textToSearch = text;
        ArrayList<String> newList = new ArrayList<String>();
        for (String current : tableau) {
            if (current.toLowerCase().
                contains(textToSearch))
            	newList.add (current);
        }
        String[] newTableau = newList.toArray(new String[newList.size()]);
        this.setChanged();
		this.notifyObservers(newTableau);
	}

	public void filtrerType(String filtre) {
		//String [] origine = new String[] {"France", "Grèce","Italie","Vietnam","Japon","Etats-Unis","Mexique","Allemagne","Espagne","Autre"} ;
		//List<String> origineListe = Arrays.asList(origine);
		if (filtre=="Type"){
			String [] type = new String[] {" ","Entrée", "Plat", "Dessert"} ;
	    	this.setChanged();
	    	this.notifyObservers(type);
	        	}
		else {
			String [] origine = new String[] {" ","France", "Grèce","Italie","Vietnam","Japon","Etats-Unis","Mexique","Allemagne","Espagne","Autre"} ;
	    	this.setChanged();
	    	this.notifyObservers(origine);			
		}
	        }

	public void filtrerListe(String filtre) {
		String [] type = new String[] {"Entrée", "Plat", "Dessert"} ;
		List<String> typeListe = Arrays.asList(type);
		//String [] origine = new String[] {"France", "Grèce","Italie","Vietnam","Japon","Etats-Unis","Mexique","Allemagne","Espagne","Autre"} ;
		//List<String> origineListe = Arrays.asList(origine);
		ArrayList<String> newList = new ArrayList<String>();
		if (typeListe.contains(filtre)) {
			/*for (int i=0;i<LRecette.size();i++) {
				if ((LRecette.get(i).Type1).equals(filtre)) {
					newList.add(LRecette.get(i).Nom);
				}}*/
			newList= this.dicoType.get(filtre);
		}
		else if (filtre==" ") {
			for (int i=0;i<LRecette.size();i++) {
				newList.add(LRecette.get(i).Nom);
				}
			
		}
		else {
			for (int i=0;i<LRecette.size();i++) {
				if ((LRecette.get(i).Type2).equals(filtre)) {
					newList.add(LRecette.get(i).Nom);
				}}
		}
		//System.out.println(newList);
    	this.setChanged();
    	this.notifyObservers(newList);
	}
			
		

}
