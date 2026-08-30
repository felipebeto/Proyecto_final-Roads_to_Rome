package com.rtr;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

import Pantallas.PantallaMenuPrincipal;
import util.Render;


public class Main extends Game {
	
    public BitmapFont fuente;

    @Override
    public void create() {
       Render.batch = new SpriteBatch();
       fuente = new BitmapFont();
       this.setScreen(new PantallaMenuPrincipal());
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        Render.batch.dispose();
        fuente.dispose();
    }
}
