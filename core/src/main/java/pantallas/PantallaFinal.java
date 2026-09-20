package pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.rtr.Main;
import elementos.Audio;
import elementos.Imagen;
import elementos.Texto;
import util.InputManager;
import util.Render;
import Enums.*;

		public class PantallaFinal implements Screen{
			
			private Imagen fondo;
			private Texto titulo;
			private Texto subtitulo;
			private Texto kills;
			private int contK = 0;
			private boolean finFadeIn = false;
			private float a = 0;
			private Audio musica;
			private Finales f;
			private Main main;
			private SpriteBatch batch;
			private InputManager input;
			public PantallaFinal(int contK, Finales f, Main main, SpriteBatch batch, InputManager input) {
				this.contK = contK;
				this.f = f;
				this.main = main;
				this.batch = batch;
				this.input = input;
				Gdx.input.setInputProcessor(input);
			}
			
			@Override
			public void show() {
				fondo = new Imagen(f.getFondo());
				fondo.setTrans(a);
				fondo.ajustarTamaño();
				titulo = new Texto(f.getMsj(), 40, 450);
				titulo.agrandar(2);
				kills = new Texto("Kills: " + contK, 50, 410);
				subtitulo = new Texto("clickea la pantalla para volver al menú", 60, 350);
				musica = new Audio(f.getMusica());
				batch.setProjectionMatrix(new Matrix4().setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
			}
			private void calcularFade() {
				if(!finFadeIn ) {
					a+= 0.005f;
					if (a>1) {
						finFadeIn = true;
						a =1;
					}
				}
			}
			@Override
			public void render(float delta) {
				Render.limpiar(0, 0, 0);
				musica.comenzar();
				batch.begin();
				fondo.dibujar(batch);
				if (!finFadeIn) {
					calcularFade();
					fondo.setTrans(a);
					
				}else {
					titulo.escribir(batch);
					subtitulo.escribir(batch);
					if(contK!=0) {
						kills.escribir(batch);
					}
					
					
					if (input.isClick()) {
						musica.detener();
						main.setScreen(new PantallaMenuPrincipal(main, batch, input));
					}
					
				}
				
				batch.end();
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
				fondo.dispose();
				titulo.dispose();
				subtitulo.dispose();
				kills.dispose();
				musica.dispose();
			}
		}
