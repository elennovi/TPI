package control.commands;

import exceptions.CommandExecuteException;
import exceptions.CommandParseException;
import model.Game;

public class LightFlashCommand extends Command {
	private static final String name = "light";
	private static final String shortcut = "l";
	private static final String details = "[l]ight";
	private static final String help = "kills all the vampires";
	private static final String failedMsg = "[ERROR]: Failed to light flash";
	
	public LightFlashCommand() {
		super(name, shortcut, details, help);
	}
	public boolean execute(Game game) throws CommandExecuteException {
		try {
			game.lightFlashCommand();
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
