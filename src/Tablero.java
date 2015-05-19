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
	private Casilla casillas[][]; // Casillas del tablero que formar��n el
									// mapa
	private int ancho = 0;
	private int alto = 0;
	Image suelo2 = (new ImageIcon("img/hierbilla2.gif")).getImage();
	Image suelo = (new ImageIcon("img/hierbilla.gif")).getImage();
	Image heroeS = (new ImageIcon("img/heroeoncio.gif")).getImage();
	Image heroeN = (new ImageIcon("img/heroe_arriba.gif")).getImage();
	Image heroeE = (new ImageIcon("img/heroe_derecha.gif")).getImage();
	Image heroeO = (new ImageIcon("img/heroe_izquierda.gif")).getImage();
	Image obstaculo = (new ImageIcon("img/obstaculo.gif")).getImage();
	Image enemigoN = (new ImageIcon("img/enemigoN.gif")).getImage();
	Image enemigoE = (new ImageIcon("img/enemigoE.gif")).getImage();
	Image enemigoS = (new ImageIcon("img/enemigo.gif")).getImage();
	Image enemigoO = (new ImageIcon("img/enemigoO.gif")).getImage();
	Image llave = (new ImageIcon("img/llave.gif")).getImage();
	Image cofre = (new ImageIcon("img/cofre.gif")).getImage();
	Image espada = (new ImageIcon("img/espada.gif")).getImage();
	Image escudo = (new ImageIcon("img/escudo.gif")).getImage();
	Image corazon = (new ImageIcon("img/corazon.gif")).getImage();

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
			while (bf.ready()) {
				for (int j = 0; j < alto; j++) {
					linea = bf.readLine();
					for (int i = 0; i < ancho; i++) {
						int numEstado = Integer.parseInt(linea.substring(i,
								i + 1));
						casillas[i][j] = new Casilla(obtenerEstado(numEstado));
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
			while (bf.ready()) {
				for (int j = 0; j < alto; j++) {
					linea = bf.readLine();
					for (int i = 0; i < ancho; i++) {
						int numEstado = Integer.parseInt(linea.substring(i,
								i + 1));
						casillas[i][j].setEstado(obtenerEstado(numEstado));
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
		System.out.println(mapa);
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
					
					switch (casillas[i][j].getOrientacion()) {
					case Norte:
						g2.drawImage(heroeN, (int) (i * ratioW), (int) (j * ratioH),
								(int) ratioW, (int) ratioH, this);
						break;
					case Este:
						g2.drawImage(heroeE, (int) (i * ratioW), (int) (j * ratioH),
								(int) ratioW, (int) ratioH, this);
						break;
					case Sur:
						g2.drawImage(heroeS, (int) (i * ratioW), (int) (j * ratioH),
								(int) ratioW, (int) ratioH, this);
						break;
					case Oeste:
						g2.drawImage(heroeO, (int) (i * ratioW), (int) (j * ratioH),
								(int) ratioW, (int) ratioH, this);
						break;
					default:
						System.err.println("Orientacion desconocida");
					}
					break;
				case Obstaculo:
					g2.drawImage(obstaculo, (int) (i * ratioW),
							(int) (j * ratioH), (int) ratioW, (int) ratioH,
							this);
					break;
				case Enemigo:
					switch (casillas[i][j].getOrientacion()) {
					case Norte:
						g2.drawImage(enemigoN, (int) (i * ratioW), (int) (j * ratioH),
								(int) ratioW, (int) (ratioH + ratioH / 2), this);
						break;
					case Este:
						g2.drawImage(enemigoE, (int) (i * ratioW), (int) (j * ratioH),
								(int) (ratioW + ratioW / 2), (int) ratioH, this);
						break;
					case Sur:
						g2.drawImage(enemigoS, (int) (i * ratioW), (int) (j * ratioH),
								(int) ratioW, (int) (ratioH + ratioH / 2), this);
						break;
					case Oeste:
						g2.drawImage(enemigoO, (int) (i * ratioW), (int) (j * ratioH),
								(int) (ratioW + ratioW / 2), (int) ratioH, this);
						break;
					default:
						System.err.println("Orientacion desconocida");
					}
					break;
				case Cofre:
					g2.drawImage(cofre, (int) (i * ratioW),
							(int) (j * ratioH), (int) ratioW,
							(int) (ratioH + ratioH / 2), this);
					break;
				case Llave:
					g2.drawImage(llave, (int) (i * ratioW),
							(int) (j * ratioH), (int) ratioW,
							(int) (ratioH + ratioH / 2), this);
					break;
				case Espada:
					g2.drawImage(espada, (int) (i * ratioW) + 12,
							(int) (j * ratioH) + 12, this);
					break;
				case Escudo:
					g2.drawImage(escudo, (int) (i * ratioW) + 12,
							(int) (j * ratioH) + 12, this);
					break;
				case Corazon:
					g2.drawImage(corazon, (int) (i * ratioW) + 12,
							(int) (j * ratioH) + 12, this);
					break;
				default:
					System.err.println("Estado a dibujar desconocido.");
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
		else if (estado == Estado.Heroe.ordinal())
			return Estado.Heroe;
		else if (estado == Estado.Enemigo.ordinal())
			return Estado.Enemigo;
		else if (estado == Estado.Puerta.ordinal())
			return Estado.Puerta;
		else if (estado == Estado.Objeto.ordinal())
			return Estado.Objeto;
		else if (estado == Estado.Cofre.ordinal())
			return Estado.Cofre;
		else if (estado == Estado.Llave.ordinal())
			return Estado.Llave;
		else if (estado == Estado.Espada.ordinal())
			return Estado.Espada;
		else if (estado == Estado.Escudo.ordinal())
			return Estado.Escudo;
		else if (estado == Estado.Corazon.ordinal())
			return Estado.Corazon;
		return Estado.Vacia;
	}
}
