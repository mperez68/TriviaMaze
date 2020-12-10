/*
 * Door.java
 *
 * TCSS 360 - Fall 2020 Project
 */

package game;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.io.Serializable;

import game.Question;
import game.MapPanel;

/**
 * This class defines the Door object which contains a Question object. Can be prompted to present the question and attempt
 * an answer from the player. This will open or lock the door as is appropriate.
 * 
 * Version 2.0: Added method changeState(), added constructor Door(theX, theY, theAccess, theQuestion).
 *
 * @author Marc Perez (perezm68)
 * @date 11/16/2020
 * @version 1.0
 * @author Logan Crawford (crawfl5)
 * @date 11/22/2020
 * @version 2.0
 * @author Marc Perez (perezm68)
 * @date 11/27/2020
 * @version 3.0
 * @author Logan Crawford (crawfl5)
 * @date 12/9/2020
 * @version 4.0
 */
public class Door implements Serializable{
	/**
	 * Randomly generated serial ID for saving/loading state.
	 */
	private static final long serialVersionUID = -8134799949951062726L;

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
	
	private int myWidth;
	
	private int myHeight;
	/**
	 * Constructor. Requires X and Y coordinates. Sets all doors automatically to closed.
	 * @param theX Room 'X' coordinate on draw grid determined by placement on Map grid.
	 * @param theY Room 'Y' coordinate on draw grid determined by placement on Map grid.
	 * @param theQuestion The question object that must be answered to pass this door.
	 */
	public Door(int theX, int theY, int theWidth, int theHeight, Question theQuestion) {
		myX = theX;
		myY = theY;
		myWidth = theWidth;
		myHeight = theHeight;
		myAccess = AccessLevel.CLOSED;
		myQuestion = theQuestion;	
	}
	
	/**
	 * Constructor. Requires X and Y coordinates. Sets all doors automatically to predetermined access level.
	 * @param theX Room 'X' coordinate on draw grid determined by placement on Map grid.
	 * @param theY Room 'Y' coordinate on draw grid determined by placement on Map grid.
	 * @param theAccess The predetermined access level.
	 * @param theQuestion The question object that must be answered to pass this door.
	 */
	public Door(int theX, int theY, int theWidth, int theHeight, AccessLevel theAccess, Question theQuestion) {
		myX = theX;
		myY = theY;
		myWidth = theWidth;
		myHeight = theHeight;
		myAccess = theAccess;
		myQuestion = theQuestion;	
	}
	
