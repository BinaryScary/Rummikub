package core;

import java.io.File;

import junit.framework.TestCase;

public class AddTest extends TestCase {
	public void testAddTileRun() {
		File file = new File("src/test/resources/test9a");
		Game g = new Game();
		g.init(file,false);
		g.getPlayers().get(0).initialMeld = false;
		g.start();
		assertNotNull(g.getTable());
	}
	public void testAddTileSet() {
		File file = new File("src/test/resources/test9b");
		Game g = new Game();
		g.init(file,false);
		g.getPlayers().get(0).initialMeld = false;
		g.start();
		assertNotNull(g.getTable());
	}
	public void testAddTileSeveral() {
		File file = new File("src/test/resources/test9c");
		Game g = new Game();
		g.init(file,false);
		g.getPlayers().get(0).initialMeld = false;
		g.start();
		assertNotNull(g.getTable());
	}
}
