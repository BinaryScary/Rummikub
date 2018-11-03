package core;

public class StrategyHuman extends Player {

	public StrategyHuman() {
		super();
		name = "human";
	}

	@Override
	protected void play() {
		CLI ui = new CLI();
		char choice;
		
		ui.message(hand.toString());
		while(!hasChar(choice = ui.response("Create or Split meld?(C,S): "), new char[]{'c','s'})) {
			ui.message("*ERROR choice invalid");
		}
	}

	@Override
	protected void updateHand(Game update) {
		hand = update.getH0();
	}

	private boolean hasChar(char c, char[] chars) {
		if(new String(chars).indexOf(c) == -1) {
			return false;
		}else {
			return true;
		}
	}
}
