package mapas;

import com.badlogic.gdx.Gdx;

import Enums.Direccion;
import personajes.Borracho;
import personajes.Enemigo;
import personajes.Esqueleto;
import util.Recursos;

public class SalaDL extends Sala {

	public SalaDL() {
		super(1000, 1000, Recursos.SALA_DL, Direccion.ABAJO, Direccion.IZQUIERDA);
		
	}

	@Override
	protected void cargarElementos() {
		agregarObstaculo(0, 840, 1000, 1000); //arriba
		agregarObstaculo(840, 0, 1000, 1000); //derecha
		agregarObstaculo(0, 0, 400, 160); //abajo1
		agregarObstaculo(600, 0, 1000, 160); //abajo2
		agregarObstaculo(0, 0, 160, 400); //izquierda1
		agregarObstaculo(0, 600, 160, 1000); //izquierda2
		agregarEnemigos(new Borracho(600, 700));
		agregarEnemigos(new Borracho(700, 400));
		agregarEnemigos(new Esqueleto(700, 300));
		agregarEnemigos(new Esqueleto(700, 700));
		agregarObstaculo(240, 640, 40, 80);
		agregarObstaculo(280, 280, 40, 40);
		agregarObstaculo(320, 240, 40, 40);
		agregarObstaculo(440, 720, 80, 40);
		agregarObstaculo(720, 520, 40, 120);
	}

	@Override
	public boolean revisarPortal(float x, float y) {
		
		return false;
	}

}
