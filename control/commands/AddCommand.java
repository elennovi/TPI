package control.commands;

import exceptions.CommandExecuteException;
import exceptions.CommandParseException;
import model.Game;

public class AddCommand extends Command{
	private static int numArgsCommandAddSlayer = 3;
	private int row;
	private int col;
	private static final String name = "add";
	private static final String shortcut = "a";
	private static final String details = "[a]dd <x> <y>";
	private static final String help = "add a slayer in position x, y";
	private static final String failedMsg = "[ERROR]: Failed to add slayer";
	
	public AddCommand() {
		super(name, shortcut, details, help);
	}
	
	public boolean execute(Game game) throws CommandExecuteException {
		try {
			game.addSlayerCommand(row, col);
			return true;
		}
		catch (CommandExecuteException ex) {
			System.out.println(ex.getMessage());
			throw new CommandExecuteException(failedMsg);
		}
	}

	public Command parse(String[] commandWords) throws CommandParseException {
		if (matchCommandName(commandWords[0])) {
			if (commandWords.length == numArgsCommandAddSlayer) {
				try {
					col = Integer.parseInt(commandWords[1]);
					row = Integer.parseInt(commandWords[2]);
					return this;
				}
				catch (NumberFormatException ex) {
					throw new CommandParseException("[ERROR]: " + invalidArgMsg + " for " + name + " slayer command, number expected: " + details);
				}
			}
			else
				throw new CommandParseException("[ERROR]: " +  incorrectNumberOfArgsMsg + " for " + name + " command: " + details);
		}
		return null;
	}
}
