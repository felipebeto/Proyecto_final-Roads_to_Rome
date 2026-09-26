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

public class PantallaCampania extends PantallaJuego{
	
	public PantallaCampania(Main main, SpriteBatch batch, InputManager input) {
		super(main, batch, input);
	}
	@Override
	public void render(float delta) {
		super.render(delta);
		if(jugador.isMuerto()) {
			musica.detener();
			main.setScreen(new PantallaFinal(contador, Finales.DERROTA, main, batch, input));
		}
		if(mapa.isLimpia()) {
			musica.detener();
			main.setScreen(new PantallaFinal(contador, Finales.VICTORIA, main, batch, input));
		}
		
	}

}
