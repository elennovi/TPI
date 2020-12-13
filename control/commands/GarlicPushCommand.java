package control.commands;

import model.Game;

public class GarlicPushCommand extends Command {
	private static final String name = "garlic";
	private static final String shortcut = "g";
	private static final String details = "[g]arlic ";
	private static final String help = "pushes back vampires";
	
	public GarlicPushCommand() {
		super(name, shortcut, details, help);
	}

	public boolean execute(Game game) {
		return game.garlicPushCommand();
	}

	public Command parse(String[] commandWords) {
		return parseNoParamsCommand(commandWords);
	}
	
}
