package controller;

import java.awt.Color;
import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import actions.RailwayPressedAction;
import actions.RailwayUnpressedAction;
import listeners.RailwayMouseListener;
import model.Station;
import model.GameObject;
import model.Path;
import model.Train;
import view.GameView;

public class GameController {
	private GameView view;
	private List<GameObject> trains;
	private List<GameObject> stations;
	private GameObject dest;
	private RailwayPressedAction rPressed;
	private RailwayUnpressedAction rUnpressed;
	private RailwayMouseListener rListener;

	public GameController() throws IOException {
		view = new GameView(800, 600);
		initiateGame();
	}

	private void initiateGame() throws IOException {
		rPressed = new RailwayPressedAction();
		rUnpressed = new RailwayUnpressedAction((Station)dest);
		
		stations = new ArrayList<GameObject>();

		dest = new Station(40, new Point(400, 500), Color.blue);
		
		stations.add(new Station(40, new Point(0, 90)));
		stations.add(new Station(40, new Point(0, 180)));
		stations.add(new Station(40, new Point(0, 270)));
		stations.add(new Station(40, new Point(0, 360)));
		
		stations.add(new Station(40, new Point(800, 90)));
		stations.add(new Station(40, new Point(800, 180)));
		stations.add(new Station(40, new Point(800, 270)));
		stations.add(new Station(40, new Point(800, 360)));
		
		stations.add(new Station(40, new Point(265, 0)));
		stations.add(new Station(40, new Point(355, 0)));
		stations.add(new Station(40, new Point(445, 0)));
		stations.add(new Station(40, new Point(535, 0)));

		rPressed = new RailwayPressedAction();
		rUnpressed = new RailwayUnpressedAction((Station)dest);
		for(GameObject station: stations) {
			rPressed.addStation((Station)station);
			view.addGameObject(station);
		}
		view.addGameObject(dest);
		
		rListener = new RailwayMouseListener(view.getPanel(), this);
		rListener.setPressedAction(rPressed);
		rListener.setUnpressedAction(rUnpressed);
	}

	public void refreshView() {
		view.repaint();
	}
	
	public void addPathToView(Path path) {
		view.addPath(path);
	}
	
	public void refreshCurPath(Point point) {
		view.getCurPath().makePath(point);
	}
	
	public void setCurStationUsing(boolean flag) {
		view.getCurPath().getStation().setUsed(flag);
	}
	
	public void removeCurPath() {
		view.removeCurPath();
	}

	public static void main(String[] args) throws IOException {
		new GameController();
	}
}
