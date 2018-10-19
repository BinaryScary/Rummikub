package core;
import junit.framework.TestCase;

public class MeldTest extends TestCase {
	public void testMeldInit() {
		Tile[] tArr = {new Tile(Tile.colour.RED, Tile.value.ONE),new Tile(Tile.colour.BLUE, Tile.value.TWO)};
		Meld m1 = new Meld();
		Meld m2 = new Meld(tArr);

		assertNotNull(m1);
		assertNotNull(m2);
	}
	public void testMeldString() {
		Tile[] tArr = {new Tile(Tile.colour.RED, Tile.value.ONE),new Tile(Tile.colour.BLUE, Tile.value.TWO)};
		Meld m1 = new Meld(tArr);
		assertEquals("RED ONE BLUE TWO",m1.toString());
	}
	public void testMeldEquals() {
		Tile[] tArr1 = {new Tile(Tile.colour.RED, Tile.value.ONE),new Tile(Tile.colour.BLUE, Tile.value.TWO)};
		Tile[] tArr2 = {new Tile(Tile.colour.RED, Tile.value.ONE),new Tile(Tile.colour.BLUE, Tile.value.TWO)};
		Meld m1 = new Meld(tArr1);
		Meld m2 = new Meld(tArr2);
		assertEquals(m1, m2);
	}
	
	public void testMeldTotal() {
		Tile[] tArr1 = {new Tile(Tile.colour.RED, Tile.value.ONE),new Tile(Tile.colour.BLUE, Tile.value.TWO)};
		Meld m1 = new Meld(tArr1);
		
		assertEquals(3, m1.totalMeld());
	}
}
