package items;

import java.util.ArrayList;

import enums.Modificadores;

public class Inventario {
	private Objeto[] contenido = new Objeto[3];
	private Objeto objetoAct;
	private int indiceAct = 0;
	
	public void agregar(Objeto o) {
		for(int i = 0;i<contenido.length;i++) {
			if(!isOcupado(i)) {
				if(i == 2 && !Modificadores.P5.isActivo()) return;
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
		if(Modificadores.P5.isActivo()) return isOcupado(0) && isOcupado(1) && isOcupado(2);
		return isOcupado(0) && isOcupado(1);
	}
	public void cambiarObjeto() {
		if (indiceAct ==0) {
			indiceAct = 1;
			objetoAct = contenido[1];
		}else if(indiceAct == 1){
			if(Modificadores.P5.isActivo()) {
				indiceAct = 2;
				objetoAct = contenido[2];
			}else {
				indiceAct = 0;
				objetoAct = contenido[0];
			}
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
		if(Modificadores.P5.isActivo()) return !isOcupado(0) && !isOcupado(1) && !isOcupado(2);
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
