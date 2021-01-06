package control.commands;

import exceptions.CommandExecuteException;
import exceptions.CommandParseException;
import model.Game;

public class GarlicPushCommand extends Command {
	private static final String name = "garlic";
	private static final String shortcut = "g";
	private static final String details = "[g]arlic ";
	private static final String help = "pushes back vampires";
	private static final String failedMsg = "[ERROR]: Failed to garlic push";
	
	public GarlicPushCommand() {
		super(name, shortcut, details, help);
	}

	public boolean execute(Game game) throws CommandExecuteException {
		try {
			game.garlicPushCommand();
			return true;
		}
		catch(CommandExecuteException ex) {
			System.out.println(ex.getMessage());
			throw new CommandExecuteException(failedMsg);
		}
	}

	public Command parse(String[] commandWords) throws CommandParseException {
		return parseNoParamsCommand(commandWords);
	}
	
}
