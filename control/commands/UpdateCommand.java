package control.commands;

import model.Game;

public class UpdateCommand extends Command{
	public UpdateCommand() {
		super("none", "n", "[n]one | []", "update");
	}

	public boolean execute(Game game) {
		game.update();
		return true;
	}

	public Command parse(String[] commandWords) {
		if (matchCommandName(commandWords[0]) || commandWords[0].equals("")) {
			if (commandWords.length > 1)
				System.out.println(unknownCommand);
			else
				return this;
		}
		return null;
	}
}
