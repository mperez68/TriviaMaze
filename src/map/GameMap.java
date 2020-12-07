/*
 * Map.java
 *
 * TCSS 360 - Fall doorDepthdoorDepth Project
 */
package map;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.HashSet;

import javax.swing.*; 
import java.io.*;
//import javax.swing.BorderFactory;
//import javax.swing.BoxLayout;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JMenu;
//import javax.swing.JMenuBar;
//import javax.swing.JMenuItem;
//import javax.swing.JPanel;
//import javax.swing.JRadioButtonMenuItem;

import map.Room;
import map.Room.Direction;
/**
 * Encapsulating Container class. Holds a grid of all Rooms in game session, which in turn hold all Door and Question
 * objects. Includes public methods that allow manipulation 
 *
 * Version 2.0: Added method winPossible(), method win(), developed save/load format.
 *
 * @author Marc Perez (perezm68)
<<<<<<< HEAD
 * @date 11/16/doorDepthdoorDepth
 * @version 1.0
=======
 * @author Logan Crawford (crawfl5)
 * @date 11/22/2020
 * @version 2.0
>>>>>>> master
 */
public class GameMap implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2884583485369202961L;

	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;

    private static final Dimension FRAME_SIZE = new Dimension(960, 720);
	
	private JFrame myFrame;
	
	private MapPanel myMapPanel = new MapPanel(new BorderLayout());
	
	private final JPanel myQuestionPanel = new JPanel(new BorderLayout());
	
	private final JMenu myMenu = new JMenu("File");
	
	private final JButton[] myDirectionalButtons = {new JButton("North"), new JButton("East"), new JButton("South"), new JButton("West")};
	
	private final JMenuItem[] myMenuButtons = {new JMenuItem("New Game"), new JMenuItem("Save..."),
			new JMenuItem("Load..."), new JMenuItem("Exit")};
	
	private final JButton[] myQuestionButtons = {new JButton("1)"), new JButton("2)"), new JButton("3)"), new JButton("4)")};
	/**
	 * 2-dimensional array containing all Room objects on the map.
	 */
	private Room myGrid[][];
	
	private int myMapWidth;
	private int myMapHeight;
	/**
	 * 
	 * @param theM
	 * @param theN
	 */
	public GameMap(int theM, int theN){
		myPlayerX = 0;
		myPlayerY = 0;
		myMapWidth = theM;
		myMapHeight = theN;
	}
	
	public void start() {
		myFrame = new JFrame("Trivia Maze");
		// Viewport scalers
			// Map Panel and Directional Buttons
		final int dirWidth = 150; // min 150
		final int dirHeight = (dirWidth / 5) * 3;
		
		final int mapBorder = 10;
		final int mapWidth = FRAME_SIZE.height - (8 * mapBorder) - dirHeight;
		final int mapHeight = FRAME_SIZE.height - (8 * mapBorder);
		
		Point dir = new Point ((mapWidth / 2) - (dirWidth) / 2, mapHeight - dirHeight);
			// Question Panel and Question Buttons
		final int questionBorder = mapBorder;
		final int questionWidth = FRAME_SIZE.width - (4 * mapBorder) - mapWidth;
		final int questionHeight = mapHeight;
	
		// Menu Bar
		final JMenuBar menuBar = new JMenuBar();
		menuBar.add(myMenu);
		myFrame.setJMenuBar(menuBar);
		
		// Map Bar
		myMapPanel.setLayout(null);
		myMapPanel.setBackground(Color.DARK_GRAY);
		myMapPanel.setBounds(mapBorder, mapBorder, mapWidth, mapHeight);
		
		myFrame.add(myMapPanel);
		
		// Question Bar
		myQuestionPanel.setLayout(null);
		myQuestionPanel.setBackground(Color.RED);
		myQuestionPanel.setBounds( (2 * questionBorder) + mapWidth, questionBorder, questionWidth, questionHeight);
		
		myFrame.add(myQuestionPanel);
		
		//Assign Bounds to directional buttons
		myDirectionalButtons[0].setBounds(dir.x + dirWidth / 4, dir.y, dirWidth / 2, dirHeight / 3);
		myDirectionalButtons[1].setBounds(dir.x + dirWidth / 2, dir.y + dirHeight / 3, dirWidth / 2, dirHeight / 3);
		myDirectionalButtons[3].setBounds(dir.x, dir.y + dirHeight / 3, dirWidth / 2, dirHeight / 3);
		myDirectionalButtons[2].setBounds(dir.x + dirWidth / 4, dir.y + dirHeight * 2 / 3, dirWidth / 2, dirHeight / 3);
		
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
		
		generate(myMapWidth, myMapHeight);
	}
	/**
	 * Variable for the 'X' coordinate of the player token.
	 */
	private int myPlayerX;
	/**
	 * Variable for the 'Y' coordinate of the player token.
	 */
	private int myPlayerY;
	
	private final Point myGridLocation = new Point(0,0);
	/**
	 * Redraws the map. Each room has it's own location, so this method calls each rooms individual draw method.
	 */
	public void draw() {
		myMapPanel.movePlayer(myPlayerX - myMapPanel.getWidth() / myGrid.length / 10,
				myPlayerY - myMapPanel.getHeight() / myGrid.length / 10,
				myMapPanel.getWidth() / myGrid.length / 5,
				myMapPanel.getHeight() / myGrid.length / 5);
		// Assign Directional Buttons
		myDirectionalButtons[0].addActionListener(new ActionListener() {	//NORTH
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
            	if (myGrid[myGridLocation.x][myGridLocation.y].myAdjacentDoors[0] != null) {
            		if (myGrid[myGridLocation.x][myGridLocation.y].myAdjacentDoors[0].canPass()) {
            			myGridLocation.setLocation(myGridLocation.x, myGridLocation.y - 1);
            			myMapPanel.movePlayer( ( myPlayerX - myMapPanel.getWidth() / myGrid.length / 10 ) + (myMapPanel.getWidth() / myGrid.length) * myGridLocation.x,
            					myPlayerY - myMapPanel.getHeight() / myGrid.length / 10 + (myMapPanel.getWidth() / myGrid.length) * myGridLocation.y,
            					myMapPanel.getWidth() / myGrid.length / 5,
            					myMapPanel.getHeight() / myGrid.length / 5);
            			myMapPanel.repaint();
            			
            		} else {
	            		myGrid[myGridLocation.x][myGridLocation.y].attempt(Direction.UP, myQuestionButtons);
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
            			myGridLocation.setLocation(myGridLocation.x + 1, myGridLocation.y);
            			myMapPanel.movePlayer( ( myPlayerX - myMapPanel.getWidth() / myGrid.length / 10 ) + (myMapPanel.getWidth() / myGrid.length) * myGridLocation.x,
            					myPlayerY - myMapPanel.getHeight() / myGrid.length / 10 + (myMapPanel.getWidth() / myGrid.length) * myGridLocation.y,
            					myMapPanel.getWidth() / myGrid.length / 5,
            					myMapPanel.getHeight() / myGrid.length / 5);
            			myMapPanel.repaint();
            		} else {
            			myGrid[myGridLocation.x][myGridLocation.y].attempt(Direction.RIGHT, myQuestionButtons);
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
            			myGridLocation.setLocation(myGridLocation.x, myGridLocation.y + 1);
            			myMapPanel.movePlayer( ( myPlayerX - myMapPanel.getWidth() / myGrid.length / 10 ) + (myMapPanel.getWidth() / myGrid.length) * myGridLocation.x,
            					myPlayerY - myMapPanel.getHeight() / myGrid.length / 10 + (myMapPanel.getWidth() / myGrid.length) * myGridLocation.y,
            					myMapPanel.getWidth() / myGrid.length / 5,
            					myMapPanel.getHeight() / myGrid.length / 5);
            			myMapPanel.repaint();
            		} else {
                		myGrid[myGridLocation.x][myGridLocation.y].attempt(Direction.DOWN, myQuestionButtons);
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
            			myGridLocation.setLocation(myGridLocation.x - 1, myGridLocation.y);
            			myMapPanel.movePlayer( ( myPlayerX - myMapPanel.getWidth() / myGrid.length / 10 ) + (myMapPanel.getWidth() / myGrid.length) * myGridLocation.x,
            					myPlayerY - myMapPanel.getHeight() / myGrid.length / 10 + (myMapPanel.getWidth() / myGrid.length) * myGridLocation.y,
            					myMapPanel.getWidth() / myGrid.length / 5,
            					myMapPanel.getHeight() / myGrid.length / 5);
            			myMapPanel.repaint();
            		} else {
                		myGrid[myGridLocation.x][myGridLocation.y].attempt(Direction.LEFT, myQuestionButtons);
                        System.out.println(Direction.LEFT.toString() + " is " + myGrid[myGridLocation.x][myGridLocation.y].isNotLocked(Direction.LEFT));
            		}
            	} else {
            		System.out.println(Direction.LEFT.toString() + " is DEAD END");
            	}
            }
		});
		for (int i = 0; i < myGrid.length; i++) {
			for (int j = 0; j < myGrid[i].length; j++) {
				myGrid[i][j].draw(myMapPanel, myQuestionPanel);
			}
		}
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
		}
		// Set bounds for new rooms.
		final int roomWidth = myMapPanel.getWidth() / theM;
		final int roomHeight = myMapPanel.getWidth() / theN;
		// Create new map.
		myGrid = new Room[theM][];
		for (int i = 0; i < theM; i++) {
			myGrid[i] = new Room[theN];
			// generate empty rooms.
			for (int j = 0; j < theN; j++) {
				myGrid[i][j] = new Room(roomWidth*i,roomHeight*j, roomWidth, roomHeight);
			}
		}
		// reset player token.
		myPlayerX = roomWidth / 2;
		myPlayerY = roomHeight / 2;
		// Generate list of Q/A's
		// TODO pull from database instead
		int numEdges = ( (theM-1) * theN ) + ( (theN-1) * theM );
		Deque<Question> questionList = fillList(numEdges);
		// fill empty rooms with doors.
		
		final int doorDepth = (roomWidth + roomHeight) / 8;
		
		Door tempDoor;
		for (int i = 0; i < theM; i++) {
			for (int j = 0; j < theN; j++) {
				if (i + 1 < theM) {	// IF room exists to the right, THEN create door
					tempDoor = new Door( (roomWidth * i) + roomWidth - (doorDepth / 4), (roomHeight * j) + (roomHeight / 4),
							doorDepth / 2, roomHeight / 2, questionList.pop());
					myGrid[i][j].setDoor( Direction.RIGHT, tempDoor);
					myGrid[i+1][j].setDoor( Direction.LEFT, tempDoor);
				}
				if (j + 1 < theN) { // IF room exists below, THEN create door
					tempDoor = new Door( (roomWidth * i) + (roomWidth / 4), (roomHeight * j) + roomHeight - (doorDepth / 4),
							roomWidth / 2, doorDepth / 2, questionList.pop());
					myGrid[i][j].setDoor( Direction.DOWN, tempDoor );
					myGrid[i][j+1].setDoor( Direction.UP, tempDoor );
				}
			}
		}
		draw();
	}
	private Deque<Question> fillList(int theNumEdges) {
		
		DatabaseConnection.build();
		
		Deque<Question> questionList = new LinkedList<Question>();
		
		//List<String> tempAnswerList = new LinkedList<String>();
		String tempAnswers[] = {"ans1","ans2","ans3","ans4"};
		
		Map<String, Integer> dbQuestions = DatabaseConnection.getQuestions();
		Map<String, Integer> dbAnswers;
		
		int j = 0;
		int tempCorrect = 0;
		for (Map.Entry<String, Integer> mapElement : dbQuestions.entrySet()) {
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
		
		
//		for (int k = 0; k < theNumEdges; k++) {
//			questionList.push(new Question("Question #" + (k + 1), tempAnswers, 1));
//		}
		
		System.out.println(theNumEdges + " Questions Generated...");
		return questionList;
	}

	/**
	 * Saves current map player location, rooms, doors, and questions to a text file.
	 */
	public void save(String theFileName) {
		// TODO save map to file.
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("save1.txt"));
			out.writeObject(myGrid);
			out.writeObject(myMapPanel);
			out.writeObject(myMapWidth);
			out.writeObject(myMapHeight);
			out.writeObject(myPlayerX);
			out.writeObject(myPlayerY);
			out.flush();
			out.close();
		} catch(Exception e) {
			System.out.println(e);
			System.exit(0);
		}
	}
	/**
	 * Loads a saved map from a file of the name given.
	 * @param theFileName FULL file name with extension of the file to load from.
	 */
	public void load(String theFileName) {
		// TODO load map from file.
		
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("save1.txt"));
			myGrid = (Room[][])in.readObject();
			myMapPanel = (MapPanel)in.readObject();
			myMapWidth = (int)in.readObject();
			myMapHeight = (int)in.readObject();
			myPlayerX = (int)in.readObject();
			myPlayerY = (int)in.readObject();
			in.close();
			myMapPanel.movePlayer(myPlayerX - myMapPanel.getWidth() / myGrid.length / 10,
					myPlayerY - myMapPanel.getHeight() / myGrid.length / 10,
					myMapPanel.getWidth() / myGrid.length / 5,
					myMapPanel.getHeight() / myGrid.length / 5);
			myFrame.repaint();
		} catch(Exception e) {
			System.out.println(e);
			System.exit(0);
		}
	}
	
	public void exitGame() {
		System.exit(0);
	}
	
	/**
	 * Static function to move the player token location.
	 * @param theX New player 'X' coordinate.
	 * @param theY New player 'Y' coordinate.
	 */
	public void moveToken(int theX, int theY) {
		// TODO animate movement in GUI
		myPlayerX = theX;
		myPlayerY = theY;
		myMapPanel.movePlayer(myPlayerX - myMapPanel.getWidth() / myGrid.length / 10,
				myPlayerY - myMapPanel.getHeight() / myGrid.length / 10,
				myMapPanel.getWidth() / myGrid.length / 5,
				myMapPanel.getHeight() / myGrid.length / 5);
		myMapPanel.repaint();
	}
	/**
	 * Attempt to enter the room in the direction given.
	 * @param theDirection UP, DOWN, LEFT, or RIGHT; determines direction player travels.
	 */
	public void attempt(Direction theDirection) {
		myGrid[myPlayerX][myPlayerY].attempt(theDirection, myQuestionButtons);
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