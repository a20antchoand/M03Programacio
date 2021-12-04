package JocDeCartes;

import java.util.ArrayList;
import java.util.Scanner;

/*
 * APOCALIPSIS BY: TONI
 * DATA:           02/12/2021
 * PROFESOR:	   ABEL SUSIN	
 * 
 * Joc de cartes per fugir de l'apocalipsis, regles:
 * 		- de 3 a 6 jugadors
 * 		- 3-4 jugadors = 6 cartes per jugador
 * 		- 5-6 jugadors = 5 cartes per jugador
 * 		- Cada carta te 3 opcions +1, -1, ->, <- 
 * 		- Jugadors inicialment tenen 6 vides, poden tenir mes
 * 		- Casella 15 mou 5 caselles endevant o enrrera un jugador
 * 		- Carta normal valor per 1
 * 		- Carta especial valor per 2
 * 		- 3 rondes
 * 		- Guanya qui arriba primer al final
 * 		- Guanya en cas d'acabar les rondes el jugador mes avancat
 * 		- Guanya si queda sol al taulell
 * 
 * */

/* +==+ Agafar l'opcio de la carta +==+
 * 
 * System.out.println("Carta 0 - op 0: " + j.getMa()[0].getOpcions()[0]);				
 * 
 * */

public class Apocalipsis {

	// ATRBUTS FINALS

	final static int numRondes = 3;

	// SCANNERS

	static Scanner sStr = new Scanner(System.in);
	static Scanner sInt = new Scanner(System.in);

	// ATRIBUTS MAIN

	Taulell taulell;
	static Jugador guanyador = null;

	/*
	 * METODE MAIN - INICIEM EL JOC
	 * 
	 */

	public static void main(String[] args) throws Exception {

		Apocalipsis joc = new Apocalipsis();
		Jugador[] jugadors = null;

		boolean teCartes;
		Jugador guanyador = null;
		int numCarta;
		int numOpcio;
		int numeroJugador;
		Jugador jugador = null;
		Carta cartaJugar = null;
		Carta.tipusMoviment opcioJugar = null;

		/*
		 * OBTENIM ELS JUGADORS I INTENTEM CREAR EL TAULELL
		 * 
		 */

		while (joc.taulell == null) {
			jugadors = joc.obtenirJugadors();
			try {
				joc.taulell = new Taulell(jugadors);
				System.out.println(joc.taulell.toString());
			} catch (IllegalArgumentException e) {
				System.out.println("Error al crear el taulell, numero de jugadors incorrectes.\n");
			}
		}

		/*
		 * JUGUEM TOTES LES RONDES
		 * 
		 */

		for (int i = 0; i < numRondes; i++) {
			if (guanyador == null && joc.taulell.jugadors.size() != 0) {
				System.out.println("\n\n !!!!!!!!!!!!!!!!!!!!!!!!Ronda numero: " + (i + 1) + " !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");

				joc.taulell.baralla = Carta.generarBaralla();
				joc.taulell.repartirMa(joc.taulell.baralla);

				do {

					int numCartes;
					teCartes = true;

					for (int j = 0; j < joc.taulell.jugadors.size(); j++) {

						if (guanyador == null && joc.taulell.jugadors.size() != 0) {
							jugador = joc.taulell.jugadors.get(j);
							numCartes = jugador.getMa().size() - 1;
							System.out.println("\nTorn del jugador: " + jugador.getNom());

							joc.taulell.mostrarMa(jugador.getId());

							do {

								System.out.print("\nIndica el numero de carta: ");
								numCarta = sInt.nextInt();

								System.out.print("Indica la opcio: ");
								numOpcio = sInt.nextInt();

								cartaJugar = joc.taulell.validarCartaOpcio(jugador, numCarta, numOpcio);

								if (cartaJugar != null) {
									opcioJugar = joc.taulell.agafarOpcio(jugador, cartaJugar, numOpcio);
								}

							} while (cartaJugar == null);

							do {
								System.out.print("Indica el numero del jugador: ");
								numeroJugador = sInt.nextInt();
								numeroJugador-=1;
							} while (!joc.taulell.validarJugador(numeroJugador));

							switch (opcioJugar) {
							case MOUENDEVANT:
								joc.taulell.moureJugador(numeroJugador, 1, cartaJugar, opcioJugar);
								break;
							case MOUENRRERA:
								joc.taulell.moureJugador(numeroJugador, -1, cartaJugar, opcioJugar);
								break;
							case SUMAVIDA:
								joc.taulell.gestionarVides(numeroJugador, 1, cartaJugar, opcioJugar);
								break;
							case RESTAVIDA:
								joc.taulell.gestionarVides(numeroJugador, -1, cartaJugar, opcioJugar);
								break;
							default:
								throw new IllegalArgumentException("Unexpected value: " + opcioJugar);
							}

							if (numCartes == 0) {
								teCartes = false;
							}

							// Eliminem jugador mort

							Jugador eliminar = null;
							for (Jugador aux : jugadors) {
								if (aux.getId() == numeroJugador) {
									eliminar = aux;
								}
							}
							
							if (eliminar.getVides() <= 0) {
								System.out.println("\nEl jugador " + eliminar.getNom()
										+ " ha mort.");
								joc.taulell.jugadors.remove(eliminar);

							}

							// Comprovem guanyador

							System.out.println(joc.taulell.toString());
							guanyador = joc.taulell.guanyador();
							
						}
					}

				} while (teCartes && guanyador == null && joc.taulell.jugadors.size() != 0);

			}
			
		}
		
		if (guanyador != null) {
			
			System.out.println("\nEl jugador " + guanyador.getNom() + " ha guanyat.");
			
		} else if (joc.taulell.jugadors.size() == 0) {
			
			System.out.println("\nNingun jugador ha aguantat amb vida.");
			
		} else {
			
			ArrayList<String> primers = new ArrayList<>();
			int intAux = 0;
			
			for (Jugador jAux : joc.taulell.jugadors) {
				
				if (jAux.getPos() >= intAux) {
					
					intAux = jAux.getPos();
					primers.add(jAux.getNom());
					
				}
				
			}
			
			if (primers.size() == 1) {
				
				System.out.println("\nNingu ha arribat al Hangar, pero el que esta mes aprop és: " + primers.get(0));
				
			} else {
				
				System.out.println("\nNingu ha arribat al Hangar, pero el que esta mes aprop és: ");
				
				for (String aux : primers) {
					
					System.out.println(aux + ",");
					
				}
				
			}
			
		}
	}

	/*
	 * OBTENIM ELS JUGADORS
	 * 
	 */

	public Jugador[] obtenirJugadors() {

		@SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);
		ArrayList<Jugador> jugadors = new ArrayList<>();
		boolean parar = false;
		String nomJugador;
		char continuar = ' ';

		while (!parar) {

			System.out.print("Indica el nom del jugador: ");
			nomJugador = s.next();

			jugadors.add(new Jugador(nomJugador));

			do {

				System.out.print("Vols crear mes jugadors? (s/n): ");
				continuar = s.next().charAt(0);

				if (continuar == 's')
					parar = false;
				else if (continuar == 'n')
					parar = true;
				else
					System.out.println("Opcio no valida.");

			} while (continuar != 's' && continuar != 'n');
		}

		return jugadors.toArray(new Jugador[jugadors.size()]);

	}
}
