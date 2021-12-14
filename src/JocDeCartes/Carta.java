package JocDeCartes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Carta {

	// ENUM
	
	enum tipusMoviment {
		SUMAVIDA, RESTAVIDA, MOUENDEVANT, MOUENRRERA
	};

	// ATRIBUTS FINALS
	
	final static int NUMEROOPCIONS = 4;
	final static int NUMCARTES = 46;
	final static int NUMCARTES34 = 6;
	final static int NUMCARTES56 = 5;
	
	//ATRIBUTS CARTA
	
	private int numCarta;
	private tipusMoviment[] opcions;
	private boolean isEspecial;
	
	/*
	 * INICIALITZEM LA CARTA 
	 * 
	 * */
	
	private Carta(int numCarta, tipusMoviment[] opcions, boolean isEspecial) {
		this.numCarta = numCarta;
		this.opcions = opcions;
		this.isEspecial = isEspecial;
	}

	/*
	 * GENEREM LA BARALLA 
	 * 
	 * */
	
	public static List<Carta> generarBaralla() {

		List<Carta> baralla = new ArrayList<>();
		int numCarta;
		tipusMoviment[] opcionsCarta;
		boolean especial = false;
		Random rand = new Random();

		for (int i = 0; i < 46; i++) {

			numCarta = i + 1;
			opcionsCarta = new tipusMoviment[3];

			for (int j = 0; j < opcionsCarta.length; j++) {
				opcionsCarta[j] = tipusMoviment.values()[rand.nextInt(NUMEROOPCIONS)];
			}

			if (i < 40) {
				especial = false;
			} else {
				especial = true;
			}

			baralla.add(new Carta(numCarta, opcionsCarta, especial));
		}
		Collections.shuffle(baralla);
		return baralla;

	}

	/*
	 * GETTERS 
	 * 
	 * */
	
	public int getNumCarta() {
		return numCarta;
	}

	public tipusMoviment[] getOpcions() {
		return opcions;
	}

	public boolean isEspecial() {
		return isEspecial;
	}

	/*
	 * TO STRING 
	 * 
	 * */
	
	public String toString() {

		StringBuilder resultat = new StringBuilder();
		resultat.append("\nNumero carta: " + this.numCarta + "\n");
		resultat.append("==============\n");

		if (isEspecial) {
			resultat.append("|  Especial  |\n");
		}
		
		for (int i = 0; i < opcions.length; i++) {
			resultat.append("|" + (i+1) + ":");
			
			switch (opcions[i]) {

				case SUMAVIDA:
					if (isEspecial) {
						resultat.append(" +2 Vida  |\n");
					} else {
						resultat.append(" +1 Vida  |\n");
					}
					break;
	
				case RESTAVIDA:
					if (isEspecial) {
						resultat.append(" -2 Vida  |\n");
					} else {
						resultat.append(" -1 Vida  |\n");
					}
					break;
	
				case MOUENDEVANT:
					if (isEspecial) {
						resultat.append("   ->->   |\n");
					} else {
						resultat.append("   --->   |\n");
					}
					break;
	
				case MOUENRRERA:
					if (isEspecial) {
						resultat.append("   <-<-   |\n");
					} else {
						resultat.append("   <---   |\n");
					}
					break;

			}
		}

		resultat.append("==============");
		return resultat.toString();
	}

}