package gametests;

import static org.junit.jupiter.api.Assertions.*;

import javax.swing.JButton;
import javax.swing.JLabel;

import org.junit.jupiter.api.Test;

import game.Door;
import game.GameMap;
import game.MapPanel;
import game.Question;
import game.Door.AccessLevel;

class DoorTests {
	private final String q = "Q1";
	private final String[] ans = {"A", "B","C", "D"};
	private final int cor = 1;
	
	private final Question testQ = new Question(q, ans, cor);
	
	private final int xCoord = 100;
	private final int yCoord = 100;
	
	private final int width = 50;
	private final int height = 50;
	
	private final JButton[] theQuestionButtons = {new JButton(""), new JButton(""), new JButton(""), new JButton("")};
	private final JButton[] theDirectionalButtons = {new JButton(""), new JButton(""), new JButton(""), new JButton("")};
	private final JLabel theQuestionLabel = new JLabel("");
	private final JLabel[] theAnswerLabels = {new JLabel(""), new JLabel(""), new JLabel(""), new JLabel("")};
	private final MapPanel theMapPanel = new MapPanel(null);
	private final GameMap theGameMap = new GameMap(1,1);

	@Test
	void testDoorIntIntIntIntQuestion() {
		theGameMap.start();
		constructorTestCorrect(new Door(xCoord, yCoord, width, height, testQ));
		constructorTestFalse(new Door(xCoord, yCoord, width, height, testQ));
	}

	@Test
	void testDoorIntIntIntIntAccessLevelQuestion() {
		theGameMap.start();
		assertEquals(new Door(xCoord, yCoord, width, height, AccessLevel.LOCKED, testQ).getState(), AccessLevel.LOCKED, "Door not detecting as locked.");
		
		constructorTestCorrect(new Door(xCoord, yCoord, width, height, AccessLevel.CLOSED, testQ));
		constructorTestFalse(new Door(xCoord, yCoord, width, height, AccessLevel.CLOSED, testQ));
	}
	
	private void constructorTestCorrect(Door theDoor) {
		assertEquals(theDoor.getState(), AccessLevel.CLOSED, "Not constructing at correct access level.");
		theDoor.attempt(theQuestionButtons, theDirectionalButtons, theQuestionLabel, theAnswerLabels, theMapPanel, theGameMap);
		theQuestionButtons[cor].doClick();
		assertEquals(theDoor.getState(), AccessLevel.OPEN, "Not opening on correct answer.");
	}
	
	private void constructorTestFalse(Door theDoor) {
		assertEquals(theDoor.getState(), AccessLevel.CLOSED, "Not constructing at correct access level.");
		theDoor.attempt(theQuestionButtons, theDirectionalButtons, theQuestionLabel, theAnswerLabels, theMapPanel, theGameMap);
		theQuestionButtons[cor + 1].doClick();
		assertEquals(theDoor.getState(), AccessLevel.LOCKED, "Not locking on wrong answer.");
	}

	@Test
	void testCanPass() {
		assertTrue(new Door(xCoord, yCoord, width, height, AccessLevel.OPEN, testQ).canPass(), "Not returning as passable.");
		assertFalse(new Door(xCoord, yCoord, width, height, AccessLevel.LOCKED, testQ).canPass(), "falsely returning as passable.");
	}

	@Test
	void testIsNotLocked() {
		assertTrue(new Door(xCoord, yCoord, width, height, AccessLevel.LOCKED, testQ).isNotLocked(), "Falsely returning as unlocked.");
		assertFalse(new Door(xCoord, yCoord, width, height, AccessLevel.OPEN, testQ).isNotLocked(), "Falsely returning as locked.");
	}

	@Test
	void testGetState() {
		assertEquals(new Door(xCoord, yCoord, width, height, testQ).getState(), AccessLevel.CLOSED, "Not retrieving correct access level.");
	}

	@Test
	void testChangeState() {
		final Door testDoor = new Door(xCoord, yCoord, width, height, testQ);
		assertEquals(testDoor.getState(), AccessLevel.CLOSED, "Not retrieving correct access level at construction.");
		testDoor.changeState(AccessLevel.OPEN);
		assertEquals(testDoor.getState(), AccessLevel.OPEN, "Not retrieving correct access level after changing to open.");
		testDoor.changeState(AccessLevel.LOCKED);
		assertEquals(testDoor.getState(), AccessLevel.LOCKED, "Not retrieving correct access level after changing to locked.");
		testDoor.changeState(AccessLevel.CLOSED);
		assertEquals(testDoor.getState(), AccessLevel.CLOSED, "Not retrieving correct access level after changing to closed.");
	}

	@Test
	void testAttempt() {
		final Door testDoor = new Door(xCoord, yCoord, width, height, testQ);
		
		assertEquals(testDoor.getState(), AccessLevel.CLOSED, "Not constructing at correct access level.");
		testDoor.attempt(theQuestionButtons, theDirectionalButtons, theQuestionLabel, theAnswerLabels, theMapPanel, theGameMap);
		theQuestionButtons[cor].doClick();
		assertEquals(testDoor.getState(), AccessLevel.OPEN, "Not opening on correct answer.");
	}

}
