import map.Map;
import map.Room.Direction;

public class TriviaMaze {

	public static void main(String[] theArgs) {
		Map m = new Map(3,3);
		m.attempt(Direction.RIGHT);
		m.attempt(Direction.RIGHT);
		Map.moveToken(2, 2);
		m.attempt(Direction.UP);
	}
}
