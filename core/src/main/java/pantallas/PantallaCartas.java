package pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.rtr.Main;

import cartas.Carta;
import elementos.Camara;
import elementos.Imagen;
import enums.Modificadores;
import personajes.Personaje;
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
	private Personaje jugador;
	
	public PantallaCartas(Main main, SpriteBatch batch, InputManager input, int nivel, Personaje jugador) {
		this.jugador = jugador;
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
		fondo = new Imagen(Recursos.FONDO_CARTAS);
		fondo.setTrans(a);
		fondo.ajustarTamaño();
		camara = new Camara();
		batch.setProjectionMatrix(new Matrix4().setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
		if(nivelAnt == 1) {
			carta1 = new Carta(elegirModifR(true), elegirModifR(false));
			carta2 = new Carta(elegirModifR(true), elegirModifR(false));
			carta3 = new Carta(elegirModifR(true), elegirModifR(false));
		}else {
			carta1 = new Carta(elegirModif(true), elegirModif(false));
			carta2 = new Carta(elegirModif(true), elegirModif(false));
			carta3 = new Carta(elegirModif(true), elegirModif(false));
		}
		
	}
	public Modificadores elegirModifR(boolean valor) {
		boolean sirve = false;
		Modificadores modif;
		int num;
		do {
			num = Aleatorio.generarEntero(Modificadores.values().length);
			switch (num) {
				case 1: 
					modif = Modificadores.N1;
				break;
				case 2: 
					modif = Modificadores.N2;
				break;
				case 3: 
					modif = Modificadores.N3;
				break;
				case 4: 
					modif = Modificadores.N4;
				break;
				case 5: 
					modif = Modificadores.N5;
				break;
				case 6: 
					modif = Modificadores.N6;
				break;
				case 7: 
					modif = Modificadores.P1;
				break;
				case 8: 
					modif = Modificadores.P2;
				break;
				case 9: 
					modif = Modificadores.P3;
				break;
				case 10: 
					modif = Modificadores.P4;
				break;
				case 11: 
					modif = Modificadores.P5;
				break;
        		case 12: 
        			modif = Modificadores.P6;
        		break;
        		default: 
        			modif = Modificadores.N2; 
        		break;
			}
			if(modif.isPositivo() == valor && !modif.isUsado()) sirve = true;
		}while(!sirve);
		modif.usar();
		return modif;
	}
	private Modificadores elegirModif(boolean valor) {
		Modificadores modif;
		int i = 0;
		do {
			modif = Modificadores.values()[i];
			i++;
		}while(!modif.isPositivo() == valor || modif.isUsado());
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
				main.setScreen(new PantallaCampania(main, batch, input, nivelAnt+1, jugador));
			}
			if(carta2.isSobre(mouseX, mouseY) && input.isClick()) {
				carta2.activar();
				main.setScreen(new PantallaCampania(main, batch, input, nivelAnt+1, jugador));
			}
			if(carta3.isSobre(mouseX, mouseY) && input.isClick()) {
				carta3.activar();
				main.setScreen(new PantallaCampania(main, batch, input, nivelAnt+1, jugador));
			}
		}
		batch.end();
		
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
