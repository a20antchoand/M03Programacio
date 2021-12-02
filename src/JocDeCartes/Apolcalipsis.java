package JocDeCartes;

import java.util.ArrayList;
import java.util.Scanner;

/*
 * APOCALIPSIS BY: TONI
 * DATA:           02/12/2021
 * PROFESOR:	   ABEL SUSÍN	
 * 
 * Joc de cartes per fugir de l'apocalipsis, regles:
 * 		- de 3 a 6 jugadors
 * 		- 3-4 jugadors = 6 cartes per jugador
 * 		- 5-6 jugadors = 5 cartes per jugador
 * 		- Cada carta te 3 opcions +1, -1, ->, <- 
 * 		- Jugadors inicialment tenen 6 vides, poden tenir més
 * 		- Casella 15 mou 5 caselles endevant o enrrera un jugador
 * 		- Carta normal valor per 1
 * 		- Carta especial valor per 2
 * 		- 3 rondes
 * 		- Guanya qui arriba primer al final
 * 		- Guanya en cas d'acabar les rondes el jugador mes avançat
 * 		- Guanya si queda sol al taulell
 * 
 * */


/* +==+ Agafar l'opció de la carta +==+
 * 
 * System.out.println("Carta 0 - op 0: " + j.getMa()[0].getOpcions()[0]);				
 * System.out.println("Carta 0 - op 1: " + j.getMa()[0].getOpcions()[1]);
 * System.out.println("Carta 0 - op 2: " + j.getMa()[0].getOpcions()[2]);
 * 
 * */

public class Apolcalipsis {

	final static int numRondes = 3;
	
	static Scanner sStr = new Scanner (System.in);
	static Scanner sInt = new Scanner (System.in);
	
	Taulell taulell;
	static Jugador guanyador = null;


	public static void main(String[] args) throws Exception {

		Apolcalipsis joc = new Apolcalipsis();
		Jugador[] jugadors;

		while (joc.taulell == null) {
			jugadors = joc.obtenirJugadors();
			try {
				joc.taulell = new Taulell(jugadors);
				System.out.println("Taulell creat amb exit.");
				joc.taulell.repartirMa(joc.taulell.baralla);
				System.out.println(joc.taulell.toString());		
			} catch (IllegalArgumentException e) {
				System.out.println("Error al crear el taulell, numero de jugadors incorrectes.\n");
			}
		}
		
		for (int i = 0 ; i < numRondes ; i++) {
			
			char op = 0;
			int numCarta;
			int numOpcio;
			
			System.out.println("\n\n Ronda numero: " + (i+1) + "\n\n");
			
			for (Jugador j : joc.taulell.jugadors) {
				
				System.out.println("\nTorn del jugador: " + j.getNom());
				System.out.print("\nVols veure les teves cartes?: ");
				
				op = sStr.next().charAt(0);
				
				if (op == 's' || op == 'S') {
					joc.taulell.mostrarMa(j.getId());
				}
				do {
				
					System.out.print("\nIndica el numero de carta: ");
					numCarta = sInt.nextInt();
				
					System.out.print("Indica la opció: ");
					numOpcio = sInt.nextInt();
					
				} while (!joc.taulell.validarCartaOpcio(j, numCarta, numOpcio));

			}
			

		}
		

		
	}

	@SuppressWarnings("resource")
	public Jugador[] obtenirJugadors() {

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
