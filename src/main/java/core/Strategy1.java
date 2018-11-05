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

	public Strategy1() {
		super();
		name = "A1";
	}

	public Strategy1(Hand h) {
		super(h);
		name = "A1";
	}

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

	public ArrayList<Meld> playableRuns() {
		ArrayList<Meld> winners = new ArrayList<Meld>();
		ArrayList<Meld> winnersFinal = new ArrayList<Meld>();

		ArrayList<Meld> winnersR = new ArrayList<Meld>();
		for(Tile t : hand.sortByValueColorArrays().get(0)) {
			if (findRunTilesByInt(hand.sortByValueColorArrays().get(0), t.getValue().getVal()) == null) {
				//muck
			} else {
				Meld temp = new Meld(findRunTilesByInt(hand.sortByValueColorArrays().get(0), t.getValue().getVal()));
				winnersR.add(temp);
			}
		}
		Meld tempR = new Meld();
		for(Meld m : winnersR) {
			if (m.totalMeld() > tempR.totalMeld()) {
				tempR = m;
			}
		}
		winners.add(tempR);

		ArrayList<Meld> winnersB = new ArrayList<Meld>();
		for(Tile t : hand.sortByValueColorArrays().get(1)) {
			if (findRunTilesByInt(hand.sortByValueColorArrays().get(1), t.getValue().getVal()) == null) {
				//muck
			} else {
				Meld temp = new Meld(findRunTilesByInt(hand.sortByValueColorArrays().get(1), t.getValue().getVal()));
				winnersB.add(temp);
			}
		}
		Meld tempB = new Meld();
		for(Meld m : winnersB) {
			if (m.totalMeld() > tempB.totalMeld()) {
				tempB = m;
			}
		}
		winners.add(tempB);

		ArrayList<Meld> winnersG = new ArrayList<Meld>();
		for(Tile t : hand.sortByValueColorArrays().get(2)) {
			if (findRunTilesByInt(hand.sortByValueColorArrays().get(2), t.getValue().getVal()) == null) {
				//muck
			} else {
				Meld temp = new Meld(findRunTilesByInt(hand.sortByValueColorArrays().get(2), t.getValue().getVal()));
				winnersG.add(temp);
			}
		}
		Meld tempG = new Meld();
		for(Meld m : winnersG) {
			if (m.totalMeld() > tempG.totalMeld()) {
				tempG = m;
			}
		}
		winners.add(tempG);

		ArrayList<Meld> winnersO = new ArrayList<Meld>();
		for(Tile t : hand.sortByValueColorArrays().get(3)) {
			if (findRunTilesByInt(hand.sortByValueColorArrays().get(3), t.getValue().getVal()) == null) {
				//muck
			} else {
				Meld temp = new Meld(findRunTilesByInt(hand.sortByValueColorArrays().get(3), t.getValue().getVal()));
				winnersO.add(temp);
			}
		}
		Meld tempO = new Meld();
		for(Meld m : winnersO) {
			if (m.totalMeld() > tempO.totalMeld()) {
				tempO = m;
			}
		}
		winners.add(tempO);
		// for each color, for each tile, run find tiles by int, if the result is null then idgaf
		// if the number is greater than or equal to 3 then add it to the winners array, whichever array is the biggest winner gets to fuckin go

		for(Meld m : winners) {
			if (m.totalMeld() != 0) {
				winnersFinal.add(m);
			}
		}
		return winnersFinal;
	}

	private ArrayList<Tile> findRunTilesByInt(ArrayList<Tile> h, int valueInt) {
		ArrayList<Tile> handF = new ArrayList<Tile>();
		int checkVal = valueInt;

		for(Tile t : h) {
			if (t.getValue().getVal() == checkVal) {
				handF.add(t);
				checkVal++;
			}
		}

		if (handF.size() < 3) {
			return null;
		} else {
			return handF;
		}
	}
//	private Meld[] playableRuns() {
//	}

	private void playMeld(Meld m) {

	}

}
