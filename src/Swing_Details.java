import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class Swing_Details {

	private JFrame frame;
	Recette Recette;
	Modele m;
	Controleur ctrl;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Swing_Details window = new Swing_Details();
					//window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public Swing_Details(Recette r, Modele m, Controleur ctrl) {
		this.Recette = r;
		this.m = m;
		this.ctrl = ctrl;
		if (m.modeClair) {
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
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();frame.setBounds(100, 100, 289, 450);
		frame.setTitle("Délice !");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);
		panel.setBounds(10, 10, 254, 390);
		frame.getContentPane().add(panel);
		
		JLabel lblNewLabel = new JLabel("Image illustration");
		lblNewLabel.setIcon(new ImageIcon(Recette.Image));
		lblNewLabel.setBackground(Color.LIGHT_GRAY);
		lblNewLabel.setBounds(10, 10, 234, 137);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(Recette.getNom());
		lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 16));
		lblNewLabel_1.setBounds(10, 152, 234, 30);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setForeground(new Color(255, 127, 80));
		lblNewLabel_4.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		lblNewLabel_4.setBounds(144, 214, 100, 20);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setForeground(new Color(255, 127, 80));
		lblNewLabel_5.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		lblNewLabel_5.setBounds(10, 214, 100, 20);
		panel.add(lblNewLabel_5);
		
		JSpinner spinner = new JSpinner(new SpinnerNumberModel(Recette.Personnes,0,100,1));
		spinner.setForeground(new Color(255, 127, 80));
		spinner.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		spinner.setBounds(37, 265, 50, 20);
		panel.add(spinner);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setBounds(113, 328, 30, 30);
		panel.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Voir liste");
		btnNewButton.setForeground(new Color(255, 127, 80));
		btnNewButton.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 12));
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
		lblNewLabel_7_1_2.setBounds(3, 247, 121, 13);
		panel.add(lblNewLabel_7_1_2);
		
		JLabel lblNewLabel_7_1_2_1 = new JLabel("Ingr\u00E9dients");
		lblNewLabel_7_1_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7_1_2_1.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
		lblNewLabel_7_1_2_1.setBounds(130, 247, 124, 13);
		panel.add(lblNewLabel_7_1_2_1);
	}
	
	public void setVisible(Recette r) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try { 
					Swing_Details window = new Swing_Details(r, m, ctrl);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
