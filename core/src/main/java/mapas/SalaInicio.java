package mapas;

import Enums.Direccion;
import personajes.Enemigo;
import util.Recursos;

public class SalaInicio extends Sala{
	public SalaInicio() {
		super(1000, 1000, Recursos.SALA_INICIO, Direccion.ABAJO, null);
		
	}

	@Override
	protected void cargarElementos() {
		agregarObstaculo(840, 0, 1000, 1000); //derecha
		agregarObstaculo(0, 0, 160, 1000); //izquierda
		agregarObstaculo(0, 840, 1000, 1000); //arriba
		agregarObstaculo(0, 0, 400, 160); //abajo1
		agregarObstaculo(600, 0, 1000, 160); //abajo2
		
	}

	@Override
	public boolean revisarPortal(float x, float y) {
		
		return false;
	}
}
