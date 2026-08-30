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

	@Override
	public void show() {
		fondo = new Fotos(Recursos.FONDO_MENU);
		fondo.ajustarTamaño();
		titulo = new Texto();
		titulo.agrandar(2);
		subtitulo = new Texto();
	}

	@Override
	public void render(float delta) {
		Render.limpiar(1, 1, 1);
		
		Render.batch.begin();
		fondo.dibujar();
		titulo.escribir("Roads to Rome ", 40, 450);
		subtitulo.escribir("Nueva partida", 60, 300);
		subtitulo.escribir("Continuar", 60, 250);
		subtitulo.escribir("Logros", 60, 200);
		subtitulo.escribir("Opciones", 60, 150);
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
