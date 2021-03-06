/*
 * Room.java
 *
 * TCSS 360 - Fall 2020 Project
 */

package game;

import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.io.Serializable;

import game.Door;

/**
 * The Room Object. This will contain Door objects that contain Question objects that can be called to determine map
 * traversals. 
 *
 * Version 2.0: Added method getDoor(), method getX(), method getY().
 * 
 * @author Marc Perez (perezm68)
 * @author Logan Crawford (crawfl5)
 * @date 11/27/2020
 * @version 2.0
 */
public class Room implements Serializable {
	/**
	 * Randomly generated serial ID for saving/loading state.
	 */
	private static final long serialVersionUID = 3232937702176484868L;

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
	 * Room 'X' coordinate on panel.
	 */
	private int myX;
	/**
	 * Room 'Y' coordinate on panel.
	 */
	private int myY;
	/**
	 * Width in pixels of this room.
	 */
	private int myWidth;
	/**
	 * Height in pixels of this room.
	 */
	private int myHeight;
	
	private Point myGridLocation;
	/**
	 * Constructor. Requires X and Y coordinates.
	 * @param theX X for the Point of Interest on the map panel.
	 * @param theY Y for the Point of Interest on the map panel.
	 * @param theGridLocation Grid location of this room.
	 */
	public Room(int theX, int theY, int theWidth, int theHeight, Point theGridLocation) {
		myGridLocation = theGridLocation;
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
	public void setDoor(Direction theDirection, Door theDoor) {
		if (myAdjacentDoors[theDirection.getValue()] == null) {
			myAdjacentDoors[theDirection.getValue()] = theDoor;
		}
	}
	
	/**
	 * Getter for the instance room's door of the specified direction.
	 * @return Returns the instance of the door. Returns null if there is no door in the specified direction.
	 */
	public Door getDoor(Direction theDirection) {
		return myAdjacentDoors[theDirection.getValue()];
	}
	
	/**
	 * Getter for x-coordinate value.
	 * @return x-coordinate value.
	 */
	public int getX() {
		return myGridLocation.x;
	}
	
	/**
	 * Getter for y-coordinate value.
	 * @return y-coordinate value.
	 */
	public int getY() {
		return myGridLocation.y;
	}
	
	/**
	 * Attempt to enter the room in the direction given.
	 * @param theDirection UP, DOWN, LEFT, or RIGHT; determines direction player travels.
	 * @param theAnswerLabels 
	 * @param theQuestionLabel 
	 * @param theMapPanel 
	 */
	public void attempt(Direction theDirection, JButton[] theQuestionButtons, JButton[] theDirectionalButtons,
			JLabel theQuestionLabel, JLabel[] theAnswerLabels, MapPanel theMapPanel, GameMap theGameMap) {
		myAdjacentDoors[theDirection.getValue()].attempt(theQuestionButtons, theDirectionalButtons, theQuestionLabel,
				theAnswerLabels, theMapPanel, theGameMap);
	}
}
