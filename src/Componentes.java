public interface Componentes {
	// Resolución de la pantalla
	// int ALTO = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	// int ANCHO = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	int ALTO = 700;
	int ANCHO = 700;

	// Estados de una casilla
	enum Estado {
		Vacia, Obstaculo, Corazon, Enemigo, Puerta, Heroe, Cofre, Llave, Espada, Escudo
	}

	// Orientación de los personajes
	enum Orientacion {
		Norte, Este, Sur, Oeste, AtacandoNorte, AtacandoEste, AtacandoSur, AtacandoOeste
	}
}