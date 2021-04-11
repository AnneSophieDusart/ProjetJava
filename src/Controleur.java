import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class Controleur implements ListSelectionListener {
	
	Modele modl;
	
	public Controleur(Modele m) {
		this.modl = m;
	}


	@Override
	public void valueChanged(ListSelectionEvent e) {
		if(!e.getValueIsAdjusting()) {
			//System.out.println(Swing_RechercheTitre.list.getSelectedIndex());
			modl.selectionRecette(Swing_RechercheTitre.list.getSelectedIndex());
		}
	}
	
}