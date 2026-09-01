package personajes;

import com.badlogic.gdx.math.Rectangle;

import elementos.Imagen;
import mapas.Mapa;
import util.Colisiones;
import util.Recursos;

public class Enemigo extends Personaje{
	private int alcance = 20;
	public Enemigo() {
		super(Recursos.ancho/2+100, Recursos.alto/2-62, 100, 100, "momo.png", 150, 187);
		
	}

	@Override
	public void calcularMovimiento(float delta, Mapa mapa, Personaje personaje) {
	}
	public int getAlcance() {
		return alcance;
	}

	@Override
	public boolean recibirDanio(int cantidad) {
		
		return false;
	}

	

}
