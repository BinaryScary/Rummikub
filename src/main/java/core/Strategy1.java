package core;

import java.util.ArrayList;

public class Strategy1 extends Player {
	
	private CLI ui;

	@Override
	protected void updateHand(Game update) {
		hand = update.getH1();
	}

	@Override
	protected void play() {
		ui = new CLI();
		char choice;
		Tile temp;
		
		ui.message(hand.toString());
			temp = pile.deal();
			hand.addTileToHand(temp);
			ui.message("Player 2 has " + temp + ".");
			
			playTable() == -1; 
				temp = pile.deal();
				hand.addTileToHand(temp);
				ui.message("Plyer 2 drew a " + temp + ".");

	}
	
	/*private int playTable() {
		char choice;
		String meldStr;
		Meld meld;
		ArrayList<Meld> invalidMelds;
		//does this need a deep copy?
		Table tempTable = new Table(table.getTable()); 
		Meld tempMeld;
		Tile tempTile;
		ArrayList<Tile> removedTiles = new ArrayList<Tile>();
		int meldIndex = 0;

		while(true) {
			ui.message("Current Table: ");
			ui.message(tempTable.toString());
			ui.message("Current Hand: ");
			ui.message(hand.toString());

			while(!hasChar(choice = ui.response("Create Meld, Add to Meld, Split Meld, or End?(c,a,s,e): "), new char[]{'c','a','s','e'})) { 
				ui.message("*ERROR choice invalid");
			}
		}*/
		
	}
}
