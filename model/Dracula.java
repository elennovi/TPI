package model;

public class Dracula extends Vampire{
	private static final String DRACULALETTER = "D";
	private Game game;
	private static boolean alive = false;
	public Dracula(int r, int c, Game game) {
		super(r, c, game, DRACULALETTER);
		this.game = game;
		alive = true;
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
	
	public boolean receiveLightFlash() {
		return false;
	}
	public void deleteObjects() {
		alive = false;
		super.deleteObjects();
	}

}
