package pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.rtr.Main;

import Enums.Finales;
import elementos.Audio;
import elementos.BarraVida;
import elementos.Camara;
import elementos.Imagen;
import mapas.Dungeon1;
import mapas.Mapa;
import personajes.*;
import util.Colisiones;
import util.Recursos;
import util.Render;

public abstract class PantallaJuego implements Screen{
	
	protected Personaje jugador;
	protected Personaje enemigo;
	protected Dungeon1 mapa;
	protected Imagen rojo;
	protected Audio musica;
	protected Sound sonidoGolpe;
	protected Sound sonidoOof;
	protected Camara camara;	
	protected BarraVida barraVida;
	private boolean efectoDanio = false;
	protected float a = 0;
	private boolean animacionT = true;
	float porcentajeVida = 0;
	protected Main main;
	protected SpriteBatch batch;
	public PantallaJuego(Main main, SpriteBatch batch) {
		this.main = main;
		this.batch = batch;
	}

	@Override
	public void show() {
		jugador = new Jugador();
		enemigo = new Enemigo(500, 200);
		mapa = new Dungeon1();
		rojo = new Imagen("fondos/peligro.jfif");
		rojo.ajustarTamaño();
		rojo.setTrans(a);
		mapa.getFondo().ajustarTamaño();
		musica = new Audio(Recursos.MUSICA_JUEGO);
		sonidoGolpe = Gdx.audio.newSound(Gdx.files.internal(Recursos.SONIDO_GOLPE));
		sonidoOof = Gdx.audio.newSound(Gdx.files.internal(Recursos.SONIDO_OOF));
		camara = new Camara();
		barraVida  = new BarraVida();
	}

	@Override
	public abstract void render(float delta);

	protected boolean calcularRangoAtaque() {
		float centroJugadorX = jugador.getX() + jugador.getHitbox().width/2;
		float centroJugadorY = jugador.getY() + jugador.getHitbox().height/2;
		float centroEnemigoX = enemigo.getX() + enemigo.getHitbox().width/2;
		float centroEnemigoY = enemigo.getY() + enemigo.getHitbox().height/2;

		float distancia = Vector2.dst(centroJugadorX, centroJugadorY, centroEnemigoX, centroEnemigoY);
		if(distancia<=jugador.getAlcance()) return true;
		else return false;
	}

	protected void fadeDanio(Personaje jugador) {
		if (efectoDanio || !animacionT) {
			if(animacionT) {
				a=1;
				rojo.setTrans(a);
				rojo.dibujar(batch);
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