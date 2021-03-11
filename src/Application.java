import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Application extends Frame implements WindowListener {    

	public static void main(String[] args) {
		new Application();
	}
	
	public Application() {
		
		//this.modl = new Modele();
		//this.ctrl = new Controleur();

		this.setLayout(new BorderLayout());
		
		this.addWindowListener(this);
		this.setTitle("Recettes de cuisine ;-)");
		this.pack();
		this.setVisible(true);

	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
	
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		System.exit(0); 
	
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
	
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
