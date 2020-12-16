package gametests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.junit.jupiter.api.Test;

import game.Door;
import game.GameMap;
import game.MapPanel;
import game.Question;
import game.Room;
import game.Door.AccessLevel;
import game.Room.Direction;

class RoomTests {
	private final String q = "Q1";
	private final String[] ans = {"A", "B","C", "D"};
	private final int cor = 1;
	
	private final Question testQ = new Question(q, ans, cor);
	
	private final int xCoord = 100;
	private final int yCoord = 100;
	private Point gridPoint = new Point(1,1);
	
	private final int width = 50;
	private final int height = 50;
	
	private final JButton[] theQuestionButtons = {new JButton(""), new JButton(""), new JButton(""), new JButton("")};
	private final JButton[] theDirectionalButtons = {new JButton(""), new JButton(""), new JButton(""), new JButton("")};
	private final JLabel theQuestionLabel = new JLabel("");
	private final JLabel[] theAnswerLabels = {new JLabel(""), new JLabel(""), new JLabel(""), new JLabel("")};
	private final MapPanel theMapPanel = new MapPanel(null);
	private final GameMap theGameMap = new GameMap(2,2);

	@Test
	void testRoom() {
		 final String expected = "[Room @ (" + xCoord + ", " + yCoord + ")] >>"
		 		+ " [ [CLOSED Door @ (" + xCoord + ", " + yCoord + ")][CLOSED Door @ (" + xCoord + ", " + yCoord + ")] ]";
		 
		Room testRoom = new Room(xCoord, yCoord, width, height, gridPoint);
		testRoom.setDoor(Direction.DOWN, new Door(xCoord, yCoord, width, height, testQ));
		testRoom.setDoor(Direction.RIGHT, new Door(xCoord, yCoord, width, height, testQ));

		final PrintStream temp = System.out;
		final ByteArrayOutputStream output = new ByteArrayOutputStream();
		System.setOut(new PrintStream(output));
		
		testRoom.draw(theMapPanel, new JPanel());

		System.setOut(temp);

		assertTrue(expected.equals(output.toString().trim()), "Room info not constructing correctly.");
	}

	@Test
	void testIsNotLocked() {
		Room testRoom = new Room(xCoord, yCoord, width, height, gridPoint);
		testRoom.setDoor(Direction.DOWN, new Door(xCoord, yCoord, width, height, testQ));

		assertFalse(testRoom.isNotLocked(Direction.DOWN), "Not returning as locked correctly.");
	}

	@Test
	void testSetDoor() {
		Room testRoom = new Room(xCoord, yCoord, width, height, gridPoint);

		assertNull(testRoom.getDoor(Direction.DOWN), "Not returning null correctly.");
		
		testRoom.setDoor(Direction.DOWN, new Door(xCoord, yCoord, width, height, testQ));

		assertNotNull(testRoom.getDoor(Direction.DOWN), "Returning null incorrectly.");
	}

	@Test
	void testGetDoor() {
		Room testRoom = new Room(xCoord, yCoord, width, height, gridPoint);
		Door testDoor = new Door(xCoord, yCoord, width, height, testQ);
		testRoom.setDoor(Direction.DOWN, testDoor);
		
		assertEquals(testDoor, testRoom.getDoor(Direction.DOWN), "Door not retrieving correctly.");
	}

	@Test
	void testGetX() {
		assertEquals(gridPoint.x, new Room(xCoord, yCoord, width, height, gridPoint).getX(), "X Coordinate not retrieving correctly.");
	}

	@Test
	void testGetY() {
		assertEquals(gridPoint.y, new Room(xCoord, yCoord, width, height, gridPoint).getY(), "Y Coordinate not retrieving correctly.");
	}

	@Test
	void testAttempt() {
		Room testRoom = new Room(xCoord, yCoord, width, height, gridPoint);
		Door testDoor = new Door(xCoord, yCoord, width, height, testQ);
		testRoom.setDoor(Direction.DOWN, testDoor);
		
		assertEquals(testRoom.getDoor(Direction.DOWN).getState(), AccessLevel.CLOSED, "Not constructing at correct access level.");
		testRoom.attempt(Direction.DOWN, theQuestionButtons, theDirectionalButtons, theQuestionLabel, theAnswerLabels, theMapPanel, theGameMap);
		theQuestionButtons[cor].doClick();
		assertEquals(testRoom.getDoor(Direction.DOWN).getState(), AccessLevel.OPEN, "Not opening on correct answer.");
	}

}
