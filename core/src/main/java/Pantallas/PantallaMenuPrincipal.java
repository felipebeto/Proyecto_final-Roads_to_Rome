package Pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.rtr.Main;

import elementos.Audio;
import elementos.Imagen;
import elementos.Texto;
import util.Recursos;
import util.Render;

public class PantallaMenuPrincipal implements Screen{
	
	private Imagen fondo;
	private Texto titulo;
	private Texto subtitulo1;
	private Texto subtitulo2;
	private Texto subtitulo3;
	private Texto subtitulo4;
	private boolean finFadeIn = false;
	private float a = 0;
	private float mouseX, mouseY;
	private Audio musica;
	private Sound sonidoClick;

	@Override
	public void show() {
		fondo = new Imagen(Recursos.FONDO_MENU);
		fondo.setTrans(a);
		fondo.ajustarTamaño();
		titulo = new Texto("Roads to Rome ", 40, 450);
		titulo.agrandar(2);
		subtitulo1 = new Texto("Nueva partida", 60, 300);
		subtitulo2 = new Texto("Continuar", 60, 250);
		subtitulo3 = new Texto("Logros", 60, 200);
		subtitulo4 = new Texto("Opciones", 60, 150);
		musica = new Audio(Recursos.MUSICA_MENU);
		sonidoClick = Gdx.audio.newSound(Gdx.files.internal(Recursos.SONIDO_CLICK));
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
		Render.batch.begin();
		fondo.dibujar();
		if (!finFadeIn) {
			calcularFade();
			fondo.setTrans(a);
		}else {
			titulo.escribir();
			subtitulo1.setColor(subtitulo1.isHover(mouseX, mouseY) ? Color.RED : Color.WHITE);
			subtitulo1.escribir();
			subtitulo2.setColor(subtitulo2.isHover(mouseX, mouseY) ? Color.RED : Color.WHITE);
			subtitulo2.escribir();
			subtitulo3.setColor(subtitulo3.isHover(mouseX, mouseY) ? Color.RED : Color.WHITE);
			subtitulo3.escribir();
			subtitulo4.setColor(subtitulo4.isHover(mouseX, mouseY) ? Color.RED : Color.WHITE);
			subtitulo4.escribir();
		}
		if(Gdx.input.justTouched()) {
			if(subtitulo1.isHover(mouseX, mouseY)) {
				sonidoClick.play();
				musica.detener();
				Recursos.MAIN.setScreen(new PantallaJuego());
			}
			if(subtitulo2.isHover(mouseX, mouseY)) sonidoClick.play();
			if(subtitulo3.isHover(mouseX, mouseY)) sonidoClick.play();
			if(subtitulo4.isHover(mouseX, mouseY)) sonidoClick.play();
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
