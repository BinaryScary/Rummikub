
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
		
		public ArrayList<Tile> sortByValue() {
			ArrayList<Tile> handDuplicate;
			handDuplicate = hand;
			Collections.sort(handDuplicate, new ValueSort());
			return handDuplicate;
		}

		@Override
		public String toString(){
			String str = "[";
			if(sortByValue().size() == 0) { return str; }
			for(Tile t : sortByValue()) { str += t.toString() + " "; }
			str = str.trim() + "]";
			return str;
		}
}
