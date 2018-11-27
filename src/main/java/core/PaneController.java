package core;

import javafx.scene.layout.Pane;

public class PaneController implements UserInterface {
	Pane pane;
	public PaneController(Pane p){
        pane = p;
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
