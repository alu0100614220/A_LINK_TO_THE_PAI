import java.awt.Point;


public class Heroe {
	private int hp;
	private int arma;
	private Point posicion;
	private int orientacion;
	Heroe(int i, int j){
		setPosicion(new Point(i,j));
		setOrientacion(0);
		this.setHp(100);
		this.setArma(0);
	}
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
