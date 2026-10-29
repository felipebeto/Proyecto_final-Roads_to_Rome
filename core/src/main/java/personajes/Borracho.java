package personajes;

import items.Inventario;
import mapas.Sala;

public class Borracho extends Enemigo{

	public Borracho(float x, float y) {
		super(x, y, "momo.png", 120, 2, 20, 40, 45);
	}
	@Override
	public Inventario getInventario() {
		
		return null;
	}
	@Override
	public void tirarObjeto(Sala sala) {
	}
	@Override
	public void agarrarObjeto(Sala sala) {
	}
	@Override
	public void calcularAcciones(Sala mapa, float delta) {
	}

}
