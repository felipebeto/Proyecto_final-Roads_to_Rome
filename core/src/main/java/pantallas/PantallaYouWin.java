package pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Matrix4;

import elementos.Audio;
import elementos.Camara;
import elementos.Imagen;
import elementos.Texto;
import util.Recursos;
import util.Render;

public class PantallaYouWin implements Screen{

	private Imagen fondo;
	private Texto titulo;
	private Texto subtitulo;
	private Audio audio;
	private boolean finFadeIn = false;
	private float a = 0;
	@Override
	public void show() {
		fondo = new Imagen(Recursos.YOUWIN);
		fondo.setTrans(a);
		fondo.ajustarTamaño();
		titulo = new Texto("Ganaste!!!", 340, 450);
		titulo.agrandar(2);
		subtitulo = new Texto("clickea la pantalla para jugar otra vez", 60, 350);
		Render.batch.setProjectionMatrix(new Matrix4().setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
		audio = new Audio(Recursos.MUSICA_YOUWIN);
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
		audio.comenzar();
		Render.limpiar(0, 0, 0);
		Render.batch.begin();
		fondo.dibujar();
		if (!finFadeIn) {
			calcularFade();
			fondo.setTrans(a);
		}else {
			titulo.escribir();
			subtitulo.escribir();
		}
		if(Gdx.input.justTouched()) {
			audio.detener();
			Recursos.MAIN.setScreen(new PantallaMenuPrincipal());
		}
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