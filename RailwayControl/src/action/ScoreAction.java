package action;

import controller.GameController;

public class ScoreAction implements ButtonAction{

	@Override
	public void action() {
		GameController.initiateScore();
	}

}
