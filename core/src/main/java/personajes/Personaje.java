package personajes;

import com.badlogic.gdx.math.Rectangle;

import elementos.Imagen;
import util.Recursos;

public abstract class Personaje {
	protected float x, y;
    protected int vida;
    protected int velocidad;
    protected Rectangle hitbox;
    protected Imagen sprite;
    public Personaje(float x, float y, int vida, int velocidad, Imagen sprite, float ancho, float alto) {
        this.x = x; 
        this.y = y; 
        this.vida = vida; 
        this.velocidad = velocidad;
        this.sprite = sprite;
        this.hitbox = new Rectangle(x, y, ancho, alto);
    }
    public void dibujar() {
		sprite.setPosicion(x, y);
		sprite.dibujar();
	}
    public void recibirDanio(int cantidad) {
    	vida -= cantidad;
    	isMuerto();
    }
    private boolean isMuerto() {
    	if(vida>0) {
    		return false;
    	}else {
    		vida=0;
    		return true;
    	}
	}
	public abstract void calcularMovimiento(float delta);
	protected void revisarLimite() {
		if(x<0) x = 0;
		if(y<0) y = 0;
		if(x>Recursos.ancho-hitbox.width) x = Recursos.ancho-hitbox.width;
		if(y>Recursos.alto-hitbox.height) y = Recursos.alto-hitbox.height;
	}
}
