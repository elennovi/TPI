package control.commands;

import model.Game;

public class GarlicPushCommand extends Command {
	public static final int cost = 10;
	
	public GarlicPushCommand() {
		super("garlic", "g", "[g]arlic", "push all vampires one position");
	}

	public boolean execute(Game game) {
		if (game.haveEnoughMoney(cost)) {
			game.pushVampires();
			game.update();
			return true;
		}
		else
			System.out.println(notEnoughCoins);
		return false;
	}

	public Command parse(String[] commandWords) {
		return parseNoParamsCommand(commandWords);
	}
	
}
