package control.commands;
import exceptions.CommandExecuteException;
import exceptions.CommandParseException;
import exceptions.DraculaIsAliveException;
import exceptions.InvalidPositionException;
import exceptions.NoMoreVampiresException;
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
	private static final String failedMsg = "[ERROR]: Failed to add this vampire";
	
	public AddVampireCommand() {
		super(name, shortcut, details, help);
	}

	public boolean execute(Game game) throws CommandExecuteException {
		try {
			game.addVampireCommand(type, row, col);
			return true;
		}
		catch (DraculaIsAliveException ex) {
			System.out.println(ex.getMessage());
			throw new CommandExecuteException(failedMsg);
		}
		catch (InvalidPositionException ex) {
			System.out.println(ex.getMessage());
			throw new CommandExecuteException(failedMsg +  " ");
		}
		catch (NoMoreVampiresException ex){
			System.out.println(ex.getMessage());
			throw new CommandExecuteException(failedMsg);
		}
	}

	public Command parse(String[] commandWords) throws CommandParseException {
		if(matchCommandName(commandWords[0])) {
			if(commandWords.length == numArgsCommandAddVampi) {
				type = "";
				try {
					col = Integer.parseInt(commandWords[1]);
					row = Integer.parseInt(commandWords[2]);
					return this;
				}
				catch (NumberFormatException ex) {
					throw new CommandParseException("[ERROR]: " + invalidArgMsg + " for add " + name + " command, number expected: " + details);
				}
			}
			else if(commandWords.length == numArgsCommandAddSpecialVampi) {
				type = commandWords[1];
				if (type.equals("e") || type.equals("d")) {
					try {
						col = Integer.parseInt(commandWords[2]);
						row = Integer.parseInt(commandWords[3]);
						return this;
					}
					catch (NumberFormatException ex) {
						throw new CommandParseException("[ERROR]: " + invalidArgMsg + " for add " + name + " command, number expected: " + details);
					}
				}
				else
					throw new CommandParseException("[ERROR]: " + invalidTypeMsg + ": " + details);
			}
		}
		return null;
	}
}
