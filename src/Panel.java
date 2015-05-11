
import javax.swing.JLabel;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class Panel extends JPanel {
	Heroe heroe;
	JLabel Vida = new JLabel("Vida: 100");
	Panel(Heroe heroe){
		this.heroe = heroe;
		this.add(Vida);
	}
}
