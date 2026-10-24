package personajes;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import items.Daga;
import items.Espada;
import items.Inventario;
import items.Objeto;
import mapas.Cofre;
import mapas.ObjetoTirado;
import mapas.Sala;
import util.Colisiones;
import util.InputManager;
import util.Recursos;

public class Jugador extends Personaje {
	private Inventario inventario = new Inventario();
	private float cooldownAtaque = 0;
	private float cooldownAccion = 0;
    private Animation<TextureRegion> animacionCaminar;
    private TextureRegion frameQuieto;
    private float tiempoAnimacion = 0;
    private boolean mirandoDerecha = true;
    private boolean moviendose = false;
    private Animation<TextureRegion> animacionAtacar;
    private boolean atacando = false;
    private float tiempoAtaque = 0;
    private final float DURACION_ATAQUE = 0.32f;
    private Texture sheetCaminar;
    private Texture sheetAtaque;
    private InputManager input = new InputManager();
    

    public Jugador() {
        super(500, 500, 100, 400, "gambit1.png", 39, 39, 100);
        cargarSpriteSheets();
        inventario.agregar(new Espada());
    }

    private void cargarSpriteSheets() {
        sheetCaminar = new Texture(Gdx.files.internal("sheetCaminando.png"));
        TextureRegion[] framesCaminando = TextureRegion.split(sheetCaminar, 56, 81)[0];
        frameQuieto = framesCaminando[0];
        animacionCaminar = new Animation<>(0.12f, framesCaminando);
        animacionCaminar.setPlayMode(Animation.PlayMode.LOOP);

        sheetAtaque = new Texture(Gdx.files.internal("sheetGolpe.png"));
        TextureRegion[] framesAtaque = TextureRegion.split(sheetAtaque, 89, 77)[0];
        animacionAtacar = new Animation<>(0.08f, framesAtaque);
        animacionAtacar.setPlayMode(Animation.PlayMode.NORMAL);
    }

    @Override
    public void calcularMovimiento(float delta, Sala mapa, Personaje enemigo) {
    	Gdx.input.setInputProcessor(input);
    	if (cooldownDanio > 0) cooldownDanio -= delta;
    	if (cooldownAtaque > 0) cooldownAtaque -= delta;
        if (atacando) {
            tiempoAtaque += delta;
            if (tiempoAtaque >= DURACION_ATAQUE) atacando = false;
            return;
        }

        float nuevaX = x, nuevaY = y;

        if (isRetrocediendo) {
            float[] pos = actualizarRetroceso(delta);
            nuevaX = pos[0];
            nuevaY = pos[1];
        } else {
            moviendose = false;
            if (input.isIzquierda()) { nuevaX -= velocidad*delta; moviendose=true; mirandoDerecha=false; }
            if (input.isDerecha()) { nuevaX += velocidad*delta; moviendose=true; mirandoDerecha=true; }
            if (input.isArriba()) { nuevaY += velocidad*delta; moviendose=true; }
            if (input.isAbajo()) { nuevaY -= velocidad*delta; moviendose=true; }
            if (moviendose) tiempoAnimacion += delta;
        }

        revisarHitbox(nuevaX, nuevaY, mapa);
        
    }
    @Override
    public void calcularAcciones(Sala mapa, float delta) {
    	if(cooldownAccion >0) {
    		cooldownAccion-=delta;
    	}else {
    		cooldownAccion = 0.5f;
    		if(input.isTirar()) tirarObjeto(mapa);
    		if(input.isCambiar()) inventario.cambiarObjeto();
    		if(input.isInteractuar()) interactuar(mapa);
    	}
    	
    	System.out.println("slot actual: "+inventario.getIndiceAct());
    }


	private void interactuar(Sala mapa) {
    	Cofre cofre = mapa.getCofre();
	    if (cofre != null && cofre.isVisible() && !cofre.isAbierto() && Colisiones.colisionaConEntidad(cofre.getHitbox(), hitbox)) {
	        cofre.abrir();
	        mapa.soltarObjeto(cofre.getX(), cofre.getY(), cofre.getContenido());
	        return;
	    }
	    agarrarObjeto(mapa);
	}

	@Override
	public boolean prepararAtaque() {
    	if(input.isClick()) return true;
    	return false;
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
    	if(cooldownAtaque>0) return;
        if (!atacando) {
            atacando = true;
            cooldownAtaque = this.inventario.getObjActual().getCooldown();
            System.out.println(this.inventario.getObjActual().getCooldown());
            tiempoAtaque = 0;
            if (enemigo.recibirDanio(this.inventario.getObjActual().getDanio())) {
                enemigo.iniciarRetroceso(this, this.inventario.getObjActual().getEmpuje());
            }
        }
    }

    @Override
    public void dibujar(SpriteBatch batch) {
        TextureRegion frameActual;
        if (atacando) frameActual = animacionAtacar.getKeyFrame(tiempoAtaque, false);
        else if (moviendose) frameActual = animacionCaminar.getKeyFrame(tiempoAnimacion, true);
        else frameActual = frameQuieto;

        sprite.setRegion(frameActual);
        sprite.setFlip(!mirandoDerecha);
        sprite.setPosicion(x, y);
        sprite.dibujar(batch);
    }

    @Override
    public void tirarObjeto(Sala sala) {
        Objeto objeto = inventario.tirar(inventario.getIndiceAct());
        if (objeto != null) sala.soltarObjeto(x, y, objeto);
    }
    @Override
    public void agarrarObjeto(Sala sala) {
        if (!inventario.isLleno()) {
        	ObjetoTirado objeto = sala.revisarObjeto(this);
        	if (objeto != null) {
        		inventario.agregar(objeto.getObjeto());
        		sala.quitarObjeto(objeto);
        	}
        }
        
    }
    
	@Override
	public void dispose() {
		super.dispose();
		sheetCaminar.dispose();
		sheetAtaque.dispose();
	}
	@Override
	public Inventario getInventario() {
		return inventario;
	}

}