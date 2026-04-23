package edu.kings;

/**
 * This class is part of the "Campus of Kings" application. "Campus of Kings" is a
 * very simple, text based adventure game.
 *
 * This class holds an enumeration of all command words known to the game. It is
 * used to recognize commands as they are typed in.
 *
 * @author Maria Jump
 * @version 2015.02.01
 *
 * Used with permission from Dr. Maria Jump at Northeastern University
 */

public class CommandWords {
	/** A constant array that holds all valid command words. */
	private static CommandEnum[] validCommands = 
		{CommandEnum.GO, CommandEnum.HELP, CommandEnum.QUIT, CommandEnum.LOOK,
		CommandEnum.STATUS, CommandEnum.BACK, CommandEnum.TURNS, CommandEnum.SCORE, 
		CommandEnum.EXAMINE, CommandEnum.TAKE, CommandEnum.DROP, CommandEnum.INVENTORY,
		CommandEnum.UNLOCK, CommandEnum.LOCK, CommandEnum.PACK, CommandEnum.UNPACK};

	/**
	 * Static block to initialize the fields of CommandWords.
	 */

	/**
	 * Check whether a given String is a valid command word.
	 *
	 * @param aString The string to determine whether it is a valid command.
	 * @return true if a given string is a valid command, false if it isn't.
	 */
	public static boolean isCommand(CommandEnum aCommandEnum) {
		boolean valid = false;
		int index = 0;
		while (!valid && index < validCommands.length) {
			if (validCommands[index].equals(aCommandEnum)) {
				valid = true;
			}
			index++;
		}
		// if we get here, the string was not found in the commands
		return valid;
	}
	
	/** 
	* Converts a String into a CommandEnum object. 
	* 
	* @param theString The String containing the command word. 
	* @return The CommandEnum object representing the command, or null if 
	*		  the command does not exist.
	*/ 
	public static CommandEnum getCommand(String theString) {
		switch(theString) {
		case ("go"):
			return CommandEnum.GO;
		case ("help"):
			return CommandEnum.HELP;
		case ("look"):
			return CommandEnum.LOOK;
		case ("back"):
			return CommandEnum.BACK;
		case ("status"):
			return CommandEnum.STATUS;
		case ("quit"):
			return CommandEnum.QUIT;
		case ("score"):
			return CommandEnum.SCORE;
		case ("turns"):
			return CommandEnum.TURNS;
		case ("examine"):
			return CommandEnum.EXAMINE;
		case ("take"):
			return CommandEnum.TAKE;
		case ("drop"):
			return CommandEnum.DROP;
		case ("inventory"):
			return CommandEnum.INVENTORY;
		case ("unlock"):
			return CommandEnum.UNLOCK;
		case ("lock"):
			return CommandEnum.LOCK;
		case ("pack"):
			return CommandEnum.PACK;
		case ("unpack"):
			return CommandEnum.UNPACK;
		default:
			return null;
		}
	}
}
	
