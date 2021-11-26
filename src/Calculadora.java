import java.util.Scanner;

public class Calculadora {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        char op = ' ';
        int numero1 = 0;
        int numero2 = 0;

    	System.out.println("Escriu Z per finalitzar el progarama...");
        
        while (op != 'Z') {
        	
            try {
            
                System.out.print("Escriu l'operador: ");
                
                op = s.next().charAt(0);
                
                if (op == 'Z') {
                    throw new Exception("Sortint del programa...");
                }
                
                if (op != '+' && op != '-' && op != '*' && op != '/') {
                	throw new Exception("Operador invalid");
                }
                
                System.out.print("Indica el numero 1: ");
                numero1 = Integer.parseInt(s.next());

                System.out.print("Indica el numero 2: ");
                numero2 = Integer.parseInt(s.next());

                System.out.print("Resultat: ");
                switch (op) {
                    case '+':
                        System.out.println((numero1 + numero2));
                        break;

                    case '-':
                        System.out.println((numero1 - numero2));
                        break;

                    case '*':
                        System.out.println((numero1 * numero2));
                        break;

                    case '/':
                        System.out.println((numero1 / numero2));
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("El numero no es valid.");
            } catch (ArithmeticException e) {
                System.out.println("No es pot dividir entre 0 pallus.");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
        s.close();
    }
}