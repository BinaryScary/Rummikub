package core;

import java.io.File;

public class GameStart {

	public static void main(String[] args) {
		File file = new File("src/test/resources/testInit");
		Game g = new Game();
		g.init(file);
//		Game g = new Game();
//		g.init();
//		g.start();
		System.out.println("done");
	}

}
