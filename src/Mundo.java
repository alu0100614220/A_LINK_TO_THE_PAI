import java.io.BufferedReader;
import java.io.FileReader;


public class Mundo implements Componentes {
	private int ancho, alto;
	private int posicion;
	
	Mundo(int numMundo) {
		try {
			BufferedReader bf = new BufferedReader(new FileReader("maps/" + numMundo + "/config.map"));
			setAncho(Integer.parseInt(bf.readLine()));
			setAlto(Integer.parseInt(bf.readLine()));
			setPosicion(Integer.parseInt(bf.readLine()));
			bf.close();
		}
		catch(Exception e) {
			System.err.println("Error de lectura en mapa");
		}
	}
	
	public void cambioMapa(Orientacion orientacion) {
		switch (orientacion) {
		case Norte:
			posicion -= ancho;
			break;
		case Sur:
			posicion += ancho;
			break;
		case Este:
			posicion += 1;
			break;
		case Oeste:
			posicion -= 1;
			break;
		default:
			System.err.println("Orientacion inválida.");
			break;
		}
	}

	public int getAlto() {
		return alto;
	}

	public void setAlto(int alto) {
		this.alto = alto;
	}

	public int getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public int getPosicion() {
		return posicion;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}
}
