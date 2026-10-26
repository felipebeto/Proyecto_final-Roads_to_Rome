package pantallas;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rtr.Main;
import Enums.Finales;
import elementos.Audio;
import elementos.BarraVida;
import elementos.Camara;
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
	
	public PantallaCampania(Main main, SpriteBatch batch, InputManager input) {
		super(main, batch, input);
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
				e.atacar(jugador);
			}
		}
		if (jugador.prepararAtaque()) {
			for (Enemigo e : mapa.getEnemigos()) {
				if (calcularRangoAtaque(e)) {
					sonidoGolpe.play();
					jugador.atacar(e);
					if (mapa.revisarMuerto(e)) {
						contador++;
						break;
					}
						
				}
			}
		}
		jugador.calcularAcciones(mapa, delta);
		batch.end();
		porcentajeVida = (float) jugador.getVida() / 100;
		barraVida.pintar(porcentajeVida);
		
		if(jugador.isMuerto()) {
			musica.detener();
			main.setScreen(new PantallaFinal(contador, Finales.DERROTA, main, batch, input));
		}
		if(mapa.isLimpia()) {
			if(mazmorra.isUltima()) {
				if(mapa.revisarPortal(jugador.getX(), jugador.getY())) {
					if(nivelActual==2) {
						musica.detener();
						main.setScreen(new PantallaSalaFinal(main, batch, input, contador));
						//main.setScreen(new PantallaFinal(contador, Finales.VICTORIA, main, batch, input));
					}else {
						mazmorra.salas.clear();
						nivelActual++;
						mazmorra = GeneradorMazmorra.generar(Aleatorio.generarEntero(6, 8));
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
			
		}
		
	}
	@Override
	public void dispose() {
		super.dispose();
		musica.dispose();
		sonidoGolpe.dispose();
		sonidoOof.dispose();
	}

}
