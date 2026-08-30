package Pantallas;

import com.badlogic.gdx.Screen;

import elementos.Imagen;
import util.Recursos;
import util.Render;

public class PantallaMenu implements Screen{
	private Imagen fondoMenu;
	private boolean finFadeIn = false;
	private float a;
	@Override
	public void show() {
		fondoMenu = new Imagen("fondo.jpg", 0, 0, Recursos.ANCHO, Recursos.ALTO);
	}

	private void calcularFade() {
		if(!finFadeIn) {
			a+= 0.01f;
			if (a>1) {
				finFadeIn = true;
				a=1;
			}
		}
	}
	@Override
	public void render(float delta) {
		if(!finFadeIn) {
			calcularFade();
			fondoMenu.setTrans(a);
		}
		
		Render.limpiarPantalla(0f, 0f, 0f);
		Render.batch.begin();
		fondoMenu.dibujar();
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
