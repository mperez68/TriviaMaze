/*
 * Door.java
 *
 * TCSS 360 - Fall 2020 Project
 */

package map;
import map.Question;
//TODO import GUI singleton

/**
 * This class defines the Door object which contains a Question object. Can be prompted to present the question and attempt
 * an answer from the player. This will open or lock the door as is appropriate.
 * 
 * @author Marc Perez (perezm68)
 * @date 11/16/2020
 * @version 1.0
 *
 */
public class Door {
	/**
	 * Enumerated constants to ensure input is always valid.
	 */
	public enum AccessLevel {OPEN, CLOSED, LOCKED}
	/**
	 * Value to mark which entry level of open, closed, or locked this door is.
	 */
	private AccessLevel myAccess;
	/**
	 * The Question object containing the question/answer data to pass this door.
	 */
	private Question myQuestion;
	/**
	 * X Coordinate, relative to (0,0) point.
	 */
	private int myX;
	/**
	 * Y Coordinate, relative to (0,0) point.
	 */
	private int myY;
	/**
	 * Constructor. Requires X and Y coordinates. Sets all doors automatically to closed.
	 * @param theX Room 'X' coordinate on draw grid determined by placement on Map grid.
	 * @param theY Room 'Y' coordinate on draw grid determined by placement on Map grid.
	 * @param theQuestion The question object that must be answered to pass this door.
	 */
	public Door(int theX, int theY, Question theQuestion) {
		myX = theX;
		myY = theY;
		myAccess = AccessLevel.CLOSED;
		myQuestion = theQuestion;	
	}
	/**
	 * Redraws the door. refers to it's own location.
	 */
	public void draw() {
		// TODO implement as GUI draw
		System.out.printf("[" + myAccess.toString() + " Door @ (" + myX + ", " + myY + ")]");
	}
	/**
	 * returns true if player can pass through this door and False if it is closed or locked.
	 * @return
	 */
	public boolean canPass() {
		boolean passFlag = false;
		if (myAccess.equals(AccessLevel.OPEN)) {
			passFlag = true;
		}
		return passFlag;
	}
	/**
	 * Getter for if the door is locked.
	 * @return Truth value of if the door is locked.
	 */
	public boolean isNotLocked() {
		boolean lockedFlag = false;
		if (myAccess.equals(AccessLevel.LOCKED)) {
			lockedFlag = true;
		}
		return lockedFlag;
	}
	/**
	 * Attempt to enter the door. Prompts the player with a question.
	 */
	public void attempt() {
		if (myAccess.equals(AccessLevel.LOCKED)) {
			System.out.println("Door Locked!");
		} else {
			myQuestion.draw();
			// TODO handle as setting listener
			int input = (int)(Math.random() * 4);
			if (myQuestion.attempt(input)) {
				myAccess = AccessLevel.OPEN;
				System.out.println("Correct!");
			} else {
				myAccess = AccessLevel.LOCKED;
				System.out.println("Wrong!");
			}
			myQuestion.drawAnswers();
		}
	}
}
