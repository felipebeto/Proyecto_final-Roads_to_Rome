package pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import elementos.Audio;
import elementos.Imagen;
import elementos.Texto;
import util.Recursos;
import util.Render;

public class PantallaGameOver implements Screen{
	
	private Imagen fondo;
	private Texto titulo;
	private Texto subtitulo;
	private boolean finFadeIn = false;
	private float a = 0;
	private Audio musica;
	@Override
	public void show() {
		fondo = new Imagen(Recursos.GAMEOVER);
		fondo.setTrans(a);
		fondo.ajustarTamaño();
		titulo = new Texto("Game Over", 40, 450);
		titulo.agrandar(2);
		subtitulo = new Texto("clickea la pantalla para reintentar", 60, 350);
		musica = new Audio(Recursos.MUSICA_GAMEOVER);
		musica.cambiarVolumen(1);
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
		Render.limpiar(0, 0, 0);
		musica.comenzar();
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
			musica.detener();
			Recursos.MAIN.setScreen(new PantallaJuego());
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
