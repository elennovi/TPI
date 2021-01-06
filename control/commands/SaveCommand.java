package control.commands;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import exceptions.CommandParseException;
import model.Game;

public class SaveCommand extends Command {
	private static int numArgsCommandSave = 2;
	private static final String name = "save";
	private static final String shortcut = "s";
	private static final String details = "[S]ave <filename>";
	private static final String help = "Save the state of the game to a file.";
	private static final String headerMsg = "Buffy the Vampire Slayer v3.0";
	private String filename;
	
	public SaveCommand() {
		super(name, shortcut, details, help);
	}

	public boolean execute(Game game) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename + ".dat"))) {
			writer.write(headerMsg + "\n\n");
			writer.write(game.serializeCommand());
			System.out.println("Game successfully saved to file " +  filename + ".dat.");
		} 
		catch (IOException e) {
			System.out.println("An error occurred");
			e.printStackTrace();
		}
		return false;
	}

	public Command parse(String[] commandWords) throws CommandParseException {
		if (matchCommandName(commandWords[0])) {
			if (commandWords.length == numArgsCommandSave) {
				filename = commandWords[1];
				return this;
			}
			else
				throw new CommandParseException("[ERROR]: " +  incorrectNumberOfArgsMsg + " for " + name + " command: " + details);
		}
		return null;
	}
}
