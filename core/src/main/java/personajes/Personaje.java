package personajes;

import com.badlogic.gdx.math.Rectangle;

import elementos.Imagen;
import mapas.Mapa;
import util.Colisiones;
import util.Recursos;

public abstract class Personaje {
	protected float x, y;
    protected int vida;
    protected int velocidad;
    protected Rectangle hitbox;
    protected Imagen sprite;
    protected float cooldownDanio = 0;
    protected int alcance;
    public Personaje(float x, float y, int vida, int velocidad, String ruta, float ancho, float alto, int alcance) {
        this.x = x; 
        this.y = y; 
        this.vida = vida; 
        this.velocidad = velocidad;
        this.sprite = new Imagen(ruta);
        sprite.setTamanio(ancho, alto);
        this.hitbox = new Rectangle(x, y, ancho, alto);
        this.alcance = alcance;
    }
    public void dibujar() {
		sprite.setPosicion(x, y);
		sprite.dibujar();
	}
    public abstract boolean recibirDanio(int cantidad);
    public boolean isMuerto() {
    	if(vida>0) {
    		return false;
    	}else {
    		vida=0;
    		return true;
    	}
	}
	public abstract void calcularMovimiento(float delta, Mapa mapa, Personaje p);
	public abstract void atacar(Personaje p);
	protected void revisarLimite() {
		if(x<0) x = 0;
		if(y<0) y = 0;
		if(x>Recursos.ancho-hitbox.width) x = Recursos.ancho-hitbox.width;
		if(y>Recursos.alto-hitbox.height) y = Recursos.alto-hitbox.height;
	}
	protected void revisarHitbox(float nuevaX, float nuevaY, Mapa mapa) {
		this.hitbox.setPosition(nuevaX, y);
        if (!Colisiones.colisionaConAlguno(hitbox, mapa.getObstaculos())) x = nuevaX;	
        	

        this.hitbox.setPosition(x, nuevaY);
        if (!Colisiones.colisionaConAlguno(hitbox, mapa.getObstaculos())) y = nuevaY;
		revisarLimite();
        this.hitbox.setPosition(x, y);
	}
	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}
	public boolean colisionar(Rectangle area) {
		return Colisiones.colisionaConEntidad(area, hitbox);
	}
	public Rectangle getHitbox() {
		return hitbox;
	}
	public float getCooldown() {
		return cooldownDanio;
	}
	public int getAlcance() {
		return alcance;
	}
	public int getVida() {
		return vida;
	}
}
