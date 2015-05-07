import java.awt.Point;

public class Heroe {
	private static final int HP = 100;	// Vida inicial del jugador
	private int hp; // Vida del jugador
	private int arma; // Arma actual del jugador
	private Point posicion; // Posición en el mapa del jugador
	private int orientacion; // Orientación en el mapa del jugador

	Heroe(int i, int j) {
		setPosicion(new Point(i, j));
		setOrientacion(0);
		this.setHp(HP);
		this.setArma(0);
	}

	/*
	 * Getters & Setters
	 */
	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getArma() {
		return arma;
	}

	public void setArma(int arma) {
		this.arma = arma;
	}

	public Point getPosicion() {
		return posicion;
	}

	public void setPosicion(Point posicion) {
		this.posicion = posicion;
	}

	public int getOrientacion() {
		return orientacion;
	}

	public void setOrientacion(int orientacion) {
		this.orientacion = orientacion;
	}
}
