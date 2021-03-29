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

import com.formdev.flatlaf.FlatLightLaf;

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

public class Test2 {

	private JFrame frmRecettesDeCuisine;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test2 window = new Test2();
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
	public Test2() {
		try {
		    UIManager.setLookAndFeel( new FlatLightLaf() );
		} catch( Exception ex ) {
		    System.err.println( "Failed to initialize LaF" );
		}
		
		initialize();
		
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRecettesDeCuisine = new JFrame();
		frmRecettesDeCuisine.setResizable(false);
		frmRecettesDeCuisine.setBackground(Color.WHITE);
		frmRecettesDeCuisine.setTitle("Recettes de cuisine");
		frmRecettesDeCuisine.setBounds(100, 100, 560, 427);
		frmRecettesDeCuisine.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmRecettesDeCuisine.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Rechercher");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Par nom");
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Par ingr\u00E9dient");
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Par type");
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JMenu mnNewMenu_1 = new JMenu("Recettes");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Supprimer une recette");
		mnNewMenu_1.add(mntmNewMenuItem_4);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Ajouter une recette");
		mnNewMenu_1.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Modifier une recette");
		mnNewMenu_1.add(mntmNewMenuItem_5);
		
		JMenu mnNewMenu_2 = new JMenu("Pr\u00E9f\u00E9rences");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Blabla");
		mnNewMenu_2.add(mntmNewMenuItem_6);
		frmRecettesDeCuisine.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(292, 10, 244, 348);
		frmRecettesDeCuisine.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Image illustration");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\bilbo\\OneDrive\\Documents\\Eclipse\\ProjetJava\\ImageRecette\\crepe.jpg"));
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
				Swing_Etape recette =  new Swing_Etape();
				recette.setVisible();
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
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"Quiche en ramequin", "Salade grecque", "Oeufs au mimosa", "Rouleau de saumon", "Mousse de courgettes", "Saut\u00E9 de boeuf", "Poulet coco tha\u00EF", "Maff\u00E9 de boeuf ", "Thon coco au riz", "Tortilla", "Risotto", "Soupe au potiron", "Hamburger classique", "Fajitas", "Oeufs cocottes", "Oeufs \u00E0 l'italienne", "Cr\u00E8pes", "Tiramisu traditionnel", "Panacotta", "Pancakes vegan \u00E0 la banane", "Mousse au chocolat"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setSelectedIndex(0);
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
}
