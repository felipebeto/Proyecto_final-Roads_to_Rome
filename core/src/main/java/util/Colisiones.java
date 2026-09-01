package util;

import java.util.ArrayList;

import com.badlogic.gdx.math.Rectangle;

public class Colisiones {
	public static boolean colisionaConAlguno(Rectangle sujeto, ArrayList<Rectangle> obstaculos) {
        for (Rectangle o : obstaculos) {
            if (sujeto.overlaps(o)) return true;
        }
        return false;
    }
	public static boolean colisionaConEntidad(Rectangle entidad1, Rectangle entidad2) {
		if(entidad1.overlaps(entidad2)) return true;
		return false;
	}
}
