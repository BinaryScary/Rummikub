package core;

import java.io.File;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
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
	Pane board;
	Pane score;
	private final Object PAUSE_KEY = new Object();
	private String eventResult;
	private Tile eventTile;
	private Meld eventMeld;
	private ArrayList<Pane> rPane = new ArrayList<Pane>();
	
	public GUI(Pane p){
        pane = p;
	}
	
	public File getFile() {
		//TODO getFile
		return null;
	}

	public void highlight(Pane pane) {
		pane.setBorder(new Border(new BorderStroke(Color.YELLOW, 
            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
	}

	public void unHighlight(Pane pane) {
		pane.setBorder(null);
	}
	
	public ArrayList<Tile> tileQuery(){
		ArrayList<Tile> buf = new ArrayList<Tile>();
		displayTiles();
		for(Node n: board.getChildren()) {
			n.setOnMouseClicked(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent arg0) {
					System.out.println(n.getUserData());
					eventTile = (Tile) n.getUserData();
					buf.add(eventTile);
				}
			});
		}
		
		confirmButton();
		pause();
		return buf;
	}
	
	public void displayTiles() {
		int posX = 0;
		int posY = 0;
		Pane tempPane;
		
		for(int c = 0; c < 4; c++) {
			for(int v = 0; v < 13; v++) {
				Tile tile = new Tile(Tile.colour.values()[c], Tile.value.values()[v]);
				tempPane = tileGraphic(tile);
				tempPane.setTranslateY(height*0.10 + (posY * height * 0.10));
				tempPane.setTranslateX((width*0.265) + (posX * width * 0.050));

				board.getChildren().add(tempPane);
				posX++;
				if(posX % 14 == 0 && posX != 0) {
					posY++;
					posX = 0;
				}
			}
		}

	}

	public void boardInit(Stage primaryStage) {
        pane = new Pane();
//        Button nextButton = new Button("Done");
//        ObservableList<String> options = FXCollections.observableArrayList("StrategyHuman", "Strategy1", "Strategy2");
//        ComboBox stratBox = new ComboBox(options);
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
//        hand.setPrefSize(width * 0.73, height * 0.24);

        board = new Pane();
//        board.setPrefSize(width * 0.73, height * 0.65);

        control = new Pane();
//        control.setPrefSize(width * 0.22, height * 0.65);
        
        score = new Pane();
//        TextField nameField = new TextField();
//		Label enterName = new Label("ENTER NAME:");
//		enterName.relocate(400, 10);
//		enterName.setPrefSize(150, 25);
//
//		nameField.relocate(500, 10);
//		nameField.setPrefSize(150, 25);
//		nameField.setText("Player #");
		
//		stratBox.setPromptText("Choose Player's Strategy:");
//		stratBox.relocate(260, 10);
//		stratBox.setPrefSize(200, 25);
        
		//CRITICAL BE VERY CAREFUL the order of adds affects z scale
        pane.getChildren().add(title);
        pane.getChildren().add(scoreBg);
        pane.getChildren().add(controlBg);
        pane.getChildren().add(handBg);
        pane.getChildren().add(boardBg);
        pane.getChildren().add(messageBg);
        pane.getChildren().add(score);
        pane.getChildren().add(message);
        pane.getChildren().add(hand);
        pane.getChildren().add(board);
        pane.getChildren().add(control);
//        pane.getChildren().add(enterName);
//        pane.getChildren().add(nameField);
        primaryStage.setTitle("Rummikub");
        primaryStage.setScene(new Scene(pane, width, height));
        
        //Abdous
//        nextButton.setOnMousePressed(new EventHandler<MouseEvent>() {
//			public void handle(MouseEvent me) {
//				if (stratBox.getSelectionModel().getSelectedItem() != null) {
//					String name = nameField.getText();
//					String strat = stratBox.getSelectionModel().getSelectedItem().toString();
//					nameField.setEditable(false);
//					nextButton.setDisable(true);
//				}
//
//			}
//		});
        
	}
	
	

	@Override
	public void message(String mes) {
		message.setText(mes);
	}

	public void displayHand(Hand tiles) {
		int posX = 0;
		int posY = 0;
		hand.getChildren().clear();
		Pane tempPane;
		for(Tile t : tiles.getTiles()) {
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
	
	@Override
	public void displayTable(Table tiles) {
		int posX = 0;
		int posY = 0;
		Double gap = 0.0;
		board.getChildren().clear();
		Pane tempPane;
		boolean recent = false;
		
		for(Pane p : rPane) {
			unHighlight(p);
		}
		rPane.clear();

		for(Meld m : tiles.getTable()) {
			if(tiles.getRecent().contains(m)) {
				recent = true;
			}else {
				recent = false;
			}
			for(Tile t : m.getMeld()) {
				tempPane = meldGraphic(t,m);
				tempPane.setTranslateY(height*0.10 + (posY * height * 0.10));
				tempPane.setTranslateX((width*0.265) + (posX * width * 0.050) + gap);
				if(recent == true) {
					highlight(tempPane);
					rPane.add(tempPane);
				}

//				Rectangle(width * 0.25,height * 0.08,width * 0.73, height * 0.65);

				board.getChildren().add(tempPane);
				posX++;
				if(posX % 12 == 0 && posX != 0) {
					posY++;
					posX = 0;
					gap = 0.0;
				}
			}
			gap += width * 0.025;
		}
	}
	
	public void displayScore(ArrayList<Hand> arr) {
		score.getChildren().clear();
		int i = 1;
		for(Hand h : arr) {
			Text t = new Text(width * 0.03, height * 0.75 + i * height * 0.05, "Player "+ i + " Tiles: " + h.getTiles().size());
			t.setFont(new Font(width * height * 0.0000225));
			score.getChildren().add(t);
			i++;
		}
//		(width * 0.02,height * 0.74, width * 0.22, height * 0.24);
	}
	
	private Pane meldGraphic(Tile tile, Meld m) {
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
		ArrayList<Object> temp = new ArrayList<Object>();
		temp.add(m);
		temp.add(tile);
		gTile.setUserData(temp);
		
		return gTile;
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
		gTile.setUserData(tile);
		
		return gTile;
	}
	
	public Meld getMeld() {
		for(Node n: board.getChildren()) {
			n.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@SuppressWarnings("unchecked")
				public void handle(MouseEvent arg0) {
					eventMeld = (Meld) ((ArrayList<Object>) n.getUserData()).get(0);
					//TODO possibly highlight node
					resume();
				}
			});
		}

		confirmButton();
		pause();
		if(eventResult == "Return") return null;
		return eventMeld;
	}
	
	public ArrayList<Object> getTableTileMeld() {
		ArrayList<Object> temp = new ArrayList<Object>();
		for(Node n: board.getChildren()) {
			n.setOnMouseClicked(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent arg0) {
					System.out.println(n.getUserData());
					temp.add((Meld) ((ArrayList<Object>) n.getUserData()).get(0));
					temp.add((Tile) ((ArrayList<Object>) n.getUserData()).get(1));

					//TODO possibly highlight node
					resume();
				}
			});
		}
		
		confirmButton();
		pause();
		if(eventResult == "Return") return null;

		return temp;
	}

	public Tile getTableTile() {
		for(Node n: board.getChildren()) {
			n.setOnMouseClicked(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent arg0) {
					System.out.println(n.getUserData());
					eventTile = (Tile) ((ArrayList<Object>) n.getUserData()).get(1);
					//TODO possibly highlight node
					resume();
				}
			});
		}
		
		confirmButton();
		pause();
		if(eventResult == "Return") return null;

		return eventTile;
	}

	public Meld getTableTiles() {
		Meld res = new Meld();
		for(Node n: board.getChildren()) {
			n.setOnMouseClicked(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent arg0) {
					System.out.println(n.getUserData());
					eventTile = (Tile) ((ArrayList<Object>) n.getUserData()).get(1);
					//TODO possibly highlight node
					resume();
				}
			});
		}
		
		confirmButton();
		while(true) {
			pause();
			if(eventResult == "Return") break;

			//watch out for unitentional null adds
			if(res.indexOf(eventTile) == -1) {
				res.add(eventTile);
			}
		}

		return res;
	}

	public Meld getTiles() {
		Meld res = new Meld();
		ArrayList<Node> cache = new ArrayList<Node>();
		for(Node n: hand.getChildren()) {
			n.setOnMouseClicked(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent arg0) {
					System.out.println(n.getUserData());
					eventTile = (Tile) n.getUserData();
					//TODO possibly highlight node
					cache.add(n);
					n.setStyle("-fx-border-style: dashed");
					resume();
				}
			});
		}
		
		confirmButton();
		while(true) {
			pause();
			if(eventResult == "Return") break;

			if(res.indexOf(eventTile) == -1) {
				res.add(eventTile);
			}
		}
		for(Node n: cache) {
			n.setStyle("-fx-border-style: none");
		}
		return res;
	}
	
	public void confirmButton() {

		control.getChildren().clear();
		final Button button = new Button("Return");
		button.setMinWidth(width * 0.220);
		button.setMinHeight(height * 0.100);
		button.setLayoutX(width * 0.022);
		button.setLayoutY(height * 0.150);

		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				GUI.this.eventResult = button.getText();
				resume();
			}
		});
		control.getChildren().add(button);
	}
	
	public int query(String mes, String[] choices) {
		int counter = 0;
		control.getChildren().clear();
        Text qStr = new Text(width * 0.022, height * 0.100, mes);
        qStr.setFont(new Font(width * height * 0.00002));
        qStr.setWrappingWidth(width * 0.215);
        control.getChildren().add(qStr);
        
        for(String str: choices) {
        	final Button button = new Button(str);
        	button.setWrapText(true);
        	button.setMinWidth(width * 0.220);
        	button.setMinHeight(height * 0.100);
        	button.setLayoutX(width * 0.022);
        	button.setLayoutY(height * 0.180 + (counter * height * 0.100));
        	
        	button.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					GUI.this.eventResult = button.getText();
					resume();
				}
			});
        	
        	
			control.getChildren().add(button);
			counter++;
        }
        pause();
        control.getChildren().clear();

	
//        Rectangle controlBg = new Rectangle(width * 0.02,height * 0.08,width * 0.22, height * 0.65);
//        controlBg.setFill(Color.TAN);
        
        counter = 0;
        for(String str: choices) {
        	if(str == eventResult) {
        		return counter;
        	}
        	counter++;
        }

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
		return 0;
	}

	@Override
	public void playerTurn(char player) {
		
	}

	@Override
	public void displayMeld(Meld m) {
		
	}

	@Override
	public void outcome(char out) {
		
	}

}
