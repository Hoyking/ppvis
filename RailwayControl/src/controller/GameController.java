package controller;

import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import listeners.RailwayMouseListener;
import model.Station;
import model.GameObject;
import model.Train;
import view.GameView;

public class GameController {
	private GameView view;
	private List<GameObject> trains;
	private List<GameObject> stations;
	private GameObject dest;
	private List<Point> ways;
	private List<Point> tempWay;
	private RailwayMouseListener rListener;
	private boolean validWay;
	private static final long DELAY = 50;
	private long lastTime;
	private static final int PRESSED = 0;
	private static final int UNPRESSED = 1;
	private static final int DRAGGED = 2;

	public GameController() throws IOException {
		view = new GameView(800, 600);
		initiateGame();
	}

	private void initiateGame() throws IOException {
		lastTime = System.currentTimeMillis();

		stations = new ArrayList<GameObject>();
		ways = new ArrayList<Point>();
		tempWay = new ArrayList<Point>();

		dest = new Station(40, new Point(400, 500), Color.blue);

		// train = new Train((Image)ImageIO.read(new
		// File("./resources/textures/train.jpg")));
		// train.setPosition(new Point(50, 50));

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

		rListener = new RailwayMouseListener(view.getPanel(), this);

		// view.drawObject(train);
		view.drawObject(dest);
		for (int areaIndex = 0; areaIndex < stations.size(); areaIndex++) {
			view.drawObject(stations.get(areaIndex));
		}
	}

	public void refreshView(Point point, int pointType) throws IOException {
		switch (pointType) {
		case PRESSED: {
			Station station = new Station();
			for (int index = 0; index < stations.size(); index++) {
				station = (Station) stations.get(index);
				if (Math.pow((double) (point.getX() - station.getPosition().getX()), 2)
						+ Math.pow((double) (point.getY() - station.getPosition().getY()), 2) <= (station.getRadius())
								* (station.getRadius())) {
					if (!station.isUsed()) {
						validWay = true;
						station.setUsed(true);
					}
					break;
				}
			}
			if (validWay) {
				tempWay.clear();
				tempWay.add(null);
				tempWay.add(station.getPosition());
				repaintView();
			}
		}
			break;
		case UNPRESSED: {
			if (Math.pow((double) (point.getX() - dest.getPosition().getX()), 2)
					+ Math.pow((double) (point.getY() - dest.getPosition().getY()), 2) > (dest.getWidth() / 2)
							* (dest.getWidth() / 2))
				validWay = false;
			if (validWay) {
				tempWay.add(dest.getPosition());
				tempWay.add(null);
				validWay = false;
				ways.addAll(tempWay);
				repaintView();
			} else {
				tempWay.clear();
				lastTime = System.currentTimeMillis() - DELAY - 1;
				repaintView();
			}
		}
			break;
		case DRAGGED: {
			if (validWay) {
				tempWay.add(point);
				repaintView();
			}
		}
		}
	}

	private void repaintView() throws IOException {
		long curTime = System.currentTimeMillis();
		if (curTime - lastTime > DELAY) {
			List<Point> allWays = new ArrayList<Point>(ways);
			allWays.addAll(tempWay);
			List<GameObject> objects = new ArrayList<GameObject>(stations);
			objects.add(dest);
			// objects.add(train);
			view.repaint(allWays, objects);
			lastTime = curTime;
		}
	}

	public static void main(String[] args) throws IOException {
		new GameController();
	}
}
