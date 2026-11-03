package personajes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import items.Inventario;
import mapas.Sala;

public class Esqueleto extends Enemigo{

	private Texture sheetCaminar;

	public Esqueleto(float x, float y) {
		super(x, y, "esqueleto.png", 60, 0.5f, 55, 15, 80);
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
		sheetCaminar = new Texture(Gdx.files.internal("esqcamina.png"));
        TextureRegion[] framesCaminando = TextureRegion.split(sheetCaminar, 72, 84)[0];
        frameQuieto = framesCaminando[0];
        animacionCaminar = new Animation<>(0.12f, framesCaminando);
        animacionCaminar.setPlayMode(Animation.PlayMode.LOOP);
	}

}
