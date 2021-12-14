package RegEx;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegEx5 {
	
	static Scanner s = new Scanner(System.in);

	public static void main(String[] args) {

			
			String frase = "Hola! Com anem? Cauen moltes coses,\n"
					+ "com ara iodes o iogurts, a part d'una\n"
					+ " hiena i moltes joies. hieure iuna uz";

			String expresioRegular = "^[hH]?[iu][aeiou]\\w+[\\s|\\w]|\\s[hH]?[iu][aeiou]\\w+[\\s|\\w]";

			Pattern regles = Pattern.compile(expresioRegular);
			
			Matcher textAnalitzar = regles.matcher(frase);

			while (textAnalitzar.find()) {
				System.out.println(textAnalitzar.group());
			}
						
	}
	
}