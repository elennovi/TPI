package exceptions;

public class NoMoreVampiresException extends CommandExecuteException{
	private static final long serialVersionUID = 1L;
	private static final String noMoreVampiresLeft = "No more remaining vampires left";
	
	public NoMoreVampiresException() {
		super("[ERROR]: " + noMoreVampiresLeft);
	}
}
