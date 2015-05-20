import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.Timer;

/**
 * Es la clase principal, gestionará en general todo el juego en sí. Controla
 * los movimientos del heroe, enemigos, etc. Tambien controla toda la lógica.
 * 
 */
@SuppressWarnings("serial")
public class Juego extends JFrame implements Componentes {
	private Tablero tablero; // Mapa actual en el juego
	private Heroe heroe = new Heroe(1, 1); // Es el personaje que controla el
											// jugador
	private ArrayList<Enemigo> enemigos; // Array donde se almacenarán los
											// enemigos
	private TecladoListener listener = new TecladoListener();

	/*
	 * Timers de: - Movimiento de enemigo - Vida del héroe - Tiempo de
	 * reutilización de acción del héroe - Controla las animaciones de héroe
	 */
	private Timer movEnemigo;
	private Timer heroeLife;
	private Timer heroeCoolDown;
	private Timer animacion;

	private boolean pausa = false; // Controla si el juego está o no en pausa
	private Panel panelHeroe; // Panel de información del héroe
	private int mundoActual = 1; // Mundo en el que se sitúa el juego
	private Mundo mundo = new Mundo(mundoActual); // Carga los mapas del mundo
	private PanelInferior panelInfo = new PanelInferior(this); // Panel de
																// información
																// del juego
	private Orientacion antigua = Orientacion.Sur;

	public Juego() {
		movEnemigo = new Timer(500, new ListenerEnemigos());
		heroeLife = new Timer(50, new ListenerEnemigos());
		heroeCoolDown = new Timer(100, new CDListener());
		animacion = new Timer(500, new accion());
		panelHeroe = new Panel(getHeroe());
		panelInfo = new PanelInferior(this);
		setTitle("A Link to the PAI");
		setSize(ANCHO, ALTO);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setTablero(new Tablero(mundoActual, mundo.getPosicion()));
		this.add(getTablero(), BorderLayout.CENTER);
		this.add(panelHeroe, BorderLayout.NORTH);
		this.add(panelInfo, BorderLayout.SOUTH);
		this.addKeyListener(listener);
		getTablero().setCasilla(getHeroe().getPosicion(), Estado.Heroe,
				Orientacion.Sur);
		setEnemigos();
		movEnemigo.start(); // Inicio movimiento enemigo
	}

	/**
	 * Llamaremos al método cada vez que pulsemos restart y reiniciará el juego
	 */
	public void reinicia() {
		// Paramos los timers
		movEnemigo.stop();
		heroeLife.stop();
		heroeCoolDown.stop();
		animacion.stop();
		// Volvemos al mapa inicial
		getTablero().cambiarTablero("maps/" + getMundoActual() + "/1.map");
		this.setMundoActual(1);
		this.mundo.setPosicion(1);
		// Reiniciamos al héroe en la posicion 1,1
		setHeroe(new Heroe(1, 1));
		// Ocultamos el panel y lo reiniciamos
		panelHeroe.setVisible(false);
		panelHeroe = new Panel(getHeroe());
		this.add(panelHeroe, BorderLayout.NORTH);
		// Añadimos el heroe al tablero
		getTablero().setCasilla(getHeroe().getPosicion(), Estado.Heroe,
				Orientacion.Sur);
		setEnemigos();
	}

	/**
	 * Inicializa el array de enemigos
	 */
	private void setEnemigos() {
		enemigos = new ArrayList<Enemigo>();
		for (int i = 0; i < getTablero().getEnemigos().size(); i++) {
			enemigos.add(new Enemigo(getTablero().getEnemigos().get(i),
					getTablero()));
		}
	}

	/*
	 * Getters & Setters
	 */

	public Tablero getTablero() {
		return tablero;
	}

	public void setTablero(Tablero tablero) {
		this.tablero = tablero;
	}

	public Heroe getHeroe() {
		return heroe;
	}

	public void setHeroe(Heroe heroe) {
		this.heroe = heroe;
	}

	public ArrayList<Enemigo> getEnemigos() {
		return enemigos;
	}

	public void setEnemigos(ArrayList<Enemigo> enemigos) {
		this.enemigos = enemigos;
	}

	public int getMundoActual() {
		return mundoActual;

	}

	public void setMundoActual(int mundoActual) {
		this.mundoActual = mundoActual;
	}

	/**
	 * Cambia al tablero nuevo
	 */
	private void cambiarMapa(Point posicionNueva, Orientacion aux) {
		enemigos.clear();
		mundo.cambioMapa(aux);
		getHeroe().setPosicion(posicionNueva);
		getTablero().cambiarTablero(
				"maps/" + mundoActual + "/" + mundo.getPosicion() + ".map");
		getTablero().setCasilla(getHeroe().getPosicion(), Estado.Heroe, aux);
		setEnemigos();
	}

