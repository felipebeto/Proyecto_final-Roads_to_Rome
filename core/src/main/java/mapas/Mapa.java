package mapas;

import java.util.ArrayList;

import com.badlogic.gdx.math.Rectangle;

import elementos.Imagen;

public abstract class Mapa {
	protected float ancho, alto;
	protected ArrayList <Rectangle> obstaculos;
	protected Imagen fondo;
	public Mapa(float ancho, float alto, String rutaFondo) {
		this.ancho = ancho;
		this.alto = alto;
		this.obstaculos = new ArrayList<>();
		this.fondo = new Imagen(rutaFondo);
	}
	protected abstract void cargarObstaculos(); 

	public void agregarObstaculo(float x, float y, float ancho, float alto) {
		obstaculos.add(new Rectangle(x, y, ancho, alto));
	}
	
	public void dibujarFondo() {
		fondo.dibujar();
	}

}
