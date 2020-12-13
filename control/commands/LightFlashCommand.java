package control.commands;

import model.Game;

public class LightFlashCommand extends Command {
	private static final String name = "light";
	private static final String shortcut = "l";
	private static final String details = "[l]ight";
	private static final String help = "kills all the vampires";
	
	public LightFlashCommand() {
		super(name, shortcut, details, help);
	}
	public boolean execute(Game game) {
		return game.lightFlashCommand();
	}

	public Command parse(String[] commandWords) {
		return parseNoParamsCommand(commandWords);
	}

}
