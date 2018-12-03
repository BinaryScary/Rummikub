package core;

import java.util.ArrayList;

public class StrategyHuman extends Player {

//	private CLI ui;
	private GUI ui;

	public StrategyHuman() {
		super();
		name = "human";
	}

	public StrategyHuman(GUI ui) {
		super();
		name = "human";
		this.ui = ui;
	}

	@Override
	protected void play() {
		newMelds = new ArrayList<Meld>();
		modMelds = new ArrayList<Meld>();
//		char choice;
		int choice;
		Tile temp;
		
		hand.setSort();
		ui.displayHand(hand);
		ui.displayTable(table);

//		System.out.println(ui.query("test message this is a question", new String[]{"hey", "test button2", "one more"}));
		//TODO skipped

//		while(!hasChar(choice = ui.response("Play Table, or Draw Tile?(p,d): "), new char[]{'p','d'})) {
//			ui.message("*ERROR choice invalid");
//		}
		
		choice = ui.query("Play Table or Draw Tile?", new String[] {"Table", "Tile"});
		
		if(choice == 1) {
			temp = draw();
			if(temp != null) {
				ui.message("You drew a " + temp + ".");
				hand.setSort();
				ui.displayHand(hand);
			}else {
				ui.message("No more cards in deck!");
			}
		}else if(choice == 0) {
			if(playTable() == -1) {
				temp = pile.deal();
				hand.addTileToHand(temp);
				ui.message("You drew a " + temp + ".");
				hand.setSort();
				ui.displayHand(hand);
			}
		}
	}
	
	private int playTable() {
		int choice;
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
//			ui.message("Current Table: ");
//			ui.message(displayPlay());
//			ui.message("Current Hand: ");
//			ui.message(hand.toString());
//			System.out.println(tempTable);
			ui.displayTable(tempTable);
			ui.displayHand(hand);

//			while(!hasChar(choice = ui.response("Create Meld, Add to Meld, Split Meld, or End?(c,a,s,e): "), new char[]{'c','a','s','e'})) { 
//				ui.message("*ERROR choice invalid");
//			}
			choice = ui.query("Create Meld, Add to Meld, Split Meld, or End?", new String[] {"Create","Add","Split","End"});
//			System.out.println("test");
			//initialMeld check
//			if(initialMeld == true && hasChar(choice, new char[]{'a','s'})){
			if(initialMeld == true && (choice == 1 || choice == 2)){
				ui.message("*ERROR cannot add, or split meld on initial run");
				continue;
			}

			//create
			if(choice == 0) {
				//TODO removed loop but check if needed
//				while(true) {
					ui.message("Click on tiles in hand to create meld");
					meld = ui.getTiles();
//					meldStr = ui.responseStr("Enter your meld (e.g \"R1 B1 G1\") or nothing to end: ");
//					if(meldStr == null) {
//						continue;
//					}
//					
//					meld = assembleMeld(meldStr);
//					if(meld == null) {
					if(meld.size() == 0) {
						continue;
					}

//					if(checkFromHand(meld) == -1) {
//						continue;
//					}
//
					//initialMeld check
					if(initialMeld == true && meld.totalMeld() < 30) {
						ui.message("Meld need to total 30+");
						continue;
					}else if(initialMeld == true && meld.totalMeld() >= 30) {
						removeFromHand(meld);
						newMelds.add(meld);
						tempTable.add(meld);
						table = tempTable;
						game.setTable(table);
						initialMeld = false;
						return 0;
					}
//					
					removeFromHand(meld);
					newMelds.add(meld);
					tempTable.add(meld);
					
					continue;
//				}
//				//add
			}else 
			if(choice == 1) {
//				ui.message(tempTable.toString());
				//TODO exit option
				ui.message("Select meld");
//				meldStr = ui.responseStr("Enter Table Meld (e.g \"R1 B1 G1\") or nothing to end: ");
				tempMeld = ui.getMeld();
				System.out.println(tempMeld);
				if(tempMeld == null) {
					continue;
				}
//
//				meld = assembleMeld(meldStr);
//				if(meld == null) {
//					continue;
//				}
//				meldIndex = table.indexOf(meld);
//				if(meldIndex == -1) {
//					ui.message("*Error Meld not on table");
//					continue;
//				}
//
//				meldStr = ui.responseStr("Enter Tile('s) to add: ");
				ui.message("Selected Tiles to add");
//				Object[] selection = ui.getSelection();
//				choice = (int) selection[0];
				//to be implemented

				//ui.getSelection

//				meld = assembleMeld(meldStr);
//				if(meld == null) {
//					continue;
//				}
//
//				while(!hasChar(choice = ui.response("Tile From Table or Hand?(h,t): "), new char[]{'h','t'})) { 
//					ui.message("*ERROR choice invalid");
//				}
//

//				if(choice == '0') {
//					if(checkFromHand(meld) == -1) {
//						continue;
//					}else {
//						removeFromHand(meld);
//					}
//
//					tempMeld = new Meld(tempTable.getAt(meldIndex));
//					if(tempMeld.checkFrontAdd(meld)) {
//						tempMeld.addFront(meld);
//					}else {
//						tempMeld.add(meld);
//					}
//					if(!tempMeld.validMeld()) {
//						ui.message("*Error Invalid meld addition");
//						addMeldToHand(meld);
//						continue;
//					}else {
//						modMelds.add(meld);
//						tempTable.getAt(meldIndex).add(meld);
//					}
//				}else {
//					if(tempTable.indexOf(meld) == -1) {
//						ui.message("*Error Tile not on Table");
//						continue;
//					}
//
//					tempTable.remove(meld);
//					tempMeld = new Meld(tempTable.getAt(meldIndex));
//					if(tempMeld.checkFrontAdd(meld)) {
//						tempMeld.addFront(meld);
//					}else {
//						tempMeld.add(meld);
//					}
//					if(!tempMeld.validMeld()) {
//						ui.message("*Error Invalid meld addition");
//						continue;
//					}else {
//						modMelds.add(meld);
//						tempTable.getAt(meldIndex).add(meld);
//					}
//				}

				
//		continue;
		//split
//			}else if(choice == 2) {
//				ui.message(tempTable.toString());
//				meldStr = ui.responseStr("Enter Table Meld (e.g \"R1 B1 G1\") or nothing to end: ");
//				if(meldStr == null) {
//					continue;
//				}
//
//				meld = assembleMeld(meldStr);
//				if(meld == null) {
//					continue;
//				}
//				
//				meldIndex = table.indexOf(meld);
//				if(meldIndex == -1) {
//					ui.message("*Error Meld not on table");
//					continue;
//				}
//				
//				meldStr = ui.responseStr("Enter Tile where split should end (e.g \"R1\"): ");
//				tempTile = stringToTile(meldStr);
//				if(tempTile == null) {
//					ui.message("*Error Invalid Tile");
//					continue;
//				}
//				
//				if(meld.indexOf(tempTile) == -1) {
//					ui.message("*Error Tile not a member of meld");
//					continue;
//				}
//				
//				tempTable.remove(meldIndex);
//				tempTable.add(new Meld(new ArrayList<Tile>(meld.getMeld().subList(0, meld.indexOf(tempTile) + 1))));
//				modMelds.add(tempTable.getAt(tempTable.size()));
//				tempTable.add(new Meld(new ArrayList<Tile>(meld.getMeld().subList(meld.indexOf(tempTile) + 1,meld.size()))));
//				modMelds.add(tempTable.getAt(tempTable.size()));
//				
//				continue;
			}else if(choice == 3) {
				
				break;
			}
			break;
		}

