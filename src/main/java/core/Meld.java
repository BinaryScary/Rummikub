package core;
import java.util.ArrayList;
import java.util.Arrays;

public class Meld {	
		
	private ArrayList<Tile> arr;
	
	Meld(){
		arr = null;
	}
	
	Meld(Tile[] arr){
		this.arr = new ArrayList<Tile>(Arrays.asList(arr));
	}
	
	public Tile getAt(int i) {
		Tile buf = null;
		try {
			buf = arr.get(i);
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}

		return buf;
	}
	
	public Tile remove(int i) {
		Tile buf = null;
		try {
			buf = arr.remove(i);
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		
		return buf;
	}
	
	public boolean remove(Tile t) {
		return arr.remove(t);
	}
	
	public void add(Tile t) {
		arr.add(t);
	}
	
	public int totalMeld() {
		int total = 0;
		if(arr.size() == 0) {
			return total;
		}

		for(Tile t : arr) {
			total += t.getValue().getVal();
		}
		return total;
	}

    @Override
    public String toString(){
		String str = "";
		if(arr.size() == 0) {
			return str;
		}

		for(Tile t : arr) {
			str += t.toString() + " ";
		}

		return str.trim();
    }
	
    @Override
    public boolean equals(Object t){
    	if(((Meld)t).size() != arr.size()) {
    		return false;
    	}

    	for(int i=0; i < arr.size(); i++) {

    		if(!((Meld)t).getAt(i).equals(arr.get(i))) {
    			return false;
    		}

    	}
    	
    	return true;
    }
	
//	public Tile setAt(int i, Tile t) {
//		Tile buf = null;
//		try {
//			buf = arr.set(i, t);
//		} catch (IndexOutOfBoundsException e) {
//			e.printStackTrace();
//		}
//
//		return buf;
//	}

	public int size() {
		return arr.size();
	}
	
//TODO validMeld
		
}

