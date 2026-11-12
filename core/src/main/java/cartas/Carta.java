package cartas;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Carta {
	private Modificador modif1;
	private Modificador modif2;
	public Carta(Modificador modif1, Modificador modif2) {
		this.modif1 = modif1;
		this.modif2 = modif2;
	}
	public void activar() {
		modif1.activar();
		modif2.activar();
	}
	public void dibujar(SpriteBatch batch) {
		
	}
	

}
