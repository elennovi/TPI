package control.commands;

import exceptions.CommandExecuteException;
import exceptions.CommandParseException;
import model.Game;

public class AddBloodBankCommand extends Command {
	private int cost;
	private int row;
	private int col;
	private static final int numArgsCommandAddBloodBank = 4;
	private static final String name = "bank";
	private static final String shortcut = "b";
	private static final String details = "[b]ank <x> <y> <z>";
	private static final String help = "add a blood bank with cost z in position x, y";
	private static final String failedMsg = "[ERROR]: Failed to add bank";
	
	public AddBloodBankCommand() {
		super(name, shortcut, details, help);
	}

	public boolean execute(Game game) throws CommandExecuteException{
		try {
			game.addBloodBankCommand(row, col, cost);
			return true;
		}
		catch (CommandExecuteException ex) {
			System.out.println(ex.getMessage());
			throw new CommandExecuteException(failedMsg);
		}
	}

	public Command parse(String[] commandWords) throws CommandParseException{
		if (matchCommandName(commandWords[0])) {
			if (commandWords.length == numArgsCommandAddBloodBank) {
				try {
					col = Integer.parseInt(commandWords[1]);
					row = Integer.parseInt(commandWords[2]);
					cost = Integer.parseInt(commandWords[3]);
					return this;
				}
				catch(NumberFormatException ex) {
					throw new CommandParseException("[ERROR]: " + invalidArgMsg + " for " + name + " command, number expected: " + details);
				}
			}
		}
		return null;
	}

}
