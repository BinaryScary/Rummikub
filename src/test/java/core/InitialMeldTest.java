package core;

import java.io.File;

import junit.framework.TestCase;

public class InitialMeldTest extends TestCase {

	public void testPlay30() {
		File file = new File("src/test/resources/test4a1");
		Game g = new Game();
		g.init(file);
		g.start();
		
		assertNotNull(g.getTable());
	}

	public void testPlayMore30() {
		File file = new File("src/test/resources/test4a2");
		Game g = new Game();
		g.init(file);
		g.start();
		
		assertNotNull(g.getTable());
	}

	public void testFirstMoveWin() {
		File file = new File("src/test/resources/test4c");
		Game g = new Game();
		g.init(file);
		g.start();
		
		assertNotNull(g.winner);
	}
}
