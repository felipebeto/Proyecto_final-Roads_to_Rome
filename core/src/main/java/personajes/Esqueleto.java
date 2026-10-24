package personajes;

import items.Inventario;
import mapas.Sala;

public class Esqueleto extends Enemigo{

	public Esqueleto(float x, float y) {
		super(x, y, "esqueleto.png");
	}

	@Override
	public void atacar(Personaje jugador) {
		
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
