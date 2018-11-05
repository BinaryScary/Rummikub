package core;

import java.util.ArrayList;

public class Strategy2 extends Player {
	
	public Strategy2() {
		super();
		name = "A2";
	}
	public Strategy2(Hand h) {
		super(h);
		name = "A2";
	}

	private CLI ui;

	@Override
	protected void updateHand(Game update) {
		hand = update.getH2();
	}

	@Override
	protected void play() {

	}
}