package items;

import personajes.Personaje;
import util.Recursos;

public class Pocion extends Consumible{

	public Pocion() {
		super("Pocion", Recursos.POCION);
		
	}
	@Override
	public void usar(Personaje pj) {
		pj.curar(60);
		pj.getInventario().tirar(pj.getInventario().getIndiceAct());
	}

}
