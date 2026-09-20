package pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rtr.Main;
import Enums.Finales;
import personajes.Enemigo;
import personajes.Personaje;
import util.Colisiones;
import util.InputManager;
import util.Render;
import java.util.ArrayList;

public class PantallaOleada extends PantallaJuego{
	
	private int contador = 0; 
	private ArrayList <Personaje> enemigos;
	public PantallaOleada(Main main, SpriteBatch batch, InputManager input) {
		super(main, batch, input);
	}
	
	@Override
	public void show() {
		super.show();
		iniciarArray();
	}
	private void iniciarArray() {
		enemigos = new ArrayList<>();
		enemigos.add(enemigo.aparecer(mapa, jugador));
	}			

	@Override
	public void render(float delta) {
		musica.comenzar();
		
		jugador.calcularMovimiento(delta, mapa, enemigo);
		for (Personaje e : enemigos) {
			e.calcularMovimiento(delta, mapa, jugador);
		}
		
		Render.limpiar(0, 0, 0);
		camara.actualizarPosicion(jugador, batch);
		
		batch.begin();
		mapa.dibujarFondo(batch);
		for (Personaje e : enemigos) {
			e.dibujar(batch);
		}
		
		jugador.dibujar(batch);
		
		for (Personaje e : enemigos) {
	        if (Colisiones.colisionaConEntidad(jugador.getHitbox(), e.getHitbox())) {
	            sonidoOof.play();
	            e.atacar(jugador);
	        }
	    }
		if(jugador.prepararAtaque()) {
			for (Personaje e : enemigos) {
				if(calcularRangoAtaque(e)) {
					sonidoGolpe.play();
					jugador.atacar(e);
					if(revisarMuerto(e)) break;
				}
			}
		}
		batch.end();
		porcentajeVida = (float) jugador.getVida() / 100;
		barraVida.pintar(porcentajeVida);
		if(jugador.isMuerto()) {
			musica.detener();
			main.setScreen(new PantallaFinal(contador, Finales.DERROTA, main, batch, input));
		}
		if(enemigos.isEmpty()) {
			eliminarEnemigos();
			if(contador<5) {
				enemigos.add(enemigo.aparecer(mapa, jugador));
			}else if(contador<15){
				enemigos.add(enemigo.aparecer(mapa, jugador));
				enemigos.add(enemigo.aparecer(mapa, jugador));
			}else {
				enemigos.add(enemigo.aparecer(mapa, jugador));
				enemigos.add(enemigo.aparecer(mapa, jugador));
				enemigos.add(enemigo.aparecer(mapa, jugador));
			}
			
		}
	}
	private boolean revisarMuerto(Personaje e) {
		if (e.isMuerto()) {
			e.dispose();
			enemigos.remove(e);
			contador++;
			return true;
		}
		return false;
		
	}

	private void eliminarEnemigos() {
		for (Personaje e : enemigos) {
			e.dispose();
		}
		enemigos.clear();
	}

}