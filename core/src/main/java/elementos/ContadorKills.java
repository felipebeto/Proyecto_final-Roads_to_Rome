package elementos;

import util.Recursos;
import util.Render;

public class ContadorKills {
	private int kills = 0;
	private Texto cont = new Texto("0", 280, Recursos.alto - 40);
	

		public void actualizar(int cant) {
			cont.cambiarMsj(Integer.toString(kills));
			Render.batch.begin();
			cont.escribir();
			Render.batch.end();
		}


		public int getKills() {
			return kills;
		}
}