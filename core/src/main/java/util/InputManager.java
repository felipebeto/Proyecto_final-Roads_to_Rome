package util;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

public class InputManager extends InputAdapter{
	private boolean a, d, w, s;
    private boolean izq, der, arriba, abajo;
	private boolean click;
	private boolean e, q, f;
	

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Input.Keys.A) a = true;
        if (keycode == Input.Keys.D) d = true;
        if (keycode == Input.Keys.W) w = true;
        if (keycode == Input.Keys.S) s = true;
        if (keycode == Input.Keys.LEFT) izq = true;
        if (keycode == Input.Keys.RIGHT) der = true;
        if (keycode == Input.Keys.UP) arriba = true;
        if (keycode == Input.Keys.DOWN) abajo = true;
        if (keycode == Input.Keys.E) e = true;
        if (keycode == Input.Keys.Q) q = true;
        if (keycode == Input.Keys.F) f = true;
		return true;
	}
	@Override
	public boolean keyUp(int keycode) {
		 if (keycode == Input.Keys.A) a = false;
	     if (keycode == Input.Keys.D) d = false;
	     if (keycode == Input.Keys.W) w = false;
	     if (keycode == Input.Keys.S) s = false;
	     if (keycode == Input.Keys.LEFT) izq = false;
	     if (keycode == Input.Keys.RIGHT) der = false;
	     if (keycode == Input.Keys.UP) arriba = false;
	     if (keycode == Input.Keys.DOWN) abajo = false;
	     if (keycode == Input.Keys.E) e = false;
	     if (keycode == Input.Keys.Q) q = false;
	     if (keycode == Input.Keys.F) f = false;
		return true;
	}
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if(button == Input.Buttons.LEFT) {
			click = true;
			//System.out.println("clickeado");
		}
		return true;
	}
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if(button == Input.Buttons.LEFT) {
			click = false; 
			//System.out.println("dejo de clickear");
		}
		return true;
	}
	public void resetearClick() {
		click = false;
	}

	public boolean isIzquierda() {
		return izq||a;
	}
	public boolean isDerecha() {
		return der||d;
	}
	public boolean isArriba() {
		return arriba||w;
	}
	public boolean isAbajo() {
		return abajo||s;
	}
	public boolean isTirar() {
		//System.out.println(q);
		return q;
	}
	public boolean isInteractuar() {
		return e;
	}
	public boolean isClick() {
		//if(click) System.out.println("clickeando...");
		return click;
	}
	public boolean isCambiar() {
		return f;
	}
}
