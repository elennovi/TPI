package control.commands;

import model.Game;

public class LightFlashCommand extends Command {
	public static final int COST = 50;

	public LightFlashCommand() {
		super("light", "l", "[l]ight", "kills all the vampires");
}

	public boolean execute(Game game) {
		if(game.haveEnoughMoney(COST)) {
			game.killAllVampires();
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
