package elementos;

import com.badlogic.gdx.graphics.g2d.BitmapFont;

import util.Render;

public class Texto {
	private BitmapFont fuente;

	public Texto() {
		this.fuente = new BitmapFont();
	}
	public void agrandar(float num) {
		fuente.getData().setScale(num);
	}
	public void escribir(String msj, float x, float y) {
		fuente.draw(Render.batch, msj, x, y);
	}
}
