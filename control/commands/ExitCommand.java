package control.commands;

import exceptions.CommandParseException;
import model.Game;

public class ExitCommand extends Command {
	private static final String name = "exit";
	private static final String shortcut = "e";
	private static final String details = "[e]xit";
	private static final String help = "exit game";
	public ExitCommand() {
		super(name, shortcut, details, help);
	}

	public boolean execute(Game game) {
		game.setExit();
		return false;
	}

	public Command parse(String[] commandWords) throws CommandParseException {
		return parseNoParamsCommand(commandWords);
	}
}
