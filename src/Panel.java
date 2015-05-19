
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
	Image imVida = (new ImageIcon("img/objeto.gif")).getImage();
	Image imMuerte = (new ImageIcon("img/muerte.gif")).getImage();
	Image imEspada = (new ImageIcon("img/espada.gif")).getImage();
	Image imEscudo = (new ImageIcon("img/escudo.gif")).getImage();
	JLabel picVida = new JLabel(new ImageIcon(imVida));
	JLabel picMuerte = new JLabel(new ImageIcon(imMuerte));
	JLabel vida = new JLabel("100");
	
	Panel(Heroe heroe){
		this.heroe = heroe;
		this.setBackground(Color.LIGHT_GRAY);
		this.add(picVida);
		this.add(vida);
//		//this.add(textoEspacio);

	}
	
	public void actualizar(){
		if(heroe.getHp() <= 0){
			vida.setText("RIP");
			this.remove(picVida);
			this.add(picMuerte);
		}		
		else
			vida.setText(Integer.toString(heroe.getHp()));

	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawString("Objetos: ", 10, 17);
		if (heroe.getKey()) {
			g.drawImage(imLlave, 90, -7, this);
		}
		if (heroe.getSword()) {
			g.drawImage(imEspada, 60, 2, this);
		}
		if (heroe.getShield()) {
			g.drawImage(imEscudo, 82, 3, this);
		}
		
		
	}
}
