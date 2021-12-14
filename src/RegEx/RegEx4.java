package RegEx;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegEx4 {

	final static char[] lletres = {'T', 'R', 'W', 'A','G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L','C','K','E'};
	
	static Scanner s = new Scanner(System.in);

	public static void main(String[] args) {

			String dni;
			

			System.out.print("Indica una paraula: ");
			dni = s.nextLine();

			String expresioRegular = "^\\d{8}[a-zA-Z]$";

			Pattern regles = Pattern.compile(expresioRegular);
			
			Matcher textAnalitzar = regles.matcher(dni);

			
			if (textAnalitzar.matches() && isValid(dni)) {
					System.out.println("DNI VALID");	

				
			} else {
				System.out.println("DNI INVALIDO");
			}
						
	}

	private static boolean isValid(String dni) {
		int numero;
		char lletra;
		numero = Integer.parseInt(dni.substring(0, dni.length() - 1));
		numero = numero % 23;
		lletra = lletres[numero];
		if (lletra == dni.charAt(dni.length()-1)) {
			return true;
		}
		return false;
	}
	
}



