package mapas;

import com.badlogic.gdx.Gdx;

import util.Recursos;

public class SalaUD extends Sala {

	public SalaUD() {
		super(1000, 1000, Recursos.SALA_UD);
		
	}

	@Override
	protected void cargarElementos() {
		agregarObstaculo(0, 840, 400, 1000); //arriba1
		agregarObstaculo(600, 840, 1000, 1000); //arriba2
		agregarObstaculo(840, 0, 1000, 1000); //derecha
		agregarObstaculo(0, 0, 160, 1000); //izquierda
		agregarObstaculo(0, 0, 400, 160); //abajo1
		agregarObstaculo(600, 0, 1000, 160); //abajo2
	}

}
