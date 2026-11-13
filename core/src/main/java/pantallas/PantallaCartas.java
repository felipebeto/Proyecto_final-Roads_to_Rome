package pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rtr.Main;

import cartas.Carta;
import elementos.Camara;
import elementos.Imagen;
import enums.Modificadores;
import util.Aleatorio;
import util.InputManager;
import util.Recursos;
import util.Render;

public class PantallaCartas implements Screen{
	private InputManager input;
	private Imagen fondo;
	private boolean finFadeIn = false;
	private float a = 0;
	private float mouseX, mouseY;
	private Main main;
	private SpriteBatch batch;
	private Camara camara;
	private Carta carta1;
	private Carta carta2;
	private Carta carta3;
	private int nivelAnt;
	
	public PantallaCartas(Main main, SpriteBatch batch, InputManager input, int nivel) {
		this.main = main;
		this.batch = batch;
		this.input = input;
		this.nivelAnt = nivel;
		Gdx.input.setInputProcessor(input);
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
	public void show() {
		fondo = new Imagen(Recursos.FONDO_MENU);
		fondo.setTrans(a);
		fondo.ajustarTamaño();
		camara = new Camara();
		carta1 = new Carta(elegirModif(true), elegirModif(false));
		carta2 = new Carta(elegirModif(true), elegirModif(false));
		carta3 = new Carta(elegirModif(true), elegirModif(false));
	}
	public Modificadores elegirModif(boolean valor) {
		boolean sirve = false;
		Modificadores modif;
		do {
			int num = Aleatorio.generarEntero(Modificadores.values().length);
			switch (num) {
				case 1: modif = Modificadores.N1;
				case 2: modif = Modificadores.N2;
				case 3: modif = Modificadores.N3;
				case 4: modif = Modificadores.N4;
				case 5: modif = Modificadores.N5;
				case 6: modif = Modificadores.N6;
				case 7: modif = Modificadores.P1;
				case 8: modif = Modificadores.P2;
				case 9: modif = Modificadores.P3;
				case 10: modif = Modificadores.P4;
				case 11: modif = Modificadores.P5;
        		case 12: modif = Modificadores.P6;
        		default: modif = Modificadores.N1; 
			}
			if(!modif.isActivo() && modif.isPositivo() == valor) sirve = true;
		}while(!sirve);
		modif.usar();
		return modif;
	}
	@Override
	public void render(float delta) {
		mouseX = Gdx.input.getX();
	    mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();
		Render.limpiar(0, 0, 0);
		batch.begin();
		fondo.dibujar(batch);
		if (!finFadeIn) {
			calcularFade();
			fondo.setTrans(a);
		}else {
			carta1.dibujar(batch, 1);
			carta2.dibujar(batch, 2);
			carta3.dibujar(batch, 3);
			if(carta1.isSobre(mouseX, mouseY) && input.isClick()) {
				carta1.activar();
				main.setScreen(new PantallaCampania(main, batch, input, nivelAnt+1));
			}
			if(carta2.isSobre(mouseX, mouseY) && input.isClick()) {
				carta2.activar();
				main.setScreen(new PantallaCampania(main, batch, input, nivelAnt+1));
			}
			if(carta3.isSobre(mouseX, mouseY) && input.isClick()) {
				carta3.activar();
				main.setScreen(new PantallaCampania(main, batch, input, nivelAnt+1));
			}
		}
		
	}

	@Override
	public void resize(int width, int height) {
		camara.actualizarPantalla(width, height);		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