	/**
	 * Comprobará a que dirección se irá dependiendo de la orientación
	 */
	public void comprobarCambio() {
		Point posicion = getHeroe().getPosicion();
		Point posicionNueva;
		if (posicion.x == 0) {
			posicionNueva = new Point(getTablero().getAncho() - 2, getHeroe()
					.getPosicion().y);
			cambiarMapa(posicionNueva, Orientacion.Oeste);
		} else if (posicion.y == 0) {
			posicionNueva = new Point(getHeroe().getPosicion().x,
					tablero.getAlto() - 2);
			cambiarMapa(posicionNueva, Orientacion.Norte);
		} else if (posicion.x == getTablero().getAncho() - 1) {
			posicionNueva = new Point(1, getHeroe().getPosicion().y);
			cambiarMapa(posicionNueva, Orientacion.Este);
		} else if (posicion.y == getTablero().getAlto() - 1) {
			posicionNueva = new Point(getHeroe().getPosicion().x, 1);
			cambiarMapa(posicionNueva, Orientacion.Sur);
		}
	}

	/**
	 * Si la posición es una casilla vacia devuelve true. Si no es vacia
	 * comprobamos cada uno de los tipos que puede ser.
	 * 
	 * @param posicion
	 * @return
	 */
	private boolean condicionParaMoverse(Point posicion) {
		if (!getTablero().getCasilla(posicion).isOcupado()) {
			return true;
		}
		switch (getTablero().getCasilla(posicion).getEstado()) {
		case Llave:
			getHeroe().setLlave(true);
			return true;
		case Espada:
			getHeroe().setEspada(true);
			return true;
		case Escudo:
			getHeroe().setEscudo(true);
			return true;
		case Corazon:
			getHeroe().setCorazon(true);
			return true;
		default:
			return false;
		}
	}

	/**
	 * Le pasamos la orientación y la posición, y actualizamos el tablero,
	 * quitando al héroe de su posición antigua y poniendolo en la nueva con la
	 * orientación
	 * 
	 * @param posicion
	 * @param orientacion
	 */
	private void moverse(Point posicion, Orientacion orientacion) {
		getTablero().setCasilla(getHeroe().getPosicion(), Estado.Vacia,
				orientacion);
		getTablero().setCasilla(posicion, Estado.Heroe, orientacion);
		getHeroe().setPosicion(posicion);
		getHeroe().setOrientacion(orientacion);
	}

	/**
	 * Si el heroe esta bloqueado por algun objeto y se mueve en una direccion,
	 * se cambia la orientacion hacia esa direccion
	 */
	private void girarse(Orientacion aux) {
		getTablero().setCasilla(getHeroe().getPosicion(), Estado.Heroe, aux);
		getHeroe().setOrientacion(aux);
	}

	/**
	 * Clase que gestiona los eventos por teclado
	 *
	 */
	class TecladoListener extends KeyAdapter {