	/**
	 * Redraws the door. refers to it's own location.
	 */
	public void draw(MapPanel theMap, JPanel theQuestions) {
		theMap.addDoor(myX, myY, myWidth, myHeight);
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
	 * Getter for the enumerated state of the door.
	 * @return The open/closed/locked state of the game.
	 */
	public AccessLevel getState() {
		return myAccess;
	}
	
	/**
	 * Used by load game method in GameMap to change the state of the door on the drawing panel
	 * to open.
	 * @param theMapPanel The panel that the game is currently using.
	 */
	public void addOpen(MapPanel theMapPanel) {
		theMapPanel.addOpen(myX + 2, myY + 2, myWidth - 4, myHeight - 4);
	}
	
	/**
	 * Used by load game method in GameMap to change the state of the door on the drawing panel
	 * to locked.
	 * @param theMapPanel The panel that the game is currently using.
	 */
	public void addLock(MapPanel theMapPanel) {
		theMapPanel.addLock(myX + 2, myY + 2, myWidth - 4, myHeight - 4);
	}
	
	/**
	 * Setter for access level of door (open, closed, locked).
	 * @param The new access level of the door.
	 */
	public void changeState(AccessLevel state) {
		myAccess = state;
	}
	
	/**
	 * Attempt to enter the door. Prompts the player with a question.
	 * @param theAnswerLabels 
	 * @param theQuestionLabel 
	 * @param theMapPanel 
	 */
	public void attempt(JButton[] theQuestionButtons, JButton[] theDirectionalButtons, JLabel theQuestionLabel, JLabel[] theAnswerLabels,
			MapPanel theMapPanel, GameMap theGameMap) {
		
		if (myAccess.equals(AccessLevel.LOCKED)) {
			System.out.println("Door Locked!");
		} else {
			myQuestion.draw(theQuestionLabel, theAnswerLabels);
			// Light up buttons, disable movement
			for (int i = 0; i < theQuestionButtons.length; i++) {
				theQuestionButtons[i].setVisible(true);
				theDirectionalButtons[i].setVisible(false);
			}
			// Assign
			theQuestionButtons[0].addActionListener(new ActionListener() {	// ANS 1
	            @Override
	            public void actionPerformed(final ActionEvent theEvent) {
					for (int i = 0; i < theQuestionButtons.length; i++) {	// enable movement again
						theDirectionalButtons[i].setVisible(true);
					}
	            	if (myQuestion.attempt(1)) {
						myAccess = AccessLevel.OPEN;
						theMapPanel.addOpen(myX + 2, myY + 2, myWidth - 4, myHeight - 4);
						System.out.println("Correct!");
					} else {
						myAccess = AccessLevel.LOCKED;
						theMapPanel.addLock(myX + 2, myY + 2, myWidth - 4, myHeight - 4);
						System.out.println("Wrong!");
						if (!theGameMap.winPossible()) {
							theMapPanel.repaint();
                    		System.out.println("YOU LOSE!");
                    		JOptionPane.showMessageDialog(null, "YOU LOSE!");
                    		theGameMap.reset();
						}
					}
	            	myQuestion.drawAnswers(theQuestionButtons, theQuestionLabel, theAnswerLabels);
	            	theMapPanel.repaint();
	            }
	        });
			theQuestionButtons[1].addActionListener(new ActionListener() {	// ANS 2
	            @Override
	            public void actionPerformed(final ActionEvent theEvent) {
					for (int i = 0; i < theQuestionButtons.length; i++) {	// enable movement again
						theDirectionalButtons[i].setVisible(true);
					}
	            	if (myQuestion.attempt(2)) {
						myAccess = AccessLevel.OPEN;
						theMapPanel.addOpen(myX + 2, myY + 2, myWidth - 4, myHeight - 4);
						System.out.println("Correct!");
					} else {
						myAccess = AccessLevel.LOCKED;
						theMapPanel.addLock(myX + 2, myY + 2, myWidth - 4, myHeight - 4);
						System.out.println("Wrong!");
						if (!theGameMap.winPossible()) {
							theMapPanel.repaint();
                    		System.out.println("YOU LOSE!");
                    		JOptionPane.showMessageDialog(null, "YOU LOSE!");
                    		theGameMap.reset();
						}
					}
	            	myQuestion.drawAnswers(theQuestionButtons, theQuestionLabel, theAnswerLabels);
	            	theMapPanel.repaint();
	            }
	        });
			theQuestionButtons[2].addActionListener(new ActionListener() {	// ANS 3
	            @Override
	            public void actionPerformed(final ActionEvent theEvent) {
					for (int i = 0; i < theQuestionButtons.length; i++) {	// enable movement again
						theDirectionalButtons[i].setVisible(true);
					}
	            	if (myQuestion.attempt(3)) {
						myAccess = AccessLevel.OPEN;
						theMapPanel.addOpen(myX + 2, myY + 2, myWidth - 4, myHeight - 4);
						System.out.println("Correct!");
					} else {
						myAccess = AccessLevel.LOCKED;
						theMapPanel.addLock(myX + 2, myY + 2, myWidth - 4, myHeight - 4);
						System.out.println("Wrong!");
						if (!theGameMap.winPossible()) {
							theMapPanel.repaint();
                    		System.out.println("YOU LOSE!");
                    		JOptionPane.showMessageDialog(null, "YOU LOSE!");
                    		theGameMap.reset();
						}
					}
	            	myQuestion.drawAnswers(theQuestionButtons, theQuestionLabel, theAnswerLabels);
	            	theMapPanel.repaint();
	            }
	        });
			theQuestionButtons[3].addActionListener(new ActionListener() {	// ANS 4
	            @Override
	            public void actionPerformed(final ActionEvent theEvent) {
					for (int i = 0; i < theQuestionButtons.length; i++) {	// enable movement again
						theDirectionalButtons[i].setVisible(true);
					}
	            	if (myQuestion.attempt(4)) {
						myAccess = AccessLevel.OPEN;
						theMapPanel.addOpen(myX + 2, myY + 2, myWidth - 4, myHeight - 4);
						System.out.println("Correct!");
					} else {
						myAccess = AccessLevel.LOCKED;
						theMapPanel.addLock(myX + 2, myY + 2, myWidth - 4, myHeight - 4);
						System.out.println("Wrong!");
						if (!theGameMap.winPossible()) {
							theMapPanel.repaint();
                    		System.out.println("YOU LOSE!");
                    		JOptionPane.showMessageDialog(null, "YOU LOSE!");
                    		theGameMap.reset();
						}
					}
	            	myQuestion.drawAnswers(theQuestionButtons, theQuestionLabel, theAnswerLabels);
	            	theMapPanel.repaint();
	            }
	        });
			
		}
	}
}
