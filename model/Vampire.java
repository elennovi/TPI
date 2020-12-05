package model;

public class Vampire extends GameObject{
	private Game game;
	private boolean cyclePair; // Indica si el ciclo de aparición del vampiro
	// es par o impar, de esta forma podremos saber cuándo se mueven
	private static boolean wins = false;
	private static boolean loose = false;
	private static int numVampires = 0;
	private static int deadVampires = 0;
	public static final int HEALTH = 5;
	public static final int HARM = 1;
	public static final String LETTER = "V";
	
	public Vampire(int r, int c, Game game) { // Inicializa losa atributos
		super(r, c, HEALTH);
		cyclePair = game.isAPairCycle(); // Comprobamos cual es la paridadd del ciclo
		this.game = game;
		++numVampires;
		// aparición del vampiro
	}
	public void advance() { // Un vampiro avanza solo cuando no tiene ningún
		// slayer ni ningún otro vampiro delante y además el ciclo le permite
		// avanzar 
		if (!game.somethingInPosition(getRow(), getCol() - 1) && canAdvanceCycle())
			decreaseCol();
		else if (game.somethingInPosition(getRow(), getCol() - 1))
			changeCycle();// si tiene algo delante entonces cambiaremos su
		// ciclo
	}
	public void changeCycle() {
		cyclePair = !game.isAPairCycle();
	}
	public boolean canAdvanceCycle() { // Puede avanzar si la paridad de ciclo
		// de creación del vampiro es igual que la creación de la paridad del
		// ciclo en el que se encuentra ell juego
		return cyclePair == game.isAPairCycle();
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
	public boolean receiveSlayerAttack(int damage) { // MODIFICAR EL DAMAGE
		decreaseLife(damage);
		return true;
	}
	public String getPositionToString(int x, int y) {
		return LETTER + " [" + getHealth() + "]";
	}
	public void someoneWins() {
		if (hasArrived())
			Vampire.wins = true;
	}
	public boolean receiveGarlicPush() {
		increaseCol(); // se le añade una posicion a las columnas
		changeCycle(); // le cambiamos el ciclo de movimiento
		if (!game.inPlane(getRow(), getCol())) // si no está en el plano
			setDeadObject(); // lo ponemos la vida a 0 porque no está en el tablero
		return true;
	}
	public boolean receiveLightFlash() {
		setDeadObject();
		return true;
	}
}