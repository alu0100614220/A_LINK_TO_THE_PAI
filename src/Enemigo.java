import java.awt.Point;

public class Enemigo implements Componentes {
	private int hp; // Vida del enemigo
	private Point posicion; // Posición en el mapa
	private Orientacion orientacion; // Orientación del enemigo
	private Tablero tablero; // Acceso a Tablero
	private int danyo = -30; // Daño que inflinge el enemigo
	boolean movimiento; // Controla el movimiento del enemigo

	/**
	 * Constructor de enemigo
	 */
	public Enemigo(Point punto, Tablero tablero) {
		setTablero(tablero);
		setPosicion(new Point(punto));
		setOrientacion(Orientacion.Sur);
		this.setHp(100);
	}
	
	/**
	 * Método que decide entre moverse si el heroe esta lejos
	 * o atacar si esta junto al heroe
	 */
	public boolean actuar(Point objetivo) {
		if (skill(objetivo)) {
			return true;
		} else {
			mover(objetivo);
			return false;
		}
	}
	
	/**
	 * Método que mueve al enemigo a la casilla correspondiente
	 */
	private void moverse(Point posicion, Orientacion aux){
		if (!getTablero().getCasilla(posicion).isOcupado()) {
			getTablero().setCasilla(getPosicion(), Estado.Vacia,
					aux);
			setPosicion(posicion);
			this.movimiento = true;
		}
		getTablero().setCasilla(getPosicion(), Estado.Enemigo,
				aux);
		setOrientacion(aux);
	}

	/**
	 * Método que analiza hacia donde debe moverse el enemigo
	 */
	public void mover(Point objetivo) {
		Point posicion;
		this.movimiento = false;
		
		if (getPosicion().x < objetivo.x && !movimiento) {
			posicion = new Point(getPosicion().x + 1, getPosicion().y);	
			moverse(posicion, Orientacion.Este);
		}
		if (getPosicion().x > objetivo.x && !movimiento) {
			posicion = new Point(getPosicion().x - 1, getPosicion().y);
			moverse(posicion, Orientacion.Oeste);
		}
		if (getPosicion().y < objetivo.y && !movimiento) {
			posicion = new Point(getPosicion().x, getPosicion().y + 1);
			moverse(posicion, Orientacion.Sur);
		}
		if (getPosicion().y > objetivo.y && !movimiento) {
			posicion = new Point(getPosicion().x, getPosicion().y - 1);
			moverse(posicion, Orientacion.Norte);
		}
	}

	/**
	 * Método para atacar al heroe si este en la posición 
	 * a la que está mirando
	 */
	public boolean skill(Point objetivo) {
		Point punto = new Point(getPosicion());
		switch (getOrientacion()) {
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
		if (objetivo.x == punto.x && objetivo.y == punto.y) {
			return true;
		} else {
			return false;
		}
	}
	
	/*
	 * Getters & Setters
	 */
	public Tablero getTablero() {
		return tablero;
	}

	public void setTablero(Tablero tablero) {
		this.tablero = tablero;
	}
	
	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
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

	public int getDamage() {
		return danyo;
	}

	public void setDamage(int damage) {
		danyo = damage;
	}
}
