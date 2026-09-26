package mapas;

import com.badlogic.gdx.Gdx;

import util.Recursos;

public class SalaDR extends Sala {

	public SalaDR() {
		super(1000, 1000, Recursos.SALA_DR);
		
	}

	@Override
	protected void cargarElementos() {
		agregarObstaculo(0, 0, 400, 160); //abajo1
		agregarObstaculo(600, 0, 1000, 160); //abajo2
		agregarObstaculo(840, 0, 1000, 400); //derecha1
		agregarObstaculo(840, 600, 1000, 1000); //derecha2
		agregarObstaculo(0, 840, 1000, 1000); //arriba
		agregarObstaculo(0, 0, 160, 1000); //izquierda
	}

}
