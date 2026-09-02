package personajes;

import com.badlogic.gdx.math.Rectangle;

import elementos.Imagen;
import mapas.Mapa;
import util.Aleatorio;
import util.Colisiones;
import util.Recursos;

public class Enemigo extends Personaje{
	private float cooldownMove = 0;
	private float maxCooldown = 1.5f;
	private float tiempoAnim = 0.5f;
	private boolean animacion = false;
	private float contAnimacion = 0;
	private float nuevaX, nuevaY, direccionX, direccionY, objX, objY, difX, difY;
	public Enemigo() {
		super(Recursos.ancho/2+100, Recursos.alto/2-62, 100, 100, "momo.png", 80, 87, 30);
		
	}

	@Override
		public void calcularMovimiento(float delta, Mapa mapa, Personaje personaje) {
			if(cooldownMove <=0) {
				animacion = true;
				direccionX = 0;
				direccionY = 0;
				cooldownMove = maxCooldown;
				int op = Aleatorio.generarEntero(2);
				if (op ==1) {
					
					objX = x + Aleatorio.generarEntero(-alcance*2, alcance*2);
					objY =  y + Aleatorio.generarEntero(-alcance*2, alcance*2);
				}else{
					objX = personaje.getX();
					objY = personaje.getY();
				}
					
				
				
			}else {
				if(animacion) {
					calcularDistanciaObjetivo();
					contAnimacion +=delta;
					if (direccionX<x) {
						nuevaX = x -(difX/tiempoAnim)*delta;
					}else if (direccionX>x) {
						nuevaX = x +(difX/tiempoAnim)*delta;
					}else nuevaX=objX;
					if (direccionY<y) {
						nuevaY = y -(difY/tiempoAnim)*delta;
					}else if (direccionY>y) {
						nuevaY = y +(difY/tiempoAnim	)*delta;
					}else
					if(contAnimacion>0.5f) {
						animacion = false;
						contAnimacion = 0;
					}
				}
				cooldownMove -=delta;
				revisarHitbox((float)nuevaX, (float)nuevaY, mapa);
			}
			
			
		}
	private void calcularDistanciaObjetivo() {
		if(objX>x) {
			if(x+alcance>objX) {
				direccionX = objX;
				difX = objX-x;
			}
			else {
				direccionX = x + alcance;
				difX = alcance;
			}
		}else {
			if(x-alcance<objX) {
				direccionX = objX;
				difX = x-objX;
			}
			else {
				direccionX = x - alcance;
				difX = alcance;
			}
		}
		if(objY>y) {
			if(y+alcance>objY) {
				direccionY = objY;
				difY= objY-y;
			}
			else {
				direccionY = y + alcance;
				difY= alcance;
			}
		}else {
			if(y-alcance<objY) {
				direccionY = objY;
				difY= y-objY;
			}
			else {
				direccionY = y - alcance;
				difY= alcance;
			}
		}
	}
	

	@Override
	public boolean recibirDanio(int cantidad) {
		vida -=cantidad;
		return false;
	}

	@Override
	public void atacar() {
	}

	

}