		//check for invalid melds in players modified table
		invalidMelds = validTable(tempTable);
		if(invalidMelds == null) {

			//make changes permanent to table
			removedTiles = null;
			table = tempTable;
			game.setTable(table);
			return 0;
		}else {
			if(removedTiles != null) {
				for(Tile t: removedTiles) {
					hand.addTileToHand(t);
				}
				removedTiles = null;
			}
			newMelds = new ArrayList<Meld>();
			modMelds = new ArrayList<Meld>();
			ui.message("~Reseting Table~");

			//return soft error for draw
			return -1;
		}
	}
	
	private void addMeldToHand(Meld m) {
		for(Tile t: m.getMeld()) {
			hand.addTileToHand(t);
		}
	}

	
	private ArrayList<Meld> validTable(Table t) {
		ArrayList<Meld> mArr = new ArrayList<Meld>();
		for(Meld m: t.getTable()) {
			if(!m.validMeld()) {
				mArr.add(m);
			}
		}
		
		if(!mArr.isEmpty()) {
			return mArr;
		}

		return null;
	}
	
	private Meld assembleMeld(String melds) {
		String[] meldArr;
		Meld meld = new Meld();
		Tile tile;

		if(melds == null || melds.isEmpty()) {
			ui.message("*Error no tiles given.");
			return null;
		}

		meldArr = melds.split("\\s+");
		for(String s : meldArr) {
			if(s.length() > 1 && s.length() < 4) {
				tile = stringToTile(s);
				if(tile == null) {
					ui.message("Invalid tile " + s);
					return null;
				}
				
				meld.add(tile);

			}else {
				ui.message("Invalid tile " + s);
				return null;
			}
		}
		
		return meld;
	}
	
	private int checkFromHand(Meld m) {
		for(Tile t: m.getMeld()) {
			if(hand.indexOf(t) == -1) {
				ui.message(t + " Tile not in hand");
				return -1;
			}
		}
		return 0;
	}
	
	private int removeFromHand(Meld m){
		for(Tile t: m.getMeld()) {
			if(hand.indexOf(t) == -1) {
				ui.message(t + " Tile not in hand");
				return -1;
			}else {
				hand.playTileFromHand(t);
			}
		}
		return 0;
	}
	
	private Tile stringToTile(String tile) {
		Tile t = new Tile();
		int tempVal = 0;
		
		for (Tile.colour c : Tile.colour.values()) {
			if(tile.charAt(0) == c.getCol()) {
				t.setColour(c);
			}
		}
		if(t.getColour() == null) {
			return null;
		}

		if(tile.length() == 2) {
			tempVal = Character.getNumericValue(tile.charAt(1));
		}else if(tile.length() == 3) {
			tempVal = Integer.parseInt(tile.substring(1,3));
		}
		if(tempVal < 1) {
			return null;
		}

		for (Tile.value v : Tile.value.values()) {
			if(tempVal == v.getVal()) {
				t.setValue(v);
			}
		}
		if(t.getValue() == null) {
			return null;
		}
		return t;
	}

	@Override
	protected void updateHand(Game update) {
		hand = update.getH0();
	}

	private boolean hasChar(char c, char[] chars) {
		if(new String(chars).indexOf(c) == -1) {
			return false;
		}else {
			return true;
		}
	}
}
