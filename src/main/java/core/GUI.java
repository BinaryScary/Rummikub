package core;


import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.paint.Paint;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class GUI implements UserInterface {
	//MVC cannot be accomplished with javafx as to profs email
	private double width = 1000;
	private double height = 1000;
	Pane pane;
	public GUI(Pane p){
        pane = p;
	}

	public void boardInit(Stage primaryStage) {
        pane = new Pane();
        pane.setStyle("-fx-background-color: grey;");
        Rectangle board = new Rectangle(width * 0.25,height * 0.08,width * 0.73, height * 0.65);
        board.setFill(Color.SADDLEBROWN);
        Rectangle scores = new Rectangle(width * 0.02,height * 0.08,width * 0.22, height * 0.65);
        scores.setFill(Color.TAN);
        Rectangle controls = new Rectangle(width * 0.02,height * 0.74, width * 0.22, height * 0.24);
        controls.setFill(Color.TAN);
        Rectangle hand = new Rectangle(width * 0.25, height * 0.74, width * 0.73, height * 0.24);
        hand.setFill(Color.TAN);
        
        Text title = new Text(width * 0.02, height * 0.07, "Rummikub");
        title.setFont(new Font(width * height * 0.00006));
        
        pane.getChildren().add(title);
        pane.getChildren().add(scores);
        pane.getChildren().add(controls);
        pane.getChildren().add(board);
        pane.getChildren().add(hand);
        primaryStage.setTitle("Rummikub");
        primaryStage.setScene(new Scene(pane, width, height));
	}

	@Override
	public void message(String mes) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public char response(String mes) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void playerTurn(char player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayMeld(Meld m) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayTable(Table t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void outcome(char out) {
		// TODO Auto-generated method stub
		
	}
}
