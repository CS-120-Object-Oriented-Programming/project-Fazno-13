package edu.kings;
/**
 * This class is the main class of the "Campus of Kings" application.
 * "Campus of Kings" is a very simple, text based adventure game. Users can walk
 * around some scenery. That's all. It should really be extended to make it more
 * interesting!
 *
 * This game class creates and initializes all the others: it creates all rooms,
 * creates the parser and starts the game. It also evaluates and executes the
 * commands that the parser returns.
 *
 * @author Maria Jump
 * @author Vincent Fazzino
 * @version 2015.02.01
 *
 * Used with permission from Dr. Maria Jump at Northeastern University
 */

public class Game {
	/** The world where the game takes place. */
	private World world;
	/** This is a field that stores	the	character controlled by	the	player */
	private Player character;
	/** Adds the Score and number of turns */
	private int score;
	private int turns;
	/** Tracks the last room the player is in */
	private Room lastroom;
	
	/**
	 * Create the game and initialize its internal map.
	 */
	public Game() {
		world = new World();
		// set the starting room
		character = new Player(world.getRoom("outside"));
		lastroom = world.getRoom("outside");
	}

	/**
	 * Main play routine. Loops until end of play.
	 */
	public void play() {
		printWelcome();
		score = 0;
		// Enter the main game loop. Here we repeatedly read commands and
		// execute them until the game is over.
		boolean wantToQuit = false;
		while (!wantToQuit) {
			Command command = Reader.getCommand();
			wantToQuit = processCommand(command);
			turns++;
			// other stuff that needs to happen every turn can be added here.
		}
		printGoodbye();
	}

	///////////////////////////////////////////////////////////////////////////
	// Helper methods for processing the commands

	/**
	 * Given a command, process (that is: execute) the command.
	 *
	 * @param command
	 *            The command to be processed.
	 * @return true If the command ends the game, false otherwise.
	 */
	private boolean processCommand(Command command) {
		boolean wantToQuit = false;

		if (command.isUnknown()) {
			Writer.println("I don't know what you mean...");
		} else {

			CommandEnum commandWord = command.getCommandWord();
			switch (commandWord) {
			case HELP:
				printHelp();
				break;
			case GO:
				goRoom(command);
				break;
			case QUIT:
				wantToQuit = quit(command);
				break;
			case LOOK:
				look(command);
				break;
			case STATUS:
				Writer.println("The Player score is: " + score);
				Writer.println("You are on turn: " + turns);
				printLocationInformation(character.getCurrentRoom());
				break;
			case BACK:
				Room back = character.getCurrentRoom();
				character.setCurrentRoom(lastroom);
				printLocationInformation(lastroom);
				lastroom = back;
				break;
			case TURNS:
				Writer.println("You are on turn: " + turns);
				break;
			case SCORE:
				Writer.println("The Player score is: " + score);
				break;
			default:
				Writer.println(commandWord + " is not implemented yet!");
			}
		}
		return wantToQuit;
	}

	///////////////////////////////////////////////////////////////////////////
	// Helper methods for implementing all of the commands.
	// It helps if you organize these in alphabetical order.

	/*
	 *  Prints out the location information.
	 */
	private void printLocationInformation(Room newRoom) {
		Writer.println(character.getCurrentRoom().toString());
	}
	
	
	private void look(Command command) {
		Writer.println("There is nothing around you");
	}

	/**
	 * Try to go to one direction. If there is an exit, enter the new room,
	 * otherwise print an error message.
	 *
	 * @param command
	 *            The command to be processed.
	 */
	private void goRoom(Command command) {
		if (!command.hasSecondWord()) {
			// if there is no second word, we don't know where to go...
			Writer.println("Go where?");
		} else {
			String direction = command.getRestOfLine();

			// Try to leave current.
			Door doorway = null;
			if (direction.equals("north")) {
				doorway = character.getCurrentRoom().getNorthExit();
			}
			if (direction.equals("east")) {
				doorway = character.getCurrentRoom().getEastExit();
			}
			if (direction.equals("south")) {
				doorway = character.getCurrentRoom().getSouthExit();
			}
			if (direction.equals("west")) {
				doorway = character.getCurrentRoom().getWestExit();
			}

			if (doorway == null) {
				Writer.println("There is no door!");
			} else {
				lastroom = character.getCurrentRoom();
				Room newRoom = doorway.getDestination();
				character.setCurrentRoom(newRoom);
				printLocationInformation(newRoom);
			}
		}
	}

	/**
	 * Print out the closing message for the player.
	 */
	private void printGoodbye() {
		Writer.println("I hope you weren't too bored here on the Campus of Kings!");
		Writer.println("Thank you for playing.  Good bye.");
	}

	/**
	 * Print out some help information. Here we print some stupid, cryptic
	 * message and a list of the command words.
	 */
	private void printHelp() {
		Writer.println("You are lost. You are alone. You wander");
		Writer.println("around at the university.");
		Writer.println();
		Writer.println("Your command words are:");
		for (CommandEnum commandWord: CommandEnum.values()) {
			Writer.print(commandWord.getCommand() + " ");
		}
	}

	/**
	 * Print out the opening message for the player.
	 */
	private void printWelcome() {
		Writer.println();
		Writer.println("Welcome to the Campus of Kings!");
		Writer.println("Campus of Kings is a new, incredibly boring adventure game.");
		Writer.println("Type 'help' if you need help.");
		Writer.println();
		printLocationInformation(character.getCurrentRoom());
	}

	/**
	 * "Quit" was entered. Check the rest of the command to see whether we
	 * really quit the game.
	 *
	 * @param command
	 *            The command to be processed.
	 * @return true, if this command quits the game, false otherwise.
	 */
	private boolean quit(Command command) {
		boolean wantToQuit = true;
		if (command.hasSecondWord()) {
			Writer.println("Quit what?");
			wantToQuit = false;
		}
		return wantToQuit;
	}
	
}
