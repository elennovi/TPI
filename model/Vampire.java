package model;

public class Vampire extends GameObject{
	private Game game;
	private boolean cyclePair; // Indica si el ciclo de aparición del vampiro
	// es par o impar, de esta forma podremos saber cuándo se mueven
	private int initCycle;
	private static boolean wins = false;
	private static boolean loose = false;
	private static int numVampires = 0;
	private static int deadVampires = 0;
	private static final int HEALTH = 5;
	private static final int HARM = 1;
	private static final String GENERICLETTER = "V";
	
	public Vampire(int r, int c, Game game) { // Inicializa losa atributos
		super(r, c, HEALTH, GENERICLETTER);
		cyclePair = game.isAPairCycle(); // Comprobamos cual es la paridadd del ciclo
		this.game = game;
		++numVampires;
		initCycle = game.getCycles();
	} // Es el constructor genérico de vampiro (V[vida])
	
	public Vampire(int r, int c, Game game, String letter) { 
		super(r, c, HEALTH, letter);
		cyclePair = game.isAPairCycle(); // Comprobamos cual es la paridadd del ciclo
		this.game = game;
		++numVampires;
		initCycle = game.getCycles();
	} // Es el constructor que utilizaremos para los tipos de vampiros
	
	public void advance() { // Un vampiro avanza solo cuando no tiene ningún
		// slayer ni ningún otro vampiro delante y además el ciclo le permite
		// avanzar 
		if (!game.somethingInPosition(getRow(), getCol() - 1) && canAdvanceCycle())
			decreaseCol();
		else if (game.somethingInPosition(getRow(), getCol() - 1) || !cycleDistinctGameCycle())
			changeCycle();// si tiene algo delante entonces cambiaremos su
		// ciclo
	}
	
	public void changeCycle() {
		cyclePair = !cyclePair;
	}
	public boolean canAdvanceCycle() { // Puede avanzar si la paridad de ciclo
		// de creación del vampiro es igual que la creación de la paridad del
		// ciclo en el que se encuentra ell juego
		return cyclePair == game.isAPairCycle() && cycleDistinctGameCycle();
	}
	public boolean cycleDistinctGameCycle() {
		return initCycle != game.getCycles();
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
	public void deleteObjects() {
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
		decreaseLife(damage);
		return true;
	}
	public void someoneWins() {
		if (hasArrived())
			Vampire.wins = true;
	}
	public boolean receiveGarlicPush() {
		increaseCol();
		if (!canAdvanceCycle()) 
			changeCycle();
		if (!game.inPlane(getRow(), getCol())) { // si no está en el plano
			setDeadObject(); // lo ponemos la vida a 0 porque no está en el tablero
		}
		increaseCol();
		return true;
	}
	public boolean receiveLightFlash() {
		setDeadObject();
		return true;
	}
}