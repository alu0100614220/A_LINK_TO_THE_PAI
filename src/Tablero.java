import java.awt.GridLayout;
import java.awt.Point;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Tablero extends JPanel {
	private final static int SIZE = 20; // Tamaño del mapa
	private Casilla casillas[][]; // Casillas del tablero que formarán el mapa

	Tablero() {
		casillas = new Casilla[SIZE][SIZE];
		this.setLayout(new GridLayout(SIZE, SIZE));
		for (int j = 0; j < SIZE; j++) {
			for (int i = 0; i < SIZE; i++) {
				casillas[i][j] = new Casilla();
				this.add(casillas[i][j]);
			}
		}
	}

	void setCasilla(Point punto, int estado, int orientacion) {
		casillas[punto.x][punto.y].setEstado(estado);
		casillas[punto.x][punto.y].setOrientacion(orientacion);
		repaint();
	}
}
