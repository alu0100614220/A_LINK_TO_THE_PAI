import java.awt.Color;
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

/**
 * Clase para representar el tablero donde tendrá lugar el juego.
 *
 */
@SuppressWarnings("serial")
public class Tablero extends JPanel implements Componentes {
	private final static int SIZE = 20; // Tamaño del mapa
	private Casilla casillas[][]; // Casillas del tablero que formarán el mapa
	private int ancho = 0; // Ancho del tablero
	private int alto = 0; // Alto del tablero

	/**
	 * Declaración de las diferentes imágenes que representarán los diferentes
	 * elementos del juego
	 */
	private Image suelo = (new ImageIcon("img/hierbilla.gif")).getImage();
	private Image heroeSur = (new ImageIcon("img/heroeoncio.gif")).getImage();
	private Image heroeNorte = (new ImageIcon("img/heroe_arriba.gif"))
			.getImage();
	private Image heroeEste = (new ImageIcon("img/heroe_derecha.gif"))
			.getImage();
	private Image heroeOeste = (new ImageIcon("img/heroe_izquierda.gif"))
			.getImage();
	private Image heroeAtaqueSur = (new ImageIcon("img/heroeAS.gif"))
			.getImage();
	private Image heroeAtaqueNorte = (new ImageIcon("img/heroeAN.gif"))
			.getImage();
	private Image heroeAtaqueEste = (new ImageIcon("img/heroeAE.gif"))
			.getImage();
	private Image heroeAtaqueOeste = (new ImageIcon("img/heroeAO.gif"))
			.getImage();
	private Image obstaculo = (new ImageIcon("img/obstaculo.gif")).getImage();
	private Image enemigoNorte = (new ImageIcon("img/enemigoN.gif")).getImage();
	private Image enemigoEste = (new ImageIcon("img/enemigoE.gif")).getImage();
	private Image enemigoSur = (new ImageIcon("img/enemigo.gif")).getImage();
	private Image enemigoOeste = (new ImageIcon("img/enemigoO.gif")).getImage();
	private Image llave = (new ImageIcon("img/llave.gif")).getImage();
	private Image cofre = (new ImageIcon("img/cofre.gif")).getImage();
	private Image espada = (new ImageIcon("img/espada.gif")).getImage();
	private Image escudo = (new ImageIcon("img/escudo.gif")).getImage();
	private Image corazon = (new ImageIcon("img/corazon.gif")).getImage();

	/**
	 * Array que contiene los enemigos que habrá en cada mapa
	 */
	private ArrayList<Point> enemigos = new ArrayList<Point>();

	/**
	 * Constructor por defecto. Inicializa el vector de casillas.
	 */
	public Tablero() {
		casillas = new Casilla[SIZE][SIZE];
		this.setLayout(new GridLayout(SIZE, SIZE));
		for (int j = 0; j < SIZE; j++) {
			for (int i = 0; i < SIZE; i++) {
				casillas[i][j] = new Casilla();
			}
		}
	}

	/**
	 * Constructor que recibe un mundo y un mapa y crea el tablero adecuado
	 * leyendo dicho mapa.
	 * 
	 * @param mundo
	 *            Mundo donde se contendrá el mapa
	 * @param mapa
	 *            Mapa a mostrar
	 */
	public Tablero(int mundo, int mapa) {
		crearTablero(mundo, mapa);
	}

	/**
	 * Getter del array de enemigos
	 * 
	 * @return Array de enemigos
	 */
	public ArrayList<Point> getEnemigos() {
		return enemigos;
	}

	/**
	 * Setter de enemigos.
	 * 
	 * @param enemigos
	 *            Nuevo array de enemigos a asignar.
	 */
	public void setEnemigos(ArrayList<Point> enemigos) {
		this.enemigos = enemigos;
	}

	/**
	 * Crea un tablero a partir del mundo y mapa especificados. Se leerá de
	 * fichero el mapa especificado.
	 * 
	 * @param mundo
	 *            Mundo donde se contendrá el mapa
	 * @param mapa
	 *            Mapa a mostrar
	 */
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

	/**
	 * Método que cambia el tablero al mapa especificado. Utilizado cuando el
	 * héroe cambia de escenario a otro.
	 * 
	 * @param mapa
	 *            Mapa al que cambiar
	 */
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

