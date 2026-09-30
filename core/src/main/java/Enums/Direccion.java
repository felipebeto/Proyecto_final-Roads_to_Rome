package Enums;

public enum Direccion {
	ARRIBA(), ABAJO(), IZQUIERDA(), DERECHA();
	public Direccion getOpuesto() {
		switch (this) {
        case ARRIBA: return ABAJO;
        case ABAJO: return ARRIBA;
        case IZQUIERDA: return DERECHA;
        case DERECHA: return IZQUIERDA;
        default: return ABAJO; 
		}
	}
}
