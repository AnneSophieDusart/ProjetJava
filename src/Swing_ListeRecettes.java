import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

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
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;

public class Swing_ListeRecettes implements Observer{
	
	Modele m;
	Controleur ctrl;
	JList list;
	JComboBox comboBox2;
	String[] values;
	String[] monTab;

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
					Swing_ListeRecettes window = new Swing_ListeRecettes(m,ctrl);
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
	public Swing_ListeRecettes(Modele modl, Controleur cont) {
		this.m= modl;
		this.ctrl=cont;
		if (modl.modeClair) {
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
		initialize();
		m.addObserver(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 560, 427);
		frame.setTitle("Délice !");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 50, 526, 330);
		frame.getContentPane().add(scrollPane);
		
		this.list = new JList();
		if (m.modeClair) {
			list.setBackground(Color.WHITE);
		} else {
			list.setBackground(Color.BLACK);
		}
		list.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		this.values = new String[m.LRecette.size()];
		for (int i=0; i<this.m.LRecette.size(); i++) {
			values[i]=m.LRecette.get(i).Nom;
		}
		this.monTab = values;
		list.setModel(new AbstractListModel() {
			//String[] values = new String[] {"Recette 1", "Recette 2", "Recette 3", "Recette 1", "Recette 2", "Recette 3", "Recette 1", "Recette 2", "Recette 3", "Recette 1", "Recette 2", "Recette 3", "Recette 1", "Recette 2", "Recette 3", "Recette 1", "Recette 2", "Recette 3"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
					int index = list.getSelectedIndex();
					int numRecette = 0;
					for (int i=0; i<m.LRecette.size();i++) {
						if (m.LRecette.get(i).Nom==monTab[index]) {
							numRecette=i;
						}}
					Recette r = m.LRecette.get(numRecette);
					
					Swing_Details win = new Swing_Details(r, m, ctrl);
					win.setVisible(r);
				}
			}
		});
		scrollPane.setViewportView(list);
		
		JLabel lblNewLabel = new JLabel("New label");
		if (m.modeClair) {
			lblNewLabel.setIcon(new ImageIcon("ImageAppli\\add_black.png"));
		} else {
			lblNewLabel.setIcon(new ImageIcon("ImageAppli\\add_white.png"));
		}
		lblNewLabel.setBounds(466, 10, 30, 30);
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel.setIcon(new ImageIcon("ImageAppli\\add_souris.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if (m.modeClair) {
					lblNewLabel.setIcon(new ImageIcon("ImageAppli\\add_black.png"));
				} else {
					lblNewLabel.setIcon(new ImageIcon("ImageAppli\\add_white.png"));
				}
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
				
				String[] origines_recettes = {"France", "Grèce","Italie","Vietnam","Japon","Etats-Unis","Mexique","Allemagne","Espagne","Autre"};
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

				m.dicoType.get(recette.Type1).add(recette.Nom);
				m.LRecette.add(recette);
				m.enregistrer();
				
				JOptionPane.showMessageDialog(frame, "Votre recette a été ajouté avec succés !", "Bien joué !", JOptionPane.INFORMATION_MESSAGE);
				Swing_ListeRecettes lr =  new Swing_ListeRecettes(m,ctrl);
				lr.setVisible2();
				frame.dispose();	
			}
		});
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		if (m.modeClair) {
			lblNewLabel_1.setIcon(new ImageIcon("ImageAppli\\edit_black.png"));
		} else {
			lblNewLabel_1.setIcon(new ImageIcon("ImageAppli\\edit_white.png"));
		}
		lblNewLabel_1.setBounds(426, 10, 30, 30);
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel_1.setIcon(new ImageIcon("ImageAppli\\edit_souris.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if (m.modeClair) {
					lblNewLabel_1.setIcon(new ImageIcon("ImageAppli\\edit_black.png"));
				} else {
					lblNewLabel_1.setIcon(new ImageIcon("ImageAppli\\edit_white.png"));
				}
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int index = list.getSelectedIndex();
				
				if (index==-1) {
					JOptionPane.showMessageDialog(frame, "Aucune recette n'est séléctionnée !", "Erreur",JOptionPane.ERROR_MESSAGE);
				} else {
					int numRecette = 0;
					for (int i=0; i<m.LRecette.size();i++) {
						if (m.LRecette.get(i).Nom==monTab[index]) {
							
							numRecette=i;
						}}
					Recette recette = m.LRecette.get(numRecette);
					String titre = JOptionPane.showInputDialog(frame, "Quel est le titre de la recette ?", recette.getNom());
					if (titre==null) {
						return;
					}
					
					String[] types_recettes = {"Entrée", "Plat", "Dessert"};
					String type1 = (String) JOptionPane.showInputDialog(frame, "De quel type de plat s'agit-il ?", "Ajouter une nouvelle recette", JOptionPane.INFORMATION_MESSAGE, null, types_recettes, recette.getType1());
					if (type1==null) {
						return;
					}
					

					String[] origines_recettes = {"France", "Grèce","Italie","Vietnam","Japon","Etats-Unis","Mexique","Allemagne","Espagne","Autre"};
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
					
					m.dicoType.get(m.LRecette.get(index).Type1).remove(m.LRecette.get(index).Nom);
					m.LRecette.remove(index);
					m.dicoType.get(n_recette.Type1).add(n_recette.Nom);
					m.LRecette.add(n_recette);
					m.enregistrer();
					
					JOptionPane.showMessageDialog(frame, "Votre recette a été modifiée avec succés !", "Bien joué !", JOptionPane.INFORMATION_MESSAGE);
					Swing_ListeRecettes lr =  new Swing_ListeRecettes(m,ctrl);
					lr.setVisible2();
					frame.dispose();
				}
				
				
				
				
				
			}
		});
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("New label"); 
		if (m.modeClair) {
			lblNewLabel_2.setIcon(new ImageIcon("ImageAppli\\trash_black.png"));
		} else {
			lblNewLabel_2.setIcon(new ImageIcon("ImageAppli\\trash_white.png"));
		}
		lblNewLabel_2.setBounds(506, 10, 30, 30);
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel_2.setIcon(new ImageIcon("ImageAppli\\trash_souris.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if (m.modeClair) {
					lblNewLabel_2.setIcon(new ImageIcon("ImageAppli\\trash_black.png"));
				} else {
					lblNewLabel_2.setIcon(new ImageIcon("ImageAppli\\trash_white.png"));
				}
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = list.getSelectedIndex();
				if (index==-1) {
					JOptionPane.showMessageDialog(frame, "Aucune recette n'est séléctionnée !", "Erreur",JOptionPane.ERROR_MESSAGE);
				} else {
					int numRecette = 0;
					for (int i=0; i<m.LRecette.size();i++) {
						if (m.LRecette.get(i).Nom==monTab[index]) {
							numRecette=i;
						}}
					int popup = JOptionPane.showConfirmDialog(frame, "Etes-vous sur de vouloir supprimer la recette : "+m.LRecette.get(numRecette).Nom+ "  ?", "Confirmation", JOptionPane.WARNING_MESSAGE);
					//System.out.println(popup);
					if (popup==0) {
						m.dicoType.get(m.LRecette.get(numRecette).Type1).remove(m.LRecette.get(numRecette).Nom);
						m.LRecette.remove(numRecette);
						m.enregistrer();
						Swing_ListeRecettes lr =  new Swing_ListeRecettes(m,ctrl);
						lr.setVisible2();
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
		if (m.modeClair) {
			comboBox.setBackground(Color.WHITE);
		} else {
			comboBox.setBackground(Color.BLACK);
		}
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Type", "Origine"}));
		//comboBox.setModel(new DefaultComboBoxModel(new String[] {" ","Entrée", "Plat", "Dessert"}));
		comboBox.setBounds(63, 15, 76, 21);
		frame.getContentPane().add(comboBox);
		comboBox.addItemListener(ctrl);
		
		this.comboBox2 = new JComboBox();
		if (m.modeClair) {
			comboBox2.setBackground(Color.WHITE);
		} else {
			comboBox2.setBackground(Color.BLACK);
		}
		comboBox2.setModel(new DefaultComboBoxModel(new String[] {" ","Entrée", "Plat", "Dessert"}));
		comboBox2.setBounds(164, 15, 76, 21);
		frame.getContentPane().add(comboBox2);
		
		JLabel lblNewLabel_3_1 = new JLabel("-->");
		lblNewLabel_3_1.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
		lblNewLabel_3_1.setBounds(143, 14, 61, 20);
		frame.getContentPane().add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("New label");
		lblNewLabel_1_1.setBounds(386, 10, 30, 30);
		frame.getContentPane().add(lblNewLabel_1_1);
		if (m.modeClair) {
			lblNewLabel_1_1.setIcon(new ImageIcon("ImageAppli\\eye_black.png"));
		} else {
			lblNewLabel_1_1.setIcon(new ImageIcon("ImageAppli\\eye_white.png"));
		}
		lblNewLabel_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel_1_1.setIcon(new ImageIcon("ImageAppli\\eye_souris.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if (m.modeClair) {
					lblNewLabel_1_1.setIcon(new ImageIcon("ImageAppli\\eye_black.png"));
				} else {
					lblNewLabel_1_1.setIcon(new ImageIcon("ImageAppli\\eye_white.png"));
				}
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = list.getSelectedIndex();
				
				if (index==-1) {
					JOptionPane.showMessageDialog(frame, "Aucune recette n'est séléctionnée !", "Erreur",JOptionPane.ERROR_MESSAGE);
				} else {
					int numRecette = 0;
					for (int i=0; i<m.LRecette.size();i++) {
						if (m.LRecette.get(i).Nom==monTab[index]) {
							numRecette=i;
						}}
					Recette r = m.LRecette.get(numRecette);
					
					Swing_Details win = new Swing_Details(r, m, ctrl);
					win.setVisible(r);
				}
				
			}
		});
		
		
		comboBox2.addItemListener(ctrl);
		
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
		m.modeClair=false;
		m.enregistrer();
		Swing_ListeRecettes win = new Swing_ListeRecettes(m,ctrl);
		win.setVisible2();
	}
	
	private void setLightMode() {
		this.frame.setVisible(false);
		this.frame.dispose();
		m.modeClair=true;
		m.enregistrer();
		Swing_ListeRecettes win = new Swing_ListeRecettes(m,ctrl);
		win.setVisible2();
	}
	
	public void setVisible2() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try { 
					Swing_ListeRecettes window = new Swing_ListeRecettes(m,ctrl);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public void update(Observable m, Object arg) {
		if (arg instanceof String[]) {
			String[] newTableau = (String[]) arg;
			this.comboBox2.removeAllItems();
			this.comboBox2.setModel(new DefaultComboBoxModel(newTableau));
			this.list.setListData (this.values);
		}
		else if (arg instanceof ArrayList<?>){
			//System.out.println(arg);
			ArrayList<String> liste = (ArrayList<String>) arg;
		    this.monTab = liste.toArray(new String[liste.size()]);
			this.list.setListData (monTab);
		}
	}
}
