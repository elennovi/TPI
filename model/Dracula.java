package model;

public class Dracula extends Vampire{
	private static final String DRACULALETTER = "D";
	private Game game;
	private static boolean alive = false;
	public Dracula(int r, int c, Game game) {
		super(r, c, game, DRACULALETTER);
		this.game = game;
		initDracula();
	}
	public void attack() {
		if (!isDead()) {
			IAttack other = game.getAttackableInPosition(getRow(), getCol() - 1);
			if (other != null)
				other.receiveDraculaAttack();
		}
	}
	public static boolean isAlive() {
		return alive;
	}
	public boolean receiveGarlicPush() {
		super.receiveGarlicPush();
		deadDracula();
		return true;
	}
	public boolean receiveSlayerAttack(int damage) {
		super.receiveSlayerAttack(damage);
		deadDracula();
		return true;
	}
	public static void setDeadDracula() {
		alive = false;
	}
	public boolean receiveLightFlash() {return false;}
	
	//FUNCIONES PRIVADAS:
	private void initDracula() {
		alive = true;
	}
	private void deadDracula() {
		if (isDead())
			setDeadDracula();
	}
}
