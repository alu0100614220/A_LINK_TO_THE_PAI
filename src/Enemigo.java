import java.awt.Point;

public class Enemigo implements Componentes {
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

	public void accion(Point objetivo) {
		
		if (objetivo.x < this.posicion.x) {
			this.posicion.x = this.posicion.x - 1;
		}
		if (objetivo.x > this.posicion.x) {
			this.posicion.x = this.posicion.x + 1;
		}
		if (objetivo.y < this.posicion.y) {
			this.posicion.y = this.posicion.y - 1;
		}
		if (objetivo.y > this.posicion.y) {
			this.posicion.y = this.posicion.y + 1;
		}
	}

	public void setPosicion(Point posicion) {
		this.posicion = posicion;
	}

	public boolean skill(Point objetivo){
		if (objetivo.distance(this.posicion)< 3) {
			System.out.println(objetivo.distance(this.posicion));
			return true;
		}
		return false;
	}
	public Orientacion getOrientacion() {
		return orientacion;
	}

	public void setOrientacion(Orientacion orientacion) {
		this.orientacion = orientacion;
	}
}
