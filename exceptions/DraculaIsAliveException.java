package exceptions;

public class DraculaIsAliveException extends CommandExecuteException{
	private static final long serialVersionUID = 1L;
			
	public DraculaIsAliveException(String msg) {
		super(msg);
	}
}
