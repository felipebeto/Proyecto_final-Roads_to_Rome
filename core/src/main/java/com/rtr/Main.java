package com.rtr;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pantallas.PantallaMenuPrincipal;
import util.InputManager;


public class Main extends Game {
	private InputManager input;
    private Main main;
    private SpriteBatch batch;

    @Override
    public void create() {
       batch = new SpriteBatch();
       main = this;
       input = new InputManager();
	Gdx.input.setInputProcessor(input);
       batch = new SpriteBatch();
       this.setScreen(new PantallaMenuPrincipal(main, batch, input));
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
