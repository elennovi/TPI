package exceptions;

public class InvalidPositionException extends CommandExecuteException{
	private static final long serialVersionUID = 1L;
	
	public InvalidPositionException(String message) {
		super(message);
	}
}
