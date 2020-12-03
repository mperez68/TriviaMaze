/*
 * Map.java
 *
 * TCSS 360 - Fall myDoorDepthmyDoorDepth Project
 */
package game;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

import javax.swing.*;

import game.Room;
import game.Room.Direction;
/**
 * Encapsulating Container class. Holds a grid of all Rooms in game session, which in turn hold all Door and Question
 * objects. Includes public methods that allow manipulation 
 *
 * Version 2.0: Added method winPossible(), method win(), developed save/load format.
 * 
 * Version 3.0: Added GUI Elements.
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
 */
public class GameMap{	
					// --------------- GUI Scaling Variables --------------- //
	/**
	 * 2 Dimensional definition of screen size in pixels.
	 */
    private static final Dimension FRAME_SIZE = new Dimension(1024, 768);
    /**
     * Border depth in pixels.
     */
	private static final int PANEL_BORDER = 10;
	/**
	 * Width in pixels of the directional button box under the map.
	 */
	private static final int DIRECTION_BAR_WIDTH = 150; // min 150
	/**
	 * Height in pixels of the directional button box under the map.
	 */
	private static final int DIRECTION_BAR_HEIGHT = (DIRECTION_BAR_WIDTH / 5) * 3;
	/**
	 * Height in pixels of the both panels.
	 */
	private static final int PANEL_HEIGHT = FRAME_SIZE.height - (8 * PANEL_BORDER);
	/**
	 * Width in pixels of the map panel, which includes the map and the directional button box.
	 */
	private static final int MAP_PANEL_WIDTH = FRAME_SIZE.height - (8 * PANEL_BORDER) - DIRECTION_BAR_HEIGHT;
	/**
	 * Width in pixels of the question panel, the panel that has the questions and possible answers.
	 */
	private static final int QUESTION_PANEL_WIDTH = FRAME_SIZE.width - (4 * PANEL_BORDER) - MAP_PANEL_WIDTH;
	/**
	 * Directional button box "Point of Interest", or upper left point.
	 */
	private static final Point DIRECTIONAL_BAR_POI = new Point ((MAP_PANEL_WIDTH / 2) - (DIRECTION_BAR_WIDTH / 2), PANEL_HEIGHT - DIRECTION_BAR_HEIGHT);
	/**
	 * Width in pixels of a room as drawn in the GUI.
	 */
	private int myRoomTokenWidth;
	/**
	 * Height in pixels of a room as drawn in the GUI.
	 */
	private int myRoomTokenHeight;
	/**
	 * Width and Height in pixels of a player token as drawn in the GUI.
	 */
	private int myPlayerTokenWidth;
	/**
	 * Width or Height in pixels of a door as drawn in the GUI.
	 */
	private int myDoorDepth;
					// --------------- GUI Objects --------------- //
	/**
	 * Window frame Object.
	 */
	private JFrame myFrame;
	/**
	 * Map panel Object. Modified JPanel that stores the units as they are drawn to the screen.
	 */
	private MapPanel myMapPanel = new MapPanel(new BorderLayout());
	/**
	 * Question panel Object. Contains question text and buttons.
	 */
	private final JPanel myQuestionPanel = new JPanel(new BorderLayout());
	/**
	 * Menu Object. Contains drop down menu items.
	 */
	private final JMenu myMenu = new JMenu("File");
	/**
	 * Array of Directional Button Objects. Assigns a button for each direction the player can travel. Assigned to the Map Panel.
	 */
	private final JButton[] myDirectionalButtons = {new JButton("North"), new JButton("East"), new JButton("South"), new JButton("West")};
	/**
	 * Array of Question Button Objects. Assigns a button for question option. Assigned to the Question Panel.
	 */
	private final JButton[] myQuestionButtons = {new JButton("1)"), new JButton("2)"), new JButton("3)"), new JButton("4)")};
	// TODO labels for question output (5)
	/**
	 * Array of drop down button objects. Assigned to the JMenu object.
	 */
	private final JMenuItem[] myMenuButtons = {new JMenuItem("New Game"), new JMenuItem("Save..."),
			new JMenuItem("Load..."), new JMenuItem("Exit")};
	
