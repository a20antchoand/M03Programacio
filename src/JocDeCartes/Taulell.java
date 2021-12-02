package JocDeCartes;

import java.util.ArrayList;
import java.util.List;

public class Taulell {
	
	public final int longitud = 30; 
	public final int casellaEspecial = 10;
	
	public static int idJugador = 1;
	
	public ArrayList<Jugador> jugadors = new ArrayList<>();
	public List<Carta> baralla;
	
	public Taulell (Jugador[] jugadors) throws Exception {
	
		if (jugadors.length < 3 ) {
			throw new IllegalArgumentException("Numero de jugadors invalid, minim 3.");
		}
		
		if (jugadors.length > 6) {
			throw new IllegalArgumentException("Numero de jugadors invalid, maxim 6.");
		}
		
		
		for (Jugador jugador : jugadors) {
			this.jugadors.add(jugador);
		}
		
		this.baralla = Carta.generarBaralla();
		
	}
	
	public void repartirMa(List<Carta> baralla) throws Exception {
		
		List<Carta> ma = new ArrayList<>();
		
		if (jugadors.size() == 3 || jugadors.size() == 4) {	
			for (Jugador j : jugadors) {
				for (int i = 0; i < Carta.numCartes34; i++) {
					ma.add(baralla.get(baralla.size()-1));
					baralla.remove(baralla.size()-1);
				}
				j.setMa(ma);
			}
		} else if (jugadors.size() == 5 || jugadors.size() == 6) {
			for (Jugador j : jugadors) {
				for (int i = 0; i < Carta.numCartes56; i++) {
					ma.add(baralla.get(baralla.size()-1));
					baralla.remove(baralla.size()-1);
				}
				j.setMa(ma);
			}
		} else {
			throw new Exception ("Numero de jugadors incorrectes.");
		}
		
	}
	
	public void mostrarMa (int idJugador) {
		
		List<Carta> ma;
		
		for (Jugador j : jugadors) {
			if (j.getId() == idJugador) {
				ma = j.getMa();
				System.out.println("\nCartes de: " + j.getNom());
				for (int i = 0; i < ma.size(); i++) {
					System.out.println(ma.get(i).toString());
				}
			}
		}
	}
	
	public boolean validarCartaOpcio(Jugador j, int numCarta, int numOpcio) throws Exception {
		
		boolean cartaValidar = false;
		boolean opcioValidar = false;
		boolean resultat = false;
		
		for (Carta carta : j.getMa()) {
			if (carta.getNumCarta() == numCarta && !cartaValidar) {
				cartaValidar = true;
			}
		}
		
		if (cartaValidar) {
			if (numOpcio >= 0 && numOpcio < Carta.numeroOpcions) {
					opcioValidar = true;
			} else {
				System.out.println("Opcio no valida");
			}
		}
		
	
		if (cartaValidar && opcioValidar) {
			resultat = true;
			mostrarOpcio(j, numCarta, numOpcio);
		} else {
			System.out.println("Alguna de les dades no es valida");
		}
		
		return resultat;
	}
	
	public void mostrarOpcio(Jugador j, int numCarta, int numOpcio) {
		List<Carta> maAux;
		List<Carta> ma = j.getMa();
		for (Carta carta : ma) {
			if (carta.getNumCarta() == numCarta) {
				
				System.out.println("Opcio: " + carta.getOpcions()[numOpcio-1]);
				maAux = ma;
				maAux.remove(carta);
				j.setMa(maAux);
				
			}
		}
	}
	
	public void moureJugador (Jugador jugador, int moviments) throws Exception {
		
		if (moviments != 1 && moviments != 2 && moviments != -1 && moviments != -2) {
			throw new Exception("Numero de moviments incorrecte.");
		}	
	
		int posActual = jugador.getPos();
		int posFinal = posActual + moviments;
				 
		if (posFinal > 0 && posFinal < longitud) {
			jugador.setPos(posFinal);
		} else if (posFinal == longitud) {
			Apolcalipsis.guanyador = jugador;
		}
		
	}
	
	public String toString () {
		StringBuilder resultat = new StringBuilder();
		
		for (Jugador j : jugadors) {
			resultat.append(j.getId() + " - " + j.getNom() + "\n");
		}
		
		resultat.append("#");
		for (int i = 0; i < longitud; i++) {
			resultat.append("===#");
		}
		resultat.append("\n");
		for (Jugador j : jugadors) {
			int posicioJugador =j.getPos();
			resultat.append("#");
			for (int i = 0; i < longitud ; i++) {
				if (i == posicioJugador) {
					resultat.append(" " + j.getId() + " #");
				} else {
					resultat.append("   #");
				}
			}
			resultat.append("\n");
		}		
		resultat.append("#");
		for (int i = 0; i < longitud; i++) {
			resultat.append("===#");
		}

		return resultat.toString();
	}


	
}
