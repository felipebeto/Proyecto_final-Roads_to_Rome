package pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rtr.Main;

import elementos.Audio;
import elementos.Camara;
import elementos.Imagen;
import elementos.Texto;
import util.InputManager;
import util.Recursos;
import util.Render;
import datos.Perfil;

public class PantallaMenuPrincipal implements Screen{
	private InputManager input;
	private Imagen fondo;
	private Texto nombreJugador;
	private Texto oroJugador;
	private Texto killsJugador;
	private Texto titulo;
	private Texto nivelMaxAlcanzado;
	private Texto subtitulo1;
	private Texto subtitulo2;
	private Texto subtitulo3;
	private Texto subtitulo4;
	private boolean finFadeIn = false;
	private float a = 0;
	private float mouseX, mouseY;
	private Audio musica;
	private Sound sonidoClick;
	private Main main;
	private SpriteBatch batch;
	private Camara camara;

	public PantallaMenuPrincipal(Main main, SpriteBatch batch, InputManager input) {
		this.main = main;
		this.batch = batch;
		this.input = input;
		Gdx.input.setInputProcessor(input);
	}
	@Override
	public void show() {
		fondo = new Imagen(Recursos.FONDO_MENU);
		fondo.setTrans(a);
		fondo.ajustarTamaño();
		titulo = new Texto("Roads to Rome ", Gdx.graphics.getWidth() / 2 - 200, Gdx.graphics.getHeight() / 2 + 200);
		titulo.agrandar(4);
		nombreJugador = new Texto("Jugador: " + Perfil.getNombre(), 60, 300);
		oroJugador = new Texto("Oro en la caja fuerte: " + Perfil.getOroCajaFuerte(), 60, 250);
		killsJugador = new Texto("La cantidad de enemigos asesinados es: " + Perfil.getEnemigosTotales(), 60, 200);
		nivelMaxAlcanzado = new Texto("El nivel maximo alcanzado fue: " + Perfil.getNivelMaximo(), 60, 150);
		subtitulo1 = new Texto("Nueva partida", 400, 300);
		subtitulo2 = new Texto("Modo oleada", 400, 250);
		subtitulo3 = new Texto("Mejoras", 400, 200);
		subtitulo4 = new Texto("Salir del juego", 400, 150);
		musica = new Audio(Recursos.MUSICA_MENU);
		musica.cambiarVolumen(1);
		sonidoClick = Gdx.audio.newSound(Gdx.files.internal(Recursos.SONIDO_CLICK));
		camara = new Camara();
		
	}
	private void calcularFade() {
		if(!finFadeIn ) {
			a+= 0.005f;
			if (a>1) {
				finFadeIn = true;
				a =1;
			}
		}
	}

	@Override
	public void render(float delta) {
		mouseX = Gdx.input.getX();
	    mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();
		Render.limpiar(0, 0, 0);
		musica.comenzar();
		batch.begin();
		fondo.dibujar(batch);
		if (!finFadeIn) {
			calcularFade();
			fondo.setTrans(a);
		}else {
			titulo.escribir(batch);
			nombreJugador.escribir(batch);
			oroJugador.escribir(batch);
			killsJugador.escribir(batch);
			nivelMaxAlcanzado.escribir(batch);
			subtitulo1.setColor(subtitulo1.isSobre(mouseX, mouseY) ? Color.RED : Color.WHITE);
			subtitulo1.escribir(batch);
			subtitulo2.setColor(subtitulo2.isSobre(mouseX, mouseY) ? Color.RED : Color.WHITE);
			subtitulo2.escribir(batch);
			subtitulo3.setColor(subtitulo3.isSobre(mouseX, mouseY) ? Color.RED : Color.WHITE);
			subtitulo3.escribir(batch);
			subtitulo4.setColor(subtitulo4.isSobre(mouseX, mouseY) ? Color.RED : Color.WHITE);
			subtitulo4.escribir(batch);
		}
		if(input.isClick()) {
			if(subtitulo1.isSobre(mouseX, mouseY)) {
				sonidoClick.play();
				musica.detener();
				main.setScreen(new PantallaCampania(main, batch, input));
			}
			if(subtitulo2.isSobre(mouseX, mouseY)) {
					sonidoClick.play();
					musica.detener();
					main.setScreen(new PantallaOleada(main, batch, input));
			}
			if(subtitulo3.isSobre(mouseX, mouseY)) {
				sonidoClick.play();
				musica.detener();
				main.setScreen(new PantallaMejoras(main, batch, input));
			}
			if(subtitulo4.isSobre(mouseX, mouseY)) {
			    musica.detener();
			    Gdx.app.exit();
			}
		}
		batch.end();
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
		fondo.dispose();
		titulo.dispose();
		nombreJugador.dispose();
		oroJugador.dispose();
		killsJugador.dispose();
		nivelMaxAlcanzado.dispose();
		subtitulo1.dispose();
		subtitulo2.dispose();
		subtitulo3.dispose();
		subtitulo4.dispose();
		musica.dispose();
		sonidoClick.dispose();
	}

}