import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Panel extends JPanel {
	Heroe heroe;
	final static JLabel textoEspacio = new JLabel("   ");
	Image imLlave = (new ImageIcon("img/llave.gif")).getImage();
	Image imVida = (new ImageIcon("img/vida.gif")).getImage();
	Image imVidaMas = (new ImageIcon("img/corazon.gif")).getImage();
	Image imMuerte = (new ImageIcon("img/muerte.gif")).getImage();
	Image imEspada = (new ImageIcon("img/espada.gif")).getImage();
	Image imEscudo = (new ImageIcon("img/escudo.gif")).getImage();
	JLabel picVida = new JLabel(new ImageIcon(imVida));
	JLabel picMuerte = new JLabel(new ImageIcon(imMuerte));
	JLabel picVidaMas = new JLabel(new ImageIcon(imVidaMas));
	JLabel vida = new JLabel("100");
	Boolean estado = false;

	Panel(Heroe heroe) {
		this.heroe = heroe;
		this.setBackground(Color.LIGHT_GRAY);
		this.add(picVidaMas);
		picVidaMas.setVisible(false);
		this.add(picVida);
		this.add(vida);
	}

	public void actualizar() {
		if (heroe.getCorazon()) {
			picVida.setVisible(false);
			picVidaMas.setVisible(true);
		}
		if (heroe.getHp() <= 0) {
			vida.setText("RIP");
			this.remove(picVida);
			this.remove(picVidaMas);
			this.add(picMuerte);
		} else
			vida.setText(Integer.toString(heroe.getHp()));

	}

	public void pintaPause(boolean estado) {
		this.estado = estado;
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawString("Objetos: ", 10, 25);
		if (heroe.getLlave()) {
			g.drawImage(imLlave, 105, 2, this);
		}
		if (heroe.getEspada()) {
			g.drawImage(imEspada, 60, 2, this);
		}
		if (heroe.getEscudo()) {
			g.drawImage(imEscudo, 82, 2, this);
		}
		if (estado == true) {
			g.setColor(Color.CYAN);
			g.fillRect(this.getWidth() - 60, 0, this.getWidth(),
					this.getHeight());
			g.setColor(Color.black);
			g.drawString("Pause.", this.getWidth() - 50, 17);
		} else {
			g.drawString("", this.getWidth() - 50, 17);
		}

	}
}
