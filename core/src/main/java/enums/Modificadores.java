package enums;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import elementos.Imagen;
import util.Aleatorio;

public enum Modificadores {
	N1("Minusculo", false, "cartas/cartaN1.png"), N2("Rodeado", false, "cartas/cartaN2.png"), N3("Pobre", false, "cartas/cartaN3.png"), N4("Debilitado", false, "cartas/cartaN4.png"), N5("Envenenado", false, "cartas/cartaN5.png"), N6("Cansado", false, "cartas/cartaN6.png"), 
	P1("Pacifista", true, "cartas/cartaP1.png"), P2("Invisible", true, "cartas/cartaP2.png"), P3("Temerario", true, "cartas/cartaP3.png"), P4("Curandero", true, "cartas/cartaP4.png"), P5("Coleccionista", true, "cartas/cartaP5.png"), P6("Avaricioso", true, "cartas/cartaP6.png");
	
	private boolean activo;
	private Imagen img;
	private String nombre;
	private boolean positivo;
	private boolean usado;
	private Modificadores(String nombre, boolean positivo, String ruta) {
		this.activo = false;
		this.usado = false;
		this.nombre = nombre;
		this.positivo = positivo;
		this.img = new Imagen(ruta);
	}
	
	public boolean isActivo() {
		return activo;
	}
	public String getNombre() {
		return nombre;
	}
	public boolean isPositivo() {
		return positivo;
	}
	public boolean isUsado() {
		return usado;
	}
	public void activar() {
		this.activo = true;
	}
	public void usar() {
		this.usado = true;
	}
	public Imagen getImg() {
		return img;
	}
	public void dibujar(SpriteBatch batch) {
		img.dibujar(batch);
	}


	public void resetear() {
		this.usado = false;
		this.activo = false;
	}

}
