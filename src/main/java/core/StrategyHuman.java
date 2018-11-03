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
		String meldStr;
		Meld meld;
		Tile temp;
		
		ui.message(hand.toString());
		while(!hasChar(choice = ui.response("Create meld, Split meld, or Draw Tile?(c,s,d): "), new char[]{'c','s','d'})) {
			ui.message("*ERROR choice invalid");
		}
		
		if(choice == 'd') {
			temp = pile.deal();
			hand.addTileTop_hand(temp);
			ui.message("You drew a " + temp + ".");
		}else if(choice == 'c') {
			while(true) {
				meldStr = ui.responseStr("Enter your meld (e.g \"R1 B1 G1\"): ");
				meld = parseMeld(meldStr);
				if(meldStr == null) {
					continue;
				}
				System.out.println(meld);
				ui.response("test");
			}
		}

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
			tempVal = Integer.parseInt(tile.substring(1,2));
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
