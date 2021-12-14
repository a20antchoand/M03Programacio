package EquacioGrau2;

public class PrimerCoeficientZeroException extends Exception{

    public PrimerCoeficientZeroException() {
        super("El primer coeficient és 0");
    }

}
