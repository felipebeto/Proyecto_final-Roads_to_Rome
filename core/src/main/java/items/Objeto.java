package items;

public abstract class Objeto {
	protected String nombre;

	public Objeto(String nombre) {
		this.nombre = nombre;
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

}
