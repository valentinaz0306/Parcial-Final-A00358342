package modelo;

public abstract class Persona{
	
	public final static char INFECTADO = 'i';
	public final static char SANO = 's';
	public final static char RECUPERADO = 'r';
	
	private int posx;
	private int posy;


	
	public Persona(int px, int py) {

		posx = px;
		posy = py;
	
	}
	// metodo moverse

	public abstract void moverse();

	public abstract void pintar();

	public int getPosx() {
		return posx;
	}

	public void setPosx(int posx) {
		this.posx = posx;
	}

	public int getPosy() {
		return posy;
	}

	public void setPosy(int posy) {
		this.posy = posy;
	}



}