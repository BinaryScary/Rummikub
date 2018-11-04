package core;

import java.util.ArrayList;
import java.util.Observable;

@SuppressWarnings("deprecation")
public class Game extends Observable{
	private Pile pile;
	private Table table;
	private CLI ui;
	private Hand handArr[];
	private Player playerArr[];

	public void init() {
		pile = new Pile();
		table = new Table();
		ui = new CLI();
		playerArr = new Player[4];
		handArr = new Hand[4];

		for(int i = 0; i<4; i++) {
			handArr[i] = new Hand();
		}

		playerArr[0] = new StrategyHuman();
		playerArr[1] = new StrategyHuman();
		playerArr[2] = new StrategyHuman();
		playerArr[3] = new StrategyHuman();
		pile.scramble();


		this.addObserver(playerArr[0]);
		this.addObserver(playerArr[1]);
		this.addObserver(playerArr[2]);
		this.addObserver(playerArr[3]);
	}

	//init(file)

	public void start() {
		ui.message("Welcome To Rummikub!");
		//Each Player is assigned a hand 0 for human and so on
		deal(handArr[0]);
		deal(handArr[1]);
		deal(handArr[2]);
		deal(handArr[3]);

		//testing
		Tile[] tArr = {new Tile(Tile.colour.RED, Tile.value.TEN),new Tile(Tile.colour.BLUE, Tile.value.TEN),new Tile(Tile.colour.GREEN, Tile.value.TEN),new Tile(Tile.colour.ORANGE, Tile.value.TEN),new Tile(Tile.colour.RED, Tile.value.TEN),new Tile(Tile.colour.BLUE, Tile.value.TEN),new Tile(Tile.colour.GREEN, Tile.value.TEN),new Tile(Tile.colour.ORANGE, Tile.value.TEN)};
		handArr[0] = new Hand(tArr);
		Meld tMeld = new Meld(new Tile[] {new Tile(Tile.colour.RED, Tile.value.TEN),new Tile(Tile.colour.BLUE, Tile.value.TEN),new Tile(Tile.colour.GREEN, Tile.value.TEN)});
		table.add(tMeld);


		broadcast();

		turnLoop();
	}

	private Player turnLoop() {

		for(int i = 0; i<4; i++) {
			ui.message("Player " + playerArr[i].toString() + "'s turn.");

			playerArr[i].play();

			broadcast();

			ui.message("Player " + playerArr[i].toString() + "'s hand.");
			ui.message(handArr[i].toString());
			ui.message("Table: ");
			ui.message(table.toString());

			if(handArr[i].isEmpty()) {
				return playerArr[i];
			}

			if(i==3) i=-1;
		}

		return null;
	}

	private void deal(Hand hand) {
		for(int i = 0; i<14; i++) {
			hand.addTileToHand(pile.deal());
		}
	}

	public Pile getPile() {
		return pile;
	}

	public void setPile(Pile pile) {
		this.pile = pile;
	}

	private void broadcast() {
		setChanged();
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
		hands.add(handArr[0]);
		hands.add(handArr[1]);
		hands.add(handArr[2]);
		hands.add(handArr[3]);
		return hands;

	}

	public Hand getH0() {
		return handArr[0];
	}

	public void setH0(Hand h0) {
		this.handArr[0] = h0;
	}

	public Hand getH1() {
		return handArr[1];
	}

	public void setH1(Hand h1) {
		this.handArr[1] = h1;
	}

	public Hand getH2() {
		return handArr[2];
	}

	public void setH2(Hand h2) {
		this.handArr[2] = h2;
	}

	public Hand getH3() {
		return handArr[3];
	}

	public void setH3(Hand h3) {
		this.handArr[3] = h3;
	}


}
