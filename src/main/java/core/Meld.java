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
	
	public boolean validMeld() {
		if(typeMeld() == 's') {
			Tile.value val = arr.get(0).getValue();
			Tile.colour[] colArr = {arr.get(0).getColour(),null,null,null};
			
			if(arr.size() > 4) {
				return false;
			}
			
			for(int i = 1; i < arr.size(); i++) {
				if(arr.get(1).getValue() != val) {
					return false;
				}
				if(arr.contains(arr.get(i).getColour())) {
					return false;
				}
				colArr[i] = arr.get(i).getColour();
			}
			return true;
		}

		if(typeMeld() == 'r') {
			Tile buf = arr.get(0);
			Tile.colour col = buf.getColour();
			
			for(int i = 1; i < arr.size(); i++) {
				if(arr.get(i).getColour() != col) {
					return false;
				}
				if(arr.get(i).getValue().getVal() -1 != buf.getValue().getVal()) {
					return false;
				}
				buf = arr.get(i);
			}
			return true;
		}
		
		return false;
	}
	
	private char typeMeld() {
		//{r : run | s : set | n : none}
		if(arr.size() < 3) {
			return 'n';
		}
		
		if(arr.get(0).getValue().getVal() == arr.get(1).getValue().getVal() && arr.get(0).getColour() != arr.get(1).getColour() ) {
			return 's';
		}
		if(arr.get(0).getValue().getVal() + 1 == arr.get(1).getValue().getVal() && arr.get(0).getColour() == arr.get(1).getColour() ) {
			return 'r';
		}
		
		return 'n';
	}
		
}