					// --------------- Map Variables --------------- //
	/**
	 * 2-dimensional array containing all Room objects on the map.
	 */
	private Room myGrid[][];
	/**
	 * Width in number of rooms in the grid. Assigned in the constructor, used to initialize the map.
	 */
	private int myGridWidth;
	/**
	 * Height in number of rooms in the grid. Assigned in the constructor, used to initialize the map.
	 */
	private int myGridHeight;
	/**
	 * Current player location on the grid.
	 */
	private final Point myGridLocation = new Point(0,0);
					// --------------- Methods --------------- //
	/**
	 * Constructor for the GameMap object. Assigns scalars for GUI objects to refer to.
	 * @param theM the width in number of rooms of the map.
	 * @param theN the height in number of rooms of the map.
	 */
	public GameMap(int theM, int theN){
		myGridWidth = theM;
		myGridHeight = theN;
	}
	/**
	 * First function that should be run. Builds the GUI to a frame and generates a map of rooms. Displays map to GUI.
	 */
	public void start() {
		myFrame = new JFrame("Trivia Maze");
		myFrame.setResizable(false);
		// Viewport scalers
	
		// Menu Bar
		final JMenuBar menuBar = new JMenuBar();
		menuBar.add(myMenu);
		myFrame.setJMenuBar(menuBar);
		
		// Map Bar
		myMapPanel.setLayout(null);
		myMapPanel.setBackground(Color.DARK_GRAY);
		myMapPanel.setBounds(PANEL_BORDER, PANEL_BORDER, MAP_PANEL_WIDTH, PANEL_HEIGHT);
		
		myFrame.add(myMapPanel);
		
		// Question Bar
		myQuestionPanel.setLayout(null);
		myQuestionPanel.setBackground(Color.RED);
		myQuestionPanel.setBounds( (2 * PANEL_BORDER) + MAP_PANEL_WIDTH, PANEL_BORDER, QUESTION_PANEL_WIDTH, PANEL_HEIGHT);
		
		myFrame.add(myQuestionPanel);
		
		//Assign Bounds to directional buttons
		myDirectionalButtons[0].setBounds(DIRECTIONAL_BAR_POI.x + DIRECTION_BAR_WIDTH / 4, DIRECTIONAL_BAR_POI.y,
				DIRECTION_BAR_WIDTH / 2, DIRECTION_BAR_HEIGHT / 3);
		myDirectionalButtons[1].setBounds(DIRECTIONAL_BAR_POI.x + DIRECTION_BAR_WIDTH / 2, DIRECTIONAL_BAR_POI.y + DIRECTION_BAR_HEIGHT / 3,
				DIRECTION_BAR_WIDTH / 2, DIRECTION_BAR_HEIGHT / 3);
		myDirectionalButtons[3].setBounds(DIRECTIONAL_BAR_POI.x, DIRECTIONAL_BAR_POI.y + DIRECTION_BAR_HEIGHT / 3,
				DIRECTION_BAR_WIDTH / 2, DIRECTION_BAR_HEIGHT / 3);
		myDirectionalButtons[2].setBounds(DIRECTIONAL_BAR_POI.x + DIRECTION_BAR_WIDTH / 4, DIRECTIONAL_BAR_POI.y + DIRECTION_BAR_HEIGHT * 2 / 3,
				DIRECTION_BAR_WIDTH / 2, DIRECTION_BAR_HEIGHT / 3);
		
		// Assign buttons to layouts
		for (int i = 0; i < 4; i++) {
			// Menu Buttons -> Menu Bar
			myMenu.add(myMenuButtons[i]);
			if (i % 2 == 0) {
				myMenu.addSeparator();
			}
			
			// Directional Buttons -> Map Panel
			myDirectionalButtons[i].setLayout(null);
			myMapPanel.add(myDirectionalButtons[i]);
			
			// Question Buttons -> Question Panel
			myQuestionButtons[i].setLayout(null);
			myQuestionButtons[i].setBounds(50, 300 + (i * 100), 50, 50);
			myQuestionButtons[i].setVisible(false);
			myQuestionPanel.add(myQuestionButtons[i]);
		}

		// Draw window to screen
		myFrame.setSize(FRAME_SIZE);
		myFrame.setLayout(null);
		myFrame.setLocationRelativeTo(null);
		myFrame.setVisible(true);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		generate(myGridWidth, myGridHeight);
	}
	/**
	 * Redraws the map. Each room has it's own location, so this method calls each rooms individual draw method.
	 */
	public void draw() {
		if (!myMapPanel.hasPlayerToken()){	// IF there is no player token on the map, create one in the Northwest room.
			myMapPanel.addPlayerToken((myGridLocation.x * myRoomTokenWidth) + (myRoomTokenWidth / 2) - (myPlayerTokenWidth / 2),
					(myGridLocation.y * myRoomTokenHeight) + (myRoomTokenHeight / 2) - (myPlayerTokenWidth / 2),
					myPlayerTokenWidth, myPlayerTokenWidth);
		}
		
		// Assign Directional Buttons
		myDirectionalButtons[0].addActionListener(new ActionListener() {	//NORTH
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
            	if (myGrid[myGridLocation.x][myGridLocation.y].myAdjacentDoors[0] != null) {
            		if (myGrid[myGridLocation.x][myGridLocation.y].myAdjacentDoors[0].canPass()) {
            			moveToken(myGridLocation.x, myGridLocation.y - 1);
            			myMapPanel.repaint();
            			if (win()) {
            				// TODO reset game
            				myMapPanel.clear();
                    		System.out.println("YOU WIN!");
            			}
            			
            		} else {
	            		myGrid[myGridLocation.x][myGridLocation.y].attempt(Direction.UP, myQuestionButtons, myMapPanel);
	                    System.out.println(Direction.UP.toString() + " is " + myGrid[myGridLocation.x][myGridLocation.y].isNotLocked((Direction.UP)));
            		}
            	} else {
            		System.out.println(Direction.UP.toString() + " is DEAD END");
            	}
            }
        });
		
