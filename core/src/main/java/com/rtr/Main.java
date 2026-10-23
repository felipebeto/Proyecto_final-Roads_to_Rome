package com.rtr;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pantallas.PantallaMenuPrincipal;
import util.InputManager;
import datos.BaseDatos;
import datos.Perfil;
import pantallas.PantallaNombre;

public class Main extends Game {
	private InputManager input;
    private Main main;
    private SpriteBatch batch;

    @Override
    public void create() {
       BaseDatos.iniciar();
       batch = new SpriteBatch();
       main = this;
       input = new InputManager();
	Gdx.input.setInputProcessor(input);
       batch = new SpriteBatch();
       //cambio
       if (Perfil.getNombre() == null) {
           this.setScreen(new PantallaNombre(main, batch, input));
        } else {
           this.setScreen(new PantallaMenuPrincipal(main, batch, input));
        }
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
