package personajes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import elementos.Imagen;
import mapas.Mapa;
import util.Colisiones;
import util.Recursos;

public class Jugador extends Personaje{
	private Animation<TextureRegion> animacionCaminar;
	private TextureRegion frameQuieto;
	private float tiempoAnimacion = 0;
	private boolean mirandoDerecha = true;
	private boolean moviendose = false;
	public Jugador() {
		super(Recursos.ancho/2-35, Recursos.alto/2-41, 100, 200, "gambit1.png", 70,  82, 100);
		cargarSpriteSheet();
	}
	private void cargarSpriteSheet() {
		Texture sheet = new Texture(Gdx.files.internal("sheetCaminando.png"));
		TextureRegion[][] matriz = TextureRegion.split(sheet, 56, 81);
		TextureRegion[] frames = matriz[0];

		frameQuieto = frames[0];
		animacionCaminar = new Animation<>(0.12f, frames);
		animacionCaminar.setPlayMode(Animation.PlayMode.LOOP);
	}
	@Override
	public void calcularMovimiento(float delta, Mapa mapa, Personaje enemigo) {
		moviendose = false;
		float nuevaX=x, nuevaY=y;
		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			nuevaX -= velocidad * delta;
			moviendose = true; 
			mirandoDerecha = false;
		}
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
        	nuevaX += velocidad * delta;
        	moviendose = true; 
        	mirandoDerecha = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
        	nuevaY += velocidad * delta;
        	moviendose = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
        	nuevaY -= velocidad * delta;
        	moviendose = true;
        }
        
        if (moviendose) tiempoAnimacion +=delta;
        
		revisarHitbox(nuevaX, nuevaY, mapa);
        if(cooldownDanio>0) cooldownDanio -= delta;
        
	}
	
	@Override
	public boolean recibirDanio(int cantidad) {
		
		if(cooldownDanio<=0) {
			vida-=cantidad;
			cooldownDanio = 1;
			return true;
		}
		return false;
	}
	@Override
	public void atacar() {
		
	}
	@Override
	public void dibujar() {
	    TextureRegion frameActual;
	    if(moviendose) frameActual =  animacionCaminar.getKeyFrame(tiempoAnimacion, true);
	    else frameActual = frameQuieto;
	    sprite.setRegion(frameActual);
	    sprite.setFlip(!mirandoDerecha);
	    sprite.setPosicion(x, y);
	    sprite.dibujar();
	}
	
	
	

}
