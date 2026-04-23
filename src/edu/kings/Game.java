package edu.kings;

import java.util.ArrayList;
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
	private ArrayList<Item> item;
	
	/**
	 * Create the game and initialize its internal map.
	 */
	public Game() {
		world = new World();
		// set the starting room
		character = new Player(world.getRoom("outside"), new ArrayList<>());
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
			case EXAMINE:
				examineItem(command);
				break;
			case TAKE:
				takeItem(command);
				break;
			case DROP:
				dropItem(command);
				break;
			case INVENTORY:
				myInventory();
				break;
			case UNLOCK:
				unlockDoor(command);
				break;
			case LOCK:
				lockDoor(command);
				break;
			case PACK:
				pack(command);
				break;
			case UNPACK:
				unPack(command);
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
		Writer.println(character.getCurrentRoom().toString());
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
			boolean val = false;
			// Try to leave current.
			Door doorway = null;
			if (direction.equals("north")) {
				if (character.getCurrentRoom().getExit(direction) == null) {
				} else if (character.getCurrentRoom().getExit(direction).isLocked() == false) {
					doorway = character.getCurrentRoom().getExit(direction);
					val = true;
				} else if (character.getCurrentRoom().getExit(direction).isLocked() == true) {
					Writer.println("The door is locked.");
					val = true;
				}
				
			}
			if (direction.equals("east")) {
				if (character.getCurrentRoom().getExit(direction) == null) {
				} else if (character.getCurrentRoom().getExit(direction).isLocked() == false) {
					doorway = character.getCurrentRoom().getExit(direction);
					val = true;
				} else if (character.getCurrentRoom().getExit(direction).isLocked() == true) {
					Writer.println("The door is locked.");
					val = true;
				}
			}
			if (direction.equals("south")) {
				if (character.getCurrentRoom().getExit(direction) == null) {
				} else if (character.getCurrentRoom().getExit(direction).isLocked() == false) {
					doorway = character.getCurrentRoom().getExit(direction);
					val = true;
				} else if (character.getCurrentRoom().getExit(direction).isLocked() == true) {
					Writer.println("The door is locked.");
					val = true;
				}
			}
			if (direction.equals("west")) {
				if (character.getCurrentRoom().getExit(direction) == null) {
				} else if (character.getCurrentRoom().getExit(direction).isLocked() == false) {
					doorway = character.getCurrentRoom().getExit(direction);
					val = true;
				} else if (character.getCurrentRoom().getExit(direction).isLocked() == true) {
					Writer.println("The door is locked.");
					val = true;
				}
			}

			if (doorway == null) {
				if (val == false) {
					Writer.println("There is no door!");
				}
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
	
	private void examineItem(Command command) {
		Boolean val = false;
		if (!command.hasSecondWord()) {
			Writer.println("Examine what? ");
		} else {
			String theItem = command.getRestOfLine();
			for (int i=0; i < character.getCurrentRoom().getItem().size(); i++) {
				if (character.getCurrentRoom().getItem().get(i).getName().equals(theItem)) {
					Writer.println(character.getCurrentRoom().getItem().get(i).toString());
					val = true;
				}
			}
			for (int index = 0; index < character.getInventory().size(); index++) {
				if (character.getInventory().get(index).getName().equals(theItem)) {
					Writer.println(character.getInventory().get(index).getDescription());
					val = true;
				}
			} if (val == false) {
				Writer.println("There is no such item. ");
			}
		}
	}
	
	private void takeItem(Command command) {
		Boolean val = false;
		if (!command.hasSecondWord()) {
			Writer.println("Take what? ");
		} else {
			String theItem = command.getRestOfLine();
			for (int i=0; i < character.getCurrentRoom().getItem().size(); i++) {
				if (character.getCurrentRoom().getItem().get(i).getName().equals(theItem)) {
					character.setInventory(character.getCurrentRoom().getItem().get(i));
					character.getCurrentRoom().getItem().remove(i);
					val = true;
				}
			} if (val == false) {
				Writer.println("There is no shuch item. ");
			}
		}
	}
	
	private void dropItem(Command command) {
		Boolean val = false;
		if (!command.hasSecondWord()) {
			Writer.println("Drop what? ");
		} else {
			String theItem = command.getRestOfLine();
			for (int i=0; i < character.getInventory().size(); i++) {
				if (character.getInventory().get(i).getName().equals(theItem)) {
					character.getCurrentRoom().getItem().add(character.getInventory().get(i));
					Writer.println("you dropped " + character.getInventory().get(i));
					character.getInventory().remove(i);
					val = true;
				}
			} if (val == false) {
				Writer.println("You do not have this item. ");
			}
		}
	}
	
	private void myInventory() {
		Writer.println(character.getInventory());
	}

	private void unlockDoor(Command command) {
		if (!command.hasSecondWord()) {
			// if there is no second word, we don't know where to go...
			Writer.println("Unlock What?");
		} else {
			String lockDirection = command.getRestOfLine();
			Boolean val = false;
		if (character.getCurrentRoom().getExit(lockDirection) == null) {
			Writer.println("There is no door");
		} else if (character.getCurrentRoom().getExit(lockDirection).isLocked() == false) {
			Writer.println("The door is not locked. ");
		} else if (character.getCurrentRoom().getExit(lockDirection).isLocked() == true) {
			Writer.println("Which key? ");
			String theAnswer = Reader.getResponse();
			for (int i = 0; i < character.getInventory().size(); i++) {
				if(character.getInventory().get(i).getName().equals(theAnswer) && character.getCurrentRoom().getExit(lockDirection).getKey().equals(theAnswer)) {
					character.getCurrentRoom().getExit(lockDirection).setLocked(false);
					Writer.println("The door has been unlocked. ");
					val = true;
				} else if(character.getInventory().get(i).getName().equals(theAnswer) && character.getCurrentRoom().getExit(lockDirection).getKey() != theAnswer) {
					Writer.println("The key does not fit. ");
					val = true;
				}
			}
			if (val == false) {
				Writer.println("You do not have that key. ");
			}
			}
		}
	}
	
	private void lockDoor(Command command) {
		if (!command.hasSecondWord()) {
			// if there is no second word, we don't know where to go...
			Writer.println("Lock What?");
		} else {
			String lockDirection = command.getRestOfLine();
			Boolean val = false;
			if (character.getCurrentRoom().getExit(lockDirection) == null) {
				Writer.println("There is no door");
			} else if (character.getCurrentRoom().getExit(lockDirection).isLocked() == true) {
				Writer.println("The door is already locked. ");
			} else if (character.getCurrentRoom().getExit(lockDirection).isLocked() == false && character.getCurrentRoom().getExit(lockDirection).getKey() == null) {
				Writer.println("This door can not be locked. ");
			} else if (character.getCurrentRoom().getExit(lockDirection).isLocked() == false && character.getCurrentRoom().getExit(lockDirection).getKey() != null) {
				Writer.println("Which key? ");
				String theAnswer = Reader.getResponse();
				for (int i = 0; i < character.getInventory().size(); i++) {
					if(character.getInventory().get(i).getName().equals(theAnswer) && character.getCurrentRoom().getExit(lockDirection).getKey().equals(theAnswer)) {
						character.getCurrentRoom().getExit(lockDirection).setLocked(true);
						Writer.println("The door has been locked. ");
						val = true;
					} else if(character.getInventory().get(i).getName().equals(theAnswer) && character.getCurrentRoom().getExit(lockDirection).getKey() != theAnswer) {
						Writer.println("That is the wrong key. ");
						val = true;
					}
				}
				if (val == false) {
					Writer.println("You do not have that key. ");
				}
			}
		}
	}
	
	private void pack(Command command) {
		if(!command.hasSecondWord()) {
			Writer.println("Pack what? ");
		} else {
			String theItem = command.getRestOfLine();
			boolean val = true;
			boolean isInRoom = false;
			boolean isInInventory = false;
			
			for(int i = 0; i < character.getCurrentRoom().getItem().size(); i++) {
				if(character.getCurrentRoom().getItem().get(i).getName().equals(theItem)) {
					Writer.println("What would you like to pack it in? ");
					String containerItem = Reader.getResponse();
					isInRoom = true;
					for(int index = 0; index < character.getCurrentRoom().getItem().size(); index++) {
						val = false;
						if(character.getCurrentRoom().getItem().get(index) instanceof Container && character.getCurrentRoom().getItem().get(index).getName().equals(containerItem)) {
							((Container)character.getCurrentRoom().getItem().get(index)).addItem(character.getCurrentRoom().getItem().get(i));
							character.getCurrentRoom().getItem().remove(character.getCurrentRoom().getItem().get(i));
							Writer.println("The item has been packed into the container. ");
							val = true;
						}
					}
				}
			} for(int anotherI = 0; anotherI < character.getInventory().size(); anotherI++) {
				if(character.getInventory().get(anotherI).getName().equals(theItem)) {
					Writer.println("What would you like to pack it in? ");
					String newContainerItem = Reader.getResponse();
					isInInventory = true;
					for(int anotherIndex = 0; anotherIndex < character.getCurrentRoom().getItem().size(); anotherIndex++) {
						val = false;
						if(character.getCurrentRoom().getItem().get(anotherIndex) instanceof Container && character.getCurrentRoom().getItem().get(anotherIndex).getName().equals(newContainerItem)) {
							((Container)character.getCurrentRoom().getItem().get(anotherIndex)).addItem(character.getInventory().get(anotherI));
							character.getInventory().remove(character.getInventory().get(anotherI));
							Writer.println("The item has been packed into the container. ");
							val = true;
						}
					}
				}
			} if(val == false) {
				Writer.println("That item is not a container. ");
			} if (isInRoom == false && isInInventory == false) {
				Writer.println("That item is not available. ");
			}
		}
	}
	
	public void unPack(Command command) {
		if (!command.hasSecondWord()) {
			Writer.println("Unpack what? ");
		} else {
			String container = command.getRestOfLine();
			boolean val = false;
			
			for (int i = 0; i < character.getCurrentRoom().getItem().size(); i++ ) {
				if (character.getCurrentRoom().getItem().get(i).getName().equals(container)) {
					if(character.getCurrentRoom().getItem().get(i) instanceof Container == true) {
						Writer.println("What item would you like to unpack?");
						String theAnswer = Reader.getResponse();
						for (int index = 0; index < ((Container)character.getCurrentRoom().getItem().get(i)).getContainerInventory().size(); index++) {
							if (((Container)character.getCurrentRoom().getItem().get(i)).getContainerInventory().get(index).getName().equals(theAnswer)) {
								character.setInventory(((Container)character.getCurrentRoom().getItem().get(i)).getContainerInventory().get(index));
								((Container)character.getCurrentRoom().getItem().get(i)).getContainerInventory().remove(index);
								Writer.println("The item has been unpacked. ");
								val = true;
							} else if(val == false){
								Writer.println("There is no such item in this container. ");
								val = true;
							}
						}
					} else {
						Writer.println("That item is not a container. ");
						val = true;
					}
				}
			} if (val == false) {
				Writer.println("That item is not in this room. ");
			}
		}
	}
	
}
