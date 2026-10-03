package pantallas;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rtr.Main;
import Enums.Finales;
import util.Aleatorio;
import util.GeneradorMazmorra;
import util.InputManager;


public class PantallaCampania extends PantallaJuego{
	private int nivelActual;
	
	public PantallaCampania(Main main, SpriteBatch batch, InputManager input) {
		super(main, batch, input);
	}
	@Override
	public void render(float delta) {
		super.render(delta);
		if(jugador.isMuerto()) {
			musica.detener();
			main.setScreen(new PantallaFinal(contador, Finales.DERROTA, main, batch, input));
		}
		if(mapa.isLimpia()) {
			if(mazmorra.isUltima()) {
				if(mapa.revisarPortal(jugador.getX(), jugador.getY())) {
					if(nivelActual==2) {
						musica.detener();
						main.setScreen(new PantallaFinal(contador, Finales.VICTORIA, main, batch, input));
					}
					mazmorra.salas.clear();
					nivelActual++;
					mazmorra = GeneradorMazmorra.generar(Aleatorio.generarEntero(6, 8));
				}
			}
			if(jugador.revisarLimite()) {
				if(jugador.avanzarSala(mapa)) {
					mazmorra.avanzarSala();
				}else {
					mazmorra.retrocederSala();
				}
				
			}
		}
		
	}

}
