package exceptions;

public class CommandParseException extends GameException{
	private static final long serialVersionUID = 1L;
	
	public CommandParseException(String message) {
		super(message);
	}

}
