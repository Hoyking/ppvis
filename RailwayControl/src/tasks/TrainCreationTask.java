package tasks;

import java.util.TimerTask;
import controller.ActionController;

public class TrainCreationTask extends TimerTask {
	private ActionController ac;
	
	public TrainCreationTask (ActionController ac) {
		this.ac = ac;
	}
	
	@Override
	public void run() {
		ac.initiatePreparingTask();
		ac.initiateTask();
		this.cancel();
	}
}
