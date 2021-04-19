import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.JButton;
import javax.swing.JProgressBar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

public class Swing_Etape {

	private JFrame frame;
	Modele m;	
	int Recette = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Modele m = new Modele();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Swing_Etape window = new Swing_Etape(m);
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

	public Swing_Etape(Modele modl) {
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
		this.m=modl;
		this.Recette = m.indexRecetteSelectionnee;
		initialize();
	}
	
	public void setVisible() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try { 
					Swing_Etape window = new Swing_Etape(m);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 550);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel(m.LRecette.get(m.indexRecetteSelectionnee).Nom);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 10, 416, 30);
		panel.add(lblNewLabel);
		
		
		JProgressBar progressBar = new JProgressBar();
		int valeurProgression = 100 / m.LRecette.get(m.indexRecetteSelectionnee).Etapes.size();
		progressBar.setValue(0);
		progressBar.setBounds(10, 458, 416, 5);
		panel.add(progressBar);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("ImageAppli\\next_normal.png"));
		lblNewLabel_1.setBounds(396, 473, 30, 30);
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Le bouton \"Next >\" a été cliqué.");
				progressBar.setValue(progressBar.getValue()+valeurProgression);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel_1.setIcon(new ImageIcon("ImageAppli\\next_souris.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblNewLabel_1.setIcon(new ImageIcon("ImageAppli\\next_normal.png"));
			}
		});
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("New label");
		lblNewLabel_1_1.setIcon(new ImageIcon("ImageAppli\\previous_normal.png"));
		lblNewLabel_1_1.setBounds(10, 473, 30, 30);
		lblNewLabel_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Le bouton \"< Previous \" a été cliqué.");
				progressBar.setValue(progressBar.getValue()-valeurProgression);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel_1_1.setIcon(new ImageIcon("ImageAppli\\previous_souris.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblNewLabel_1_1.setIcon(new ImageIcon("ImageAppli\\previous_normal.png"));
			}
		});
		panel.add(lblNewLabel_1_1);
	}
}
