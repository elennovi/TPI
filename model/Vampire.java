package model;

public class Vampire extends GameObject{
	private Game game;
	private int moveCycle;
	private static final int INITCYCLE = 1;
	private static boolean wins = false;
	private static boolean loose = false;
	private static int numVampires = 0;
	private static int deadVampires = 0;
	private static final int HEALTH = 5;
	private static final int HARM = 1;
	private static final String GENERICLETTER = "V";
	
	public Vampire(int r, int c, Game game) {
		super(r, c, HEALTH, GENERICLETTER);
		moveCycle = INITCYCLE;
		this.game = game;
		++numVampires;
	} // Es el constructor genérico de vampiro (V[vida])
	
	public Vampire(int r, int c, Game game, String letter) { 
		super(r, c, HEALTH, letter);
		moveCycle = INITCYCLE;
		this.game = game;
		++numVampires;
	} // Es el constructor que utilizaremos para los tipos de vampiros
	
	public void advance() { 
		--moveCycle;
		if (!game.somethingInPosition(getRow(), getCol() - 1) && canAdvanceCycle()) {
			decreaseCol(); 
			moveCycle = INITCYCLE;
		}
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
	public static boolean Wins() {
		return wins;
	}
	public static boolean Loose() {
		return loose;
	}
	public static void setLoose() {
		loose = true;
	}
	public static int getNumVampires() {
		return numVampires;
	}
	public static int getDeadVampires() {
		return deadVampires;
	}
	public static void setNumVampires() {
		numVampires = 0;
	}
	public static void setNumDead() {
		deadVampires = 0;
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
			if (isDead())
				countVampires();
		}
		return true;
	}
	public boolean receiveGarlicPush() {
		// si hay algo en esa posición no puede retroceder
		if (isGoingToMove())
			++moveCycle;
		if (!game.somethingInPosition(getRow(), getCol() + 1))
			increaseCol();
		if (!game.inPlane(getRow(), getCol())) { // si no está en el plano
			setDeadObject(); // lo ponemos la vida a 0 porque no está en el tablero
		}
		if (isDead())
			countVampires();
		return true;
	}
	public boolean receiveLightFlash() {
		setDeadObject();
		countVampires();
		return true;
	}
	public String serialize() {
		return super.commonInfo() + ";" + moveCycle;
	}
}