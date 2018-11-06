package core;

import java.util.ArrayList;
import java.util.Collections;

public class Pile {
	
	private ArrayList<Tile> pile;
	
	public Pile() {
		pile = new ArrayList<Tile>();
		formPile(); formPile();
	}
	
	public boolean isEmpty() {
		return pile.isEmpty();
	}
	
	public void add(Tile t) {
		pile.add(t);
	}
	
	private void formPile() {
		for(int c = 0; c < 4; c++) {
			for(int v = 0; v < 13; v++) {
				Tile tile = new Tile(Tile.colour.values()[c], Tile.value.values()[v]);
				pile.add(tile); 
			}
		}
	}
	
	public int numTiles() { return pile.size(); }
	
	public Tile deal() {
		if(pile.size() > 0) {
			return pile.remove(0);
		} else {
			return null;
		}
	}
	
	public boolean scramble(){
		Collections.shuffle(pile);
		return true;
	}
}
