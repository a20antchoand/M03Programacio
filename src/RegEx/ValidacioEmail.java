package RegEx;

/* El següent exemple tracta de validar una cadena que suposadament conté un email, 
el fa amb quatre comprovacions, amb un patró cadascuna, la primera que no continga 
com a primer caràcter una @ o un punt, la segona que no comence per www. , que 
continga una i només una @ i la quarta que no continga caràcters il·legals: */

import java.util.regex.*;

public class ValidacioEmail {
    public static void main(String[] args) throws Exception {
        String input = "regular@a.com";
        // comprova que no comença per punt o @
        Pattern p = Pattern.compile("^.|^@");
        Matcher m = p.matcher(input);
        if (m.find())
            System.err.println("Les direccions email no comencen per punt ni per @");

        // comprova que no comenci per www.
        p = Pattern.compile("^www.");
        m = p.matcher(input);
        if (m.find())
            System.out.println("Els emails no comencen per www");

        // comprova que contingui @
        p = Pattern.compile("@");
        m = p.matcher(input);
        if (!m.find())
            System.out.println("La cadena no té arroba");
        
        // comprova que contingui @
        p = Pattern.compile("@\\w+.com$");
        m = p.matcher(input);
        if (!m.find())
            System.out.println("La cadena no té domini");
        
        // comprova que no tingui caracters prohibits
        p = Pattern.compile("[^A-Za-z0-9.@_-~#]+");
        m = p.matcher(input);
        StringBuffer sb = new StringBuffer();
        boolean resultat = m.find();
        boolean caractersIlegals = false;

        while(resultat) {
            caractersIlegals = true;
            m.appendReplacement(sb, "");
            resultat = m.find();
        }

        // Añade el ultimo segmento de la entrada a la cadena
        m.appendTail(sb);

        input = sb.toString();

        if (caractersIlegals) {
            System.out.println("La cadena conté caràcters ilegals");
        }
    }
}