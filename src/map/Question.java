/*
 * Question.java
 *
 * TCSS 360 - Fall 2020 Project
 */

package map;
// TODO import GUI singleton

import javax.swing.JButton;

/**
 * This class defines the Question object. The Question object is intended to contain the question and answer data
 * in the form of a question, a list of possible answers, and an indicator of the correct answer. It assumes an ability
 * to write to the GUI by way of accessing a singleton class that contains the objects manipulating the game window.
 * 
 * @author Marc Perez (perezm68)
 * @date 11/16/2020
 * @version 1.0
 */
public class Question {
	/**
	 * Container for the question text.
	 */
	private String myQuestion;
	/**
	 * Container for all displayed possible answers.
	 */
	private String[] myAnswers;
	/**
	 * Array reference point that indicates the correct answer of the list above.
	 */
	private int myCorrect;
	/**
	 * simple constructor for Question. Requires all contained data to construct.
	 * @param theQuestion question text.
	 * @param theAnswers all displayed possible answers.
	 * @param theCorrect reference point that indicates the correct answer of the array given.
	 */
	public Question(String theQuestion, String[] theAnswers, int theCorrect) {
		myQuestion = theQuestion;
		myAnswers = theAnswers;
		myCorrect = theCorrect;
	}
	/**
	 * Draws the contained data to the viewport as a question prompt.
	 */
	public void draw() {
		// TODO temporary console printing. Replace with GUI implementation.
		System.out.println(myQuestion);
		for (int i = 0; i < myAnswers.length; i++) {
			System.out.println(Integer.toString(i+1) + ") " + myAnswers[i]);
		}
	}
	/**
	 * Draws the contained data to the viewport as an answer confirmation.
	 * @param theQuestionButtons 
	 */
	public void drawAnswers(JButton[] theQuestionButtons) {
		for (int i = 0; i < theQuestionButtons.length; i++) {
			theQuestionButtons[i].removeActionListener(theQuestionButtons[i].getActionListeners()[0]);
			theQuestionButtons[i].setVisible(false);
		}
		System.out.println(myQuestion);
		for (int i = 0; i < myAnswers.length; i++) {
			if (i == myCorrect) {
				System.out.print(">>>");
			}
			System.out.print(Integer.toString(i+1) + ") " + myAnswers[i]);
			if (i == myCorrect) {
				System.out.println("<<<");
			} else {
				System.out.println();
			}
		}
	}
	/**
	 * Compares input in the form of how it is displayed on screen. 
	 * @param theInput an integer corresponding to the DISPLAYED number of the answer.
	 * @return Returns whether or not the answer was correct or false.
	 */
	public boolean attempt(int theInput) {
		boolean correctFlag = false;
		if (theInput - 1 == myCorrect) {
			correctFlag = true;
		}
		return correctFlag;
	}
}
