package modelo;

import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;

public class Bola extends Persona  implements Runnable{

	private PApplet app;
	private float tam;
	private float r;
	private float g;
	private float b;
	private char estado;
	private int velY;
	private int velX;
	private List<Bola> bolas;

	public Bola(int px, int py, PApplet app, char e) {
		
		super(px, py);
	
		this.app = app;
		tam =  7;
		
		setEstado(e); 
		
		if(getEstado() == Persona.SANO) {
			r = 11;
			g = 218;
			b = 81;
		}else if(getEstado() == Persona.INFECTADO) {
			r = 255;
			g = 9;
			b = 20;
		}else {
			r = 0;
			g = 97;
			b = 176;
		}
		
		velY = (int) (Math.random() * 4) + 1;
		velX=(int) (Math.random() * 4) + 1;
	}



	public void pintar() {
		app.fill(r, g, b);
		app.ellipse(getPosx(),getPosy(),tam, tam);
		//System.out.println(super.getPosx()+" "+super.getPosy());

	}

	public void moverse() {
	
		this.setPosy(velY + this.getPosy());
		//System.out.println(velY+" "+this.getPosy());
		if (this.getPosy() >= 800 || this.getPosy() <= 0) {
			velY= velY * -1;
		}
		this.setPosx(velX + this.getPosx());
		//System.out.println(velX+" "+this.getPosx());
		if (this.getPosx() >= 800 || this.getPosx() <= 0) {
			velX = velX * -1;
		}
		
		//System.out.println("");
		

	}// moverse
	
	public void rebotan() {
		velX = velX * -1;
		velY= velY * -1;
		
	}
	
	public double getR() {
		return tam/2;
		
	}

	public void setColor(int rr, int gg, int bb){
		r = rr;
		g = gg;
		b = bb;
	}



	public char getEstado() {
		return estado;
	}



	public void setEstado(char estado) {
		this.estado = estado;
	}



	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("HOLAAAAAAA");
		estado = Persona.INFECTADO;
		r = 255;
		g = 9;
		b = 20;
		try {
			Thread.sleep(14000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		r = 0;
		g = 97;
		b = 176;
		estado = Persona.RECUPERADO;
	}
}// cierra
