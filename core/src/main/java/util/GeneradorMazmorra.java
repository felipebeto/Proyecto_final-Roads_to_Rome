package util;

import Enums.Direccion;
import mapas.*;

public class GeneradorMazmorra {
	public static Mazmorra generar(int cantSalas) {
		Mazmorra mazmorra = new Mazmorra();
		Sala salaAnt;
		boolean conecta;
		int cont = 0;
		for(int i =0; i<cantSalas; i++) {
			cont = 0;
			if(i==0) {
				Sala primera = new SalaInicio();
				primera.setearEntrada(null);
				mazmorra.salas.add(primera);
			}else {
				salaAnt=mazmorra.salas.get(i-1);
				if(i+1==cantSalas) {
					Sala ultima = new SalaFinal();
					ultima.setearEntrada(ultima.getPuerta1());
					mazmorra.salas.add(ultima);
				}else if(i+2==cantSalas){
					do {
						cont++;
						conecta = false;
						Sala salaNueva = elegirSala();
						if(!salaAnt.usado1 && salaNueva.getPuerta1().getOpuesto() == salaAnt.getPuerta1() && salaNueva.getPuerta2()==Direccion.ABAJO) {
							conecta = true;
							salaNueva.setearUsado1();
							salaNueva.setearEntrada(salaNueva.getPuerta1());
							mazmorra.salas.add(salaNueva);
						}else {
							if(!salaAnt.usado1 && salaNueva.getPuerta2().getOpuesto() == salaAnt.getPuerta1() && salaNueva.getPuerta1()==Direccion.ABAJO) {
								conecta = true;
								salaNueva.setearUsado2();
								salaNueva.setearEntrada(salaNueva.getPuerta2());
								mazmorra.salas.add(salaNueva);
							}
							else {
								if(!salaAnt.usado2 && salaNueva.getPuerta1().getOpuesto() == salaAnt.getPuerta2() && salaNueva.getPuerta2()==Direccion.ABAJO) {
									conecta = true;
									salaNueva.setearUsado1();
									salaNueva.setearEntrada(salaNueva.getPuerta1());
									mazmorra.salas.add(salaNueva);
								}else {
									if(!salaAnt.usado2 && salaNueva.getPuerta2().getOpuesto() == salaAnt.getPuerta2() && salaNueva.getPuerta1()==Direccion.ABAJO) {
										conecta = true;
										salaNueva.setearUsado2();
										salaNueva.setearEntrada(salaNueva.getPuerta2());
										mazmorra.salas.add(salaNueva);
									}
								}
							}
						}
					}while(!conecta && cont<200);
					if(!conecta) {
						System.out.println("demora");
						return generar(cantSalas);
					}
				}else {
					do {
						cont++;
						conecta = false;
						Sala salaNueva = elegirSala();
						if(!salaAnt.usado1 && salaNueva.getPuerta1().getOpuesto() == salaAnt.getPuerta1()) {
							conecta = true;
							salaNueva.setearUsado1();
							salaNueva.setearEntrada(salaNueva.getPuerta1());
							mazmorra.salas.add(salaNueva);
						}else {
							if(!salaAnt.usado1 && salaNueva.getPuerta2().getOpuesto() == salaAnt.getPuerta1()) {
								conecta = true;
								salaNueva.setearUsado2();
								salaNueva.setearEntrada(salaNueva.getPuerta2());
								mazmorra.salas.add(salaNueva);
							}
							else {
								if(!salaAnt.usado2 && salaNueva.getPuerta1().getOpuesto() == salaAnt.getPuerta2()) {
									conecta = true;
									salaNueva.setearUsado1();
									salaNueva.setearEntrada(salaNueva.getPuerta1());
									mazmorra.salas.add(salaNueva);
								}else {
									if(!salaAnt.usado2 && salaNueva.getPuerta2().getOpuesto() == salaAnt.getPuerta2()) {
										conecta = true;
										salaNueva.setearUsado2();
										salaNueva.setearEntrada(salaNueva.getPuerta2());
										mazmorra.salas.add(salaNueva);
									}
								}
							}
						}
					}while(!conecta && cont<200);
					if(!conecta) {
						System.out.println("demora");
						return generar(cantSalas);
					}
				}
			}
			
		}
		return mazmorra;
	}

	private static Sala elegirSala() {
		int opc = Aleatorio.generarEntero(6);
		switch (opc) {
		case 0:
			return new SalaDL();
		case 1:
			return new SalaDR();
		case 2:
			return new SalaLR();
		case 3:
			return new SalaUR();
		case 4:
			return new SalaUD();
		case 5:
			return new SalaUL();
		default:
			return new SalaDL();
		}
	}

}
