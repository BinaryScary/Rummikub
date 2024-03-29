package core;

import java.util.ArrayList;

public class Strategy1 extends Player {
	boolean played = false;

	public Strategy1() {
		super();
		name = "A1";
	}
	public Strategy1(Hand h) {
		super(h);
		name = "A1";
	}
	public Strategy1(Hand h, Table t) {
		super(h);
		table = t;
		name = "A1";
	}

//	@Override
//	protected void updateHand(Game update) {
//		hand = update.getH1();
//	}

	@Override
	protected void play() {
		newMelds = new ArrayList<Meld>();
		modMelds = new ArrayList<Meld>();
		Tile tempTile;
		ArrayList<Meld> arrMeld;

		arrMeld = playableRuns();
		for(Meld m: arrMeld) {
			playMeld(m);
		}
		
		arrMeld = playableSets();
		for(Meld m: arrMeld) {
			playMeld(m);
		}
		
		if(initialMeld == false) {
			playTile();
		}
		
		if(played == false) {
			tempTile = draw();
			if(tempTile == null) {
				System.out.println("Tried to draw but no more tiles in pile");
			}
		}

		played = false;
	}
	
	public void playTile(){
		Tile tempTile;
		Tile.value tempVal;
		for(Meld m: table.getTable()) {
			if(m.typeMeld() == 's') {
				for(Tile.colour c: Tile.colour.values()) {
						tempTile = new Tile( c, m.getAt(0).getValue());
					if(!m.getMeld().contains(tempTile)) {
						if(hand.getTiles().contains(tempTile)){
							m.add(tempTile);
							modMelds.add(m);
							hand.playTileFromHand(tempTile);
							played = true;
						}
					}
				}
				
			}else if(m.typeMeld() == 'r') {
				tempVal = m.getAt(0).getValue().previous();
				if(tempVal != null) {
					while(true) {
						tempTile = new Tile( m.getAt(0).getColour(),tempVal);
						if(hand.getTiles().contains(tempTile)) {
							m.addFront(tempTile);
							modMelds.add(m);
							hand.playTileFromHand(tempTile);
							played = true;
						}else {
							break;
						}
						tempVal = tempVal.previous();
					}
				}
				tempVal = m.getAt(m.size()-1).getValue().next();
				if(tempVal != null) {
					while(true) {
						tempTile = new Tile( m.getAt(m.size()-1).getColour(),tempVal);
						if(hand.getTiles().contains(tempTile)) {
							m.add(tempTile);
							modMelds.add(m);
							hand.playTileFromHand(tempTile);
							played = true;
						}else {
							break;
						}
						tempVal = tempVal.next();
					}
				}
			}
			
		}
		
	}
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

	public void playMeld(Meld m) {
		if (initialMeld == true) {
			if (m.totalMeld() >= 30) {
				played = true;
				initialMeld = false;
				newMelds.add(m);
				table.add(m);
				for(Tile t: m.getMeld()) {
					hand.playTileFromHand(t);
				}
			}
		}else {
			played = true;
			newMelds.add(m);
			table.add(m);
			for(Tile t: m.getMeld()) {
				hand.playTileFromHand(t);
			}
		}
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


}
