import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.JButton;
import javax.swing.JProgressBar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.Color;

public class Swing_Etape implements Observer {

	private JFrame frame;
	Modele m;	
	Controleur ctrl;
	Recette recette;
	int num_etp =0;
	int nbr_pers;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Modele m = new Modele();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Swing_Etape window = new Swing_Etape(m, r);
					//window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */

	public Swing_Etape(Modele modl, Recette r, int nbr_pers) {
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
		this.recette=r;
		this.nbr_pers=nbr_pers;
		initialize();
	}
	
	public void setVisible(Recette r, int nbr_pers) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try { 
					Swing_Etape window = new Swing_Etape(m, r, nbr_pers);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Initialize the contents of the frame.
	 * @wbp.parser.entryPoint
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 440, 550);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel(recette.Nom);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 10, 406, 40);
		panel.add(lblNewLabel);
		
		
		JProgressBar progressBar = new JProgressBar();
		int valeurProgression = 100 / recette.Etapes.size();
		progressBar.setValue(0);
		progressBar.setBounds(10, 463, 406, 5);
		panel.add(progressBar);
		
		JLabel lblNewLabel_1 = new JLabel("Next");
		
		JLabel lblNewLabel_1_1 = new JLabel(" ");
		//lblNewLabel_1_1.setIcon(new ImageIcon("ImageAppli\\previous_normal.png"));
		lblNewLabel_1_1.setBounds(10, 473, 30, 30);
		lblNewLabel_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (num_etp==0) {
					System.out.println("Vous êtes déjà à la premiere étape");
				} else if (num_etp==1) {
					System.out.println("Retour à la Première étape");
					progressBar.setValue(progressBar.getValue()-valeurProgression);
					lblNewLabel_1_1.setIcon(null);
					num_etp--;
				} else {
					progressBar.setValue(progressBar.getValue()-valeurProgression);
					if (num_etp==recette.getEtapes().size()) {
						lblNewLabel_1.setIcon(new ImageIcon("ImageAppli\\next_normal.png"));
					}
					num_etp--;
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				if (num_etp!=0) {
					lblNewLabel_1_1.setIcon(new ImageIcon("ImageAppli\\previous_souris.png"));
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if (num_etp!=0) {
					lblNewLabel_1_1.setIcon(new ImageIcon("ImageAppli\\previous_normal.png"));
				}
				
			}
		});
		panel.add(lblNewLabel_1_1);
		
		
		
		lblNewLabel_1.setIcon(new ImageIcon("ImageAppli\\next_normal.png"));
		lblNewLabel_1.setBounds(386, 473, 30, 30);
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (num_etp<recette.getEtapes().size()) {
					progressBar.setValue(progressBar.getValue()+valeurProgression);
					System.out.println("On passe à l'étape suivante !");
					num_etp++;
					if (num_etp==recette.getEtapes().size()) {
						lblNewLabel_1.setIcon(new ImageIcon("ImageAppli\\check_souris.png"));
					} else if (num_etp==1) {
						lblNewLabel_1_1.setIcon(new ImageIcon("ImageAppli\\previous_normal.png"));
					}
				} else {
					frame.dispose();
				}
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				if (num_etp!=recette.getEtapes().size()) {
					lblNewLabel_1.setIcon(new ImageIcon("ImageAppli\\next_souris.png"));
				} else {
					lblNewLabel_1.setIcon(new ImageIcon("ImageAppli\\check_souris.png"));
				}
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if (num_etp!=recette.getEtapes().size()) {
					lblNewLabel_1.setIcon(new ImageIcon("ImageAppli\\next_normal.png"));
				} else {
					lblNewLabel_1.setIcon(new ImageIcon("ImageAppli\\check_normal.png"));
				}
			}
		});
		panel.add(lblNewLabel_1);
		
		
		JLabel lblNewLabel_3 = new JLabel(String.valueOf(num_etp)+1);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 30));
		lblNewLabel_3.setBounds(178, 57, 60, 61);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_2 = new JLabel("1");
		if (m.modeClair) {
			lblNewLabel_2.setIcon(new ImageIcon("ImageAppli\\bandeau_clair.jpg"));
		} else {
			lblNewLabel_2.setIcon(new ImageIcon("ImageAppli\\bandeau_sombre.jpg"));
		}
		
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 25));
		lblNewLabel_2.setBackground(new Color(32, 178, 170));
		lblNewLabel_2.setBounds(0, 60, 436, 61);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_4 = new JLabel("<html><span>"+recette.getEtapes().get(num_etp).getInstrution()+"</html></span>");
		lblNewLabel_4.setFont(new Font("Segoe UI Light", Font.PLAIN, 20));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(10, 131, 406, 309);
		panel.add(lblNewLabel_4);
		
		JButton btnNewButton = new JButton("Ingr\u00E9dients");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String liste = recette.afficherIngredients(nbr_pers);
				JOptionPane.showMessageDialog(frame, liste, "Liste ingrédients", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnNewButton.setBounds(159, 478, 103, 21);
		panel.add(btnNewButton);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
}
