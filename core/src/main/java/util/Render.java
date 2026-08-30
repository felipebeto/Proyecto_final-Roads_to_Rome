package util;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Render {
	public static SpriteBatch batch;
	public static void limpiar(float r, float g, float b) {
		ScreenUtils.clear(r, g, b, 1f);
	}

}
