package edu.kings;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class represents the entire world that makes up the "Campus of Kings"
 * application. "Campus of Kings" is a very simple, text based adventure game.
 * Users can walk around some scenery. That's all. It should really be extended
 * to make it more interesting!
 *
 * This world class creates the world where the game takes place.
 *
 * @author Maria Jump
 * @version 2015.02.01
 *
 * Used with permission from Dr. Maria Jump at Northeastern University
 */
public class World {
	/** The rooms in the world. */
	private HashMap<String, Room> rooms;
	private ArrayList<Item> test = new ArrayList<>();

	/**
	 * Constructor for the world.
	 */
	public World() {
		rooms = new HashMap<String, Room>();
		createRooms();
	}

	/**
	 * This method takes care of creating all of the aspects of the world for
	 * the "Campus of Kings" application.
	 *
	 * @param name
	 *            The provided name of the room.
	 * @return The room associated with the provided name
	 */
	public Room getRoom(String name) {
		return rooms.get(name.toLowerCase());
	}

	/////////////////////////////////////////////////////////////////////////////////////
	// Start of private helper methods

	/**
	 * Helper method for recreating a Room. Ensure that the room is created and
	 * installed in to the collection of Rooms
	 *
	 * @param theRoom
	 *            The room to add to the world.
	 */
	private void addRoom(Room theRoom) {
		rooms.put(theRoom.getName().toLowerCase(), theRoom);
	}

	private void createDoor(Room roomOne, String direction, String theKey, Room roomTwo) {
		Door aDoor = new Door(roomTwo, theKey);
		if (theKey != null) {
			aDoor.setLocked(true);
		}
		rooms.get(roomOne.getName().toLowerCase()).allDoors.put(direction,aDoor);
	}

	private Item createItem(Item newItem) {
		return newItem;
	}
	
	private Container createContainer(Container container) {
		return container;
	}
	
	/**
	 * This method creates all of the individual places in this world and all
	 * the doors connecting them.
	 */
	
