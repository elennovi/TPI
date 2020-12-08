package model;

import java.util.ArrayList;

public class GameObjectList {
	private ArrayList <GameObject> gameObjects;
	
	public GameObjectList(Level level) {
		gameObjects = new ArrayList <GameObject>(); // creamos una lista con objetos
	}
	public boolean isInPosition(int x, int y) { // Función que comprueba si 
		// un slayer de la lista ocupa una determinada posición dada
		for (GameObject o: gameObjects)
			if (o.isInPosition(x, y))
				return true;
		return false;
	}
	public void addObject(GameObject object) { 
		gameObjects.add(object);
	}
	public void vampiresWins() {
		for(GameObject o: gameObjects)
			if (o.vampiresWins())
				break;
	}
	public void advance() {
		for(GameObject o: gameObjects)
			o.advance(); // se le dice a todos los objetos
		// que se muevan
	}
	public void removeDead() {
		int i = 0;
		while(i < gameObjects.size()) {
			if(gameObjects.get(i).isDead()) {
				gameObjects.get(i).deleteObjects(); // contabilizamos los muertos
				gameObjects.remove(i); // lo eliminamos de la lista 
			}
			else
				i++;
		}
	}
	public void attack() {
		for (GameObject i: gameObjects)
			i.attack();
	}
	public IAttack getAttackableInPosition(int row, int i) {
		for (GameObject object: gameObjects) 
			if (object.isInPosition(row, i))
				return object;
		return null;
	}
	public String getPositionToString(int i, int j) {
		for (GameObject o: gameObjects)
			if (o.isInPosition(i, j))
				return o.getPositionToString(i, j);
		return "";
	}
	public void pushVampires() {
		for(IAttack o: gameObjects)
			o.receiveGarlicPush();
	}
	public void killAllVampires() {
		for(IAttack o: gameObjects)
			o.receiveLightFlash();
	}
}
