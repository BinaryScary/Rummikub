package core;


import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class GUI implements UserInterface {
	//MVC cannot be accomplished with javafx as to profs email
	private double width = 1000;
	private double height = 1000;
	Text message;
	Pane pane;
	Pane handTiles;
	public GUI(Pane p){
        pane = p;
	}

	public void boardInit(Stage primaryStage) {
        pane = new Pane();
        pane.setStyle("-fx-background-color: grey;");
        Rectangle board = new Rectangle(width * 0.25,height * 0.08,width * 0.73, height * 0.65);
        board.setFill(Color.SADDLEBROWN);
        Rectangle controls = new Rectangle(width * 0.02,height * 0.08,width * 0.22, height * 0.65);
        controls.setFill(Color.TAN);
        Rectangle scores = new Rectangle(width * 0.02,height * 0.74, width * 0.22, height * 0.24);
        scores.setFill(Color.TAN);
        Rectangle hand = new Rectangle(width * 0.25, height * 0.74, width * 0.73, height * 0.24);
        hand.setFill(Color.TAN);
        Rectangle messages = new Rectangle(width * 0.25, height * 0.65, width * 0.73, height * 0.08);
        messages.setFill(Color.TAN);
        
        Text title = new Text(width * 0.02, height * 0.07, "Rummikub");
        title.setFont(new Font(width * height * 0.00006));

        message = new Text(width * 0.26, height * 0.68, "");
        message.setFont(new Font(width * height * 0.000020));
        message.setWrappingWidth(width * 0.72);
        
        handTiles = new Pane();
        handTiles.setPrefSize(1000, 1000);
        
        pane.getChildren().add(title);
        pane.getChildren().add(scores);
        pane.getChildren().add(controls);
        pane.getChildren().add(hand);
        pane.getChildren().add(board);
        pane.getChildren().add(messages);
        pane.getChildren().add(message);
        pane.getChildren().add(handTiles);
        primaryStage.setTitle("Rummikub");
        primaryStage.setScene(new Scene(pane, width, height));
	}

	@Override
	public void message(String mes) {
		message.setText(mes);
	}

	public void displayHand(Hand mes) {
		int posX = 0;
		int posY = 0;
		handTiles.getChildren().clear();
		Pane tempPane;
		for(Tile t : mes.getTiles()) {
			tempPane = tileGraphic(t);
			tempPane.setTranslateY(height*0.75 + (posY * height * 0.10));
			tempPane.setTranslateX((width*0.265) + (posX * width * 0.050));
			System.out.println(tempPane.getTranslateX());

			handTiles.getChildren().add(tempPane);
			posX++;
			if(posX == 14) {
				posX = 0;
				posY = 1;
			}
		}
	}
	
	private Pane tileGraphic(Tile tile) {
		StackPane gTile = new StackPane();
		Rectangle r = new Rectangle(width * 0.045, height * 0.09);
		r.setFill(Color.CORNSILK);
		gTile.getChildren().add(r);
		
		Text t = new Text(0, 0, Integer.toString(tile.getValue().getVal()));
        t.setFont(new Font(width * height * 0.00003));
        switch (tile.getColour().getCol()) {
        case 'R': 
        	t.setFill(Color.RED);
			break;
        case 'G': 
        	t.setFill(Color.GREEN);
			break;
        case 'B': 
        	t.setFill(Color.BLUE);
			break;
        case 'O': 
        	t.setFill(Color.ORANGE);
			break;
        }
		gTile.getChildren().add(t);
		
		return gTile;
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
