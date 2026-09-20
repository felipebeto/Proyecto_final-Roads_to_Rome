package pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rtr.Main;
import Enums.Finales;
import util.Colisiones;
import util.Render;

public class PantallaOleada extends PantallaJuego{
	
	private int contador = 0; 
	public PantallaOleada(Main main, SpriteBatch batch) {
		super(main, batch);
	}

	@Override
	public void render(float delta) {
		musica.comenzar();
		
		jugador.calcularMovimiento(delta, mapa, enemigo);
		enemigo.calcularMovimiento(delta, mapa, jugador);
		Render.limpiar(0, 0, 0);
		batch.begin();
		mapa.dibujarFondo(batch);
		enemigo.dibujar(batch);
		jugador.dibujar(batch);
		rojo.dibujar(batch);
		camara.actualizarPosicion(jugador, batch);
		fadeDanio(jugador);
		rojo.setTrans(a);
		
		if(Colisiones.colisionaConEntidad(jugador.getHitbox(), enemigo.getHitbox())) {
			sonidoOof.play();
			enemigo.atacar(jugador);
		}
		if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && calcularRangoAtaque()) {
			sonidoGolpe.play();
			jugador.atacar(enemigo);
		}
		batch.end();
		porcentajeVida = (float) jugador.getVida() / 100;
		barraVida.pintar(porcentajeVida);
		if(jugador.isMuerto()) {
			musica.detener();
			main.setScreen(new PantallaFinal(contador, Finales.DERROTA, main, batch));
		}
		if(enemigo.isMuerto()) {
			contador++;
			enemigo.dispose();
			enemigo = enemigo.aparecer(mapa);
		}
	}
}