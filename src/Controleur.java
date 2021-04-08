import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class Controleur implements ListSelectionListener {
	
	Modele modl;
	
	public Controleur(Modele m) {
		this.modl = m;
	}


	@Override
	public void valueChanged(ListSelectionEvent e) {
		System.out.println("On change de plat");
	}
	
}