package pantallas;

import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.rtr.Main;

import datos.Perfil;
import elementos.Audio;
import elementos.Imagen;
import elementos.Texto;
import enums.Mejoras;
import util.InputManager;
import util.Recursos;
import util.Render;

public class PantallaTutorial implements Screen {
	private Main main;
	private SpriteBatch batch;
	private InputManager input;

	private Imagen fondo;
	private Audio musica;
	private Sound sonidoClick;
	private Texto volver;
	private float mouseX, mouseY;

	public PantallaTutorial(Main main, SpriteBatch batch, InputManager input) {
		this.main = main;
		this.batch = batch;
		this.input = input;
		Gdx.input.setInputProcessor(input);
	}

	@Override
	public void show() {
		
		fondo = new Imagen(Recursos.FONDO_TUTORIAL);
		fondo.ajustarTamaño();
		musica = new Audio(Recursos.MUSICA_MENU);
		musica.cambiarVolumen(1);
		sonidoClick = Gdx.audio.newSound(Gdx.files.internal(Recursos.SONIDO_CLICK));
		volver = new Texto("Volver", 60, 100);
		batch.setProjectionMatrix(new Matrix4().setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
	}


	@Override
	public void render(float delta) {
		mouseX = Gdx.input.getX();
		mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();
		Render.limpiar(0, 0, 0);
		musica.comenzar();

		batch.begin();
		fondo.dibujar(batch);
		volver.setColor(volver.isSobre(mouseX, mouseY) ? Color.RED : Color.WHITE);
		volver.escribir(batch);
		batch.end();
		if (input.isClick())
			if (volver.isSobre(mouseX, mouseY)) {
				sonidoClick.play();
				musica.detener();
				input.resetearClick();
				main.setScreen(new PantallaMenuPrincipal(main, batch, input));
			}
	}

	@Override
	public void resize(int width, int height) {
		batch.setProjectionMatrix(new Matrix4().setToOrtho2D(0, 0, width, height));
		if (fondo != null)
			fondo.ajustarTamaño();
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
	@Override
	public void hide() {
		fondo.dispose();
		musica.dispose();
		volver.dispose();
	}

	@Override
	public void dispose() {
	}

}
