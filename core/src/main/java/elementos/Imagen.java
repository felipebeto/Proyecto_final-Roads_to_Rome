package elementos;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import util.Render;

public class Imagen {
	private Texture txt;
	private Sprite spr;
	public Imagen(String ruta, float x, float y, int ancho, int alto) {
		this.txt = new Texture(ruta);
		this.spr = new Sprite(txt);
		this.spr.setPosition(x, y);
		this.spr.setSize(ancho, alto);
	}
	public Imagen(String ruta) {
		this.txt = new Texture(ruta);
		this.spr = new Sprite(txt);
	}
	public void dibujar() {
		spr.draw(Render.batch);
	}
	public void setTrans(float a) {
		spr.setAlpha(a);
		
	}

}
