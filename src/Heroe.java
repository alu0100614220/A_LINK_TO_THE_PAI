import java.awt.Point;

public class Heroe implements Componentes {
	private int HP = 100; // Vida inicial del jugador
	private int hp; // Vida del jugador
	private Point posicion; // Posiciï¿½ï¿½n en el mapa del jugador
	private Orientacion orientacion; // Orientaciï¿½ï¿½n en el mapa del jugador
	private int danio = -50;
	//Booleanos para saber que objetos tiene el heroe
	private boolean llave = false; 
	private boolean espada = false;
	private boolean escudo = false;
	private boolean corazon = false;
	
	Heroe(int i, int j) {
		setPosicion(new Point(i, j));
		setOrientacion(Orientacion.Sur);//Orientado por defecto mirando hacia abajo
		this.setHp(HP);
	}

	/**
	 * Getters & Setters
	 */
	/**
	 * Vida
	 */
	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = this.hp + hp;
	}
	/**
	 * Reinicio de la vida
	 */
	public void resetHp() {
		this.hp = 100;
	}
	
	/**
	 * Posicion
	 */
	public Point getPosicion() {
		return posicion;
	}

	public void setPosicion(Point posicion) {
		this.posicion = posicion;
	}
	/**
	 * Orientacion
	 */
	public Orientacion getOrientacion() {
		return orientacion;
	}

	public void setOrientacion(Orientacion orientacion) {
		this.orientacion = orientacion;
	}
	/**
	 * Para el daño
	 */
	public int getDanio() {
		return danio;
	}

	public void setDanio(int danio) {
		this.danio = danio;
	}
	/**
	 * Cuando consiga la llave
	 */
	public boolean getLlave() {
		return llave;
	}

	public void setLlave(boolean llave) {
		this.llave = llave;
	}
	/**
	 * Espada
	 */
	public boolean getEspada() {
		return espada;
	}

	public void setEspada(boolean espada) {
		this.espada = espada;
		this.danio = -100;
	}
	/**
	 * Escudo
	 */
	public boolean getEscudo() {
		return escudo;
	}

	public void setEscudo(boolean escudo) {
		this.escudo = escudo;
	}
	/**
	 * Aumentando la vida con corazon
	 */
	public boolean getCorazon() {
		return corazon;
	}

	public void setCorazon(boolean corazon) {
		this.corazon = corazon;
		this.hp = 150;
	}
	
	/**
	 * @param enemy
	 * Verdadero si tiene un enemigo en la posicion en la que esta mirando
	 * @return
	 */
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
		default:
			break;
		}
		if (enemy.getPosicion().x == punto.x
				&& enemy.getPosicion().y == punto.y) {
			return true;
		} else {
			return false;
		}

	}
	
}
