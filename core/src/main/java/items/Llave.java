package items;

import mapas.Sala;
import personajes.Personaje;
import util.Colisiones;
import util.Recursos;

public class Llave extends Consumible{

	public Llave() {
		super("Llave", Recursos.LLAVE);
		
	}
	public void usar(Personaje pj, Sala mapa) {
		if(pj.getX()>400 && pj.getX()<520 && pj.getY()>720){
			pj.getInventario().tirar(pj.getInventario().getIndiceAct());
			mapa.abrirPuerta();
		}
		
	}

}
