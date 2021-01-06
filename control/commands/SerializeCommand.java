package control.commands;

import exceptions.CommandParseException;
import model.Game;

public class SerializeCommand extends Command {
	private static final String name = "serialize";
	private static final String shortcut = "z";
	private static final String details = "Seriali[z]e";
	private static final String help = "Serializes the board.";
	
	public SerializeCommand() {
		super(name, shortcut, details, help);
	}
	
	public boolean execute(Game game) {
		System.out.println(game.serializeCommand());
		return false;
	}
	
	public Command parse(String[] commandWords) throws CommandParseException {
		return parseNoParamsCommand(commandWords);
	}
	
}