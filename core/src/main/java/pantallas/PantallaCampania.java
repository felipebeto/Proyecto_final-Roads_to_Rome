package pantallas;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rtr.Main;

import elementos.Audio;
import elementos.BarraVida;
import elementos.Camara;
import enums.Finales;
import personajes.Enemigo;
import personajes.Jugador;
import personajes.Personaje;
import util.Aleatorio;
import util.Colisiones;
import util.GeneradorMazmorra;
import util.InputManager;
import util.Recursos;
import util.Render;


public class PantallaCampania extends PantallaJuego{
	private int nivelActual;
	
	public PantallaCampania(Main main, SpriteBatch batch, InputManager input, int nivelAct) {
		super(main, batch, input);
		nivelActual = nivelAct;
	}
	
	@Override
	public void show() {
		super.show();
		mazmorra = GeneradorMazmorra.generar(Aleatorio.generarEntero(6, 8));
		musica = new Audio(Recursos.MUSICA_JUEGO);
		sonidoGolpe = Gdx.audio.newSound(Gdx.files.internal(Recursos.SONIDO_GOLPE));
		sonidoOof = Gdx.audio.newSound(Gdx.files.internal(Recursos.SONIDO_OOF));
		barraVida  = new BarraVida();
		barraVida.recalcularCoords();
		
	}
	//cambio
	@Override 
	protected int getNivelActual() {
		return nivelActual;
	}
	
	@Override
	public void render(float delta) {
		mapa = mazmorra.getSalaActual();
		super.render(delta);
		
		musica.comenzar();
		
		for (Personaje e : mapa.getEnemigos()) {
			e.calcularMovimiento(delta, mapa, jugador);
		}
		
		
		
		batch.begin();
		mapa.dibujarFondo(batch);
		if(mapa.hayCofre()) {
			mapa.getCofre().dibujar(batch);
		}
		mapa.dibujarSuelo(batch);
		for (Personaje e : mapa.getEnemigos()) {
			e.dibujar(batch);
		}
		jugador.dibujar(batch);
		for (Personaje e : mapa.getEnemigos()) {
			if (Colisiones.colisionaConEntidad(jugador.getHitbox(), e.getHitbox())) {
				sonidoOof.play();
				e.daniar(jugador);
			}
		}
		if (jugador.prepararAtaque()) {
			jugador.atacar();
			for (Enemigo e : mapa.getEnemigos()) {
				if (calcularRangoAtaque(e)) {
					sonidoGolpe.play();
					jugador.daniar(e);
					if (mapa.revisarMuerto(e)) {
						contador++;
						break;
					}
						
				}
			}
		}
		jugador.calcularAcciones(mapa, delta);
		batch.end();
		porcentajeVida = (float) jugador.getVida() / jugador.getVidaMaxima();
		barraVida.pintar(porcentajeVida);
		
		if(jugador.isMuerto()) {
			musica.detener();
			main.setScreen(new PantallaFinal(contador, Finales.DERROTA, main, batch, input));
		}
		//if(mapa.isLimpia()) {
			if(mazmorra.isUltima()) {
				if(mapa.revisarPortal(jugador.getX(), jugador.getY())) {
					if(nivelActual==2) {
						musica.detener();
						main.setScreen(new PantallaSalaFinal(main, batch, input, contador));
						//main.setScreen(new PantallaFinal(contador, Finales.VICTORIA, main, batch, input));
					}else {
						mazmorra.salas.clear();
						main.setScreen(new PantallaCartas(main, batch, input, nivelActual));
					}
					
				}
			}
			if(jugador.revisarLimite()) {
				if(jugador.avanzarSala(mapa)) {
					mazmorra.avanzarSala();
				}else {
					mazmorra.retrocederSala();
				}
				
			}
			if(mapa.hayCofre()) {
				mapa.revelarCofre();
			}
			
		//}
		
	}
	@Override
	public void dispose() {
		super.dispose();
		musica.dispose();
		sonidoGolpe.dispose();
		sonidoOof.dispose();
		mazmorra.dispose();
	}

}
