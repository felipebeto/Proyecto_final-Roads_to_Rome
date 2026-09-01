package mapas;

import util.Recursos;

public class Dungeon1 extends Mapa{

	public Dungeon1() {
		super(Recursos.ancho, Recursos.alto, Recursos.FONDO_JUEGO);
		
	}



	@Override
	protected void cargarObstaculos() {
		//agregarObstaculo(395, 365, 65, 40); // cofre
        //agregarObstaculo(130, 350, 80, 40); // vasijas arriba-izq
        //agregarObstaculo(130, 270, 80, 60); // pila de huesos
        //agregarObstaculo(225, 320, 65, 40); // baba 1
        //agregarObstaculo(435, 315, 65, 40); // baba 2
        //agregarObstaculo(290, 215, 65, 40); // baba 3
        //agregarObstaculo(495, 165, 85, 35); // vasijas abajo-der
        // bloques de esquina (para que no se cuele por las zonas negras de la "cruz")
        agregarObstaculo(0, 378, 95, 177);     // esquina sup-izq
        agregarObstaculo(620, 378, 145, 177);  // esquina sup-der
        agregarObstaculo(0, 0, 95, 260);      // esquina inf-izq
        agregarObstaculo(620, 0, 145, 260);   // esquina inf-der
        
        agregarObstaculo(95, 460, 490, 110);   // arriba 
        agregarObstaculo(95, 0, 490, 90);   // abajo 
        System.out.println("Obstáculos cargados: " + obstaculos.size());
	}
	

}
