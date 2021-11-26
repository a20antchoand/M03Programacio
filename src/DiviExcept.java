import java.util.Scanner;

public class DiviExcept {

	static Scanner s = new Scanner(System.in);

	public static void main(String[] args) {

		int num1, num2;
		
		do {
			
			try {
				
				System.out.print("Numero: ");
				num1 = Integer.parseInt(s.next());
				System.out.print("Numero: ");
				num2 = Integer.parseInt(s.next());

				System.out.println("Divisio: " + (num1 / num2));
				
			} catch (NumberFormatException e) {
				
				System.out.println("Numero incorrecte.");
				
			} catch (ArithmeticException e) {
				
				System.out.println("No es pot dividir entre 0.");
				
			}
			
		} while (true);
		
	}
	
}
