import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public interface Componentes {
	// Resolución de la pantalla
//	int ALTO = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
//	int ANCHO = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	int ALTO = 700;
	int ANCHO = 700;
	// Estados de una casilla
	enum Estado {
		Vacia, Obstaculo, Heroe, Enemigo, Puerta, Objeto
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