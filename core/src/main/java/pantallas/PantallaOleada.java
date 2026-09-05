package pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;

import elementos.Audio;
import elementos.BarraVida;
import elementos.Camara;
import elementos.ContadorKills;
import elementos.Imagen;
import mapas.Dungeon1;
import personajes.Enemigo;
import personajes.Jugador;
import personajes.Personaje;
import util.Colisiones;
import util.Recursos;
import util.Render;

public class PantallaOleada implements Screen{
	
	private Personaje jugador;
	private Personaje enemigo;
	private Dungeon1 mapa;
	private Imagen rojo;
	private Audio musica;
	private Sound sonidoGolpe;
	private Sound sonidoOof;
	private Camara camara;	
	private BarraVida barraVida;
	private int contador = 0; 
	private boolean efectoDanio = false;
	private float a = 0;
	private boolean animacionT = true;
	float porcentajeVida = 0;
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
			sonidoOof.play();
			enemigo.atacar(jugador);
		}
		if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && calcularRangoAtaque()) {
			sonidoGolpe.play();
			jugador.atacar(enemigo);
		}
		Render.batch.end();
		porcentajeVida = (float) jugador.getVida() / 100;
		barraVida.pintar(porcentajeVida);
		if(jugador.isMuerto()) {
			musica.detener();
			Recursos.MAIN.setScreen(new PantallaGameOver(contador));
		}
		if(enemigo.isMuerto()) {
			contador++;
			enemigo.dispose();
			enemigo = enemigo.aparecer(mapa);
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