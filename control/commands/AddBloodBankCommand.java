package control.commands;

import model.Game;

public class AddBloodBankCommand extends Command {
	private int cost;
	private int row;
	private int col;
	private static final int numArgsCommandAddBloodBank = 4;
	
	public AddBloodBankCommand() {
		super("bank", "b", "[b]ank <x> <y> <z>", "add a blood bank with cost z in position x, y.");
	}

	public boolean execute(Game game) {
		if(game.inPlane(row, col) && !game.somethingInPosition(row, col) && !game.isInLastCol(col)) {
			if (game.haveEnoughMoney(cost)) {
				game.addBloodBank(row, col, cost);
				game.update();
				return true;
			}
			else
				System.out.println(notEnoughCoins);
		}
		else
			System.out.println(invalidPosition);	
		return false;
	}

	public Command parse(String[] commandWords) {
		if (matchCommandName(commandWords[0])) {
			if (commandWords.length == numArgsCommandAddBloodBank) {
				col = Integer.parseInt(commandWords[1]);
				row = Integer.parseInt(commandWords[2]);
				cost = Integer.parseInt(commandWords[3]);
				return this;
			}
		}
		return null;
	}

}
