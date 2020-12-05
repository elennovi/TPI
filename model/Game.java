package model;
import java.util.Random;
import view.GamePrinter;
import view.IPrintable;

public class Game implements IPrintable{
	private Random rand;
	private Level level;
	private Player player;
	private GamePrinter printer;
	private GameObjectBoard objects;
	private int cycles;
	private static final int numCoinsPerCicle = 10;
	private static boolean Exit;
	
	public Game(long seed, Level level) {
		// genera un random que le pasa al resto de objetos del juego
		rand = new Random(seed);
		this.level = level;
		printer = new GamePrinter(this, level.getCols(), level.getRows());
		initGame();
	}
	public void initGame() {
		cycles = 0;
		player = new Player();
		objects = new GameObjectBoard(level);
		Exit = false;
	}
	public void addCycle() {
		++cycles;
	}
	public boolean isFinished() { // El juego acaba cuando los vampiros
		// mueren o cuando han llegado a la meta
		return Vampire.Wins() || Vampire.Loose() || Exit;
	}
	public int Rows() {
		return level.getRows();
	}
	public int Cols() {
		return level.getCols();
	}
	public int getNumVampires() {
		return level.getNumberOfVampires();
	}
	public String toString() {
		return printer.toString();
	}
	public String stringRemainingVampires() { // Los vampiros que quedan
		// por salir en forma string
		return Integer.toString(level.getNumberOfVampires() - (Vampire.getNumVampires() + Vampire.getDeadVampires()));
	}
	public String stringVampiresOnBoard() { // Devuelve los vampiros
		// que hay en la lista de vampiros 
		return Integer.toString(Vampire.getNumVampires());
	}
	public String getInfo() {
		return "Number of cycles: " + cycles + "\n" + 
				"Coins: " + player.getCoins() + "\n" + 
				"Remaining vampires: " + stringRemainingVampires() + "\n" + 
				"Vampires on the board: " + stringVampiresOnBoard();
	}
	public void addSlayer(int row, int col, int cost) { // Se añade un slayer y se
		// le restan las monedas
			objects.addObject(new Slayer(row, col, this));
			decreasePlayerCoins(cost);
	}
	public void addVampireRandom() { 
		if(remainingVampires() && vampireFrequency()) {
			int randRow = rand.nextInt(level.getRows());
			int randCol = level.getCols() - 1;
			if(!objects.somethingInPosition(randRow, randCol))
				addVampire(randRow, randCol);
		}
	}
	public void addVampire(int row, int col) {
		objects.addObject(new Vampire(row, col, this));
	}
	public void addSpecialVampire(int row, int col, String type) {
		switch(type) {
		case "D":
			//objects.addObject(new Dracula(row, col, this));
			break;
		case "E":
			objects.addObject(new ExplosiveVampire(row, col, this));
			break;
		}
	}
	public boolean remainingVampires() {
		return (Vampire.getNumVampires() + Vampire.getDeadVampires() < level.getNumberOfVampires());
	}
	public boolean vampireFrequency() {
		return rand.nextDouble() < level.getVampireFrequency();
	}
	public void update() {
		if(rand.nextFloat() > 0.5) // se añaden monedas
			player.addCoins(numCoinsPerCicle); // Se añaden los coins
		objects.update(rand); // Se actualiza el tablero
		addVampireRandom();
		checkAnyWinner();
		if (!isFinished())
			addCycle();
	}
	public boolean canAddSlayer() { // Se puede
		// añadir un slayer si tiene al menos 50 monedas
		return (player.getCoins() >= Slayer.getCostSlayer());
	}
	public String getWinnerMessage() {
		if(Vampire.Wins()) // Si ganan los vampiros
			return "Vampires win!";
		else if(Vampire.Loose()) // Si ganan los slayers (o pierden los vampiros)
			return "Player wins";
		return "Nobody wins...";
	}
	public boolean isAPairCycle() { // Devuelve un valor indicando si
		// el ciclo en el que se encuentra el  juego es par o impar
		return cycles % 2 == 0;
	}
	public boolean somethingInPosition(int r,int c) { // Devuelve si hay
		// algún objeto en esa posición
		return objects.somethingInPosition(r, c);
	}
	public void checkAnyWinner() {
		objects.someoneWins();
	}
	public boolean inPlane(int x, int y) {
    	return x >= 0 && x < Rows() && y >= 0 && y < Cols();
    }
	public void setExit() {
		Exit = true;
	}
	public int getCycles() {
		return cycles;
	}
	public IAttack getAttackableInPosition(int row, int i) {
		return objects.getAttackableInPosition(row, i);
	}
	public String getPositionToString(int i, int j) {
		return objects.getPositionToString(i, j);
	}
	public void addCoins(int numCoins) {
		player.addCoins(numCoins);
	}
	public void pushVampires() {
		objects.pushVampires();
	}
	public boolean isInLastCol(int col) {
		return level.getCols() - 1 == col;
	}
	public boolean haveEnoughMoney(int coins) {
		return player.getCoins() >= coins; 
	}
	public void killAllVampires() {
		objects.killAllVampires();
	}
	public void decreasePlayerCoins(int cost) {
		player.decreaseCoins(cost);
	}
}