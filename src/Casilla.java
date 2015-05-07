import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Casilla extends JPanel {
	private int estado;
	private int orientacion;

	Casilla() {
		setEstado(0);
		setOrientacion(0);
		this.setBackground(Color.GREEN);
	}

	/*
	 * Getters & Setters
	 */
	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public int getOrientacion() {
		return orientacion;
	}

	public void setOrientacion(int orientacion) {
		this.orientacion = orientacion;
	}

	/**
	 * Método para dibujar el estado de la casilla
	 */
	public void dibujarEstado(Graphics g) {
		if (estado == Constantes.estado.Vacia.ordinal()) {
		} else if (estado == Constantes.estado.Obstaculo.ordinal()) {
			g.setColor(Color.DARK_GRAY);
			g.fillRect(0, 0, 10, 10);
		} else if (estado == Constantes.estado.Heroe.ordinal()) {
			g.setColor(Color.RED);
			System.out.println(orientacion);
			if (orientacion == Constantes.orientacion.Norte.ordinal())
				g.drawString("N", 10, 10);
			else if (orientacion == Constantes.orientacion.Este.ordinal())
				g.drawString("E", 10, 10);
			else if (orientacion == Constantes.orientacion.Sur.ordinal())
				g.drawString("S", 10, 10);
			else if (orientacion == Constantes.orientacion.Oeste.ordinal())
				g.drawString("O", 10, 10);
		} else if (estado == Constantes.estado.Enemigo.ordinal()) {
		}
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		dibujarEstado(g);
	}
}