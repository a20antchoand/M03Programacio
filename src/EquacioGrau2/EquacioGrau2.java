package EquacioGrau2;

public class EquacioGrau2 {

    double sol1;
    double sol2;
    double a;
    double b;
    double c;

    public EquacioGrau2 (double a, double b, double c) throws PrimerCoeficientZeroException, NoArrelsRealsException {

        if (a == 0) {
            throw new PrimerCoeficientZeroException();
        }

        if (Math.pow(b, 2) < 4 * a * c) {
            throw new NoArrelsRealsException();
        }
        

        
        this.a = a;
        this.b = b;
        this.c = c;
        
    }

    public void arrels() {

        sol1 = ( -b + Math.sqrt(Math.pow(b,2) - 4 * a * c)) / (2 * a);
        sol2 = ( -b - Math.sqrt(Math.pow(b,2) - 4 * a * c)) / (2 * a);

        System.out.println(sol1 + " ------ " + sol2);
    }

    public double getSol1() {
        return sol1;
    }

    public double getSol2() {
        return sol2;
    }

}
