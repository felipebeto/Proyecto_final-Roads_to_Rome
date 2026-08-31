package Pantallas;

import com.badlogic.gdx.Screen;

import elementos.Audio;
import elementos.Imagen;
import personajes.Jugador;
import util.Recursos;
import util.Render;

public class PantallaJuego implements Screen{
	
	private Jugador jugador;
	private Imagen escenario;
	private Audio musica;
	@Override
	public void show() {
		jugador = new Jugador();
		escenario = new Imagen("fondos/escenarioBase.jpg");
		escenario.ajustarTamaño();
		musica = new Audio(Recursos.MUSICA_JUEGO);
	}

	@Override
	public void render(float delta) {
		musica.comenzar();
		jugador.calcularMovimiento(delta);
		Render.limpiar(0, 0, 0);
		Render.batch.begin();
		escenario.dibujar();
		jugador.dibujar();
		Render.batch.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void dispose() {
	}

}
