package model;
import java.util.Random;

import exceptions.CommandExecuteException;
import exceptions.DraculaIsAliveException;
import exceptions.InvalidPositionException;
import exceptions.NoMoreVampiresException;
import exceptions.NotEnoughCoinsException;
import view.GamePrinter;
import view.IPrintable;

public class Game implements IPrintable{
	private Random rand;
	private Level level;
	private Player player;
	private GamePrinter printer;
	private GameObjectBoard objects;
	private int cycles;
	private static boolean Exit;
	private static final int NUM_COINS_PER_CYCLE = 10;
	private static final int COST_ADD_COMMAND = 50;
	private static final int COST_LIGHT_COMMAND = 50;
	private static final int COST_GARLIC_COMMAND = 10;
	private static final String notEnoughCoins= "Not enough coins";
    private static final String invalidPositionMsg = "Unvalid position";
    private static final String noMoreVampiresLeft = "No more remaining vampires left";
    private static final String draculaIsAlive = "Dracula is alive";
    private static final String draculaIsAlreadyAlive = "Dracula is already on board";
	
	public Game(long seed, Level level) {
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
		Dracula.setDeadDracula();
	}
	public void addCycle() {
		++cycles;
	}
	public boolean isFinished() {
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
	public String stringRemainingVampires() {
		return Integer.toString(level.getNumberOfVampires() - (Vampire.getNumVampires() + Vampire.getDeadVampires()));
	}
	public String stringVampiresOnBoard() {
		return Integer.toString(Vampire.getNumVampires());
	}
	public String getInfo() {
		String message = "Number of cycles: " + cycles + "\n" + 
				"Coins: " + player.getCoins() + "\n" + 
				"Remaining vampires: " + stringRemainingVampires() + "\n" + 
				"Vampires on the board: " + stringVampiresOnBoard() + "\n";
		if (Dracula.isAlive())
			message += draculaIsAlive + "\n";
		return message;
	}
	public void addBloodBank(int row, int col, int cost) {
		objects.addObject(new BloodBank(row, col, this, cost));
		decreasePlayerCoins(cost);
	}
	public void addSlayer(int row, int col, int cost) {
			objects.addObject(new Slayer(row, col, this));
			decreasePlayerCoins(cost);
	}
	public void addRandomVampires() { 
		// Vampiro normal
		if (remainingVampires() && vampireFrequency()){
			int randRow = rand.nextInt(level.getRows());
			int randCol = level.getCols() - 1;
			if(!objects.somethingInPosition(randRow, randCol))
				objects.addObject(new Vampire(randRow, randCol, this));
		}
		// Dracula
		if (!Dracula.isAlive() && remainingVampires() && vampireFrequency()) {
			int randRow = rand.nextInt(level.getRows());
			int randCol = level.getCols() - 1;
			if(!objects.somethingInPosition(randRow, randCol))
				objects.addObject(new Dracula(randRow, randCol, this));
		}
		// Vampiro Explosivo
		if (remainingVampires() && vampireFrequency()){
			int randRow = rand.nextInt(level.getRows());
			int randCol = level.getCols() - 1;
			if(!objects.somethingInPosition(randRow, randCol))
				objects.addObject(new ExplosiveVampire(randRow, randCol, this));
		}
	}
	public void addVampire(int row, int col, String type) throws CommandExecuteException {
		switch(type) {
		case "d":
			if (!Dracula.isAlive())
				objects.addObject(new Dracula(row, col, this));
			else {
				throw new DraculaIsAliveException("[ERROR]: " + draculaIsAlreadyAlive);
			}
			break;
		case "e":
			objects.addObject(new ExplosiveVampire(row, col, this));
			break;
		default: 
			objects.addObject(new Vampire(row, col, this));
		}
	}
	public boolean remainingVampires() {
		return (Vampire.getNumVampires() + Vampire.getDeadVampires() < level.getNumberOfVampires());
	}
	public boolean vampireFrequency() {
		return rand.nextDouble() < level.getVampireFrequency();
	}
	public void update() {
		if(rand.nextFloat() > 0.5)
			player.addCoins(NUM_COINS_PER_CYCLE);
		objects.update(rand);
		addRandomVampires(); 
		checkAnyWinner();
		if (!isFinished())
			addCycle();
	}
	public void addVampireCommand(String type, int row, int col) throws CommandExecuteException {
		if(inPlane(row, col) && !somethingInPosition(row, col)) {
            if(remainingVampires())
            	addVampire(row, col, type);
            else throw new NoMoreVampiresException("[ERROR]: " + noMoreVampiresLeft);
        }
        else throw new InvalidPositionException("[ERROR]: Position (" + col + ", " + row + "): " + invalidPositionMsg);
	}
	public void addSlayerCommand(int row, int col) throws CommandExecuteException { 
		if(!inPlane(row, col) || isInLastCol(col) || somethingInPosition(row, col))
			throw new InvalidPositionException("[ERROR]: Position (" + col + ", " + row + "): " + invalidPositionMsg);
		else if(haveEnoughMoney(COST_ADD_COMMAND)) {
				addSlayer(row, col, COST_ADD_COMMAND);
				update();
		}
		else
			throw new NotEnoughCoinsException("[ERROR]: Defender cost is " + COST_ADD_COMMAND + ": " + notEnoughCoins);
	}
	public void lightFlashCommand() throws CommandExecuteException {
		if(haveEnoughMoney(COST_LIGHT_COMMAND)) {
			killAllVampires();
			decreasePlayerCoins(COST_LIGHT_COMMAND);
			update();
		}
		else throw new NotEnoughCoinsException("[ERROR]: Light Flash cost is " + COST_LIGHT_COMMAND + ": " + notEnoughCoins);
	}
	public void addBloodBankCommand(int row, int col, int cost) throws CommandExecuteException {
		if(inPlane(row, col) && !somethingInPosition(row, col) && !isInLastCol(col)) {
			if (haveEnoughMoney(cost)) {
				addBloodBank(row, col, cost);
				update();
			}
			else throw new NotEnoughCoinsException("[ERROR]: Defender cost is " + cost + ": " + notEnoughCoins);
		}
		else throw new InvalidPositionException("[ERROR]: Position (" + col + ", " + row + "): " + invalidPositionMsg);	
	}
	public void garlicPushCommand() throws CommandExecuteException {
		if (haveEnoughMoney(COST_GARLIC_COMMAND)) {
			pushVampires();
			decreasePlayerCoins(COST_GARLIC_COMMAND);
			update();
		}
		else throw new NotEnoughCoinsException("[ERROR]: Garlic Push cost is " + COST_GARLIC_COMMAND + ": " + notEnoughCoins);
	}
	public String serializeCommand() {
		return ("Cycles: " + cycles + "\n" +
				"Coins: " + player.getCoins() + "\n" +
				"Level: " + level.getName().toUpperCase() + "\n" +
				"Remaining Vampires: " + stringRemainingVampires() + "\n" +
				"Vampires on Board: " + stringVampiresOnBoard() + "\n\n" +
				objects.serialize() + "\n");
	}
	public String getWinnerMessage() {
		if(Vampire.Wins()) return "Vampires win!";
		else if(Vampire.Loose()) return "Player wins";
		return "Nobody wins...";
	}
	public boolean somethingInPosition(int r,int c) {
		return objects.somethingInPosition(r, c);
	}
	public void checkAnyWinner() {
		if (Vampire.getDeadVampires() == getNumVampires())
			Vampire.setLoose();
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