	/**
	 * Método que pintará todas las imágenes que forman parte del tablero a
	 * partir de la información del array de Casillas
	 */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
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
				switch (casillas[i][j].getEstado()) { // Switch que decide que
														// dibujar en funcion
														// del Estado de la
														// casilla
				case Heroe:

					switch (casillas[i][j].getOrientacion()) { // Switch que
																// decide la
																// orientación
																// del héroe en
																// función de
																// la
																// info de la
																// casilla
					case Norte:
						g2.drawImage(heroeNorte, (int) (i * ratioW),
								(int) (j * ratioH), (int) ratioW, (int) ratioH,
								this);
						break;
					case AtacandoNorte:
						g2.drawImage(heroeAtaqueNorte, (int) (i * ratioW),
								(int) (((j - 1) * ratioH) + ratioH / 2),
								(int) ratioW, (int) (ratioH + ratioH / 2), this);
						break;
					case Este:
						g2.drawImage(heroeEste, (int) (i * ratioW),
								(int) (j * ratioH), (int) ratioW, (int) ratioH,
								this);
						break;
					case AtacandoEste:
						g2.drawImage(heroeAtaqueEste, (int) (i * ratioW),
								(int) (j * ratioH),
								(int) (ratioW + ratioW / 2), (int) ratioH, this);
						break;

					case Sur:
						g2.drawImage(heroeSur, (int) (i * ratioW),
								(int) (j * ratioH), (int) ratioW, (int) ratioH,
								this);
						break;
					case AtacandoSur:
						g2.drawImage(heroeAtaqueSur, (int) (i * ratioW),
								(int) (j * ratioH), (int) ratioW,
								(int) (ratioH + ratioH / 2), this);
						break;
					case Oeste:
						g2.drawImage(heroeOeste, (int) (i * ratioW),
								(int) (j * ratioH), (int) ratioW, (int) ratioH,
								this);
						break;
					case AtacandoOeste:
						g2.drawImage(heroeAtaqueOeste,
								(int) (((i - 1) * ratioW) + ratioW / 2),
								(int) (j * ratioH),
								(int) (ratioW + ratioW / 2), (int) ratioH, this);
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
					switch (casillas[i][j].getOrientacion()) { // Switch que
																// decide la
																// orientación
																// del enemigo
																// en
																// función de
																// la
																// info de la
																// casilla
					case Norte:
						g2.drawImage(enemigoNorte, (int) (i * ratioW),
								(int) (((j - 1) * ratioH) + ratioH / 2),
								(int) ratioW, (int) (ratioH + ratioH / 2), this);
						break;
					case Este:
						g2.drawImage(enemigoEste, (int) (i * ratioW),
								(int) (j * ratioH),
								(int) (ratioW + ratioW / 2), (int) ratioH, this);
						break;
					case Sur:
						g2.drawImage(enemigoSur, (int) (i * ratioW),
								(int) (j * ratioH), (int) ratioW,
								(int) (ratioH + ratioH / 2), this);
						break;
					case Oeste:
						g2.drawImage(enemigoOeste,
								(int) (((i - 1) * ratioW) + ratioW / 2),
								(int) (j * ratioH),
								(int) (ratioW + ratioW / 2), (int) ratioH, this);
						break;
					default:
						System.err.println("Orientacion desconocida");
					}
					break;
				case Cofre:
					g2.drawImage(cofre, (int) (i * ratioW), (int) (j * ratioH),
							(int) ratioW, (int) (ratioH + ratioH / 2), this);
					break;
				case Llave:
					g2.drawImage(llave, (int) (i * ratioW), (int) (j * ratioH),
							(int) ratioW, (int) (ratioH + ratioH / 2), this);
					break;
				case Espada:
					g2.drawImage(espada, (int) (i * ratioW),
							(int) (j * ratioH), (int) (ratioW + ratioW / 2),
							(int) (ratioH + ratioH / 2), this);
					break;
				case Escudo:
					g2.drawImage(escudo, (int) (i * ratioW),
							(int) (j * ratioH), (int) (ratioW + ratioW / 2),
							(int) (ratioH + ratioH / 2), this);
					break;
				case Corazon:
					g2.drawImage(corazon, (int) (i * ratioW),
							(int) (j * ratioH), (int) (ratioW + ratioW / 2),
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

	/**
	 * Método para devolver el valor del enum de Estado a partir de un entero.
	 * 
	 * @param estado
	 * @return Estado correspondiente
	 */
	public Estado obtenerEstado(int estado) {
		if (estado == Estado.Obstaculo.ordinal())
			return Estado.Obstaculo;
		else if (estado == Estado.Heroe.ordinal())
			return Estado.Heroe;
		else if (estado == Estado.Enemigo.ordinal())
			return Estado.Enemigo;
		else if (estado == Estado.Puerta.ordinal())
			return Estado.Puerta;
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
