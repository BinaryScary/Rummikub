package core;

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
		Tile temp;

		while(true) {
			while(!hasChar(choice = ui.response("Create Meld, Add to Meld, Split Meld, or End?(c,a,s,e): "), new char[]{'c','a','s'})) { ui.message("*ERROR choice invalid");
			}
			
			if(choice == 'c') {
				while(true) {
					meldStr = ui.responseStr("Enter your meld (e.g \"R1 B1 G1\") or skip to Draw: ");
					if(meldStr == null) {
						temp = pile.deal();
						hand.addTileTop_hand(temp);
						ui.message("You drew a " + temp + ".");
					}
					meld = parseMeld(meldStr);
					if(meld == null) {
						continue;
					}
					System.out.println(meld);
					if(!meld.validMeld()) {
						ui.message("Invalid Meld");
						continue;
					}
					if(meld.totalMeld() < 30) {
						ui.message("Meld need to total 30+");
						continue;
					}
					
					table.add(meld);
					break;
				}
			}
			
			break;
		}
		return -1;
	}
	
	private Meld parseMeld(String melds) {
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
