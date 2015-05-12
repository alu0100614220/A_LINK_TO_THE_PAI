import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Enemigo implements Componentes {
	private int hp; // Vida del enemigo
	private Point posicion; // Posición en el mapa
	private Orientacion orientacion;
	private Tablero tablero;
	private Timer accion;
	private final static int SPEED = 500;

	Enemigo(Point punto, Tablero tableraso) {
		accion = new Timer(SPEED, new Listener());
		accion.start();
		tablero = tableraso;
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

	public int getHp() {
		return hp;
	}
	public void atacar(){
		Point punto = getPosicion();
		switch (getOrientacion()) {
		case Norte:
			punto.y = punto.y - 1;
			tablero.getCasilla(punto).setDanio(100);
			tablero.setCasilla(punto, Estado.Puerta, orientacion);
			break;
		case Sur:
			punto.y = punto.y + 1;
			tablero.getCasilla(punto).setDanio(100);
			tablero.setCasilla(punto, Estado.Puerta, orientacion);
			break;
		case Este:
			punto.x = punto.x + 1;
			tablero.getCasilla(punto).setDanio(100);
			tablero.setCasilla(punto, Estado.Puerta, orientacion);
			break;
		case Oeste:
			punto.x = punto.x - 1;
			tablero.getCasilla(punto).setDanio(100);
			tablero.setCasilla(punto, Estado.Puerta, orientacion);
			break;
		}
		
	}
	public void actuar(Point objetivo){
		if (skill(objetivo)) {
			atacar();
		}else{
			mover(objetivo);
		}
	}

	public void mover(Point objetivo) {
		if (getPosicion().x < objetivo.x) {
			Point posicion = new Point(getPosicion().x + 1, getPosicion().y);
			if (!getTablero().getCasilla(posicion).isOcupado()) {
				getTablero().setCasilla(getPosicion(), Estado.Vacia,
						Orientacion.Este);
				getTablero().setCasilla(posicion, Estado.Enemigo,
						Orientacion.Este);
				setPosicion(posicion);
				setOrientacion(Orientacion.Este);
			}
		} else if (getPosicion().x > objetivo.x) {
			Point posicion = new Point(getPosicion().x - 1, getPosicion().y);
			if (!getTablero().getCasilla(posicion).isOcupado()) {
				getTablero().setCasilla(getPosicion(), Estado.Vacia,
						Orientacion.Este);
				getTablero().setCasilla(posicion, Estado.Enemigo,
						Orientacion.Oeste);
				setPosicion(posicion);
				setOrientacion(Orientacion.Oeste);
			}
		} else if (getPosicion().y < objetivo.y) {
			Point posicion = new Point(getPosicion().x, getPosicion().y + 1);
			if (!getTablero().getCasilla(posicion).isOcupado()) {
				getTablero().setCasilla(getPosicion(), Estado.Vacia,
						Orientacion.Este);
				getTablero().setCasilla(posicion, Estado.Enemigo,
						Orientacion.Sur);
				setPosicion(posicion);
				setOrientacion(Orientacion.Sur);
			}
		} else if (getPosicion().y > objetivo.y) {
			Point posicion = new Point(getPosicion().x, getPosicion().y - 1);
			if (!getTablero().getCasilla(posicion).isOcupado()) {
				getTablero().setCasilla(getPosicion(), Estado.Vacia,
						Orientacion.Este);
				getTablero().setCasilla(posicion, Estado.Enemigo,
						Orientacion.Norte);
				setPosicion(posicion);
				setOrientacion(Orientacion.Norte);
			}
		}
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

	public boolean skill(Point objetivo) {
		if (objetivo.distance(this.posicion) < 3) {
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

	class Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
		}
	}
}
