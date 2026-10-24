package items;

import elementos.Imagen;

public class Arma extends Objeto{

	protected int danio;
	protected int alcance;
	protected int empuje;
	protected float cooldown;
	
	public Arma(String nombre, String ruta, int danio, int alcance, int empuje, float cooldown) {
		super(nombre, ruta);
		this.danio = danio;
		this.alcance = alcance;
		this.empuje = empuje;
		this.cooldown = cooldown;
	}
	@Override
	public int getDanio() {
		return danio;
	}
	@Override
	public float getCooldown() {
		return cooldown;
	}
	@Override
	public int getEmpuje() {
		return empuje;
	}
	@Override
	public int getAlcance() {
		return alcance;
	}

}
