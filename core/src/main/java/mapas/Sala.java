package mapas;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import Enums.Direccion;
import elementos.Imagen;
import personajes.Enemigo;

public abstract class Sala {
	protected float ancho, alto;
	protected ArrayList <Rectangle> obstaculos;
	protected Imagen fondo;
	private ArrayList<Enemigo> enemigos;
	private Direccion puerta1;
	private Direccion puerta2;
	public boolean usado1 = false;
	public boolean usado2 = false;
	public Sala(float ancho, float alto, String rutaFondo, Direccion puerta1, Direccion puerta2) {
		this.ancho = ancho;
		this.alto = alto;
		this.fondo = new Imagen(rutaFondo);
		this.puerta1 = puerta1;
		this.puerta2 = puerta2;
		this.obstaculos = new ArrayList<>();
		this.enemigos = new ArrayList<>();
		cargarElementos();
	}
	public Direccion getPuerta1() {
		return puerta1;
	}
	public Direccion getPuerta2() {
		return puerta2;
	}
	protected abstract void cargarElementos(); 

	protected void agregarObstaculo(float x, float y, float ancho, float alto) {
		obstaculos.add(new Rectangle(x, y, ancho, alto));
	}
	protected void agregarEnemigos(Enemigo e) {
		//enemigos.add(e);
		
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
	public void setearUsado1() {
		this.usado1 = true;
	}
	public void setearUsado2() {
		this.usado2 = true;
	}
}
