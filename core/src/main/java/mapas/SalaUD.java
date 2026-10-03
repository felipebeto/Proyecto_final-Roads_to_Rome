package mapas;

import com.badlogic.gdx.Gdx;

import Enums.Direccion;
import personajes.Enemigo;
import util.Recursos;

public class SalaUD extends Sala {

	public SalaUD() {
		super(1000, 1000, Recursos.SALA_UD, Direccion.ARRIBA, Direccion.ABAJO);
		
	}

	@Override
	protected void cargarElementos() {
		agregarObstaculo(0, 840, 400, 1000); //arriba1
		agregarObstaculo(600, 840, 1000, 1000); //arriba2
		agregarObstaculo(840, 0, 1000, 1000); //derecha
		agregarObstaculo(0, 0, 160, 1000); //izquierda
		agregarObstaculo(0, 0, 400, 160); //abajo1
		agregarObstaculo(600, 0, 1000, 160); //abajo2
		agregarEnemigos(new Enemigo(700, 500));
		agregarEnemigos(new Enemigo(700, 400));
		agregarEnemigos(new Enemigo(700, 600));
		agregarEnemigos(new Enemigo(700, 700));
	}

	@Override
	public boolean revisarPortal(float x, float y) {
		
		return false;
	}

}
