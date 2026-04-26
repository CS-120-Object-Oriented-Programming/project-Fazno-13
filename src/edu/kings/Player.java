package edu.kings;

import java.util.ArrayList;

public class Player {

	/**
	 * This class is the Player class
	 * 
	 * @author Vincent Fazzino
	 * @version 2.25.2026
	 *
	 */
	
	private Room currentRoom;
	private ArrayList<Item> inventory;
	
	//Constructor
	public Player(Room currentRoom, ArrayList<Item> inventory){
		this.currentRoom = currentRoom;
		this.inventory = inventory;
	}
	
	//Getter
	public Room getCurrentRoom() {
		return currentRoom;
	}
	
	//Setter
	public void setCurrentRoom(Room currentRoom) {
		this.currentRoom = currentRoom;
	}

	public ArrayList<Item> getInventory() {
		return inventory;
	}

	public String setInventory(Item newItem) {
		if (newItem.getWeight() == 0) {
			inventory.add(newItem);
			Writer.println("Succsessfuly pick up " + newItem.getName() + ". ");
			return newItem.getName();
		} else {
			Writer.println("Item is too heavy.");
			return "Item is too heavy.";
		}
	}
	
	public Item removeItem(String itemName) {
		for (int i=0;  i < inventory.size(); i++) {
			if (inventory.get(i).getName() == itemName) {
				inventory.remove(i);
				return inventory.get(i);
			}
		} return null;
	}
	
}
