package items;

import mapas.Sala;
import personajes.Personaje;
import util.Recursos;

public class Pocion extends Consumible{

	public Pocion() {
		super("Pocion", Recursos.POCION);
		
	}
	@Override
	public void usar(Personaje pj, Sala mapa) {
		pj.curar(60);
		pj.getInventario().tirar(pj.getInventario().getIndiceAct());
	}

}
