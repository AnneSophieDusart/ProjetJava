import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.imageio.ImageIO;
import javax.swing.AbstractListModel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JScrollBar;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JFormattedTextField;
import javax.swing.JToggleButton;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Swing_RechercheTitre implements Observer{
	
	Modele m;
	Controleur ctrl;
	int Recette = 0;
	static JList<String> list;
	JLabel lblNewLabel,lblNewLabel_1,lblNewLabel_4,lblNewLabel_5;
	JSpinner spinner;
	JTextField texteField;
	String[] values,monTableau;

	private JFrame frmRecettesDeCuisine;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Modele m = new Modele();
		Controleur ctrl = new Controleur(m);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Swing_RechercheTitre window = new Swing_RechercheTitre(m, ctrl);
					window.frmRecettesDeCuisine.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Swing_RechercheTitre(Modele modl, Controleur cont) {
		
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
		this.m= modl;
		this.ctrl=cont;
		initialize();
		m.addObserver(this);
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frmRecettesDeCuisine = new JFrame();
		frmRecettesDeCuisine.setResizable(false);
		//frmRecettesDeCuisine.setBackground(Color.WHITE);
		frmRecettesDeCuisine.setTitle("Délice !");
		frmRecettesDeCuisine.setBounds(100, 100, 560, 450);
		frmRecettesDeCuisine.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmRecettesDeCuisine.setJMenuBar(menuBar);
		
		JMenu mnNewMenu_2 = new JMenu("Pr\u00E9f\u00E9rences");
		menuBar.add(mnNewMenu_2);
		
		JMenu mnNewMenu_3 = new JMenu("Interface");
		mnNewMenu_2.add(mnNewMenu_3);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Mode clair");
		mntmNewMenuItem_7.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println("Le bouton \"Mode clair \" vient d'être cliqué.");
				setLightMode();
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_7);
		
		JMenuItem mntmNewMenuItem_8 = new JMenuItem("Mode sombre");
		mntmNewMenuItem_8.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println("Le bouton \"Mode sombre \" vient d'être cliqué.");
				setDarkMode();
				
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_8);
		frmRecettesDeCuisine.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(292, 10, 254, 380);
		frmRecettesDeCuisine.getContentPane().add(panel);
		panel.setLayout(null);
		
		this.lblNewLabel = new JLabel("Image illustration");
		lblNewLabel.setIcon(new ImageIcon(m.LRecette.get(Recette).Image));
		lblNewLabel.setBackground(Color.LIGHT_GRAY);
		lblNewLabel.setBounds(10, 10, 234, 137);
		panel.add(lblNewLabel);
		
		this.lblNewLabel_1 = new JLabel(m.LRecette.get(Recette).Nom);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 16));
		lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1.setBounds(10, 152, 234, 30);
		panel.add(lblNewLabel_1);

		
		/*int duree = m.LRecette.get(Recette).TempsCuisson +  m.LRecette.get(Recette).TempsPreparation;
		String temps ="";
		if (duree > 60 ) {
			temps+= duree/60 + " h " + duree%60 + " min";
		}
		else if (duree == 60) {
			temps+= "1 h";
		}
		else {
			temps+= duree + " min";
		}*/
		
		String cuisson ="";
		if (m.LRecette.get(Recette).TempsCuisson > 60 ) {
			cuisson+= m.LRecette.get(Recette).TempsCuisson/60 + " h " + m.LRecette.get(Recette).TempsCuisson%60 + " min";
		}
		else if (m.LRecette.get(Recette).TempsCuisson == 60) {
			cuisson+= "1 h";
		}
		else {
			cuisson+= m.LRecette.get(Recette).TempsCuisson + " min";
		}
		this.lblNewLabel_4 = new JLabel(cuisson);
		lblNewLabel_4.setForeground(new Color(255, 127, 80));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		lblNewLabel_4.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_4.setBounds(144, 214, 100, 20);
		panel.add(lblNewLabel_4);
		

		String prepa ="";
		if (m.LRecette.get(Recette).TempsPreparation > 60 ) {
			prepa+= m.LRecette.get(Recette).TempsPreparation/60 + " h " + m.LRecette.get(Recette).TempsPreparation%60 + " min";
		}
		else if (m.LRecette.get(Recette).TempsPreparation == 60) {
			prepa+= "1 h";
		}
		else {
			prepa+= m.LRecette.get(Recette).TempsPreparation + " min";
		}		
		
		this.lblNewLabel_5 = new JLabel(prepa);
		lblNewLabel_5.setForeground(new Color(255, 127, 80));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		lblNewLabel_5.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_5.setBounds(10, 214, 100, 20);
		panel.add(lblNewLabel_5);
		
		
		this.spinner = new JSpinner(new SpinnerNumberModel(m.LRecette.get(Recette).Personnes,0,100,1));   
		spinner.setForeground(new Color(255, 127, 80));
		spinner.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		spinner.setBounds(37,265, 50, 20);
		spinner.addChangeListener(ctrl);
		panel.add(spinner);
		
		/*String listeIngre = "";
		for ( int i=0; i<m.LRecette.get(Recette).Ingredients.size(); i++) {
			listeIngre+=m.LRecette.get(Recette).Ingredients.get(i)+ "   ";
		}*/
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 42, 272, 340);
		frmRecettesDeCuisine.getContentPane().add(scrollPane);
		
		this.list = new JList();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setFont(new Font("Segoe UI Light", Font.PLAIN, 13));
		this.values = new String[m.LRecette.size()];
		for (int i=0; i<this.m.LRecette.size(); i++) {
			values[i]=m.LRecette.get(i).Nom;
		}
		this.monTableau = values;
		list.setModel(new AbstractListModel() {
			//String[] values = new String[] {"Quiche en ramequin", "Salade grecque", "Oeufs au mimosa", "Rouleau de saumon", "Mousse de courgettes", "Saut\u00E9 de boeuf", "Poulet coco tha\u00EF", "Maff\u00E9 de boeuf ", "Thon coco au riz", "Tortilla", "Risotto", "Soupe au potiron", "Hamburger classique", "Fajitas", "Oeufs cocottes", "Oeufs \u00E0 l'italienne", "Cr\u00E8pes", "Tiramisu traditionnel", "Panacotta", "Pancakes vegan \u00E0 la banane", "Mousse au chocolat"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setSelectedIndex(0);
		list.addListSelectionListener(ctrl);
		scrollPane.setViewportView(list);
		
		this.texteField = new JTextField();
		texteField.setText("Recherche rapide");
		texteField.setBounds(10, 10, 272, 19);
		texteField.setForeground(Color.gray);
		texteField.addMouseListener(new MouseAdapter() {           
		    @Override
		    public void mouseReleased(MouseEvent e) {}         
		    @Override
		    public void mousePressed(MouseEvent e) {}          
		    @Override
		    public void mouseExited(MouseEvent e) {}           
		    @Override
		    public void mouseEntered(MouseEvent e) {}          
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        JTextField texteField = ((JTextField)e.getSource());
		        texteField.setText("");
		        texteField.setForeground(Color.black);
		        texteField.removeMouseListener(this);
		    }
		});
		texteField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
 
            @Override
            public void keyPressed(KeyEvent e) {}
 
           @Override
            public void keyReleased(KeyEvent e) {
                m.listeRechercheNom(texteField.getText(),list,values);
            }
        });
		
		frmRecettesDeCuisine.getContentPane().add(texteField);
		
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon("ImageAppli\\start_normal.png"));
		lblNewLabel_2.setBounds(113, 328, 30, 30);
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel_2.setIcon(new ImageIcon("ImageAppli\\start_souris.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblNewLabel_2.setIcon(new ImageIcon("ImageAppli\\start_normal.png"));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				//int index = list.getSelectedIndex();
				Recette r = m.LRecette.get(Recette);
				
				Swing_Etape recette =  new Swing_Etape(m, r, (Integer) spinner.getValue());
				recette.setVisible(r, (Integer) spinner.getValue());
			}
		});
		panel.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Voir liste");
		btnNewButton.setForeground(new Color(255, 127, 80));
		btnNewButton.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 12));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//int index = list.getSelectedIndex();
				Recette r = m.LRecette.get(Recette);
				String liste = r.afficherIngredients((Integer) spinner.getValue());
				JOptionPane.showMessageDialog(frmRecettesDeCuisine, liste, "Liste ingrédients", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnNewButton.setBounds(153, 266, 79, 21);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel_7_1 = new JLabel("Pr\u00E9paration");
		lblNewLabel_7_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7_1.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
		lblNewLabel_7_1.setBounds(10, 198, 100, 13);
		panel.add(lblNewLabel_7_1);
		
		JLabel lblNewLabel_7_1_1 = new JLabel("Cuisson");
		lblNewLabel_7_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7_1_1.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
		lblNewLabel_7_1_1.setBounds(144, 199, 100, 13);
		panel.add(lblNewLabel_7_1_1);
		
		JLabel lblNewLabel_7_1_2 = new JLabel("Nombre de personne");
		lblNewLabel_7_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7_1_2.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
		lblNewLabel_7_1_2.setBounds(0, 247, 121, 13);
		panel.add(lblNewLabel_7_1_2);
		
		JLabel lblNewLabel_7_1_2_1 = new JLabel("Ingr\u00E9dients");
		lblNewLabel_7_1_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7_1_2_1.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
		lblNewLabel_7_1_2_1.setBounds(130, 247, 124, 13);
		panel.add(lblNewLabel_7_1_2_1);
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	
	private void setDarkMode() {
		this.frmRecettesDeCuisine.setVisible(false);
		this.frmRecettesDeCuisine.dispose();
		m.modeClair=false;
		Swing_RechercheTitre win = new Swing_RechercheTitre(m,ctrl);
		win.setVisible2();
	}
	
	private void setLightMode() {
		this.frmRecettesDeCuisine.setVisible(false);
		this.frmRecettesDeCuisine.dispose();
		m.modeClair=true;
		Swing_RechercheTitre win = new Swing_RechercheTitre(m,ctrl);
		win.setVisible2();
	}
	
	public void setVisible2() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try { 
					Swing_RechercheTitre window = new Swing_RechercheTitre(m,ctrl);
					window.frmRecettesDeCuisine.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public void update(Observable modl, Object arg) {
		if (arg instanceof Integer) {
			int Recettes=(Integer) arg;
			if (Recettes == -1 ) {
				
			}
			else {
			for (int i=0; i<m.LRecette.size();i++) {
				if (m.LRecette.get(i).Nom==this.monTableau[Recettes]) {
					//System.out.println(this.monTableau[Recettes]);
					this.Recette=i;
				}
			}
			
			this.lblNewLabel.setIcon(new ImageIcon(m.LRecette.get(Recette).Image));;
			this.lblNewLabel_1.setText(m.LRecette.get(Recette).Nom);
			/*int duree = m.LRecette.get(Recette).TempsCuisson +  m.LRecette.get(Recette).TempsPreparation;
			String temps ="";
			if (duree > 60 ) {
				temps+= duree/60 + " h " + duree%60 + " min";
			}
			else if (duree == 60) {
				temps+= "1 h";
			}
			else {
				temps+= duree + " min";
			}
			this.lblNewLabel_3.setText(temps);*/

			String cuisson ="";
			if (m.LRecette.get(Recette).TempsCuisson > 60 ) {
				cuisson+= m.LRecette.get(Recette).TempsCuisson/60 + " h " + m.LRecette.get(Recette).TempsCuisson%60 + " min";
			}
			else if (m.LRecette.get(Recette).TempsCuisson == 60) {
				cuisson+= "1 h";
			}
			else {
				cuisson+= m.LRecette.get(Recette).TempsCuisson + " min";
			}
			this.lblNewLabel_4.setText(cuisson);
		
			String prepa ="";
			if (m.LRecette.get(Recette).TempsPreparation > 60 ) {
				prepa+= m.LRecette.get(Recette).TempsPreparation/60 + " h " + m.LRecette.get(Recette).TempsPreparation%60 + " min";
			}
			else if (m.LRecette.get(Recette).TempsPreparation == 60) {
				prepa+= "1 h";
			}
			else {
				prepa+= m.LRecette.get(Recette).TempsPreparation + " min";
			}		
			this.lblNewLabel_5.setText(prepa);
			
			//String listeIngre = "";
			//for ( int i=0; i<m.LRecette.get(Recette).Ingredients.size(); i++) {
			//	listeIngre+=m.LRecette.get(Recette).Ingredients.get(i)+ "   ";
			//}

			this.spinner.setValue(m.LRecette.get(Recette).Personnes) ;}
		}
		else if (arg instanceof String[]){
			//System.out.println(values[0]);
			this.monTableau = (String[])arg;
			//System.out.println(values[0]);
			Swing_RechercheTitre.list.setListData (monTableau);
			//ArrayList<Integer> nouvelleQuantite= (ArrayList<Integer>) arg;
			//String listeIngre = "";
			//ArrayList<Ingredient> ingre = m.LRecette.get(Recette).Ingredients;
			//for ( int i=0; i<ingre.size(); i++) {
			//	listeIngre+=ingre.get(i).ingredient+ " : " + String.valueOf(nouvelleQuantite.get(i)) + " " + ingre.get(i).mesure+ "   ";
			//}
		}
	}
}
