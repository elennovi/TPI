package control.commands;

import model.Dracula;
import model.Game;

public class AddVampireCommand extends Command {
	private static int numArgsCommandAddVampi = 3;
	private static int numArgsCommandAddSpecialVampi = 4;
	private int row;
	private int col;
	private String type;
	private int numArgs;
	
	public AddVampireCommand() {
		super("vampire", "v", "[v]ampire [<type>] <x> <y>. Type = {\"\"|\"D\"|\"E\"}", "add a vampire in position x, y");
	}

	public boolean execute(Game game) {
		if(game.inPlane(row, col) && !game.somethingInPosition(row, col)) {
			if(game.remainingVampires()) {
				if(numArgs == numArgsCommandAddVampi) {
					game.addVampire(row, col);
					return true;
				}
				else if(numArgs == numArgsCommandAddSpecialVampi) {
					if (game.addSpecialVampire(row, col, type))
						return true;
					else
						System.out.println(draculaIsAlive);
				}
			}
			else
				System.out.println(noMoreVampiresLeft);
		}
		else
			System.out.println(invalidPosition);	
		return false;
	}

	public Command parse(String[] commandWords) {
		if(matchCommandName(commandWords[0])) {
			numArgs = commandWords.length;
			if(commandWords.length == numArgsCommandAddVampi) {
				col = Integer.parseInt(commandWords[1]);
				row = Integer.parseInt(commandWords[2]);
				return this;
			}
			else if(commandWords.length == numArgsCommandAddSpecialVampi) {
				type = commandWords[1];
				col = Integer.parseInt(commandWords[2]);
				row = Integer.parseInt(commandWords[3]);
				return this;
			}
		}
		return null;
	}
}
