import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Tablero extends JPanel {
	private Casilla casillas[][];
	private final static int SIZE = 20;

	Tablero() {
		casillas = new Casilla[SIZE][SIZE];
		this.setLayout(new GridLayout(SIZE, SIZE));
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				casillas[i][j] = new Casilla();
				this.add(casillas[i][j]);
			}
		}		
	}
	void setCasilla(Point punto, int estado){
		casillas[punto.x][punto.y].setEstado(estado);
		repaint();
	}
}
