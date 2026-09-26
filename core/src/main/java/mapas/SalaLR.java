package mapas;

import com.badlogic.gdx.Gdx;

import personajes.Enemigo;
import util.Recursos;

public class SalaLR extends Sala{

	public SalaLR() {
		super(1000, 1000, Recursos.SALA_LR);
		
	}

	@Override
	protected void cargarElementos() {
		agregarObstaculo(840, 0, 1000, 400); //derecha1
		agregarObstaculo(840, 600, 1000, 1000); //derecha2
		agregarObstaculo(0, 0, 160, 400); //izquierda1
		agregarObstaculo(0, 600, 160, 1000); //izquierda2
		agregarObstaculo(0, 840, 1000, 1000); //arriba
		agregarObstaculo(0, 0, 1000, 160); //abajo
		
		agregarEnemigos(new Enemigo(700, 500));
		agregarEnemigos(new Enemigo(700, 400));
		agregarEnemigos(new Enemigo(700, 600));
		agregarEnemigos(new Enemigo(700, 700));
	}

}
