
package core;

import java.util.ArrayList;

public class Hand {

		String name = "";

		Hand(String x) {
			// super();
			name = x;
		}
		
		private ArrayList<Tile> p_hand = new ArrayList<Tile>();
		
		public ArrayList<Tile> getTiles() {
			return this.p_hand;
		}

		public Tile getTile(int index) {
			return this.p_hand.get(index);
		}

		public void addTileTop_hand(Tile newTile) {
			p_hand.add(newTile);
		}

		public int sizeOfp_hand() {
			return p_hand.size();
		}
		
		public boolean isEmpty() {
			return p_hand.isEmpty();
		}

		public void playTileFromp_hand(Tile tileToPlay) {
			
			p_hand.remove(tileToPlay);
			p_hand.trimToSize();

		}
		
		
}

