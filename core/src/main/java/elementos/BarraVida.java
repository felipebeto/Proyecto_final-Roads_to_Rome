package elementos;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import util.Recursos;

public class BarraVida {
	private ShapeRenderer barra = new ShapeRenderer();
	float barraX = Recursos.ancho/2, barraY = Recursos.alto/2 + 65;
	float barraAncho = 50, barraAlto = 5;
	public void pintar(float porcentajeVida) {
		barra.begin(ShapeRenderer.ShapeType.Filled);
		barra.setColor(Color.DARK_GRAY);
		barra.rect(barraX, barraY, barraAncho, barraAlto);

		barra.setColor(Color.RED);
		barra.rect(barraX, barraY, barraAncho * porcentajeVida, barraAlto); 
		barra.end();
	}
	public void recalcularCoords() {
		this.barraX = Recursos.ancho/2;
		this.barraY = Recursos.alto/2 + 65;
	}

}
