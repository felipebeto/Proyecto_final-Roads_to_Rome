package enums;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import util.Aleatorio;

public enum Modificadores {
	N1("Minusculo", false), N2("Rodeado", false), N3("Pobre", false), N4("Debilitado", false), N5("Envenenado", false), N6("Cansado", false), 
	P1("Pacifista", true), P2("Invisible", true), P3("Temerario", true), P4("Curandero", true), P5("Coleccionista", true), P6("Avaricioso", true);
	
	private boolean activo;
	private String nombre;
	private boolean positivo;
	private boolean usado;
	private Modificadores(String nombre, boolean positivo) {
		this.activo = false;
		this.usado = false;
		this.nombre = nombre;
		this.positivo = positivo;
	}
	
	public boolean isActivo() {
		return activo;
	}
	public String getNombre() {
		return nombre;
	}
	public boolean isPositivo() {
		return positivo;
	}
	public void activar() {
		this.activo = true;
	}
	public void usar() {
		this.usado = true;
	}

	public Modificadores elegirModif(boolean valor) {
		boolean sirve = false;
		Modificadores modif;
		do {
			int num = Aleatorio.generarEntero(Modificadores.values().length);
			switch (num) {
				case 1: modif = N1;
				case 2: modif = N2;
				case 3: modif = N3;
				case 4: modif = N4;
				case 5: modif = N5;
				case 6: modif = N6;
				case 7: modif = P1;
				case 8: modif = P2;
				case 9: modif = P3;
				case 10: modif = P4;
				case 11: modif = P5;
        		case 12: modif = P6;
        		default: modif = N1; 
			}
			if(!modif.activo && modif.positivo == valor) sirve = true;
		}while(!sirve);
		return modif;
	}

	public void dibujar(SpriteBatch batch) {
		
		
	}

}
