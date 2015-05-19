import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Casilla extends JPanel implements Componentes {
	private Estado estado;
	private Orientacion orientacion;
	private boolean ocupado;

	Casilla() {
		setEstado(Estado.Vacia);
		setOrientacion(Orientacion.Sur);
	}

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
			setOcupado(false);
			break;
		case Espada:
			setOcupado(false);
			break;
		case Escudo:
			setOcupado(false);
			break;
		case Objeto:
			setOcupado(false);
			break;
		case Cofre:
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