package controller;

import java.awt.Color;
import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import action.RailwayPressedAction;
import action.RailwayUnpressedAction;
import model.Station;
import view.GameOverView;
import view.GameView;
import view.IntroView;
import model.GameObject;

public class GameController {
	private static GameView gameView;
	private static GameOverView gameOverView;
	private static IntroView introView;
	private static List<GameObject> stations;
	private static GameObject dest;
	private static RailwayPressedAction rPressed;
	private static RailwayUnpressedAction rUnpressed;
	private static JFrame frame;

	public GameController() throws IOException {
		frame = new JFrame();
		frame.setSize(800, 600);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initiateIntro();
	}
	
	private static void fillStationsPack() throws IOException {
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
	
	public static void initiateIntro() {
		try {
			introView = new IntroView(800, 600);
		} catch (IOException e) {
			e.printStackTrace();
		}
		frame.setContentPane(introView);
	}
	
	public static void initiateGame() throws IOException {
		//initiating gaming view
		gameView = new GameView(800, 600);
		gameView.setEnabled(true);
		frame.setContentPane(gameView);
		//stations
		fillStationsPack();
		//destination
		dest = new Station(40, new Point(400, 90), Color.blue);
		//action controller
		ActionController ac = new ActionController(gameView);
		Thread thread = new Thread(ac);
		thread.start();
		rPressed = new RailwayPressedAction();
		rUnpressed = new RailwayUnpressedAction((Station)dest);
		ac.setStationList(stations);
		ac.setActions(rPressed, rUnpressed);
		//adding gaming objects
		for(GameObject station: stations) {
			rPressed.addStation((Station)station);
			gameView.addStation((Station)station);
		}
		for(GameObject station: stations) {
			gameView.addStation(station);
		}
		gameView.addStation(dest);
		gameView.repaint();
	}	
	
	public static void initiateGameOver(int score) {
		try {
			gameOverView = new GameOverView(800, 600, score);
		} catch (IOException e) {
			e.printStackTrace();
		}
		gameView.setEnabled(false);
		frame.setContentPane(gameOverView);
	}
	
	public static void exit() {
		frame.setEnabled(false);
		frame.dispose();
		System.exit(0);
	}
	
	public static void main(String[] args) throws IOException {
		new GameController();
	}
}
