/*
 * Map.java
 *
 * TCSS 360 - Fall 2020 Project
 */
package map;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Set;
import java.util.HashSet;

import map.Room;
import map.Room.Direction;
import map.Door.AccessLevel;
/**
 * Encapsulating Container class. Holds a grid of all Rooms in game session, which in turn hold all Door and Question
 * objects. Includes public methods that allow manipulation 
 *
 * Version 2.0: Added method winPossible(), method win(), developed save/load format.
 *
 * @author Marc Perez (perezm68)
 * @author Logan Crawford (crawfl5)
 * @date 11/22/2020
 * @version 2.0
 */
public class Map {
	/**
	 * 2-dimensional array containing all Room objects on the map.
	 */
	private Room myGrid[][];
	public Map(int theM, int theN){
		generate(theM, theN);
	}
	/**
	 * Static variable for the 'X' coordinate of the player token.
	 */
	private static int myPlayerX;
	/**
	 * Static variable for the 'Y' coordinate of the player token.
	 */
	private static int myPlayerY;
	/**
	 * Redraws the map. Each room has it's own location, so this method calls each rooms individual draw method.
	 */
	public void draw() {
		for (int i = 0; i < myGrid.length; i++) {
			for (int j = 0; j < myGrid[i].length; j++) {
				myGrid[i][j].draw();
			}
		}
		
	}
	/**
	 * Generates a new map of size M x N. If a map currently exists, it deletes it before creating a new one.
	 * @param theM Width of new map.
	 * @param theN Height of new map.
	 */
	private void generate(int theM, int theN) {
		// reset player token.
		myPlayerX = 0;
		myPlayerY = 0;
		// If map is already populated, delete current map contents.
		if (myGrid != null) {
			for (int i = 0; i < theM; i++) {
				myGrid[i] = null;
			}
			myGrid = null;
		}
		// Create new map.
		myGrid = new Room[theM][];
		for (int i = 0; i < theM; i++) {
			myGrid[i] = new Room[theN];
			// generate empty rooms.
			for (int j = 0; j < theN; j++) {
				myGrid[i][j] = new Room(i * 2,j * 2);
			}
		}
		// Generate list of Q/A's
		Deque<Question> questionList = new LinkedList<Question>();
		// TODO pull from database instead
		String tempAnswers[] = {"ans1","ans2","ans3","ans4"};
		int numEdges = ( (theM-1) * theN ) + ( (theN-1) * theM );
		System.out.println(numEdges + " Questions Generated...");
		for (int k = 0; k < numEdges; k++) {
			questionList.push(new Question("Question #" + (k + 1), tempAnswers, 1));
		}
		// END TODO
		// fill empty rooms with doors.
		Door tempDoor;
		for (int i = 0; i < theM; i++) {
			for (int j = 0; j < theN; j++) {
				if (i + 1 < theM) {	// IF room exists to the right, THEN create door
					tempDoor = new Door( (2 * i) + 1, (2 * j), questionList.pop());
					myGrid[i][j].setDoor( Direction.RIGHT, tempDoor);
					myGrid[i+1][j].setDoor( Direction.LEFT, tempDoor);
				}
				if (j + 1 < theN) { // IF room exists below, THEN create door
					tempDoor = new Door( (2 * i), (2 * j) + 1, questionList.pop());
					myGrid[i][j].setDoor( Direction.DOWN, tempDoor );
					myGrid[i][j+1].setDoor( Direction.UP, tempDoor );
				}
			}
		}
		draw();
	}
	/**
	 * Saves current map player location, rooms, doors, and questions to a text file.
	 */
	public void save(String theFileName) {
		// TODO save map to file.
		
		// save in format (a file path will need to be reported from GUI):
		// theM 
		// theN
		// myPlayerX 
		// myPlayerY
		// myGrid[0][0] RIGHT door state
		// myGrid[0][0] DOWN door state
		// myGrid[0][1] RIGHT door state
		// myGrid[0][1] DOWN door state
		// ...
		// ...
		// myGrid[theM - 1][theN - 1] RIGHT door state
		// myGrid[theM - 1][theN - 1] DOWN door state
		// 0
	}
	/**
	 * Loads a saved map from a file of the name given.
	 * @param theFileName FULL file name with extension of the file to load from.
	 */
	public void load(String theFileName) {
		// TODO load map from file.
		
		// build map based on format from save(theFileName)
		// a file path will need to be reported from GUI
	}
	/**
	 * Static function to move the player token location.
	 * @param theX New player 'X' coordinate.
	 * @param theY New player 'Y' coordinate.
	 */
	public static void moveToken(int theX, int theY) {
		// TODO animate movement in GUI
		myPlayerX = theX;
		myPlayerY = theY;
	}
	/**
	 * Attempt to enter the room in the direction given.
	 * @param theDirection UP, DOWN, LEFT, or RIGHT; determines direction player travels.
	 */
	public void attempt(Direction theDirection) {
		myGrid[myPlayerX][myPlayerY].attempt(theDirection);
		draw();
	}
	
	/**
	 * Determines if a solution to the trivia maze exists in its current state.
	 * @return The truth value of whether or not it is possible to win.
	 */
	public boolean winPossible() {
		Deque<Room> queue = new LinkedList<Room>();
		Set<Room> set = new HashSet<Room>();
		queue.push(myGrid[myPlayerX][myPlayerY]);
		Room current = null;
		int x, y;
		do {
			current = queue.pop();
			set.add(current);
			if (set.contains(myGrid[myGrid.length - 1][myGrid[0].length - 1])) {
				return true;
			}
			x = current.getX() / 2;
			y = current.getY() / 2;
			if (x + 1 < myGrid.length) {
				if (!myGrid[x][y].getDoor(Direction.RIGHT).isNotLocked() && !set.contains(myGrid[x + 1][y])) {
					queue.push(myGrid[x + 1][y]);
					set.add(myGrid[x + 1][y]);
				}
			}
			if (x - 1 >= 0) {
				if (!myGrid[x][y].getDoor(Direction.LEFT).isNotLocked() && !set.contains(myGrid[x - 1][y])) {
					queue.push(myGrid[x - 1][y]);
					set.add(myGrid[x - 1][y]);
				}
			}
			if (y + 1 < myGrid[0].length) {
				if (!myGrid[x][y].getDoor(Direction.DOWN).isNotLocked() && !set.contains(myGrid[x][y + 1])) {
					queue.push(myGrid[x][y + 1]);
					set.add(myGrid[x][y + 1]);
				}
			}
			if (y - 1 >= 0) {
				if (!myGrid[x][y].getDoor(Direction.UP).isNotLocked() && !set.contains(myGrid[x][y - 1])) {
					queue.push(myGrid[x][y - 1]);
					set.add(myGrid[x][y - 1]);
				}
			}
		} while(!queue.isEmpty());
		return false;
	}
	
	/**
	 * Determines if the game has been won (player has reached last room).
	 * @return Truth value of if the game is won.
	 */
	public boolean win() {
		return myPlayerX == myGrid.length - 1 && myPlayerY == myGrid[0].length - 1;
	}
}
