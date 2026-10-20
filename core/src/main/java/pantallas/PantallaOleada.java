package pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rtr.Main;
import Enums.Finales;
import personajes.Enemigo;
import personajes.Personaje;
import util.Colisiones;
import util.InputManager;
import util.Render;
import java.util.ArrayList;

public class PantallaOleada extends PantallaJuego {
	
	private int contRonda = 0;

	public PantallaOleada(Main main, SpriteBatch batch, InputManager input) {
		super(main, batch, input);
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		if (jugador.isMuerto()) {
			musica.detener();
			main.setScreen(new PantallaFinal(contador, contRonda, Finales.DERROTA, main, batch, input));
		}
		if (mapa.isLimpia()) {
			contRonda++;
			mapa.regenerar();

		}
	}

}