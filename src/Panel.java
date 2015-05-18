
import javax.swing.JLabel;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class Panel extends JPanel {
	Heroe heroe;
	final static JLabel TEXT = new JLabel("HP:" );
	JLabel Vida = new JLabel("100");
	Panel(Heroe heroe){
		this.heroe = heroe;
		this.add(TEXT);
		this.add(Vida);
	}
}
