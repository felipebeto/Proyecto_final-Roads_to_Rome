package cartas;

import elementos.Imagen;

public class Modificador {
	private String nombre;
	private Imagen img;
	private boolean activo;
	public Modificador(String nombre, String ruta) {
		this.nombre = nombre;
		this.img = new Imagen(ruta);
		this.activo = false;
	}
	public String getNombre() {
		return nombre;
	}
	public Imagen getImg() {
		return img;
	}
	public boolean isActivo() {
		return activo;
	}
	public void activar() {
		this.activo = true;
		
	}

}
