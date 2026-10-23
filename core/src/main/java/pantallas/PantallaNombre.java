package pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rtr.Main;

import datos.Perfil;
import elementos.Imagen;
import elementos.Texto;
import util.InputManager;
import util.Recursos;
import util.Render;


public class PantallaNombre implements Screen {
	private static final int MAX_LETRAS = 12;

	private Main main;
	private SpriteBatch batch;
	private InputManager input;
	private Imagen fondo;
	private Texto titulo;
	private Texto ayuda;
	private BitmapFont fuente;
	private StringBuilder nombre = new StringBuilder();
	private boolean terminado = false;
	private float tiempo = 0;

	public PantallaNombre(Main main, SpriteBatch batch, InputManager input) {
		this.main = main;
		this.batch = batch;
		this.input = input;
	}

	@Override
	public void show() {
		fondo = new Imagen(Recursos.FONDO_MENU);
		fondo.ajustarTamaño();
		titulo = new Texto("Roads to Rome", 
				Gdx.graphics.getWidth() / 2 - 200, Gdx.graphics.getHeight() / 2 + 200);
		titulo.agrandar(4);
		ayuda = new Texto("Ingresa tu nombre y presiona enter", 
				Gdx.graphics.getWidth() / 2 - 120, Gdx.graphics.getHeight() / 2 + 100);
		fuente = new BitmapFont();
		fuente.getData().setScale(2);

		Gdx.input.setInputProcessor(new InputAdapter() {
			@Override
			public boolean keyTyped(char c) {
				if ((Character.isLetterOrDigit(c) || c == ' ') && nombre.length() < MAX_LETRAS) nombre.append(c);
				return true;
			}

			@Override
			public boolean keyDown(int keycode) {
				if (keycode == Input.Keys.BACKSPACE && nombre.length() > 0) nombre.deleteCharAt(nombre.length() - 1);
				if (keycode == Input.Keys.ENTER || keycode == Input.Keys.NUMPAD_ENTER) {
					String texto = nombre.toString().trim();
					if (!texto.isEmpty()) {
						Perfil.setNombre(texto);
						terminado = true;
					}
				}
				return true;
			}
		});
	}

	@Override
	public void render(float delta) {
		if (terminado) {
			main.setScreen(new PantallaMenuPrincipal(main, batch, input));
			return;
		}
		tiempo += delta;
		String cursor = ((int) (tiempo * 2) % 2 == 0) ? "_" : "";

		Render.limpiar(0, 0, 0);
		batch.begin();
		fondo.dibujar(batch);
		titulo.escribir(batch);
		ayuda.escribir(batch);
		fuente.draw(batch, nombre + cursor, Gdx.graphics.getWidth() / 2 - 120, Gdx.graphics.getHeight() / 2 + 50);
		batch.end();
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
		fondo.dispose();
		titulo.dispose();
		ayuda.dispose();
		fuente.dispose();
	}

	@Override
	public void dispose() {
	}

}
