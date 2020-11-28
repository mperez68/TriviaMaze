/*
 * Room.java
 *
 * TCSS 360 - Fall 2020 Project
 */

package map;

import javax.swing.JButton;
import javax.swing.JPanel;

import map.Door;
//TODO import GUI singleton

/**
 * The Room Object. This will contain Door objects that contain Question objects that can be called to determine map
 * traversals. 
 * 
 * @author Marc Perez (perezm68)
 * @date 11/16/2020
 * @version 1.0
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
	
	private int myWidth;
	
	private int myHeight;
	/**
	 * Constructor. Requires X and Y coordinates.
	 * @param theX Room 'X' coordinate on draw grid determined by placement on Map grid.
	 * @param theY Room 'Y' coordinate on draw grid determined by placement on Map grid.
	 */
	public Room(int theX, int theY, int theWidth, int theHeight) {
		myX = theX;
		myY = theY;
		myWidth = theWidth;
		myHeight = theHeight;
		myAdjacentDoors = new Door[4];
	}
	/**
	 * Redraws the room. refers to it's own location and calls each adjacent door, which also has it's own location.
	 */
	public void draw(MapPanel theMap, JPanel theQuestions) {
		// Console Confirmation
		System.out.print("[Room @ (" + myX + ", " + myY + ")] >> [ ");
		// Drawing this room
		
		theMap.addRoom(myX + 5, myY + 5, myWidth - 10, myHeight - 10);
		
		for (int i = 0; i < 4; i++) {
			if (myAdjacentDoors[i] != null){
				myAdjacentDoors[i].draw(theMap, theQuestions);
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
	 * Attempt to enter the room in the direction given.
	 * @param theDirection UP, DOWN, LEFT, or RIGHT; determines direction player travels.
	 */
	public void attempt(Direction theDirection, JButton[] theQuestionButtons) {
		myAdjacentDoors[theDirection.getValue()].attempt(theQuestionButtons);
	}
}
