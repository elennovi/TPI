package control.commands;

import model.Game;

public class SuperCoinsCommand extends Command {
	private static final int NUM_SUPER_COINS = 1000;
	private static final String name = "coins";
	private static final String shortcut = "c";
	private static final String details = "[c]oins";
	private static final String help = "add 1000 coins";
	
	public SuperCoinsCommand() {
		super(name, shortcut, details, help);
	}

	public boolean execute(Game game) {
		game.addCoins(NUM_SUPER_COINS);
		return true;
	}

	public Command parse(String[] commandWords) {
		return parseNoParamsCommand(commandWords);
	}

}
