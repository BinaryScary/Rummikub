package core;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
	Pane hand;
	Pane control;
	private final Object PAUSE_KEY = new Object();
	
	public GUI(Pane p){
        pane = p;
	}

	public void boardInit(Stage primaryStage) {
        pane = new Pane();
        pane.setStyle("-fx-background-color: grey;");
        Rectangle boardBg = new Rectangle(width * 0.25,height * 0.08,width * 0.73, height * 0.65);
        boardBg.setFill(Color.SADDLEBROWN);
        Rectangle controlBg = new Rectangle(width * 0.02,height * 0.08,width * 0.22, height * 0.65);
        controlBg.setFill(Color.TAN);
        Rectangle scoreBg = new Rectangle(width * 0.02,height * 0.74, width * 0.22, height * 0.24);
        scoreBg.setFill(Color.TAN);
        Rectangle handBg = new Rectangle(width * 0.25, height * 0.74, width * 0.73, height * 0.24);
        handBg.setFill(Color.TAN);
        Rectangle messageBg = new Rectangle(width * 0.25, height * 0.65, width * 0.73, height * 0.08);
        messageBg.setFill(Color.TAN);
        
        Text title = new Text(width * 0.02, height * 0.07, "Rummikub");
        title.setFont(new Font(width * height * 0.00006));

        message = new Text(width * 0.26, height * 0.68, "");
        message.setFont(new Font(width * height * 0.000020));
        message.setWrappingWidth(width * 0.72);
        
        hand = new Pane();
        hand.setPrefSize(width * 0.73, height * 0.24);

        control = new Pane();
        control.setPrefSize(width * 0.22, height * 0.65);
        
        pane.getChildren().add(title);
        pane.getChildren().add(scoreBg);
        pane.getChildren().add(controlBg);
        pane.getChildren().add(handBg);
        pane.getChildren().add(boardBg);
        pane.getChildren().add(messageBg);
        pane.getChildren().add(message);
        pane.getChildren().add(hand);
        pane.getChildren().add(control);
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
		hand.getChildren().clear();
		Pane tempPane;
		for(Tile t : mes.getTiles()) {
			tempPane = tileGraphic(t);
			tempPane.setTranslateY(height*0.75 + (posY * height * 0.10));
			tempPane.setTranslateX((width*0.265) + (posX * width * 0.050));
//			System.out.println(tempPane.getTranslateX());

			hand.getChildren().add(tempPane);
			posX++;
			if(posX == 14) {
				posX = 0;
				posY = 1;
			}
		}
	}
	
	private Pane tileGraphic(Tile tile) {
		//TODO possibly add event handler here for card choosing
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

	public int query(String mes, String[] choices) {
		Button button;
		int location = 0;
		control.getChildren().clear();
        Text qStr = new Text(width * 0.022, height * 0.100, mes);
        qStr.setFont(new Font(width * height * 0.00002));
        qStr.setWrappingWidth(width * 0.215);
        control.getChildren().add(qStr);
        //do we need a query string?
        
        for(String str: choices) {
        	button = new Button(str);
        	button.setWrapText(true);
        	button.setMinWidth(width * 0.220);
        	button.setMinHeight(height * 0.100);
        	button.setLayoutX(width * 0.022);
        	button.setLayoutY(height * 0.150 + (location * height * 0.100));
        	
        	button.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					System.out.println("test");
					resume();
				}
			});
        	
        	
			control.getChildren().add(button);
			location++;
        }
        pause();

	
//        Rectangle controlBg = new Rectangle(width * 0.02,height * 0.08,width * 0.22, height * 0.65);
//        controlBg.setFill(Color.TAN);

		return 0;
	}

	private void pause() {
	    Platform.enterNestedEventLoop(PAUSE_KEY);
	}

	private void resume() {
	    Platform.exitNestedEventLoop(PAUSE_KEY, null);
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
