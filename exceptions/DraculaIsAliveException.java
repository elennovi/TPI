package exceptions;

public class DraculaIsAliveException extends CommandExecuteException{
	private static final long serialVersionUID = 1L;
	private static final String draculaIsAlreadyAlive = "Dracula is already on board";
			
	public DraculaIsAliveException() {
		super("[ERROR]: " + draculaIsAlreadyAlive);
	}
}
