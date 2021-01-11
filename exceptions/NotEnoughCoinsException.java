package exceptions;

public class NotEnoughCoinsException extends CommandExecuteException{
	private static final long serialVersionUID = 1L;
	private static final String notEnoughCoins= "Not enough coins";
	
	public NotEnoughCoinsException(String name, int cost) {
		super("[ERROR]: " + name + " cost is " + cost + ": " + notEnoughCoins);
	}
}
