import java.awt.EventQueue;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.AbstractListModel;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;

public class Swing_RechercheIngredients implements Observer {

	Modele m;
	Controleur ctrl;
	int Recette = 0;
	static JList<String> list;
	JLabel lblNewLabel;
	JLabel lblNewLabel_1;
	
	private JFrame frame;
	private JTextField txtPremierIngrdient;
	private JTextField txtSecondIngrdient;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Modele m = new Modele();
		Controleur ctrl = new Controleur(m);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Swing_RechercheIngredients window = new Swing_RechercheIngredients(m, ctrl);
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
	public Swing_RechercheIngredients(Modele modl, Controleur cont) {
		
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
		this.m= modl;
		this.ctrl=cont;
		initialize();
		m.addObserver(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 570, 450);
		frame.setTitle("Délice !");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		txtPremierIngrdient = new JTextField();
		if (m.modeClair) {
			txtPremierIngrdient.setBackground(Color.WHITE);
		} else {
			txtPremierIngrdient.setBackground(Color.BLACK);
		}
		txtPremierIngrdient.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 13));
		txtPremierIngrdient.setText("Premier ingr\u00E9dient");
		txtPremierIngrdient.setBounds(10, 10, 232, 22);
		panel.add(txtPremierIngrdient);
		txtPremierIngrdient.setColumns(10);
		
		txtSecondIngrdient = new JTextField();
		if (m.modeClair) {
			txtSecondIngrdient.setBackground(Color.WHITE);
		} else {
			txtSecondIngrdient.setBackground(Color.BLACK);
		}
		txtSecondIngrdient.setText("Second ingr\u00E9dient");
		txtSecondIngrdient.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 13));
		txtSecondIngrdient.setColumns(10);
		txtSecondIngrdient.setBounds(10, 34, 232, 22);
		panel.add(txtSecondIngrdient);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 66, 272, 334);
		panel.add(scrollPane);
		
		JList list_1 = new JList();
		if (m.modeClair) {
			list_1.setBackground(Color.WHITE);
		} else {
			list_1.setBackground(Color.BLACK);
		}
		list_1.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 15));
		String[] values = new String[m.LRecette.size()];
		for (int i=0; i<this.m.LRecette.size(); i++) {
			values[i]=m.LRecette.get(i).Nom;
		}
		list_1.setModel(new AbstractListModel() {
			//String[] values = new String[] {"Recette 1", "Recette 2", "Recette 3", "Recette 1", "Recette 2", "Recette 3", "Recette 1", "Recette 2", "Recette 3", "Recette 1", "Recette 2", "Recette 3", "Recette 1", "Recette 2", "Recette 3", "Recette 1", "Recette 2", "Recette 3"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		scrollPane.setViewportView(list_1);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setBounds(252, 17, 30, 30);
		if (m.modeClair) {
			lblNewLabel_2.setIcon(new ImageIcon("ImageAppli\\search_black.png"));
		} else {
			lblNewLabel_2.setIcon(new ImageIcon("ImageAppli\\search_white.png"));
		}
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel_2.setIcon(new ImageIcon("ImageAppli\\search_souris.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if (m.modeClair) {
					lblNewLabel_2.setIcon(new ImageIcon("ImageAppli\\search_black.png"));
				} else {
					lblNewLabel_2.setIcon(new ImageIcon("ImageAppli\\search_white.png"));
				}
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Le bouton \"Search \" vient d'être cliqué.");
			}
		});
		panel.add(lblNewLabel_2);
		
		JPanel panel_1 = new JPanel();
		if (m.modeClair) {
			panel_1.setBackground(Color.WHITE);
		} else {
			panel_1.setBackground(Color.BLACK);
		}
		panel_1.setLayout(null);
		panel_1.setBounds(292, 10, 254, 390);
		panel.add(panel_1);
		
		JLabel lblNewLabel_3 = new JLabel("Image illustration");
		lblNewLabel_3.setIcon(new ImageIcon(m.LRecette.get(Recette).Image));
		lblNewLabel_3.setBackground(Color.LIGHT_GRAY);
		lblNewLabel_3.setBounds(10, 10, 234, 137);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_1_1 = new JLabel(m.LRecette.get(Recette).Nom);
		lblNewLabel_1_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 16));
		lblNewLabel_1_1.setBounds(10, 152, 234, 30);
		panel_1.add(lblNewLabel_1_1);
		
		
		String cuisson ="";
		if (m.LRecette.get(Recette).TempsCuisson > 60 ) {
			cuisson+= m.LRecette.get(Recette).TempsCuisson/60 + " h " + m.LRecette.get(Recette).TempsCuisson%60 + " min";
		}
		else if (m.LRecette.get(Recette).TempsCuisson == 60) {
			cuisson+= "1 h";
		}
		else {
			cuisson+= m.LRecette.get(Recette).TempsCuisson + " min";
		}
		JLabel lblNewLabel_4 = new JLabel(cuisson);
		lblNewLabel_4.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setForeground(new Color(255, 127, 80));
		lblNewLabel_4.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		lblNewLabel_4.setBounds(144, 214, 100, 20);
		panel_1.add(lblNewLabel_4);
		
		String prepa ="";
		if (m.LRecette.get(Recette).TempsPreparation > 60 ) {
			prepa+= m.LRecette.get(Recette).TempsPreparation/60 + " h " + m.LRecette.get(Recette).TempsPreparation%60 + " min";
		}
		else if (m.LRecette.get(Recette).TempsPreparation == 60) {
			prepa+= "1 h";
		}
		else {
			prepa+= m.LRecette.get(Recette).TempsPreparation + " min";
		}	
		JLabel lblNewLabel_5 = new JLabel(prepa);
		lblNewLabel_5.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setForeground(new Color(255, 127, 80));
		lblNewLabel_5.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		lblNewLabel_5.setBounds(10, 214, 100, 20);
		panel_1.add(lblNewLabel_5);
		
		JSpinner spinner = new JSpinner(new SpinnerNumberModel(m.LRecette.get(Recette).Personnes,0,100,1));
		spinner.setForeground(new Color(255, 127, 80));
		spinner.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		spinner.setBounds(37, 265, 50, 20);
		panel_1.add(spinner);
		
		JLabel lblNewLabel_2_1 = new JLabel("New label");
		lblNewLabel_2_1.setBounds(113, 328, 30, 30);
		if (m.modeClair) {
			lblNewLabel_2_1.setIcon(new ImageIcon("ImageAppli\\start_black.png"));
		} else {
			lblNewLabel_2_1.setIcon(new ImageIcon("ImageAppli\\start_white.png"));
		}
		lblNewLabel_2_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel_2_1.setIcon(new ImageIcon("ImageAppli\\start_souris.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if (m.modeClair) {
					lblNewLabel_2_1.setIcon(new ImageIcon("ImageAppli\\start_black.png"));
				} else {
					lblNewLabel_2_1.setIcon(new ImageIcon("ImageAppli\\start_white.png"));
				}
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				Recette r = m.LRecette.get(Recette);
				
				Swing_Etape recette =  new Swing_Etape(m, r, (Integer) spinner.getValue());
				recette.setVisible(r, (Integer) spinner.getValue());
			}
		});
		panel_1.add(lblNewLabel_2_1);
		
		JButton btnNewButton = new JButton("Voir liste");
		btnNewButton.setForeground(new Color(255, 127, 80));
		btnNewButton.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 12));
		btnNewButton.setBounds(153, 266, 79, 21);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//int index = list.getSelectedIndex();
				Recette r = m.LRecette.get(Recette);
				String liste = r.afficherIngredients((Integer) spinner.getValue());
				JOptionPane.showMessageDialog(frame, liste, "Liste ingrédients", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		panel_1.add(btnNewButton);
		
		JLabel lblNewLabel_7_1 = new JLabel("Pr\u00E9paration");
		lblNewLabel_7_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7_1.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
		lblNewLabel_7_1.setBounds(10, 198, 100, 13);
		panel_1.add(lblNewLabel_7_1);
		
		JLabel lblNewLabel_7_1_1 = new JLabel("Cuisson");
		lblNewLabel_7_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7_1_1.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
		lblNewLabel_7_1_1.setBounds(144, 199, 100, 13);
		panel_1.add(lblNewLabel_7_1_1);
		
		JLabel lblNewLabel_7_1_2 = new JLabel("Nombre de personne");
		lblNewLabel_7_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7_1_2.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
		lblNewLabel_7_1_2.setBounds(3, 247, 121, 13);
		panel_1.add(lblNewLabel_7_1_2);
		
		JLabel lblNewLabel_7_1_2_1 = new JLabel("Ingr\u00E9dients");
		lblNewLabel_7_1_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7_1_2_1.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
		lblNewLabel_7_1_2_1.setBounds(130, 247, 124, 13);
		panel_1.add(lblNewLabel_7_1_2_1);
		
		
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
		m.modeClair=false;
		m.enregistrer();
		Swing_RechercheIngredients win = new Swing_RechercheIngredients(m,ctrl);
		win.setVisible2();
	}
	
	private void setLightMode() {
		this.frame.setVisible(false);
		this.frame.dispose();
		m.modeClair=true;
		m.enregistrer();
		Swing_RechercheIngredients win = new Swing_RechercheIngredients(m,ctrl);
		win.setVisible2();
	}
	
	public void setVisible2() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try { 
					Swing_RechercheIngredients window = new Swing_RechercheIngredients(m,ctrl);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
}
