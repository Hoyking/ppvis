package action;

import controller.GameController;

public class ExitAction implements ButtonAction{

	@Override
	public void action() {
		GameController.exit();
	}

}
