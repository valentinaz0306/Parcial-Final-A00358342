package vista;
import controlador.Controller;
import processing.core.PApplet;

public class Main  extends PApplet {

	private Controller control;
	
	public static void main(String[] args){
		// TODO Auto-generated method stub
		PApplet.main("vista.Main");
	}

	public void settings() {
		size(800,800); 
		control = new Controller(this);
	}
	
	public void draw() {
		background(255);
		control.update();
		
	}
}
