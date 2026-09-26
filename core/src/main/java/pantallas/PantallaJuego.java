package pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.rtr.Main;

import Enums.Finales;
import elementos.Audio;
import elementos.BarraVida;
import elementos.Camara;
import elementos.Imagen;
import mapas.Dungeon1;
import mapas.*;
import personajes.*;
import util.Colisiones;
import util.InputManager;
import util.Recursos;
import util.Render;

public abstract class PantallaJuego implements Screen{
	protected Personaje jugador;
	protected SalaLR mapa;
	protected Imagen rojo;
	protected Audio musica;
	protected Sound sonidoGolpe;
	protected Sound sonidoOof;
	protected Camara camara;	
	protected BarraVida barraVida;
	protected float a = 0;
	protected float porcentajeVida = 0;
	protected Main main;
	protected SpriteBatch batch;
	protected InputManager input;
	protected int contador;
	public PantallaJuego(Main main, SpriteBatch batch, InputManager input) {
		this.main = main;
		this.batch = batch;
		this.input = input;
		Gdx.input.setInputProcessor(input);
		input.resetearClick();
	}

	@Override
	public void show() {
		jugador = new Jugador();
		mapa = new SalaLR();
		rojo = new Imagen("fondos/peligro.jfif");
		rojo.ajustarTamaño();
		rojo.setTrans(a);
		musica = new Audio(Recursos.MUSICA_JUEGO);
		sonidoGolpe = Gdx.audio.newSound(Gdx.files.internal(Recursos.SONIDO_GOLPE));
		sonidoOof = Gdx.audio.newSound(Gdx.files.internal(Recursos.SONIDO_OOF));
		camara = new Camara();
		barraVida  = new BarraVida();
		
	}

	@Override
	public void render(float delta) {
		musica.comenzar();
		jugador.calcularMovimiento(delta, mapa, jugador);
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
		
	}

	protected boolean calcularRangoAtaque(Personaje e) {
		float centroJugadorX = jugador.getX() + jugador.getHitbox().width/2;
		float centroJugadorY = jugador.getY() + jugador.getHitbox().height/2;
		float centroEnemigoX = e.getX() + e.getHitbox().width/2;
		float centroEnemigoY = e.getY() + e.getHitbox().height/2;

		float distancia = Vector2.dst(centroJugadorX, centroJugadorY, centroEnemigoX, centroEnemigoY);
		if(distancia<=jugador.getAlcance()) return true;
		else return false;
	}

	@Override
	public void resize(int width, int height) {
		camara.actualizarPantalla(width, height);
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
		jugador.dispose();
		mapa.dispose();
		rojo.dispose();
		musica.dispose();
		sonidoGolpe.dispose();
		sonidoOof.dispose();
	}

}