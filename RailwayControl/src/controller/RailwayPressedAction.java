package controller;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import model.Station;

public class RailwayPressedAction {
	private List <Station> stations;
	private List <Point> points;
	
	public RailwayPressedAction () {
		stations = new ArrayList <Station> ();
		points = new ArrayList <Point> ();
	}
	
	public void addStation (Station station) {
		stations.add(station);
	}
	
	public void removeStation (Station station) {
		stations.remove(station);
	}
	
	public void removeStation (int index) {
		stations.remove(index);
	}
	
	public boolean addPoint (Point point) {
		for(Station station: stations) {
			if (checkPoint(station, point)) {
				if (!station.isUsed()) {
					station.setUsed(true);
					points.add(point);
					return true;
				}
			}
		}
		return false;
	}
	
	public void removePoint (Point point) {
		points.remove(point);
	}
	
	public void removePoint (int index) {
		points.remove(index);
	}
	
	private boolean checkPoint (Station station, Point point)
	{
		if (Math.pow((double) (point.getX() - station.getPosition().getX()), 2)
				+ Math.pow((double) (point.getY() - station.getPosition().getY()), 2) <= (station.getRadius())
						* (station.getRadius()))
			return true;
		else return false;
	}
}
