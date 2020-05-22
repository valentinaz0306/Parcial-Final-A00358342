package controlador;

import modelo.Logica;
import processing.core.PApplet;

public class Controller {
	
	private PApplet app;
	private Logica logica;
	
	public Controller(PApplet app) {
		this.app = app;
		logica = new Logica(app);
		new Thread(logica).start();;
	}
	
	public void update() {
		logica.update();
	}
}
