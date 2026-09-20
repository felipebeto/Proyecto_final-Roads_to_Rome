package Enums;

import util.Recursos;

public enum Finales {
	VICTORIA(Recursos.YOUWIN, Recursos.MUSICA_YOUWIN, "Ganaste, felicidades!"), DERROTA(Recursos.GAMEOVER, Recursos.MUSICA_GAMEOVER, "Game Over.");

	private String fondo;
	private String musica;
	private String msj;

	Finales(String fondo, String musica, String msj) {
		this.fondo = fondo;
		this.musica = musica;
		this.msj = msj;
	}

	public String getFondo() {
		return fondo;
	}

	public String getMsj() {
		return msj;
	}

	public String getMusica() {
		return musica;
	}

}
