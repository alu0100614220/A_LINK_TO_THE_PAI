import java.awt.Point;

public abstract class Enemigo implements Componentes {
	private int hp; // Vida del enemigo
	private Point posicion; // Posición en el mapa
	private Orientacion orientacion;

	Enemigo(int i, int j) {
		setPosicion(new Point(i, j));
		setOrientacion(Orientacion.Sur);
		this.setHp(100);
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

	public Point getPosicion() {
		return posicion;
	}

	public void setPosicion(Point posicion) {
		this.posicion = posicion;
	}

	public abstract void skill();

	public Orientacion getOrientacion() {
		return orientacion;
	}

	public void setOrientacion(Orientacion orientacion) {
		this.orientacion = orientacion;
	}
}
