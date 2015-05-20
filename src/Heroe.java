import java.awt.Point;

public class Heroe implements Componentes {
	private static final int HP = 100; // Vida inicial del jugador
	private int hp; // Vida actual del jugador
	private Point posicion; // Posición en el mapa del jugador
	private Orientacion orientacion; // Orientación en el mapa del jugador
	private Orientacion antigua; // Orientación antigua del jugador
	private int danio = -50; // Daño del jugador
	private int defensa = 20; // Daño que evita el escudo
	// Booleanos para saber que objetos tiene el heroe
	private boolean llave = false;
	private boolean espada = false;
	private boolean escudo = false;
	private boolean corazon = false;

	public Heroe(int i, int j) {
		setPosicion(new Point(i, j));
		// Por defecto la orientación es Sur
		setOrientacion(Orientacion.Sur);
		setAntigua(Orientacion.Sur);
		this.setHp(HP);
	}

	/*
	 * Getters & Setters
	 */

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		if (getEscudo())
			hp += defensa;
		this.hp = this.hp + hp;
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

	public Orientacion getAntigua() {
		return antigua;
	}

	public void setAntigua(Orientacion antigua) {
		this.antigua = antigua;
	}

	public int getDanio() {
		return danio;
	}

	public void setDanio(int danio) {
		this.danio = danio;
	}

	public boolean getLlave() {
		return llave;
	}

	public void setLlave(boolean llave) {
		this.llave = llave;
	}

	public boolean getEspada() {
		return espada;
	}

	public void setEspada(boolean espada) {
		this.espada = espada;
		this.danio = -100;
	}

	public boolean getEscudo() {
		return escudo;
	}

	public void setEscudo(boolean escudo) {
		this.escudo = escudo;
	}

	public boolean getCorazon() {
		return corazon;
	}

	public void setCorazon(boolean corazon) {
		this.corazon = corazon;
		this.hp = 150;
	}

	/**
	 * Reinicio la vida a HP
	 */
	public void resetHp() {
		this.hp = HP;
	}

	/**
	 * @param enemigo
	 *            Verdadero si tiene un enemigo en la posición en la que está
	 *            mirando
	 * @return
	 */
	public boolean atacar(Enemigo enemigo) {
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
		default:
			break;
		}
		if (enemigo.getPosicion().x == punto.x
				&& enemigo.getPosicion().y == punto.y) {
			return true;
		} else {
			return false;
		}
	}
}