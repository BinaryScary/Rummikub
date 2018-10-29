
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
		
		public void HandReader() {
			
			if (isEmpty()) {
				System.out.println(name +  " has no tiles");
				
			} 
			
			else {
				System.out.println(name + " 's tiles are: ");
				
				for (int i = 0; i < sizeOfp_hand(); i++) {
					String Colour = "" ;
					
					if (p_hand.get(i).getColour().equals("BLUE")) {
						Colour = "Blue";
					}
					
					if (p_hand.get(i).getColour().equals("GREEN")) {
						Colour = "Green";
					}
					
					if (p_hand.get(i).getColour().equals("ORANGE")) {
						Colour = "Orange";
					}
					
					if (p_hand.get(i).getColour().equals("RED")) {
						Colour = "Red";
					}
					
					System.out.println(i + 1 + "- " + Colour + " " + p_hand.get(i).getValue());
				}
			}
		}
}

