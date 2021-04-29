import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;

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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextPane;

public class Swing_Etape implements Observer {

	private JFrame frame;
	Modele m;	
	Controleur ctrl;
	Recette recette;
	int num_etp =0;
	int nbr_pers;
	JLabel lblNewLabel_3;
	JTextPane lblNewLabel_4;

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
		m.addObserver(this);
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
		frame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println("Test");
			}
			@Override
			public void keyTyped(KeyEvent e) {
				System.out.println("Test2");
			}
		});
		frame.setBounds(100, 100, 440, 422);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setTitle(recette.Nom);
		frame.setResizable(false);
		
		
		
		
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel(recette.Nom);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 10, 406, 40);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1_2 = new JLabel("New label");
		lblNewLabel_1_2.setBounds(375, 75, 30, 30);
		if (m.modeClair) {
			lblNewLabel_1_2.setIcon(new ImageIcon("ImageAppli\\edit_white.png"));
		} else {
			lblNewLabel_1_2.setIcon(new ImageIcon("ImageAppli\\edit_black.png"));
		}
		
		panel.add(lblNewLabel_1_2);
		
		
		JProgressBar progressBar = new JProgressBar();
		int valeurProgression = 100 / ((recette.Etapes.size())-1);
		progressBar.setValue(0);
		progressBar.setBounds(10, 329, 406, 5);
		panel.add(progressBar);
		
		JLabel lblNewLabel_1 = new JLabel("Next");
		
		JLabel lblNewLabel_1_1 = new JLabel(" ");
		//lblNewLabel_1_1.setIcon(new ImageIcon("ImageAppli\\previous_normal.png"));
		lblNewLabel_1_1.setBounds(10, 346, 30, 30);
		lblNewLabel_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (num_etp==1) {
					progressBar.setValue(progressBar.getValue()-valeurProgression);
					lblNewLabel_1_1.setIcon(null);
					num_etp--;
					m.changeEtape(recette,num_etp);
				} else if (num_etp==0) {
					//Do nothing
				} else {
					progressBar.setValue(progressBar.getValue()-valeurProgression);
					if (num_etp==(recette.getEtapes().size())-1) {
						if (m.modeClair) {
							lblNewLabel_1.setIcon(new ImageIcon("ImageAppli\\next_black.png"));
						} else {
							lblNewLabel_1.setIcon(new ImageIcon("ImageAppli\\next_white.png"));
						}
					}
					num_etp--;
					m.changeEtape(recette,num_etp);
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
					if (m.modeClair) {
						lblNewLabel_1_1.setIcon(new ImageIcon("ImageAppli\\previous_black.png"));
					} else {
						lblNewLabel_1_1.setIcon(new ImageIcon("ImageAppli\\previous_white.png"));
					}
				}
				
			}
		});
		panel.add(lblNewLabel_1_1);
		
		if (m.modeClair) {
			lblNewLabel_1.setIcon(new ImageIcon("ImageAppli\\next_black.png"));
		} else {
			lblNewLabel_1.setIcon(new ImageIcon("ImageAppli\\next_white.png"));
		}
		lblNewLabel_1.setBounds(386, 346, 30, 30);
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (num_etp<(recette.getEtapes().size())-1) {
					progressBar.setValue(progressBar.getValue()+valeurProgression);
					num_etp++;
					m.changeEtape(recette,num_etp);
					if (num_etp==(recette.getEtapes().size())-1) {
						lblNewLabel_1.setIcon(new ImageIcon("ImageAppli\\check_souris.png"));
					} else if (num_etp==1) {
						if (m.modeClair) {
							lblNewLabel_1_1.setIcon(new ImageIcon("ImageAppli\\previous_black.png"));
						} else {
							lblNewLabel_1_1.setIcon(new ImageIcon("ImageAppli\\previous_white.png"));
						}
					}
				} else {
					frame.dispose();
				}
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				if (num_etp!=recette.getEtapes().size()-1) {
					lblNewLabel_1.setIcon(new ImageIcon("ImageAppli\\next_souris.png"));
				} else {
					lblNewLabel_1.setIcon(new ImageIcon("ImageAppli\\check_souris.png"));
				}
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if (num_etp!=recette.getEtapes().size()-1) {
					if (m.modeClair) {
						lblNewLabel_1.setIcon(new ImageIcon("ImageAppli\\next_black.png"));
					} else {
						lblNewLabel_1.setIcon(new ImageIcon("ImageAppli\\next_white.png"));
					}
				} else {
					if (m.modeClair) {
						lblNewLabel_1.setIcon(new ImageIcon("ImageAppli\\check_black.png"));
					} else {
						lblNewLabel_1.setIcon(new ImageIcon("ImageAppli\\check_white.png"));
					}
					
				}
			}
		});
		panel.add(lblNewLabel_1);
		
		
		this.lblNewLabel_3 = new JLabel(String.valueOf(num_etp)+1);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 30));
		lblNewLabel_3.setBounds(182, 60, 60, 61);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_2 = new JLabel("");
		if (m.modeClair) {
			lblNewLabel_2.setIcon(new ImageIcon("ImageAppli\\bandeau_clair.png"));
		} else {
			lblNewLabel_2.setIcon(new ImageIcon("ImageAppli\\bandeau_sombre.jpg"));
		}
		
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 25));
		lblNewLabel_2.setBackground(new Color(32, 178, 170));
		lblNewLabel_2.setBounds(10, 60, 406, 61);
		panel.add(lblNewLabel_2);
		
		this.lblNewLabel_4 = new JTextPane();
		lblNewLabel_4.setEditable(false);
		if (m.modeClair) {
			lblNewLabel_4.setForeground(Color.BLACK);
			lblNewLabel_4.setBackground(Color.WHITE);
		} else {
			lblNewLabel_4.setForeground(Color.WHITE);
			lblNewLabel_4.setBackground(Color.BLACK);
		}
		lblNewLabel_4.setText(recette.getEtapes().get(num_etp).getInstrution());
		lblNewLabel_4.setFont(new Font("Segoe UI Light", Font.PLAIN, 20));
		lblNewLabel_4.setBounds(20, 131, 385, 183);
		panel.add(lblNewLabel_4);
		
		
		lblNewLabel_1_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel_1_2.setIcon(new ImageIcon("ImageAppli\\edit_souris.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if (m.modeClair) {
					lblNewLabel_1_2.setIcon(new ImageIcon("ImageAppli\\edit_white.png"));
				} else {
					lblNewLabel_1_2.setIcon(new ImageIcon("ImageAppli\\edit_black.png"));
				}
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				String etp = JOptionPane.showInputDialog(frame, "Modifier l'étape", recette.getEtapes().get(num_etp).getInstrution());
				recette.changeEtape(num_etp, etp);
				lblNewLabel_4.setText(recette.getEtapes().get(num_etp).getInstrution());
				m.enregistrer();
			}
		});
		
		
		JButton btnNewButton = new JButton("Ingr\u00E9dients");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String liste = recette.afficherIngredients(nbr_pers);
				JOptionPane.showMessageDialog(frame, liste, "Liste ingrédients", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnNewButton.setBounds(157, 351, 103, 21);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setOpaque(true);
		if (m.modeClair) {
			lblNewLabel_5.setBackground(Color.WHITE);
		} else {
			lblNewLabel_5.setBackground(Color.BLACK);
		}
		lblNewLabel_5.setBounds(10, 60, 406, 260);
		panel.add(lblNewLabel_5);
		
		
		KeyboardFocusManager keyManager;

		keyManager=KeyboardFocusManager.getCurrentKeyboardFocusManager();
		keyManager.addKeyEventDispatcher(new KeyEventDispatcher() {

		  @Override
		  public boolean dispatchKeyEvent(KeyEvent e) {
		    if(e.getID()==KeyEvent.KEY_PRESSED && (e.getKeyCode()==KeyEvent.VK_RIGHT || e.getKeyCode()==KeyEvent.VK_KP_RIGHT)){
		      
		      if (num_etp<(recette.getEtapes().size())-1) {
		    	  System.out.println("Droite");
		    	  progressBar.setValue(progressBar.getValue()+valeurProgression);
		    	  num_etp++;
		    	  if (num_etp==(recette.getEtapes().size())-1) { 
		    		  lblNewLabel_1.setIcon(new ImageIcon("ImageAppli\\check_souris.png"));
		    	  } else if (num_etp==1) {
		    		  if (m.modeClair) {
		    			  lblNewLabel_1_1.setIcon(new ImageIcon("ImageAppli\\previous_black.png"));
		    		  } else {
		    			  lblNewLabel_1_1.setIcon(new ImageIcon("ImageAppli\\previous_white.png"));
					  }
				  }
				  m.changeEtape(recette,num_etp);
			  } else {
				  frame.dispose();
			  }
		      return true;
		    } else if(e.getID()==KeyEvent.KEY_PRESSED && (e.getKeyCode()==KeyEvent.VK_LEFT || e.getKeyCode()==KeyEvent.VK_KP_LEFT)) {
		    	System.out.println("Gauche");
		    	if (num_etp==1) {
					progressBar.setValue(progressBar.getValue()-valeurProgression);
					lblNewLabel_1_1.setIcon(null);
					num_etp--;
					m.changeEtape(recette,num_etp);
				} else if (num_etp==0) {
					//Do nothing
				} else {
					progressBar.setValue(progressBar.getValue()-valeurProgression);
					if (num_etp==(recette.getEtapes().size())-1) {
						if (m.modeClair) {
							lblNewLabel_1.setIcon(new ImageIcon("ImageAppli\\next_black.png"));
						} else {
							lblNewLabel_1.setIcon(new ImageIcon("ImageAppli\\next_white.png"));
						}
					}
					num_etp--;
					m.changeEtape(recette,num_etp);
				}
			    return true;
		    } else if(e.getID()==KeyEvent.KEY_PRESSED && e.getKeyCode()==KeyEvent.VK_SPACE) {
		    	System.out.println("Espace");
		    	if (num_etp<(recette.getEtapes().size())-1) {
					progressBar.setValue(progressBar.getValue()+valeurProgression);
					num_etp++;
					m.changeEtape(recette,num_etp);
					if (num_etp==(recette.getEtapes().size())-1) {
						lblNewLabel_1.setIcon(new ImageIcon("ImageAppli\\check_souris.png"));
					} else if (num_etp==1) {
						if (m.modeClair) {
							lblNewLabel_1_1.setIcon(new ImageIcon("ImageAppli\\previous_black.png"));
						} else {
							lblNewLabel_1_1.setIcon(new ImageIcon("ImageAppli\\previous_white.png"));
						}
					}
				} else {
					frame.dispose();
				}
			    return true;
		    }
		    return false;
		  }

		});
		
		
	}

	@Override
	public void update(Observable m, Object arg) {
		// TODO Auto-generated method stub
		if (arg instanceof Etape) {
			Etape etape = (Etape)arg;
			if (etape.NumEtape<10) {
				this.lblNewLabel_3.setText("0"+String.valueOf((etape).NumEtape));
			}else {this.lblNewLabel_3.setText(String.valueOf((etape).NumEtape));}
			this.lblNewLabel_4.setText(etape.getInstrution());
		}
	}

	
}
