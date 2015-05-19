import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelInferior extends JPanel {
	Juego juego;
	JButton restart = new JButton("Restart");
	Image imFlechas = (new ImageIcon("img/flechas.gif")).getImage();
	
	PanelInferior(Juego juego) {
		this.juego = juego;
		
		this.add(restart);
		this.add(new JLabel("Movimiento:"));
		this.add(new JLabel(new ImageIcon(imFlechas)));
		this.add(new JLabel("Atacar:"));
		this.add(new JLabel("Pausa:"));
		restart.addActionListener(new Clicker());
		restart.setFocusable(false);
	}

	class Clicker implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == restart) {
				System.out.println("HAYOLA");
			}
		}

	}
}
