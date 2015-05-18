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

	private Panel panel;
	private int mundoActual = 1;
	private Mundo mundo = new Mundo(mundoActual);

	Juego() {
		enemyMoving = new Timer(500, new Listener());
		heroeLife = new Timer(50, new Listener());
		heroeCoolDown = new Timer(1000, new CDListener());
		panel = new Panel(getHeroe());
		setTitle("Roguelike PAI");
		setSize(ANCHO, ALTO);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setTablero(new Tablero(mundoActual, mundo.getPosicion()));
		this.add(getTablero(), BorderLayout.CENTER);
		this.add(panel, BorderLayout.NORTH);
		this.addKeyListener(listener);
		getTablero().setCasilla(getHeroe().getPosicion(), Estado.Heroe,
				Orientacion.Sur);

		setEnemigos();
	}

	private void setEnemigos() {
		enemigos = new ArrayList<Enemigo>();
		for (int i = 0; i < getTablero().getEnemigos().size(); i++) {
			enemigos.add(new Enemigo(getTablero().getEnemigos().get(i),
					getTablero(), this));
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

	public void comprobarCambio() {
		Point posicion = getHeroe().getPosicion();
		if (posicion.x == 0) {
			enemigos.clear();
			mundo.cambioMapa(Orientacion.Oeste);
			getHeroe().setPosicion(
					new Point(getTablero().getAncho() - 2, getHeroe()
							.getPosicion().y));
			getTablero().cambiarTablero(
					"maps/" + mundoActual + "/" + mundo.getPosicion() + ".map");
			getTablero().setCasilla(getHeroe().getPosicion(), Estado.Heroe,
					Orientacion.Oeste);
			setEnemigos();
		} else if (posicion.y == 0) {
			enemigos.clear();
			mundo.cambioMapa(Orientacion.Norte);
			getHeroe()
					.setPosicion(
							new Point(getHeroe().getPosicion().x, tablero
									.getAlto() - 2));
			getTablero().cambiarTablero(
					"maps/" + mundoActual + "/" + mundo.getPosicion() + ".map");
			getTablero().setCasilla(getHeroe().getPosicion(), Estado.Heroe,
					Orientacion.Norte);
			setEnemigos();
		} else if (posicion.x == getTablero().getAncho() - 1) {
			enemigos.clear();
			mundo.cambioMapa(Orientacion.Este);
			getHeroe().setPosicion(new Point(1, getHeroe().getPosicion().y));
			getTablero().cambiarTablero(
					"maps/" + mundoActual + "/" + mundo.getPosicion() + ".map");
			getTablero().setCasilla(getHeroe().getPosicion(), Estado.Heroe,
					Orientacion.Este);
			setEnemigos();
		} else if (posicion.y == getTablero().getAlto() - 1) {
			enemigos.clear();
			mundo.cambioMapa(Orientacion.Sur);
			getHeroe().setPosicion(new Point(getHeroe().getPosicion().x, 1));
			getTablero().cambiarTablero(
					"maps/" + mundoActual + "/" + mundo.getPosicion() + ".map");
			getTablero().setCasilla(getHeroe().getPosicion(), Estado.Heroe,
					Orientacion.Sur);
			setEnemigos();
		}
	}

	class KeyListn implements KeyListener {

		public void keyPressed(KeyEvent e) {
			enemyMoving.start();
			if (getHeroe().getHp() >= 0) {
				
				if (e.getKeyCode() == KeyEvent.VK_A) {
					if (heroeCoolDown.isRunning() == false) { //Comprueba el cd del ataque
						for (int i = 0; i < enemigos.size(); i++) {
							if(getHeroe().atacar(enemigos.get(i))){
								System.out.println(enemigos.get(i).getHp());
								enemigos.get(i).setHp(getHeroe().getDamage());
								System.out.println(enemigos.get(i).getHp());
							}
							if (enemigos.get(i).getHp()<= 0) {
								getTablero().setCasilla(enemigos.get(i).getPosicion(), Estado.Vacia, Orientacion.Este);
								enemigos.remove(i);
							}
						}
					}else{
					}
					heroeCoolDown.start();
					
				}
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
	class CDListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			heroeCoolDown.stop();
		}
		
	}
	class Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			for (int i = 0; i < enemigos.size(); i++) {
				if (enemigos.get(i).actuar(getHeroe().getPosicion())) {
					getHeroe().setHp(enemigos.get(i).getDamage());
					if (getHeroe().getHp() <= 0) {
						enemyMoving.stop();
						heroeLife.stop();
						panel.Vida.setText("R.I.P");

					}else{
						panel.Vida.setText(Integer.toString(getHeroe().getHp()));

					}
				}

			}

		}
	}
}
