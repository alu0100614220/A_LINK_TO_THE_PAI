
/**
 * Clase que representa las casillas en las que
 * se encontrarán los diferentes elementos del juego
 */
public class Casilla implements Componentes {
	private Estado estado; //Estado de la casilla (Héroe, Enemigo, etc)
	private Orientacion orientacion; //Orientación del elemento (si es Héroe o Enemigo)
	private boolean ocupado; //Booleano para determinar si la casilla está ocupada.

	/**
	 * Constructor por defecto.
	 * Por defecto la casilla está vacía.
	 */
	Casilla() {
		setEstado(Estado.Vacia);
		setOrientacion(Orientacion.Sur);
	}

	/**
	 * Construye la casilla con un Estado determinado
	 * @param estado
	 */
	Casilla(Estado estado) {
		setEstado(estado);
		setOrientacion(Orientacion.Sur);
	}

	/*
	 * Getters & Setters
	 */

	public Estado getEstado() {
		return estado;
	}

	/**
	 * Setter de estado. También cambia el booleano de ocupación
	 * @param estado
	 */
	public void setEstado(Estado estado) {
		this.estado = estado;
		switch (getEstado()) {
		case Obstaculo:
			setOcupado(true);
			break;
		case Heroe:
			setOcupado(true);
			break;
		case Enemigo:
			setOcupado(true);
			break;
		case Llave:
			setOcupado(true);
			break;
		case Espada:
			setOcupado(true);
			break;
		case Escudo:
			setOcupado(true);
			break;
		case Objeto:
			setOcupado(false);
			break;
		case Cofre:
			setOcupado(true);
			break;
		case Corazon:
			setOcupado(true);
			break;
		default:
			setOcupado(false);
			break;
		}
	}

	public Orientacion getOrientacion() {
		return orientacion;
	}

	public void setOrientacion(Orientacion orientacion) {
		this.orientacion = orientacion;
	}

	public boolean isOcupado() {
		return ocupado;
	}

	public void setOcupado(boolean ocupado) {
		this.ocupado = ocupado;
	}
}