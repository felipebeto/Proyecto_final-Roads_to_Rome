package com.rtr;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    private SpriteBatch batch;
    private Texture image;
    private Texture image2;
    private Texture loading;
    private int cont = 0;

    @Override
    public void create() {
        batch = new SpriteBatch();
        image = new Texture("zubat.png");
        image2 = new Texture("zubat2.png");
        loading = new Texture("cargandos.png");
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.9f, 0.9f, 0.9f, 1f);
        cont ++;
        if(cont%2 == 0) {
        	batch.begin();
        	batch.draw(image, 175, 75, 350, 350);
        	batch.end();
        }else {
        	batch.begin();
        	batch.draw(image2, 175, 75, 350, 350);
        	batch.end();
        }
        batch.begin();
    	batch.draw(loading, 175, 15, 350, 130);
    	batch.end();
        
        
    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
    }
}
