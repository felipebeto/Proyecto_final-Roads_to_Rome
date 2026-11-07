package elementos;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Texto {
	private BitmapFont fuente;
	private GlyphLayout layout;
	private Rectangle hitbox;
	private String msj;
	private String msj2;
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
	public void escribir(SpriteBatch batch) {
		fuente.draw(batch, msj, x, y);
	}
	public boolean isSobre(float mouseX, float mouseY) {
		return hitbox.contains(mouseX, mouseY);
	}
	public void setColor(Color color) {
		fuente.setColor(color);
	}
	public void dispose() {
		fuente.dispose();
	}
	
	public void setMensaje(String msj2) {
		this.msj2 = msj2;
		layout.setText(fuente, msj2);
		hitbox.set(x, y - layout.height, layout.width, layout.height);
	}
}