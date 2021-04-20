import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class Controleur implements ListSelectionListener, ChangeListener {
	
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


	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		JSpinner s = (JSpinner) e.getSource();
		String n = s.getValue().toString();
        //System.out.println(n);	
		Integer i = Integer.parseInt(n);
		this.modl.changeQuantite(i);
	}
	
}