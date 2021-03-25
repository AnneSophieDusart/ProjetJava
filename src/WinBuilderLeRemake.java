

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JProgressBar;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class WinBuilderLeRemake extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WinBuilderLeRemake frame = new WinBuilderLeRemake();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public WinBuilderLeRemake() {
		setTitle("Afficher la recette");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setValue(20);
		progressBar.setStringPainted(true);
		progressBar.setForeground(Color.BLUE);
		progressBar.setBounds(113, 31, 209, 32);
		contentPane.add(progressBar);
		
		JLabel lblNewLabel = new JLabel("Nom de la recette");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 74, 414, 25);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Etape n \u00B0");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 110, 414, 25);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Liste des ingr\u00E9dients");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(10, 146, 414, 25);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Instructions");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(10, 182, 414, 25);
		contentPane.add(lblNewLabel_3);
		
		JButton btnNewButton = new JButton("Etape pr\u00E9c\u00E9dente");
		btnNewButton.setBounds(10, 227, 125, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("Etape suivante");
		btnNewButton_2.setBounds(299, 227, 125, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_1 = new JButton("Ajouter un commentaire");
		btnNewButton_1.setBounds(142, 227, 147, 23);
		contentPane.add(btnNewButton_1);
	}
}
