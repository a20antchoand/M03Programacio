package RegEx;

import java.util.Scanner;

public class RegEx1 {

	static Scanner s = new Scanner(System.in);

	public static void main(String[] args) {

		do {
			String paraula;
			StringBuilder reversa = new StringBuilder();

			System.out.print("Indica una paraula: ");
			paraula = s.nextLine();

			reversa.append(paraula);
			reversa.reverse();

			System.out.println(paraula + "\n");
			System.out.println(reversa.toString() + "\n");

			if (paraula.equalsIgnoreCase(reversa.toString())) {
				System.out.println("La paraula [" + paraula + "] és palindroma.");
			} else {
				System.out.println("La paraula [" + paraula + "] no es palindroma");
			}
		} while (true);

	}

}
