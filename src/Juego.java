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
		setSize(Constantes.WIDTH, Constantes.HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setTablero(new Tablero());
		this.add(getTablero(), BorderLayout.CENTER);
		this.addKeyListener(listener);
		getTablero().setCasilla(getHeroe().getPosicion(),
				Constantes.estado.Heroe.ordinal(),
				Constantes.orientacion.Sur.ordinal());
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
					Constantes.estado.Vacia.ordinal(), 0);
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				Point posicion = new Point(getHeroe().getPosicion().x,
						getHeroe().getPosicion().y - 1);
				getTablero().setCasilla(posicion,
						Constantes.estado.Heroe.ordinal(),
						Constantes.orientacion.Norte.ordinal());
				getHeroe().setPosicion(posicion);
				getHeroe().setOrientacion(Constantes.orientacion.Norte.ordinal());
			} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				Point posicion = new Point(getHeroe().getPosicion().x,
						getHeroe().getPosicion().y + 1);
				getTablero().setCasilla(posicion,
						Constantes.estado.Heroe.ordinal(),
						Constantes.orientacion.Sur.ordinal());
				getHeroe().setPosicion(posicion);
				getHeroe().setOrientacion(Constantes.orientacion.Sur.ordinal());
			} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				Point posicion = new Point(getHeroe().getPosicion().x - 1,
						getHeroe().getPosicion().y);
				getTablero().setCasilla(posicion,
						Constantes.estado.Heroe.ordinal(),
						Constantes.orientacion.Oeste.ordinal());
				getHeroe().setPosicion(posicion);
				getHeroe().setOrientacion(Constantes.orientacion.Oeste.ordinal());
			} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				Point posicion = new Point(getHeroe().getPosicion().x + 1,
						getHeroe().getPosicion().y);
				getTablero().setCasilla(posicion,
						Constantes.estado.Heroe.ordinal(),
						Constantes.orientacion.Este.ordinal());
				getHeroe().setPosicion(posicion);
				getHeroe().setOrientacion(Constantes.orientacion.Este.ordinal());
			}

		}

		public void keyReleased(KeyEvent e) {
		}

		public void keyTyped(KeyEvent e) {
		}
	}
}
