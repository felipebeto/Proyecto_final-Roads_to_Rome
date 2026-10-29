package personajes;

import mapas.Sala;
import util.Aleatorio;
import util.Colisiones;
import util.Recursos;

public abstract class Enemigo extends Personaje{
	private int danio;
	private int concentracion;
	private float cooldownMove = 0;
	private float maxCooldown;
	private float tiempoAnim = 0.5f;
	private boolean animacion = false;
	private float contAnimacion = 0;
	protected int distanciaRetro = 100;
	private float nuevaX, nuevaY, direccionX, direccionY, objX, objY, difX, difY;

	public Enemigo(float x, float y, String ruta, int vida, float maxCooldown, int alcance, int danio, int concentracion) {
		super(x, y, vida, 100, ruta, 80, 87, alcance);
		this.maxCooldown = maxCooldown;
		this.danio = danio;
		this.concentracion = concentracion;
	}

	@Override
		public void calcularMovimiento(float delta, Sala mapa, Personaje personaje) {
			if(vida<=0) {
				return;
			}
			if (isRetrocediendo) {
				float[] pos = actualizarRetroceso(delta);
				nuevaX = pos[0];
				nuevaY = pos[1];
			} else if(cooldownMove <=0) {
					animacion = true;
					direccionX = 0;
					direccionY = 0;
					cooldownMove = maxCooldown;
					int op = Aleatorio.generarEntero(100);
					if (op < concentracion) {
						objX = x + Aleatorio.generarEntero(-alcance*2, alcance*2);
						objY =  y + Aleatorio.generarEntero(-alcance*2, alcance*2);
					}else{
						objX = personaje.getX();
						objY = personaje.getY();
					}
					nuevaX = x;
					nuevaY = y;
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
				
			}		
			revisarHitbox((float)nuevaX, (float)nuevaY, mapa);
			
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
		return true;
	}

	@Override
	public void atacar() {
		
	}
	@Override
	public  void daniar(Personaje jugador) {
		if(vida>0) {
			if (jugador.recibirDanio(danio)) {
				jugador.iniciarRetroceso(this, distanciaRetro);
			}
		}
	}
	
	public Enemigo aparecer(Sala mapa, Personaje jugador) {
		float x2;
		float y2;
		
		do {
			x2 = Aleatorio.generarEntero(Recursos.ANCHO_MAPA);
			y2 = Aleatorio.generarEntero(Recursos.ALTO_MAPA);
			this.hitbox.setPosition(x2, y2);
			if (!Colisiones.colisionaConAlguno(hitbox, mapa.getObstaculos()) && !Colisiones.colisionaConEntidad(hitbox, jugador.getHitbox())) {
				y = y2;
				x = x2;
			}
		}while(Colisiones.colisionaConAlguno(hitbox, mapa.getObstaculos()) || Colisiones.colisionaConEntidad(hitbox, jugador.getHitbox()));
		
		return new Borracho(x, y);
	}

	@Override
	public boolean prepararAtaque() {
		
		return false;
	}


	

}