package pantallas;

import com.badlogic.gdx.Screen;

import elementos.Audio;
import elementos.Imagen;
import mapas.Dungeon1;
import mapas.Mapa;
import personajes.*;
import util.Colisiones;
import util.Recursos;
import util.Render;

public class PantallaJuego implements Screen{
	
	private Personaje jugador;
	private Personaje enemigo;
	private Dungeon1 mapa;
	private Imagen rojo;
	private Audio musica;
	private boolean efectoDanio = false;
	private float a = 0;
	private boolean animacionT = true;
	@Override
	public void show() {
		jugador = new Jugador();
		enemigo = new Enemigo();
		mapa = new Dungeon1();
		rojo = new Imagen("fondos/peligro.jfif");
		rojo.ajustarTamaño();
		rojo.setTrans(a);
		mapa.getFondo().ajustarTamaño();
		musica = new Audio(Recursos.MUSICA_JUEGO);
	}

	@Override
	public void render(float delta) {
		musica.comenzar();
		jugador.calcularMovimiento(delta, mapa, enemigo);
		Render.limpiar(0, 0, 0);
		Render.batch.begin();
		mapa.dibujarFondo();
		enemigo.dibujar();
		jugador.dibujar();
		rojo.dibujar();
		
		fadeDanio(jugador);
		rojo.setTrans(a);
		
		if(Colisiones.colisionaConEntidad(jugador.getHitbox(), enemigo.getHitbox())) {
			efectoDanio = jugador.recibirDanio(5);
		}
		Render.batch.end();
		if(jugador.isMuerto()) {
			musica.detener();
			Recursos.MAIN.setScreen(new PantallaGameOver());
		}
	}

	private void fadeDanio(Personaje jugador) {
		if (efectoDanio || !animacionT) {
			if(animacionT) {
				a=1;
				rojo.setTrans(a);
				rojo.dibujar();
			}
			a -= 0.05f;
			animacionT = false;
			if (a < 0) {
				efectoDanio = false;
				a = 0;
				animacionT = true;
			}
		}

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
