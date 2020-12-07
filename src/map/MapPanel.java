package map;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;
import java.io.*;

public class MapPanel extends JPanel implements Serializable{
    
    /**  A generated serial version UID for object Serialization. */
    private static final long serialVersionUID = 8452917670991316606L;
    
    final List<Rectangle> myRooms = new LinkedList<Rectangle>();

    final List<Rectangle> myDoors = new LinkedList<Rectangle>();
    
    Rectangle myPlayerToken;
    
    public MapPanel(BorderLayout theBorderLayout) {
        super(theBorderLayout);
        myPlayerToken = new Rectangle(0, 0, 50, 50);
    }
    
    public void addRoom(int theX, int theY, int theWidth, int theHeight) {
    	myRooms.add(new Rectangle(theX,theY,theWidth,theHeight));
    }
    
    public void addDoor(int theX, int theY, int theWidth, int theHeight) {
    	myDoors.add(new Rectangle(theX,theY,theWidth,theHeight));
    }
    
    public void movePlayer(int theX, int theY, int theWidth, int theHeight) {
    	myPlayerToken = new Rectangle(theX, theY, theWidth, theHeight);
    }
    
    /**
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
        	tempGraphics.setPaint(Color.WHITE);
        	tempGraphics.fill(myDoors.get(i));
        }

    	tempGraphics.setPaint(Color.RED);
    	tempGraphics.fill(myPlayerToken);
    }

}