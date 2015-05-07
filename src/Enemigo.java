import java.awt.Point;

public abstract class Enemigo {
	private int hp; // Vida del enemigo
	private Point posicion; // Posición en el mapa
	private int orientacion;

	Enemigo(int i, int j) {
		setPosicion(new Point(i, j));
		setOrientacion(0);
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

	public int getOrientacion() {
		return orientacion;
	}

	public void setOrientacion(int orientacion) {
		this.orientacion = orientacion;
	}
}
