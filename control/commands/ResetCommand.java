package control.commands;

import exceptions.CommandParseException;
import model.Game;

public class ResetCommand extends Command {
	public ResetCommand() {
		super("reset", "r", "[r]eset", "reset game");
	}
	public boolean execute(Game game) {
		game.initGame();
		return true; // el reset printea el juego de nuevo
	}
	public Command parse(String[] commandWords) throws CommandParseException {
		return parseNoParamsCommand(commandWords);
	}
}
