package core;

import java.util.Observable;

@SuppressWarnings("deprecation")
public class Game extends Observable{ 
	private Pile pile;
	private Hand h1;
	private Hand h2;
	private Hand h3;
	private Hand h4;
	private Table table;
	private CLI cli;
	
	public void init() {
		pile = new Pile();
//		h1 = new Hand("human");
//		h2 = new Hand("p1");
//		h3 = new Hand("p2");
//		h4 = new Hand("p3");
		table = new Table();
		cli = new CLI();
		
		pile.scramble();
		
	}
	
	//init(file)
	
	public void start() {
		gameLogic();
	}
	
	private void gameLogic() {
		
	}
	
	
}