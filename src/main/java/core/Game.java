package core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;

@SuppressWarnings("deprecation")
public class Game extends Observable{
	private Pile pile;
	private Table table;
//	private CLI ui;
	private Hand handArr[];
	private Player playerArr[];
	private int playerCount;
	private int uiNum = 0;
	private int turn;
	private boolean test = false;
	public Player winner = null;
	//TODO score
	
	private Pane pane;
	private GUI ui;
	
	public void init(File file, boolean pScram) {
		test = true;
		pile = new Pile(pScram);
		pile.scramble();
		table = new Table();

		turn = (int)(Math.random() * ((playerCount-1) + 1));
		playerCount = 4;
		playerArr = new Player[4];
		handArr = new Hand[4];
		playerArr[0] = new Strategy1();
		playerArr[1] = new Strategy3();
		playerArr[2] = new Strategy1();
		playerArr[3] = new Strategy3();
		for(int i = 0; i<playerCount; i++) {
			handArr[i] = new Hand();
			playerArr[i].setHand(i);
		}

		for(int i = 0; i<playerCount; i++) {
			this.addObserver(playerArr[i]);
		}
		fileSetup(file);

//        for(int i = 0; i< playerCount; i++) {
//			if(handArr[i].isEmpty()) {
//				deal(handArr[i]);
//			}
//        }
	}

	public void init(File file) {
		test = true;
		pile = new Pile();
		pile.scramble();
		table = new Table();

		turn = (int)(Math.random() * ((playerCount-1) + 1));
		playerCount = 4;
		playerArr = new Player[4];
		handArr = new Hand[4];
		playerArr[0] = new Strategy1();
		playerArr[1] = new Strategy3();
		playerArr[2] = new Strategy1();
		playerArr[3] = new Strategy3();
		for(int i = 0; i<playerCount; i++) {
			handArr[i] = new Hand();
			playerArr[i].setHand(i);
		}

		for(int i = 0; i<playerCount; i++) {
			this.addObserver(playerArr[i]);
		}
		fileSetup(file);

        for(int i = 0; i< playerCount; i++) {
			if(handArr[i].isEmpty()) {
				deal(handArr[i]);
			}
        }
	}
	public void init(Stage primaryStage, File file) {

		pile = new Pile();
		pile.scramble();
		table = new Table();

        ui = new GUI(pane);

        ui.boardInit(primaryStage);
        primaryStage.show();
        
		turn = (int)(Math.random() * ((playerCount-1) + 1));
		playerCount = 4;
		playerArr = new Player[4];
		handArr = new Hand[4];
		playerArr[0] = new StrategyHuman(ui);
		playerArr[1] = new Strategy1();
		playerArr[2] = new Strategy3();
		playerArr[3] = new Strategy1();
		for(int i = 0; i<playerCount; i++) {
			handArr[i] = new Hand();
			playerArr[i].setHand(i);
		}

		for(int i = 0; i<playerCount; i++) {
			this.addObserver(playerArr[i]);
		}
		fileSetup(file);

        for(int i = 0; i< playerCount; i++) {
			if(handArr[i].isEmpty()) {
				deal(handArr[i]);
			}
        }
	}

	public void init(Stage primaryStage) {
		pile = new Pile();
		pile.scramble();
		table = new Table();
//		Tile[] tArr1 = {new Tile(Tile.colour.RED, Tile.value.TEN),new Tile(Tile.colour.BLUE, Tile.value.TEN),new Tile(Tile.colour.ORANGE, Tile.value.TEN)};
//		Tile[] tArr2 = {new Tile(Tile.colour.RED, Tile.value.NINE),new Tile(Tile.colour.RED, Tile.value.TEN),new Tile(Tile.colour.RED, Tile.value.ELEVEN)};
//		Meld m1 = new Meld(tArr1);
//		Meld m2 = new Meld(tArr2);
//		Meld[] mArr = {m1,m2};
//		table = new Table(mArr);

//		ui = new CLI();
//		playerArr = new Player[4];

//		handArr = new Hand[4];
        ui = new GUI(pane);

        ui.boardInit(primaryStage);
        primaryStage.show();
        
        int set = ui.query("Setup?", new String[] {"Yes","No"});
        
        if(set == 0) {
        	setup();
        }else {
			turn = (int)(Math.random() * ((playerCount-1) + 1));
			playerCount = 4;
			playerArr = new Player[4];
			handArr = new Hand[4];
			playerArr[0] = new StrategyHuman(ui);
			playerArr[1] = new Strategy1();
			playerArr[2] = new Strategy3();
			playerArr[3] = new Strategy1();
			for(int i = 0; i<playerCount; i++) {
				handArr[i] = new Hand();
				playerArr[i].setHand(i);
			}
			for(int i = 0; i < playerCount; i++) {
				deal(handArr[i]);
			}
        }
        
        //player count
//		playerArr[0] = new StrategyHuman(ui);
//		playerArr[1] = new Strategy1();
//		playerArr[2] = new Strategy3();
//		playerArr[3] = new Strategy1();

		//player count

		for(int i = 0; i<playerCount; i++) {
			this.addObserver(playerArr[i]);
		}
	}
	
