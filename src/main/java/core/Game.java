package core;

import java.util.Observable;

@SuppressWarnings("deprecation")
public class Game extends Observable{ 
	private Pile pile;
	private Table table;
	private CLI cli;
	private Player human;
	private Player p1;
	private Player p2;
	private Player p3;
	
	public void init() {
		pile = new Pile();
		table = new Table();
		cli = new CLI();
		Player human = new Player("human", 0);
		Player p1 = new Player("p1", 1);
		Player p2 = new Player("p2", 2);
		Player p3 = new Player("p3", 3);
		pile.scramble();
		
		this.addObserver(human);
		this.addObserver(p1);
		this.addObserver(p2);
		this.addObserver(p3);
	}
	
	//init(file)
	
	public void start() {
		gameLogic();
	}
	
	private void gameLogic() {
		
		
		
		
		
	}
	
	private void broadcast() {
		notifyObservers();
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public Player getHuman() {
		return human;
	}

	public void setHuman(Player human) {
		this.human = human;
	}

	public Player getP1() {
		return p1;
	}

	public void setP1(Player p1) {
		this.p1 = p1;
	}

	public Player getP2() {
		return p2;
	}

	public void setP2(Player p2) {
		this.p2 = p2;
	}

	public Player getP3() {
		return p3;
	}

	public void setP3(Player p3) {
		this.p3 = p3;
	}
	
	
}