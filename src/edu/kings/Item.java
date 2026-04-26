package edu.kings;

public class Item {
	
	private String name;
	private String description;
	private int points;
	private int weight;
	
	public Item(String name, int points, int weight, String description) {
		this.name = name;
		this.description = description;
		this.points = points;
		this.weight = weight;
	}
	
	public String getName() {
		return name;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
	public int getPoints() {
		return points;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public String toString() {
		String retVal = getName() + ": " + getDescription();
		return retVal;
	}
}
