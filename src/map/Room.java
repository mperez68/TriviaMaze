/*
 * Room.java
 *
 * TCSS 360 - Fall 2020 Project
 */

package map;
import map.Door;
//TODO import GUI singleton

/**
 * The Room Object. This will contain Door objects that contain Question objects that can be called to determine map
 * traversals. 
 *
 * Version 2.0: Added method getDoor(), method getX(), method getY().
 * 
 * @author Marc Perez (perezm68)
 * @author Logan Crawford (crawfl5)
 * @date 11/22/2020
 * @version 2.0
 */
public class Room {
	/**
	 * Enumerated type to control input variance. Each direction corresponds to the array locations associated in the
	 * myAdjacentDoors array.
	 * @author Marc Perez (perezm68)
	 */
	public enum Direction{UP(0), RIGHT(1), DOWN(2), LEFT(3);
		private final int myValue;
		Direction(int theValue){ myValue = theValue; };
		public int getValue() { return myValue; }
		public int getOpposite() { return (myValue+2)%4; }
	}
	
	/**
	 * Array of up to 4 doors, each facing a different direction. Only accessed with integers associated with the enumerated
	 * type Direction.
	 */
	Door myAdjacentDoors[];
	/**
	 * Room 'X' coordinate on draw grid.
	 */
	private int myX;
	/**
	 * Room 'Y' coordinate on draw grid.
	 */
	private int myY;
	/**
	 * Constructor. Requires X and Y coordinates.
	 * @param theX Room 'X' coordinate on draw grid determined by placement on Map grid.
	 * @param theY Room 'Y' coordinate on draw grid determined by placement on Map grid.
	 */
	public Room(int theX, int theY) {
		myX = theX;
		myY = theY;
		myAdjacentDoors = new Door[4];
	}
	/**
	 * Redraws the room. refers to it's own location and calls each adjacent door, which also has it's own location.
	 */
	public void draw() {
		// TODO implement as GUI draw
		System.out.print("[Room @ (" + myX + ", " + myY + ")] >> [ ");
		for (int i = 0; i < 4; i++) {
			if (myAdjacentDoors[i] != null){
				myAdjacentDoors[i].draw();
			}
		}
		System.out.println(" ]");
	}
	/**
	 * Getter for if the door is locked.
	 * @param theDirection the direction outward from this room towards the chosen door.
	 * @return Truth value of if the door is locked.
	 */
	public boolean isNotLocked(Direction theDirection) {
		return myAdjacentDoors[theDirection.getValue()].isNotLocked();
	}
	/**
	 * Setter for unassigned doors. Does not alter door if it is already set.
	 * @param theDirection the direction outward from this room towards the chosen door.
	 * @param theDoor The new Door object being placed. Will not be placed if a door already exists there.
	 */
	protected void setDoor(Direction theDirection, Door theDoor) {
		if (myAdjacentDoors[theDirection.getValue()] == null) {
			myAdjacentDoors[theDirection.getValue()] = theDoor;
		}
	}
	
	/**
	 * Getter for the instance room's door of the specified direction.
	 * @return Returns the instance of the door. Returns null if there is no door in the specified direction.
	 */
	public Door getDoor(Direction theDirection) {
		if (myAdjacentDoors[theDirection.getValue()] != null) {
			return myAdjacentDoors[theDirection.getValue()];
		} else {
			return null;
		}
	}
	
	/**
	 * Getter for x-coordinate value.
	 * @return x-coordinate value.
	 */
	public int getX() {
		return myX;
	}
	
	/**
	 * Getter for y-coordinate value.
	 * @return y-coordinate value.
	 */
	public int getY() {
		return myY;
	}
	
	/**
	 * Attempt to enter the room in the direction given.
	 * @param theDirection UP, DOWN, LEFT, or RIGHT; determines direction player travels.
	 */
	public void attempt(Direction theDirection) {
		myAdjacentDoors[theDirection.getValue()].attempt();
	}
}
