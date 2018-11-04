package core;
import junit.framework.TestCase;

public class HandTest extends TestCase {
	
	
	Tile t1 = new Tile(Tile.colour.RED, Tile.value.ONE);
	Tile t2 = new Tile(Tile.colour.BLUE, Tile.value.TWO);
	Tile[] tArr = {t1,t2};
	
	public void testisEmpty() {
		Hand h1 = new Hand();
		h1.addTileToHand(t1); 
		h1.addTileToHand(t2); 
		assertFalse(h1.isEmpty());
	}
	
	public void testGetTile() {
		Hand h1 = new Hand();
		h1.addTileToHand(t1); 
		h1.addTileToHand(t2); 
		assertEquals(h1.getTile(0), t1);
	}
	
	public void testGetSize() {
		Hand h1 = new Hand();
		h1.addTileToHand(t1); 
		h1.addTileToHand(t2); 
		assertEquals(h1.sizeOfHand(), 2);
		
		h1.playTileFromHand(t1);
		assertEquals(h1.sizeOfHand(), 1);
	}
}
