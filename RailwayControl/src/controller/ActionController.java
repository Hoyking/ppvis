package controller;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;

import actions.RailwayPressedAction;
import actions.RailwayUnpressedAction;
import listeners.RailwayMouseListener;
import model.Shedule;
import model.GameObject;
import model.Path;
import model.Station;
import tasks.TrainCreationTask;
import tasks.TrainPreparingTask;
import view.GameView;

public class ActionController implements Runnable {
	private GameView view;
	private RailwayMouseListener rListener;
	private List <Shedule> shedules;
	private List <GameObject> stations;
	private Shedule curShedule;
	private static final double ROUND = 5;
	private double coef = 1.0;
	private int usingStationsNum = 0;
	
	public ActionController(GameView view) {
		shedules = new ArrayList <Shedule> ();
		this.view = view;
		rListener = new RailwayMouseListener(view.getPanel(), this);
		initiateTask();
	}
	
	public void setActions(RailwayPressedAction rPressed, RailwayUnpressedAction rUnpressed) {
		rListener.setPressedAction(rPressed);
		rListener.setUnpressedAction(rUnpressed);
	}
	
	public void initiateTask() {
		TrainCreationTask task = new TrainCreationTask(this);
		Timer timer = new Timer();
		timer.schedule(task, (long)(ROUND / coef * 1000));
		coef += 0.1;
	}
	
	public void initiatePreparingTask() {
		Random random = new Random();
		Station station;
		while (true) {
			if(usingStationsNum == 12) {
				clearStations();
				usingStationsNum = 1;
			}
			station = (Station)stations.get(random.nextInt(12));
			if(station.getUsingType() == 0) {
				usingStationsNum++;
				break;
			}
		}
		TrainPreparingTask pTask = new TrainPreparingTask(this, station);
		Thread thread = new Thread(pTask);
		thread.start();
	}
	
	private void clearStations() {
		for(GameObject obj: stations) {
			Station station = (Station)obj;
			station.setUsingType(0);
			shedules.clear();
		}
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
		view.addGameObject(shedule.getTrain());
		//view.repaint();
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
		if(findShedule(station).getPath() != null) {
			station.setUsingType(2);
			station.setCurNumber(0);
		}
		else {
			station.setUsingType(0);
			station.setCurNumber(0);
			System.out.println("Game over");
		}
	}
	
	@Override
	public void run() {
		/*while(shedules.size() == 0) {
			Thread.yield();
		}
		for(Shedule comp: shedules) {
			
		}*/
	}
	
}