	private void createRooms() {
		// Creating all the rooms
		Room foodPlaza = new Room("Food Plaza", "A place to meet new people and stop to eat/ drink.");
		Room enterance = new Room("Academy Entrance", "This is the where you entered the Academy.");
		Room outsideOrionHall = new Room("Orion Hall (outside)", "This is the first place you will learn a spell. The fire spells.");
		Room insideOrionHall = new Room("Orion Hall (inside)", "There are several of classrooms and students learning about fire");
		Room outsideMerlinHall = new Room("Merlin Hall (outside)", "This is the second place you will learn a spell. The water spells.");
		Room insideMerlinHall = new Room("Merlin Hall (inside)", "There are several of classrooms and students learning about water");
		Room outsideZephironHall = new Room("Zephiron Hall (outside)", "This is the fourth place you will learn a spell. The wind spells.");
		Room insideZephironHall = new Room("Zephiron Hall (inside)", "There are several of classrooms and students learning about wind");
		Room outsideDemetriusHall = new Room("Demetrius Hall (outside)", "This is the third place you will learn a spell. The earth spells.");
		Room insideDemetriusHall = new Room("Demetrius Hall (inside)", "There are several of classrooms and students learning about earth");
		Room outsideDorms = new Room("Dorms (outside)", "This is where you live in the Academy");
		Room insideDorms = new Room("Dorms (inside)", "This is where you and your fellow classmates sleep and hang out");
		Room courtyard = new Room("Courtyard", "The courtyard is in the center of the Academy. It has an academy map if you get lost. The fountain is a nice touch.");
		Room arawnsForest = new Room("Arawn's Forest", "This forest is named after the founder of the Academy.");
		Room outsideMordainMansion = new Room("Mordain Mansion (outside)", "An old abandon mansion that used to belong to Mordain. Rumor around school is that he was killed by your teachers.");
		Room insideMordainMansion = new Room("Mordain Mansion (inside)", "The place looks beaten down with an odd statue in the middle");	
		
		Room dorm101 = new Room("Dorm Room #1", "This is a dorm room");
		Room dorm102 = new Room("Dorm Room #2", "This is a dorm room");
		Room dorm103 = new Room("Dorm Room #3 (Yours)", "This is your dorm room");
		Room dorm104 = new Room("Dorm Room #4", "This is a dorm room");
		Room dorm105 = new Room("Dorm Room #5", "This is a dorm room");
		Room dorm106 = new Room("Dorm Room #6", "This is a dorm room");
		Room dorm107 = new Room("Dorm Room #7", "This is a dorm room");
		Room dorm108 = new Room("Dorm Room #8", "This is a dorm room");
		
		Room orion101 = new Room("Classroom Room #1", "This is a class room");
		Room orion102 = new Room("Classroom Room #2", "This is a class room");
		Room orion103 = new Room("Classroom Room #3", "This is a class room");
		Room orion104 = new Room("Classroom Room #4", "This is a class room");
		Room orion105 = new Room("Classroom Room #5", "This is a class room");
		Room orion106 = new Room("Classroom Room #6", "This is a class room");
		Room orion107 = new Room("Classroom Room #7", "This is a class room");
		Room orion108 = new Room("Classroom Room #8", "This is a class room");
		
		Room merlin101 = new Room("Classroom Room #1", "This is a class room");
		Room merlin102 = new Room("Classroom Room #2", "This is a class room");
		Room merlin103 = new Room("Classroom Room #3", "This is a class room");
		Room merlin104 = new Room("Classroom Room #4", "This is a class room");
		Room merlin105 = new Room("Classroom Room #5", "This is a class room");
		Room merlin106 = new Room("Classroom Room #6", "This is a class room");
		Room merlin107 = new Room("Classroom Room #7", "This is a class room");
		Room merlin108 = new Room("Classroom Room #8", "This is a class room");
		
		Room zephiron101 = new Room("Classroom Room #1", "This is a class room");
		Room zephiron102 = new Room("Classroom Room #2", "This is a class room");
		Room zephiron103 = new Room("Classroom Room #3", "This is a class room");
		Room zephiron104 = new Room("Classroom Room #4", "This is a class room");
		Room zephiron105 = new Room("Classroom Room #5", "This is a class room");
		Room zephiron106 = new Room("Classroom Room #6", "This is a class room");
		Room zephiron107 = new Room("Classroom Room #7", "This is a class room");
		Room zephiron108 = new Room("Classroom Room #8", "This is a class room");
		
		Room demetrius101 = new Room("Classroom Room #1", "This is a class room");
		Room demetrius102 = new Room("Classroom Room #2", "This is a class room");
		Room demetrius103 = new Room("Classroom Room #3", "This is a class room");
		Room demetrius104 = new Room("Classroom Room #4", "This is a class room");
		Room demetrius105 = new Room("Classroom Room #5", "This is a class room");
		Room demetrius106 = new Room("Classroom Room #6", "This is a class room");
		Room demetrius107 = new Room("Classroom Room #7", "This is a class room");
		Room demetrius108 = new Room("Classroom Room #8", "This is a class room");
		
		Room mordain101 = new Room("Classroom Room #1", "This is a class room");
		Room mordain102 = new Room("Classroom Room #2", "This is a class room");
		Room mordain103 = new Room("Classroom Room #3", "This is a class room");
		Room mordain104 = new Room("Classroom Room #4", "This is a class room");
		Room mordain105 = new Room("Classroom Room #5", "This is a class room");
		Room mordain106 = new Room("Classroom Room #6", "This is a class room");
		Room mordain107 = new Room("Classroom Room #7", "This is a class room");
		Room mordain108 = new Room("Classroom Room #8", "This is a class room");
		
		// Adding all the rooms to the world
		this.addRoom(foodPlaza);
		this.addRoom(enterance);
		this.addRoom(outsideOrionHall);
		this.addRoom(outsideMerlinHall);
		this.addRoom(outsideZephironHall);
		this.addRoom(outsideDemetriusHall);
		this.addRoom(outsideDorms);
		this.addRoom(courtyard);
		this.addRoom(arawnsForest);
		this.addRoom(outsideMordainMansion);
		this.addRoom(insideOrionHall);
		this.addRoom(insideMerlinHall);
		this.addRoom(insideZephironHall);
		this.addRoom(insideDemetriusHall);
		this.addRoom(insideDorms);
		this.addRoom(insideMordainMansion);
		this.addRoom(dorm101);
		this.addRoom(dorm102);
		this.addRoom(dorm103);
		this.addRoom(dorm104);
		this.addRoom(dorm105);
		this.addRoom(dorm106);
		this.addRoom(dorm107);
		this.addRoom(dorm108);
		this.addRoom(orion101);
		this.addRoom(orion102);
		this.addRoom(orion103);
		this.addRoom(orion104);
		this.addRoom(orion105);
		this.addRoom(orion106);
		this.addRoom(orion107);
		this.addRoom(orion108);		
		this.addRoom(merlin101);
		this.addRoom(merlin102);
		this.addRoom(merlin103);
		this.addRoom(merlin104);
		this.addRoom(merlin105);
		this.addRoom(merlin106);
		this.addRoom(merlin107);
		this.addRoom(merlin108);
		this.addRoom(zephiron101);
		this.addRoom(zephiron102);
		this.addRoom(zephiron103);
		this.addRoom(zephiron104);
		this.addRoom(zephiron105);
		this.addRoom(zephiron106);
		this.addRoom(zephiron107);
		this.addRoom(zephiron108);
		this.addRoom(demetrius101);
		this.addRoom(demetrius102);
		this.addRoom(demetrius103);
		this.addRoom(demetrius104);
		this.addRoom(demetrius105);
		this.addRoom(demetrius106);
		this.addRoom(demetrius107);
		this.addRoom(demetrius108);		
		this.addRoom(mordain101);
		this.addRoom(mordain102);
		this.addRoom(mordain103);
		this.addRoom(mordain104);
		this.addRoom(mordain105);
		this.addRoom(mordain106);
		this.addRoom(mordain107);
		this.addRoom(mordain108);



		// Creating all the doors between the rooms
//Main Areas
		createDoor(courtyard, "north", null, enterance);
		createDoor(enterance, "south", null, courtyard);

		createDoor(courtyard, "south", null, arawnsForest);
		createDoor(arawnsForest, "north", null, courtyard);
		
		createDoor(courtyard, "east", null, outsideMerlinHall);
		createDoor(outsideMerlinHall, "west", null, courtyard);
		
		createDoor(courtyard, "west", null, outsideDorms);
		createDoor(outsideDorms, "east", null, courtyard);
		
		createDoor(foodPlaza, "south", null, outsideDorms);
		createDoor(outsideDorms, "north", null, foodPlaza);
		
		createDoor(foodPlaza, "east", null, enterance);
		createDoor(enterance, "west", null, foodPlaza);
		
		createDoor(enterance, "east", null, outsideOrionHall);
		createDoor(outsideOrionHall, "west", null, enterance);
		
		createDoor(outsideOrionHall, "south", null, outsideMerlinHall);
		createDoor(outsideMerlinHall, "north", null, outsideOrionHall);

		createDoor(outsideMerlinHall, "south", null, outsideZephironHall);
		createDoor(outsideZephironHall, "north", null, outsideMerlinHall);
		
		createDoor(outsideZephironHall, "west", null, arawnsForest);
		createDoor(arawnsForest, "east", null, outsideZephironHall);
		
		createDoor(arawnsForest, "west", null, outsideDemetriusHall);
		createDoor(outsideDemetriusHall, "east", null, arawnsForest);
		
		createDoor(arawnsForest, "south", null, outsideMordainMansion);
		createDoor(outsideMordainMansion, "north", null, arawnsForest);
		
		createDoor(outsideDemetriusHall, "north", null, outsideDorms);
		createDoor(outsideDorms, "south", null, outsideDemetriusHall);
//Outside to inside
		createDoor(outsideDorms, "enter", "Wizard ID", insideDorms);
		createDoor(insideDorms, "exit", null, outsideDorms);
		
		createDoor(outsideOrionHall, "enter", "Wizard ID", insideOrionHall);
		createDoor(insideOrionHall, "exit", null, outsideOrionHall);
		
		createDoor(outsideMerlinHall, "enter", "Wizard ID", insideMerlinHall);
		createDoor(insideMerlinHall, "exit", null, outsideMerlinHall);
		
		createDoor(outsideZephironHall, "enter", "Wizard ID", insideZephironHall);
		createDoor(insideZephironHall, "exit", null, outsideZephironHall);
		
		createDoor(outsideDemetriusHall, "enter", "Wizard ID", insideDemetriusHall);
		createDoor(insideDemetriusHall, "exit", null, outsideDemetriusHall);
		
		createDoor(outsideMordainMansion, "enter", null, insideMordainMansion);
		createDoor(insideMordainMansion, "exit", null, outsideMordainMansion);
//Rooms in Dorms
		createDoor(insideDorms, "room 101", null, dorm101);
		createDoor(dorm101, "exit", null, insideDorms);
		
		createDoor(insideDorms, "room 102", "Master Key", dorm102);
		createDoor(dorm102, "exit", null, insideDorms);
		
		createDoor(insideDorms, "room 103", null, dorm103);
		createDoor(dorm103, "exit", null, insideDorms);
		
		createDoor(insideDorms, "room 104", null, dorm104);
		createDoor(dorm104, "exit", null, insideDorms);
		
		createDoor(insideDorms, "room 105", null, dorm105);
		createDoor(dorm105, "exit", null, insideDorms);
		
		createDoor(insideDorms, "room 106", null, dorm106);
		createDoor(dorm106, "exit", null, insideDorms);
		
		createDoor(insideDorms, "room 107", "Master Key", dorm107);
		createDoor(dorm107, "exit", null, insideDorms);
		
		createDoor(insideDorms, "room 108", null, dorm108);
		createDoor(dorm108, "exit", null, insideDorms);
//Rooms in Orion			
		createDoor(insideOrionHall, "room 101", null, orion101);
		createDoor(orion101, "exit", null, insideOrionHall);
		
		createDoor(insideOrionHall, "room 102", null, orion102);
		createDoor(orion102, "exit", null, insideOrionHall);
		
		createDoor(insideOrionHall, "room 103", null, orion103);
		createDoor(orion103, "exit", null, insideOrionHall);
		
		createDoor(insideOrionHall, "room 104", null, orion104);
		createDoor(orion104, "exit", null, insideOrionHall);
		
		createDoor(insideOrionHall, "room 105", null, orion105);
		createDoor(orion105, "exit", null, insideOrionHall);
		
		createDoor(insideOrionHall, "room 106", null, orion106);
		createDoor(orion106, "exit", null, insideOrionHall);
		
		createDoor(insideOrionHall, "room 107", null, orion107);
		createDoor(orion107, "exit", null, insideOrionHall);
		
		createDoor(insideOrionHall, "room 108", null, orion108);
		createDoor(orion108, "exit", null, insideOrionHall);
//Rooms in Merlin
		createDoor(insideMerlinHall, "room 101", null, merlin101);
		createDoor(merlin101, "exit", null, insideMerlinHall);
		
		createDoor(insideMerlinHall, "room 102", null, merlin102);
		createDoor(merlin102, "exit", null, insideMerlinHall);
		
		createDoor(insideMerlinHall, "room 103", null, merlin103);
		createDoor(merlin103, "exit", null, insideMerlinHall);
		
		createDoor(insideMerlinHall, "room 104", null, merlin104);
		createDoor(merlin104, "exit", null, insideMerlinHall);
		
		createDoor(insideMerlinHall, "room 105", null, merlin105);
		createDoor(merlin105, "exit", null, insideMerlinHall);
		
		createDoor(insideMerlinHall, "room 106", null, merlin106);
		createDoor(merlin106, "exit", null, insideMerlinHall);
		
		createDoor(insideMerlinHall, "room 107", null, merlin107);
		createDoor(merlin107, "exit", null, insideMerlinHall);
		
		createDoor(insideMerlinHall, "room 108", null, merlin108);
		createDoor(merlin108, "exit", null, insideMerlinHall);
//Rooms in Zephiron
		createDoor(insideZephironHall, "room 101", null, zephiron101);
		createDoor(zephiron101, "exit", null, insideZephironHall);
		
		createDoor(insideZephironHall, "room 102", null, zephiron102);
		createDoor(zephiron102, "exit", null, insideZephironHall);
		
		createDoor(insideZephironHall, "room 103", null, zephiron103);
		createDoor(zephiron103, "exit", null, insideZephironHall);
		
		createDoor(insideZephironHall, "room 104", null, zephiron104);
		createDoor(zephiron104, "exit", null, insideZephironHall);
		
		createDoor(insideZephironHall, "room 105", null, zephiron105);
		createDoor(zephiron105, "exit", null, insideZephironHall);
		
		createDoor(insideZephironHall, "room 106", null, zephiron106);
		createDoor(zephiron106, "exit", null, insideZephironHall);
		
		createDoor(insideZephironHall, "room 107", null, zephiron107);
		createDoor(zephiron107, "exit", null, insideZephironHall);
		
		createDoor(insideZephironHall, "room 108", null, zephiron108);
		createDoor(zephiron108, "exit", null, insideZephironHall);
//Rooms in Demetrius	
		createDoor(insideDemetriusHall, "room 101", null, demetrius101);
		createDoor(demetrius101, "exit", null, insideDemetriusHall);
		
		createDoor(insideDemetriusHall, "room 102", null, demetrius102);
		createDoor(demetrius102, "exit", null, insideDemetriusHall);
		
		createDoor(insideDemetriusHall, "room 103", null, demetrius103);
		createDoor(demetrius103, "exit", null, insideDemetriusHall);
		
		createDoor(insideDemetriusHall, "room 104", null, demetrius104);
		createDoor(demetrius104, "exit", null, insideDemetriusHall);
		
		createDoor(insideDemetriusHall, "room 105", null, demetrius105);
		createDoor(demetrius105, "exit", null, insideDemetriusHall);
		
		createDoor(insideDemetriusHall, "room 106", null, demetrius106);
		createDoor(demetrius106, "exit", null, insideDemetriusHall);
		
		createDoor(insideDemetriusHall, "room 107", null, demetrius107);
		createDoor(demetrius107, "exit", null, insideDemetriusHall);
		
		createDoor(insideDemetriusHall, "room 108", null, demetrius108);
		createDoor(demetrius108, "exit", null, insideDemetriusHall);
//Rooms in Mordain
		createDoor(insideMordainMansion, "room 101", null, mordain101);
		createDoor(mordain101, "exit", null, insideMordainMansion);
		
		createDoor(insideMordainMansion, "room 102", null, mordain102);
		createDoor(mordain102, "exit", null, insideMordainMansion);
		
		createDoor(insideMordainMansion, "room 103", null, mordain103);
		createDoor(mordain103, "exit", null, insideMordainMansion);
		
		createDoor(insideMordainMansion, "room 104", null, mordain104);
		createDoor(mordain104, "exit", null, insideMordainMansion);
		
		createDoor(insideMordainMansion, "room 105", null, mordain105);
		createDoor(mordain105, "exit", null, insideMordainMansion);
		
		createDoor(insideMordainMansion, "room 106", null, mordain106);
		createDoor(mordain106, "exit", null, insideMordainMansion);
		
		createDoor(insideMordainMansion, "room 107", null, mordain107);
		createDoor(mordain107, "exit", null, insideMordainMansion);
		
		createDoor(insideMordainMansion, "room 108", null, mordain108);
		createDoor(mordain108, "exit", null, insideMordainMansion);
		
		
		// Adding all the items to their rooms
		dorm103.addItem(createItem(new Item("Wizard ID",0,0, "This is your ID to enter buildings")));
		dorm103.addItem(createItem(new Item("Note",0,1, "This is a note from your roommate")));
		dorm108.addItem(createItem(new Item("Wand",0,0, "This lets you cast spells")));
		dorm101.addItem(createItem(new Item("Bag",0,0, "This lets you hold items")));
		
		
		// Adding NPCs to there rooms
	}
	
		//outside.addItem(createItem(new Item("essef key",0,0, "The key to essef")));
		//test.add(new Item("key", 0, 0, "a random key"));
		//outside.addItem(createContainer(new Container("box", 0, 1, "a random box", test)));
}
