package core;
import junit.framework.TestCase;

public class HandTest extends TestCase {
	
	
	Tile t1 = new Tile(Tile.colour.RED, Tile.value.ONE);
	Tile t2 = new Tile(Tile.colour.BLUE, Tile.value.TWO);
	Tile[] tArr = {t1,t2};
	
	public void testisEmpty(){
	Hand h1 = new Hand();
	h1.addTileTop_hand(t1); 
	h1.addTileTop_hand(t2); 
	assertFalse(h1.isEmpty());
	}
}
