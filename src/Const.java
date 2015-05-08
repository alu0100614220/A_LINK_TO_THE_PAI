import java.awt.Toolkit;

public interface Const {
	// Resolución de la pantalla
	int HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	int WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();

	// Estados de una casilla
	enum ESTADO {
		Vacia, Obstaculo, Heroe, Enemigo
	}

	// Orientación de los personajes
	enum ORIENTACION {
		Norte, Este, Sur, Oeste
	}
	
	// Armas del jugador
	enum ARMA {
		Espada, Arco
	}
}
