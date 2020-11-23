import map.Map;
import map.Room.Direction;

public class TriviaMaze {

	public static void main(String[] theArgs) {
		Map m = new Map(3,3);
		//System.out.println(m.winPossible());
		m.attempt(Direction.RIGHT);
		m.attempt(Direction.RIGHT);
		Map.moveToken(2, 2);
		//System.out.println(m.winPossible());
		m.attempt(Direction.UP);
	}
}
