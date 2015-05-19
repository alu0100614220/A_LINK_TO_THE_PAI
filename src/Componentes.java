/**
 * Interfaz para almacenar diferentes constantes accesibles por el resto de clases
 */
public interface Componentes {
	// Resolución de la pantalla
	int ALTO = 700;
	int ANCHO = 700;

	/**
	 * Posibles estados de una casilla.
	 */
	enum Estado {
		Vacia, Obstaculo, Corazon, Enemigo, Puerta, Heroe, Cofre, Llave, Espada, Escudo
	}

	
	/**
	 * Diferentes orientaciones.
	 */
	enum Orientacion {
		Norte, Este, Sur, Oeste, AtacandoNorte, AtacandoEste, AtacandoSur, AtacandoOeste
	}
}