	public void setup() {
        playerCount = ui.query("How many players?", new String[] {"2","3","4"}) + 2;
		playerArr = new Player[playerCount];
		handArr = new Hand[playerCount];
		
        for(int i = 0; i < playerCount; i++) {
        	uiNum = ui.query("What Strategy is used for Player " + (i+1), new String[] {"Human","Strategy 1","Strategy 3"});
        	
        	switch (uiNum) {
        	case 0: playerArr[i] = new StrategyHuman(ui);
					break;
        	case 1: playerArr[i] = new Strategy1();
					break;
        	case 2: playerArr[i] = new Strategy3();
					break;
        	}
        }

		for(int i = 0; i<playerCount; i++) {
			playerArr[i].setHand(i);
		}

        String[] startInt = new String[playerCount + 1]; 
        for(int i = 0; i<playerCount; i++) {
        	startInt[i] = Integer.toString(i+1);
        }
        startInt[playerCount] = "Random";
        turn = ui.query("What player starts? ", startInt);
        if(turn == playerCount) {
			turn = (int)(Math.random() * ((playerCount-1) + 1));
        }
        
        int set = ui.query("Setup Hands, File, or none?", new String[] {"Hands","File","None"});
        if(set == 0) {
        	for(int i = 0; i < playerCount; i++) {
        		ui.message("Hand for Player " + (i+1));
        		handArr[i] = new Hand(ui.tileQuery());
        		if(handArr[i].isEmpty()) {
					handArr[i] = new Hand();
					deal(handArr[i]);
        		}
        	}
        }else if(set == 1) {
        	fileSetup(ui.getFile());
        }
        
        
	}
	
	public void fileSetup(File file) {
		String str;
		BufferedReader br;
		String[] tileStr;
		int arrCache = 0;
		Meld tempMeld = null;
		Tile tempTile;

		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			ui.message("File not found");
			return;
		}
		try {
			str = br.readLine();
		} catch (IOException e) {
			ui.message("File does not follow correct format");

			try {
				br.close();
			} catch (IOException e1) {
				e1.printStackTrace();
				ui.message("Could not close BufferedReader");
			}

			return;
		}
		if(closeBR(br) == -1) {
			return;
		}

		tileStr = str.split("\\s+");
		
