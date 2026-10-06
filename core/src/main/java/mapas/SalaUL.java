package mapas;

import com.badlogic.gdx.Gdx;

import Enums.Direccion;
import personajes.Borracho;
import personajes.Enemigo;
import personajes.Esqueleto;
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
		agregarEnemigos(new Borracho(700, 500));
		agregarEnemigos(new Borracho(700, 200));
		agregarEnemigos(new Esqueleto(700, 600));
		agregarEnemigos(new Esqueleto(600, 600));
		agregarObstaculo(280, 280, 40, 40);
		agregarObstaculo(280, 640, 40, 40);
		agregarObstaculo(320, 680, 40, 40);
		agregarObstaculo(600, 720, 160, 40);
		agregarObstaculo(680, 360, 40, 80);
		
	}

	@Override
	public boolean revisarPortal(float x, float y) {
		
		return false;
	}

}
