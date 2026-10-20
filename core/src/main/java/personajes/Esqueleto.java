package personajes;

import items.Inventario;

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

}
