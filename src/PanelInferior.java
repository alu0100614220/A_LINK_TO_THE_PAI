import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelInferior extends JPanel {
	Juego juego;// Acceso a heroe
	JButton restart = new JButton("Restart"); //Boton que nos permite resetear el juego
	/**
	 * Declaracion de las imagenes que usaremos
	 */
	Image imFlechas = (new ImageIcon("img/flechas.gif")).getImage();
	Image imgA = (new ImageIcon("img/a.gif")).getImage();
	Image imgP = (new ImageIcon("img/p.gif")).getImage();
	Image imgE = (new ImageIcon("img/e.gif")).getImage();

	/**
	 * Constructor donde colocamos en su sitio los distintos elementos
	 * 
	 */	
	PanelInferior(Juego juego) {
		this.juego = juego;
		this.setBackground(Color.LIGHT_GRAY);
		this.add(new JLabel("Movimiento:"));
		this.add(new JLabel(new ImageIcon(imFlechas)));
		this.add(new JLabel("Atacar:"));
		this.add(new JLabel(new ImageIcon(imgA)));
		this.add(new JLabel("Abrir Cofre:"));
		this.add(new JLabel(new ImageIcon(imgE)));
		this.add(new JLabel("Pausa:"));
		this.add(new JLabel(new ImageIcon(imgP)));
		this.add(restart);
		restart.addActionListener(new Clicker());
		restart.setFocusable(false);
	}
	/**
	 *Listener del boton "restart" que reinicia el juego
	 */
	class Clicker implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == restart) {
				juego.reinicia();			
			}
		}

	}
}
