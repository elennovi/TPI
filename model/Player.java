package model;

public class Player {
	private int coins; // Representan las monedas que tiene el jugador
	private static final int iniCoins = 50;
	public Player() { 
		coins = iniCoins;
	}
	public int getCoins() { 
		return coins;
	}
	public void addCoins(int numCoins) { // Genera un número aleatorio y comprueba si
		coins += numCoins; 
	}
	public void slayerBought() { // Si el jugador canjea monedas por un slayer
		coins -= 50; // le restamos las monedas que cuesta
	}
}