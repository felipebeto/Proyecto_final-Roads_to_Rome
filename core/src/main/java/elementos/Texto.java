package elementos;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Rectangle;

import util.Render;

public class Texto {
	private BitmapFont fuente;
	private GlyphLayout layout;
	private Rectangle hitbox;
	private String msj;
	private float x, y;

	public Texto(String msj, float x, float y) {
		this.msj = msj;
		this.x = x;
		this.y = y;
		this.fuente = new BitmapFont();
		this.layout = new GlyphLayout();
		layout.setText(fuente, msj);
		hitbox = new Rectangle(x, y - layout.height, layout.width, layout.height);
	}
	public void agrandar(float num) {
		fuente.getData().setScale(num);
	}
	public void cambiarMsj(String nuevo) {
		msj = nuevo;
	}
	public void escribir() {
		fuente.draw(Render.batch, msj, x, y);
	}
	public boolean isHover(float mouseX, float mouseY) {
		return hitbox.contains(mouseX, mouseY);
	}
	public void setColor(Color color) {
		fuente.setColor(color);
	}
}