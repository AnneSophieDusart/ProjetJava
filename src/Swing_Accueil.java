import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Swing_Accueil implements WindowListener {
	
	Modele m;
	Controleur ctrl;

	private JFrame frmRecettesDeCuisine;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Swing_Accueil window = new Swing_Accueil();
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
	public Swing_Accueil() {
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
		this.m = new Modele();
		this.ctrl = new Controleur(m);
		
		frmRecettesDeCuisine = new JFrame();
		frmRecettesDeCuisine.setResizable(false);
		frmRecettesDeCuisine.setTitle("Délice !");
		frmRecettesDeCuisine.setBounds(100, 100, 750, 581);
		frmRecettesDeCuisine.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmRecettesDeCuisine.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("ImageAppli\\recettes_jour_c.jpg"));
		lblNewLabel.setBounds(0, 0, 370, 275);
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel.setIcon(new ImageIcon("ImageAppli\\recettes_jour_bw.jpg"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblNewLabel.setIcon(new ImageIcon("ImageAppli\\recettes_jour_c.jpg"));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				Swing_MenuJour recette = new Swing_MenuJour(m,ctrl);
				recette.setVisible2();
			}
		});
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setIcon(new ImageIcon("ImageAppli\\delice_logo.png"));
		lblNewLabel_3.setBounds(323, 229, 100, 100);
		panel.add(lblNewLabel_3);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("ImageAppli\\rechercher_ing\u00E9drients_c.jpg"));
		lblNewLabel_1.setBounds(376, 0, 370, 275);
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel_1.setIcon(new ImageIcon("ImageAppli\\rechercher_ing\u00E9drients_bw.jpg"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblNewLabel_1.setIcon(new ImageIcon("ImageAppli\\rechercher_ing\u00E9drients_c.jpg"));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				Swing_RechercheIngredients recette = new Swing_RechercheIngredients(m,ctrl);
				recette.setVisible2();
			}
		});
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon("ImageAppli\\liste_recettes_c.png"));
		lblNewLabel_2.setBounds(0, 281, 370, 275);
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel_2.setIcon(new ImageIcon("ImageAppli\\liste_recettes_bw.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblNewLabel_2.setIcon(new ImageIcon("ImageAppli\\liste_recettes_c.png"));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				Swing_ListeRecettes recette = new Swing_ListeRecettes(m,ctrl);
				recette.setVisible2();
			}
		});
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("New label");
		lblNewLabel_2_1.setIcon(new ImageIcon("ImageAppli\\rechercher_titre_c.jpg"));
		lblNewLabel_2_1.setBounds(376, 281, 370, 275);
		lblNewLabel_2_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel_2_1.setIcon(new ImageIcon("ImageAppli\\rechercher_titre_bw.jpg"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblNewLabel_2_1.setIcon(new ImageIcon("ImageAppli\\rechercher_titre_c.jpg"));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				Swing_RechercheTitre recette = new Swing_RechercheTitre(m,ctrl);
				recette.setVisible2();
			}
		});
		panel.add(lblNewLabel_2_1);
		
		
	
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		this.m.enregistrer();
		System.exit(0);
		}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
