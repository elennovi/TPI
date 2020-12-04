package control.commands;

import model.Game;

public class SuperCoinsCommand extends Command {
	private static final int numCoins = 1000;
	
	public SuperCoinsCommand() {
		super("coins", "c", "[c]oins", "add 1000 coins");
	}

	public boolean execute(Game game) {
		game.addCoins(numCoins);
		game.update();
		return true;
	}

	public Command parse(String[] commandWords) {
		return parseNoParamsCommand(commandWords);
	}

}
