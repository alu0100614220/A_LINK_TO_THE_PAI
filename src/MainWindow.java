import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Clase que contiene el método principal e inicializa el juego.
 * También se encarga de reproducir el audio de fondo. 
 */
public class MainWindow {
	/**
	 * Método que se encarga de la reproducción de la música de fondo.
	 */
	public void playSound() {
		File audioFile = new File("8bitZelda.wav");

		try {
			AudioInputStream audioStream = AudioSystem
					.getAudioInputStream(audioFile);
			AudioFormat format = audioStream.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			try {
				Clip audioClip = (Clip) AudioSystem.getLine(info);
				audioClip.open(audioStream);
				audioClip.loop(Clip.LOOP_CONTINUOUSLY);
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			}

		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método principal que lanza el juego.
	 */
	public static void main(String[] args) {
		Juego juego = new Juego();
		MainWindow ventana = new MainWindow();
		ventana.playSound();
		juego.setVisible(true);

	}
}
