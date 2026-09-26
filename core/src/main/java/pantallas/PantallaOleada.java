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

	private int contador = 0;
	private int contRonda = 0;

	public PantallaOleada(Main main, SpriteBatch batch, InputManager input) {
		super(main, batch, input);
	}

	@Override
	public void render(float delta) {
		musica.comenzar();

		jugador.calcularMovimiento(delta, mapa, enemigo);
		for (Personaje e : mapa.getEnemigos()) {
			e.calcularMovimiento(delta, mapa, jugador);
		}

		Render.limpiar(0, 0, 0);
		camara.actualizarPosicion(jugador, batch);

		batch.begin();
		mapa.dibujarFondo(batch);
		for (Personaje e : mapa.getEnemigos()) {
			e.dibujar(batch);
		}

		jugador.dibujar(batch);

		for (Personaje e : mapa.getEnemigos()) {
			if (Colisiones.colisionaConEntidad(jugador.getHitbox(), e.getHitbox())) {
				sonidoOof.play();
				e.atacar(jugador);
			}
		}
		if (jugador.prepararAtaque()) {
			for (Enemigo e : mapa.getEnemigos()) {
				if (calcularRangoAtaque(e)) {
					sonidoGolpe.play();
					jugador.atacar(e);
					if (mapa.revisarMuerto(e)) {
						contador++;
						break;
					}
						
				}
			}
		}
		batch.end();
		porcentajeVida = (float) jugador.getVida() / 100;
		barraVida.pintar(porcentajeVida);
		if (jugador.isMuerto()) {
			musica.detener();
			main.setScreen(new PantallaFinal(contador, Finales.DERROTA, main, batch, input));
		}
		if (mapa.isLimpia()) {
			contRonda++;
			mapa.regenerar();

		}
	}

}