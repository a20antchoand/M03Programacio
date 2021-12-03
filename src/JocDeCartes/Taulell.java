package JocDeCartes;

import java.util.ArrayList;
import java.util.List;

public class Taulell {
	
	// ATRIBUTS FINALS
	
	public final int LONGITUD = 30; 
	public final int CASELLAESPECIAL = 10;
	public final int ESPECIAL = 2;
	
	// ATRIBUTS ESTATICS
	
	public static int idJugador = 1;
	
	// ATRIBUTS DE TAULELL
	
	public ArrayList<Jugador> jugadors = new ArrayList<>();
	public int[] primerJugador = new int[LONGITUD]; 
	public List<Carta> baralla;
	
	/*
	 * INICIAR TAULELL
	 * 
	 * */
	
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
		
		
		
	}
	
	/*
	 * REPARTIR MA
	 * 
	 * */
	
	public void repartirMa(List<Carta> baralla) throws Exception {
		
		List<Carta> maActual;
		
		if (jugadors.size() == 3 || jugadors.size() == 4) {	
			
			for (Jugador j : jugadors) {
				maActual = new ArrayList<>();
				for (int i = 0; i < 5; i++) {
					
					maActual.add(baralla.get(baralla.size()-1));
					baralla.remove(baralla.size()-1);
					
				}
				
				j.setMa(maActual);
				
				
			}
			
		} else if (jugadors.size() == 5 || jugadors.size() == 6) {
			
			for (Jugador j : jugadors) {
				maActual = new ArrayList<>();
				for (int i = 0; i < 6; i++) {
					
					maActual.add(baralla.get(baralla.size()-1));
					baralla.remove(baralla.size()-1);
					
				}
				
				j.setMa(maActual);
				
			}
			
		} else {
			
			throw new Exception ("Numero de jugadors incorrectes.");
			
		}
	}
	
	/*
	 * MOSTRAR MA JUGADOR
	 * 
	 * */
	
	public void mostrarMa (int idJugador) {
		
		for (Jugador j : jugadors) {
			
			if (j.getId() == idJugador) {
				
				System.out.println("\nCartes de: " + j.getNom());
				System.out.println(j.getMa().size());
				
				for (int i = 0; i < j.getMa().size(); i++) {
					
					System.out.println(j.getMa().get(i).toString());
					
				}
				
			}
			
		}
		
	}
	
	/*
	 * VALIDAR CARTA I OPCIO
	 * 
	 * */
	
	public Carta validarCartaOpcio(Jugador j, int numCarta, int numOpcio) throws Exception {
		
		boolean cartaValidar = false;
		boolean opcioValidar = false;
		Carta cartaValidada = null;
		for (Carta carta : j.getMa()) {
			
			if (carta.getNumCarta() == numCarta && !cartaValidar) {
				
				cartaValidar = true;
				cartaValidada = carta;
			}
		}
		
		if (cartaValidar) {
			
			if (numOpcio >= 0 && numOpcio < Carta.numeroOpcions) {
				
					opcioValidar = true;
					
			} else {
				
				System.out.println("Opcio no valida");
				
			}
		}
		
	
		if (!cartaValidar || !opcioValidar) {
			
			throw new Exception ("Alguna de les opcions no es valida.");
			
		}
		
		return cartaValidada;
	}
	
	/*
	 * MOSTRAR OPCIO
	 * 
	 * */
	
	public Carta.tipusMoviment agafarOpcio(Jugador j, Carta carta, int numOpcio) {
		
		List<Carta> ma = j.getMa();
		Carta cartaEliminar = null;
		Carta.tipusMoviment opcio = null;
		
		opcio = carta.getOpcions()[numOpcio-1];
		cartaEliminar = carta;
		
		j.getMa().remove(cartaEliminar);
		j.setMa(ma);
		return opcio;
		
	}
	
	/*
	 * MOURE JUGADR
	 * 
	 * */
	
	public void moureJugador (int numJugador, int moviments, Carta carta, Carta.tipusMoviment opcio) throws Exception {
		
		Jugador jugador = jugadors.get(numJugador-1);
		
		if (moviments != 1 && moviments != -1) {
			
			throw new Exception("Numero de moviments incorrecte.");
			
		}	
		
		if (carta.isEspecial()) {
			if (opcio == Carta.tipusMoviment.MOUENDEVANT) {
				moviments = ESPECIAL;
			} else {
				moviments = (-ESPECIAL);
			}
		}
		
		int posActual = jugador.getPos();
		int posFinal = posActual + moviments;
				 
		if (posFinal > 0 && posFinal < LONGITUD) {
			jugador.setPos(posFinal);
		} else if (posFinal == LONGITUD) {
			Apocalipsis.guanyador = jugador;
		}
		
	}
	
	/*
	 * GESTIONAR VIDES
	 * 
	 * */
	
	public void gestionarVides (int numJugador, int vides, Carta carta, Carta.tipusMoviment opcio) throws Exception {
		
		Jugador jugador = jugadors.get(numJugador-1);
		
		if (vides != 1 && vides != -1) {
			
			throw new Exception("Numero de moviments incorrecte.");
			
		}
		
		if (carta.isEspecial()) {
			if (opcio == Carta.tipusMoviment.SUMAVIDA) {
				vides = ESPECIAL;
			} else {
				vides = (-ESPECIAL);
			}
			
		}
		
		int videsActual = jugador.getVides();
		int videsFinal = videsActual + vides;
				 
		if (videsFinal > 0) {
			jugador.setVides(videsFinal);
		} else if (videsFinal == 0) {
			jugadors.remove(jugador);
		}
		
	}
	
	/*
	 * VALIDAR JUGADOR
	 * 
	 * */
	
	public boolean validarJugador (int numJugador) {
		
		boolean resultat = false;
		
		for (Jugador aux : jugadors) {
			
			if (aux.getId() == numJugador) {
				
				resultat = true;
				
			}
		}
		
		return resultat;
		
	}
	
	/*
	 * TO STRING
	 * 
	 * */
	
	@Override
	public String toString () {
		StringBuilder resultat = new StringBuilder();
		resultat.append("\n");
		for (Jugador j : jugadors) {
			resultat.append(j.getId() + " - NOM: " + j.getNom() + " -> VIDES: " + j.getVides() + "\n");
		}
		resultat.append("\n");
		resultat.append("#");
		for (int i = 0; i < LONGITUD; i++) {
			resultat.append("===#");
		}
		resultat.append("\n");
		for (Jugador j : jugadors) {
			int posicioJugador =j.getPos();
			resultat.append("#");
			for (int i = 0; i < LONGITUD ; i++) {
				if (i == posicioJugador) {
					resultat.append(" " + j.getId() + " #");
				} else {
					resultat.append("   #");
				}
			}
			resultat.append("\n");
		}		
		resultat.append("#");
		for (int i = 0; i < LONGITUD; i++) {
			resultat.append("===#");
		}

		return resultat.toString();
	}
	
}
