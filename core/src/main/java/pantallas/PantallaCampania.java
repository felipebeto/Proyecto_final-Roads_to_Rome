package pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rtr.Main;

import Enums.Finales;
import util.Colisiones;
import util.InputManager;
import util.Render;

public class PantallaCampania extends PantallaJuego{
	
	public PantallaCampania(Main main, SpriteBatch batch, InputManager input) {
		super(main, batch, input);
	}
	@Override
	public void render(float delta) {
		musica.comenzar();
		jugador.calcularMovimiento(delta, mapa, enemigo);
		enemigo.calcularMovimiento(delta, mapa, jugador);
		Render.limpiar(0, 0, 0);
		camara.actualizarPosicion(jugador, batch);
		
		batch.begin();
		mapa.dibujarFondo(batch);
		enemigo.dibujar(batch);
		jugador.dibujar(batch);
		rojo.dibujar(batch);
		rojo.setTrans(a);
		
		if(Colisiones.colisionaConEntidad(jugador.getHitbox(), enemigo.getHitbox())) {
			sonidoOof.play();
			enemigo.atacar(jugador);
		}
		if(jugador.prepararAtaque() && calcularRangoAtaque(enemigo)) {
			sonidoGolpe.play();
			jugador.atacar(enemigo);
		}
		batch.end();
		porcentajeVida = (float) jugador.getVida() / 100;
		barraVida.pintar(porcentajeVida);
		if(jugador.isMuerto()) {
			musica.detener();
			main.setScreen(new PantallaFinal(0, Finales.DERROTA, main, batch, input));
		}
		if(enemigo.isMuerto()) {
			musica.detener();
			main.setScreen(new PantallaFinal(1, Finales.VICTORIA, main, batch, input));
		}
		
	}

}
