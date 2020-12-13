package control.commands;
import model.Game;

public class AddVampireCommand extends Command {
	private int row;
	private int col;
	private String type;
	private static int numArgsCommandAddVampi = 3;
	private static int numArgsCommandAddSpecialVampi = 4;
	private static final String name = "vampire";
	private static final String shortcut = "v";
	private static final String details = "[v]ampire [<type>] <x> <y>. Type = {\"\"|\"D\"|\"E\"}";
	private static final String help = "add a vampire in position x, y";
	
	
	public AddVampireCommand() {
		super(name, shortcut, details, help);
	}

	public boolean execute(Game game) {
		return game.addVampireCommand(type, row, col);
	}

	public Command parse(String[] commandWords) {
		if(matchCommandName(commandWords[0])) {
			if(commandWords.length == numArgsCommandAddVampi) {
				type = "";
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
