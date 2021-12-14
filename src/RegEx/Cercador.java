package RegEx;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cercador {

	public static void main(String[] args) {

		// String to analyze
		String text = "Ha estat molt poc Temps el que hi has dedicat.";
		
		// Regular expression to be used to analyze the string
		String expresioRegular = "[a-z]{3,6}[ts]";

		// Pattern object to compile the regular expression
		Pattern regles = Pattern.compile(expresioRegular);
		
		//Object to process the regular expression and find coincidences in the text
		Matcher textAnalitzar = regles.matcher(text);
		
		if (textAnalitzar.matches() == true)
			System.out.print("La cadena coincideix en la seva totalitat");
		
		//while there are coincidences in the text ....
		while (textAnalitzar.find() == true){ 

			// The string which has produced the last coincidence is shown 
			System.out.print("Cadena: " + textAnalitzar.group() );

			// Index of the character where the last coincidence has been found
			System.out.print(" (Inici: " + textAnalitzar.start() );
			
			// Index of the first character after the last coincidence
			System.out.println(", Fi: " + textAnalitzar.end() + ")");
		}

	}
}