import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JList;
import javax.swing.JTextArea;
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
	JLabel lblNewLabel,lblNewLabel_1,lblNewLabel_3,lblNewLabel_4,lblNewLabel_5;
	JTextArea labelArea;
	JSpinner spinner;

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
		panel.setBounds(292, 10, 284, 388);
		frmRecettesDeCuisine.getContentPane().add(panel);
		panel.setLayout(null);
		
		this.lblNewLabel = new JLabel("Image illustration");
		lblNewLabel.setIcon(new ImageIcon(m.LRecette.get(Recette).Image));
		lblNewLabel.setBackground(Color.LIGHT_GRAY);
		lblNewLabel.setBounds(10, 10, 224, 130);
		panel.add(lblNewLabel);
		
		this.lblNewLabel_1 = new JLabel(m.LRecette.get(Recette).Nom);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 12));
		lblNewLabel_1.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_1.setBounds(10, 146, 224, 13);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon("ImageAppli\\start_normal.png"));
		lblNewLabel_2.setBounds(113, 338, 30, 30);
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
				System.out.println("Le bouton \"Au travail : \" vient d'être cliqué.");
				Swing_Etape recette =  new Swing_Etape(m);
				recette.setVisible();
			}
		});
		panel.add(lblNewLabel_2);
		
		int duree = m.LRecette.get(Recette).TempsCuisson +  m.LRecette.get(Recette).TempsPreparation;
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
		this.lblNewLabel_3 = new JLabel("Durée de la recette : " + temps);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Segoe UI Semibold", Font.BOLD, 10));
		lblNewLabel_3.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_3.setBounds(10, 175, 224, 13);
		panel.add(lblNewLabel_3);
		
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
		this.lblNewLabel_4 = new JLabel("Cuisson : " + cuisson);
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("Segoe UI Semibold", Font.BOLD, 10));
		lblNewLabel_4.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_4.setBounds(10, 190, 100, 13);
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
		
		this.lblNewLabel_5 = new JLabel("Préparation : " + prepa);
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setFont(new Font("Segoe UI Semibold", Font.BOLD, 10));
		lblNewLabel_5.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_5.setBounds(10, 190, 350, 13);
		panel.add(lblNewLabel_5);
		
		
		JLabel lblNewLabel_6 = new JLabel("Nombre de personne : ");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setFont(new Font("Segoe UI Semibold", Font.BOLD, 10));
		lblNewLabel_6.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_6.setBounds(10, 223, 150, 13);
		panel.add(lblNewLabel_6);
		
		
		this.spinner = new JSpinner(new SpinnerNumberModel(m.LRecette.get(Recette).Personnes,0,100,1));   
		spinner.setBounds(150,220, 50, 20);
		spinner.addChangeListener(ctrl);
		panel.add(spinner);
		
		String listeIngre = "";
		for ( int i=0; i<m.LRecette.get(Recette).Ingredients.size(); i++) {
			listeIngre+=m.LRecette.get(Recette).Ingredients.get(i)+ "   ";
		}

		this.labelArea = new JTextArea("Ingrédients : \n" + listeIngre);
		labelArea.setEditable(false);
		labelArea.setOpaque(false);
		labelArea.setBounds(10, 245, 220, 170);
		labelArea.setFont(new Font("Segoe UI Semibold", Font.BOLD, 10));
		labelArea.setLineWrap(true);
		labelArea.setWrapStyleWord(true);
		panel.add(labelArea);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 42, 272, 340);
		frmRecettesDeCuisine.getContentPane().add(scrollPane);
		
		this.list = new JList();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setFont(new Font("Segoe UI Light", Font.PLAIN, 13));
		String[] values = new String[m.LRecette.size()];
		for (int i=0; i<this.m.LRecette.size(); i++) {
			values[i]=m.LRecette.get(i).Nom;
		}
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
		
		JFormattedTextField frmtdtxtfldRechercheRapide = new JFormattedTextField();
		frmtdtxtfldRechercheRapide.setText("Recherche rapide");
		frmtdtxtfldRechercheRapide.setBounds(10, 10, 272, 19);
		frmRecettesDeCuisine.getContentPane().add(frmtdtxtfldRechercheRapide);
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
			this.Recette=(Integer) arg;
			this.lblNewLabel.setIcon(new ImageIcon(m.LRecette.get(Recette).Image));;
			this.lblNewLabel_1.setText(m.LRecette.get(Recette).Nom);
			int duree = m.LRecette.get(Recette).TempsCuisson +  m.LRecette.get(Recette).TempsPreparation;
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
			this.lblNewLabel_3.setText("Durée de la recette : " + temps);

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
			this.lblNewLabel_4.setText("Cuisson : " + cuisson);
		
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
			this.lblNewLabel_5.setText("Préparation : " + prepa);
			
			String listeIngre = "";
			for ( int i=0; i<m.LRecette.get(Recette).Ingredients.size(); i++) {
				listeIngre+=m.LRecette.get(Recette).Ingredients.get(i)+ "   ";
			}

			this.labelArea.setText("Ingrédients : \n" + listeIngre);
			this.spinner.setValue(m.LRecette.get(Recette).Personnes) ;
		}
		else {
			ArrayList<Integer> nouvelleQuantite= (ArrayList<Integer>) arg;
			String listeIngre = "";
			ArrayList<Ingredient> ingre = m.LRecette.get(Recette).Ingredients;
			for ( int i=0; i<ingre.size(); i++) {
				listeIngre+=ingre.get(i).ingredient+ " : " + String.valueOf(nouvelleQuantite.get(i)) + " " + ingre.get(i).mesure+ "   ";
			}
			this.labelArea.setText("Ingrédients : \n" + listeIngre);
		}
	}
}
