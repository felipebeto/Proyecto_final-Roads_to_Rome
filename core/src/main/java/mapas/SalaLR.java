package mapas;

import com.badlogic.gdx.Gdx;

import Enums.Direccion;
import personajes.Enemigo;
import util.Recursos;

public class SalaLR extends Sala{

	public SalaLR() {
		super(1000, 1000, Recursos.SALA_LR, Direccion.IZQUIERDA, Direccion.DERECHA);
		
	}

	@Override
	protected void cargarElementos() {
		agregarObstaculo(840, 0, 1000, 400); //derecha1
		agregarObstaculo(840, 600, 1000, 1000); //derecha2
		agregarObstaculo(0, 0, 160, 400); //izquierda1
		agregarObstaculo(0, 600, 160, 1000); //izquierda2
		agregarObstaculo(0, 840, 1000, 1000); //arriba
		agregarObstaculo(0, 0, 1000, 160); //abajo
		
		agregarEnemigos(new Enemigo(500, 500));
		agregarEnemigos(new Enemigo(500, 400));
		agregarEnemigos(new Enemigo(500, 600));
		agregarEnemigos(new Enemigo(700, 700));
		
		agregarObstaculo(240, 320, 80, 40);
		agregarObstaculo(440, 720, 160, 40);
		agregarObstaculo(520, 240, 80, 40);
		agregarObstaculo(600, 280, 40, 40);
		agregarObstaculo(720, 600, 40, 40);
	}

	@Override
	public boolean revisarPortal(float x, float y) {
		
		return false;
	}

}
