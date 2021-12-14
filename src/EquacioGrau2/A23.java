package EquacioGrau2;

public class A23 {

    public static void main(String[] args) {

        EquacioGrau2 e0 = null;
        EquacioGrau2 eN = null;
        EquacioGrau2 e = null;

        try {
            e0 = new EquacioGrau2(5,5,5);
            e0.arrels();
        } catch (PrimerCoeficientZeroException ex) {
            ex.printStackTrace();
        } catch (NoArrelsRealsException ex) {
            ex.printStackTrace();
        }

        try {
            eN = new EquacioGrau2(5,5,29);
            eN.arrels();
        } catch (PrimerCoeficientZeroException ex) {
            ex.printStackTrace();
        } catch (NoArrelsRealsException ex) {
            ex.printStackTrace();
        }

        try {
            e = new EquacioGrau2(5,16,5);
            e.arrels();
        } catch (PrimerCoeficientZeroException ex) {
            ex.printStackTrace();
        } catch (NoArrelsRealsException ex) {
            ex.printStackTrace();
        }

    }

}
