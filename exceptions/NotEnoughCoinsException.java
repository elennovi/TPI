package exceptions;

public class NotEnoughCoinsException extends CommandExecuteException{
	private static final long serialVersionUID = 1L;
	
	public NotEnoughCoinsException(String msg) {
		super(msg);
	}
}
