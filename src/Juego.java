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
	private Enemigo enemigo = new Enemigo(10, 10);
	private Panel panel;
	private int mapaActual = 1;
	

	Juego() {
		enemyMoving = new Timer(500, new Listener());
		heroeLife = new Timer(50, new Listener());
		panel = new Panel(getHeroe());
		setTitle("Roguelike PAI");
		setSize(ANCHO, ALTO);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setTablero(new Tablero("maps/1.map"));
		this.add(getTablero(), BorderLayout.CENTER);
		this.add(panel, BorderLayout.NORTH);
		this.addKeyListener(listener);
		getTablero().setCasilla(getHeroe().getPosicion(), Estado.Heroe,
				Orientacion.Sur);
		getTablero().setCasilla(enemigo.getPosicion(), Estado.Enemigo,
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
	
	public ArrayList<Enemigo> getEnemigos() {
		return enemigos;
	}

	public void setEnemigos(ArrayList<Enemigo> enemigos) {
		this.enemigos = enemigos;
	}
	
	public int getMapaActual() {
		return mapaActual;
	}

	public void setMapaActual(int mapaActual) {
		this.mapaActual = mapaActual;
	}

	public void comprobarCambio() {
		Point posicion = getHeroe().getPosicion();
		if(posicion.x == 0) {
			cambioMapa(Orientacion.Oeste);
		}
		else if (posicion.y == 0) {
			cambioMapa(Orientacion.Norte);
		}
		else if (posicion.x == getTablero().getAncho()) {
			cambioMapa(Orientacion.Este);
		}
		else if (posicion.y == getTablero().getAlto()) {
			cambioMapa(Orientacion.Sur);
		}
	}

	private void cambioMapa(Orientacion orientacion) {
		mapaActual++;
		switch (orientacion) {
		case Norte:
			getTablero().cambiarTablero("maps/" + mapaActual + "N" + ".map");
			getHeroe().setPosicion(new Point(getHeroe().getPosicion().x, tablero.getAlto() - 1));
			//repaint();
			break;
		case Sur:
			getTablero().cambiarTablero("maps/" + mapaActual + "S" + ".map");
			getHeroe().setPosicion(new Point(getHeroe().getPosicion().x, 1));			
			break;
		case Este:
			getTablero().cambiarTablero("maps/" + mapaActual + "E" + ".map");
			getHeroe().setPosicion(new Point(1, getHeroe().getPosicion().y));			
			break;
		case Oeste:
			getTablero().cambiarTablero("maps/" + mapaActual + "O" + ".map");
			getHeroe().setPosicion(new Point(getTablero().getAncho() - 1, getHeroe().getPosicion().y));			
			break;

		default:
			System.err.println("Orientacion inválida.");
			break;
		}
	}

	/**
	 * Método que define el movimiento del enemigo
	 */
	public void accion(Point objetivo) {
		if (enemigo.skill(getHeroe().getPosicion())) {
			getHeroe().setHp(-30);
			if (getHeroe().getHp() <= 0) {
				panel.Vida.setText("RIP");
				heroeLife.stop();
				enemyMoving.stop();
			} else {
				panel.Vida.setText(Integer.toString(getHeroe().getHp()));
			}
		}
		if (enemigo.getPosicion().x < heroe.getPosicion().x) {
			Point posicion = new Point(enemigo.getPosicion().x + 1,
					enemigo.getPosicion().y);
			if (!getTablero().getCasilla(posicion).isOcupado()) {
				getTablero().setCasilla(enemigo.getPosicion(), Estado.Vacia,
						Orientacion.Este);
				getTablero().setCasilla(posicion, Estado.Enemigo,
						Orientacion.Este);
				enemigo.setPosicion(posicion);
				enemigo.setOrientacion(Orientacion.Este);
			}
		} else if (enemigo.getPosicion().x > heroe.getPosicion().x) {
			Point posicion = new Point(enemigo.getPosicion().x - 1,
					enemigo.getPosicion().y);
			if (!getTablero().getCasilla(posicion).isOcupado()) {
				getTablero().setCasilla(enemigo.getPosicion(), Estado.Vacia,
						Orientacion.Este);
				getTablero().setCasilla(posicion, Estado.Enemigo,
						Orientacion.Oeste);
				enemigo.setPosicion(posicion);
				enemigo.setOrientacion(Orientacion.Oeste);
			}
		} else if (enemigo.getPosicion().y < heroe.getPosicion().y) {
			Point posicion = new Point(enemigo.getPosicion().x,
					enemigo.getPosicion().y + 1);
			if (!getTablero().getCasilla(posicion).isOcupado()) {
				getTablero().setCasilla(enemigo.getPosicion(), Estado.Vacia,
						Orientacion.Este);
				getTablero().setCasilla(posicion, Estado.Enemigo,
						Orientacion.Sur);
				enemigo.setPosicion(posicion);
				enemigo.setOrientacion(Orientacion.Sur);
			}
		} else if (enemigo.getPosicion().y > heroe.getPosicion().y) {
			Point posicion = new Point(enemigo.getPosicion().x,
					enemigo.getPosicion().y - 1);
			if (!getTablero().getCasilla(posicion).isOcupado()) {
				getTablero().setCasilla(enemigo.getPosicion(), Estado.Vacia,
						Orientacion.Este);
				getTablero().setCasilla(posicion, Estado.Enemigo,
						Orientacion.Norte);
				enemigo.setPosicion(posicion);
				enemigo.setOrientacion(Orientacion.Norte);
			}
		}
	}

	class KeyListn implements KeyListener {

		public void keyPressed(KeyEvent e) {
			enemyMoving.start();
			if (getHeroe().getHp() >= 0) {
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
				comprobarCambio();
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
