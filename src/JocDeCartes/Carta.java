package JocDeCartes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Carta {

	enum tipusMoviment {
		SUMAVIDA, RESTAVIDA, MOUENDEVANT, MOUENRRERA
	};

	final static int numeroOpcions = 4;
	final static int numCartes = 46;
	final static int numCartes34 = 6;
	final static int numCartes56 = 5;
	
	public static tipusMoviment opcio;
	
	private int numCarta;
	private tipusMoviment[] opcions;
	private boolean isEspecial;
	

	private Carta(int numCarta, tipusMoviment[] opcions, boolean isEspecial) {
		this.numCarta = numCarta;
		this.opcions = opcions;
		this.isEspecial = isEspecial;
	}

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
				opcionsCarta[j] = tipusMoviment.values()[rand.nextInt(numeroOpcions)];
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

	public int getNumCarta() {
		return numCarta;
	}

	public tipusMoviment[] getOpcions() {
		return opcions;
	}

	public boolean isEspecial() {
		return isEspecial;
	}

	public String toString() {

		StringBuilder resultat = new StringBuilder();
		resultat.append("\nNumero carta: " + this.numCarta + "\n");
		resultat.append("==============\n");

		for (int i = 0; i < opcions.length; i++) {
			resultat.append("|" + (i+1) + ":");
			
			switch (opcions[i]) {

				case SUMAVIDA:
					resultat.append(" +1 Vida  |\n");
					break;
	
				case RESTAVIDA:
					resultat.append(" -1 Vida  |\n");
					break;
	
				case MOUENDEVANT:
					resultat.append("   --->   |\n");
					break;
	
				case MOUENRRERA:
					resultat.append("   <---   |\n");
					break;

			}
		}

		if (isEspecial) {
			resultat.append("|  Especial  |\n");
		}

		resultat.append("==============");
		return resultat.toString();
	}

}