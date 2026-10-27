package mapas;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import items.Objeto;

public class ObjetoTirado {
	float x, y;
	Objeto objeto;
	Rectangle hitbox;
	public ObjetoTirado(float x, float y, Objeto objeto) {
		this.x = x;
		this.y = y;
		this.objeto = objeto;
		hitbox = new Rectangle(x, y, 40, 40);
	}
	public Rectangle getHitbox() {
		
		return hitbox;
	}
	public void dibujar(SpriteBatch batch) {
		objeto.getImagen().setPosicion(x, y);
        objeto.getImagen().dibujar(batch);
	}
	public Objeto getObjeto() {
		
		return objeto;
	}
	public void dispose() {
		objeto.dispose();
	}

}
