package mapas;

import com.badlogic.gdx.Gdx;

import Enums.Direccion;
import personajes.Enemigo;
import util.Recursos;

public class SalaUL extends Sala {

	public SalaUL() {
		super(1000, 1000, Recursos.SALA_UL, Direccion.ARRIBA, Direccion.IZQUIERDA);
		
	}

	@Override
	protected void cargarElementos() {
		agregarObstaculo(0, 0, 1000, 160); //abajo
		agregarObstaculo(840, 0, 1000, 1000); //derecha
		agregarObstaculo(0, 840, 400, 1000); //arriba1
		agregarObstaculo(600, 840, 1000, 1000); //arriba2
		agregarObstaculo(0, 0, 160, 400); //izquierda1
		agregarObstaculo(0, 600, 160, 1000); //izquierda2
		agregarEnemigos(new Enemigo(700, 500));
		agregarEnemigos(new Enemigo(700, 400));
		agregarEnemigos(new Enemigo(700, 600));
		agregarEnemigos(new Enemigo(700, 700));
	}

}
