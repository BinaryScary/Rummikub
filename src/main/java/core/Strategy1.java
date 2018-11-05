package core;

import java.util.ArrayList;

public class Strategy1 extends Player {
	
	public Strategy1() {
		super();
		name = "A1";
	}
	public Strategy1(Hand h) {
		super(h);
		name = "A1";
	}

	private CLI ui;

	@Override
	protected void updateHand(Game update) {
		hand = update.getH1();
	}

	@Override
	protected void play() {

	}

	@SuppressWarnings("unlikely-arg-type")
	public ArrayList<Meld> playableSets() {
		ArrayList<Meld> arr = new ArrayList<Meld>();
		Meld tempMeld;
		
		for(Tile t : hand.getTiles()) {
			tempMeld = new Meld();
			for(Tile.colour c : Tile.colour.values()) {
				if(hand.getTiles().indexOf(new Tile(c, t.getValue())) != -1) {
					tempMeld.add(hand.getTile(hand.getTiles().indexOf(new Tile(c, t.getValue()))));
				}
			}
			if(tempMeld.getMeld().size() >=3 && !arr.contains(tempMeld)) {
				arr.add(tempMeld);
			}
		}
		
		return arr;
	}
//	private Meld[] playableRuns() {
//	}

	private void playMeld(Meld m) {
		
	}
		
}
