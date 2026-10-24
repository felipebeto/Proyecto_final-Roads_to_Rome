package items;

import elementos.Imagen;

public abstract class Objeto {
	protected String nombre;
	protected Imagen imagen;

	public Objeto(String nombre, String ruta) {
		this.nombre = nombre;
		if(ruta != null) this.imagen = new Imagen(ruta);
	}
	public int getDanio() {
		return 0;
	}
	public float getCooldown() {
		return 0;
	}
	public int getEmpuje() {
		return 0;
	}
	public int getAlcance() {
		return 0;
	}
	public Imagen getImagen() {
		
		return imagen;
	}

}
