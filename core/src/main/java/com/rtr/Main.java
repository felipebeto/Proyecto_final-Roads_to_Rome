package com.rtr;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import Imagenes.Imagen;
import Pantallas.PantallaIntro;
import util.Render;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
	private Imagen zubat1;
	private Imagen zubat2;
	private Imagen cargan2;
    private int cont = 0;

    @Override
    public void create() {
    	Render.app = this;
        zubat1 = new Imagen("snorlax1.png", 305, 90, 350, 350);
        zubat2 = new Imagen("snorlax2.png", 305, 90, 350, 350);
        cargan2 = new Imagen("cargandos.png", 305, 15, 350, 130);
        this.setScreen(new PantallaIntro());
    }

    @Override
    public void render() {
    	super.render();
        //ScreenUtils.clear(0.9f, 0.9f, 0.9f, 1f);
        //cont ++;
        //cargar(cont);
    }

    @Override
    public void dispose() {
        Render.batch.dispose();
    }
    private void cargar(int cont2) {
    	Render.batch.begin();
        if(cont%2 == 0) {
        	zubat1.dibujar();
        	//batch.draw(image, 175, 75, 350, 350);
        }else {
        	zubat2.dibujar();
        	//batch.draw(image2, 175, 75, 350, 350);
        }
        cargan2.dibujar();
        Render.batch.end();
    }
}