		char test;
		for(String s: tileStr) {
		//pile : table { R1 } : hand0 : hand1 : hand2 : hand3 : E
			test = s.charAt(0);
			if((int)test == 58) {
				arrCache++;
				continue;
			}
			if((int)test == 123) {
				tempMeld = new Meld();
				continue;
			}
			if((int)test == 125) {
				if(tempMeld == null) {
					ui.message("*Error Unable to init, bad meld in table");
					return;
				}
				table.add(tempMeld);
				continue;
			}
			
			tempTile = stringToTile(s);
			
			switch(arrCache) {
			case 0:
				pile.add(tempTile);
				break;
			case 1:
				if(tempMeld == null) {
					ui.message("*Error Meld is null");
					return;
				}
				tempMeld.add(tempTile);
				break;
			case 2:
				handArr[0].addTileToHand(tempTile);
				break;
			case 3:
				handArr[1].addTileToHand(tempTile);
				break;
			case 4:
				handArr[2].addTileToHand(tempTile);
				break;
			case 5:
				handArr[3].addTileToHand(tempTile);
				break;
			case 6:
				System.out.println("Pile: " + pile);
				System.out.println("Table: " +table);
				System.out.println("Hand1: " + handArr[0]);
				System.out.println("Hand2: " + handArr[1]);
				System.out.println("Hand3: " + handArr[2]);
				System.out.println("Hand4: " + handArr[3]);
				return;
			default:
				System.out.println("Pile: " + pile);
				System.out.println("Table: " +table);
				System.out.println("Hand1: " + handArr[0]);
				System.out.println("Hand2: " + handArr[1]);
				System.out.println("Hand3: " + handArr[2]);
				System.out.println("Hand4: " + handArr[3]);
				return;
				
			}
		}
		ui.message("*Error not enough Tile info given");
		System.out.println(pile);
		System.out.println(table);
		System.out.println(handArr[0]);
		System.out.println(handArr[1]);
		System.out.println(handArr[2]);

	}
	
	
	private Tile stringToTile(String tile) {
		Tile t = new Tile();
		int tempVal = 0;
		
		for (Tile.colour c : Tile.colour.values()) {
			if(tile.charAt(0) == c.getCol()) {
				t.setColour(c);
			}
		}
		if(t.getColour() == null) {
			return null;
		}

		if(tile.length() == 2) {
			tempVal = Character.getNumericValue(tile.charAt(1));
		}else if(tile.length() == 3) {
			tempVal = Integer.parseInt(tile.substring(1,3));
		}
		if(tempVal < 1) {
			return null;
		}

		for (Tile.value v : Tile.value.values()) {
			if(tempVal == v.getVal()) {
				t.setValue(v);
			}
		}
		if(t.getValue() == null) {
			return null;
		}
		return t;
	}

	private int closeBR(BufferedReader br) {
		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
			ui.message("Could not close BufferedReader");
			return -1;
		}
		return 0;
	}

	public void start() {
		if(test == false) {
			ui.message("Welcome To Rummikub!");
		}			
		//Each Player is assigned a hand 0 for human and so on
//		Tile[] tArr = {new Tile(Tile.colour.BLUE, Tile.value.TEN), new Tile(Tile.colour.GREEN, Tile.value.TEN), new Tile(Tile.colour.RED, Tile.value.TEN),new Tile(Tile.colour.ORANGE, Tile.value.TEN)};
//		handArr[0] = new Hand(tArr);

//		//testing
//		Tile[] tArr = {new Tile(Tile.colour.RED, Tile.value.TEN),new Tile(Tile.colour.BLUE, Tile.value.TEN),new Tile(Tile.colour.GREEN, Tile.value.TEN),new Tile(Tile.colour.ORANGE, Tile.value.TEN),new Tile(Tile.colour.RED, Tile.value.TEN),new Tile(Tile.colour.BLUE, Tile.value.TEN),new Tile(Tile.colour.GREEN, Tile.value.TEN),new Tile(Tile.colour.ORANGE, Tile.value.TEN)};
//		handArr[0] = new Hand(tArr);
//		Meld tMeld = new Meld(new Tile[] {new Tile(Tile.colour.RED, Tile.value.TEN),new Tile(Tile.colour.BLUE, Tile.value.TEN),new Tile(Tile.colour.GREEN, Tile.value.TEN)});
//		table.add(tMeld);

		broadcast();

		if(test == false) {
			winner = turnLoop();
			if(winner == null) {
				ui.message("Tie");
			}else{
				ui.message("Player " + (turn+1) + " Won!");
			}
		}else {
			winner = turnLoop();
			if(winner == null) {
				System.out.println("Tie!");
			}else{
				System.out.println("Player " + (turn+1) + " Won!");
			}
			System.out.println("Table: " + table);
		}
				System.out.println("Hand1: " + handArr[0]);
				System.out.println("Hand2: " + handArr[1]);
				System.out.println("Hand3: " + handArr[2]);
				System.out.println("Hand4: " + handArr[3]);
	}

	private Player turnLoop() {
		int recent = -1;
		//player count
		for(;turn<playerCount; turn++) {
			
			if(!table.getRecent().isEmpty() && recent < 0) {
				recent = playerCount;
			}
			if(recent == 0) {
				table.clearRecent();
				recent--;
			}
			if(test == false)ui.message("Player " + (turn+1) + "'s turn.");
			playerArr[turn].play();

			broadcast();
			if(recent > 0)recent--;

//			ui.message("Player " + playerArr[i].toString() + "'s hand.");
//			ui.message(handArr[i].toString());
//			ui.message("Table: ");
//			ui.message(playerArr[i].displayPlay());
			if(handArr[turn].isEmpty()) {
				return playerArr[turn];
			}
			
			if(pile.isEmpty()) {
				pile.dryDraws++;
			}

			if(pile.dryDraws >= 5) {
				return null;
			}
			//player count -1
			if(turn== playerCount -1) {
				turn=-1;		
			}
			
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

	public ArrayList<Player> getPlayers() {
		ArrayList<Player> players  = new ArrayList<Player>();
		//player count
		for(int i =0; i < playerCount; i++) {
			players.add(playerArr[i]);
		}
		return players;

	}

	public ArrayList<Hand> gethands() {
		ArrayList<Hand> hands  = new ArrayList<Hand>();
		//player count
		for(int i =0; i < playerCount; i++) {
			hands.add(handArr[i]);
		}
		return hands;

	}
	
	public Hand getHand(int num) {
		return this.handArr[num];
	}

	public void setHand(int num, Hand h) {
		this.handArr[num] = h;
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
