package RegEx;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegEx2 {

	static Scanner s = new Scanner(System.in);

	public static void main(String[] args) {

		do {
			String frase;
			int contador = 0;

			System.out.print("Indica una paraula: ");
			frase = s.nextLine();

			String expresioRegular = "a";

			Pattern regles = Pattern.compile(expresioRegular);
			
			Matcher textAnalitzar = regles.matcher(frase);

			while (textAnalitzar.find() == true){ 
				contador++;
			}
			
			System.out.println("La paraula \"" + expresioRegular + "\" apareix " + contador + " vegades.");
			
		} while (true);

	}
	
}
