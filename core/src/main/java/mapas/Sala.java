package mapas;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import Enums.Direccion;
import elementos.Imagen;
import items.Objeto;
import personajes.Enemigo;
import personajes.Personaje;
import util.Colisiones;

public abstract class Sala {
	protected float ancho, alto;
	protected ArrayList <Rectangle> obstaculos;
	protected Imagen fondo;
	private ArrayList<Enemigo> enemigos;
	private ArrayList<ObjetoTirado> objetos;
	private Direccion puerta1;
	private Direccion puerta2;
	public Direccion dEntrada;
	public boolean usado1 = false;
	public boolean usado2 = false;
	public Cofre cofre = null;
	public Sala(float ancho, float alto, String rutaFondo, Direccion puerta1, Direccion puerta2) {
		this.ancho = ancho;
		this.alto = alto;
		this.fondo = new Imagen(rutaFondo);
		this.puerta1 = puerta1;
		this.puerta2 = puerta2;
		this.obstaculos = new ArrayList<>();
		this.enemigos = new ArrayList<>();
		this.objetos = new ArrayList<>();
		cargarElementos();
	}
	public void setearEntrada(Direccion d) {
		this.dEntrada = d;
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
	protected void sacarObstaculo(int i) {
		obstaculos.remove(i);
	}
	
	protected void agregarEnemigos(Enemigo e) {
		enemigos.add(e);
	}
	protected void agregarCofre(int w, int h) {
		cofre = new Cofre(w, h, false);
	}
	protected void agregarCofre(int w, int h, boolean especial) {
		cofre = new Cofre(w, h, especial);
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
		if (cofre != null) cofre.dispose();
		for(int i = 0; i<enemigos.size();i++) {
			enemigos.get(i).dispose();
		}
		for(int i = 0; i<objetos.size();i++) {
			objetos.get(i).dispose();
		}
	}

	public ArrayList<Enemigo> getEnemigos() {
		return enemigos;
	}

	public boolean isLimpia() {
		return enemigos.isEmpty();
	}
	public void revelarCofre() {
		if(cofre !=null) {
			cofre.revelar();
		}
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
	public abstract boolean revisarPortal(float x, float y);
	
	public Cofre getCofre() {
		return cofre;
	}
	public void soltarObjeto(float x, float y, Objeto objeto) {
	    if (objeto != null) objetos.add(new ObjetoTirado(x, y, objeto));
	}
	public ObjetoTirado revisarObjeto(Personaje jugador){
		for(ObjetoTirado objeto : objetos) {
			if(Colisiones.colisionaConEntidad(objeto.getHitbox(), jugador.getHitbox())) {
				return objeto;
			}
		}
		return null;
	}
	public void quitarObjeto(ObjetoTirado s){ 
		objetos.remove(s); 
	}
	public void dibujarSuelo(SpriteBatch batch) {
	    for (ObjetoTirado objeto : objetos) objeto.dibujar(batch);
	}
	public boolean hayCofre() {
		return cofre!=null;
	}
	public abstract void abrirPuerta();
	
}
