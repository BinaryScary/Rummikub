
package core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Hand {

		private ArrayList<Tile> hand;

		public Hand() {
			hand = new ArrayList<Tile>();
		}

		public Hand(Tile[] arr){
			this.hand = new ArrayList<Tile>(Arrays.asList(arr));
		}

		public class ValueSort implements Comparator<Tile> {
			public int compare(Tile x, Tile y) {
				return x.getValue().getVal() - y.getValue().getVal();
			}
		}

		public ArrayList<Tile> getTiles() {
			return this.hand;
		}

		public Tile getTile(int index) {
			return this.hand.get(index);
		}

		public int indexOf(Tile t) {
			return this.hand.indexOf(t);
		}

		public void addTileToHand(Tile newTile) {
			hand.add(newTile);
		}

		public int sizeOfHand() {
			return hand.size();
		}

		public boolean isEmpty() {
			return hand.isEmpty();
		}

		public void playTileFromHand(Tile tileToPlay) {

			hand.remove(tileToPlay);
			hand.trimToSize();
		}

		public ArrayList<Tile> sortByValueColor() {
			ArrayList<Tile> handDuplicate;
			ArrayList<Tile> handR = new ArrayList<Tile>();
			ArrayList<Tile> handB = new ArrayList<Tile>();
			ArrayList<Tile> handG = new ArrayList<Tile>();
			ArrayList<Tile> handO = new ArrayList<Tile>();
			handDuplicate = hand;

			for(Tile t : handDuplicate) {
				if (t.getColour().getCol() == 'R') {
					handR.add(t);
				} else if (t.getColour().getCol() == 'B') {
					handB.add(t);
				} else if (t.getColour().getCol() == 'G') {
					handG.add(t);
				} else if (t.getColour().getCol() == 'O') {
					handO.add(t);
				}
			}
			
			Collections.sort(handR, new ValueSort());
			Collections.sort(handB, new ValueSort());
			Collections.sort(handG, new ValueSort());
			Collections.sort(handO, new ValueSort());
			
			handR.addAll(handB);
			handR.addAll(handG);
			handR.addAll(handO);
			return handR;
		}
		
		private void removeDoubles() {
			// TODO Auto-generated method stub
			ArrayList<Tile> tiles = new ArrayList<Tile>();
			ArrayList<Tile> x = new ArrayList<Tile>();
			x.addAll(this.hand);
			this.hand.clear();
			this.hand.addAll(x);

			tiles = this.hand;
		}
		//checks for runss 
		public boolean runFound(Hand x) { 

			if (x.hand.isEmpty()) {
				return false;
			}
			x.removeDoubles();
			ArrayList<Tile> y = new ArrayList<Tile>();
			y.add(x.getTile(0));
			int count = 1;
			for (int i = 1; i < x.sizeOfHand(); i++) {
				if ((x.getTile(i).getValue().getVal()) - (y.get(count - 1).getValue().getVal()) == 1) {
					y.add(x.getTile(i));
					count++;
					if ((count == 3) && (x.sizeOfHand() - 1 == i)) {
						return true;
					}
				} else if (y.size() > 2) {
					return true;
				} else if ((i == x.sizeOfHand() - 1) && (y.size() > 2)) {
					return true;
				} else if ((i == x.sizeOfHand() - 1) && (y.size() < 3)) {
					return false;
				} else if (i < x.sizeOfHand() - 1) {
					y.clear();
					count = 1;

					y.add(x.getTile(i));

				}
			}
			return false;
		}

		@Override
		public String toString(){
			String str = "[";
			if(sortByValueColor().size() == 0) { return str; }
			for(Tile t : sortByValueColor()) { str += t.toString() + " "; }
			str = str.trim() + "]";
			return str;
		}
}
