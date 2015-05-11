import java.awt.GridLayout;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Tablero extends JPanel implements Componentes {
	private final static int SIZE = 20; // Tama��o del mapa
	private Casilla casillas[][]; // Casillas del tablero que formar��n el mapa
	private int ancho = 0;
	private int alto = 0;

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
	
	Tablero(String mapa) {
		crearTablero(mapa);
	}
	
	public void crearTablero(String mapa) {
		this.removeAll();
		casillas = null;
		try {
			BufferedReader bf = new BufferedReader(new FileReader(mapa));
			ancho = Integer.parseInt(bf.readLine());
			alto = Integer.parseInt(bf.readLine());
			String linea = "";
			casillas = new Casilla[ancho][alto];
			this.setLayout(new GridLayout(ancho, alto, 1, 1));
			while (bf.ready()) {
				for (int j = 0; j < alto; j++) {
					linea = bf.readLine();
					for (int i = 0; i < ancho; i++) {
						int numEstado = Integer.parseInt(linea.substring(i, i + 1));
						casillas[i][j] = new Casilla(obtenerEstado(numEstado));
						this.add(casillas[i][j]);
					}
				}
			}
			bf.close();
		}
		catch(Exception e) {
			System.err.println("Error de lectura");
		}
	}
	
	public void cambiarTablero(String mapa) {
		try {
			BufferedReader bf = new BufferedReader(new FileReader(mapa));
			ancho = Integer.parseInt(bf.readLine());
			alto = Integer.parseInt(bf.readLine());
			String linea = "";
			this.setLayout(new GridLayout(ancho, alto, 1, 1));
			while (bf.ready()) {
				for (int j = 0; j < alto; j++) {
					linea = bf.readLine();
					for (int i = 0; i < ancho; i++) {
						int numEstado = Integer.parseInt(linea.substring(i, i + 1));
						casillas[i][j].setEstado(obtenerEstado(numEstado));
						this.add(casillas[i][j]);
					}
				}
			}
			bf.close();
		}
		catch(Exception e) {
			System.err.println("Error de lectura");
		}
				
	}

	/*
	 * Getters & Setters
	 */
	
	public int getAncho() {
		return ancho;
	}
	
	public int getAlto() {
		return alto;
	}
	
	public Casilla getCasilla(Point punto) {
		return casillas[punto.x][punto.y];
	}
	
	public void setCasilla(Point punto, Estado estado, Orientacion orientacion) {
		casillas[punto.x][punto.y].setEstado(estado);
		casillas[punto.x][punto.y].setOrientacion(orientacion);
		repaint();
	}
	
	public Estado obtenerEstado(int estado) {
		if(estado == Estado.Obstaculo.ordinal())
			return Estado.Obstaculo;
		if(estado == Estado.Heroe.ordinal())
			return Estado.Heroe;
		if(estado == Estado.Enemigo.ordinal())
			return Estado.Enemigo;
		return Estado.Vacia;
	}
}
