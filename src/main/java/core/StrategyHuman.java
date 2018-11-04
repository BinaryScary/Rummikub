package core;

import java.util.ArrayList;

public class StrategyHuman extends Player {

	private CLI ui;

	public StrategyHuman() {
		super();
		name = "human";
	}

	@Override
	protected void play() {
		ui = new CLI();
		char choice;
		Tile temp;
		
		ui.message(hand.toString());
		while(!hasChar(choice = ui.response("Play Table, or Draw Tile?(p,d): "), new char[]{'p','d'})) {
			ui.message("*ERROR choice invalid");
		}
		
		if(choice == 'd') {
			temp = pile.deal();
			hand.addTileTop_hand(temp);
			ui.message("You drew a " + temp + ".");
		}else if(choice == 'p') {
			if(playTable() == -1) {
				temp = pile.deal();
				hand.addTileTop_hand(temp);
				ui.message("You drew a " + temp + ".");
			}
		}

	}
	
	private int playTable() {
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
			//initialMeld check
			if(initialMeld == true && hasChar(choice, new char[]{'a','s'})){
				ui.message("*ERROR cannot add, or split meld on initial run");
				continue;
			}

			if(choice == 'c') {
				while(true) {
					meldStr = ui.responseStr("Enter your meld (e.g \"R1 B1 G1\") or nothing to end: ");
					if(meldStr == null) {
						continue;
					}
					
					meld = assembleMeld(meldStr);
					if(meld == null) {
						continue;
					}
					if(checkFromHand(meld) == -1) {
						continue;
					}
					ui.message(meld.toString());

					//initialMeld check
					if(initialMeld == true && meld.totalMeld() < 30) {
						ui.message("Meld need to total 30+");
						continue;
					}else if(initialMeld == true && meld.totalMeld() >= 30) {
						removeFromHand(meld);
						tempTable.add(meld);
						table = tempTable;
						game.setTable(table);
						initialMeld = false;
						return 0;
					}
					
					removeFromHand(meld);
					tempTable.add(meld);
					continue;
				}
			}else if(choice == 'a') {
				ui.message(tempTable.toString());
				meldStr = ui.responseStr("Enter Table Meld (e.g \"R1 B1 G1\") or nothing to end: ");
				if(meldStr == null) {
					continue;
				}

				meld = assembleMeld(meldStr);
				if(meld == null) {
					continue;
				}
				
				meldIndex = table.indexOf(meld);
				if(meldIndex == -1) {
					ui.message("*Error Meld not on table");
					continue;
				}

				meldStr = ui.responseStr("Enter Tile('s) to add: ");
				meld = assembleMeld(meldStr);
				if(meld == null) {
					continue;
				}
				if(checkFromHand(meld) == -1) {
					continue;
				}else {
					removeFromHand(meld);
				}

				tempMeld = new Meld(tempTable.getAt(meldIndex));
				tempMeld.add(meld);
				if(!tempMeld.validMeld()) {
					ui.message("*Error Invalid meld addition");
					continue;
				}else {
					tempTable.getAt(meldIndex).add(meld);
				}
				
		continue;
			}else if(choice == 's') {
				ui.message(tempTable.toString());
				meldStr = ui.responseStr("Enter Table Meld (e.g \"R1 B1 G1\") or nothing to end: ");
				if(meldStr == null) {
					continue;
				}

				meld = assembleMeld(meldStr);
				if(meld == null) {
					continue;
				}
				
				meldIndex = table.indexOf(meld);
				if(meldIndex == -1) {
					ui.message("*Error Meld not on table");
					continue;
				}
				
				meldStr = ui.responseStr("Enter Tile where split should end (e.g \"R1\"): ");
				tempTile = stringToTile(meldStr);
				if(tempTile == null) {
					ui.message("*Error Invalid Tile");
					continue;
				}
				
				if(meld.indexOf(tempTile) == -1) {
					ui.message("*Error Tile not a member of meld");
					continue;
				}
				
				tempTable.remove(meldIndex);
				tempTable.add(new Meld(new ArrayList<Tile>(meld.getMeld().subList(0, meld.indexOf(tempTile) + 1))));
				tempTable.add(new Meld(new ArrayList<Tile>(meld.getMeld().subList(meld.indexOf(tempTile) + 1,meld.size()))));
				
				continue;
			}else if(choice == 'e') {
				
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
					hand.addTileTop_hand(t);
				}
				removedTiles = null;
			}
			ui.message("~Reseting Table~");

			//return soft error for draw
			return -1;
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
				hand.playTileFromp_hand(t);
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
