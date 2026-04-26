package edu.kings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
	
	// Key is direction and Value is Door
	// HashMap for all Doors
	// Doors: North, South, East, West, Portal, Void
	HashMap<String, Door> allDoors = new HashMap<>();
	
	// A list of items in that room
	ArrayList <Item> itemsInRoom = new ArrayList<>();
	
	public String toString() {
		String retVal = getName() + ": " + getDescription() + "\n Exits: ";
		for (String nextDoor: allDoors.keySet()) {
			retVal += nextDoor + " ";
		}
		retVal += "\nItems: ";
		for (int i=0; i< itemsInRoom.size(); i++) {
			retVal += itemsInRoom.get(i).toString() + " ";
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
	
	/** 
	* Defines an exit from this room. 
	* 
	* @param direction The direction of the exit. 
	* @param neighbor The door in the given direction. 
	*/ 
	
	public void setExit(String direction, Door neighbor) {
		allDoors.put(direction, neighbor);
	}
	
	public Door getExit(String direction) {
		return allDoors.get(direction);
	}
	
	
	// make a list, add item to list, remove from list and add to inventory, 
	public void addItem(Item newItem) {
		itemsInRoom.add(newItem);
	}
	
	public ArrayList<Item> getItem() {
		return itemsInRoom;
	}
	
	public Item removeItem(String itemName) {
		for (int i=0;  i < itemsInRoom.size(); i++) {
			if (itemsInRoom.get(i).getName() == itemName) {
				itemsInRoom.remove(i);
				return itemsInRoom.get(i);
			}
		} return null;
	}
}
