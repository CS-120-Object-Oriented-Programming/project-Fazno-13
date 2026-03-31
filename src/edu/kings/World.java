package edu.kings;
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

	/**
	 * Helper method for creating doors between rooms.
	 *
	 * @param from
	 *            The room where the door originates.
	 * @param north
	 *            The room to the north of the originating room.
	 */
	private void createNorthDoor(Room from, Room north) {
		Door northDoor = new Door(north);
		from.setNorthExit(northDoor);
	}

	/**
	 * Helper method for creating doors between rooms.
	 *
	 * @param from
	 *            The room where the door originates.
	 * @param east
	 *            The room to the east of the originating room.
	 */
	private void createEastDoor(Room from, Room east) {
		Door eastDoor = new Door(east);
		from.setEastExit(eastDoor);
	}

	/**
	 * Helper method for creating doors between rooms.
	 *
	 * @param from
	 *            The room where the door originates.
	 * @param south
	 *            The room to the south of the originating room.
	 */
	private void createSouthDoor(Room from, Room south) {
		Door southDoor = new Door(south);
		from.setSouthExit(southDoor);
		}

	/**
	 * Helper method for creating doors between rooms.
	 *
	 * @param from
	 *            The room where the door originates.
	 * @param west
	 *            The room to the west of the originating room.
	 */
	private void createWestDoor(Room from, Room west) {
		Door westDoor = new Door(west);
		from.setWestExit(westDoor);
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
		this.createSouthDoor(essef, outside);
		this.createNorthDoor(outside, essef);

		this.createEastDoor(campusCenter, outside);
		this.createWestDoor(outside, campusCenter);

		this.createEastDoor(outside, holyCross);
		this.createWestDoor(holyCross, outside);

		this.createSouthDoor(outside, admin);
		this.createNorthDoor(admin, outside);

		this.createEastDoor(admin, lab);
		this.createWestDoor(lab, admin);

		this.createSouthDoor(admin, janoskiOffice);
		this.createNorthDoor(janoskiOffice, admin);

		this.createWestDoor(admin, slivaOffice);
		this.createEastDoor(slivaOffice, admin);

		this.createSouthDoor(lab, classroom);
		this.createNorthDoor(classroom, lab);
	}
}
