package model;

public class ExplosiveVampire extends Vampire {
	private Game game;
	public static int HEALTH = 5;
	public static final String LETTER = "EV";

	public ExplosiveVampire(int r, int c, Game game) {
		super(r, c, game);
		this.game = game;
	}
}
