package mapas;

import java.util.ArrayList;

public class Mazmorra {
	public ArrayList<Sala> salas = new ArrayList<>();
	public int indiceSala = 0;

	public Sala getSalaActual() {
		return salas.get(indiceSala);
	}

	public void avanzarSala() {
		if(!isUltima())indiceSala++;
	}
	public boolean isUltima() {
		return indiceSala==salas.size()-1;
	}

}
