package core;

import java.util.ArrayList;
import java.util.Observable;

@SuppressWarnings("deprecation")
public class Game extends Observable{ 
	private Pile pile;
	private Table table;
	private CLI cli;
	private Hand h0;
	private Hand h1;
	private Hand h2;
	private Hand h3;
	private Player human;
	private Player p1;
	private Player p2;
	private Player p3;
	
	public void init() {
		pile = new Pile();
		table = new Table();
		cli = new CLI();
		Hand h0 = new Hand();
		Hand h1 = new Hand();
		Hand h2 = new Hand();
		Hand h3 = new Hand();

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
		cli.message("Welcome To Rummikub!");
		
		
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

	public ArrayList<Hand> gethands() {
		ArrayList<Hand> hands  = new ArrayList<Hand>();
		hands.add(h0);
		hands.add(h1);
		hands.add(h2);
		hands.add(h3);
		return hands;
		
	}

	public Hand getH0() {
		return h0;
	}

	public void setH0(Hand h0) {
		this.h0 = h0;
	}

	public Hand getH1() {
		return h1;
	}

	public void setH1(Hand h1) {
		this.h1 = h1;
	}

	public Hand getH2() {
		return h2;
	}

	public void setH2(Hand h2) {
		this.h2 = h2;
	}

	public Hand getH3() {
		return h3;
	}

	public void setH3(Hand h3) {
		this.h3 = h3;
	}
	
	
}