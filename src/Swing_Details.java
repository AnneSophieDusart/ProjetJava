import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTextPane;

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
		frame = new JFrame();frame.setBounds(100, 100, 553, 408);
		frame.setTitle("Délice !");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		if (m.modeClair) {
			panel.setBackground(Color.WHITE);
		} else {
			panel.setBackground(Color.BLACK);
		}
		panel.setLayout(null);
		panel.setBounds(10, 10, 254, 312);
		frame.getContentPane().add(panel);
		
		JLabel lblNewLabel = new JLabel();
		ImageIcon imageIcon = new ImageIcon(Recette.Image);
		Image image = imageIcon.getImage();
		Image newimg = image.getScaledInstance(234, 137, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(newimg);
		lblNewLabel.setIcon(imageIcon);
		//lblNewLabel.setIcon(new ImageIcon(Recette.Image));
		lblNewLabel.setBackground(Color.LIGHT_GRAY);
		lblNewLabel.setBounds(10, 10, 234, 137);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(Recette.getNom());
		lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 16));
		lblNewLabel_1.setBounds(10, 152, 234, 30);
		panel.add(lblNewLabel_1);
		
		
		String cuisson ="";
		if (Recette.TempsCuisson > 60 ) {
			cuisson+= Recette.TempsCuisson/60 + " h " + Recette.TempsCuisson%60 + " min";
		}
		else if (Recette.TempsCuisson == 60) {
			cuisson+= "1 h";
		}
		else {
			cuisson+= Recette.TempsCuisson + " min";
		}
		JLabel lblNewLabel_4 = new JLabel(cuisson);
		lblNewLabel_4.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setForeground(new Color(255, 127, 80));
		lblNewLabel_4.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		lblNewLabel_4.setBounds(144, 214, 100, 20);
		panel.add(lblNewLabel_4);
		
		
		String prepa ="";
		if (Recette.TempsPreparation > 60 ) {
			prepa+= Recette.TempsPreparation/60 + " h " + Recette.TempsPreparation%60 + " min";
		}
		else if (Recette.TempsPreparation == 60) {
			prepa+= "1 h";
		}
		else {
			prepa+= Recette.TempsPreparation + " min";
		}		
		JLabel lblNewLabel_5 = new JLabel(prepa);
		lblNewLabel_5.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setForeground(new Color(255, 127, 80));
		lblNewLabel_5.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		lblNewLabel_5.setBounds(10, 214, 100, 20);
		panel.add(lblNewLabel_5);
		
		JTextPane txtpnHello = new JTextPane();
		
		JSpinner spinner = new JSpinner(new SpinnerNumberModel(Recette.Personnes,0,100,1));
		spinner.addChangeListener(new ChangeListener() {

	        public void stateChanged(ChangeEvent e) {
				txtpnHello.setText(Recette.afficherIngredients((Integer) spinner.getValue()));
	        }
	    });
		spinner.setForeground(new Color(255, 127, 80));
		spinner.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		spinner.setBounds(104, 273, 50, 20);
		panel.add(spinner);
		
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
		lblNewLabel_7_1_2.setBounds(66, 254, 121, 13);
		panel.add(lblNewLabel_7_1_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		if (m.modeClair) {
			panel_1.setBackground(Color.WHITE);
		} else {
			panel_1.setBackground(Color.BLACK);
		}
		panel_1.setBounds(274, 10, 254, 312);
		frame.getContentPane().add(panel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel((String) null);
		lblNewLabel_1_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 16));
		lblNewLabel_1_1.setBounds(10, 152, 234, 30);
		panel_1.add(lblNewLabel_1_1);
		
		
		//TextPane ci dessous
		if (m.modeClair) {
			txtpnHello.setBackground(Color.WHITE);
		} else {
			txtpnHello.setBackground(Color.BLACK);
		}
		txtpnHello.setEditable(false);
		txtpnHello.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 13));
		txtpnHello.setText(Recette.afficherIngredients((Integer) spinner.getValue()));
		txtpnHello.setBounds(10, 24, 244, 260);
		panel_1.add(txtpnHello);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setBounds(255, 332, 30, 30);
		frame.getContentPane().add(lblNewLabel_2);
		if (m.modeClair) {
			lblNewLabel_2.setIcon(new ImageIcon("ImageAppli\\start_black.png"));
		} else {
			lblNewLabel_2.setIcon(new ImageIcon("ImageAppli\\start_white.png"));
		}
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel_2.setIcon(new ImageIcon("ImageAppli\\start_souris.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if (m.modeClair) {
					lblNewLabel_2.setIcon(new ImageIcon("ImageAppli\\start_black.png"));
				} else {
					lblNewLabel_2.setIcon(new ImageIcon("ImageAppli\\start_white.png"));
				}
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				Swing_Etape recette =  new Swing_Etape(m, Recette, (Integer) spinner.getValue());
				recette.setVisible(Recette, (Integer) spinner.getValue());
			}
		});
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
