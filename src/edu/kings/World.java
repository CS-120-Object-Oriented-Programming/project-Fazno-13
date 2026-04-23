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
	private ArrayList<Item> items = new ArrayList<>();

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
		// Creating all the rooms.
		Room outside = new Room("Outside", "outside in the center of the King's College campus.");
		Room holyCross = new Room("Holy Cross", "at one of two main dormitories on campus.");
		Room essef = new Room("Essef", "at the other main dormitory on campus.");
		Room campusCenter = new Room("Campus Center", "in the center of student activities on campus.");
		Room admin = new Room("Admin", "in the oldest building on campus and home to the computer science department.");
		Room slivaOffice = new Room("Sliva's Office", "in Dr Sliva's office.");
		Room janoskiOffice = new Room("Janoski's Office", "in Dr Janoski's office.");
		Room lab = new Room("Computer Lab", "in the Computer Science and Math computing lab.");
		Room classroom = new Room("Classroom", "in the classroom where the computer science classes are taught.");

		// Adding all the rooms to the world.
		this.addRoom(outside);
		this.addRoom(holyCross);
		this.addRoom(essef);
		this.addRoom(campusCenter);
		this.addRoom(admin);
		this.addRoom(slivaOffice);
		this.addRoom(janoskiOffice);
		this.addRoom(lab);
		this.addRoom(classroom);

		// Creating all the doors between the rooms.
		createDoor(essef, "south", null, outside);
		createDoor(outside, "north", "essef key", essef);

		createDoor(campusCenter, "east", null, outside);
		createDoor(outside, "west", null, campusCenter);

		createDoor(outside, "east", null, holyCross);
		createDoor(holyCross, "west", null, outside);

		createDoor(outside, "south", null, admin);
		createDoor(admin, "north", null, outside);

		createDoor(admin, "east", null, lab);
		createDoor(lab, "west", null, admin);

		createDoor(admin, "south", null,  janoskiOffice);
		createDoor(janoskiOffice, "north", null, admin);

		createDoor(admin, "west", null, slivaOffice);
		createDoor(slivaOffice, "east", null, admin);

		createDoor(lab, "south", null, classroom);
		createDoor(classroom, "north", null, lab);
		
		// Adding all the items to their rooms
		
		outside.addItem(createItem(new Item("essef key",0,0, "The key to essef")));
		items.add(new Item("key", 0, 0, "a random key"));
		outside.addItem(createContainer(new Container("box", 0, 1, "a random box", items)));
	}
}
