
import java.awt.EventQueue;

import map.Map;

public class TriviaMaze {

	public static void main(String[] theArgs) {
		EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
        		Map m = new Map(5,5);
        		m.start();
            }
        });
	}
}
