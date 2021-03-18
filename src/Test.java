import javax.swing.*;

public class Test {
	
    public static void main(String args[]){
      JFrame frame = new JFrame("This is an example of creating Frame");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(500,400);
      frame.setLayout(null);
      frame.setVisible(true);
      
      // Texte et image
      JLabel j1 = new JLabel("Ceci est un test de JLabel 1");
      j1.setBounds(70, 120, 120, 50);
      frame.add(j1);
      JLabel j2 = new JLabel("Ceci est un test de JLabel 2");
      j2.setBounds(70, 70, 120, 50);
      frame.add(j2);
      
      // Buttons
      JButton bt = new JButton("Click on me !");
      bt.setBounds(90, 300, 115, 50);
      frame.add(bt);
      
      //Liste déroulante
      String[] langues = {"FR", "EN", "DE", "ES"};
      JComboBox cb1 = new JComboBox(langues);
      cb1.setBounds(200, 50, 90, 20);
      frame.add(cb1);
      
      //Champs de texte (1 ligne TextFiels, plusieur TextArea)
      JTextField tf1 = new JTextField("Rechercher une recette");
      tf1.setBounds(50, 200, 200, 30);
      frame.add(tf1);
      
      //Progress Bar
      JProgressBar pb1 = new JProgressBar(0, 100);
      pb1.setBounds(300, 300, 160, 30);
      pb1.setValue(20);
      pb1.setStringPainted(true);
      frame.add(pb1);
      
      //Pop up
      //JOptionPane
    }
}