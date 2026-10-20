package mapas;

import com.badlogic.gdx.Gdx;

import Enums.Direccion;
import personajes.Borracho;
import personajes.Enemigo;
import personajes.Esqueleto;
import util.Recursos;

public class SalaUR extends Sala {

	public SalaUR() {
		super(1000, 1000, Recursos.SALA_UR, Direccion.ARRIBA, Direccion.DERECHA);
		
	}

	@Override
	protected void cargarElementos() {
		agregarObstaculo(0, 840, 400, 1000); //arriba1
		agregarObstaculo(600, 840, 1000, 1000); //arriba2
		agregarObstaculo(0, 0, 160, 1000); //izquierda
		agregarObstaculo(840, 0, 1000, 400); //derecha1
		agregarObstaculo(840, 600, 1000, 1000); //derecha2
		agregarObstaculo(0, 0, 1000, 160); //abajo
		agregarEnemigos(new Borracho(500, 500));
		agregarEnemigos(new Borracho(500, 400));
		agregarEnemigos(new Esqueleto(500, 600));
		agregarEnemigos(new Esqueleto(500, 300));
		agregarObstaculo(640, 720, 80, 40); 
		agregarObstaculo(280, 640, 40, 80);
		agregarObstaculo(240, 320, 40, 80);
		agregarObstaculo(280, 320, 40, 40);
		agregarObstaculo(640, 240, 120, 40);
		
	}

	@Override
	public boolean revisarPortal(float x, float y) {
		
		return false;
	}

}
