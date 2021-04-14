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
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

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
				System.out.println("Le bouton \"+ \" vient d'être cliqué.");
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
				System.out.println("Le bouton \"Trash \" vient d'être cliqué.");
			}
		});
		frame.getContentPane().add(lblNewLabel_2);
		
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
