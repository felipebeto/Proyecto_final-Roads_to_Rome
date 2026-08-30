package Pantallas;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.rtr.Main;

import elementos.Fotos;
import elementos.Texto;
import util.Recursos;
import util.Render;

public class PantallaMenuPrincipal implements Screen{
	
	private Fotos fondo;
	private Texto titulo;
	private Texto subtitulo;
	private boolean finFadeIn = false;
	private float a = 0;

	@Override
	public void show() {
		fondo = new Fotos(Recursos.FONDO_MENU);
		fondo.setTrans(a);
		fondo.ajustarTamaño();
		titulo = new Texto();
		titulo.agrandar(2);
		subtitulo = new Texto();
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
		
		Render.batch.begin();
		fondo.dibujar();
		if (!finFadeIn) {
			calcularFade();
			fondo.setTrans(a);
		}else {
			titulo.escribir("Roads to Rome ", 40, 450);
			subtitulo.escribir("Nueva partida", 60, 300);
			subtitulo.escribir("Continuar", 60, 250);
			subtitulo.escribir("Logros", 60, 200);
			subtitulo.escribir("Opciones", 60, 150);
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
