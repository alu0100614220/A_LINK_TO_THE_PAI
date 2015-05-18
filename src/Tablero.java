import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Tablero extends JPanel implements Componentes {
	private final static int SIZE = 20; // Tama��o del mapa
	private Casilla casillas[][]; // Casillas del tablero que formar��n el mapa
	private int ancho = 0;
	private int alto = 0;
	Image suelo2 = (new ImageIcon("img/hierbilla2.gif")).getImage();
	Image suelo = (new ImageIcon("img/hierbilla.gif")).getImage();
	Image heroe = (new ImageIcon("img/heroeoncio.gif")).getImage();
	Image obstaculo = (new ImageIcon("img/obstaculo.gif")).getImage();
	Image enemigo = (new ImageIcon("img/enemigo.gif")).getImage();

	private ArrayList<Point> enemigos = new ArrayList<Point>();

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

	Tablero(int mundo, int mapa) {
		crearTablero(mundo, mapa);
	}

	public ArrayList<Point> getEnemigos() {
		return enemigos;
	}

	public void setEnemigos(ArrayList<Point> enemigos) {
		this.enemigos = enemigos;
	}

	public void crearTablero(int mundo, int mapa) {
		this.removeAll();
		casillas = null;
		try {
			BufferedReader bf = new BufferedReader(new FileReader("maps/"
					+ mundo + "/" + mapa + ".map"));
			ancho = Integer.parseInt(bf.readLine());
			alto = Integer.parseInt(bf.readLine());
			String linea = "";
			casillas = new Casilla[ancho][alto];
			// this.setLayout(new GridLayout(ancho, alto, 1, 1));
			while (bf.ready()) {
				for (int j = 0; j < alto; j++) {
					linea = bf.readLine();
					for (int i = 0; i < ancho; i++) {
						int numEstado = Integer.parseInt(linea.substring(i,
								i + 1));
						casillas[i][j] = new Casilla(obtenerEstado(numEstado));
						// this.add(casillas[i][j]);
						if (obtenerEstado(numEstado) == Estado.Enemigo) {
							Point punto = new Point(i, j);
							enemigos.add(punto);
						}
					}
				}
			}
			bf.close();
		} catch (Exception e) {
			System.err.println("Error de lectura");
		}
	}

	public void cambiarTablero(String mapa) {
		try {
			enemigos.clear();
			BufferedReader bf = new BufferedReader(new FileReader(mapa));
			ancho = Integer.parseInt(bf.readLine());
			alto = Integer.parseInt(bf.readLine());
			String linea = "";
			// this.setLayout(new GridLayout(ancho, alto, 1, 1));
			while (bf.ready()) {
				for (int j = 0; j < alto; j++) {
					linea = bf.readLine();
					for (int i = 0; i < ancho; i++) {
						int numEstado = Integer.parseInt(linea.substring(i,
								i + 1));
						casillas[i][j].setEstado(obtenerEstado(numEstado));
						// this.add(casillas[i][j]);
						if (obtenerEstado(numEstado) == Estado.Enemigo) {
							Point punto = new Point(i, j);
							enemigos.add(punto);
						}
					}
				}
			}
			bf.close();
		} catch (Exception e) {
			System.err.println("Error de lectura");
		}

	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		double ratioW = getWidth() / ancho;
		double ratioH = getHeight() / alto;
		for (int i = 0; i < ancho; i++)
			for (int j = 0; j < alto; j++) {
				g2.drawImage(suelo, (int) (i * ratioW), (int) (j * ratioH),
						(int) ratioW, (int) ratioH, this);
			}
		for (int i = 0; i < ancho; i++)
			for (int j = 0; j < alto; j++) {
				switch (casillas[i][j].getEstado()) {
				case Heroe:
					g2.drawImage(heroe, (int) (i * ratioW), (int) (j * ratioH),
							(int) ratioW, (int) ratioH, this);
					break;
				case Obstaculo:
					g2.drawImage(obstaculo, (int) (i * ratioW),
							(int) (j * ratioH), (int) ratioW, (int) ratioH,
							this);
					break;
				case Enemigo:
					g2.drawImage(enemigo, (int) (i * ratioW),
							(int) (j * ratioH), (int) ratioW,
							(int) (ratioH + ratioH / 2), this);
					break;
				default:
					break;
				}
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
		if (estado == Estado.Obstaculo.ordinal())
			return Estado.Obstaculo;
		if (estado == Estado.Heroe.ordinal())
			return Estado.Heroe;
		if (estado == Estado.Enemigo.ordinal())
			return Estado.Enemigo;
		if (estado == Estado.Puerta.ordinal())
			return Estado.Puerta;
		if (estado == Estado.Objeto.ordinal())
			return Estado.Objeto;
		return Estado.Vacia;
	}
}
