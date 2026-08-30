package com.rtr;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import Pantallas.PantallaIntro;
import elementos.Imagen;
import util.Render;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
	

    @Override
    public void create() {
    	Render.app = this;
        this.setScreen(new PantallaIntro());
    }

    @Override
    public void render() {
    	super.render();
        
    }

    @Override
    public void dispose() {
        Render.batch.dispose();
    }
    
}
