package mapas;

import Enums.Direccion;
import personajes.Enemigo;
import util.Recursos;

public class SalaFinal extends Sala{
	public SalaFinal() {
		super(1000, 1000, Recursos.SALA_FINAL, Direccion.ARRIBA, null);
		
	}

	@Override
	protected void cargarElementos() {
		agregarObstaculo(840, 0, 1000, 1000); //derecha
		agregarObstaculo(0, 0, 160, 1000); //izquierda
		agregarObstaculo(0, 840, 400, 1000); //arriba1
		agregarObstaculo(600, 840, 1000, 1000); //arriba2
		agregarObstaculo(0, 0, 1000, 160); //abajo
		
	}
}
