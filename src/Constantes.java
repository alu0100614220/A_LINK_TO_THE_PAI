import java.awt.Toolkit;

public interface Constantes {
	// Resolución de la pantalla
	int HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	int WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();

	// Estados de una casilla
	enum estado {
		Vacia, Obstaculo, Heroe, Enemigo
	}

	// Orientación de los personajes
	enum orientacion {
		Norte, Este, Sur, Oeste
	}
	
	// Armas del jugador
	enum arma {
		Espada, Arco
	}
}
