import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Casilla extends JPanel implements Componentes {
	private Estado estado;
	private Orientacion orientacion;
	private boolean ocupado;	
	private int danio = 0;
	Casilla() {
		setEstado(Estado.Vacia);
		setOrientacion(Orientacion.Sur);
	}
	
	Casilla(Estado estado) {
		setEstado(estado);
		setOrientacion(Orientacion.Sur);
	}

	/*
	 * Getters & Setters
	 */
	
	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Orientacion getOrientacion() {
		return orientacion;
	}

	public void setOrientacion(Orientacion orientacion) {
		this.orientacion = orientacion;
	}
	
	public boolean isOcupado() {
		return ocupado;
	}

	public void setOcupado(boolean ocupado) {
		this.ocupado = ocupado;
	}

	/**
	 * M��todo para dibujar el estado de la casilla
	 */
	public void dibujarEstado(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, getWidth(), getHeight());
		switch(estado) {
			case Vacia:
				setOcupado(false);
				break;
			case Obstaculo:
				setOcupado(true);
				g.setColor(Color.DARK_GRAY);
				g.fillRect(0, 0, getWidth(), getHeight());
				break;
			case Heroe:
				setOcupado(true);
				g.setColor(Color.RED);
				switch(orientacion){
					case Norte:
						g.drawString("N", 5, 20);
						break;
					case Este:
						g.drawString("E", 5, 20);
						break;
					case Sur:
						g.drawString("S", 5, 20);
						break;
					case Oeste:
						g.drawString("O", 5, 20);
						break;
					default:
						System.err.println("Orientacion desconocida");
				}
				break;
			case Enemigo:
				setOcupado(true);
				g.setColor(Color.BLUE);
				switch(orientacion){
					case Norte:
						g.drawString("N", 5, 20);
						break;
					case Este:
						g.drawString("E", 5, 20);
						break;
					case Sur:
						g.drawString("S", 5, 20);
						break;
					case Oeste:
						g.drawString("O", 5, 20);
						break;
					default:
						System.err.println("Orientacion desconocida");
				}
				break;
			case Puerta:
				setOcupado(false);
				g.setColor(Color.YELLOW);
				g.fillRect(0, 0, getWidth(), getHeight());
				break;
			default:
				System.err.println("Error al dibujar casilla.");
				break;
		}
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Font fuente=new Font("Monospaced", Font.BOLD, 25);
        g.setFont(fuente);
		dibujarEstado(g);
	}

	public int getDanio() {
		return danio;
	}

	public void setDanio(int danioa) {
		this.danio = danioa;
		Thread threadaso = new Thread(new Runnable(){
			public void run(){
				if (Thread.currentThread().getName() == "threadaso") {
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						danio = 0;
					}
				}
			}
		}, "threadaso");
		threadaso.start();
	}
}