package personajes;

import items.Inventario;
import mapas.Sala;

public class Borracho extends Enemigo{

	public Borracho(float x, float y) {
		super(x, y, "momo.png");
	}
	@Override
	public void atacar(Personaje jugador) {
		if(vida>0) {
			if (jugador.recibirDanio(20)) {
				jugador.iniciarRetroceso(this, distanciaRetro);
			}
		}
	    
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
