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
	protected int handNum;
	protected Game game;
	protected boolean initialMeld = true; //TODO change to true on submit
	protected ArrayList<Meld> newMelds;
	protected ArrayList<Meld> modMelds;
	
	
	public boolean isInitialMeld() {
		return initialMeld;
	}
	
	public void setHand(int h) {
		handNum = h;
	}

	public void setInitialMeld(boolean initialMeld) {
		this.initialMeld = initialMeld;
	}

	public Player (){
		newMelds = new ArrayList<Meld>();
		modMelds = new ArrayList<Meld>();
		hand = new Hand(); 
    	table = new Table();
    	handNum = 0;
	}
	
	public Player(Hand h) {
		newMelds = new ArrayList<Meld>();
		modMelds = new ArrayList<Meld>();
		hand = h;
		table = new Table();
    	handNum = 0;
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
	
//	protected abstract void updateHand(Game update);
	protected void updateHand(Game update) {
		hand = update.getHand(handNum);
	}
	
	protected abstract void play();
	
	@Override
	public String toString(){
		return "" + handNum;
	}
	public Tile draw() {
//		return hand.addTileToHand(pile.deal());
		Tile temp;
		if(!pile.isEmpty()) {
			temp = pile.deal();
			hand.addTileToHand(temp);
		}else{
			pile.dryDraws++;
			return null;
		}
		return temp;
	}
	
	protected String displayPlay() {
		String str = "";
		for(Meld m: newMelds) {
			str += "*" + m + " ";
		}
		for(Meld m: modMelds) {
			str += "!" + m + " ";
		}
		for(Meld m: table.getTable()) {
			if(!newMelds.contains(m) && !modMelds.contains(m)) {
				str += m + " ";
			}
		}
		
		newMelds = new ArrayList<Meld>();
		modMelds = new ArrayList<Meld>();
		str = str.trim();
		return str;
	}
}
