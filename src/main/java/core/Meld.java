<<<<<<< HEAD:src/main/java/core/Meld.java
package core;
=======
import java.util.ArrayList;
import java.util.Collections;
public class Meld {	
		
		private ArrayList<Tile> melded_tiles = new ArrayList<Tile>(); 
		
		public Meld (ArrayList<Tile> c) {
			addTiles(c);  
		}
		
		public void addTiles(ArrayList<Tile> c) {
			melded_tiles.addAll(c); 
		}
		
		public void addTile(Tile c) {
			melded_tiles.add(c); 
		}
		
		public void removeTile(Tile c) {
			melded_tiles.remove(c); 
		}
>>>>>>> 15667b2bfca3aa7a67d2fa37a023e286c071cf54:src/main/java/Meld.java

		public void removeTiles (ArrayList<Tile> c) {
			melded_tiles.removeAll(c); 
		}
		
}

