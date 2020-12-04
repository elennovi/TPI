package model;
import java.util.Random;

public class Player {
	private int coins; // Representan las monedas que tiene el jugador
	private Random rand; // Creamos un Random para que todos los rand que haya en el juego
	// sean los mismos

	public Player(Random rand) { // El constructor del jugador, crea un jugador y se le dan
		this.rand = rand; // valor a los atributos
		coins = 50;
	}
	public int getCoins() { 
		return coins;
	}
	public void addCoins() { // Genera un número aleatorio y comprueba si
		if(rand.nextFloat() > 0.5) // se añaden monedas
			coins += 10; 
	}
	public void slayerBought() { // Si el jugador canjea monedas por un slayer
		coins -= 50; // le restamos las monedas que cuesta
	}
}