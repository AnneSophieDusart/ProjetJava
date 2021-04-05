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
		
		frmRecettesDeCuisine = new JFrame();
		frmRecettesDeCuisine.setTitle("Recettes de cuisine");
		frmRecettesDeCuisine.setBounds(100, 100, 725, 504);
		//frmRecettesDeCuisine.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmRecettesDeCuisine.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Acc\u00E9der");
		btnNewButton.setBounds(135, 192, 85, 21);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Acc\u00E9der");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Le bouton \"Accéder (recherche par titre) \" vient d'être cliqué.");
				Swing_RechercheTitre recette =  new Swing_RechercheTitre(true);
				recette.setVisible2(true);
			}
		});
		btnNewButton_1.setBounds(501, 192, 85, 21);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Acc\u00E9der");
		btnNewButton_2.setBounds(501, 436, 85, 21);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Acc\u00E9der");
		btnNewButton_3.setBounds(135, 436, 85, 21);
		panel.add(btnNewButton_3);
		
		JLabel lblNewLabel_4 = new JLabel("Recettes");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 25));
		lblNewLabel_4.setBounds(21, 35, 137, 47);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_4_1 = new JLabel("du jour");
		lblNewLabel_4_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_1.setForeground(Color.WHITE);
		lblNewLabel_4_1.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 25));
		lblNewLabel_4_1.setBounds(21, 63, 137, 47);
		panel.add(lblNewLabel_4_1);
		
		
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon("ImageAppli\\img_acc_1.jpg"));
		lblNewLabel.setBounds(0, 0, 350, 228);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_4_2 = new JLabel("Rechercher");
		lblNewLabel_4_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_2.setForeground(Color.WHITE);
		lblNewLabel_4_2.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 25));
		lblNewLabel_4_2.setBounds(550, 84, 137, 47);
		panel.add(lblNewLabel_4_2);
		
		JLabel lblNewLabel_4_1_1 = new JLabel("par titre");
		lblNewLabel_4_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_1_1.setForeground(Color.WHITE);
		lblNewLabel_4_1_1.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 25));
		lblNewLabel_4_1_1.setBounds(550, 112, 137, 47);
		panel.add(lblNewLabel_4_1_1);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("ImageAppli\\img_acc_2.jpg"));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(360, 0, 350, 228);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_4_1_1_1 = new JLabel("par ingr\u00E9dients");
		lblNewLabel_4_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_4_1_1_1.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 25));
		lblNewLabel_4_1_1_1.setBounds(80, 282, 193, 47);
		panel.add(lblNewLabel_4_1_1_1);
		
		JLabel lblNewLabel_4_2_1 = new JLabel("Rechercher");
		lblNewLabel_4_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_2_1.setForeground(Color.WHITE);
		lblNewLabel_4_2_1.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 25));
		lblNewLabel_4_2_1.setBounds(107, 254, 137, 47);
		panel.add(lblNewLabel_4_2_1);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon("ImageAppli\\img_acc_4.jpg"));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(0, 238, 350, 228);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_4_1_1_2 = new JLabel("des recettes");
		lblNewLabel_4_1_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_1_1_2.setForeground(Color.WHITE);
		lblNewLabel_4_1_1_2.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 25));
		lblNewLabel_4_1_1_2.setBounds(391, 301, 137, 47);
		panel.add(lblNewLabel_4_1_1_2);
		
		JLabel lblNewLabel_4_2_2 = new JLabel("Liste");
		lblNewLabel_4_2_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_2_2.setForeground(Color.WHITE);
		lblNewLabel_4_2_2.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 25));
		lblNewLabel_4_2_2.setBounds(391, 273, 137, 47);
		panel.add(lblNewLabel_4_2_2);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setIcon(new ImageIcon("ImageAppli\\img_acc_1.jpg"));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(360, 238, 350, 228);
		panel.add(lblNewLabel_3);
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
