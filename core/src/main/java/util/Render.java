package util;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.rtr.Main;

public class Render {
	
	public static SpriteBatch batch = new SpriteBatch();
	public static Main app;
	public static void limpiarPantalla(float r, float g, float b) {
		ScreenUtils.clear(r, g, b, 1f);
	}
	
}
