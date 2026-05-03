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
	private boolean bag;
	private int score;
	private int turns;
	private int social = 10;
	private int energy = 10;
	private int maxSocial = 10;
	private int maxEnergy = 10;
	
	//Constructor
	public Player(Room currentRoom, ArrayList<Item> inventory, boolean bag){
		this.currentRoom = currentRoom;
		this.inventory = inventory;
		this.bag = bag;
	}
	
	//Getter
	public Room getCurrentRoom() {
		return currentRoom;
	}
	
	public boolean isBag() {
		return bag;
	}
	
	//Setter
	public void setCurrentRoom(Room currentRoom) {
		this.currentRoom = currentRoom;
	}

	public ArrayList<Item> getInventory() {
		return inventory;
	}
	
	public void setBag(boolean bag) {
		this.bag = bag;
	}

	public String setInventory(Item newItem) {
		if (bag) {
			if (newItem.getWeight() == 0) {
				inventory.add(newItem);
				Writer.println("Succsessfuly pick up " + newItem.getName() + ". ");
				return newItem.getName();
			} else {
				Writer.println("Item is too heavy.");
				return "Item is too heavy.";
			}
		} else {
			return "You do not have a bag to hold anything";
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
	
	public boolean canCast() {
		boolean cast = false;
		for (int i=0;  i < inventory.size(); i++) {
			if (inventory.get(i).getName() == "wand") {
				cast = true;
				return cast;
			}
		} return cast;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getTurns() {
		return turns;
	}

	public void setTurns(int turns) {
		this.turns = turns;
	}

	public int getSocial() {
		return social;
	}

	public void setSocial(int social) {
		this.social = social;
	}

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}

	public int getMaxSocial() {
		return maxSocial;
	}

	public int getMaxEnergy() {
		return maxEnergy;
	}
	
}
