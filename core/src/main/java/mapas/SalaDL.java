package mapas;

import com.badlogic.gdx.Gdx;

import util.Recursos;

public class SalaDL extends Sala {

	public SalaDL() {
		super(1000, 1000, Recursos.SALA_DL);
		
	}

	@Override
	protected void cargarElementos() {
		agregarObstaculo(0, 840, 1000, 1000); //arriba
		agregarObstaculo(840, 0, 1000, 1000); //derecha
		agregarObstaculo(0, 0, 400, 160); //abajo1
		agregarObstaculo(600, 0, 1000, 160); //abajo2
		agregarObstaculo(0, 0, 160, 400); //izquierda1
		agregarObstaculo(0, 600, 160, 1000); //izquierda2
	}

}
