package control.commands;

import model.Game;

public class GarlicPushCommand extends Command {
	public static final int COST = 10;
	
	public GarlicPushCommand() {
		super("garlic", "g", "[g]arlic ", "pushes back vampires");
	}

	public boolean execute(Game game) {
		if (game.haveEnoughMoney(COST)) {
			game.pushVampires();
			game.decreasePlayerCoins(COST);
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
