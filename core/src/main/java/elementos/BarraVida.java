package elementos;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import util.Recursos;

public class BarraVida {
	private ShapeRenderer barra = new ShapeRenderer();
	float barraX = 20, barraY = Recursos.alto - 40;
	float barraAncho = 200, barraAlto = 20;
	public void pintar(float porcentajeVida) {
		barra.begin(ShapeRenderer.ShapeType.Filled);
		barra.setColor(Color.DARK_GRAY);
		barra.rect(barraX, barraY, barraAncho, barraAlto);

		barra.setColor(Color.RED);
		barra.rect(barraX, barraY, barraAncho * porcentajeVida, barraAlto); 
		barra.end();
	}

}
