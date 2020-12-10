package model;

public class ExplosiveVampire extends Vampire {
	private Game game;
	private static final int HARM = 1;
	private static final String EXPLOSIVELETTER = "EV";

	public ExplosiveVampire(int r, int c, Game game) {
		super(r, c, game, EXPLOSIVELETTER);
		this.game = game;
	}
	public void dramaticDeath() {
		for (int i = getCol() - 1; i < getCol() + 2; ++i)
			for (int j = getRow() - 1; j < getRow() + 2; ++j) {
				if (i != getCol() || j != getRow()) {
					IAttack other = game.getAttackableInPosition(j, i);
					if (other != null)
						other.receiveSlayerAttack(HARM);
				}
			}
	}
	public boolean receiveSlayerAttack(int damage) {
		super.receiveSlayerAttack(damage);
		if (isDead())
			dramaticDeath();
		return true;
	}
}
