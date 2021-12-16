package RegEx;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegEx6 {

	static Scanner s = new Scanner(System.in);

	public static void main(String[] args) {

		String password;

		System.out.print("Indica una paraula: ");
		password = s.nextLine();

//		String minLength = ".{6,}";
//		String majus = "[A-Z]{1}";
//		String puntuacio = "[?¿_.!¡$#-@]{1}";
//		String numero = "\\d{1,}";
		String regEx = ".{6,}&[A-Z]{1}&[?¿_.!¡$#-@]{1}&\\\\d{1,}";

//		Pattern regla1 = Pattern.compile(minLength);
//		Pattern regla2 = Pattern.compile(majus);
//		Pattern regla3 = Pattern.compile(puntuacio);
//		Pattern regla4 = Pattern.compile(numero);
		Pattern regla = Pattern.compile(regEx);
		
//		Matcher passLength = regla1.matcher(password);
//		Matcher passMajus = regla2.matcher(password);
//		Matcher passPunt = regla3.matcher(password);
//		Matcher passNum = regla4.matcher(password);
		Matcher matcher = regla.matcher(regEx);
		
//		if (!passLength.find()) {
//			System.out.println("Longitud incorrecte." + password.length() + " digits.");
//			System.exit(0);
//		} else {
//			System.out.println("Longitud: " + passLength.group().length());
//		}
//
//		if (!passMajus.find()) {
//			System.out.println("No hi han majuscules.");
//			System.exit(0);
//		} else {
//			System.out.println(passMajus.group());
//		}
//
//		if (!passPunt.find()) {
//			System.out.println("No hi han simbols de puntuació.");
//			System.exit(0);
//		} else {
//			System.out.println(passPunt.group());
//		}
//
//		if (!passNum.find()) {
//			System.out.println("No hi han numeros.");
//			System.exit(0);
//		} else {
//			System.out.println(passNum.group());
//		}
//
//		System.out.println("Contrasenya desada correctament!");

	}

}
