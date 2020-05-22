package modelo;



import java.util.ArrayList;
import java.util.List;

import myExceptions.ContagiadoException;
import processing.core.PApplet;

public class Logica implements Runnable{
	
	private PApplet app;
	private List<Bola> bolas;
	private int iF;
	private int sA;
	private int rE;
	
	
	public Logica(PApplet app) {
		this.app = app;
		bolas = new ArrayList<Bola>();
		cargarData();
	
	}
	
	/*
	private void iniciarHilo() {
		// TODO Auto-generated method stub
		for (int j = 0; j<bolas.size(); j++) {
			new Thread(bolas.get(j)).start();
		}

	}*/

	public void update() {
		for (int i = 0; i < bolas.size(); i++) {
			bolas.get(i).pintar();
	
		}
	}
	
	
	public void cargarData() {
		String[] lines = app.loadStrings("data/data.txt");
		int[] n = new int[lines.length];
		
		int infectados = 0, sanas = 0, recuperadas = 0;
		
		for (int i = 0; i < lines.length; i++) {
			String[] parts = lines[i].split(":");
			if(parts[0].equals("infectadas"))
				infectados = Integer.parseInt(parts[1]);
			if(parts[0].equals("sanas"))
				sanas = Integer.parseInt(parts[1]);
			if(parts[0].equals("recuperadas"))
				recuperadas = Integer.parseInt(parts[1]);
			
		}
		
		//System.out.println(" "+infectados+" "+sanas+" "+recuperadas);
		
		for (int i = 0; i < infectados; i++) {
			Bola b = new Bola((int)app.random(50, 750), (int)app.random(50, 750), app, Persona.INFECTADO );
			bolas.add(b);
		}
		
		for (int i = 0; i < sanas; i++) {
			Bola b = new Bola((int)app.random(50, 750), (int)app.random(50, 750), app, Persona.SANO );
			bolas.add(b);
		}
		
		for (int i = 0; i < recuperadas; i++) {
			Bola b = new Bola((int)app.random(50, 750), (int)app.random(50, 750), app, Persona.RECUPERADO );
			bolas.add(b);
		}
		
		iF = infectados;
		rE = recuperadas;
		sA = sanas;
		
	
		
		
	
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			for (int i = 0; i < bolas.size(); i++) {
				
				Bola b = bolas.get(i);
				b.moverse();
				try {
					rebotar(b, i);
				} catch (ContagiadoException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					System.out.println(e.getMessage());
				}
				
				
			}
			
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void rebotar(Bola bb, int j) throws ContagiadoException {
		boolean rebotan = false;
		Bola b = null;
		
		for (int i = 0; i < bolas.size(); i++) {
			if(i!=j) {
				b = bolas.get(i);
				double dist = app.dist(bb.getPosx(), bb.getPosy(), b.getPosx(), b.getPosy());
				if (dist<=b.getR()+bb.getR()) {
					rebotan = true;
				}
			}
		}
		
		if(rebotan) {
			b.rebotan();
			bb.rebotan();
			
			//System.out.println(b.getEstado());
			//System.out.println(bb.getEstado());
			//System.out.println("");
			//System.out.println(b.getEstado());
			if(b.getEstado()==Persona.INFECTADO) {
				
				if(bb.getEstado()==Persona.SANO) {
					new Thread(bb).start();
					System.out.println("H");
				}
				

			}
			
			if(bb.getEstado()==Persona.INFECTADO) {
				
				if(b.getEstado()==Persona.SANO) {
					
					new Thread(b).start();
					System.out.println("H");
					
				}
				
				throw new ContagiadoException();

			}
		}
		
	}

}
