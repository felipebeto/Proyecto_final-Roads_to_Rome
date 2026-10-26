package pantallas;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rtr.Main;

import Enums.Finales;
import mapas.Sala;
import mapas.SalaEscape;
import util.InputManager;

public class PantallaSalaFinal extends PantallaJuego{

	public PantallaSalaFinal(Main main, SpriteBatch batch, InputManager input, int cont) {
		super(main, batch, input);
		mapa = new SalaEscape();
		this.contador = cont;
	}
	@Override
	public void show() {
		super.show();
		jugador.setPosicion(500, 10);
	}
	@Override
	public void render(float delta) {
		super.render(delta);
		if(jugador.getY()>880) main.setScreen(new PantallaFinal(contador, Finales.VICTORIA, main, batch, input));
	}

}
