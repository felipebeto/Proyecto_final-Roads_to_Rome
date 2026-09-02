package pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector2;

import elementos.Audio;
import elementos.Camara;
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
	private Camara camara;	
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
		camara = new Camara();
	}

	@Override
	public void render(float delta) {
		musica.comenzar();
		jugador.calcularMovimiento(delta, mapa, enemigo);
		enemigo.calcularMovimiento(delta, mapa, jugador);
		Render.limpiar(0, 0, 0);
		Render.batch.begin();
		mapa.dibujarFondo();
		enemigo.dibujar();
		jugador.dibujar();
		rojo.dibujar();
		camara.actualizarPosicion(jugador);
		fadeDanio(jugador);
		rojo.setTrans(a);
		
		if(Colisiones.colisionaConEntidad(jugador.getHitbox(), enemigo.getHitbox())) {
			efectoDanio = jugador.recibirDanio(10);
			System.out.println("vida tuya: " + jugador.getVida());
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.P) && calcularRangoAtaque()) {
			enemigo.recibirDanio(10);
			System.out.println("vida enemigo: " + enemigo.getVida());
		}
		Render.batch.end();
		if(jugador.isMuerto()) {
			musica.detener();
			Recursos.MAIN.setScreen(new PantallaGameOver());
		}
		if(enemigo.isMuerto()) {
			musica.detener();
			Recursos.MAIN.setScreen(new PantallaYouWin());
		}
	}

	private boolean calcularRangoAtaque() {
		float centroJugadorX = jugador.getX() + jugador.getHitbox().width/2;
		float centroJugadorY = jugador.getY() + jugador.getHitbox().height/2;
		float centroEnemigoX = enemigo.getX() + enemigo.getHitbox().width/2;
		float centroEnemigoY = enemigo.getY() + enemigo.getHitbox().height/2;

		float distancia = Vector2.dst(centroJugadorX, centroJugadorY, centroEnemigoX, centroEnemigoY);
		if(distancia<=jugador.getAlcance()) return true;
		else return false;
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
		camara.actualizarPantalla();
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
