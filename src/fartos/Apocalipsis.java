package JocDeCartes;

import java.util.ArrayList;
import java.util.List;
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

public class Apocalipsis {

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////ATRIBUTS///////////////////////////////////////////////////////////////////////////	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// ATRBUTS FINALS

	final static int numRondes = 5;

	// SCANNERS

	static Scanner sStr = new Scanner(System.in);
	static Scanner sInt = new Scanner(System.in);

	// ATRIBUTS MAIN

	static Taulell taulell;
	static Jugador guanyador = null;
	static boolean bloqueig = false;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////PROGRAMA///////////////////////////////////////////////////////////////////////////	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static void main(String[] args) throws Exception {

		List<Jugador> jugadors = null;

		Jugador guanyador = null;
		int numCarta;
		int numeroJugador;
		Carta cartaJugar = null;
		Carta.tipusMoviment opcioJugar = null;
		boolean teCarta = false;

		/*
		 * OBTENIM ELS JUGADORS I INTENTEM CREAR EL TAULELL
		 * 
		 */

		while (taulell == null) {
			jugadors = obtenirJugadors();
			try {
				taulell = new Taulell(jugadors);
			} catch (IllegalArgumentException e) {
				System.out.println("Error al crear el taulell, numero de jugadors incorrectes.\n");
			}
		}

		/*
		 * JUGUEM TOTES LES RONDES
		 * 
		 */

		for (int i = 0; i < numRondes; i++) {
			if (guanyador == null && taulell.quedenJugadors(jugadors.size())) {
				System.out.println("\n\n !!!!!!!!!!!!!!!!!!!!!!!!Ronda numero: " + (i + 1)
						+ " !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");

				taulell.baralla = Carta.generarBaralla();
				taulell.repartirMa(taulell.baralla);
				do {
					for (Jugador actual : jugadors) {

						if (actual.teCartes(actual.getMa())) {

							if (actual.getPatada() > 0) {
								System.out.println(actual.getPatada());
								actual.setPatada(actual.getPatada() - 1);

							} else if (actual.getPatada() == 0) {
								System.out.println(actual.getPatada());
								taulell.treurePatada(actual);

							}

							if (actual.getZancadilla() == 1) {
								actual.setZancadilla(0);
							} else {

								if (guanyador == null && taulell.jugadors.size() != 0) {

									System.out.println("\nTorn del jugador: " + actual.getNom());

									taulell.mostrarMa(actual.getId());
									System.out.println(taulell.toString());

									do {

										System.out.print("\nIndica el numero de carta: ");
										numCarta = sInt.nextInt();

										cartaJugar = taulell.validarCarta(actual, numCarta);

									} while (cartaJugar == null);

									do {
										System.out.print("Indica el numero del jugador: ");
										numeroJugador = sInt.nextInt();
										numeroJugador -= 1;
									} while (!taulell.validarJugador(numeroJugador));

									opcioJugar = cartaJugar.getOpcio();
									Jugador objectiu = jugadors.get(numeroJugador);

									actual.getMa().remove(cartaJugar);

									try {
										taulell.executar(opcioJugar, actual, objectiu);
									} catch (Exception e) {
										System.out.println("\nTens un bloqueig de dos jugadors, has perdut la carta");
									}

									// Comprovar posicio especial

									taulell.comprovarPosicio(actual, objectiu);

									// Eliminem jugador mort

									if (objectiu.getVides() <= 0) {
										System.out.println("\nEl jugador " + objectiu.getNom() + " ha mort.");
										taulell.eliminarJugador(objectiu);

									}

								}
							}

						}
						
						// Comprovem guanyador

						if ((guanyador = taulell.guanyador()) != null) {
							break;
						}
						teCarta = actual.teCartes(actual.getMa());
						
					}
					
					if (i > 3) {
						taulell.eliminarCasella();						
					}
					
				} while (teCarta && taulell.jugadors.size() != 0 && guanyador == null);

			}
			
		}
		
		if (guanyador != null) {

			System.out.println("\nEl jugador " + guanyador.getNom() + " ha guanyat.");

		} else if (taulell.jugadors.size() == 0) {

			System.out.println("\nNingun jugador ha aguantat amb vida.");

		} else {

			ArrayList<String> primers = new ArrayList<>();
			int intAux = 0;

			for (Jugador jAux : taulell.jugadors) {

				if (jAux.getPos() >= intAux) {

					intAux = jAux.getPos();
					primers.add(jAux.getNom());

				}

			}

			if (primers.size() == 1) {

				System.out
						.println("\nNingu ha arribat al Hangar, pero el que esta mes aprop és: " + primers.get(0));

			} else {

				System.out.println("\nNingu ha arribat al Hangar, pero el que esta mes aprop és: ");

				for (String aux : primers) {

					System.out.println(aux + ",");

				}

			}
		}
		
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////OBTENIM JUGADORS///////////////////////////////////////////////////////////////////	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static List<Jugador> obtenirJugadors() {

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

		return jugadors;

	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
