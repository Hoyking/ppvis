package action;

import model.Score;

public class RemoveStatsAction implements ButtonAction{
	
	@Override
	public void action() {
		Score.removeStats();
	}

}
