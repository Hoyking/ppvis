package controller;

import java.awt.Color;
import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import actions.RailwayPressedAction;
import actions.RailwayUnpressedAction;
import model.Station;
import model.GameObject;
import view.GameView;

public class GameController {
	private GameView view;
	private List<GameObject> stations;
	private GameObject dest;
	private RailwayPressedAction rPressed;
	private RailwayUnpressedAction rUnpressed;

	public GameController() throws IOException {
		view = new GameView(800, 600);
		initiateGame();
	}
	
	private void fillStationsPack() throws IOException {
		stations = new ArrayList <GameObject> ();
		//left
		stations.add(new Station(40, new Point(0, 214)));
		stations.add(new Station(40, new Point(0, 304)));
		stations.add(new Station(40, new Point(0, 394)));
		stations.add(new Station(40, new Point(0, 484)));
		//bottom
		stations.add(new Station(40, new Point(265, 574)));
		stations.add(new Station(40, new Point(355, 574)));
		stations.add(new Station(40, new Point(445, 574)));
		stations.add(new Station(40, new Point(535, 574)));
		//right
		stations.add(new Station(40, new Point(800, 484)));
		stations.add(new Station(40, new Point(800, 394)));
		stations.add(new Station(40, new Point(800, 304)));
		stations.add(new Station(40, new Point(800, 214)));
	}
	
	private void initiateGame() throws IOException {
		//stations
		fillStationsPack();
		//destination
		dest = new Station(40, new Point(400, 90), Color.blue);
		//action controller
		ActionController ac = new ActionController(view);
		rPressed = new RailwayPressedAction();
		rUnpressed = new RailwayUnpressedAction((Station)dest);
		ac.setStationList(stations);
		ac.setActions(rPressed, rUnpressed);
		//adding gaming objects
		for(GameObject station: stations) {
			rPressed.addStation((Station)station);
			view.addGameObject(station);
		}
		for(GameObject station: stations) {
			view.addStation(station);
		}
		view.addStation(dest);
		view.repaint();
	}	
	
	public static void main(String[] args) throws IOException {
		new GameController();
	}
}
