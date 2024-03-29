package model;

public class Vampire extends GameObject{
	private Game game;
	private int moveCycle;
	private static final int INITCYCLE = 1;
	private static boolean wins = false;
	private static boolean lose = false;
	private static int numVampires = 0;
	private static int deadVampires = 0;
	private static final int HEALTH = 5;
	private static final int HARM = 1;
	private static final String GENERICLETTER = "V";
	
	public Vampire(int r, int c, Game game) {
		super(r, c, HEALTH, GENERICLETTER);
		this.game = game;
		initVampire();
	} // Es el constructor gen�rico de vampiro (V[vida])
	
	public Vampire(int r, int c, Game game, String letter) { 
		super(r, c, HEALTH, letter);
		this.game = game;
		initVampire();
	} // Es el constructor que utilizaremos para los tipos de vampiros	
	public void advance() { 
		--moveCycle;
		if (canAdvance()) {
			decreaseCol(); 
			moveCycle = INITCYCLE;
		}
		vampireWins();
	}
	public static void initGameObjectBoard() {
		setNumVampires();
		setNumDead();
	}
	public static boolean Wins() {
		return wins;
	}
	public static boolean Lose() {
		return lose;
	}
	public static void setLose() {
		lose = true;
	}
	public static int getNumVampires() {
		return numVampires;
	}
	public static int getDeadVampires() {
		return deadVampires;
	}
	public void countVampires() {
		--numVampires;
		++deadVampires;
	}
	public void attack() {
		if (!isDead()) {
			IAttack other = game.getAttackableInPosition(getRow(), getCol() - 1);
			if (other != null)
				other.receiveVampireAttack(HARM);
		}
	}
	public boolean receiveSlayerAttack(int damage) {
		if (!isDead()) {
			decreaseLife(damage);
			deadVampire();
		}
		return true;
	}
	public boolean receiveGarlicPush() {
		// si hay algo en esa posici�n no puede retroceder
		if (isGoingToMove())
			++moveCycle;
		if (nothingNextTo())
			increaseCol();
		if (outOfBounds()) // si no est� en el plano
			setDeadObject(); // lo ponemos la vida a 0 porque no est� en el tablero
		deadVampire();
		return true;
	}
	public boolean receiveLightFlash() {
		setDeadObject();
		countVampires();
		return true;
	}
	public String serialize() {
		return super.serialize() + ";" + moveCycle;
	}
	
	// FUNCIONES PRIVADAS:
	private void initVampire() {
		moveCycle = INITCYCLE;
		++numVampires;
	}
	private boolean outOfBounds() {
		return !game.inPlane(getRow(), getCol());
	}
	private boolean nothingNextTo() {
		return !game.somethingInPosition(getRow(), getCol() + 1);
	}
	private void deadVampire() {
		if (isDead())
			countVampires();
	}
	private boolean canAdvance() {
		return !game.somethingInPosition(getRow(), getCol() - 1) && canAdvanceCycle();
	}
	private void vampireWins() {
		if (hasArrived())
			Vampire.wins = true;
	}
	private boolean isGoingToMove() {
		return moveCycle == 0 || canAdvanceCycle();
	}
	private boolean canAdvanceCycle() {
		return moveCycle < 0;
	}
	private boolean hasArrived() {
		return getCol() == -1;
	}
	private static void setNumVampires() {
		numVampires = 0;
	}
	private static void setNumDead() {
		deadVampires = 0;
	}
}