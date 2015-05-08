import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Casilla extends JPanel {
	private int estado;
	private int orientacion;

	Casilla() {
		setEstado(Const.ESTADO.Vacia);
		setOrientacion(Const.ORIENTACION.Sur);
		this.setBackground(Color.GREEN);
	}

	/*
	 * Getters & Setters
	 */
	public int getEstado() {
		return estado;
	}

	public void setEstado(Const.ESTADO estado) {
		this.estado = estado.ordinal();
	}

	public int getOrientacion() {
		return orientacion;
	}

	public void setOrientacion(Const.ORIENTACION orientacion) {
		this.orientacion = orientacion.ordinal();
	}

	/**
	 * Método para dibujar el estado de la casilla
	 */
	public void dibujarEstado(Graphics g) {
		if (estado == Const.ESTADO.Vacia.ordinal()) {
		} else if (estado == Const.ESTADO.Obstaculo.ordinal()) {
			g.setColor(Color.DARK_GRAY);
			g.fillRect(0, 0, 10, 10);
		} else if (estado == Const.ESTADO.Heroe.ordinal()) {
			g.setColor(Color.RED);
			System.out.println(orientacion);
			if (orientacion == Const.ORIENTACION.Norte.ordinal())
				g.drawString("N", 10, 10);
			else if (orientacion == Const.ORIENTACION.Este.ordinal())
				g.drawString("E", 10, 10);
			else if (orientacion == Const.ORIENTACION.Sur.ordinal())
				g.drawString("S", 10, 10);
			else if (orientacion == Const.ORIENTACION.Oeste.ordinal())
				g.drawString("O", 10, 10);
		} else if (estado == Const.ESTADO.Enemigo.ordinal()) {
		}
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		dibujarEstado(g);
	}
}