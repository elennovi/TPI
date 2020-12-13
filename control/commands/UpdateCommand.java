package control.commands;

import model.Game;

public class UpdateCommand extends Command{
	private static final String name = "none";
	private static final String shortcut = "n";
	private static final String details = "[n]one | []";
	private static final String help = "update";
	
	public UpdateCommand() {
		super(name, shortcut, details, help);
	}

	public boolean execute(Game game) {
		game.update();
		return true;
	}

	public Command parse(String[] commandWords) {
		if (matchCommandName(commandWords[0]) || commandWords[0].equals("")) {
			if (commandWords.length <= 1)
				return this;
		}
		return null;
	}
}
