package model;

public class BloodBank extends Slayer {
	private static final String BLOODBANKLETTER = "B";
	private static final double TENPERCENT = 0.1;
	private Game game;
	
	public BloodBank(int r, int c, Game game, int health) {
		super(r, c, game, health, BLOODBANKLETTER);
		this.game = game;
	}
	public boolean receiveVampireAttack(int damage) {
		setDeadObject();
		return true;
	}
	public void advance() {
		game.addCoins((int) (getHealth() * TENPERCENT));
	}
	public void attack() {}
}
