package personajes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import elementos.Imagen;
import mapas.Mapa;
import util.Colisiones;
import util.Recursos;

public class Jugador extends Personaje{
	
	public Jugador() {
		super(Recursos.ancho/2-35, Recursos.alto/2-41, 100, 200, "gambit1.png", 70,  82);
	}
	@Override
	public void calcularMovimiento(float delta, Mapa mapa, Personaje enemigo) {
		float nuevaX=x, nuevaY=y;
		if (Gdx.input.isKeyPressed(Input.Keys.A)) nuevaX -= velocidad * delta;
        if (Gdx.input.isKeyPressed(Input.Keys.D)) nuevaX += velocidad * delta;
        if (Gdx.input.isKeyPressed(Input.Keys.W)) nuevaY += velocidad * delta;
        if (Gdx.input.isKeyPressed(Input.Keys.S)) nuevaY -= velocidad * delta;
        

        
		revisarHitbox(nuevaX, nuevaY, mapa);
        if(cooldownDanio>0) cooldownDanio -= delta;
        
	}
	
	@Override
	public boolean recibirDanio(int cantidad) {
		
		if(cooldownDanio<=0) {
			vida-=cantidad;
			cooldownDanio = 1;
			System.out.println(vida);
			return true;
		}
		return false;
	}
	@Override
	public void atacar() {
		
	}
	
	
	

}
