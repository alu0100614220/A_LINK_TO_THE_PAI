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
	private Enemigo prueba1 = new Enemigo(10, 10);

	Juego() {
		enemyMoving = new Timer(1000, new Listener());
		setTitle("Roguelike PAI");
		setSize(ANCHO, ALTO);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setTablero(new Tablero("maps/1.map"));
		this.add(getTablero(), BorderLayout.CENTER);
		this.addKeyListener(listener);
		getTablero().setCasilla(getHeroe().getPosicion(), Estado.Heroe,
				Orientacion.Sur);
		getTablero().setCasilla(prueba1.getPosicion(), Estado.Enemigo,
				Orientacion.Sur);
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

	public void accion(Point objetivo) {
		if (prueba1.skill(getHeroe().getPosicion())) {
			
		}
		if (prueba1.getPosicion().x < heroe.getPosicion().x) {
			Point posicion = new Point(prueba1.getPosicion().x + 1,
					prueba1.getPosicion().y);
			if (!getTablero().getCasilla(posicion).isOcupado()) {
				getTablero().setCasilla(prueba1.getPosicion(), Estado.Vacia,
						Orientacion.Este);
				getTablero().setCasilla(posicion, Estado.Enemigo,
						Orientacion.Este);
				prueba1.setPosicion(posicion);
				prueba1.setOrientacion(Orientacion.Este);
			}
		} else if (prueba1.getPosicion().x > heroe.getPosicion().x) {
			Point posicion = new Point(prueba1.getPosicion().x - 1,
					prueba1.getPosicion().y);
			if (!getTablero().getCasilla(posicion).isOcupado()) {
				getTablero().setCasilla(prueba1.getPosicion(), Estado.Vacia,
						Orientacion.Este);
				getTablero().setCasilla(posicion, Estado.Enemigo,
						Orientacion.Oeste);
				prueba1.setPosicion(posicion);
				prueba1.setOrientacion(Orientacion.Oeste);
			}
		} else if (prueba1.getPosicion().y < heroe.getPosicion().y) {
			Point posicion = new Point(prueba1.getPosicion().x,
					prueba1.getPosicion().y + 1);
			if (!getTablero().getCasilla(posicion).isOcupado()) {
				getTablero().setCasilla(prueba1.getPosicion(), Estado.Vacia,
						Orientacion.Este);
				getTablero().setCasilla(posicion, Estado.Enemigo,
						Orientacion.Sur);
				prueba1.setPosicion(posicion);
				prueba1.setOrientacion(Orientacion.Sur);
			}
		} else if (prueba1.getPosicion().y > heroe.getPosicion().y) {
			Point posicion = new Point(prueba1.getPosicion().x,
					prueba1.getPosicion().y - 1);
			if (!getTablero().getCasilla(posicion).isOcupado()) {
				getTablero().setCasilla(prueba1.getPosicion(), Estado.Vacia,
						Orientacion.Este);
				getTablero().setCasilla(posicion, Estado.Enemigo,
						Orientacion.Norte);
				prueba1.setPosicion(posicion);
				prueba1.setOrientacion(Orientacion.Norte);
			}
		}
	}

	class KeyListn implements KeyListener {

		public void keyPressed(KeyEvent e) {
			enemyMoving.start();
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				Point posicion = new Point(getHeroe().getPosicion().x,
						getHeroe().getPosicion().y - 1);
				if (!getTablero().getCasilla(posicion).isOcupado()) {
					getTablero().setCasilla(getHeroe().getPosicion(),
							Estado.Vacia, Orientacion.Sur);
					getTablero().setCasilla(posicion, Estado.Heroe,
							Orientacion.Norte);
					getHeroe().setPosicion(posicion);
					getHeroe().setOrientacion(Orientacion.Norte);
				}
			} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				Point posicion = new Point(getHeroe().getPosicion().x,
						getHeroe().getPosicion().y + 1);
				if (!getTablero().getCasilla(posicion).isOcupado()) {
					getTablero().setCasilla(getHeroe().getPosicion(),
							Estado.Vacia, Orientacion.Sur);
					getTablero().setCasilla(posicion, Estado.Heroe,
							Orientacion.Sur);
					getHeroe().setPosicion(posicion);
					getHeroe().setOrientacion(Orientacion.Sur);
				}
			} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				Point posicion = new Point(getHeroe().getPosicion().x - 1,
						getHeroe().getPosicion().y);
				if (!getTablero().getCasilla(posicion).isOcupado()) {
					getTablero().setCasilla(getHeroe().getPosicion(),
							Estado.Vacia, Orientacion.Sur);
					getTablero().setCasilla(posicion, Estado.Heroe,
							Orientacion.Oeste);
					getHeroe().setPosicion(posicion);
					getHeroe().setOrientacion(Orientacion.Oeste);
				}
			} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				Point posicion = new Point(getHeroe().getPosicion().x + 1,
						getHeroe().getPosicion().y);
				if (!getTablero().getCasilla(posicion).isOcupado()) {
					getTablero().setCasilla(getHeroe().getPosicion(),
							Estado.Vacia, Orientacion.Sur);
					getTablero().setCasilla(posicion, Estado.Heroe,
							Orientacion.Este);
					getHeroe().setPosicion(posicion);
					getHeroe().setOrientacion(Orientacion.Este);
				}
			}

		}

		public void keyReleased(KeyEvent e) {
		}

		public void keyTyped(KeyEvent e) {
		}
	}

	class Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			accion(getHeroe().getPosicion());
		}
	}
}
