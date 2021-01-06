package control.commands;

import exceptions.CommandParseException;

public class CommandGenerator {
	public static final String unknownCommandMsg = "Unknown command";
	private static Command[] availableCommands = {
			new AddCommand(),
			new HelpCommand(),
			new ResetCommand(),
			new ExitCommand(),
			new UpdateCommand(),
			new GarlicPushCommand(),
			new LightFlashCommand(),
			new AddBloodBankCommand(),
			new SuperCoinsCommand(),
			new AddVampireCommand(),
			new SaveCommand(),
			new SerializeCommand()
	};
	
	public static Command parseCommand(String[] commandWords) throws CommandParseException {
		Command c = null;
		for (Command i: availableCommands) {
			c = i.parse(commandWords);
			if (c != null)
				break;
		}
		if (c == null)
			throw new CommandParseException("[ERROR]: " + unknownCommandMsg);
		return c;
	}
	
	public static String commandHelp() {
		String auxi = "Available commands:\n";
		for (Command i: availableCommands)
			auxi += i.helpText();
		return auxi;
	}
}