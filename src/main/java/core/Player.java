package core;
import java.util.Observable;
import java.util.Observer;

public class Player implements Observer{
	
	private String name;
	private Hand hand; 
	private Table table; // player can see table itself
	private Pile pile; // player can see deck itself
	
	
	public Player (String k, int id){ //should have player strategy
		
		name = k;
		hand = new Hand(); 
		int playerID = id; 
    	//should contain player strategy
    	pile = new Pile();
    	table = new Table();

	}
	public void update(Observable obs, Object x) {
		   
	}
	
	@Override
	public String toString(){
		return name;
	}
}
