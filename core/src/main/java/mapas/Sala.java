package mapas;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import elementos.Imagen;
import personajes.Enemigo;

public abstract class Sala {
	protected float ancho, alto;
	protected ArrayList <Rectangle> obstaculos;
	protected Imagen fondo;
	private ArrayList<Enemigo> enemigos;
	public Sala(float ancho, float alto, String rutaFondo) {
		this.ancho = ancho;
		this.alto = alto;
		this.fondo = new Imagen(rutaFondo);
		this.obstaculos = new ArrayList<>();
		this.enemigos = new ArrayList<>();
		cargarElementos();
	}
	protected abstract void cargarElementos(); 

	protected void agregarObstaculo(float x, float y, float ancho, float alto) {
		obstaculos.add(new Rectangle(x, y, ancho, alto));
	}
	protected void agregarEnemigos(Enemigo e) {
		enemigos.add(e);
		
	}
	
	public void dibujarFondo(SpriteBatch batch) {
		fondo.dibujar(batch);
	}

	public ArrayList<Rectangle> getObstaculos() {

		return obstaculos;
	}

	public Imagen getFondo() {
		return fondo;
	}

	public void dispose() {
		fondo.dispose();
	}

	public ArrayList<Enemigo> getEnemigos() {
		return enemigos;
	}

	public boolean isLimpia() {
		return enemigos.isEmpty();
	}

	public void regenerar() {
		cargarElementos();
	}

	public boolean revisarMuerto(Enemigo e) {
		if (e.isMuerto()) {
			e.dispose();
			enemigos.remove(e);
			return true;
		}
		return false;

	}
}
