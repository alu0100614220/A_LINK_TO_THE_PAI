import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class Juego extends JFrame {
	private Tablero tableraso;
	private Heroe heroeaso = new Heroe(0, 0);
	private KeyListn listener = new KeyListn();

	Juego() {
		setTitle("RogueLikePAI");
		setSize(900,900);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setTableraso(new Tablero());
		this.add(getTableraso(),BorderLayout.CENTER);
		setVisible(true);
		this.addKeyListener(listener);
		tableraso.setCasilla(heroeaso.getPosicion(), 1);
	}

	public Tablero getTableraso() {
		return tableraso;
	}

	public void setTableraso(Tablero tableraso) {
		this.tableraso = tableraso;
	}

	class KeyListn implements KeyListener {

		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				System.out.println("youwat");
				Point posicion = new Point(heroeaso.getPosicion().x,
						heroeaso.getPosicion().y - 1);
				tableraso.setCasilla(posicion, 1);
				heroeaso.setPosicion(posicion);
			} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				Point posicion = new Point(heroeaso.getPosicion().x,
						heroeaso.getPosicion().y + 1);
				tableraso.setCasilla(posicion, 1);
				heroeaso.setPosicion(posicion);
			} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				Point posicion = new Point(heroeaso.getPosicion().x - 1,
						heroeaso.getPosicion().y);
				tableraso.setCasilla(posicion, 1);
				heroeaso.setPosicion(posicion);
			} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				Point posicion = new Point(heroeaso.getPosicion().x + 1,
						heroeaso.getPosicion().y);
				tableraso.setCasilla(posicion, 1);

				heroeaso.setPosicion(posicion);
			}

		}

		public void keyReleased(KeyEvent e) {
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub

		}
	}
}
