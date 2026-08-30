package Pantallas;

import com.badlogic.gdx.Screen;

import Imagenes.Imagen;
import util.Render;

public class PantallaCarga implements Screen{
	
	private Imagen img;
	private Imagen img2;
	private Imagen img3;
	private Imagen img4;
	private Imagen img5;
	private Imagen img6;
	private Imagen img7;
	private Imagen img8;
	private Imagen carga;
	private int cont = 0;
	private float tiempo =0;
	@Override
	public void show() {
		img = new Imagen("gambito/g1.png", 355, 140, 250, 320);
		img2 = new Imagen("gambito/g2.png", 355, 140, 250, 320);
		img3 = new Imagen("gambito/g3.png", 355, 140, 250, 320);
		img4 = new Imagen("gambito/g4.png", 355, 140, 250, 320);
		img5 = new Imagen("gambito/g5.png", 355, 140, 250, 320);
		img6 = new Imagen("gambito/g4.png", 355, 140, 250, 320);
		img7 = new Imagen("gambito/g3.png", 355, 140, 250, 320);
		img8 = new Imagen("gambito/g2.png", 355, 140, 250, 320);
		carga = new Imagen("cargandos.png", 305, 15, 350, 130);
	}
	
	private void cargar() {
    	Render.batch.begin();
        switch(cont) {
        	case 1: 
        		img.dibujar();
        		break;
        	case 2: 
        		img2.dibujar();
        		break;
        	case 3: 
        		img3.dibujar();
        		break;
        	case 4: 
        		img4.dibujar();
        		break;
        	case 5: 
        		img5.dibujar();
        		break;
        	case 6: 
        		img6.dibujar();
        		break;
        	case 7: 
        		img7.dibujar();
        		break;
        	case 8: 
        		img8.dibujar();
        		break;
        	default: 
        		break;
        }
        carga.dibujar();
        Render.batch.end();
    }

	@Override
	public void render(float delta) {

	    Render.limpiarPantalla(0.9f, 0.9f, 0.9f);
	    tiempo += delta;
	    if(tiempo >= 0.1f) {
	        cont++;
	        if(cont >= 8) {
	            cont = 1;
	        }
	        tiempo = 0;
	    }
	    cargar();
	}

	@Override
	public void resize(int width, int height) {
		
		
	}

	@Override
	public void pause() {
		
		
	}

	@Override
	public void resume() {
		
		
	}

	@Override
	public void hide() {
		
		
	}

	@Override
	public void dispose() {
		
		
	}

}
