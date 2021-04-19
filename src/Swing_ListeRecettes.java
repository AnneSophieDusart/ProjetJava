import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Swing_ListeRecettes {
	
	boolean modeClair = true;
	Modele m;
	Controleur ctrl;

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Modele m = new Modele();
		Controleur ctrl = new Controleur(m);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Swing_ListeRecettes window = new Swing_ListeRecettes(true,m,ctrl);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Swing_ListeRecettes(Boolean modeClair, Modele modl, Controleur cont) {
		if (modeClair) {
			try {
			    UIManager.setLookAndFeel( new FlatLightLaf() );
			} catch( Exception ex ) {
			    System.err.println( "Failed to initialize LaF" );
			}
		} else {
			try {
			    UIManager.setLookAndFeel( new FlatDarkLaf() );
			} catch( Exception ex ) {
			    System.err.println( "Failed to initialize LaF" );
			}
		}
		this.m= modl;
		this.ctrl=cont;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 560, 427);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 50, 526, 330);
		frame.getContentPane().add(scrollPane);
		
		JList list = new JList();
		list.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		String[] values = new String[m.LRecette.size()];
		for (int i=0; i<this.m.LRecette.size(); i++) {
			values[i]=m.LRecette.get(i).Nom;
		}
		list.setModel(new AbstractListModel() {
			//String[] values = new String[] {"Recette 1", "Recette 2", "Recette 3", "Recette 1", "Recette 2", "Recette 3", "Recette 1", "Recette 2", "Recette 3", "Recette 1", "Recette 2", "Recette 3", "Recette 1", "Recette 2", "Recette 3", "Recette 1", "Recette 2", "Recette 3"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		scrollPane.setViewportView(list);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("ImageAppli\\add_normal.png"));
		lblNewLabel.setBounds(466, 10, 30, 30);
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel.setIcon(new ImageIcon("ImageAppli\\add_souris.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblNewLabel.setIcon(new ImageIcon("ImageAppli\\add_normal.png"));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				String titre = JOptionPane.showInputDialog(frame, "Quel est le titre de la recette ?", "Ajouter une recette", JOptionPane.QUESTION_MESSAGE);
				if (titre==null) {
					return;
				}
				
				String[] types_recettes = {"Entrée", "Plat", "Dessert"};
				String type1 = (String) JOptionPane.showInputDialog(frame, "De quel type de plat s'agit-il ?", "Ajouter une nouvelle recette", JOptionPane.INFORMATION_MESSAGE, null, types_recettes, types_recettes[0]);
				if (type1==null) {
					return;
				}
				
				String[] origines_recettes = {"Traditionnelle", "A compléter"};
				String type2 = (String) JOptionPane.showInputDialog(frame, "Quelle est l'origine du plat ?", "Ajouter une nouvelle recette", JOptionPane.INFORMATION_MESSAGE, null, origines_recettes, origines_recettes[0]);
				if (type2==null) {
					return;
				}
				
				boolean incorrect=true;
				int personne=-1;
				while (incorrect) {
					try {
						String str = JOptionPane.showInputDialog(frame, "Pour combien de personnes ?", "Ajouter une recette",  JOptionPane.QUESTION_MESSAGE);
						if (str==null) {
							return;
						}
						personne = Integer.parseInt(str);
						incorrect=false;
					} catch (NumberFormatException ne) {
						JOptionPane.showMessageDialog(frame, "ERREUR : Merci de bien vouloir entrer un nombre valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
					}
				}
				
				incorrect=true;
				int temps_preparation=-1;
				while (incorrect) {
					try {
						String str = JOptionPane.showInputDialog(frame, "Quel est le temps de préparation ?", "Ajouter une recette",  JOptionPane.QUESTION_MESSAGE);
						if (str==null) {
							return;
						}
						temps_preparation = Integer.parseInt(str);
						incorrect=false;
					} catch (NumberFormatException ne) {
						JOptionPane.showMessageDialog(frame, "ERREUR : Merci de bien vouloir entrer un nombre valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
					}
				}
				
				incorrect=true;
				int temps_cuisson=-1;
				while (incorrect) {
					try {
						String str = JOptionPane.showInputDialog(frame, "Quel est le temps de cuisson ?", "Ajouter une recette",  JOptionPane.QUESTION_MESSAGE);
						if (str==null) {
							return;
						}
						temps_cuisson = Integer.parseInt(str);
						incorrect=false;
					} catch (NumberFormatException ne) {
						JOptionPane.showMessageDialog(frame, "ERREUR : Merci de bien vouloir entrer un nombre valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
					}
				}
				
				
				incorrect=true;
				int nbr_ingredients=-1;
				while (incorrect) {
					try {
						String str = JOptionPane.showInputDialog(frame, "Combien y-a-t-il d'ingrédients ?", "Ajouter une recette",  JOptionPane.QUESTION_MESSAGE);
						if (str==null) {
							return;
						}
						nbr_ingredients = Integer.parseInt(str);
						incorrect=false;
					} catch (NumberFormatException ne) {
						JOptionPane.showMessageDialog(frame, "ERREUR : Merci de bien vouloir entrer un nombre valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
					}
				}
				
				ArrayList<Ingredient> liste_ingredients = new ArrayList<Ingredient>();
				
				for (int i=1; i<nbr_ingredients+1; i++) {
					String nom_ingre = JOptionPane.showInputDialog(frame, "Quel est le nom de l'ingrédient n°"+i, "Ajouter une recette",  JOptionPane.QUESTION_MESSAGE);
					if (nom_ingre==null) {
						return;
					}
					
					incorrect=true;
					int qte_ingre=-1;
					while (incorrect) {
						try {
							String str = JOptionPane.showInputDialog(frame, "Quantité ?", "Ajouter une recette",  JOptionPane.QUESTION_MESSAGE);
							if (str==null) {
								return;
							}
							qte_ingre = Integer.parseInt(str);
							incorrect=false;
						} catch (NumberFormatException ne) {
							JOptionPane.showMessageDialog(frame, "ERREUR : Merci de bien vouloir entrer un nombre valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
						}
					}
					
					String unite = JOptionPane.showInputDialog(frame, "Unité de mesure ?", "Ajouter une recette",  JOptionPane.QUESTION_MESSAGE);
					if (unite==null) {
						return;
					}
					
					Ingredient ing = new Ingredient(qte_ingre, nom_ingre, unite);
					liste_ingredients.add(ing);
				}
				
				incorrect=true;
				int nbr_etapes=-1;
				while (incorrect) {
					try {
						String str = JOptionPane.showInputDialog(frame, "Combien y-a-t-il d'étapes ?", "Ajouter une recette",  JOptionPane.QUESTION_MESSAGE);
						if (str==null) {
							return;
						}
						nbr_etapes = Integer.parseInt(str);
						incorrect=false;
					} catch (NumberFormatException ne) {
						JOptionPane.showMessageDialog(frame, "ERREUR : Merci de bien vouloir entrer un nombre valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
					}
				}
				
				
				ArrayList<Etape> liste_etapes = new ArrayList<Etape>();
				
				for (int i=1; i<nbr_etapes+1; i++) {
					String etp = JOptionPane.showInputDialog(frame, "Décrire l'étape n°"+i, "Ajouter une recette",  JOptionPane.QUESTION_MESSAGE);
					if (etp==null) {
						return;
					}
					Etape etape = new Etape(i,etp);
					liste_etapes.add(etape);
				}
				
				String[] oui_non = {"Oui", "Non"};
				String oui_ou_non = (String) JOptionPane.showInputDialog(frame, "Souhaitez-vous ajouter une illustration ?", "Ajouter une nouvelle recette", JOptionPane.QUESTION_MESSAGE, null, oui_non, oui_non[0]);
				if (oui_ou_non==null) {
					return;
				}
				
				String img;
				
				if (oui_ou_non.equals("Oui")) {
					img = JOptionPane.showInputDialog(frame, "Quel est le chemin de l'image ?", "Ajouter une recette",  JOptionPane.QUESTION_MESSAGE);
					if (img==null) {
						return;
					}
				} else {
					img = "ImageAppli/noimage.jpg";
				}
				
				Recette recette = new Recette(type1,type2,titre,temps_preparation,temps_cuisson,personne,liste_ingredients, liste_etapes, img);
				
				m.LRecette.add(recette);
				m.enregistrer();
				
				JOptionPane.showMessageDialog(frame, "Votre recette a été ajouté avec succés !", "Bien joué !", JOptionPane.INFORMATION_MESSAGE);
				Swing_ListeRecettes lr =  new Swing_ListeRecettes(modeClair,m,ctrl);
				lr.setVisible2(modeClair);
				frame.dispose();	
			}
		});
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("ImageAppli\\edit_normal.png"));
		lblNewLabel_1.setBounds(426, 10, 30, 30);
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel_1.setIcon(new ImageIcon("ImageAppli\\edit_souris.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblNewLabel_1.setIcon(new ImageIcon("ImageAppli\\edit_normal.png"));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Le bouton \"Edit \" vient d'être cliqué.");
				
				int index = list.getSelectedIndex();
				Recette recette = m.LRecette.get(index);
				
				String titre = JOptionPane.showInputDialog(frame, "Quel est le titre de la recette ?", recette.getNom());
				if (titre==null) {
					return;
				}
				
				String[] types_recettes = {"Entrée", "Plat", "Dessert"};
				String type1 = (String) JOptionPane.showInputDialog(frame, "De quel type de plat s'agit-il ?", "Ajouter une nouvelle recette", JOptionPane.INFORMATION_MESSAGE, null, types_recettes, recette.getType1());
				if (type1==null) {
					return;
				}
				

				String[] origines_recettes = {"Traditionnelle", "A compléter"};
				String type2 = (String) JOptionPane.showInputDialog(frame, "Quelle est l'origine du plat ?", "Ajouter une nouvelle recette", JOptionPane.INFORMATION_MESSAGE, null, origines_recettes, recette.getType2());
				if (type2==null) {
					return;
				}
				
				boolean incorrect=true;
				int personne=-1;
				while (incorrect) {
					try {
						String str = JOptionPane.showInputDialog(frame, "Pour combien de personnes ?", recette.getPersonnes());
						if (str==null) {
							return;
						}
						personne = Integer.parseInt(str);
						incorrect=false;
					} catch (NumberFormatException ne) {
						JOptionPane.showMessageDialog(frame, "ERREUR : Merci de bien vouloir entrer un nombre valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
					}
				}
				
				incorrect=true;
				int temps_preparation=-1;
				while (incorrect) {
					try {
						String str = JOptionPane.showInputDialog(frame, "Quel est le temps de préparation ?", recette.getTempsPreparation());
						if (str==null) {
							return;
						}
						temps_preparation = Integer.parseInt(str);
						incorrect=false;
					} catch (NumberFormatException ne) {
						JOptionPane.showMessageDialog(frame, "ERREUR : Merci de bien vouloir entrer un nombre valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
					}
				}
				
				
				incorrect=true;
				int temps_cuisson=-1;
				while (incorrect) {
					try {
						String str = JOptionPane.showInputDialog(frame, "Quel est le temps de cuisson ?", recette.getTempsCuisson());
						if (str==null) {
							return;
						}
						temps_cuisson = Integer.parseInt(str);
						incorrect=false;
					} catch (NumberFormatException ne) {
						JOptionPane.showMessageDialog(frame, "ERREUR : Merci de bien vouloir entrer un nombre valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
					}
				}
				
				boolean new_ingre = false;
				incorrect=true;
				int nbr_ingredients=-1;
				while (incorrect) {
					try {
						String str = JOptionPane.showInputDialog(frame, "Combien y-a-t-il d'ingrédients ?", recette.getIngredients().size());
						if (str==null) {
							return;
						}
						nbr_ingredients = Integer.parseInt(str);
						incorrect=false;
						if (nbr_ingredients!=recette.getIngredients().size()) {
							int popup1 = JOptionPane.showConfirmDialog(frame, "Le nombre d'ingrédients est différent de celui de la recette originale ! Si vous souhaitez continuer, il vous faudra saisir à nouveau tout les ingrédients !", "Attention !", JOptionPane.ERROR_MESSAGE);
							if (popup1==0) {
								new_ingre=true;
							} else {
								incorrect=true;
							}
						}
						
					} catch (NumberFormatException ne) {
						JOptionPane.showMessageDialog(frame, "ERREUR : Merci de bien vouloir entrer un nombre valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
					}
				}
				
				ArrayList<Ingredient> liste_ingredients = new ArrayList<Ingredient>();
				// SI LE NOMBRE D'INGREDIENTS N'EST LE MEME :
				if (new_ingre) {
					for (int i=1; i<nbr_ingredients+1; i++) {
						String nom_ingre = JOptionPane.showInputDialog(frame, "Quel est le nom de l'ingrédient n°"+i, "Modifier une recette",  JOptionPane.QUESTION_MESSAGE);
						if (nom_ingre==null) {
							return;
						}
						
						incorrect=true;
						int qte_ingre=-1;
						while (incorrect) {
							try {
								String str = JOptionPane.showInputDialog(frame, "Quantité ?", "Modifier une recette",  JOptionPane.QUESTION_MESSAGE);
								if (str==null) {
									return;
								}
								qte_ingre = Integer.parseInt(str);
								incorrect=false;
							} catch (NumberFormatException ne) {
								JOptionPane.showMessageDialog(frame, "ERREUR : Merci de bien vouloir entrer un nombre valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
							}
						}
						
						String unite = JOptionPane.showInputDialog(frame, "Unité de mesure ?", "Modifier une recette",  JOptionPane.QUESTION_MESSAGE);
						if (unite==null) {
							return;
						}
						
						Ingredient ing = new Ingredient(qte_ingre, nom_ingre, unite);
						liste_ingredients.add(ing);
					}
					
				} else { //SI C'EST LE MEME
					for (int i=1; i<nbr_ingredients+1; i++) {
						String nom_ingre = JOptionPane.showInputDialog(frame, "Quel est le nom de l'ingrédient n°"+i, recette.getIngredients().get(i-1).getIngredient());
						if (nom_ingre==null) {
							return;
						}
						
						incorrect=true;
						int qte_ingre=-1;
						while (incorrect) {
							try {
								String str = JOptionPane.showInputDialog(frame, "Quantité ?", recette.getIngredients().get(i-1).getQuantite());
								if (str==null) {
									return;
								}
								qte_ingre = Integer.parseInt(str);
								incorrect=false;
							} catch (NumberFormatException ne) {
								JOptionPane.showMessageDialog(frame, "ERREUR : Merci de bien vouloir entrer un nombre valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
							}
						}
						
						String unite = JOptionPane.showInputDialog(frame, "Unité de mesure ?", recette.getIngredients().get(i-1).getMesure());
						if (unite==null) {
							return;
						}
						
						Ingredient ing = new Ingredient(qte_ingre, nom_ingre, unite);
						liste_ingredients.add(ing);
					}
					
				}
				
				boolean new_etapes=false;
				incorrect=true;
				int nbr_etapes=-1;
				while (incorrect) {
					try {
						String str = JOptionPane.showInputDialog(frame, "Combien y-a-t-il d'étapes ?", recette.getEtapes().size());
						if (str==null) {
							return;
						}
						nbr_etapes = Integer.parseInt(str);
						incorrect=false;
						if (nbr_ingredients!=recette.getIngredients().size()) {
							int popup1 = JOptionPane.showConfirmDialog(frame, "Le nombre d'étape est différent de celui de la recette originale ! Si vous souhaitez continuer, il vous faudra saisir à nouveau toutes les étapes !", "Attention !", JOptionPane.ERROR_MESSAGE);
							if (popup1==0) {
								new_etapes=true;
							} else {
								incorrect=true;
							}
						}
					} catch (NumberFormatException ne) {
						JOptionPane.showMessageDialog(frame, "ERREUR : Merci de bien vouloir entrer un nombre valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
					}
				}
				
				ArrayList<Etape> liste_etapes = new ArrayList<Etape>();
				
				// SI LE NOMBRE D'ETAPES N'EST PAS LE MEME :
				if (new_etapes)  {
					for (int i=1; i<nbr_etapes+1; i++) {
						String etp = JOptionPane.showInputDialog(frame, "Décrire l'étape n°"+i, "Ajouter une recette",  JOptionPane.QUESTION_MESSAGE);
						if (etp==null) {
							return;
						}
						Etape etape = new Etape(i,etp);
						liste_etapes.add(etape);
					}
				} else {
					for (int i=1; i<nbr_etapes+1; i++) {
						String etp = JOptionPane.showInputDialog(frame, "Décrire l'étape n°"+i, recette.getEtapes().get(i-1).getInstrution());
						if (etp==null) {
							return;
						}
						Etape etape = new Etape(i,etp);
						liste_etapes.add(etape);
					}
				}
				
				
				String[] oui_non = {"Oui", "Non"};
				String oui_ou_non = (String) JOptionPane.showInputDialog(frame, "Souhaitez-vous ajouter/modifier une illustration ?", "Modifier une recette", JOptionPane.QUESTION_MESSAGE, null, oui_non, oui_non[0]);
				if (oui_ou_non==null) {
					return;
				}
				
				String img;
				
				if (oui_ou_non.equals("Oui")) {
					img = JOptionPane.showInputDialog(frame, "Quel est le chemin de l'image ?", recette.getImage());
					if (img==null) {
						return;
					}
				} else {
					img = "ImageAppli/noimage.jpg";
				}
				
				Recette n_recette = new Recette(type1,type2,titre,temps_preparation,temps_cuisson,personne,liste_ingredients, liste_etapes, img);
				
				m.LRecette.remove(index);
				m.LRecette.add(n_recette);
				m.enregistrer();
				
				JOptionPane.showMessageDialog(frame, "Votre recette a été modifiée avec succés !", "Bien joué !", JOptionPane.INFORMATION_MESSAGE);
				Swing_ListeRecettes lr =  new Swing_ListeRecettes(modeClair,m,ctrl);
				lr.setVisible2(modeClair);
				frame.dispose();
				
				
				
			}
		});
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon("ImageAppli\\trash_normal.png"));
		lblNewLabel_2.setBounds(506, 10, 30, 30);
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel_2.setIcon(new ImageIcon("ImageAppli\\trash_souris.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblNewLabel_2.setIcon(new ImageIcon("ImageAppli\\trash_normal.png"));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = list.getSelectedIndex();
				if (index==-1) {
					JOptionPane.showMessageDialog(frame, "Aucune recette n'est séléctionnée !", "Erreur",JOptionPane.ERROR_MESSAGE);
				} else {
					int popup = JOptionPane.showConfirmDialog(frame, "Etes-vous sur de vouloir supprimer cette recette ?", "Confirmation", JOptionPane.WARNING_MESSAGE);
					//System.out.println(popup);
					if (popup==0) {
						m.LRecette.remove(index);
						m.enregistrer();
						Swing_ListeRecettes lr =  new Swing_ListeRecettes(modeClair,m,ctrl);
						lr.setVisible2(modeClair);
						frame.dispose();	
					} else {
						System.out.println("Cancel");
					}
				}
				
			}
		});
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Trier par :");
		lblNewLabel_3.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(10, 14, 61, 20);
		frame.getContentPane().add(lblNewLabel_3);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Titre", "Type", "Origine"}));
		comboBox.setBounds(63, 15, 76, 21);
		frame.getContentPane().add(comboBox);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Pr\u00E9f\u00E9rences");
		menuBar.add(mnNewMenu);
		
		JMenu mnNewMenu_1 = new JMenu("Interface");
		mnNewMenu.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Mode clair");
		mntmNewMenuItem_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println("Le bouton \"Mode clair \" vient d'être cliqué.");
				setLightMode();
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Mode sombre");
		mntmNewMenuItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println("Le bouton \"Mode sombre \" vient d'être cliqué.");
				setDarkMode();
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem);
	}
	
	private void setDarkMode() {
		this.frame.setVisible(false);
		this.frame.dispose();
		this.modeClair=false;
		Swing_ListeRecettes win = new Swing_ListeRecettes(false,m,ctrl);
		win.setVisible2(false);
	}
	
	private void setLightMode() {
		this.frame.setVisible(false);
		this.frame.dispose();
		this.modeClair=true;
		Swing_ListeRecettes win = new Swing_ListeRecettes(true,m,ctrl);
		win.setVisible2(true);
	}
	
	public void setVisible2(boolean bool) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try { 
					Swing_ListeRecettes window = new Swing_ListeRecettes(bool,m,ctrl);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
