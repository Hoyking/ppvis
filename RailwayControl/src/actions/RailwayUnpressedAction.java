package actions;

import java.awt.Point;

import model.Station;

public class RailwayUnpressedAction {
	private Station station;
	
	public RailwayUnpressedAction(Station station) {
		this.station = station;
	}
	
	public void setStation(Station station) {
		this.station = station;
	}
	
	public Point checkPoint(Point point)
	{
		if (Math.pow((double) (point.getX() - station.getPosition().getX()), 2)
			+ Math.pow((double) (point.getY() - station.getPosition().getY()), 2) <= (station.getRadius()) * (station.getRadius()))
			return station.getPosition();
		return null;
	}
}
