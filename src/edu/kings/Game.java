package edu.kings;

import java.util.ArrayList;
import java.util.Random;

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
	/** Tracks the last room the player is in */
	private Room lastroom;
	private ArrayList<Item> item;
	
	private int energyCount = 0;
	private boolean energyWarning = true;
	private int socialCount = 0;
	private boolean socialWarning = true;
	
	Spell fireSpell = Spell.FIRE;
	Spell waterSpell = Spell.WATER;
	Spell earthSpell = Spell.EARTH;
	Spell windSpell = Spell.WIND;
	Spell darkSpell = Spell.DARK;
	
	/**
	 * Create the game and initialize its internal map.
	 */
	public Game() {
		world = new World();
		// set the starting room
		character = new Player(world.getRoom("Dorm Room #3 (Yours)"), new ArrayList<>(),false);
		lastroom = world.getRoom("Dorm Room #3 (Yours)");
	}

	/**
	 * Main play routine. Loops until end of play.
	 */
	public void play() {
		printWelcome();
		character.setScore(0);
		// Enter the main game loop. Here we repeatedly read commands and
		// execute them until the game is over.
		boolean wantToQuit = false;
		while (!wantToQuit) {
			Command command = Reader.getCommand();
			wantToQuit = processCommand(command);
			// Actions that happen every turn
			character.setTurns(character.getTurns()+1);
			socialCount ++;
			energyCount ++;
			if (socialCount%7 == 0) {
				character.setSocial(character.getSocial()-1);
				socialWarning = true;
			}
			if (energyCount%7 == 0) {
				character.setEnergy(character.getEnergy()-1);
				energyWarning = true;
			}
			//Gives warning once per cycle
			if (energyWarning && character.getSocial() == 6) {
				Writer.println("\nYou are getting a call from your roommate:");
				Writer.println("Hey dude, haven't heard about you in a bit. Hope all is well");
				energyWarning = false;
			}
			if (socialWarning && character.getEnergy() == 6) {
				Writer.println("\nYou begin to feel sleepy and your tummy grumbles");
				socialWarning = false;
			}
			wantToQuit = processStatus();
			
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
				status();
				break;
			case BACK:
				Room back = character.getCurrentRoom();
				character.setCurrentRoom(lastroom);
				printLocationInformation(lastroom);
				lastroom = back;
				break;
			case TURNS:
				Writer.println("You are on turn: " + character.getTurns());
				break;
			case SCORE:
				Writer.println("The Player score is: " + character.getScore());
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
			case CAST:
				spell(command);
				break;
			case TALK:
				talk(command);
				break;
			case TRUE:
			case FALSE:
				Writer.println("I do not understand");
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
	
	private void status() {
		Writer.println("The Player score is: " + character.getScore());
		Writer.println("You are on turn: " + character.getTurns());
		Writer.println("Energy: " + character.getEnergy() + "/" + character.getMaxEnergy());
		Writer.println("Social: " + character.getSocial() + "/" + character.getMaxSocial());
		printLocationInformation(character.getCurrentRoom());
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
			if (direction.equals("enter")) {
				if (character.getCurrentRoom().getExit(direction) == null) {
				} else if (character.getCurrentRoom().getExit(direction).isLocked() == false) {
					doorway = character.getCurrentRoom().getExit(direction);
					val = true;
				} else if (character.getCurrentRoom().getExit(direction).isLocked() == true) {
					Writer.println("The door is locked.");
					val = true;
				}
			}
			if (direction.equals("exit")) {
				if (character.getCurrentRoom().getExit(direction) == null) {
				} else if (character.getCurrentRoom().getExit(direction).isLocked() == false) {
					doorway = character.getCurrentRoom().getExit(direction);
					val = true;
				} else if (character.getCurrentRoom().getExit(direction).isLocked() == true) {
					Writer.println("The door is locked.");
					val = true;
				}
			}
			if (direction.equals("room 101")) {
				if (character.getCurrentRoom().getExit(direction) == null) {
				} else if (character.getCurrentRoom().getExit(direction).isLocked() == false) {
					doorway = character.getCurrentRoom().getExit(direction);
					val = true;
				} else if (character.getCurrentRoom().getExit(direction).isLocked() == true) {
					Writer.println("The door is locked.");
					val = true;
				}
			}
			if (direction.equals("room 102")) {
				if (character.getCurrentRoom().getExit(direction) == null) {
				} else if (character.getCurrentRoom().getExit(direction).isLocked() == false) {
					doorway = character.getCurrentRoom().getExit(direction);
					val = true;
				} else if (character.getCurrentRoom().getExit(direction).isLocked() == true) {
					Writer.println("The door is locked.");
					val = true;
				}
			}
			if (direction.equals("room 103")) {
				if (character.getCurrentRoom().getExit(direction) == null) {
				} else if (character.getCurrentRoom().getExit(direction).isLocked() == false) {
					doorway = character.getCurrentRoom().getExit(direction);
					val = true;
				} else if (character.getCurrentRoom().getExit(direction).isLocked() == true) {
					Writer.println("The door is locked.");
					val = true;
				}
			}
			if (direction.equals("room 104")) {
				if (character.getCurrentRoom().getExit(direction) == null) {
				} else if (character.getCurrentRoom().getExit(direction).isLocked() == false) {
					doorway = character.getCurrentRoom().getExit(direction);
					val = true;
				} else if (character.getCurrentRoom().getExit(direction).isLocked() == true) {
					Writer.println("The door is locked.");
					val = true;
				}
			}
			if (direction.equals("room 105")) {
				if (character.getCurrentRoom().getExit(direction) == null) {
				} else if (character.getCurrentRoom().getExit(direction).isLocked() == false) {
					doorway = character.getCurrentRoom().getExit(direction);
					val = true;
				} else if (character.getCurrentRoom().getExit(direction).isLocked() == true) {
					Writer.println("The door is locked.");
					val = true;
				}
			}
			if (direction.equals("room 106")) {
				if (character.getCurrentRoom().getExit(direction) == null) {
				} else if (character.getCurrentRoom().getExit(direction).isLocked() == false) {
					doorway = character.getCurrentRoom().getExit(direction);
					val = true;
				} else if (character.getCurrentRoom().getExit(direction).isLocked() == true) {
					Writer.println("The door is locked.");
					val = true;
				}
			}
			if (direction.equals("room 107")) {
				if (character.getCurrentRoom().getExit(direction) == null) {
				} else if (character.getCurrentRoom().getExit(direction).isLocked() == false) {
					doorway = character.getCurrentRoom().getExit(direction);
					val = true;
				} else if (character.getCurrentRoom().getExit(direction).isLocked() == true) {
					Writer.println("The door is locked.");
					val = true;
				}
			}
			if (direction.equals("room 108")) {
				if (character.getCurrentRoom().getExit(direction) == null) {
				} else if (character.getCurrentRoom().getExit(direction).isLocked() == false) {
					doorway = character.getCurrentRoom().getExit(direction);
					val = true;
				} else if (character.getCurrentRoom().getExit(direction).isLocked() == true) {
					Writer.println("The door is locked.");
					val = true;
				}
			}
			if (direction.equals("roof")) {
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
		Writer.println("I hope you had fun with my game!");
		Writer.println("Thank you for playing.  Good bye.");
		Writer.println("\nFinal Score: " + character.getScore());
		Writer.println("Total Moves: " + character.getTurns());
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
		Writer.println("Welcome to The Aethereal Academy!");
		Writer.println("\nYour name is Avalon. It’s his first day at Aethereal Academy"
					+ "\nand you just woke up late for your first class. Make sure to grab all" 
					+ "\nto grab all your stuff so you can graduate and become one of the best Wizards..");
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
					Writer.println(character.getCurrentRoom().getItem().get(i).getDescription());
					val = true;
				}
				if (character.getCurrentRoom().getItem().get(i).getName().equals("bed")) {
					Writer.println("Avalon takes a nap to restore his energy");
					character.setEnergy(character.getMaxEnergy());
					energyCount = 0;
					energyWarning= true;
					val = true;
				}
				if (character.getCurrentRoom().getItem().get(i).getName().equals("taco cart")) {
					Writer.println("Avalon eats a taco to restore his energy");
					character.setEnergy(character.getMaxEnergy());
					energyCount = 0;
					energyWarning= true;
					val = true;
				}
				if (character.getCurrentRoom().getItem().get(i).getName().equals("sandwich shop")) {
					Writer.println("Avalon eats a sandwich to restore his energy");
					character.setEnergy(character.getMaxEnergy());
					energyCount = 0;
					energyWarning= true;
					val = true;
				}
				if (character.getCurrentRoom().getItem().get(i).getName().equals("apple tree")) {
					Writer.println("Avalon eats an apple to restore his energy");
					character.setEnergy(character.getMaxEnergy());
					energyCount = 0;
					energyWarning= true;
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
		int index = 0;
		if (!command.hasSecondWord()) {
			Writer.println("Take what? ");
		} else {
			String theItem = command.getRestOfLine();
			for (int i=0; i < character.getCurrentRoom().getItem().size(); i++) {
				if (character.getCurrentRoom().getItem().get(i).getName().equals(theItem)) {
					index = i;
					val = true;
					if (character.getCurrentRoom().getItem().get(i).getName().equals("bag")) {
						character.setBag(true);
					}
				}
				
			} 
			if (character.isBag() && val) {
				character.setInventory(character.getCurrentRoom().getItem().get(index));
				character.getCurrentRoom().getItem().remove(index);
			} else
			if (val == false) {
				Writer.println("There is no such item. ");
			}
			if (!character.isBag()) {
				Writer.println("You do not have a bag. ");
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
	
	//ONLY FOR STATUE
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
	
	public void spell(Command command) {
		if (character.canCast()) {
			Boolean val = false;
			if (!command.hasSecondWord()) {
				Writer.println("Cast what? ");
			} else {
				String theItem = command.getRestOfLine();
				Writer.println(theItem);
				if (theItem.equals("fire")) {
					if (fireSpell.isAble()) {
						//Execute Fire Spell
						Writer.println("Use spell on what? ");
						String theAnswer = Reader.getResponse();
						for(int i = 0; i < character.getCurrentRoom().getItem().size(); i++) {
							if (character.getCurrentRoom().getItem().get(i).getName().equals(theAnswer)) {
								if(character.getCurrentRoom().getItem().get(i) instanceof Container == true) {
									for (int x = 0; x < ((Container)character.getCurrentRoom().getItem().get(i)).getContainerInventory().size(); x++) {
										if (((Container)character.getCurrentRoom().getItem().get(i)).getKey().equals("fire scroll")) {
											character.setInventory(((Container)character.getCurrentRoom().getItem().get(i)).getContainerInventory().get(x));
											((Container)character.getCurrentRoom().getItem().get(i)).getContainerInventory().remove(x);
											Writer.println("You have used your spell");
										}
										val = true;
									}
								} else {
									Writer.println("Not able to use spell");
									val = true;
								}
							} 
						} 
					}
				}
				if (theItem.equals("water")) {
					if (waterSpell.isAble()) {
						//Execute Water Spell
						Writer.println("Use spell on what? ");
						String theAnswer = Reader.getResponse();
						for(int i = 0; i < character.getCurrentRoom().getItem().size(); i++) {
							if (character.getCurrentRoom().getItem().get(i).getName().equals(theAnswer)) {
								if(character.getCurrentRoom().getItem().get(i) instanceof Container == true) {
									for (int x = 0; x < ((Container)character.getCurrentRoom().getItem().get(i)).getContainerInventory().size(); x++) {
										if (((Container)character.getCurrentRoom().getItem().get(i)).getKey().equals("water scroll")) {
											character.setInventory(((Container)character.getCurrentRoom().getItem().get(i)).getContainerInventory().get(x));
											((Container)character.getCurrentRoom().getItem().get(i)).getContainerInventory().remove(x);
											Writer.println("You have used your spell");
										}
										val = true;
									}
								} else {
									Writer.println("Not able to use spell");
									val = true;
								}
							} 
						} 
					}
				}	
				if (theItem.equals("earth")) {
					if (earthSpell.isAble()) {
						//Execute Earth Spell
						Writer.println("Use spell on what? ");
						String theAnswer = Reader.getResponse();
						for(int i = 0; i < character.getCurrentRoom().getItem().size(); i++) {
							if (character.getCurrentRoom().getItem().get(i).getName().equals(theAnswer)) {
								if(character.getCurrentRoom().getItem().get(i) instanceof Container == true) {
									for (int x = 0; x < ((Container)character.getCurrentRoom().getItem().get(i)).getContainerInventory().size(); x++) {
										if (((Container)character.getCurrentRoom().getItem().get(i)).getKey().equals("earth scroll")) {
											character.setInventory(((Container)character.getCurrentRoom().getItem().get(i)).getContainerInventory().get(x));
											((Container)character.getCurrentRoom().getItem().get(i)).getContainerInventory().remove(x);
											Writer.println("You have used your spell");
										}
										val = true;
									}
								} else {
									Writer.println("Not able to use spell");
									val = true;
								}
							} 
						} 
					}
				}
				if (theItem.equals("wind")) {
					windSpell.setAble();
					if (fireSpell.isAble()) {
						//Execute Wind Spell
						Writer.println("Use spell on what? ");
						String theAnswer = Reader.getResponse();
						for(int i = 0; i < character.getCurrentRoom().getItem().size(); i++) {
							if (character.getCurrentRoom().getItem().get(i).getName().equals(theAnswer)) {
								if(character.getCurrentRoom().getItem().get(i) instanceof Container == true) {
									for (int x = 0; x < ((Container)character.getCurrentRoom().getItem().get(i)).getContainerInventory().size(); x++) {
										if (((Container)character.getCurrentRoom().getItem().get(i)).getKey().equals("wind scroll")) {
											character.setInventory(((Container)character.getCurrentRoom().getItem().get(i)).getContainerInventory().get(x));
											((Container)character.getCurrentRoom().getItem().get(i)).getContainerInventory().remove(x);
											Writer.println("You have used your spell");
										}
										val = true;
									}
								} else {
									Writer.println("Not able to use spell");
									val = true;
								}
							} 
						} 
					}
				} 
				if (theItem.equals("dark")) {
					if (darkSpell.isAble()) {
						//Execute Water Spell
						Writer.println("Use spell on what? ");
						String theAnswer = Reader.getResponse();
						for(int i = 0; i < character.getCurrentRoom().getItem().size(); i++) {
							if (character.getCurrentRoom().getItem().get(i).getName().equals(theAnswer)) {
								if(character.getCurrentRoom().getItem().get(i) instanceof Container == true) {
									for (int x = 0; x < ((Container)character.getCurrentRoom().getItem().get(i)).getContainerInventory().size(); x++) {
										if (((Container)character.getCurrentRoom().getItem().get(i)).getKey().equals("dark scroll")) {
											character.setInventory(((Container)character.getCurrentRoom().getItem().get(i)).getContainerInventory().get(x));
											((Container)character.getCurrentRoom().getItem().get(i)).getContainerInventory().remove(x);
											Writer.println("You have used your spell");
										}
										val = true;
									}
								} else {
									Writer.println("Not able to use spell");
									val = true;
								}
							} 
						} 
					}
				}
				if (val == false) {
					Writer.println("You do not have that spell. ");
				}
			}
		} else {
			Writer.println("You do not have a wand to cast. ");
		}
	}
	
	private void talk(Command command) {
		Boolean val = false;
		if (!command.hasSecondWord()) {
			Writer.println("Talk to who? ");
		} else {
			String theNPC = command.getRestOfLine();
			for (int i=0; i < character.getCurrentRoom().getNPC().size(); i++) {
				if (character.getCurrentRoom().getNPC().get(i).getName().equals(theNPC)) {
					//Run NPC Command based on name
					//If basic name; ask question
					//If teacher; specific text
					//***Test SCROLLS***
					if (character.getCurrentRoom().getNPC().get(i) instanceof Orion) {
						//Orion Tasks
						int x = character.getCurrentRoom().getNPC().get(i).getProgress();
						if (character.getSocial() <= 2) {
							x = 4;
						}
						switch(x) {
						case 0:
							if (character.canCast()) {
								Writer.println("Hi, when you are ready to learn bring me a candle");
								character.getCurrentRoom().getNPC().get(i).setProgress(x+1);
							} else {
								Writer.println("How will you learn without a wand?");
							}
							break;
						case 1:
							for (int index=0; index < character.getInventory().size(); index++) {
								if (character.getInventory().get(index).getName().equals("candle")) {
									Writer.println("You gave " + character.getInventory().get(index) + "to Orion");
									character.getInventory().remove(index);
									Writer.println("Talk to him again to learn the ways of Fire");
									character.getCurrentRoom().getNPC().get(i).setProgress(x+1);
									val = true;
								}
							} if (val == false) {
								Writer.println("You do not have a candle. ");
							}
							break;
						case 2:
							Writer.println("You now know the ways of Fire");
							fireSpell.setAble();
							character.getCurrentRoom().getNPC().get(i).setProgress(x+1);
							break;
						case 3:
							Writer.println("Have fun knowing the ways of fire");
							break;
						default:
							Writer.println("...");
							
						}
					} else if (character.getCurrentRoom().getNPC().get(i) instanceof Merlin) {
						//Merlin Tasks
						int x = character.getCurrentRoom().getNPC().get(i).getProgress();
						if (character.getSocial() <= 2) {
							x = 4;
						}
						switch(x) {
						case 0:
							Writer.println("Hello, I just need to make sure you have the fire spell...");
							if (fireSpell.isAble()) {
								Writer.println("You do have the spell! Let's talk when you are ready");
								character.getCurrentRoom().getNPC().get(i).setProgress(x+1);
							} else {
								Writer.println("You do not so, go find Orion.");
							}
							break;
						case 1:
							Writer.println("To teach you the ways of water, we must find a place with water");
							Writer.println("Meet me by the foutain to learn the ways of water");
							//Move Merlin
							world.getRoom("courtyard").npcsInRoom.add(new Merlin("merlin","The Water Wizard",2));
							character.getCurrentRoom().getNPC().remove(i);
							Writer.println("Merlin walked away...");
							//
							break;
						case 2:
							waterSpell.setAble();
							Writer.println("Merlin teaches you the ways of water with the water in the foutain");
							Writer.println("Now, go forth and take your knowlege to learn from Demetrius");
							character.getCurrentRoom().getNPC().get(i).setProgress(x+1);
							break;
						case 3:
							Writer.println("Have fun knowing the ways of water");
							break;
						default:
							Writer.println("...");
						}
					} else if (character.getCurrentRoom().getNPC().get(i) instanceof Demetrius) {
						//Demetrius Tasks
						int x = character.getCurrentRoom().getNPC().get(i).getProgress();
						if (character.getSocial() <= 2) {
							x = 4;
						}
						switch(x) {
						case 0:
							Writer.println("Hey dude, you know the water spell from Merlin right?");
							if (waterSpell.isAble()) {
								Writer.println("You do know it! Great! Let's talk when you are ready dude!");
								character.getCurrentRoom().getNPC().get(i).setProgress(x+1);
							} else {
								Writer.println("Dude, go find Merlin, then come back here");
							}
							break;
						case 1:
							Writer.println("To teach you the ways of earth, you must find peace");
							if (character.getEnergy() == 10) {
								Writer.println("I can tell you are at peace with yourself");
								Writer.println("Meet me back in class and I will teach you some stuff dude!");
								//Move Demetrius
								world.getRoom("Demetrius Room #4").npcsInRoom.add(new Demetrius("demetrius","The Earth Wizard",2));
								character.getCurrentRoom().getNPC().remove(i);
								Writer.println("Demetrius walked away...");
								//
								character.getCurrentRoom().getNPC().get(i).setProgress(x+1);
							} else {
								Writer.println("You do not seem very at peace dude.");
								Writer.println("Get some rest before we try to learn.");
							}
							break;
						case 2:
							earthSpell.setAble();
							Writer.println("Demetrius teaches you the ways of earth");
							Writer.println("Nice dude! One more teacher, Zephiron! Good luck!");
							character.getCurrentRoom().getNPC().get(i).setProgress(x+1);
							break;
						case 3:
							Writer.println("Have fun knowing the ways of earth");
							break;
						default:
							Writer.println("...");
						}
					} else if (character.getCurrentRoom().getNPC().get(i) instanceof Zephiron) {
						//Zephiron Tasks
						int x = character.getCurrentRoom().getNPC().get(i).getProgress();
						if (character.getSocial() <= 2) {
							x = 4;
						}
						switch(x) {
						case 0:
							Writer.println("So... you have mastered all three spells?");
							if (earthSpell.isAble()) {
								Writer.println("Ok..when you are ready, come find me.");
								//Move Zephiron also need key for roof (note: key is hidden behind spell objects)
								world.getRoom("Roof").npcsInRoom.add(new Zephiron("zephiron","The Wind Wizard",1));
								character.getCurrentRoom().getNPC().remove(i);
								character.getCurrentRoom().addItem(new Item("zephiron note",0,1, "Find the Key, find me. Look were it all began"));
								world.getRoom("Dorm Room #3 (Yours)").addItem(new Item("paper airplane",0,1, "There is a note on the wings... Fire Room #[(5^3)-3(5+1)]"));
								world.getRoom("Academy Entrance").addItem(new Item("bird",0,0, "This is a baby bird"));
								Writer.println("Zephiron disapeared...");
								//
							} else {
								Writer.println("You did not... then leave.");
							}
							break;
						case 1:
							Writer.println("It is so peaceful up here. ");
							Writer.println("To teach you, we need a baby bird...");
							for (int index=0; index < character.getInventory().size(); index++) {
								if (character.getInventory().get(index).getName().equals("bird")) {
									Writer.println("You gave " + character.getInventory().get(index) + "to Zephiron");
									character.getInventory().remove(index);
									Writer.println("Fine. When you're ready, let us begin.");
									character.getCurrentRoom().getNPC().get(i).setProgress(x+1);
									val = true;
								}
							} if (val == false) {
								Writer.println("Go find one.");
							}
							break;
						case 2:
							windSpell.setAble();
							Writer.println("Zephiron teaches you the ways of wind");
							Writer.println("Now for your final exam, head to the foutain. ");
							Writer.println("You will know what to do. ");
							character.getCurrentRoom().getNPC().get(i).setProgress(x+1);
							break;
						case 3:
							Writer.println("What are you waiting for? Go!");
							break;
						default:
							Writer.println("...");
						}
					} else {
						Writer.println("\n" + character.getCurrentRoom().getNPC().get(i).getName() + ": ");
						Writer.println(character.getCurrentRoom().getNPC().get(i).getDescription());
						Random r = new Random();
						int question = character.getCurrentRoom().getNPC().get(i).getQuestion();
						int rand = r.nextInt(2); // 0 or 1
						if (character.getSocial() <= 4 && rand == 0) {
							character.getCurrentRoom().getNPC().get(i).setQuestion(10);	
						}
						character.setSocial(character.getSocial() + character.getCurrentRoom().getNPC().get(i).getAnswer());
						if (character.getSocial() > character.getMaxEnergy()) {
							character.setSocial(character.getMaxSocial());
						}
						character.getCurrentRoom().getNPC().get(i).setQuestion(question);
						socialCount = 0;
						socialWarning = true;
					}
					val = true;
				}
			} 
			if (val == false) {
				Writer.println("They are not in this room. ");
			}
		}
	}
	
	private boolean processStatus() {
		boolean retVal = false;
		int x= 0;
		if (character.getEnergy() == 0) {
			Writer.println("You passed out from exhaustion/ hunger...");
			retVal = true;
		}
		if (character.getSocial() == 0) {
			Writer.println("You got kicked out of school for being a loner");
			retVal = true;
		}
		if (character.getCurrentRoom().getName().equals("Outside")) {
			Writer.println("You left the school never to return... ");
			Writer.println("You here the voice of Gandalf as you walk away... ");
			Writer.println("You shall not pass!");
			retVal = true;
		}
		//Test if all angels have been activated (have all 4 token OR 1 dark token)
		for (int index=0; index < character.getInventory().size(); index++) {
			if (character.getInventory().get(index).getName().equals("token 1/4") && character.getCurrentRoom().getName().equals("Courtyard")) {
				Writer.println("You found a token");
				Writer.println("The fire angle is glowing");
				x += 1;
			}
			if (character.getInventory().get(index).getName().equals("token 2/4") && character.getCurrentRoom().getName().equals("Courtyard")) {
				Writer.println("You found a token");
				Writer.println("The fire angle is glowing");
				x += 1;
			}
			if (character.getInventory().get(index).getName().equals("token 3/4") && character.getCurrentRoom().getName().equals("Courtyard")) {
				Writer.println("You found a token");
				Writer.println("The fire angle is glowing");
				x += 1;
			}
			if (character.getInventory().get(index).getName().equals("token 4/4") && character.getCurrentRoom().getName().equals("Courtyard")) {
				Writer.println("You found a token");
				Writer.println("The fire angle is glowing");
				x += 1;
			}
			if (character.getInventory().get(index).getName().equals("dark token") && character.getCurrentRoom().getName().equals("Courtyard")) {
				Writer.println("The dark angle begins to glow and you feel an immense power");
				Writer.println("You hear the voice of Mordain:");
				Writer.println("The time has come... to take revenge!");
				retVal = true;
				return retVal;
			}
		if (x == 4) {
			Writer.println("Dumbledore congratulats you on passing all tests of the Academy");
			Writer.println("To the well-organized mind, hires to the next great adventure.");
			retVal = true;
		}
		}
		return retVal;
	}
}
