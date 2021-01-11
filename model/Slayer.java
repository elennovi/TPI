package model;
public class Slayer extends GameObject{
	private static final int HEALTH = 3;
	private static final int HARM = 1;
	private static final String GENERICLETTER = "S";
	private Game game;
	
	public Slayer(int r, int c, Game game) {
		super(r, c, HEALTH, GENERICLETTER);
		this.game = game;
	}
	public Slayer(int r, int c, Game game, int health, String letter) {
		super(r, c, health, letter);
		this.game = game;
	}
	public void attack() {
		if (!isDead())
			for (int i = getCol() + 1; i < game.Cols(); ++i) {
				IAttack other = game.getAttackableInPosition(getRow(), i);
				if (other != null && other.receiveSlayerAttack(HARM))
					break;
			}
	}
	public boolean receiveVampireAttack(int damage) {
		decreaseLife(damage);
		return true;
	}
	public boolean receiveDraculaAttack() {
		setDeadObject();
		return true;
	}
	public void advance() {} // Los slayers no se mueven
}
