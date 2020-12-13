package control.commands;

import model.Game;

public class AddBloodBankCommand extends Command {
	private int cost;
	private int row;
	private int col;
	private static final int numArgsCommandAddBloodBank = 4;
	private static final String name = "bank";
	private static final String shortcut = "b";
	private static final String details = "[b]ank <x> <y> <z>";
	private static final String help = "add a blood bank with cost z in position x, y.";
	
	public AddBloodBankCommand() {
		super(name, shortcut, details, help);
	}

	public boolean execute(Game game) {
		return game.addBloodBankCommand(row, col, cost);
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
