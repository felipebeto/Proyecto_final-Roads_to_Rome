package elementos;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import personajes.Personaje;
import util.Recursos;

public class Camara {
	private OrthographicCamera camara = new OrthographicCamera();
	private Viewport vp = new FitViewport(Recursos.ancho, Recursos.alto, camara);
	public void actualizarPosicion(Personaje jugador, SpriteBatch batch) {
		camara.position.set(jugador.getX(), jugador.getY(), 0);
		camara.update();
		batch.setProjectionMatrix(camara.combined);	
	}
	public void actualizarPantalla(){
		vp.update(Recursos.ancho, Recursos.alto);
	}
	public void centrarPantalla(SpriteBatch batch) {
		camara.position.set(Recursos.ancho/2, Recursos.alto/2, 0);
		camara.update();
		batch.setProjectionMatrix(camara.combined);
	}

}
