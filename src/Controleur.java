import java.awt.ItemSelectable;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class Controleur implements ListSelectionListener, ChangeListener, ItemListener {
	
	Modele modl;
	
	public Controleur(Modele m) {
		this.modl = m;
	}


	@Override
	public void valueChanged(ListSelectionEvent e) {
		if(!e.getValueIsAdjusting()) {
			//System.out.println(Swing_RechercheTitre.list.getSelectedIndex());
			modl.selectionRecette(Swing_RechercheTitre.list.getSelectedIndex() );
		}
	}


	@Override
	public void stateChanged(ChangeEvent e) {
		JSpinner s = (JSpinner) e.getSource();
		String n = s.getValue().toString();
        //System.out.println(n);	
		Integer i = Integer.parseInt(n);
		this.modl.changeQuantite(i);
	}


	@Override
	public void itemStateChanged(ItemEvent e) {
	    if ( e.getStateChange()==ItemEvent.SELECTED ) {
	    	String filtre=(String)e.getItem();
	    	//System.out.println(filtre);
	    	String [] typeFiltre = new String[] {"Type", "Origine"} ;
	    	List<String> typeFiltreListe = Arrays.asList(typeFiltre);
	    	if (typeFiltreListe.contains(filtre)) {
	    		modl.filtrerType(filtre);
	    	}
	    	else {
	    		modl.filtrerListe(filtre);
	    	}
	    	}
	}
	
}