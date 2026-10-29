package personajes;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import items.Daga;
import items.Dios;
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
    private float tiempoAnimacion = 0;
    private boolean mirandoDerecha = true;
    private boolean moviendose = false;
    private boolean atacando = false;
    private float tiempoAtaque = 0;
    private final float DURACION_ATAQUE = 0.32f;
    
    private TextureRegion frameQuieto;
    private TextureRegion frameQuietoEspada;
    private TextureRegion frameQuietoDaga;
    private TextureRegion frameQuietoLanza;
    private TextureRegion frameQuietoPocion;
    private TextureRegion frameQuietoLlave;
    
    private Animation<TextureRegion> animacionCaminar;
    private Animation<TextureRegion> animacionCaminarLlave;
    private Animation<TextureRegion> animacionCaminarPocion;
    private Animation<TextureRegion> animacionCaminarEspada;
    private Animation<TextureRegion> animacionCaminarDaga;
    private Animation<TextureRegion> animacionCaminarLanza;
    
    private Animation<TextureRegion> animacionAtacar;
    private Animation<TextureRegion> animacionEspada;
    private Animation<TextureRegion> animacionDaga;
    private Animation<TextureRegion> animacionLanza;
    
    private Texture sheetCaminar;
    private Texture sheetCaminarLlave;
    private Texture sheetCaminarPocion;
    private Texture sheetCaminarEspada;
    private Texture sheetCaminarDaga;
    private Texture sheetCaminarLanza;
    
    private Texture sheetGolpe;
    private Texture sheetEspadazo;
    private Texture sheetDagazo;
    private Texture sheetLanzazo;
    
    private InputManager input = new InputManager();
    

    public Jugador() {
        super(500, 500, 100, 400, "gambit1.png", 48, 60, 100);
        cargarSpriteSheets();
        
    }

    private void cargarSpriteSheets() {
        sheetCaminar = new Texture(Gdx.files.internal("sheetCaminan2.png"));
        TextureRegion[] framesCaminando = TextureRegion.split(sheetCaminar, 64, 80)[0];
        frameQuieto = framesCaminando[0];
        animacionCaminar = new Animation<>(0.12f, framesCaminando);
        animacionCaminar.setPlayMode(Animation.PlayMode.LOOP);
        
        sheetCaminarLlave = new Texture(Gdx.files.internal("sheetCaminan2.png"));
        TextureRegion[] framesCaminandoLlave = TextureRegion.split(sheetCaminarLlave, 64, 80)[0];
        frameQuietoLlave = framesCaminandoLlave[0];
        animacionCaminarLlave = new Animation<>(0.12f, framesCaminandoLlave);
        animacionCaminarLlave.setPlayMode(Animation.PlayMode.LOOP);
        
        sheetCaminarPocion = new Texture(Gdx.files.internal("sheetCaminan2.png"));
        TextureRegion[] framesCaminandoPocion = TextureRegion.split(sheetCaminarPocion, 64, 80)[0];
        frameQuietoPocion = framesCaminandoPocion[0];
        animacionCaminarPocion = new Animation<>(0.12f, framesCaminandoPocion);
        animacionCaminarPocion.setPlayMode(Animation.PlayMode.LOOP);
        
        sheetCaminarEspada = new Texture(Gdx.files.internal("sheetCaminan2.png"));
        TextureRegion[] framesCaminandoEspada = TextureRegion.split(sheetCaminarEspada, 64, 80)[0];
        frameQuietoEspada = framesCaminandoEspada[0];
        animacionCaminarEspada = new Animation<>(0.12f, framesCaminandoEspada);
        animacionCaminarEspada.setPlayMode(Animation.PlayMode.LOOP);
        
        sheetCaminarDaga = new Texture(Gdx.files.internal("sheetCaminan2.png"));
        TextureRegion[] framesCaminandoDaga = TextureRegion.split(sheetCaminarDaga, 64, 80)[0];
        frameQuietoDaga = framesCaminandoDaga[0];
        animacionCaminarDaga = new Animation<>(0.12f, framesCaminandoDaga);
        animacionCaminarDaga.setPlayMode(Animation.PlayMode.LOOP);
        
        sheetCaminarLanza = new Texture(Gdx.files.internal("sheetCaminan2.png"));
        TextureRegion[] framesCaminandoLanza = TextureRegion.split(sheetCaminarLanza, 64, 80)[0];
        frameQuietoLanza = framesCaminandoLanza[0];
        animacionCaminarLanza = new Animation<>(0.12f, framesCaminandoLanza);
        animacionCaminarLanza.setPlayMode(Animation.PlayMode.LOOP);

        sheetGolpe = new Texture(Gdx.files.internal("sheetgolp.png"));
        TextureRegion[] framesAtaque = TextureRegion.split(sheetGolpe, 72, 80)[0];
        animacionAtacar = new Animation<>(0.08f, framesAtaque);
        animacionAtacar.setPlayMode(Animation.PlayMode.NORMAL);
        
        sheetEspadazo = new Texture(Gdx.files.internal("sheetEspada.png"));
        TextureRegion[] framesEspada = TextureRegion.split(sheetEspadazo, 72, 80)[0];
        animacionEspada = new Animation<>(0.08f, framesEspada);
        animacionEspada.setPlayMode(Animation.PlayMode.NORMAL);
        
        sheetDagazo = new Texture(Gdx.files.internal("sheetDaga.png"));
        TextureRegion[] framesDaga = TextureRegion.split(sheetDagazo, 72, 80)[0];
        animacionDaga = new Animation<>(0.08f, framesDaga);
        animacionDaga.setPlayMode(Animation.PlayMode.NORMAL);
        
        sheetLanzazo = new Texture(Gdx.files.internal("sheetLanza.png"));
        TextureRegion[] framesLanza = TextureRegion.split(sheetLanzazo, 72, 80)[0];
        animacionLanza = new Animation<>(0.08f, framesLanza);
        animacionLanza.setPlayMode(Animation.PlayMode.NORMAL);
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
    		
    		if(input.isTirar()) {
    			tirarObjeto(mapa);
    			cooldownAccion = 0.5f;
    		}
    		if(input.isCambiar()) {
    			inventario.cambiarObjeto();
    			cooldownAccion = 0.5f;
    		}
    		if(input.isInteractuar()) {
    			interactuar(mapa);
    			cooldownAccion = 0.5f;
    		}
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
	    inventario.getObjActual().usar(this, mapa);
	}

	@Override
	public boolean prepararAtaque() {
    	if(input.isClick()&& cooldownAtaque<=0 && !atacando) return true;
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
	public void atacar() {
		atacando = true;
		cooldownAtaque = this.inventario.getObjActual().getCooldown();
		tiempoAtaque = 0;

	}

	@Override
    public void daniar(Personaje enemigo) {
    	
    	if (enemigo.recibirDanio(this.inventario.getObjActual().getDanio())) {
                enemigo.iniciarRetroceso(this, this.inventario.getObjActual().getEmpuje());
        }
    }

    @Override
    public void dibujar(SpriteBatch batch) {
        TextureRegion frameActual;
        if (atacando) {
        	if(inventario.getObjActual().getNombre().equals("Espada")) {
        		frameActual = animacionEspada.getKeyFrame(tiempoAtaque, false);
        	}else if(inventario.getObjActual().getNombre().equals("Lanza")) {
        		frameActual = animacionLanza.getKeyFrame(tiempoAtaque, false);
        	}else if(inventario.getObjActual().getNombre().equals("Daga")) {
        		frameActual = animacionDaga.getKeyFrame(tiempoAtaque, false);
        	}else {
        		frameActual = animacionAtacar.getKeyFrame(tiempoAtaque, false);
        	}
        	
        }
        else if (moviendose) {
        	if(inventario.getObjActual().getNombre().equals("Espada")) {
        		
        	}else if(inventario.getObjActual().getNombre().equals("Lanza")) {
        		
        	}else if(inventario.getObjActual().getNombre().equals("Daga")) {
        		
        	}else if(inventario.getObjActual().getNombre().equals("Pocion")) {
        		
        	}else if(inventario.getObjActual().getNombre().equals("Llave")) {
        		
        	}else {
        		frameActual = animacionAtacar.getKeyFrame(tiempoAtaque, false);
        	}
        	frameActual = animacionCaminar.getKeyFrame(tiempoAnimacion, true);
        }
        else {
        	if(inventario.getObjActual().getNombre().equals("Espada")) {
        		
        	}else if(inventario.getObjActual().getNombre().equals("Lanza")) {
        		
        	}else if(inventario.getObjActual().getNombre().equals("Daga")) {
        		
        	}else if(inventario.getObjActual().getNombre().equals("Pocion")) {
        		
        	}else if(inventario.getObjActual().getNombre().equals("Llave")) {
        		
        	}else {
        		frameActual = frameQuieto;
        	}
        	frameActual = frameQuieto;
        }

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
        sheetGolpe.dispose();
        inventario.dispose();
    }

    @Override
    public Inventario getInventario() {
        return inventario;
    }

}