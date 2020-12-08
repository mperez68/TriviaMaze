package map;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;
/**
 * Modified Panel Object for the Trivia Maze project.
 * 
 * This modification creates lists of rectangle objects that are drawn to the field when the inherited repaint() method is called. This alteration
 * doubles as making the Panel a container for the lists of objects it draws.
 * 
 * @author Marc Perez
 * @version 1.0
 *
 */
public class MapPanel extends JPanel implements Serializable{
    
    /**  A generated serial version UID for object Serialization. */
    private static final long serialVersionUID = 8452917670991316606L;
    /**
     * List of room objects drawn to the map as Rectangles. (Gray)
     */
    private final List<Rectangle> myRooms = new LinkedList<Rectangle>();
    /**
     * List of door objects drawn to the map as Rectangles. (Light Gray)
     */
    private final List<Rectangle> myDoors = new LinkedList<Rectangle>();
    /**
     * List of Open Symbol objects drawn to the map as Rectangles. (White) Overlays door object.
     */
    private final List<Rectangle> myOpenSymbols = new LinkedList<Rectangle>();
    /**
     * List of Locked Symbol objects drawn to the map as Rectangles. (Red) Overlays door object.
     */
    private final List<Rectangle> myLockedSymbols = new LinkedList<Rectangle>();
    /**
     * Rectangle representing the player token.
     */
    private Rectangle myPlayerToken;
    /**
     * Inherited constructor.
     * @param theBorderLayout Border Layout Object of frame.
     */
    public MapPanel(BorderLayout theBorderLayout) {
        super(theBorderLayout);
        myPlayerToken = new Rectangle(0,0,2,2);	// "empty" character token
    }
    /**
     *  Adds a room to the list drawn to the map.
     * @param theX X coordinate in pixels of Point of Interest.
     * @param theY Y coordinate in pixels of Point of Interest.
     * @param theWidth Width in pixels of the object.
     * @param theHeight Height in pixels of the object.
     */
    public void addRoom(int theX, int theY, int theWidth, int theHeight) {
    	myRooms.add(new Rectangle(theX,theY,theWidth,theHeight));
    }
    /**
     *  Adds a door to the list drawn to the map.
     * @param theX X coordinate in pixels of Point of Interest.
     * @param theY Y coordinate in pixels of Point of Interest.
     * @param theWidth Width in pixels of the object.
     * @param theHeight Height in pixels of the object.
     */
    public void addDoor(int theX, int theY, int theWidth, int theHeight) {
    	myDoors.add(new Rectangle(theX,theY,theWidth,theHeight));
    }
    /**
     *  Adds an open door symbol to the list drawn to the map.
     * @param theX X coordinate in pixels of Point of Interest.
     * @param theY Y coordinate in pixels of Point of Interest.
     * @param theWidth Width in pixels of the object.
     * @param theHeight Height in pixels of the object.
     */
    public void addOpen(int theX, int theY, int theWidth, int theHeight) {
    	myOpenSymbols.add(new Rectangle(theX,theY,theWidth,theHeight));
    }
    /**
     *  Adds a locked door symbol to the list drawn to the map.
     * @param theX X coordinate in pixels of Point of Interest.
     * @param theY Y coordinate in pixels of Point of Interest.
     * @param theWidth Width in pixels of the object.
     * @param theHeight Height in pixels of the object.
     */
    public void addLock(int theX, int theY, int theWidth, int theHeight) {
    	myLockedSymbols.add(new Rectangle(theX,theY,theWidth,theHeight));
    }
    /**
     * Sets the player token drawn to the map.
     * @param theX X coordinate in pixels of Point of Interest.
     * @param theY Y coordinate in pixels of Point of Interest.
     * @param theWidth Width in pixels of the object.
     * @param theHeight Height in pixels of the object.
     */
    public void addPlayerToken(int theX, int theY, int theWidth, int theHeight) {
    	myPlayerToken = new Rectangle(theX, theY, theWidth, theHeight);
    }
    /**
     * Moves the player token drawn to the map.
     * @param theX X coordinate in pixels of Point of Interest.
     * @param theY Y coordinate in pixels of Point of Interest.
     */
    public void movePlayer(int theX, int theY) {
    	myPlayerToken.setLocation(theX, theY);
    }
    /**
     * Indicates if a player token currently exists.
     * @return Truth value of if a player token is currently assigned.
     */
    public boolean hasPlayerToken() {
    	boolean flag = true;
    	if (myPlayerToken.contains(1, 1)) {
    		flag = false;
    	}
    	return flag;
    }
    /**
     * Resets map. Cannot be undone.
     */
    public void clear() {
    	myRooms.clear();
    	myDoors.clear();
    	myOpenSymbols.clear();
    	myLockedSymbols.clear();
    	myPlayerToken = new Rectangle(0,0,2,2);	// "empty" character token
    	repaint();
    }
    
    /**
     * Paints rectangle objects as map.
     * @param theGraphics The graphics context to use for painting.
     */
    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        Graphics2D tempGraphics = (Graphics2D) theGraphics;
        
        for (int i = 0; i < myRooms.size(); i++) {
        	tempGraphics.setPaint(Color.GRAY);
        	tempGraphics.fill(myRooms.get(i));
        }
        
        for (int i = 0; i < myDoors.size(); i++) {
        	tempGraphics.setPaint(Color.LIGHT_GRAY);
        	tempGraphics.fill(myDoors.get(i));
        }
        
        for (int i = 0; i < myOpenSymbols.size(); i++) {
        	tempGraphics.setPaint(Color.WHITE);
        	tempGraphics.fill(myOpenSymbols.get(i));
        }
        
        for (int i = 0; i < myLockedSymbols.size(); i++) {
        	tempGraphics.setPaint(Color.RED);
        	tempGraphics.fill(myLockedSymbols.get(i));
        }

    	tempGraphics.setPaint(Color.BLUE);
    	if (myPlayerToken != null) {
    		tempGraphics.fill(myPlayerToken);
    	}
    }

}