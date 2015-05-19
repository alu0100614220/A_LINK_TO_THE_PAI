import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Panel extends JPanel {
	Heroe heroe; // Acceso a heroe
	/**
	 * Declaracion de las imagenes que usaremos
	 */
	Image imLlave = (new ImageIcon("img/llave.gif")).getImage();
	Image imVida = (new ImageIcon("img/vida.gif")).getImage();
	Image imVidaMas = (new ImageIcon("img/corazon.gif")).getImage();
	Image imMuerte = (new ImageIcon("img/muerte.gif")).getImage();
	Image imEspada = (new ImageIcon("img/espada.gif")).getImage();
	Image imEscudo = (new ImageIcon("img/escudo.gif")).getImage();
	Image imPause = (new ImageIcon("img/pause.gif")).getImage();
	/*
	 * Añadiendo las imagenes a JLabels junto con el texto de la vida del heroe
	 */
	JLabel picLlave = new JLabel(new ImageIcon(imLlave));
	JLabel picEspada = new JLabel(new ImageIcon(imEspada));
	JLabel picEscudo= new JLabel(new ImageIcon(imEscudo));
	JLabel picVida = new JLabel(new ImageIcon(imVida));
	JLabel picMuerte = new JLabel(new ImageIcon(imMuerte));
	JLabel picVidaMas = new JLabel(new ImageIcon(imVidaMas));
	JLabel picPause= new JLabel(new ImageIcon(imPause));	
	JLabel vida = new JLabel("100");
	
	Boolean estado = false;// Estado para poner el juego en PAUSE

	/**
	 * Constructor donde colocamos en su sitio los distintos elementos
	 * 
	 */	
	Panel(Heroe heroe) {
		setLayout(new GridLayout(1,3));
		JPanel izq = new JPanel();
		JPanel cent = new JPanel();
		JPanel der = new JPanel();
		this.heroe = heroe;
		izq.setBackground(Color.LIGHT_GRAY);
		cent.setBackground(Color.LIGHT_GRAY);
		der.setBackground(Color.LIGHT_GRAY);
		izq.add(picLlave);
		izq.add(picEspada);
		izq.add(picEscudo);
		picLlave.setVisible(false);
		picEspada.setVisible(false);
		picEscudo.setVisible(false);
		cent.add(picVidaMas);
		picVidaMas.setVisible(false);
		cent.add(picVida);
		cent.add(vida);
		cent.add(picMuerte);
		picMuerte.setVisible(false);
		der.add(picPause);
		picPause.setVisible(false);
		this.add(izq);
		this.add(cent);
		this.add(der);
	}
	/**
	 * Método que comprueba y muestra/oculta los objetos del heroe o
	 * sus puntos de vida o si muere. 
	 */
	public void actualizar() {
		if(heroe.getLlave())
			picLlave.setVisible(true);
		else
			picLlave.setVisible(false);
		
		if(heroe.getEspada())
			picEspada.setVisible(true);
		else
			picEspada.setVisible(false);
		
		if(heroe.getEscudo())
			picEscudo.setVisible(true);
		else
			picEscudo.setVisible(false);
		
		if (heroe.getCorazon()) {
			picVida.setVisible(false);
			picVidaMas.setVisible(true);
		}
		else {
			picVida.setVisible(true);
			picVidaMas.setVisible(false);
		}
		if (heroe.getHp() <= 0) {
			vida.setText("RIP");
			picVida.setVisible(false);
			picVidaMas.setVisible(false);
			picMuerte.setVisible(true);
		} else
			vida.setText(Integer.toString(heroe.getHp()));
		if(estado) {
			picPause.setVisible(true);
		}
		else
			picPause.setVisible(false);

	}
	/**
	 * Metodo usado para saber cuando mostrar la imagen PAUSE
	 */
	public void pintaPause(boolean estado) {
		this.estado = estado;
		actualizar();
	}
}
