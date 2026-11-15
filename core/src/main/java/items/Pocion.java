package items;

import enums.Modificadores;
import mapas.Sala;
import personajes.Personaje;
import util.Recursos;

public class Pocion extends Consumible{

	public Pocion() {
		super("Pocion", Recursos.POCION);
		
	}
	@Override
	public void usar(Personaje pj, Sala mapa) {
		if(Modificadores.P4.isActivo()) pj.curar(80);
		else pj.curar(40);
		pj.getInventario().tirar(pj.getInventario().getIndiceAct());
	}

}
