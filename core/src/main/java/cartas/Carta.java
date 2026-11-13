package cartas;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import enums.Modificadores;
import util.Recursos;

public class Carta {
	private Modificadores modif1;
	private Modificadores modif2;
	private Rectangle hitbox;
	private int x;
	private int y = Recursos.alto/2;
	public Carta(Modificadores modif1, Modificadores modif2) {
		this.modif1 = modif1;
		this.modif2 = modif2;
		this.hitbox = new Rectangle(x, y, 100, 100);
	}
	public void activar() {
		modif1.activar();
		modif2.activar();
	}
	public void dibujar(SpriteBatch batch, int posicion) {
		if(posicion==1) {
			this.x = 100;
			hitbox.setPosition(x, y);
			modif1.dibujar(batch);
			modif2.dibujar(batch);
		}else {
			if(posicion ==2) {
				this.x = 200;
				hitbox.setPosition(x, y);
				modif1.dibujar(batch);
				modif2.dibujar(batch);
			}else {
				this.x = 300;
				hitbox.setPosition(x, y);
				modif1.dibujar(batch);
				modif2.dibujar(batch);
			}
		}
		
	}
	public boolean isSobre(float mouseX, float mouseY) {
		return hitbox.contains(mouseX, mouseY);
	}
	public Modificadores getModif1() {
		return modif1;
	}
	public Modificadores getModif2() {
		return modif2;
	}
	

}
