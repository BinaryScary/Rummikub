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
		//to showcase a certian ability in gui enter the file
		File file = new File("src/test/resources/testInit");
		Game g = new Game();
		g.init(primaryStage, file);
//		g.init(primaryStage);
//		g.init(file);
		g.start();
	}

}
