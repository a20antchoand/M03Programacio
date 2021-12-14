package RegEx;

/* El segunt es un exemple de del meode replaceAll sobre una cadena. 
L'exemple substitueix totes les aparicions que concorden amb el patron "ab" per la cadena "-". */

import java.util.regex.*;

public class ProvaReplaceAll{
    public static void main(String args[]){
        // compilem el patro
        Pattern patro = Pattern.compile("a*b");
        // creem el Matcher a partir del patro, la cadena com a parametre
        Matcher encaixa = patro.matcher("aabmanoloaaaaaabmanoloabmanolob");
        // invoquem el mètode replaceAll
        String resultat = encaixa.replaceAll("-");
        System.out.println(resultat);
    }
}