		myDirectionalButtons[1].addActionListener(new ActionListener() {	// EAST
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
            	if (myGrid[myGridLocation.x][myGridLocation.y].myAdjacentDoors[1] != null) {
            		if (myGrid[myGridLocation.x][myGridLocation.y].myAdjacentDoors[1].canPass()) {
            			moveToken(myGridLocation.x + 1, myGridLocation.y);
            			myMapPanel.repaint();
            			if (win()) {
            				// TODO reset game
            				myMapPanel.clear();
                    		System.out.println("YOU WIN!");
            			}
            		} else {
            			myGrid[myGridLocation.x][myGridLocation.y].attempt(Direction.RIGHT, myQuestionButtons, myMapPanel);
            			System.out.println(Direction.RIGHT.toString() + " is " + myGrid[myGridLocation.x][myGridLocation.y].isNotLocked(Direction.RIGHT));
            		}
            	} else {
            		System.out.println(Direction.RIGHT.toString() + " is DEAD END");
            	}
            }
        });
		
		myDirectionalButtons[2].addActionListener(new ActionListener() {	// SOUTH
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
            	if (myGrid[myGridLocation.x][myGridLocation.y].myAdjacentDoors[2] != null) {
            		if (myGrid[myGridLocation.x][myGridLocation.y].myAdjacentDoors[2].canPass()) {
            			moveToken(myGridLocation.x, myGridLocation.y + 1);
            			myMapPanel.repaint();
            			if (win()) {
            				// TODO reset game
            				myMapPanel.clear();
                    		System.out.println("YOU WIN!");
            			}
            		} else {
                		myGrid[myGridLocation.x][myGridLocation.y].attempt(Direction.DOWN, myQuestionButtons, myMapPanel);
                        System.out.println(Direction.DOWN.toString() + " is " + myGrid[myGridLocation.x][myGridLocation.y].isNotLocked(Direction.DOWN));
            		}
            	} else {
            		System.out.println(Direction.DOWN.toString() + " is DEAD END");
            	}
            }
        });
		
		myDirectionalButtons[3].addActionListener(new ActionListener() {	// WEST
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
            	if (myGrid[myGridLocation.x][myGridLocation.y].myAdjacentDoors[3] != null) {
            		if (myGrid[myGridLocation.x][myGridLocation.y].myAdjacentDoors[3].canPass()) {
            			moveToken(myGridLocation.x - 1, myGridLocation.y);
            			myMapPanel.repaint();
            			if (win()) {
            				// TODO reset game
            				myMapPanel.clear();
                    		System.out.println("YOU WIN!");
            			}
            		} else {
                		myGrid[myGridLocation.x][myGridLocation.y].attempt(Direction.LEFT, myQuestionButtons, myMapPanel);
                        System.out.println(Direction.LEFT.toString() + " is " + myGrid[myGridLocation.x][myGridLocation.y].isNotLocked(Direction.LEFT));
            		}
            	} else {
            		System.out.println(Direction.LEFT.toString() + " is DEAD END");
            	}
            }
		});
		
		// Calls individual draw functions within rooms, which then call draw functions within their doors.
		for (int i = 0; i < myGrid.length; i++) {
			for (int j = 0; j < myGrid[i].length; j++) {
				myGrid[i][j].draw(myMapPanel, myQuestionPanel);
			}
		}
		
		// Draws updated map to the GUI.
		myFrame.repaint();
	}
	/**
	 * Generates a new map of size M x N. If a map currently exists, it deletes it before creating a new one.
	 * @param theM Width of new map.
	 * @param theN Height of new map.
	 */
	private void generate(int theM, int theN) {
		// If map is already populated, delete current map contents.
		if (myGrid != null) {
			for (int i = 0; i < theM; i++) {
				myGrid[i] = null;
			}
			myGrid = null;
			//myMapPanel.clear();
		}
		
		// Set global GUI variables not yet assigned.
		myRoomTokenWidth = myMapPanel.getWidth() / theM;
		myRoomTokenHeight = myMapPanel.getWidth() / theN;
		myPlayerTokenWidth = myRoomTokenWidth / 5;
		myDoorDepth = (myRoomTokenWidth + myRoomTokenHeight) / 8;
		
		// Create new map.
		myGrid = new Room[theM][];
		for (int i = 0; i < theM; i++) {
			myGrid[i] = new Room[theN];
			for (int j = 0; j < theN; j++) {
				myGrid[i][j] = new Room(myRoomTokenWidth*i,myRoomTokenHeight*j, myRoomTokenWidth, myRoomTokenHeight);	// generate unlinked rooms.
			}
		}
		
		// reset player token.
		myGridLocation.setLocation(0, 0);
		// Generate list of Q/A's
		final int numEdges = ( (theM-1) * theN ) + ( (theN-1) * theM );
		Deque<Question> questionList = fillList(numEdges);
		// fill empty rooms with doors.
		Door tempDoor;
		for (int i = 0; i < theM; i++) {
			for (int j = 0; j < theN; j++) {
				if (i + 1 < theM) {	// IF room exists to the right, THEN create door
					tempDoor = new Door( (myRoomTokenWidth * i) + myRoomTokenWidth - (myDoorDepth / 4), (myRoomTokenHeight * j) + (myRoomTokenHeight / 4),
							myDoorDepth / 2, myRoomTokenHeight / 2, questionList.pop());
					myGrid[i][j].setDoor( Direction.RIGHT, tempDoor);
					myGrid[i+1][j].setDoor( Direction.LEFT, tempDoor);
				}
				if (j + 1 < theN) { // IF room exists below, THEN create door
					tempDoor = new Door( (myRoomTokenWidth * i) + (myRoomTokenWidth / 4), (myRoomTokenHeight * j) + myRoomTokenHeight - (myDoorDepth / 4),
							myRoomTokenWidth / 2, myDoorDepth / 2, questionList.pop());
					myGrid[i][j].setDoor( Direction.DOWN, tempDoor );
					myGrid[i][j+1].setDoor( Direction.UP, tempDoor );
				}
			}
		}
		draw();
	}
	/**
	 * Fills a Double Ended Queue with Question objects filled from the Database.
	 * @param theLength	
	 * @return
	 */
	private Deque<Question> fillList(int theLength) {
		
		DatabaseConnection.build();
		
		Deque<Question> questionList = new LinkedList<Question>();
		String tempAnswers[] = new String[4];
		
		Map<String, Integer> dbQuestions = DatabaseConnection.getQuestions();
		Map<String, Integer> dbAnswers;
		
		int j = 0;
		int tempCorrect = 0;
		
		if (dbQuestions.size() < theLength) {
			for (int i = 0; i < theLength; i++) {
				tempAnswers[0] = "ANS1";
				tempAnswers[1] = "ANS2";
				tempAnswers[2] = "ANS3";
				tempAnswers[3] = "ANS4";
				questionList.push(new Question("QUESTION", tempAnswers, 1));
			}
			System.out.println("Not Enough Questions in Database, filled with dummy data...");
		} else {
			for (Map.Entry<String, Integer > mapElement : dbQuestions.entrySet()) {
				dbAnswers = DatabaseConnection.getAnswers(mapElement.getValue());
				for (Map.Entry<String, Integer> questionElement : dbAnswers.entrySet()) {
					tempAnswers[j] = questionElement.getKey();
					if (questionElement.getValue() == 1) {
						tempCorrect = j;
					}
					j++;
				}
				j = 0;
				questionList.push(new Question(mapElement.getKey(), tempAnswers, tempCorrect)); 
			}
			System.out.println(theLength + " Questions Generated...");
		}
		
		
		return questionList;
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
	public void moveToken(int theX, int theY) {
		myGridLocation.setLocation(theX, theY);
		myMapPanel.movePlayer((myGridLocation.x * myRoomTokenWidth) + (myRoomTokenWidth / 2) - (myPlayerTokenWidth / 2),
				(myGridLocation.y * myRoomTokenHeight) + (myRoomTokenHeight / 2) - (myPlayerTokenWidth / 2));
		myMapPanel.repaint();
	}
	
	/**
	 * Attempt to enter the room in the direction given.
	 * @param theDirection UP, DOWN, LEFT, or RIGHT; determines direction player travels.
	 */
	public void attempt(Direction theDirection) {
		myGrid[myGridLocation.x][myGridLocation.y].attempt(theDirection, myQuestionButtons, myMapPanel);
		draw();
	}
	
	/**
	 * Determines if a solution to the trivia maze exists in its current state.
	 * @return The truth value of whether or not it is possible to win.
	 */
	public boolean winPossible() {
		Deque<Room> queue = new LinkedList<Room>();
		Set<Room> set = new HashSet<Room>();
		queue.push(myGrid[myGridLocation.x][myGridLocation.y]);
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
		return myGridLocation.x == myGrid.length - 1 && myGridLocation.y == myGrid[0].length - 1;
	}
}

