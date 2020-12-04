package model;
import java.util.Random;

public class GameObjectBoard {
	private GameObjectList gameObjects;
	
	public GameObjectBoard(Level level) { 
		gameObjects = new GameObjectList(level);
		Vampire.setNumDead();
		Vampire.setNumVampires();
	}
	public boolean somethingInPosition(int r, int c) { // Devuelve un booleano
		// indicando si hay algún objeto en una posición dada
		return gameObjects.isInPosition(r, c);
	}
	public void addObject(GameObject o) {
		gameObjects.addObject(o);
	}
	public void update(Random rand) { // Se actualiza el tablero
		gameObjects.advance(); // los objetos que tengan que moverse avanzan
		gameObjects.attack();
		gameObjects.removeDead(); // Se eliminan los cadaveres de los slayers a los que
		// han matado los vampiros;
	}
	public IAttack getAttackableInPosition(int row, int i) {
		return gameObjects.getAttackableInPosition(row, i);
	}
	public String getPositionToString(int i, int j) {
		return gameObjects.getPositionToString(i, j);
	}
	public void someoneWins() {
		gameObjects.someoneWins();
	}
}

