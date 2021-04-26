import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;

public class Swing_MenuJour {

	private JFrame frame;
	Modele m;
	Controleur ctrl;
	int Entree,Plat,Dessert;
	JLabel lblNewLabel_4,lblNewLabel_5,lblNewLabel_5_1;
	JLabel lblNewLabel_4_1,lblNewLabel_5_2,lblNewLabel_5_1_1;
	JLabel lblNewLabel_4_1_1,lblNewLabel_5_2_1,lblNewLabel_5_1_1_1;
	
	Recette Recette1, Recette2, Recette3;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Modele m = new Modele();
		Controleur ctrl = new Controleur(m);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Swing_MenuJour window = new Swing_MenuJour(m, ctrl);
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
	public Swing_MenuJour(Modele modl, Controleur ctrl) {
		this.m= modl;
		this.ctrl=ctrl;
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
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 560, 427);
		frame.setTitle("Délice !");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
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
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(1, 1, 99, 1));
		spinner.setBounds(491, 18, 45, 20);
		panel.add(spinner);
		
		Recette1 = m.LRecette.get(randomRecette("Entrée"));
		String temps1 = tempsRecette(Recette1);
		
		
		this.lblNewLabel_4 = new JLabel("Image");
		lblNewLabel_4.setIcon(new ImageIcon(Recette1.Image));
		lblNewLabel_4.setBounds(15, 61, 160, 100);
		panel.add(lblNewLabel_4);
		
		this.lblNewLabel_5 = new JLabel(Recette1.Nom);
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel_5.setBounds(15, 168, 160, 24);
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_7_1 = new JLabel("Pr\u00E9paration et cuisson");
		lblNewLabel_7_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7_1.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
		lblNewLabel_7_1.setBounds(34, 220, 129, 13);
		panel.add(lblNewLabel_7_1);
		
		this.lblNewLabel_5_1 = new JLabel(temps1);
		lblNewLabel_5_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_5_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5_1.setForeground(new Color(255, 127, 80));
		lblNewLabel_5_1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		lblNewLabel_5_1.setBounds(43, 234, 100, 20);
		panel.add(lblNewLabel_5_1);
		
		JLabel lblNewLabel_7_1_1 = new JLabel("Ingrédients");
		lblNewLabel_7_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7_1_1.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
		lblNewLabel_7_1_1.setBounds(43, 264, 100, 13);
		panel.add(lblNewLabel_7_1_1);
		
		JButton btnNewButton = new JButton("Voir liste");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Recette r = Recette1;
				String liste = r.afficherIngredients((Integer) spinner.getValue());
				JOptionPane.showMessageDialog(frame, liste, "Liste ingrédients", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnNewButton.setForeground(new Color(255, 127, 80));
		btnNewButton.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 12));
		btnNewButton.setBounds(53, 281, 79, 21);
		panel.add(btnNewButton);
		
		
		JLabel lblNewLabel_8 = new JLabel("New label");
		if (m.modeClair) {
			lblNewLabel_8.setIcon(new ImageIcon("ImageAppli\\start_black.png"));
		} else {
			lblNewLabel_8.setIcon(new ImageIcon("ImageAppli\\start_white.png"));
		}
		lblNewLabel_8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel_8.setIcon(new ImageIcon("ImageAppli\\start_souris.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if (m.modeClair) {
					lblNewLabel_8.setIcon(new ImageIcon("ImageAppli\\start_black.png"));
				} else {
					lblNewLabel_8.setIcon(new ImageIcon("ImageAppli\\start_white.png"));
				}
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				Swing_Etape recette =  new Swing_Etape(m, Recette1, (Integer) spinner.getValue());
				recette.setVisible(Recette1, (Integer) spinner.getValue());
			}
		});
		lblNewLabel_8.setBounds(79, 338, 30, 30);
		panel.add(lblNewLabel_8);
		
		Recette2 = m.LRecette.get(randomRecette("Plat"));	
		String temps2 = tempsRecette(Recette2);		
		
		this.lblNewLabel_4_1 = new JLabel("Image");
		lblNewLabel_4_1.setIcon(new ImageIcon(Recette2.Image));
		lblNewLabel_4_1.setBounds(194, 61, 160, 100);
		panel.add(lblNewLabel_4_1);
		
		this.lblNewLabel_5_2 = new JLabel(Recette2.Nom);
		lblNewLabel_5_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5_2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel_5_2.setBounds(192, 168, 160, 24);
		panel.add(lblNewLabel_5_2);
		
		JLabel lblNewLabel_7_1_2 = new JLabel("Pr\u00E9paration et cuisson");
		lblNewLabel_7_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7_1_2.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
		lblNewLabel_7_1_2.setBounds(213, 220, 129, 13);
		panel.add(lblNewLabel_7_1_2);
		
		this.lblNewLabel_5_1_1 = new JLabel(temps2);
		lblNewLabel_5_1_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_5_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5_1_1.setForeground(new Color(255, 127, 80));
		lblNewLabel_5_1_1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		lblNewLabel_5_1_1.setBounds(222, 234, 100, 20);
		panel.add(lblNewLabel_5_1_1);
		
		JLabel lblNewLabel_7_1_1_1 = new JLabel("Ingr\u00E9dients");
		lblNewLabel_7_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7_1_1_1.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
		lblNewLabel_7_1_1_1.setBounds(222, 264, 100, 13);
		panel.add(lblNewLabel_7_1_1_1);
		
		JButton btnNewButton_1 = new JButton("Voir liste");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Recette r = Recette2;
				System.out.println(r);
				String liste = r.afficherIngredients((Integer) spinner.getValue());
				JOptionPane.showMessageDialog(frame, liste, "Liste ingrédients", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnNewButton_1.setForeground(new Color(255, 127, 80));
		btnNewButton_1.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 12));
		btnNewButton_1.setBounds(232, 281, 79, 21);
		panel.add(btnNewButton_1);
		
		JLabel lblNewLabel_8_1 = new JLabel("New label");
		if (m.modeClair) {
			lblNewLabel_8_1.setIcon(new ImageIcon("ImageAppli\\start_black.png"));
		} else {
			lblNewLabel_8_1.setIcon(new ImageIcon("ImageAppli\\start_white.png"));
		}
		lblNewLabel_8_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel_8_1.setIcon(new ImageIcon("ImageAppli\\start_souris.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if (m.modeClair) {
					lblNewLabel_8_1.setIcon(new ImageIcon("ImageAppli\\start_black.png"));
				} else {
					lblNewLabel_8_1.setIcon(new ImageIcon("ImageAppli\\start_white.png"));
				}
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				Swing_Etape recette =  new Swing_Etape(m, Recette2, (Integer) spinner.getValue());
				recette.setVisible(Recette2, (Integer) spinner.getValue());
			}
		});
		lblNewLabel_8_1.setBounds(258, 338, 30, 30);
		panel.add(lblNewLabel_8_1);
		
		Recette3 = m.LRecette.get(randomRecette("Dessert"));
		String temps3 = tempsRecette (Recette3);		
		
		this.lblNewLabel_4_1_1 = new JLabel("Image");
		lblNewLabel_4_1_1.setIcon(new ImageIcon(Recette3.Image));
		lblNewLabel_4_1_1.setBounds(371, 61, 160, 100);
		panel.add(lblNewLabel_4_1_1);
		
		this.lblNewLabel_5_2_1 = new JLabel(Recette3.Nom);
		lblNewLabel_5_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5_2_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel_5_2_1.setBounds(369, 168, 160, 24);
		panel.add(lblNewLabel_5_2_1);
		
		JLabel lblNewLabel_7_1_2_1 = new JLabel("Pr\u00E9paration et cuisson");
		lblNewLabel_7_1_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7_1_2_1.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
		lblNewLabel_7_1_2_1.setBounds(390, 220, 129, 13);
		panel.add(lblNewLabel_7_1_2_1);
		
		this.lblNewLabel_5_1_1_1 = new JLabel(temps3);
		lblNewLabel_5_1_1_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_5_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5_1_1_1.setForeground(new Color(255, 127, 80));
		lblNewLabel_5_1_1_1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		lblNewLabel_5_1_1_1.setBounds(399, 234, 100, 20);
		panel.add(lblNewLabel_5_1_1_1);
		
		JLabel lblNewLabel_7_1_1_1_1 = new JLabel("Ingr\u00E9dients");
		lblNewLabel_7_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7_1_1_1_1.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
		lblNewLabel_7_1_1_1_1.setBounds(399, 264, 100, 13);
		panel.add(lblNewLabel_7_1_1_1_1);
		
		JButton btnNewButton_1_1 = new JButton("Voir liste");
		btnNewButton_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Recette r = Recette3;
				System.out.println(r);
				String liste = r.afficherIngredients((Integer) spinner.getValue());
				JOptionPane.showMessageDialog(frame, liste, "Liste ingrédients", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnNewButton_1_1.setForeground(new Color(255, 127, 80));
		btnNewButton_1_1.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 12));
		btnNewButton_1_1.setBounds(409, 281, 79, 21);
		panel.add(btnNewButton_1_1);
		
		JLabel lblNewLabel_8_1_1 = new JLabel("New label");
		if (m.modeClair) {
			lblNewLabel_8_1_1.setIcon(new ImageIcon("ImageAppli\\start_black.png"));
		} else {
			lblNewLabel_8_1_1.setIcon(new ImageIcon("ImageAppli\\start_white.png"));
		}
		lblNewLabel_8_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel_8_1_1.setIcon(new ImageIcon("ImageAppli\\start_souris.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if (m.modeClair) {
					lblNewLabel_8_1_1.setIcon(new ImageIcon("ImageAppli\\start_black.png"));
				} else {
					lblNewLabel_8_1_1.setIcon(new ImageIcon("ImageAppli\\start_white.png"));
				}
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				Swing_Etape recette =  new Swing_Etape(m, Recette3, (Integer) spinner.getValue());
				recette.setVisible(Recette3, (Integer) spinner.getValue());
			}
		});
		lblNewLabel_8_1_1.setBounds(435, 338, 30, 30);
		panel.add(lblNewLabel_8_1_1);
		
		
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBounds(10, 56, 170, 324);
		panel.add(lblNewLabel);
		if (m.modeClair) {
			lblNewLabel.setBackground(Color.WHITE);
		} else {
			lblNewLabel.setBackground(Color.BLACK);
		}
		
		JLabel lblNewLabel_1 = new JLabel(" ");
		lblNewLabel_1.setOpaque(true);
		if (m.modeClair) {
			lblNewLabel_1.setBackground(Color.WHITE);
		} else {
			lblNewLabel_1.setBackground(Color.BLACK);
		}
		lblNewLabel_1.setBounds(366, 56, 170, 324);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel(" ");
		lblNewLabel_2.setOpaque(true);
		if (m.modeClair) {
			lblNewLabel_2.setBackground(Color.WHITE);
		} else {
			lblNewLabel_2.setBackground(Color.BLACK);
		}
		lblNewLabel_2.setBounds(188, 56, 170, 324);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Au menu aujourd'hui...");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(156, 10, 241, 36);
		panel.add(lblNewLabel_3);
		
		
		JLabel lblNewLabel_6 = new JLabel("New label");
		if (m.modeClair) {
			lblNewLabel_6.setIcon(new ImageIcon("ImageAppli\\person_black.png"));
		} else {
			lblNewLabel_6.setIcon(new ImageIcon("ImageAppli\\person_white.png"));
		}
		
		lblNewLabel_6.setBounds(460, 12, 30, 30);
		panel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("New label");
		if (m.modeClair) {
			lblNewLabel_7.setIcon(new ImageIcon("ImageAppli\\refresh_black.png"));
		} else {
			lblNewLabel_7.setIcon(new ImageIcon("ImageAppli\\refresh_white.png"));
		}
		lblNewLabel_7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel_7.setIcon(new ImageIcon("ImageAppli\\refresh_souris.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if (m.modeClair) {
					lblNewLabel_7.setIcon(new ImageIcon("ImageAppli\\refresh_black.png"));
				} else {
					lblNewLabel_7.setIcon(new ImageIcon("ImageAppli\\refresh_white.png"));
				}
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				Recette1 = m.LRecette.get(randomRecette("Entrée"));
				String temps1 = tempsRecette(Recette1);
				lblNewLabel_4.setIcon(new ImageIcon(Recette1.Image));;
				lblNewLabel_5.setText(Recette1.Nom);
				lblNewLabel_5_1.setText(temps1);
				
				Recette2 = m.LRecette.get(randomRecette("Plat"));
				String temps2 = tempsRecette(Recette2);
				lblNewLabel_4_1.setIcon(new ImageIcon(Recette2.Image));;
				lblNewLabel_5_2.setText(Recette2.Nom);
				lblNewLabel_5_1_1.setText(temps2);
				
				Recette3 = m.LRecette.get(randomRecette("Dessert"));
				String temps3 = tempsRecette(Recette3);
				lblNewLabel_4_1_1.setIcon(new ImageIcon(Recette3.Image));;
				lblNewLabel_5_2_1.setText(Recette3.Nom);
				lblNewLabel_5_1_1_1.setText(temps3);
			}
		});
		lblNewLabel_7.setBounds(10, 10, 30, 30);
		panel.add(lblNewLabel_7);
		
		
	}
	
	private void setDarkMode() {
		this.frame.setVisible(false);
		this.frame.dispose();
		m.modeClair=false;
		Swing_MenuJour win = new Swing_MenuJour(m,ctrl);
		win.setVisible2();
	}
	
	private void setLightMode() {
		this.frame.setVisible(false);
		this.frame.dispose();
		m.modeClair=true;
		Swing_MenuJour win = new Swing_MenuJour(m,ctrl);
		win.setVisible2();
	}
	
	public void setVisible2() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try { 
					Swing_MenuJour window = new Swing_MenuJour(m,ctrl);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public int randomRecette( String type) {
		Random r = new Random();
		int numero = r.nextInt(m.dicoType.get(type).size());
		String nomRecette= m.dicoType.get(type).get(numero);
		int Recette = 0;
		for (int i=0; i<m.LRecette.size();i++) {
			if ( (m.LRecette.get(i).Nom).equals(nomRecette)) {
				Recette = i;
			}
		}
		return Recette;
	}
	
	public String tempsRecette(Recette Recette) {
		//int duree = m.LRecette.get(Recette).TempsCuisson +  m.LRecette.get(Recette).TempsPreparation;
		int duree = Recette.TempsCuisson + Recette.TempsPreparation;
		String temps ="";
		if (duree > 60 ) {
			temps+= duree/60 + " h " + duree%60 + " min";
		}
		else if (duree == 60) {
			temps+= "1 h";
		}
		else {
			temps+= duree + " min";
		}
		return temps;
	}
}
