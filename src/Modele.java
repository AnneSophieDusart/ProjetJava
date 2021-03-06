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
		LIngre.add(new Ingredient (2 ,"Huile", "c?s"));
		LIngre.add(new Ingredient (50 ,"Beurre fondu", "g"));
		LIngre.add(new Ingredient (60 ,"Lait", "cl"));
		
		LEtape.add(new Etape (1,"Mettre la farine dans une terrine et former un puits."));
		LEtape.add(new Etape (2,"Y d?poser les oeufs entiers, le sucre, l'huile et le beurre."));
		LEtape.add(new Etape (3,"M?langer d?licatement avec un fouet en ajoutant au fur et ? mesure le lait. La p?te ainsi obtenue doit avoir une consistance d'un liquide l?g?rement ?pais."));
		LEtape.add(new Etape (4,"Parfumer de rhum."));
		LEtape.add(new Etape (5,"Faire chauffer une po?le antiadh?sive et la huiler tr?s l?g?rement. Y verser une louche de p?te, la r?partir dans la po?le puis attendre qu'elle soit cuite d'un c?t? avant de la retourner. Cuire ainsi toutes les cr?pes ? feu doux."));
		
		Recette crepe = new Recette("Dessert","Traditionnelle","Cr?pe",10,20,2,LIngre, LEtape,"ImageRecette/crepe.jpg");
		
		this.LRecette.add(crepe);
		
		/*this.enregistrer();*/
		this.charger();
		//System.out.println(this.LRecette);
		this.dicoType = new HashMap<>();
		
		ArrayList<String> listeEntree = new ArrayList<String>();
		ArrayList<String> listePlat = new ArrayList<String>();
		ArrayList<String> listeDessert = new ArrayList<String>();	
		for (int i=0;i<LRecette.size();i++) {
			if ((LRecette.get(i).Type1).equals("Entr?e")) {
				listeEntree.add(LRecette.get(i).Nom);
			}
			else if ((LRecette.get(i).Type1).equals("Plat")) {
				listePlat.add(LRecette.get(i).Nom);
			}
			else{
				listeDessert.add(LRecette.get(i).Nom);
			}
			}
		this.dicoType.put("Entr?e",listeEntree);
		this.dicoType.put("Plat",listePlat);
		this.dicoType.put("Dessert",listeDessert);
	}
	
	public void enregistrer (){
		XMLEncoder encoder = null;
		try{
			FileOutputStream fos = new FileOutputStream(fichier);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			encoder = new XMLEncoder(bos);
			
			encoder.writeObject(modeClair);
			encoder.writeObject(this.LRecette);
			encoder.flush();
			
		} catch (final java.io.IOException e){
			throw new RuntimeException("Ecriture des donn?es impossible");
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
			
			this.modeClair = (boolean)decoder.readObject();
			this.LRecette = (ArrayList<Recette>)decoder.readObject();			
			
		}catch (Exception e){
			throw new RuntimeException("Chargement des donn?es impossible");
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
        String textToSearch = text.toLowerCase();
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
		//String [] origine = new String[] {"France", "Gr?ce","Italie","Vietnam","Japon","Etats-Unis","Mexique","Allemagne","Espagne","Autre"} ;
		//List<String> origineListe = Arrays.asList(origine);
		if (filtre=="Type"){
			String [] type = new String[] {" ","Entr?e", "Plat", "Dessert"} ;
	    	this.setChanged();
	    	this.notifyObservers(type);
	        	}
		else {
			String [] origine = new String[] {" ","France", "Gr?ce","Italie","Vietnam","Japon","Etats-Unis","Mexique","Allemagne","Espagne","Autre"} ;
	    	this.setChanged();
	    	this.notifyObservers(origine);			
		}
	        }

	public void filtrerListe(String filtre) {
		String [] type = new String[] {"Entr?e", "Plat", "Dessert"} ;
		List<String> typeListe = Arrays.asList(type);
		//String [] origine = new String[] {"France", "Gr?ce","Italie","Vietnam","Japon","Etats-Unis","Mexique","Allemagne","Espagne","Autre"} ;
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
	
	public void listeRechercheIngredients(String ingredient1, String ingredient2, String[] tableau) {
		
		//System.out.println(ingredient1);
		//System.out.println(ingredient2);
		String[] newTableau;
		
		ingredient1=ingredient1.toLowerCase();
		ingredient2=ingredient2.toLowerCase();

		ArrayList<String> listIngredients1 = new ArrayList<String>();
		String[] Tableau1;
		
		if (ingredient1.equals("oeufs") && ingredient2.equals("oeufs")) {
			ArrayList<Ingredient> LIngre=new ArrayList<>();
			ArrayList<Etape> LEtape=new ArrayList<>();
			LIngre.add(new Ingredient (100 ,"de gentillesse", "g"));
			LIngre.add(new Ingredient (10 ,"?toiles", ""));
			LIngre.add(new Ingredient (3 ,"c?lins", ""));
			LIngre.add(new Ingredient (100 ,"de chanson", "cl"));
			LIngre.add(new Ingredient (900 ,"de \" Je t'aime \"", "cl"));
			LIngre.add(new Ingredient (10 ,"de solidarit?", "g"));
			LIngre.add(new Ingredient (250 ,"de mer", "cl"));
			LIngre.add(new Ingredient (200 ,"de soleil", "g"));
			
			LEtape.add(new Etape (1,"Couper les ?toiles en tranches"));
			LEtape.add(new Etape (2,"Les faire cuire dans une po?le ? feu doux"));
			LEtape.add(new Etape (3,"Mettre les cl de \"Je t'aime\" dans un saladier."));
			LEtape.add(new Etape (4,"Enlever les ?toiles (elles ne sont pas commestibles)"));
			LEtape.add(new Etape (5,"Disposer les c?lins sur une plaque"));
			LEtape.add(new Etape (6,"Verser la solidarit? sur la plaque"));
			LEtape.add(new Etape (7,"Verser les paroles de la chanson dans un saladier"));
			LEtape.add(new Etape (8,"Asperger avec la mer"));
			LEtape.add(new Etape (9,"Mettre tout les ingr?dients sur la plaque"));
			LEtape.add(new Etape (10,"Enfourner au four"));
			LEtape.add(new Etape (1,"Faire cuire pendant toute une vie avec le soleil"));
			
			Recette crepe = new Recette("Easter Egg","Geek","Bonheur",0,0,1,LIngre, LEtape,"ImageAppli/easter.jpg");
			
			Controleur ctrl = new Controleur(this);
			
			Swing_Details win = new Swing_Details(crepe, this, ctrl);
			win.setVisible(crepe);
		}
		
		if (ingredient1.equals("") || ingredient1.equals("premier ingr?dient")) {
			Tableau1 = tableau;
		}
		else {
		//System.out.println(ingredient1.equals("") || ingredient1.equals("Premier ingr?dient"));
        	for (int i=0; i<LRecette.size();i++) {
        		for (int j=0; j<LRecette.get(i).Ingredients.size();j++) {
        			if (ingredient1.contains(LRecette.get(i).Ingredients.get(j).ingredient.toLowerCase())==true || LRecette.get(i).Ingredients.get(j).ingredient.toLowerCase().contains(ingredient1) || ingredient1.equals(LRecette.get(i).Ingredients.get(j).ingredient.toLowerCase())  ) {
        				if (listIngredients1.contains(LRecette.get(i).Nom)==false) {
        					listIngredients1.add(LRecette.get(i).Nom);}
        			}
        		}
        	}
        	Tableau1 = listIngredients1.toArray(new String[listIngredients1.size()]);
		}
        
        	ArrayList<String> listIngredients2 = new ArrayList<String>();
			String[] Tableau2;
		if (ingredient2.equals("") || ingredient2.equals("second ingr?dient")) {
				Tableau2 = tableau;
			}
		else {
			for (int i=0; i<LRecette.size();i++) {
        		for (int j=0; j<LRecette.get(i).Ingredients.size();j++) {
        			if (ingredient2.contains(LRecette.get(i).Ingredients.get(j).ingredient.toLowerCase())==true || LRecette.get(i).Ingredients.get(j).ingredient.toLowerCase().contains(ingredient2) || ingredient2.equals(LRecette.get(i).Ingredients.get(j).ingredient.toLowerCase())  ) {
        				if (listIngredients2.contains(LRecette.get(i).Nom)==false) {
        					listIngredients2.add(LRecette.get(i).Nom);}
        			}
        		}
        	}
        	Tableau2 = listIngredients2.toArray(new String[listIngredients2.size()]);
		}
        	ArrayList<String> liste1 = new ArrayList<>(Arrays.asList(Tableau1));
        	ArrayList<String> liste2 = new ArrayList<>(Arrays.asList(Tableau2));
        	//System.out.println(liste1);
        	//System.out.println(liste2);
        	ArrayList<String> listeTriees = new ArrayList<String>();
             for (String t : liste1) {
                 if(liste2.contains(t)) {
                	 listeTriees.add(t);
                 }
             }

        	newTableau = listeTriees.toArray(new String[listeTriees.size()]);
        	this.setChanged();
		this.notifyObservers(newTableau);
	}
			
		
	
}
