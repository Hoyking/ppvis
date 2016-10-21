package action;

import controller.GameController;

public class MainMenuAction implements ButtonAction{

	@Override
	public void action() {
		GameController.initiateIntro();
	}

}
