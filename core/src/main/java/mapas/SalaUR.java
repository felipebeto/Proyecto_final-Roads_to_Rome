package mapas;

import com.badlogic.gdx.Gdx;

import Enums.Direccion;
import personajes.Enemigo;
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
		agregarEnemigos(new Enemigo(700, 500));
		agregarEnemigos(new Enemigo(700, 400));
		agregarEnemigos(new Enemigo(700, 600));
		agregarEnemigos(new Enemigo(700, 700));
	}

}
