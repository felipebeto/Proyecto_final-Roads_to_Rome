package mapas;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import elementos.Imagen;
import items.Objeto;
import items.*;
import util.Aleatorio;

public class Cofre {
	private Imagen imagenC =new Imagen("cofre.jpg");
	private Imagen imagenA= new Imagen("cofrea.jpg");
	private Rectangle hitbox;
	private int ancho = 60, alto = 40;
	private float x;
	private float y;
	private Objeto contenido;
	private boolean abierto = false;
	private boolean visible = false;
	public Cofre(float x, float y) {
		this.x = x;
		this.y = y;
		this.contenido = elegirContenido();
		this.hitbox = new Rectangle(x, y, ancho, alto);
		imagenC.setTamanio(ancho, alto);
		imagenA.setTamanio(ancho, alto);
	}
	private Objeto elegirContenido() {
		int opc = Aleatorio.generarEntero(4);
		switch (opc) {
		case 0:
			return new Pocion();
		case 1:
			return new Lanza();
		case 2:
			return new Daga();
		case 3:
			return new Espada();
		default:
			return new Daga();
		}
	}
	public void revelar() {
		visible = true;
	}
	public void abrir() {
		abierto = true;
	}
	public void dibujar(SpriteBatch batch) {
    	if(visible) {
    		if(abierto) {
    			imagenA.setPosicion(x, y);
    			imagenA.dibujar(batch);
    		}
    		else {
    			imagenC.setPosicion(x, y);
    			imagenC.dibujar(batch);
    		}
    	}
        
    }
	public Rectangle getHitbox() {
		return hitbox;
	}

}
