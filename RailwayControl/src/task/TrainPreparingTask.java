package task;

import java.io.IOException;

import controller.ActionController;
import model.Station;

public class TrainPreparingTask implements Runnable{
	private Station station;
	private ActionController ac;
	
	public TrainPreparingTask(ActionController ac, Station station) {
		this.station = station;
		this.ac = ac;
	}
	
	@Override
	public void run() {
		try {
			ac.addShedule(station);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		station.setUsingType(1);
		//3
		station.setCurNumber(3);
		ac.refreshView();
		try {
			Thread.sleep(1000);
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		//2
		station.setCurNumber(2);
		ac.refreshView();
		try {
			Thread.sleep(1000);
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		//1
		station.setCurNumber(1);
		ac.refreshView();
		try {
			Thread.sleep(1000);
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		//0
		ac.checkCorrectShedule(station);
		ac.refreshView();
	}
}
