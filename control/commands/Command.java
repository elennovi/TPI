package control.commands;

import exceptions.CommandExecuteException;
import exceptions.CommandParseException;
import model.Game;

public abstract class Command {
	  protected static final String incorrectNumberOfArgsMsg = "Incorrect number of arguments";
	  protected static final String invalidArgMsg = "Unvalid argument";
	  protected static final String invalidTypeMsg = "Unvalid type";
	  protected final String name;
	  protected final String shortcut;
	  private final String details; 
	  private final String help;
	  
	  public Command(String name,  String shortcut, String details, String help){    
	    this.name = name;
	    this.shortcut = shortcut;
	    this.details = details;
	    this.help = help;
	  }
	  public abstract boolean execute(Game game) throws CommandExecuteException;
	  
	  public abstract Command parse(String[] commandWords) throws CommandParseException;
	  
	  protected boolean matchCommandName(String name) {
		    return this.shortcut.equalsIgnoreCase(name) || 
		        this.name.equalsIgnoreCase(name);
	  }
	  
	  protected Command parseNoParamsCommand(String[] words) throws CommandParseException{
			if (matchCommandName(words[0])) {
				if (words.length != 1)	
					throw new CommandParseException("[ERROR]: Command " + name + " :" + incorrectNumberOfArgsMsg);
				else return this;
			}
			return null;
	  }
	  
	  public String helpText(){
	    return details + ": " + help + "\n";
	  }
}
