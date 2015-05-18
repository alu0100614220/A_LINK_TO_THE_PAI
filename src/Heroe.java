import java.awt.Point;

public class Heroe implements Componentes {
	private static final int HP = 100; // Vida inicial del jugador
	private int hp; // Vida del jugador
	private int arma; // Arma actual del jugador
	private Point posicion; // Posición en el mapa del jugador
	private Orientacion orientacion; // Orientación en el mapa del jugador
	private int damage = -50;
	private boolean key = false;
	Heroe(int i, int j) {
		setPosicion(new Point(i, j));
		setOrientacion(Orientacion.Sur);
		this.setHp(HP);
		this.setArma(Arma.Espada);
	}

	/*
	 * Getters & Setters
	 */
	public int getHp() {
		return hp;
	}

	public boolean atacar(Enemigo enemy) {
		Point punto = new Point(this.getPosicion());
		switch (orientacion) {
		case Norte:
			punto.y = punto.y - 1;
			break;
		case Sur:
			punto.y = punto.y + 1;
			break;
		case Este:
			punto.x = punto.x + 1;
			break;
		case Oeste:
			punto.x = punto.x - 1;
			break;
		}
		if (enemy.getPosicion().x == punto.x
				&& enemy.getPosicion().y == punto.y) {
			return true;
		} else {
			return false;
		}

	}

	public void setHp(int hp) {
		this.hp = this.hp + hp;
	}

	public int getArma() {
		return arma;
	}

	public void setArma(Arma arma) {
		this.arma = arma.ordinal();
	}

	public Point getPosicion() {
		return posicion;
	}

	public void setPosicion(Point posicion) {
		this.posicion = posicion;
	}

	public Orientacion getOrientacion() {
		return orientacion;
	}

	public void setOrientacion(Orientacion orientacion) {
		this.orientacion = orientacion;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public boolean getKey() {
		return key;
	}

	public void setKey(boolean key) {
		System.out.println("Llave obtenida.");
		this.key = key;
	}
}
