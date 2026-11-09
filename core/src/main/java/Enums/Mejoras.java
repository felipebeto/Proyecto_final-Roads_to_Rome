package Enums;

import java.util.Map;

public enum Mejoras {
	VIDA("vida", "Vida", " de vida", 20),
	DANIO("danio", "Daño base", " de daño", 5),
	VELOCIDAD("velocidad", "Velocidad base", " de velocidad", 5);

	public static final int NIVEL_MAXIMO = 5;
	private static final int COSTO_BASE = 50;

	private final String id;
	private final String titulo;
	private final String unidad;
	private final int bonusPorNivel;

	Mejoras(String id, String titulo, String unidad, int bonusPorNivel) {
		this.id = id;
		this.titulo = titulo;
		this.unidad = unidad;
		this.bonusPorNivel = bonusPorNivel;
	}

	public String getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getUnidad() {
		return unidad;
	}

	/** lo q multiplica la vida, daño o velocidad */
	public int getBonus(int nivel) {
		return bonusPorNivel * nivel;
	}

	/** el oro q cuesta, x cada nivel se duplica el costo */
	public int getCosto(int nivelActual) {
		return COSTO_BASE * (nivelActual + 1);
	}

	/** nivel de la mejora, para poder ponerlo en el perfil */
	public int getNivel(Map<String, Integer> mejoras) {
		Integer nivel = mejoras.get(id);
		return nivel == null ? 0 : nivel;
	}
}
