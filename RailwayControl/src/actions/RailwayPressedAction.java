package actions;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import model.Station;

public class RailwayPressedAction {
	private List <Station> stations;
	
	public RailwayPressedAction() {
		stations = new ArrayList <Station> ();
	}
	
	public void addStation(Station station) {
		stations.add(station);
	}
	
	public void setStaitions(List <Station> stations) {
		this.stations = stations;
	}
	
	public Station checkPoint(Point point)
	{
		for (Station station: stations) {
			if (Math.pow((double) (point.getX() - station.getPosition().getX()), 2)
					+ Math.pow((double) (point.getY() - station.getPosition().getY()), 2) <= (station.getRadius())
							* (station.getRadius())) {
				if (!station.isUsed()) {
					station.setUsed(true);
					return station;
				}
				else return null;
			}
		}
		return null;
	}
}
