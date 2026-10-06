package items;

import java.util.ArrayList;

public class Inventario {
	private ArrayList<Objeto> contenido;
	private Objeto objetoAct;
	private int indiceAct = 1;
	public void agregar(Objeto o) {
		if(!isLleno()) {
			contenido.add(o);
			
		}
		
	}
	public Objeto tirar(int i) {
		Objeto o = contenido.get(i);
		contenido.remove(i);
		return o;
	}
	public boolean isLleno() {
		return (contenido.size() >=2);
	}
	public void cambiarObjeto() {
		if (indiceAct ==1) {
			indiceAct = 2;
			objetoAct = contenido.get(indiceAct);
		}else {
			indiceAct = 1;
			objetoAct = contenido.get(indiceAct);
		}
	}
	public Objeto getObjActual(){
		return objetoAct;
	}
	public boolean isVacio() {
		return contenido.isEmpty();
	}
}
