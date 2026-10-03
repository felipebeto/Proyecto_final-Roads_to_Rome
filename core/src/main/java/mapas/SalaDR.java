package mapas;

import com.badlogic.gdx.Gdx;

import Enums.Direccion;
import personajes.Enemigo;
import util.Recursos;

public class SalaDR extends Sala {

	public SalaDR() {
		super(1000, 1000, Recursos.SALA_DR, Direccion.ABAJO, Direccion.DERECHA);
		
	}

	@Override
	protected void cargarElementos() {
		agregarObstaculo(0, 0, 400, 160); //abajo1
		agregarObstaculo(600, 0, 1000, 160); //abajo2
		agregarObstaculo(840, 0, 1000, 400); //derecha1
		agregarObstaculo(840, 600, 1000, 1000); //derecha2
		agregarObstaculo(0, 840, 1000, 1000); //arriba
		agregarObstaculo(0, 0, 160, 1000); //izquierda
		agregarEnemigos(new Enemigo(500, 500));
		agregarEnemigos(new Enemigo(500, 400));
		agregarEnemigos(new Enemigo(500, 600));
		agregarEnemigos(new Enemigo(500, 700));
	}

	@Override
	public boolean revisarPortal(float x, float y) {
		
		return false;
	}

}
