package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Path {
	private Point fPoint;
	private Point lPoint;
	private List <Point> points;
	private Station station;
	
	public Path(Station station) {
		this.station = station;
		fPoint = station.getPosition();
		points = new ArrayList <Point> ();
		points.add(fPoint);
	}
	
	public void makePath(Point point) {
		points.add(point);
	}
	
	public int size() {
		return points.size();
	}
	
	public Point get(int index) {
		return points.get(index);
	}
	
	public Station getStation() {
		return station;
	}
	
	public void clearPath() {
		points.clear();
		points.add(fPoint);
	}
}
