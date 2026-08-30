package Pantallas;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;

import Imagenes.Imagen;
import util.Render;

public class PantallaIntro implements Screen{
	private Imagen fondo;
	private float a = 0, contTiempo = 0;
	private boolean finFadeIn = false;
	private boolean finall = false;
	private float contTiempo2 = 0;
	@Override
	public void show() {
		fondo = new Imagen("marvel.jpg", 0, 0, 960, 540);
		
		
	}

	private void calcularFade() {
		if(!finFadeIn) {
			a+= 0.01f;
			if (a>1) {
				finFadeIn = true;
				a=1;
			}
		}else {
			if(!finall) {
				contTiempo+= 0.05f;
				if (contTiempo>5) {
					a-= 0.01f;
					if (a<0) {
						a=0;
						finall = true;
					}
				}
			}else {
				contTiempo2 += 0.05f;
				if (contTiempo2>5) {
					Render.app.setScreen(new PantallaCarga());
				}
			}
		}
		
		
	}

	@Override
	public void render(float delta) {
		calcularFade();
		fondo.setTrans(a);
		Render.limpiarPantalla(0f, 0f, 0f);
		Render.batch.begin();
		fondo.dibujar();
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
