package personajes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import elementos.Imagen;

public class Jugador extends Personaje{

	public Jugador() {
		super(250, 250, 100, 200, new Imagen("gambit1.png"), 70,  82);
	}
	@Override
	public void calcularMovimiento(float delta) {
		if (Gdx.input.isKeyPressed(Input.Keys.A)) x -= velocidad * delta;
        if (Gdx.input.isKeyPressed(Input.Keys.D)) x += velocidad * delta;
        if (Gdx.input.isKeyPressed(Input.Keys.W)) y += velocidad * delta;
        if (Gdx.input.isKeyPressed(Input.Keys.S)) y -= velocidad * delta;
        revisarLimite();
        hitbox.setPosition(x, y);
        
	}
	

}
