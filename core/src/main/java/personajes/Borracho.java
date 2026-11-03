package personajes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import items.Inventario;
import mapas.Sala;

public class Borracho extends Enemigo{

	private Texture sheetCaminar;
	public Borracho(float x, float y) {
		super(x, y, "momo.png", 120, 2, 20, 40, 45);
	}
	@Override
	public Inventario getInventario() {
		
		return null;
	}
	@Override
	public void tirarObjeto(Sala sala) {
	}
	@Override
	public void agarrarObjeto(Sala sala) {
	}
	@Override
	public void calcularAcciones(Sala mapa, float delta) {
	}
	@Override
	protected void cargarSheet() {
		sheetCaminar = new Texture(Gdx.files.internal("vagacamina.png"));
        TextureRegion[] framesCaminando = TextureRegion.split(sheetCaminar, 64, 80)[0];
        frameQuieto = framesCaminando[0];
        animacionCaminar = new Animation<>(0.12f, framesCaminando);
        animacionCaminar.setPlayMode(Animation.PlayMode.LOOP);
	}

}
