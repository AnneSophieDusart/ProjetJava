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
					Swing_Etape window = new Swing_Etape(true,m);
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

	public Swing_Etape(boolean modeClair, Modele modl) {
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
		this.m=modl;
		this.Recette = m.indexRecetteSelectionnee;
		initialize();
	}
	
	public void setVisible(boolean modeClair) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try { 
					Swing_Etape window = new Swing_Etape(modeClair,m);
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
		progressBar.setBounds(10, 464, 416, 5);
		panel.add(progressBar);
		
		JButton btnNewButton_1 = new JButton("< Previous");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Le bouton \"< Previous \" a été cliqué.");
				progressBar.setValue(progressBar.getValue()-valeurProgression);
			}
			
		});
		btnNewButton_1.setBounds(10, 482, 87, 21);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Next >");
		btnNewButton_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Le bouton \"Next >\" a été cliqué.");
				progressBar.setValue(progressBar.getValue()+valeurProgression);
			}
		});
		btnNewButton_1_1.setBounds(339, 482, 87, 21);
		panel.add(btnNewButton_1_1);
	}
}
