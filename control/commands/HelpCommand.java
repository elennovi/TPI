package control.commands;

import model.Game;

public class HelpCommand extends Command {
	private static final String name = "help";
	private static final String shortcut = "h";
	private static final String details = "[h]elp";
	private static final String help = "show this help";
	
	public HelpCommand() {
		super(name, shortcut, details, help);
	}

	public boolean execute(Game game) {
		System.out.println(CommandGenerator.commandHelp());
		return false;
	}

	public Command parse(String[] commandWords) {
		return parseNoParamsCommand(commandWords);
	}

}
