import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ControleurRechercheIngredients implements ListSelectionListener {

	Modele modl;
	
	public ControleurRechercheIngredients(Modele m) {
		this.modl = m;
	}
	
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if(!e.getValueIsAdjusting()) {
			//System.out.println(Swing_RechercheIngredients.list_1.getSelectedIndex());
			modl.selectionRecette(Swing_RechercheIngredients.list_1.getSelectedIndex());
		}
	}

}
