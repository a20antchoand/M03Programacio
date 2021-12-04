package JocDeCartes;

import java.util.List;

public class Jugador {

	// ATRIBUT FINAL
	
	private final int vidasEstandard = 2;
	
	// ATRIBUTS JUGADOR
	
	private String nom;
	private List<Carta> ma;
	private int pos = 0;
	private int id;
	private int vides;
	
	/*
	 * INICIALITZAR JUGADOR
	 * 
	 * */
	
	public Jugador(String nom) {
		this.nom = nom;
		this.vides = vidasEstandard;
		this.id = Taulell.idJugador++;
	}
	
	/*
	 * GETTERS
	 * 
	 * */
	
	public String getNom () {
		return nom;
	}
	
	public List<Carta> getMa () {
		return ma;
	}
	
	public int getPos () {
		return pos;
	}

	public int getId () {
		return id;
	}
	
	public int getVides () {
		return vides;
	}
	
	/*
	 * SETTERS
	 * 
	 * */
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public void setMa (List<Carta> ma) {
		this.ma = ma;
	}
	
	public void setPos(int pos) {
		this.pos = pos;
	}
	
	public void setVides (int vides) {
		this.vides = vides;
	}
	
	/*
	 * TO STRING
	 * 
	 * */
	
	@Override
	public String toString () {
		return "Nom: " + nom + " / Vidas: " + vides;
	}
}
