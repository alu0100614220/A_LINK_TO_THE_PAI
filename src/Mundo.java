import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Clase para representar el mundo que contendrá los diferentes mapas
 * La información del mundo y los mapas que lo componen se lee del
 * fichero config.map
 */
public class Mundo implements Componentes {
	private int ancho, alto;
	private int posicion;

	/**
	 * Constructor. Recibe el numero del mundo a generar.
	 * @param numMundo
	 */
	public Mundo(int numMundo) {
		try {
			BufferedReader bf = new BufferedReader(new FileReader("maps/"
					+ numMundo + "/config.map"));
			setAncho(Integer.parseInt(bf.readLine()));
			setAlto(Integer.parseInt(bf.readLine()));
			setPosicion(Integer.parseInt(bf.readLine()));
			bf.close();
		} catch (Exception e) {
			System.err.println("Error de lectura en mapa");
		}
	}

	/**
	 * Método que cambia el mapa en función de la orientación
	 * recibida como parámetro.
	 * @param orientacion
	 */
	public void cambioMapa(Orientacion orientacion) {
		switch (orientacion) {
		case Norte:
			posicion -= ancho;
			break;
		case Sur:
			posicion += ancho;
			break;
		case Este:
			posicion += 1;
			break;
		case Oeste:
			posicion -= 1;
			break;
		default:
			System.err.println("Orientación inválida.");
			break;
		}
	}

	/**
	 * Getter de la altura del mundo
	 * @return altura
	 */
	public int getAlto() {
		return alto;
	}

	/**
	 * Setter de la altura del mundo
	 * @param alto
	 */
	public void setAlto(int alto) {
		this.alto = alto;
	}

	/**
	 * Getter de la anchura del mundo
	 * @return anchura
	 */
	public int getAncho() {
		return ancho;
	}

	/**
	 * Setter de la anchura del mundo
	 * @param ancho
	 */
	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	/**
	 * Getter de la posicion del mapa actual
	 * @return posición
	 */
	public int getPosicion() {
		return posicion;
	}

	/**
	 * Setter de la posicion del mapa actual
	 * @param posicion
	 */
	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}
}
