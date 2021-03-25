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

public class Test2 {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test2 window = new Test2();
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
		frame = new JFrame();
		frame.setBounds(100, 100, 560, 427);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
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
		frame.getContentPane().setLayout(null);
		
		JList list = new JList();
		list.setBounds(10, 47, 272, 311);
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
		frame.getContentPane().add(list);
		
		JTextArea txtrRechercheRapide = new JTextArea();
		txtrRechercheRapide.setBounds(10, 10, 272, 22);
		txtrRechercheRapide.setText("Recherche rapide");
		frame.getContentPane().add(txtrRechercheRapide);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(265, 47, 17, 311);
		frame.getContentPane().add(scrollBar);
		
		JPanel panel = new JPanel();
		panel.setBounds(292, 10, 244, 348);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Image illustration");
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
		btnNewButton.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnNewButton.setBounds(83, 317, 85, 21);
		panel.add(btnNewButton);
	}
}
