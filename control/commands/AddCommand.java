package control.commands;

import model.Game;

public class AddCommand extends Command{
	private static int numArgsCommandAddSlayer = 3;
	private int row;
	private int col;
	private static final String name = "add";
	private static final String shortcut = "a";
	private static final String details = "[a]dd <x> <y>";
	private static final String help = "add a slayer in position x, y";
	
	public AddCommand() {
		super(name, shortcut, details, help);
	}
	
	public boolean execute(Game game) {
		return game.addSlayerCommand(row, col);
	}

	public Command parse(String[] commandWords) {
		if (matchCommandName(commandWords[0])) {
			if (commandWords.length == numArgsCommandAddSlayer) {
				col = Integer.parseInt(commandWords[1]);
				row = Integer.parseInt(commandWords[2]);
				return this;
			}
		}
		return null;
	}
}
