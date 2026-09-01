package elementos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import util.Render;

public class Imagen {
	private Sprite s;
	private Texture t;
	public Imagen(String ruta) {
		this.t = new Texture(ruta);
		this.s = new Sprite(t);
	}
	public void ajustarTamaño() {
		s.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}
	public void setTamanio(float w, float h) {
		s.setSize(w, h);
	}
	public void dibujar() {
		s.draw(Render.batch);
	}
	public void setTrans(float a) {
		s.setAlpha(a);
	}
	public void setPosicion(float x, float y) {
		s.setPosition(x, y);
	}

}
