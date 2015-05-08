import java.awt.Toolkit;

public interface Componentes {
	// Resolución de la pantalla
	int ALTO = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	int ANCHO = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();

	// Estados de una casilla
	enum Estado {
		Vacia, Obstaculo, Heroe, Enemigo
	}

	// Orientación de los personajes
	enum Orientacion {
		Norte, Este, Sur, Oeste
	}
	
	// Armas del jugador
	enum Arma {
		Espada, Arco
	}
}
