package core;

import java.io.File;

import junit.framework.TestCase;

public class PlayTest extends TestCase {
	public void testPlayRunSet() {
		File file = new File("src/test/resources/test8ab");
		Game g = new Game();
		g.init(file);
		g.start();
		
		assertNotNull(g.getTable());
	}

	public void testPlayDouble() {
		File file = new File("src/test/resources/test8cd");
		Game g = new Game();
		g.init(file);
		g.start();
		
		assertNotNull(g.getTable());
	}

	public void testPlayMix() {
		File file = new File("src/test/resources/test8e");
		Game g = new Game();
		g.init(file);
		g.start();
		
		assertNotNull(g.getTable());
	}

	public void testPlayThreeDraw() {
		File file = new File("src/test/resources/test14b");
		Game g = new Game();
		g.init(file,false);
		g.start();
		assertEquals(g.getPlayers().get(2),g.winner);
	}

	public void testPlayThreeWin() {
		File file = new File("src/test/resources/test12b");
		Game g = new Game();
		g.init(file,false);
		g.start();
		assertEquals(g.winner,g.getPlayers().get(1));
	}

	public void testPlayThreeMeldSub() {
		File file = new File("src/test/resources/test11b");
		Game g = new Game();
		g.init(file,false);
		g.start();
		assertNotNull(g.getTable());
	}
	
	public void testPlayThreeMeld() {
		File file = new File("src/test/resources/test11a");
		Game g = new Game();
		g.init(file,false);
		g.start();
		assertNotNull(g.getTable());
	}

	public void testPlaySeveralMeldSub() {
		File file = new File("src/test/resources/test10c");
		Game g = new Game();
		g.init(file);
		g.start();
		
		assertNotNull(g.getTable());
		
	}

	public void testPlaySeveralMeld() {
		File file = new File("src/test/resources/test10b");
		Game g = new Game();
		g.init(file,false);
		g.start();
		
		assertNotNull(g.getTable());
		
	}

	public void testPlayOneMeld() {
		File file = new File("src/test/resources/test10a");
		Game g = new Game();
		g.init(file);
		g.start();
		
		assertNotNull(g.getTable());
	}
}