		public void keyPressed(KeyEvent e) {
			pausa = false;
			panelHeroe.repaint();
			if (getHeroe().getHp() > 0) { // Controlamos que podamos mover al
				// héroe si sigue vivo
				movEnemigo.start();

				if (e.getKeyCode() == KeyEvent.VK_P) { // Pausara el juego
					if (pausa == false) {
						pausa = true;
						movEnemigo.stop();
					}
					panelHeroe.pintaPause(pausa);

				} else {
					panelHeroe.pintaPause(false);
				}

				if (e.getKeyCode() == KeyEvent.VK_E
						&& !heroeCoolDown.isRunning()) { // Abrir cofre,
					Point punto = new Point(getHeroe().getPosicion());
					switch (getHeroe().getOrientacion()) { // Comprueba la
															// casilla a la que
															// esta mirando
					case Norte:
						punto.y = punto.y - 1;
						break;
					case Sur:
						punto.y = punto.y + 1;
						break;
					case Este:
						punto.x = punto.x + 1;
						break;
					case Oeste:
						punto.x = punto.x - 1;
						break;
					default:
						break;
					}
					// Comprueba la casilla a la que está mirando si es un cofre
					if (getTablero().getCasilla(punto).getEstado() == Estado.Cofre
							&& getHeroe().getLlave()) {
						getTablero().cambiarTablero(
								"maps/" + mundoActual + "/25.map");
						// Si es así y tiene la llave acabaría el juego yendo a
						// la posicion 25 del mapa
						Point ending = new Point(11, 6);
						getTablero().setCasilla(ending, Estado.Heroe,
								Orientacion.Sur);
						getHeroe().setPosicion(ending);
						getHeroe().setOrientacion(Orientacion.Norte);
						enemigos.clear();
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_A
						&& !heroeCoolDown.isRunning()) { // Controlamos el CD
					antigua = getHeroe().getOrientacion(); // Almacenamos la
															// orientación
															// antigua del héroe
															// para el manejo de
															// los sprites
					switch (heroe.getOrientacion()) { // Cambiará su
														// orintación a atacando
					case Norte:
						getTablero().setCasilla(getHeroe().getPosicion(),
								Estado.Heroe, Orientacion.AtacandoNorte);
						break;
					case Sur:
						getTablero().setCasilla(getHeroe().getPosicion(),
								Estado.Heroe, Orientacion.AtacandoSur);
						break;
					case Este:
						getTablero().setCasilla(getHeroe().getPosicion(),
								Estado.Heroe, Orientacion.AtacandoEste);
						break;
					case Oeste:
						getTablero().setCasilla(getHeroe().getPosicion(),
								Estado.Heroe, Orientacion.AtacandoOeste);
						break;
					default:
						break;
					}
					animacion.start(); // Lanzamos el sprite
					for (int i = 0; i < enemigos.size(); i++) { // Comprueba que
																// haya
																// enemigos en
																// su rango
						if (getHeroe().atacar(enemigos.get(i))) {
							enemigos.get(i).setHp(getHeroe().getDanio());
						}
						if (enemigos.get(i).getHp() <= 0) {
							getTablero().setCasilla(
									enemigos.get(i).getPosicion(),
									Estado.Vacia, Orientacion.Este);
							enemigos.remove(i);
						}
					}
					heroeCoolDown.start();

				}

				// Movimiento del héroe
				if (e.getKeyCode() == KeyEvent.VK_UP
						&& !heroeCoolDown.isRunning()) {
					Point posicion = new Point(getHeroe().getPosicion().x,
							getHeroe().getPosicion().y - 1);
					if (condicionParaMoverse(posicion)) { // Comprobamos que se
															// puede mover a esa
															// casilla
						moverse(posicion, Orientacion.Norte); // LLamamos a
																// moverse que
																// actualizará
																// la posición
					} else {
						girarse(Orientacion.Norte);
					}

				} else if (e.getKeyCode() == KeyEvent.VK_DOWN
						&& !heroeCoolDown.isRunning()) {
					Point posicion = new Point(getHeroe().getPosicion().x,
							getHeroe().getPosicion().y + 1);
					if (condicionParaMoverse(posicion)) {
						moverse(posicion, Orientacion.Sur);
					} else {
						girarse(Orientacion.Sur);
					}
				} else if (e.getKeyCode() == KeyEvent.VK_LEFT
						&& !heroeCoolDown.isRunning()) {
					Point posicion = new Point(getHeroe().getPosicion().x - 1,
							getHeroe().getPosicion().y);
					if (condicionParaMoverse(posicion)) {
						moverse(posicion, Orientacion.Oeste);
					} else {
						girarse(Orientacion.Oeste);
					}
				} else if (e.getKeyCode() == KeyEvent.VK_RIGHT
						&& !heroeCoolDown.isRunning()) {
					Point posicion = new Point(getHeroe().getPosicion().x + 1,
							getHeroe().getPosicion().y);
					if (condicionParaMoverse(posicion)) {
						moverse(posicion, Orientacion.Este);
					} else {
						girarse(Orientacion.Este);
					}
				}
				comprobarCambio();
			}

		}
	}

	/**
	 * Clase que gestionará la acción, el sprite en sí de ataque
	 *
	 */
	class accion implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			// Cada vez que se llame a la funcion devolverá al heroe a su estado
			// original
			getTablero().setCasilla(getHeroe().getPosicion(), Estado.Heroe,
					antigua);
			animacion.stop();
		}

	}

	/**
	 * Clase que comprueba el CD del heroe
	 *
	 */
	class CDListener implements ActionListener {
		/**
		 * Si se llama el héroe no está en CD
		 */
		public void actionPerformed(ActionEvent arg0) {
			heroeCoolDown.stop();
		}
	}

	/**
	 * Moverá a los enemigos cada vez que se llame
	 */
	class ListenerEnemigos implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			for (int i = 0; i < enemigos.size(); i++) { // Por cada enemigo que
														// hay
				if (enemigos.get(i).actuar(getHeroe().getPosicion())) { // Comprueba
																		// que
																		// les
																		// hace
																		// daño
					if (getHeroe().getEscudo()) { // Si tiene escudo les hacen
													// menos daño
						getHeroe().setHp(enemigos.get(i).getDamage());
					} else
						getHeroe().setHp(enemigos.get(i).getDamage());
					if (getHeroe().getHp() <= 0) { // Si se muere el héroe se
													// paran
						movEnemigo.stop();
						heroeLife.stop();
					}
				}
			}
			panelHeroe.actualizar();
		}
	}
}
