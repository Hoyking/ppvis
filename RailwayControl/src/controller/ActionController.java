package controller;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import action.RailwayPressedAction;
import action.RailwayUnpressedAction;
import listener.RailwayMouseListener;
import model.Shedule;
import model.GameObject;
import model.Path;
import model.Station;
import model.Train;
import task.TrainCreationTask;
import task.TrainPreparingTask;
import view.GameView;

public class ActionController implements Runnable {
	private GameView view;
	private RailwayMouseListener rListener;
	private List <Shedule> shedules;
	private List <GameObject> stations;
	private Shedule curShedule;
	private static final double ROUND = 5;
	private double coef = 1.0;
	private TrainCreationTask task;
	private boolean enabled = true;
	
	public ActionController(GameView view) {
		shedules = new ArrayList <Shedule> ();
		this.view = view;
		rListener = new RailwayMouseListener(view.getPanel(), this);
		initiateCreationTask();
	}
	
	public void setActions(RailwayPressedAction rPressed, RailwayUnpressedAction rUnpressed) {
		rListener.setPressedAction(rPressed);
		rListener.setUnpressedAction(rUnpressed);
	}
	
	public void initiateCreationTask() {
		task = new TrainCreationTask(this);
		Timer timer = new Timer();
		timer.schedule(task, (long)(ROUND / coef * 1000));
		coef += 0.1;
	}
	
	public void initiatePreparingTask() {
		Random random = new Random();
		Station station;
		while (true) {
			station = (Station)stations.get(random.nextInt(12));
			if(station.getUsingType() == 0) {
				break;
			}
		}
		TrainPreparingTask pTask = new TrainPreparingTask(this, station);
		Thread thread = new Thread(pTask);
		thread.start();
	}
	
	public void refreshView() {
		view.setSheduleList(new ArrayList <Shedule> (shedules));
		view.repaint();
	}
	
	public void setStationList(List <GameObject> stations) {
		this.stations = stations;
	}
	
	public void addShedule(Station station) throws IOException {
		Shedule shedule;
		if (station.getOrientation().equals("LEFT"))
		{
			shedule = new Shedule(station, 4);
			shedules.add(shedule);
		}
		else if (station.getOrientation().equals("BOTTOM"))
		{
			shedule = new Shedule(station, 0);
			shedules.add(shedule);
		}
		else
		{
			shedule = new Shedule(station, 12);
			shedules.add(shedule);
		}
	}
	
	public void addPath(Path path) {
		Shedule shedule = findShedule(path.getStation());
		shedule.setPath(path);
		curShedule = shedule;
	}
	
	public void refreshCurPath(Point point) {
		curShedule.getPath().makePath(point);
	}
	
	public void removeCurPath() {
		curShedule.removePath();
	}
	
	private Shedule findShedule (Station station) {
		for(Shedule shedule: shedules) {
			if(shedule.getStation().equals(station))
				return shedule;
		}
		return null;
	}
	
	public void checkCorrectShedule(Station station) {
		Shedule shedule = findShedule(station);
		if(shedule.getPath() != null) {
			shedule.setStarted(true);
			station.setUsingType(2);
			station.setCurNumber(0);
		}
		else {
			if(enabled) {
				enabled = false;
				station.setUsingType(0);
				station.setCurNumber(0);
				task.cancel();
				GameController.initiateGameOver(view.getScore());
			}
		}
	}
	
	private void moveTrains() {
		for(int index = 0; index < shedules.size(); index++) {
			Shedule shedule = shedules.get(index);
			if(shedule.isStarted()) {
				Path path = shedule.getPath();
				Train train = shedule.getTrain();
				if(path != null) {
					try {
						Point nextPoint = path.get(path.getIndex(shedule.getCurStationPoint()) + 1);
						train.setPosition(nextPoint);
						train.setTexture(shedule.getCurStationPoint(), nextPoint);
						shedule.setCurStationPoint(nextPoint);
					}
					catch(IndexOutOfBoundsException e) {
						shedule.getStation().setUsingType(0);
						shedules.remove(shedule);
						index--;
						view.scoreIncr();
					}
				}
				else {
					if(enabled) {
						enabled = false;
						shedule.getStation().setUsingType(0);
						shedules.remove(shedule);
						index--;
						task.cancel();
						GameController.initiateGameOver(view.getScore());
						break;
					}
				}
			}
		}
		refreshView();
	}
	
	@Override
	public void run() {
		while(shedules.size() == 0) {
			Thread.yield();
		}
		moveTrains();
		try {
			Thread.sleep(100);
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		run();
	}	
}
