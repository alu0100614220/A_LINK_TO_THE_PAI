import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Juego extends JFrame {
	private Tablero tablero; // Tablero del juego
	private Heroe heroe = new Heroe(0, 0); // Jugador
	private KeyListn listener = new KeyListn();

	Juego() {
		setTitle("Roguelike PAI");
		setSize(Const.WIDTH, Const.HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setTablero(new Tablero());
		this.add(getTablero(), BorderLayout.CENTER);
		this.addKeyListener(listener);
		getTablero().setCasilla(getHeroe().getPosicion(),
				Const.ESTADO.Heroe,
				Const.ORIENTACION.Sur);
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

	class KeyListn implements KeyListener {

		public void keyPressed(KeyEvent e) {
			getTablero().setCasilla(getHeroe().getPosicion(),
					Const.ESTADO.Vacia, Const.ORIENTACION.Sur);
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				Point posicion = new Point(getHeroe().getPosicion().x,
						getHeroe().getPosicion().y - 1);
				getTablero().setCasilla(posicion,
						Const.ESTADO.Heroe,
						Const.ORIENTACION.Norte);
				getHeroe().setPosicion(posicion);
				getHeroe().setOrientacion(Const.ORIENTACION.Norte);
			} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				Point posicion = new Point(getHeroe().getPosicion().x,
						getHeroe().getPosicion().y + 1);
				getTablero().setCasilla(posicion,
						Const.ESTADO.Heroe,
						Const.ORIENTACION.Sur);
				getHeroe().setPosicion(posicion);
				getHeroe().setOrientacion(Const.ORIENTACION.Sur);
			} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				Point posicion = new Point(getHeroe().getPosicion().x - 1,
						getHeroe().getPosicion().y);
				getTablero().setCasilla(posicion,
						Const.ESTADO.Heroe,
						Const.ORIENTACION.Oeste);
				getHeroe().setPosicion(posicion);
				getHeroe().setOrientacion(Const.ORIENTACION.Oeste);
			} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				Point posicion = new Point(getHeroe().getPosicion().x + 1,
						getHeroe().getPosicion().y);
				getTablero().setCasilla(posicion,
						Const.ESTADO.Heroe,
						Const.ORIENTACION.Este);
				getHeroe().setPosicion(posicion);
				getHeroe().setOrientacion(Const.ORIENTACION.Este);
			}

		}

		public void keyReleased(KeyEvent e) {
		}

		public void keyTyped(KeyEvent e) {
		}
	}
}
