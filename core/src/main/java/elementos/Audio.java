package elementos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class Audio {
	private Music musica;
	private float volumen;

	public Audio(String ruta) {
		this.musica = Gdx.audio.newMusic(Gdx.files.internal(ruta));
		this.musica.setLooping(true);
		this.volumen = 0.5f;
		this.musica.setVolume(volumen);
	}
	public void comenzar() {
		this.musica.play();
	}
	public void cambiarVolumen(float num) {
		this.volumen = num;
	}
	public void detener() {
		this.musica.stop();
	}

}
