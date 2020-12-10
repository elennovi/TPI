package control.commands;

import model.Game;

public class AddCommand extends Command{
	private static int numArgsCommandAddSlayer = 3;
	private int row;
	private int col;
	public static final int COST = 50;
	
	public AddCommand() {
		super("add", "a", "[a]dd <x> <y>", "add a slayer in position x, y");
	}
	
	public boolean execute(Game game) {
		if(!game.inPlane(row, col) || game.isInLastCol(col) || game.somethingInPosition(row, col))
			// Comprobamos que no hay nada en la posición en la que se va a añadir
			// el slayer y que la posición se encuentra dentro del plano
			System.out.println(invalidPosition);
		else {
			// check if the player has enough coins
			if(game.haveEnoughMoney(COST)) {
				game.addSlayer(row, col, COST);
				game.update();
				return true;
			}
			else 
				System.out.println(notEnoughCoins);
		}
		return false;
	}

	public Command parse(String[] commandWords) {
		if (matchCommandName(commandWords[0])) {
			if (commandWords.length == numArgsCommandAddSlayer) {
				col = Integer.parseInt(commandWords[1]);
				row = Integer.parseInt(commandWords[2]);
				return this;
			}
		}
		return null;
	}
}
