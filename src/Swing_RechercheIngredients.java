import java.awt.EventQueue;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
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
		frame.setBounds(100, 100, 560, 427);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		txtPremierIngrdient = new JTextField();
		txtPremierIngrdient.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
		txtPremierIngrdient.setText("Premier ingr\u00E9dient");
		txtPremierIngrdient.setBounds(10, 10, 452, 22);
		panel.add(txtPremierIngrdient);
		txtPremierIngrdient.setColumns(10);
		
		txtSecondIngrdient = new JTextField();
		txtSecondIngrdient.setText("Second ingr\u00E9dient");
		txtSecondIngrdient.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
		txtSecondIngrdient.setColumns(10);
		txtSecondIngrdient.setBounds(10, 35, 452, 22);
		panel.add(txtSecondIngrdient);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 67, 526, 313);
		panel.add(scrollPane);
		
		JList list_1 = new JList();
		list_1.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
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
		lblNewLabel_2.setIcon(new ImageIcon("ImageAppli\\search_normal.png"));
		lblNewLabel_2.setBounds(491, 10, 33, 45);
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				lblNewLabel_2.setIcon(new ImageIcon("ImageAppli\\search_souris.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblNewLabel_2.setIcon(new ImageIcon("ImageAppli\\search_normal.png"));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Le bouton \"Search \" vient d'être cliqué.");
			}
		});
		panel.add(lblNewLabel_2);
		
		
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
		Swing_RechercheIngredients win = new Swing_RechercheIngredients(m,ctrl);
		win.setVisible2();
	}
	
	private void setLightMode() {
		this.frame.setVisible(false);
		this.frame.dispose();
		m.modeClair=true;
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
