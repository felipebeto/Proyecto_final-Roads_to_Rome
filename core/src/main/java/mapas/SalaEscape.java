package mapas;

import Enums.Direccion;
import elementos.Imagen;
import util.Recursos;

public class SalaEscape extends Sala {

	public SalaEscape() {
		super(1000, 1000, Recursos.SALA_ESCAPE, Direccion.ARRIBA, Direccion.ABAJO);
		
	}

	@Override
	protected void cargarElementos() {
		agregarObstaculo(0, 840, 1000, 1000); //arriba
		agregarObstaculo(0, 840, 400, 1000); //arriba1
		agregarObstaculo(600, 840, 1000, 1000); //arriba2
		agregarObstaculo(840, 0, 1000, 1000); //derecha
		agregarObstaculo(0, 0, 160, 1000); //izquierda
		agregarObstaculo(0, 0, 400, 160); //abajo1
		agregarObstaculo(600, 0, 1000, 160); //abajo2
		agregarCofre(500, 500, true);
		cofre.revelar();
	}
	
	
	
	@Override
	public void abrirPuerta() {
		fondo.dispose();
		fondo = new Imagen(Recursos.SALA_ESCAPEAB);
		sacarObstaculo(0);
	}

	@Override
	public boolean revisarPortal(float x, float y) {
		return false;
	}

}
