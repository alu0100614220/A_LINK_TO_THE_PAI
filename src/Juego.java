import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Juego extends JFrame implements Componentes {
	private Tablero tablero; // Tablero del juego
	private Heroe heroe = new Heroe(5, 5); // Jugador
	private ArrayList<Enemigo> enemigos;
	private KeyListn listener = new KeyListn();
	public Timer enemyMoving;
	public Timer heroeLife;
	public Timer heroeCoolDown;
	public Timer animacion;
	private boolean stop = false;
	private Panel panel;
	private int mundoActual = 1;
	private Mundo mundo = new Mundo(mundoActual);
	private PanelInferior panelSur = new PanelInferior(this);
	Orientacion antigua = Orientacion.Sur;

	Juego() {
		// Definimos los timer
		enemyMoving = new Timer(500, new Listener()); // Controla los
														// movimientos del
														// enemigo
		heroeLife = new Timer(50, new Listener()); // Un controlador para saber
													// si el heroe se movio
		heroeCoolDown = new Timer(100, new CDListener());// EL CoolDown del
															// ataque del heroe
		animacion = new Timer(500, new accion());// Controlar la animacion de
													// ataque
		panel = new Panel(getHeroe()); // Panel con los datos del heroe superior
		panelSur = new PanelInferior(this); // Panel con inferior
		setTitle("Roguelike PAI");
		setSize(ANCHO, ALTO);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setTablero(new Tablero(mundoActual, mundo.getPosicion())); // El juego
																	// tiene que
																	// aceder al
																	// tablero
		this.add(getTablero(), BorderLayout.CENTER);
		this.add(panel, BorderLayout.NORTH);
		this.add(panelSur, BorderLayout.SOUTH);
		this.addKeyListener(listener);
		// Establecemos en el tablero la posicion del heroe
		getTablero().setCasilla(getHeroe().getPosicion(), Estado.Heroe,
				Orientacion.Sur);
		setEnemigos();

	}

	/**
	 * Llamaremos al metodo cada vez que pulsemos restart y nos "reiniciara" la
	 * partida sin crearlo todo de 0
	 */
	public void reinicia() {

		// Paramos los timers
		enemyMoving.stop();
		heroeLife.stop();
		heroeCoolDown.stop();
		animacion.stop();

		// Volvemos al mapa 1
		getTablero().cambiarTablero("maps/" + getMundoActual() + "/1.map");
		this.setMundoActual(1);
		this.mundo.setPosicion(1);

		// Volvemos a crear de 0 al Heroe en la posicion 5,5
		setHeroe(new Heroe(5, 5));
		// Ocultamos el panel y lo reiniciamos
		panel.setVisible(false);
		panel = new Panel(getHeroe());
		this.add(panel, BorderLayout.NORTH);
		// Añadimos el heroe al tablero
		getTablero().setCasilla(getHeroe().getPosicion(), Estado.Heroe,
				Orientacion.Sur);
		setEnemigos();
	}

	/**
	 * Crea un array de enemigos para hacerlo mas sencillo
	 */
	private void setEnemigos() {
		enemigos = new ArrayList<Enemigo>();
		for (int i = 0; i < getTablero().getEnemigos().size(); i++) {
			enemigos.add(new Enemigo(getTablero().getEnemigos().get(i),
					getTablero()));
		}
	}

	/**
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
	private void cambiarMundo(Point posicionNueva, Orientacion aux) {
		enemigos.clear();
		mundo.cambioMapa(aux);
		getHeroe().setPosicion(posicionNueva);
		getTablero().cambiarTablero(
				"maps/" + mundoActual + "/" + mundo.getPosicion() + ".map");
		getTablero().setCasilla(getHeroe().getPosicion(), Estado.Heroe, aux);
		setEnemigos();
	}

	/**
	 * Comprobara a que direccion se ira dependiendo de la orientacion
	 */
	public void comprobarCambio() {
		Point posicion = getHeroe().getPosicion();
		Point posicionNueva;
		if (posicion.x == 0) {
			posicionNueva = new Point(getTablero().getAncho() - 2, getHeroe()
					.getPosicion().y);
			cambiarMundo(posicionNueva, Orientacion.Oeste);
		} else if (posicion.y == 0) {
			posicionNueva = new Point(getHeroe().getPosicion().x,
					tablero.getAlto() - 2);
			cambiarMundo(posicionNueva, Orientacion.Norte);
		} else if (posicion.x == getTablero().getAncho() - 1) {
			posicionNueva = new Point(1, getHeroe().getPosicion().y);
			cambiarMundo(posicionNueva, Orientacion.Este);
		} else if (posicion.y == getTablero().getAlto() - 1) {
			posicionNueva = new Point(getHeroe().getPosicion().x, 1);
			cambiarMundo(posicionNueva, Orientacion.Sur);
		}
	}

	/**
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

	private void moverse(Point posicion, Orientacion aux) {
		getTablero().setCasilla(getHeroe().getPosicion(), Estado.Vacia, aux);
		getTablero().setCasilla(posicion, Estado.Heroe, aux);
		getHeroe().setPosicion(posicion);
		getHeroe().setOrientacion(aux);
	}

	private void girarse(Orientacion aux) {
		getTablero().setCasilla(getHeroe().getPosicion(), Estado.Heroe, aux);
		getHeroe().setOrientacion(aux);
	}

	class KeyListn implements KeyListener {

		public void keyPressed(KeyEvent e) {
			enemyMoving.start();
			panel.repaint();
			if (e.getKeyCode() == KeyEvent.VK_P) {
				if (stop == false) {
					stop = true;
					enemyMoving.stop();
				} else if (stop == true) {
					stop = false;
					enemyMoving.start();
				}
				panel.pintaPause(stop);

			} else {
				panel.pintaPause(false);
			}
			if (getHeroe().getHp() > 0) {
				if (e.getKeyCode() == KeyEvent.VK_E
						&& !heroeCoolDown.isRunning()) {
					Point punto = new Point(getHeroe().getPosicion());
					switch (getHeroe().getOrientacion()) {
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
					if (getTablero().getCasilla(punto).getEstado() == Estado.Cofre
							&& getHeroe().getLlave()) {
						getTablero().cambiarTablero(
								"maps/" + mundoActual + "/25.map");
						Point ending = new Point(11, 6);
						getTablero().setCasilla(ending, Estado.Heroe,
								Orientacion.Sur);
						getHeroe().setPosicion(ending);
						getHeroe().setOrientacion(Orientacion.Norte);
						enemigos.clear();
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_A
						&& !heroeCoolDown.isRunning()) {
					antigua = getHeroe().getOrientacion();
					switch (heroe.getOrientacion()) {
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
					animacion.start();
					for (int i = 0; i < enemigos.size(); i++) {

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
				if (e.getKeyCode() == KeyEvent.VK_UP
						&& !heroeCoolDown.isRunning()) {
					Point posicion = new Point(getHeroe().getPosicion().x,
							getHeroe().getPosicion().y - 1);
					if (condicionParaMoverse(posicion)) {
						moverse(posicion, Orientacion.Norte);
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

		public void keyReleased(KeyEvent e) {
		}

		public void keyTyped(KeyEvent e) {
		}
	}

	class accion implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			getTablero().setCasilla(getHeroe().getPosicion(), Estado.Heroe,
					antigua);
			animacion.stop();
		}

	}

	class CDListener implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			heroeCoolDown.stop();

		}

	}

	class Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			for (int i = 0; i < enemigos.size(); i++) {
				if (enemigos.get(i).actuar(getHeroe().getPosicion())) {
					if (getHeroe().getEscudo()) {
						getHeroe().setHp(enemigos.get(i).getDamage() + 20);
					} else
						getHeroe().setHp(enemigos.get(i).getDamage());
					if (getHeroe().getHp() <= 0) {
						enemyMoving.stop();
						heroeLife.stop();
					}
				}
			}
			panel.actualizar();
		}
	}
}
