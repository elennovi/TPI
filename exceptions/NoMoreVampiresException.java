package exceptions;

public class NoMoreVampiresException extends CommandExecuteException{
	private static final long serialVersionUID = 1L;
	
	public NoMoreVampiresException(String msg) {
		super(msg);
	}
}
