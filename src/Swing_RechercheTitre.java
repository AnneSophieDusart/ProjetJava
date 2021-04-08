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
import javax.swing.JScrollBar;
import javax.swing.ListSelectionModel;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JFormattedTextField;
import javax.swing.JToggleButton;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class Swing_RechercheTitre implements Observer{
	
	boolean modeClair = true;
	Modele m;
	Controleur ctrl;
	String image = "ImageRecette\\crepe.jpg";
	JLabel lblNewLabel;

	private JFrame frmRecettesDeCuisine;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Swing_RechercheTitre window = new Swing_RechercheTitre(true);
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
	public Swing_RechercheTitre(boolean modeClair) {
		this.modeClair = modeClair;
		
		if (this.modeClair) {
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
		boolean modeClair = this.modeClair;
		
		this.m=new Modele();
		this.ctrl = new Controleur(m);
		
		frmRecettesDeCuisine = new JFrame();
		frmRecettesDeCuisine.setResizable(false);
		frmRecettesDeCuisine.setBackground(Color.WHITE);
		frmRecettesDeCuisine.setTitle("Recettes de cuisine");
		frmRecettesDeCuisine.setBounds(100, 100, 560, 427);
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
		panel.setBounds(292, 10, 244, 348);
		frmRecettesDeCuisine.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Image illustration");
		lblNewLabel.setIcon(new ImageIcon(image));
		lblNewLabel.setBackground(Color.LIGHT_GRAY);
		lblNewLabel.setBounds(10, 10, 224, 130);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("TITRE DE LA RECETTE");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 12));
		lblNewLabel_1.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_1.setBounds(10, 146, 224, 13);
		panel.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Au travail !");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Le bouton \"Au travail : \" vient d'être cliqué.");
				Swing_Etape recette =  new Swing_Etape(modeClair);
				recette.setVisible(modeClair);
			}
		});
		btnNewButton.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnNewButton.setBounds(83, 317, 85, 21);
		panel.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 42, 272, 316);
		frmRecettesDeCuisine.getContentPane().add(scrollPane);
		
		JList list = new JList();
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
		this.modeClair=false;
		Swing_RechercheTitre win = new Swing_RechercheTitre(false);
		win.setVisible2(false);
	}
	
	private void setLightMode() {
		this.frmRecettesDeCuisine.setVisible(false);
		this.frmRecettesDeCuisine.dispose();
		this.modeClair=true;
		Swing_RechercheTitre win = new Swing_RechercheTitre(true);
		win.setVisible2(true);
	}
	
	public void setVisible2(boolean bool) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try { 
					Swing_RechercheTitre window = new Swing_RechercheTitre(bool);
					window.frmRecettesDeCuisine.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public void update(Observable modl, Object arg) {

	}
}
