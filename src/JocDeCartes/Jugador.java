package JocDeCartes;

import java.util.List;

public class Jugador {

	private final int vidasEstandard = 5;
		
	private String nom;
	private List<Carta> ma;
	private int pos = 0;
	private int id;
	private int vides;
	
	public Jugador(String nom) {
		this.nom = nom;
		this.vides = vidasEstandard;
		this.id = Taulell.idJugador++;
	}
	
	public String getNom () {
		return nom;
	}
	
	public int getVides () {
		return vides;
	}
	
	public int getPos () {
		return pos;
	}
	
	public List<Carta> getMa () {
		return ma;
	}
	
	public int getId () {
		return id;
	}
	
	public void setMa (List<Carta> ma) {
		this.ma = ma;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public void setPos(int pos) {
		this.pos = pos;
	}
	
	public void setVides (int vides) {
		this.vides = vides;
	}
	
	@Override
	public String toString () {
		return "Nom: " + nom + " / Vidas: " + vides;
	}
}
