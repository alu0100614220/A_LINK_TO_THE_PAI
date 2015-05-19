import java.awt.Point;

public class Enemigo implements Componentes {
	private int hp; // Vida del enemigo
	private Point posicion; // Posici��n en el mapa
	private Orientacion orientacion;
	private Tablero tablero;
	private int danyo = -30;

	Enemigo(Point punto, Tablero tablero) {
		setTablero(tablero);
		setPosicion(new Point(punto));
		setOrientacion(Orientacion.Sur);
		this.setHp(100);
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

	public boolean actuar(Point objetivo) {

		if (skill(objetivo)) {
			return true;
		} else {
			mover(objetivo);
			return false;
		}
	}

	public void mover(Point objetivo) {
		Point posicion;
		boolean movimiento = false;
		if (getPosicion().x < objetivo.x && !movimiento) {
			if (!getTablero().getCasilla(
					posicion = new Point(getPosicion().x + 1, getPosicion().y))
					.isOcupado()) {
				getTablero().setCasilla(getPosicion(), Estado.Vacia,
						Orientacion.Este);
				setPosicion(posicion);
				movimiento = true;
			}
			getTablero().setCasilla(getPosicion(), Estado.Enemigo,
					Orientacion.Este);
			setOrientacion(Orientacion.Este);
		}
		if (getPosicion().x > objetivo.x && !movimiento) {
			if (!getTablero().getCasilla(
					posicion = new Point(getPosicion().x - 1, getPosicion().y))
					.isOcupado()) {
				getTablero().setCasilla(getPosicion(), Estado.Vacia,
						Orientacion.Este);
				setPosicion(posicion);
				movimiento = true;
			}
			getTablero().setCasilla(getPosicion(), Estado.Enemigo,
					Orientacion.Oeste);
			setOrientacion(Orientacion.Oeste);
		}
		if (getPosicion().y < objetivo.y && !movimiento) {
			if (!getTablero().getCasilla(
					posicion = new Point(getPosicion().x, getPosicion().y + 1))
					.isOcupado()) {
				getTablero().setCasilla(getPosicion(), Estado.Vacia,
						Orientacion.Este);
				setPosicion(posicion);
				movimiento = true;
			}
			getTablero().setCasilla(getPosicion(), Estado.Enemigo,
					Orientacion.Sur);
			setOrientacion(Orientacion.Sur);
		}
		if (getPosicion().y > objetivo.y && !movimiento) {
			if (!getTablero().getCasilla(
					posicion = new Point(getPosicion().x, getPosicion().y - 1))
					.isOcupado()) {
				getTablero().setCasilla(getPosicion(), Estado.Vacia,
						Orientacion.Este);
				setPosicion(posicion);
				movimiento = true;
			}
			getTablero().setCasilla(getPosicion(), Estado.Enemigo,
					Orientacion.Norte);
			setOrientacion(Orientacion.Norte);
		}
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
