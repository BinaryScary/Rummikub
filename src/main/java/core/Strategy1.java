package core;

public class Strategy1 extends Player {

	@Override
	protected void updateHand(Game update) {
		hand = update.getH1();
	}

	@Override
	protected void play() {
		Game game;
		Player player;
		Meld M;
		
		
		for (Meld meld : M.getMeld()){
			Meld temp = new Meld(); 
			temp.add(player);
			
		}
		
	}
}
