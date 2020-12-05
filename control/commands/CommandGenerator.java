package control.commands;

public class CommandGenerator {

	private static Command[] availableCommands = {
			new AddCommand(),
			new HelpCommand(),
			new ResetCommand(),
			new ExitCommand(),
			new UpdateCommand(),
			new SuperCoinsCommand(),
			new GarlicPushCommand(),
			new LightFlashCommand(),
			new AddVampireCommand()
	};
	
	public static Command parseCommand(String[] commandWords) {
		Command c = null;
		for (Command i: availableCommands) {
			c = i.parse(commandWords);
			if (c != null)
				break;
		}
		return c;
	}
	
	public static String commandHelp() {
		String auxi = "Available commands:\n";
		for (Command i: availableCommands)
			auxi += i.helpText();
		return auxi;	
	}
}