package control.commands;

import model.Game;

public abstract class Command {

	  protected final String name;
	  protected final String shortcut;
	  private final String details; 
	  private final String help;
	  
	  protected static final String notEnoughCoins= "[ERROR]: " + "Not enough coins";
	  protected static final String invalidPosition = "[ERROR]: " + "Invalid position";
//	  protected static final String incorrectNumberOfArgsMsg = "[ERROR]: " + "Incorrect number of arguments";
//	  protected static final String incorrectArgsMsg = "[ERROR]: " + "Incorrect arguments format";
	  protected static final String noMoreVampiresLeft = "[ERROR]: " + "No more remaining vampires left";
	  protected static final String draculaIsAlive = "[ERROR]: " + "Dracula is alive";
	  protected static final String unknownCommand = "[ERROR]: " + "Unknown command";
	  
	  public Command(String name,  String shortcut, String details, String help){    
	    this.name = name;
	    this.shortcut = shortcut;
	    this.details = details;
	    this.help = help;
	  }
	  
	  public abstract boolean execute(Game game);
	  
	  public abstract Command parse(String[] commandWords);
	  
	  protected boolean matchCommandName(String name) {
		    return this.shortcut.equalsIgnoreCase(name) || 
		        this.name.equalsIgnoreCase(name);
	  }
	  
	  protected Command parseNoParamsCommand(String[] words) {
			if (matchCommandName(words[0])) {
				if (words.length > 1)	
					return null;
				else
					return this;
			}
			return null;
	  }
	  
	  public String helpText(){
	    return details + ": " + help + "\n";
	  }
}
