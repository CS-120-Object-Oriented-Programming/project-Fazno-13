package edu.kings;
/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "Campus of Kings" application. "Campus of Kings" is a
 * very simple, text based adventure game.
 *
 * A "Room" represents one location in the scenery of the game. It is connected
 * to other rooms via doors. The doors are labeled north, east, south, west.
 * For each direction, the room stores a reference to an instance of door.
 *
 * @author Maria Jump
 * @version 2015.02.01
 *
 * Used with permission from Dr. Maria Jump at Northeastern University
 */
public class Room {
	/** Counter for the total number of rooms created in the world. */
	private static int counter;
	/** The name of this room.  Room names should be unique. */
	private String name;
	/** The description of this room. */
	private String description;

	/** This room's north exit, null if none exits. */
	private Door northExit;
	/** This room's south exit, null if none exits. */
	private Door southExit;
	/** This room's east exit, null if none exits. */
	private Door eastExit;
	/** This room's west exit, null if none exits. */
	private Door westExit;

	
	public Door getNorthExit() {
		return northExit;
	}
	
	public void setNorthExit(Door northExit) {
		this.northExit = northExit;
	}
	
	public Door getEastExit() {
		return eastExit;
	}
	
	public void setEastExit(Door eastExit) {
		this.eastExit = eastExit;
	}
	
	public Door getSouthExit() {
		return southExit;
	}
	
	public void setSouthExit(Door southExit) {
		this.southExit = southExit;
	}
	
	public Door getWestExit() {
		return westExit;
	}
	
	public void setWestExit(Door westExit) {
		this.westExit = westExit;
	}
	
	public String toString() {
		String retVal = getName() + ": " + getDescription() + "\n Exits: ";
		if (getNorthExit() != null) {
			retVal += "north ";
		}
		if (getEastExit() != null) {
			retVal += "east ";
		}
		if (getSouthExit() != null) {
			retVal += "south ";
		}
		if (getWestExit() != null) {
			retVal += "west ";
		}
		return retVal + "\n";
	}
	
	
	
	/**
	 * Static initializer.
	 */
	static {
		counter = 0;
	}
	/**
	 * Create a room described "description". Initially, it has no exits.
	 * "description" is something like "a kitchen" or "an open court yard".
	 *
	 * @param name  The room's name.
	 * @param description
	 *            The room's description.
	 */
	public Room(String name, String description) {
		this.name = name;
		this.description = description;
		counter++;
	}

	/**
	 * Returns the name of this room.
	 *
	 * @return The name of this room.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the description of this room.
	 *
	 * @return The description of this room.
	 */
	public String getDescription() {
		return description;
	}
	

	/**
	 * Returns the number of rooms that have been created in the world.
	 * @return The number of rooms that have been created in the world.
	 */
	public static int getCounter() {
		return counter;
	}
	
}
