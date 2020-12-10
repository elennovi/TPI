package control;
import java.util.Scanner;

import control.commands.Command;
import control.commands.CommandGenerator;
import model.Game;

public class Controller {
	
	public final String prompt = "Command > ";
	public static final String unknownCommandMsg = "[ERROR]: " + "Unknown command";

    private Game game;
    private Scanner scanner;
    
    public Controller(Game game, Scanner scanner) {
	    this.game = game;
	    this.scanner = scanner;
    }
    
    public void  printGame() {
   	 System.out.println(game);
   }
    
    public void run() {
    	boolean refreshDisplay = true;
	    while (!game.isFinished()){
    		  if (refreshDisplay) printGame();
    		  refreshDisplay = false;
        		 
			  System.out.println(prompt);	
			  String s = scanner.nextLine();
			  String[] parameters = s.toLowerCase().trim().split(" ");
			  System.out.println("[DEBUG] Executing: " + s);
		      Command command = CommandGenerator.parseCommand(parameters);
		      if (command != null)
	    	  		refreshDisplay = command.execute(game);
		       
		      else
	    	   		System.out.println(unknownCommandMsg);
		}
    	if (refreshDisplay) printGame();
			System.out.println ("[Game over] " + game.getWinnerMessage());

    }

}