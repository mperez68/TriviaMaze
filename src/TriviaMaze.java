import java.awt.EventQueue;

import map.GameMap;

public class TriviaMaze {

	public static void main(String[] theArgs) {
		EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
        		GameMap m = new GameMap(5,5);
        		m.start();
            }
        });
	}
}
