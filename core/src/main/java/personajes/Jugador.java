package personajes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import elementos.Imagen;

public class Jugador {
	private float x, y;
	private Imagen sprite;
	private float velocidad;

	public Jugador() {
		this.sprite = new Imagen("gambit1.png");
		this.velocidad = 200;
		this.x = 250;
		this.y = 250;
	}
	public void calcularMovimiento(float delta) {
		if (Gdx.input.isKeyPressed(Input.Keys.A)) x -= velocidad * delta;
        if (Gdx.input.isKeyPressed(Input.Keys.D)) x += velocidad * delta;
        if (Gdx.input.isKeyPressed(Input.Keys.W)) y += velocidad * delta;
        if (Gdx.input.isKeyPressed(Input.Keys.S)) y -= velocidad * delta;
	}
	public void dibujar() {
		sprite.setPosicion(x, y);
		sprite.dibujar();
	}

}
