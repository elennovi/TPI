package model;

import java.util.ArrayList;
public class GameObjectList {
	private ArrayList <GameObject> gameObjects;
	
	public GameObjectList(Level level) {
		gameObjects = new ArrayList <GameObject>(); 
	}
	public boolean isInPosition(int x, int y) {
		return getObjectInPosition(x, y) != null;
	}
	public void addObject(GameObject object) { 
		gameObjects.add(object);
	}
	public void advance() {
		for(GameObject o: gameObjects)
			o.advance();
	}
	public void removeDead() {
		int i = 0;
		while(i < gameObjects.size()) {
			if(gameObjects.get(i).isDead()) {
				gameObjects.remove(i);
			}
			else i++;
		}
	}
	public void attack() {
		for (IAttack i: gameObjects)
			i.attack();
	}
	public IAttack getAttackableInPosition(int i, int j) {
		return getObjectInPosition(i, j);
	}
	public String getPositionToString(int i, int j) {
		GameObject o = getObjectInPosition(i, j);
		if (o != null)
			return o.getPositionToString();
		else return "";
	}
	public void pushVampires() {
		for(IAttack o: gameObjects)
			o.receiveGarlicPush();
	}
	public void killAllVampires() {
		for(IAttack o: gameObjects)
			o.receiveLightFlash();
	}
	public String serialize() {
		String listInfo = "Game Object List: \n";
		for(GameObject o: gameObjects)
			listInfo += (o.serialize() + "\n");
		return listInfo;
	}
	
	//FUNCIONES PRIVADAS:
	private GameObject getObjectInPosition(int i, int j) {
		for (GameObject o: gameObjects)
			if (o.isInPosition(i, j))
				return o;
		return null;
	}
}
