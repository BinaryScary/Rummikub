package core;

import java.io.File;

import javafx.application.Application;
import javafx.stage.Stage;

public class GameStart extends Application {

    public static void main(String[] args) {
        launch(args);
    }

	@Override
	public void start(Stage primaryStage) throws Exception {
		File file = new File("src/test/resources/test5.gui");
//		File file = new File("src/test/resources/test4c.gui");
//		File file = new File("src/test/resources/test4b2.gui");
//		File file = new File("src/test/resources/test4b1.gui");
//		File file = new File("src/test/resources/testInit.gui");
		Game g = new Game();
		g.init(primaryStage, file);
//		g.init(primaryStage);
//		g.init(file);
		g.start();
	}

}
