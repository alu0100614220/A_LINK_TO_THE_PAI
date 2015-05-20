
/**
 * Clase que representa las casillas en las que
 * se encontrarán los diferentes elementos del juego.
 *
 */
public class Casilla implements Componentes {
	private Estado estado; //Estado de la casilla (Héroe, Enemigo, etc)
	private Orientacion orientacion; //Orientación del elemento (si es Héroe o Enemigo)
	private boolean ocupado; //Booleano para determinar si la casilla está ocupada.

	/**
	 * Constructor por defecto.
	 * Define la casilla al estado vacía.
	 */
	public Casilla() {
		setEstado(Estado.Vacia);
		setOrientacion(Orientacion.Sur);
	}

	/**
	 * Construye la casilla a partir de un Estado pasado por parámetro.
	 * 
	 * @param estado
	 */
	public Casilla(Estado estado) {
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
	 * Cambia el booleano ocupado según el Estado pasado por parámetro.
	 * 
	 * @param estado
	 */
	public void setEstado(Estado estado) {
		this.estado = estado;
		switch (getEstado()) {
		case Vacia:
			setOcupado(false);
			break;
		case Puerta:
			setOcupado(false);
			break;
		default:
			setOcupado(true);
			break;
		}
	}

	/*
	 * Getters y Setters
	 */
	
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