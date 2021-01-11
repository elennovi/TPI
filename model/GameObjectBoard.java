package model;
import java.util.Random;

public class GameObjectBoard {
	private GameObjectList gameObjects;
	
	public GameObjectBoard(Level level) { 
		gameObjects = new GameObjectList(level);
		Vampire.initGameObjectBoard();
	}
	public boolean somethingInPosition(int r, int c) { 
		return gameObjects.isInPosition(r, c);
	}
	public void addObject(GameObject o) {
		gameObjects.addObject(o);
	}
	public void update(Random rand) {
		gameObjects.advance(); 
		gameObjects.attack();
		gameObjects.removeDead(); 
	}
	public IAttack getAttackableInPosition(int row, int i) {
		return gameObjects.getAttackableInPosition(row, i);
	}
	public String getPositionToString(int i, int j) {
		return gameObjects.getPositionToString(i, j);
	}
	public void pushVampires() {
		gameObjects.pushVampires();
	}
	public void killAllVampires() {
		gameObjects.killAllVampires();
		gameObjects.removeDead();
	}
	public String serialize() {
		return gameObjects.serialize();
	}
}

