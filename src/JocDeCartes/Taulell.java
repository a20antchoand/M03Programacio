package JocDeCartes;

import java.util.ArrayList;
import java.util.List;

public class Taulell {
	
	// ATRIBUTS FINALS
	
	public final int LONGITUD = 10; 
	public final int CASELLAESPECIAL = 5;
	public final int VALORESPECIAL = 2;
	
	
	// ATRIBUTS ESTATICS
	
	public static int idJugador = 0;
	
	// ATRIBUTS DE TAULELL
	
	public int numeroJugadors;
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
		
		this.numeroJugadors = this.jugadors.size();
		
	}
	
	/*
	 * REPARTIR MA
	 * 
	 * */
	
	public void repartirMa(List<Carta> baralla) {
		
		List<Carta> maActual;
		
		if (numeroJugadors == 3 || numeroJugadors == 4) {	
			
			for (Jugador j : jugadors) {
				maActual = new ArrayList<>();
				for (int i = 0; i < Carta.NUMCARTES34; i++) {
					
					maActual.add(baralla.get(baralla.size()-1));
					baralla.remove(baralla.size()-1);
					
				}
				
				j.setMa(maActual);
				
				
			}
			
		} else if (numeroJugadors == 5 || numeroJugadors == 6) {
			
			for (Jugador j : jugadors) {
				maActual = new ArrayList<>();
				for (int i = 0; i < Carta.NUMCARTES56; i++) {
					
					maActual.add(baralla.get(baralla.size()-1));
					baralla.remove(baralla.size()-1);
					
				}
				
				j.setMa(maActual);
				
			}
			
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
	
	public Carta validarCartaOpcio(Jugador j, int numCarta, int numOpcio) {
		
		boolean cartaValidar = false;
		Carta cartaValidada = null;
		for (Carta carta : j.getMa()) {
			
			if (carta.getNumCarta() == numCarta && !cartaValidar) {
				
				cartaValidar = true;
				cartaValidada = carta;
			}
		}
		
		if (cartaValidar) {
			
			if (numOpcio < 0 || numOpcio > Carta.NUMEROOPCIONS) {
				
				System.out.println("Opcio no valida");
				
			}
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
		
		Jugador jugador = null;
		for (Jugador aux : jugadors) {
			if (aux.getId() == numJugador) {
				jugador = aux;
			}
		}
		
		if (moviments != 1 && moviments != -1) {
			
			throw new Exception("Numero de moviments incorrecte.");
			
		}	
		
		if (carta.isEspecial()) {
			if (opcio == Carta.tipusMoviment.MOUENDEVANT) {
				moviments = VALORESPECIAL;
			} else {
				moviments = (-VALORESPECIAL);
			}
		}
		
		int posActual = jugador.getPos();
		int posFinal = posActual + moviments;
				 
		if (posFinal >= 0 && posFinal <= LONGITUD) {
			jugador.setPos(posFinal);
		} else if (posFinal > LONGITUD) {
			jugador.setPos(LONGITUD);
		}
		
	}
	
	/*
	 * GESTIONAR VIDES
	 * 
	 * */
	
	public void gestionarVides (int numJugador, int vides, Carta carta, Carta.tipusMoviment opcio) throws Exception {
		
		Jugador jugador = null;
		for (Jugador aux : jugadors) {
			if (aux.getId() == numJugador) {
				jugador = aux;
			}
		}
		
		if (vides != 1 && vides != -1) {
			
			throw new Exception("Numero de moviments incorrecte.");
			
		}
		
		if (carta.isEspecial()) {
			if (opcio == Carta.tipusMoviment.SUMAVIDA) {
				vides = VALORESPECIAL;
			} else {
				vides = (-VALORESPECIAL);
			}
			
		}
				 
		jugador.setVides((jugador.getVides() + vides));

		
	}
	
	/*
	 * VALIDAR JUGADOR
	 * 
	 * */
	
	public boolean validarJugador (int numJugador) {
		
		boolean resultat = false;
		if (numJugador >= 0 && numJugador <= this.numeroJugadors) {
			
			for (Jugador aux : jugadors) {
				
				if (aux.getId() == numJugador) {
					
					resultat = true;
					
				}
			}
		}else {
			System.out.println("\nNumero de jugador invalid.");
		}
		return resultat;
		
	}
	
	/*
	 * COMPROVAR POSICIO JUGADOR
	 * 
	 * */
	
	public boolean comprovarPosicio(Jugador jugador) {
				
		if (jugador.getPos() == CASELLAESPECIAL) {
			return true;
			
		}
		return false;
	}
	
	/*
	 * COMPROVAR SI HI HA GUANYADOR
	 * 
	 * */
	
	public Jugador guanyador() {
		
		Jugador guanyador = null;
		
		for (Jugador j : jugadors) {
			
			if (j.getPos() == (LONGITUD)) {

				guanyador = j;
				return guanyador;
				
			}
			
		} 
		
		return guanyador;
		
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
			
			resultat.append((j.getId() + 1) + " - NOM: " + j.getNom() + " -> VIDES: " + j.getVides() + "\n");
			
		}
		
		resultat.append("\n");
		resultat.append("#");
		
		for (int i = 0; i < LONGITUD+1; i++) {
			if (i != CASELLAESPECIAL) {
				resultat.append("===#");
			} else {
				resultat.append("+++#");
			}
			
		}
		
		resultat.append("\n");
		
		for (Jugador j : jugadors) {
			
			int posicioJugador =j.getPos();
			resultat.append("#");
			
			for (int i = 0; i < LONGITUD+1 ; i++) {
				
				if (i == posicioJugador) {
					
					resultat.append(" " + (j.getId() + 1) + " #");
					
				} else {
					
					resultat.append("   #");
					
				}
				
			}
			
			resultat.append("\n");
			
		}		
		
		resultat.append("#");
		
		for (int i = 0; i < LONGITUD+1; i++) {
			if (i != CASELLAESPECIAL) {
				resultat.append("===#");
			} else {
				resultat.append("+++#");
			}
			
		}

		resultat.append("\n  ");
		
		for (int i = 0; i < LONGITUD+1; i++) {
			
			resultat.append(i + "   ");
			
		}
		
		return resultat.toString();
		
	}

	
}
