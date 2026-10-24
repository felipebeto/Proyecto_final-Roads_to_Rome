package pantallas;

import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.rtr.Main;

import Enums.Mejoras;
import datos.Perfil;
import elementos.Audio;
import elementos.Imagen;
import elementos.Texto;
import util.InputManager;
import util.Recursos;
import util.Render;

/** Menu donde se gasta el oro de la caja fuerte para mejorar vida, daño base y velocidad base. */
public class PantallaMejoras implements Screen {
	private Main main;
	private SpriteBatch batch;
	private InputManager input;

	private Imagen fondo;
	private Audio musica;
	private Sound sonidoClick;
	private Texto titulo;
	private Texto volver;
	private Texto oro;
	private Texto mensaje;
	private Texto[] filas = new Texto[Mejoras.values().length];
	private Texto[] botones = new Texto[Mejoras.values().length];
	private boolean[] enMaximo = new boolean[Mejoras.values().length];
	private String textoMensaje = " ";
	private float mouseX, mouseY;

	public PantallaMejoras(Main main, SpriteBatch batch, InputManager input) {
		this.main = main;
		this.batch = batch;
		this.input = input;
		Gdx.input.setInputProcessor(input);
	}

	@Override
	public void show() {
		int alto = Gdx.graphics.getHeight();
		batch.setProjectionMatrix(new Matrix4().setToOrtho2D(0, 0, Gdx.graphics.getWidth(), alto));
		fondo = new Imagen(Recursos.FONDO_MENU);
		fondo.ajustarTamaño();
		musica = new Audio(Recursos.MUSICA_MENU);
		musica.cambiarVolumen(1);
		sonidoClick = Gdx.audio.newSound(Gdx.files.internal(Recursos.SONIDO_CLICK));
		titulo = new Texto("Mejoras", 60, 600);
		titulo.agrandar(2);
		volver = new Texto("Volver", 60, 100);
		construirTextos();
	}

	// Texto no permite cambiar su mensaje, asi que despues de cada compra se vuelven a crear los textos
	private void construirTextos() {
		liberarTextos();
		int alto = Gdx.graphics.getHeight();
		int ancho = Gdx.graphics.getWidth();
		Map<String, Integer> niveles = Perfil.getMejoras();

		oro = new Texto("Oro: " + Perfil.getOroCajaFuerte(), 80, 550);
		mensaje = new Texto(textoMensaje, 60, 130);

		Mejoras[] lista = Mejoras.values();
		for (int i = 0; i < lista.length; i++) {
			Mejoras m = lista[i];
			int nivel = m.getNivel(niveles);
			enMaximo[i] = nivel >= Mejoras.NIVEL_MAXIMO;
			float y = alto - 200 - i * 80;

			String detalle = "Bonus actual: +" + m.getBonus(nivel) + m.getUnidad();
			if (!enMaximo[i]) detalle += "  (siguiente: +" + m.getBonus(nivel + 1) + ")";
			filas[i] = new Texto(m.getTitulo() + "  -  Nivel " + nivel + "/" + Mejoras.NIVEL_MAXIMO + "\n" + detalle, 60, y);
			botones[i] = new Texto(enMaximo[i] ? "Nivel maximo" : "Mejorar - " + m.getCosto(nivel) + " de oro", 600, y);
		}
	}

	private void liberarTextos() {
		if (oro != null) oro.dispose();
		if (mensaje != null) mensaje.dispose();
		for (int i = 0; i < filas.length; i++) {
			if (filas[i] != null) filas[i].dispose();
			if (botones[i] != null) botones[i].dispose();
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
		titulo.escribir(batch);
		oro.escribir(batch);
		for (int i = 0; i < filas.length; i++) {
			filas[i].escribir(batch);
			if (enMaximo[i]) botones[i].setColor(Color.GRAY);
			else botones[i].setColor(botones[i].isSobre(mouseX, mouseY) ? Color.RED : Color.WHITE);
			botones[i].escribir(batch);
		}
		mensaje.escribir(batch);
		volver.setColor(volver.isSobre(mouseX, mouseY) ? Color.RED : Color.WHITE);
		volver.escribir(batch);
		batch.end();

		// Se usa justTouched() (solo el frame en que se aprieta) y no input.isClick(), que queda en true
		// mientras el boton esta apretado: con isClick() mantener el click compraria varios niveles de golpe.
		// El click se procesa despues de batch.end() porque hide() libera las texturas al cambiar de pantalla.
		if (Gdx.input.justTouched()) procesarClick();
	}

	private void procesarClick() {
		if (volver.isSobre(mouseX, mouseY)) {
			sonidoClick.play();
			musica.detener();
			input.resetearClick();
			main.setScreen(new PantallaMenuPrincipal(main, batch, input));
			return;
		}
		Mejoras[] lista = Mejoras.values();
		for (int i = 0; i < lista.length; i++) {
			if (botones[i].isSobre(mouseX, mouseY)) {
				sonidoClick.play();
				comprar(lista[i]);
				return;
			}
		}
	}

	private void comprar(Mejoras m) {
		int nivel = m.getNivel(Perfil.getMejoras());
		int costo = m.getCosto(nivel);
		if (nivel >= Mejoras.NIVEL_MAXIMO) {
			textoMensaje = m.getTitulo() + " ya esta en el nivel maximo";
		} else if (Perfil.comprarMejora(m.getId(), costo)) {
			textoMensaje = "Mejoraste " + m.getTitulo() + " al nivel " + (nivel + 1);
		} else if (Perfil.getOroCajaFuerte() >= costo) {
			textoMensaje = "No se pudo guardar la mejora";
		} else {
			textoMensaje = "No te alcanza el oro (te faltan " + (costo - Perfil.getOroCajaFuerte()) + ")";
		}
		construirTextos();
	}

	@Override
	public void resize(int width, int height) {
		// Mantiene el hover del mouse alineado con lo que se dibuja si cambia el tamaño de la ventana
		batch.setProjectionMatrix(new Matrix4().setToOrtho2D(0, 0, width, height));
		if (fondo != null) fondo.ajustarTamaño();
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	// Game.setScreen() llama a hide() (no a dispose()) al cambiar de pantalla, asi que se libera aca.
	// sonidoClick no se libera: dispose() cortaria el sonido del click de "Volver" que recien se disparo.
	@Override
	public void hide() {
		fondo.dispose();
		musica.dispose();
		titulo.dispose();
		volver.dispose();
		liberarTextos();
	}

	@Override
	public void dispose() {
	}

}