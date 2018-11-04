
package core;

import java.util.ArrayList;
import java.util.Arrays;

public class Hand {

		private ArrayList<Tile> hand;

		public Hand() {
			hand = new ArrayList<Tile>();
		}

		public Hand(Tile[] arr){
			this.hand = new ArrayList<Tile>(Arrays.asList(arr));
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

		@Override
		public String toString(){
			String str = "{";
			if(hand.size() == 0) {
				return str;
			}

			for(Tile t : hand) {
				str += t.toString() + " ";
			}
			str = str.trim() + "}";

			return str;
		}
}
