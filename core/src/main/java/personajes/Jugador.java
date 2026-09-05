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

public class Jugador extends Personaje {
    private Animation<TextureRegion> animacionCaminar;
    private TextureRegion frameQuieto;
    private float tiempoAnimacion = 0;
    private boolean mirandoDerecha = true;
    private boolean moviendose = false;
    private Animation<TextureRegion> animacionAtacar;
    private boolean atacando = false;
    private float tiempoAtaque = 0;
    private final float duracionAtaque = 0.32f;

    public Jugador() {
        super(Recursos.ancho/2-35, Recursos.alto/2-41, 100, 200, "gambit1.png", 70, 82, 100);
        cargarSpriteSheets();
    }

    private void cargarSpriteSheets() {
        Texture sheet = new Texture(Gdx.files.internal("sheetCaminando.png"));
        TextureRegion[] framesCaminando = TextureRegion.split(sheet, 56, 81)[0];
        frameQuieto = framesCaminando[0];
        animacionCaminar = new Animation<>(0.12f, framesCaminando);
        animacionCaminar.setPlayMode(Animation.PlayMode.LOOP);

        Texture sheetAtaque = new Texture(Gdx.files.internal("sheetGolpe.png"));
        TextureRegion[] framesAtaque = TextureRegion.split(sheetAtaque, 89, 77)[0];
        animacionAtacar = new Animation<>(0.08f, framesAtaque);
        animacionAtacar.setPlayMode(Animation.PlayMode.NORMAL);
    }

    @Override
    public void calcularMovimiento(float delta, Mapa mapa, Personaje enemigo) {
        if (atacando) {
            tiempoAtaque += delta;
            if (tiempoAtaque >= duracionAtaque) atacando = false;
            return;
        }

        float nuevaX = x, nuevaY = y;

        if (isRetrocediendo) {
            float[] pos = actualizarRetroceso(delta);
            nuevaX = pos[0];
            nuevaY = pos[1];
        } else {
            moviendose = false;
            if (Gdx.input.isKeyPressed(Input.Keys.A)) { nuevaX -= velocidad*delta; moviendose=true; mirandoDerecha=false; }
            if (Gdx.input.isKeyPressed(Input.Keys.D)) { nuevaX += velocidad*delta; moviendose=true; mirandoDerecha=true; }
            if (Gdx.input.isKeyPressed(Input.Keys.W)) { nuevaY += velocidad*delta; moviendose=true; }
            if (Gdx.input.isKeyPressed(Input.Keys.S)) { nuevaY -= velocidad*delta; moviendose=true; }
            if (moviendose) tiempoAnimacion += delta;
        }

        revisarHitbox(nuevaX, nuevaY, mapa);
        if (cooldownDanio > 0) cooldownDanio -= delta;
    }

    @Override
    public boolean recibirDanio(int cantidad) {
        if (cooldownDanio <= 0) {
            vida -= cantidad;
            cooldownDanio = 1;
            return true;
        }
        return false;
    }

    @Override
    public void atacar(Personaje enemigo) {
        if (!atacando) {
            atacando = true;
            tiempoAtaque = 0;
            if (enemigo.recibirDanio(50)) {
                enemigo.iniciarRetroceso(this);
            }
        }
    }

    @Override
    public void dibujar() {
        TextureRegion frameActual;
        if (atacando) frameActual = animacionAtacar.getKeyFrame(tiempoAtaque, false);
        else if (moviendose) frameActual = animacionCaminar.getKeyFrame(tiempoAnimacion, true);
        else frameActual = frameQuieto;

        sprite.setRegion(frameActual);
        sprite.setFlip(!mirandoDerecha);
        sprite.setPosicion(x, y);
        sprite.dibujar();
    }

	@Override
	public Personaje aparecer(Mapa mapa) {
		// TODO Auto-generated method stub
		return null;
	}
}