package edu.kings;

import java.util.ArrayList;

public class Container extends Item{

	private ArrayList<Item> containerInventory = new ArrayList<>();
	private String theKey;
	private boolean locked;
	
	public Container(String newItem, int newScore, int newWeight, String newDescription, ArrayList<Item> newList,String theKey) {
		super(newItem, newScore, newWeight, newDescription);
		containerInventory = newList;
		this.locked = false;
		this.theKey = theKey;
	}

	public ArrayList<Item> getContainerInventory() {
		return containerInventory;
	}
	
	public void addItem(Item item) {
		containerInventory.add(item);
	}
	
	public void removeItem(String itemName) {
		if (containerInventory == null) {
			Writer.println("It is empty.");
		} else {
			for (int i = 0; i < containerInventory.size(); i++)
				if(containerInventory.get(i).getName().equals(itemName)) {
					containerInventory.remove(i);
				} else {
					Writer.println("That item is not in here. ");
				}
		}
	}
	
	@Override
	public String toString() {
		String retVal = "\n" + getName() + ": " + getDescription();
		return retVal;
	}
	
	public String getKey() {
		return theKey;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLock(boolean locked) {
		this.locked = locked;
	}
	
}
