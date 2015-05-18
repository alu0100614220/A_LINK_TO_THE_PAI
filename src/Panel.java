
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
	JLabel picLlave = new JLabel(new ImageIcon(imLlave));
	JLabel picVida = new JLabel(new ImageIcon(imVida));
	JLabel vida = new JLabel("100");
	JLabel llave = new JLabel("No");
	
	Panel(Heroe heroe){
		this.heroe = heroe;
		this.add(picVida);
		this.add(vida);
		this.add(textoEspacio);
		this.add(picLlave);
		this.add(llave);
	}
	
	public void actualizar(){
		if(heroe.getHp() <= 0)
			vida.setText("RIP");
		else
			vida.setText(Integer.toString(heroe.getHp()));
		if(heroe.getKey())
			llave.setText("Sí");
	}
}
