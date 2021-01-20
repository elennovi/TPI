package model;

public class BloodBank extends Slayer {
	private static final String BLOODBANKLETTER = "B";
	private static final double TENPERCENT = 0.1;
	private static final int HEALTH = 1;
	private int cost;
	private Game game;
	
	public BloodBank(int r, int c, Game game, int cost) {
		super(r, c, game, HEALTH, BLOODBANKLETTER);
		this.game = game;
		this.cost = cost;
	}
	public boolean receiveVampireAttack(int damage) {
		setDeadObject();
		return true;
	}
	public void advance() {
		game.addCoins((int) (cost * TENPERCENT));
	}
	public String serialize() {
		return super.serialize() + ";" + cost;
	}
	public String getPositionToString() { // no vale para nada
		return BLOODBANKLETTER + " [" + cost + "]";
	}
	public void attack() {}
}
