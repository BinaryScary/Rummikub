package core;

public class Strategy1 extends Player {

	@Override
	protected void updateHand(Game update) {
		hand = update.getH1();
	}

	@Override
	protected void play() {
		
	}
	private Meld[] playableSets() {
	}
	private Meld[] playableRuns() {
	}

	private void playMeld(Meld m) {
		
	}

}
