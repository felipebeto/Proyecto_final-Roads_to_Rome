package items;

import java.util.ArrayList;

public class Inventario {
	private Objeto[] contenido = new Objeto[2];
	private Objeto objetoAct;
	private int indiceAct = 0;
	
	public void agregar(Objeto o) {
		for(int i = 0;i<contenido.length;i++) {
			if(!isOcupado(i)) {
				contenido[i] = o;
				return;
			}
		}
		
	}
	private boolean isOcupado(int i) {
		return contenido[i] != null;
	}
	public Objeto tirar(int i) {
		Objeto objeto = contenido[i];
		contenido[i] = null;
		return objeto;
	}
	public boolean isLleno() {
		return isOcupado(0) && isOcupado(1);
	}
	public void cambiarObjeto() {
		if (indiceAct ==0) {
			indiceAct = 1;
			objetoAct = contenido[1];
		}else {
			indiceAct = 0;
			objetoAct = contenido[0];
		}
	}
	public Objeto getObjActual(){
		if(contenido[indiceAct] == null) return new Mano();
		return contenido[indiceAct];
	}
	public boolean isVacio() {
		return !isOcupado(0) && !isOcupado(1);
	}
	public int getIndiceAct() {
		
		return indiceAct;
	}
	public void dispose() {
		for(int i = 0;i<contenido.length;i++) {
			contenido[i].dispose();	
		}
	}
}
