package mapas;

import com.badlogic.gdx.Gdx;

import util.Recursos;

public class SalaUL extends Sala {

	public SalaUL() {
		super(1000, 1000, Recursos.SALA_UL);
		
	}

	@Override
	protected void cargarElementos() {
		agregarObstaculo(0, 0, 1000, 160); //abajo
		agregarObstaculo(840, 0, 1000, 1000); //derecha
		agregarObstaculo(0, 840, 400, 1000); //arriba1
		agregarObstaculo(600, 840, 1000, 1000); //arriba2
		agregarObstaculo(0, 0, 160, 400); //izquierda1
		agregarObstaculo(0, 600, 160, 1000); //izquierda2
	}

}
