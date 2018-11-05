package core;

import junit.framework.TestCase;

public class Strategy1Test extends TestCase {
	public void testHasSet() {
		Tile[] tArr = {new Tile(Tile.colour.RED, Tile.value.TEN),new Tile(Tile.colour.BLUE, Tile.value.TEN),new Tile(Tile.colour.GREEN, Tile.value.TEN),new Tile(Tile.colour.ORANGE, Tile.value.TEN),new Tile(Tile.colour.RED, Tile.value.TEN),new Tile(Tile.colour.BLUE, Tile.value.TEN),new Tile(Tile.colour.GREEN, Tile.value.TEN),new Tile(Tile.colour.ORANGE, Tile.value.TEN)};
		Hand h = new Hand(tArr);
		Strategy1 p = new Strategy1(h);

		System.out.println(p.playableSets());
		assertNotNull(p.playableSets());
	}

}
