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
	private ArrayList<Item> box1 = new ArrayList<>();
	private ArrayList<Item> box2 = new ArrayList<>();
	private ArrayList<Item> box3 = new ArrayList<>();
	private ArrayList<Item> box4 = new ArrayList<>();
	private ArrayList<Item> box5 = new ArrayList<>();
	private ArrayList<Item> box6 = new ArrayList<>();
	private ArrayList<Item> box7 = new ArrayList<>();
	private ArrayList<Item> box8 = new ArrayList<>();
	private ArrayList<Item> box9 = new ArrayList<>();
	private ArrayList<Item> box10 = new ArrayList<>();
	private ArrayList<Item> box11 = new ArrayList<>();


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
	
	private NPC createNPC(NPC newNPC) {
		return newNPC;
	}
	
	private Container createContainer(Container container,boolean lock) {
		if (lock) {
			container.setLock(true);
		}
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
		Room outside = new Room("Outside", "You are now free from the Academy");
		
		Room dorm101 = new Room("Dorm Room #1", "This is a dorm room");
		Room dorm102 = new Room("Dorm Room #2", "This is a dorm room");
		Room dorm103 = new Room("Dorm Room #3 (Yours)", "This is your dorm room");
		Room dorm104 = new Room("Dorm Room #4", "This is a dorm room");
		Room dorm105 = new Room("Dorm Room #5", "This is a dorm room");
		Room dorm106 = new Room("Dorm Room #6", "This is a dorm room");
		Room dorm107 = new Room("Dorm Room #7", "This is a dorm room");
		Room dorm108 = new Room("Dorm Room #8", "This is a dorm room");
		
		Room orion101 = new Room("Orion Room #1", "This is a class room");
		Room orion102 = new Room("Orion Room #2", "This is a class room");
		Room orion103 = new Room("Orion Room #3", "This is a class room");
		Room orion104 = new Room("Orion Room #4", "This is a class room");
		Room orion105 = new Room("Orion Room #5", "This is a class room");
		Room orion106 = new Room("Orion Room #6", "This is a class room");
		Room orion107 = new Room("Orion Room #7", "This is a class room");
		Room orion108 = new Room("Orion Room #8", "This is a class room");
		
		Room merlin101 = new Room("Merlin Room #1", "This is a class room");
		Room merlin102 = new Room("Merlin Room #2", "This is a class room");
		Room merlin103 = new Room("Merlin Room #3", "This is a class room");
		Room merlin104 = new Room("Merlin Room #4", "This is a class room");
		Room merlin105 = new Room("Merlin Room #5", "This is a class room");
		Room merlin106 = new Room("Merlin Room #6", "This is a class room");
		Room merlin107 = new Room("Merlin Room #7", "This is a class room");
		Room merlin108 = new Room("Merlin Room #8", "This is a class room");
		
		Room zephiron101 = new Room("Zephiron Room #1", "This is a class room");
		Room zephiron102 = new Room("Zephiron Room #2", "This is a class room");
		Room zephiron103 = new Room("Zephiron Room #3", "This is a class room");
		Room zephiron104 = new Room("Zephiron Room #4", "This is a class room");
		Room zephiron105 = new Room("Zephiron Room #5", "This is a class room");
		Room zephiron106 = new Room("Zephiron Room #6", "This is a class room");
		Room zephiron107 = new Room("Zephiron Room #7", "This is a class room");
		Room zephiron108 = new Room("Zephiron Room #8", "This is a class room");
		Room roof = new Room("Roof", "The highest point in the Academy");
		
		
		Room demetrius101 = new Room("Demetrius Room #1", "This is a class room");
		Room demetrius102 = new Room("Demetrius Room #2", "This is a class room");
		Room demetrius103 = new Room("Demetrius Room #3", "This is a class room");
		Room demetrius104 = new Room("Demetrius Room #4", "This is a class room");
		Room demetrius105 = new Room("Demetrius Room #5", "This is a class room");
		Room demetrius106 = new Room("Demetrius Room #6", "This is a class room");
		Room demetrius107 = new Room("Demetrius Room #7", "This is a class room");
		Room demetrius108 = new Room("Demetrius Room #8", "This is a class room");
		
		Room mordain101 = new Room("Room #1", "This is a class room");
		Room mordain102 = new Room("Room #2", "This is a class room");
		Room mordain103 = new Room("Room #3", "This is a class room");
		Room mordain104 = new Room("Room #4", "This is a class room");
		Room mordain105 = new Room("Room #5", "This is a class room");
		Room mordain106 = new Room("Room #6", "This is a class room");
		Room mordain107 = new Room("Room #7", "This is a class room");
		Room mordain108 = new Room("Room #8", "This is a class room");
				
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
		this.addRoom(outside);
		this.addRoom(roof);



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
		
		createDoor(outsideDemetriusHall, "north", null, outsideDorms);
		createDoor(outsideDorms, "south", null, outsideDemetriusHall);
		
		createDoor(arawnsForest, "south", null, outsideMordainMansion);
		createDoor(outsideMordainMansion, "north", null, arawnsForest);
//Leaving The Map		
		createDoor(enterance, "north", null, outside);
		createDoor(outside, "south", null, enterance);
//Outside to inside
		createDoor(outsideDorms, "enter", "wizard id", insideDorms);
		createDoor(insideDorms, "exit", null, outsideDorms);
		
		createDoor(outsideOrionHall, "enter", "wizard id", insideOrionHall);
		createDoor(insideOrionHall, "exit", null, outsideOrionHall);
		
		createDoor(outsideMerlinHall, "enter", "wizard id", insideMerlinHall);
		createDoor(insideMerlinHall, "exit", null, outsideMerlinHall);
		
		createDoor(outsideZephironHall, "enter", "wizard id", insideZephironHall);
		createDoor(insideZephironHall, "exit", null, outsideZephironHall);
		
		createDoor(outsideDemetriusHall, "enter", "wizard id", insideDemetriusHall);
		createDoor(insideDemetriusHall, "exit", null, outsideDemetriusHall);
		
		createDoor(outsideMordainMansion, "enter", null, insideMordainMansion);
		createDoor(insideMordainMansion, "exit", null, outsideMordainMansion);
//Rooms in Dorms
		createDoor(insideDorms, "room 101", null, dorm101);
		createDoor(dorm101, "exit", null, insideDorms);
		
		createDoor(insideDorms, "room 102", "master key", dorm102);
		createDoor(dorm102, "exit", null, insideDorms);
		
		createDoor(insideDorms, "room 103", null, dorm103);
		createDoor(dorm103, "exit", null, insideDorms);
		
		createDoor(insideDorms, "room 104", null, dorm104);
		createDoor(dorm104, "exit", null, insideDorms);
		
		createDoor(insideDorms, "room 105", null, dorm105);
		createDoor(dorm105, "exit", null, insideDorms);
		
		createDoor(insideDorms, "room 106", null, dorm106);
		createDoor(dorm106, "exit", null, insideDorms);
		
		createDoor(insideDorms, "room 107", "master key", dorm107);
		createDoor(dorm107, "exit", null, insideDorms);
		
		createDoor(insideDorms, "room 108", null, dorm108);
		createDoor(dorm108, "exit", null, insideDorms);
//Rooms in Orion			
		createDoor(insideOrionHall, "room 101", "master key", orion101);
		createDoor(orion101, "exit", null, insideOrionHall);
		
		createDoor(insideOrionHall, "room 102", "master key", orion102);
		createDoor(orion102, "exit", null, insideOrionHall);
		
		createDoor(insideOrionHall, "room 103", null, orion103);
		createDoor(orion103, "exit", null, insideOrionHall);
		
		createDoor(insideOrionHall, "room 104", "master key", orion104);
		createDoor(orion104, "exit", null, insideOrionHall);
		
		createDoor(insideOrionHall, "room 105", null, orion105);
		createDoor(orion105, "exit", null, insideOrionHall);
		
		createDoor(insideOrionHall, "room 106", "master key", orion106);
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
		
		createDoor(insideMerlinHall, "room 103", "master key", merlin103);
		createDoor(merlin103, "exit", null, insideMerlinHall);
		
		createDoor(insideMerlinHall, "room 104", "master key", merlin104);
		createDoor(merlin104, "exit", null, insideMerlinHall);
		
		createDoor(insideMerlinHall, "room 105", null, merlin105);
		createDoor(merlin105, "exit", null, insideMerlinHall);
		
		createDoor(insideMerlinHall, "room 106", null, merlin106);
		createDoor(merlin106, "exit", null, insideMerlinHall);
		
		createDoor(insideMerlinHall, "room 107", null, merlin107);
		createDoor(merlin107, "exit", null, insideMerlinHall);
		
		createDoor(insideMerlinHall, "room 108", "master key", merlin108);
		createDoor(merlin108, "exit", null, insideMerlinHall);
//Rooms in Zephiron
		createDoor(insideZephironHall, "room 101", "master key", zephiron101);
		createDoor(zephiron101, "exit", null, insideZephironHall);
		
		createDoor(insideZephironHall, "room 102", "master key", zephiron102);
		createDoor(zephiron102, "exit", null, insideZephironHall);
		
		createDoor(insideZephironHall, "room 103", "master key", zephiron103);
		createDoor(zephiron103, "exit", null, insideZephironHall);
		
		createDoor(insideZephironHall, "room 104", null, zephiron104);
		createDoor(zephiron104, "exit", null, insideZephironHall);
		
		createDoor(insideZephironHall, "room 105", "master key", zephiron105);
		createDoor(zephiron105, "exit", null, insideZephironHall);
		
		createDoor(insideZephironHall, "room 106", null, zephiron106);
		createDoor(zephiron106, "exit", null, insideZephironHall);
		
		createDoor(insideZephironHall, "room 107", "master key", zephiron107);
		createDoor(zephiron107, "exit", null, insideZephironHall);
		
		createDoor(insideZephironHall, "room 108", null, zephiron108);
		createDoor(zephiron108, "exit", null, insideZephironHall);
		
		createDoor(insideZephironHall, "roof", "roof key", roof);
		createDoor(roof, "exit", null, insideZephironHall);
		
//Rooms in Demetrius	
		createDoor(insideDemetriusHall, "room 101", null, demetrius101);
		createDoor(demetrius101, "exit", null, insideDemetriusHall);
		
		createDoor(insideDemetriusHall, "room 102", null, demetrius102);
		createDoor(demetrius102, "exit", null, insideDemetriusHall);
		
		createDoor(insideDemetriusHall, "room 103", "master key", demetrius103);
		createDoor(demetrius103, "exit", null, insideDemetriusHall);
		
		createDoor(insideDemetriusHall, "room 104", null, demetrius104);
		createDoor(demetrius104, "exit", null, insideDemetriusHall);
		
		createDoor(insideDemetriusHall, "room 105", "master key", demetrius105);
		createDoor(demetrius105, "exit", null, insideDemetriusHall);
		
		createDoor(insideDemetriusHall, "room 106", "master key", demetrius106);
		createDoor(demetrius106, "exit", null, insideDemetriusHall);
		
		createDoor(insideDemetriusHall, "room 107", "master key", demetrius107);
		createDoor(demetrius107, "exit", null, insideDemetriusHall);
		
		createDoor(insideDemetriusHall, "room 108", "master key", demetrius108);
		createDoor(demetrius108, "exit", null, insideDemetriusHall);
//Rooms in Mordain
		createDoor(insideMordainMansion, "room 101", "master key", mordain101);
		createDoor(mordain101, "exit", null, insideMordainMansion);
		
		createDoor(insideMordainMansion, "room 102", "fire key", mordain102);
		createDoor(mordain102, "exit", null, insideMordainMansion);
		
		createDoor(insideMordainMansion, "room 103", "water key", mordain103);
		createDoor(mordain103, "exit", null, insideMordainMansion);
		
		createDoor(insideMordainMansion, "room 104", null, mordain104);
		createDoor(mordain104, "exit", null, insideMordainMansion);
		
		createDoor(insideMordainMansion, "room 105", null, mordain105);
		createDoor(mordain105, "exit", null, insideMordainMansion);
		
		createDoor(insideMordainMansion, "room 106", "wind key", mordain106);
		createDoor(mordain106, "exit", null, insideMordainMansion);
		
		createDoor(insideMordainMansion, "room 107", "master key", mordain107);
		createDoor(mordain107, "exit", null, insideMordainMansion);
		
		createDoor(insideMordainMansion, "room 108", "earth key", mordain108);
		createDoor(mordain108, "exit", null, insideMordainMansion);
		
		// Adding all the items to their rooms
		//id:101   wand:108   bag:101   candle: foodPlaza
		dorm103.addItem(createItem(new Item("wizard id",0,0, "This is your ID to enter buildings")));
		dorm103.addItem(createItem(new Item("roommate note",0,1, "The note says: What a party dude! Make sure to get your Bag, ID, and Wand, before heading to Orion Hall for class. You left it all around the dorms last night.")));
		dorm103.addItem(createItem(new Item("bed",0,1, "This is a place to get some rest")));
		dorm108.addItem(createItem(new Item("wand",0,0, "This lets you cast spells")));
		dorm101.addItem(createItem(new Item("bag",0,0, "This lets you hold items")));
		
		foodPlaza.addItem(createItem(new Item("candle",0,0, "This is an unlit candle")));
		foodPlaza.addItem(createItem(new Item("taco cart",0,1, "This is a good place to eat")));
		foodPlaza.addItem(createItem(new Item("sandwich shop",0,1, "This is a good place to eat")));
		arawnsForest.addItem(createItem(new Item("apple tree",0,1, "There looks to be some good apples in the tree")));
		
		courtyard.addItem(createItem(new Item("map",0,1, "A map of the Academy")));
		courtyard.addItem(createItem(new Item("foutain",0,1, "A pretty fountain with 5 angels around it, but one seems to have broken off")));
		box6.add(new Item("token 1/4", 0, 0, "a token for your final"));
		box7.add(new Item("token 2/4", 0, 0, "a token for your final"));
		box8.add(new Item("token 3/4", 0, 0, "a token for your final"));
		box9.add(new Item("token 4/4", 0, 0, "a token for your final"));
		box10.add(new Item("dark token", 0, 0, "a token for your final???"));
		courtyard.addItem(createContainer(new Container("fire angel", 0, 1, "an angle around the foutain", box6,"fire scroll"),true));
		courtyard.addItem(createContainer(new Container("water angel", 0, 1, "an angle around the foutain", box7,"water scroll"),true));
		courtyard.addItem(createContainer(new Container("earth angel", 0, 1, "an angle around the foutain", box8,"earth scroll"),true));
		courtyard.addItem(createContainer(new Container("wind angel", 0, 1, "an angle around the foutain", box9,"wind scroll"),true));
		courtyard.addItem(createContainer(new Container("broken angel", 0, 1, "an angle hidden in the grass", box10,"dark scroll"),true));

		box5.add(new Item("roof key",0,0, "keys to get onto the roof"));
		orion107.addItem(createContainer(new Container("desk", 0, 1, "a random desk", box5,null),false));
		demetrius104.addItem(createItem(new Item("demetrius note",0,1, "The note says: I am not a traditional teacher. Meet me in the forest when you are ready.")));
		
		box1.add(new Item("fire key", 0, 0, "a key with a fire emblem"));
		box2.add(new Item("water key", 0, 0, "a key with a water emblem"));
		box3.add(new Item("earth key", 0, 0, "a key with a earth emblem"));
		box4.add(new Item("wind key", 0, 0, "a key with a wind emblem"));
		arawnsForest.addItem(createContainer(new Container("tree", 0, 1, "a random tree", box1,"fire scroll"),true));
		arawnsForest.addItem(createContainer(new Container("pond", 0, 1, "a small body of water", box2,"water scroll"),true));
		arawnsForest.addItem(createContainer(new Container("bolder", 0, 1, "a large rock", box3,"earth scroll"),true));
		arawnsForest.addItem(createContainer(new Container("leaves", 0, 1, "a pile of leaves", box4,"wind scroll"),true));

		insideMordainMansion.addItem(createContainer(new Container("mordain statue", 0, 1, "A broken statue of one of the old professors. It seems to have 5 missing orbs around the base.", box11,null),false));
		mordain104.addItem(createItem(new Item("journal",0,1, "The journal says: Only a true wizard can find the keys hidden in Arawns Forest.")));
		insideMordainMansion.addItem(createItem(new Item("orb",0,0, "a slightly glowing ball")));
		mordain102.addItem(createItem(new Item("orb",0,0, "a slightly glowing ball")));
		mordain103.addItem(createItem(new Item("orb",0,0, "a slightly glowing ball")));
		mordain106.addItem(createItem(new Item("orb",0,0, "a slightly glowing ball")));
		mordain108.addItem(createItem(new Item("orb",0,0, "a slightly glowing ball")));

		
		// Adding NPCs to there rooms
		//Orion 3, 7, 8
		//zephiron 4 6 8
		//merlin 1 2 5 6 7 
		//demetrius 1 2 4
		//dorms 1 4 5 6 8
		courtyard.addNPC(createNPC(new NPC("harry","your roommate")));
		orion105.addNPC(createNPC(new Orion("orion","The Fire Wizard",0)));
		zephiron104.addNPC(createNPC(new Zephiron("zephiron","The Wind Wizard",0)));
		merlin101.addNPC(createNPC(new Merlin("merlin","The Water Wizard",0)));
		arawnsForest.addNPC(createNPC(new Demetrius("demetrius","The Earth Wizard",0)));

		enterance.addNPC(createNPC(new NPC("dr.darrell","A professor form another school")));
		courtyard.addNPC(createNPC(new NPC("dr.koscho","A professor form another school")));
		enterance.addNPC(createNPC(new NPC("cody","A wizard in training")));
		orion103.addNPC(createNPC(new NPC("leonel","A fire wizard")));
		merlin101.addNPC(createNPC(new NPC("averi","A water wizard in training")));
		arawnsForest.addNPC(createNPC(new NPC("vincent","A earth wizard in training")));
		zephiron106.addNPC(createNPC(new NPC("axel","A wind wizard in training")));
		orion107.addNPC(createNPC(new NPC("athena","A fire wizard")));
		merlin106.addNPC(createNPC(new NPC("neshell","A water wizard")));
		zephiron106.addNPC(createNPC(new NPC("dannellys","A wind wizard")));
		demetrius102.addNPC(createNPC(new NPC("jacob","A earth wizard")));
		foodPlaza.addNPC(createNPC(new NPC("nicholas","A hungry water wizard")));
		foodPlaza.addNPC(createNPC(new NPC("brandon","A hungry fire wizard")));
		foodPlaza.addNPC(createNPC(new NPC("eric","A hungry earth wizard")));
		
		enterance.addNPC(createNPC(new NPC("vinny","An overworked wizard")));
		outsideMordainMansion.addNPC(createNPC(new NPC("sam","A wizard with a dark secret")));
		outsideOrionHall.addNPC(createNPC(new NPC("noah","A failing wizard")));
		dorm101.addNPC(createNPC(new NPC("taylor","A creative  wizard")));
		merlin107.addNPC(createNPC(new NPC("roman","An athletic wizard")));
		dorm104.addNPC(createNPC(new NPC("matt","An excelling wizard")));
		dorm101.addNPC(createNPC(new NPC("kj","A musical wizard")));
		dorm106.addNPC(createNPC(new NPC("dominick","A friendly wizard")));
		dorm108.addNPC(createNPC(new NPC("jason","A tired wizard")));
		dorm105.addNPC(createNPC(new NPC("melissa","A caring wizard")));
		
		insideMordainMansion.addNPC(createNPC(new NPC("ghost","A dead wizard")));
		insideMordainMansion.addNPC(createNPC(new NPC("skeleton","A mutilated wizard")));


	}
	
}
