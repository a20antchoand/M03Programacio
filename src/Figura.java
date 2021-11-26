
public class Figura {

	private int numCostats;
	private int[] longitud;

	public Figura(int numCostats, int... longitud) throws Exception {
		
        if (numCostats < 3)
            throw new Exception("La figura te menys de 3 costats.");
        else if (longitud.length != numCostats)
            throw new Exception("La figura no te els costats indicats.");
        else {
            this.numCostats = numCostats;
            this.longitud = longitud;
        }

	}

	public int perimetre() {
		int perimetre = 0;
		for (int i = 0; i < this.getNumCostats(); i++) {
			perimetre += this.getLongitud()[i];
		}
		return perimetre;
	}

	public int[] getLongitud() {
		return longitud;
	}

	public int getNumCostats() {
		return numCostats;
	}

	public void setLongitud(int[] longitud) {
		this.longitud = longitud;
	}

	public void setNumCostats(int numCostats) {
		this.numCostats = numCostats;
	}

    public static void main(String[] args) {
       
    	//Controlem els possibles errors
    	
    	try {
            Figura f1 = new Figura(3,4,6);
            System.out.println("S'ha creat la figura 1: " + f1.perimetre());
        } catch (Exception e) {
        	e.printStackTrace();
        }

        try {
            Figura f2 = new Figura(2,5,6,7,8);
            System.out.println("S'ha creat la figura 2: " + f2.perimetre());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        try {
            Figura f3 = new Figura(4,5,6,7,8);
            System.out.println("S'ha creat la figura 3: " + f3.perimetre());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

}
