package control.commands;

import model.Game;

public class HelpCommand extends Command {

	public HelpCommand() {
		super("help", "h", "[h]elp", "show this help");
	}

	public boolean execute(Game game) {
		System.out.println(CommandGenerator.commandHelp());
		return false;
	}

	public Command parse(String[] commandWords) {
		return parseNoParamsCommand(commandWords);
	}

}
