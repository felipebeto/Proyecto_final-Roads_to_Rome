package personajes;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import Enums.Direccion;
import elementos.Imagen;
import mapas.Sala;
import util.Colisiones;
import util.Recursos;

public abstract class Personaje {
    protected float x, y;
    protected int vida;
    protected int velocidad;
    protected Rectangle hitbox;
    protected Imagen sprite;
    protected int alcance;
    protected float cooldownDanio = 0;

    protected boolean isRetrocediendo = false;
    protected float origenRetroX, origenRetroY, destinoRetroX, destinoRetroY;
    protected float tiempoRetroceso = 0;
    protected final float DURACION_RETROCESO = 0.15f;
    protected final float DISTANCIA_RETROCESO = 100;

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

    public void dibujar(SpriteBatch batch) {
    	if(vida>0) {
    		sprite.setPosicion(x, y);
    		sprite.dibujar(batch);
    	}
        
    }

    public abstract boolean recibirDanio(int cantidad);

    public boolean isMuerto() {
        if (vida > 0) return false;
        vida = 0;
        return true;
    }

    public abstract void calcularMovimiento(float delta, Sala mapa, Personaje p);
    public abstract void atacar(Personaje p);

    public boolean revisarLimite() {
        if (x == 0) return true;
        if (y == 0) return true;
        if (x == Recursos.ANCHO_MAPA - hitbox.width) return true;
        if (y == Recursos.ALTO_MAPA - hitbox.height) return true;
        return false;
    }
    protected void corregirLimite() {
    	if (x < 0) x = 0;
        if (y < 0) y = 0;
        if (x > Recursos.ANCHO_MAPA - hitbox.width) x = Recursos.ANCHO_MAPA - hitbox.width;
        if (y > Recursos.ALTO_MAPA - hitbox.height) y = Recursos.ALTO_MAPA - hitbox.height;
    }

    protected void revisarHitbox(float nuevaX, float nuevaY, Sala mapa) {
        this.hitbox.setPosition(nuevaX, y);
        if (!Colisiones.colisionaConAlguno(hitbox, mapa.getObstaculos())) x = nuevaX;

        this.hitbox.setPosition(x, nuevaY);
        if (!Colisiones.colisionaConAlguno(hitbox, mapa.getObstaculos())) y = nuevaY;
        corregirLimite();
        this.hitbox.setPosition(x, y);
    }

    
    public void iniciarRetroceso(Personaje atacante) {
        float direccionX = this.x - atacante.getX();
        float direccionY = this.y - atacante.getY();
        float longitud = (float) Math.sqrt(direccionX * direccionX + direccionY * direccionY);
        if (longitud < 0.01f) {
        	direccionX = 1; direccionY = 0; longitud = 1; 
        }

        this.origenRetroX = this.x;
        this.origenRetroY = this.y;
        this.destinoRetroX = this.x + (direccionX / longitud) * DISTANCIA_RETROCESO;
        this.destinoRetroY = this.y + (direccionY / longitud) * DISTANCIA_RETROCESO;
        this.tiempoRetroceso = 0;
        this.isRetrocediendo = true;
    }

    protected float[] actualizarRetroceso(float delta) {
        tiempoRetroceso += delta;
        float progreso = Math.min(tiempoRetroceso / DURACION_RETROCESO, 1f);
        float nuevaX = origenRetroX + (destinoRetroX - origenRetroX) * progreso;
        float nuevaY = origenRetroY + (destinoRetroY - origenRetroY) * progreso;
        if (progreso >= 1f) {
        	isRetrocediendo = false;
        }
        return new float[]{nuevaX, nuevaY};
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
    public void dispose() {
    	sprite.dispose();
    	
    }


	public abstract boolean prepararAtaque();

	public boolean avanzarSala(Sala mapa) {
		if (x == 0) {
			this.x = 700;
			if(mapa.dEntrada == Direccion.IZQUIERDA) return false;
		}
        if (y == 0) {
        	this.y = 800;
        	if(mapa.dEntrada == Direccion.ABAJO) return false;
        }
        if (x == Recursos.ANCHO_MAPA - hitbox.width) {
        	this.x = 200;
        	if(mapa.dEntrada == Direccion.DERECHA) return false;
        }
        if (y == Recursos.ALTO_MAPA - hitbox.height) {
        	this.y = 200;
        	if(mapa.dEntrada == Direccion.ARRIBA) return false;
        }
        return true;
        
	}

}