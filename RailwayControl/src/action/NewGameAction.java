package action;

import java.io.IOException;
import controller.GameController;

public class NewGameAction implements ButtonAction{

	@Override
	public void action() {
		try {
			GameController.initiateGame();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
