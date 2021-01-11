package exceptions;

public class InvalidPositionException extends CommandExecuteException{
	private static final long serialVersionUID = 1L;
	private static final String invalidPositionMsg = "Unvalid position";
	
	public InvalidPositionException(int col, int row) {
		super("[ERROR]: Position (" + col + ", " + row + "): " + invalidPositionMsg);
	}
}
