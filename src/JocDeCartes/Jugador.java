package JocDeCartes;

import java.util.List;

public class Jugador {

	private final int vidasEstandard = 5;
		
	private int pos = 0;
	private int id;
	private String nom;
	private int vidas;
	private List<Carta> ma;
	
	public Jugador(String nom) {
		this.nom = nom;
		this.vidas = vidasEstandard;
		this.id = Taulell.idJugador++;
	}
	
	public String getNom () {
		return nom;
	}
	
	public int getVidas () {
		return vidas;
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
	
	public void sumarVida (int numero) {
		this.vidas += numero;
	}
	
	public void restaVida (int numero) {
		this.vidas -= numero;
	}
	
	@Override
	public String toString () {
		return "Nom: " + nom + " / Vidas: " + vidas;
	}
}
