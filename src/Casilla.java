import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Casilla extends JPanel {
	private int estado;

	Casilla() {
		setEstado(0);
		this.setBackground(Color.GREEN);
	}

	public int getEstado() {
		return estado;
	}
	public void cambiarColor(Graphics g){
		switch (estado) {
		case 0:
			break;
		case 1:
			g.setColor(Color.RED);
			g.fillOval(0, 0, 10, 10);
			break;
		default:
			break;
		}
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		cambiarColor(g);
	}
}
