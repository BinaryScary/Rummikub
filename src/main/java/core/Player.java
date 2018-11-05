package core;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

@SuppressWarnings("deprecation")
public abstract class Player implements Observer{
	
	protected String name;
	protected Hand hand; 
	protected Pile pile;
	protected Table table; // player can see table itself
	protected Game game;
	//TODO change back to true after testing
	protected boolean initialMeld = false;
	
	
	public Player (){
		
		hand = new Hand(); 
    	table = new Table();

	}
	
	public Player(Hand h) {
		hand = h;
		table = new Table();
	}
	
	public Hand getHand() {
		return hand;
	}

	public Table getTable() {
		return table;
	}

	public void update(Observable obs, Object x) {
		Game update = (Game) obs;
		game = update;
		table = update.getTable();
		pile = update.getPile();
		updateHand(update);
	}
	
	protected abstract void updateHand(Game update);
	
	protected abstract void play();
	
	@Override
	public String toString(){
		return name;
	}
}
