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

		if ((getPosicion().x < objetivo.x)
				&& (!getTablero().getCasilla(
						posicion = new Point(getPosicion().x + 1,
								getPosicion().y)).isOcupado())) {
			getTablero().setCasilla(getPosicion(), Estado.Vacia,
					Orientacion.Este);
			getTablero().setCasilla(posicion, Estado.Enemigo, Orientacion.Este);
			setPosicion(posicion);
			setOrientacion(Orientacion.Este);
		} else if ((getPosicion().x > objetivo.x)
				&& (!getTablero().getCasilla(
						posicion = new Point(getPosicion().x - 1,
								getPosicion().y)).isOcupado())) {
			getTablero().setCasilla(getPosicion(), Estado.Vacia,
					Orientacion.Este);
			getTablero()
					.setCasilla(posicion, Estado.Enemigo, Orientacion.Oeste);
			setPosicion(posicion);
			setOrientacion(Orientacion.Oeste);
		} else if ((getPosicion().y < objetivo.y)
				&& (!getTablero().getCasilla(
						posicion = new Point(getPosicion().x,
								getPosicion().y + 1)).isOcupado())) {
			getTablero().setCasilla(getPosicion(), Estado.Vacia,
					Orientacion.Este);
			getTablero().setCasilla(posicion, Estado.Enemigo, Orientacion.Sur);
			setPosicion(posicion);
			setOrientacion(Orientacion.Sur);

		} else if ((getPosicion().y > objetivo.y)
				&& (!getTablero().getCasilla(
						posicion = new Point(getPosicion().x,
								getPosicion().y - 1)).isOcupado())) {
			getTablero().setCasilla(getPosicion(), Estado.Vacia,
					Orientacion.Este);
			getTablero()
					.setCasilla(posicion, Estado.Enemigo, Orientacion.Norte);
			setPosicion(posicion);
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
