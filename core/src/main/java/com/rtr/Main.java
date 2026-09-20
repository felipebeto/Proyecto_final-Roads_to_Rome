package com.rtr;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

import pantallas.PantallaMenuPrincipal;
import util.Recursos;
import util.Render;


public class Main extends Game {
	
    private Main main;
    private SpriteBatch batch;

    @Override
    public void create() {
       batch = new SpriteBatch();
       main = this;
       batch = new SpriteBatch();
       this.setScreen(new PantallaMenuPrincipal(main, batch));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
