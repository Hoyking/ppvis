package model;

import java.awt.Point;
import java.io.IOException;

public class Shedule {
	private Path path;
	private Train train;
	private Station station;
	private Point curStationPoint;
	private Point lastPoint;
	private boolean started;

	public Shedule(Train train, Path path, Station station) {
		this.lastPoint = new Point(400, -20);
		this.train = train;
		this.path = path;
	}
	
	public Shedule(Station station, int textureNum) throws IOException {
		this.lastPoint = new Point(400, -20);
		train = new Train();
		this.station = station;
		train.setTexture(textureNum);
		train.setPosition(station.getPosition());
		curStationPoint = station.getPosition();
	}
	
	public void removePath() {
		path.clearPath();
		path = null;
	}
	
	public Path getPath() {
		return path;
	}

	public void setPath(Path path) {
		this.path = path;
	}

	public Train getTrain() {
		return train;
	}

	public void setTrain(Train train) {
		this.train = train;
	}
	
	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}
	
	public Point getCurStationPoint() {
		return curStationPoint;
	}

	public void setCurStationPoint(Point prevPos) {
		this.curStationPoint = prevPos;
	}
	
	public boolean isStarted() {
		return started;
	}

	public void setStarted(boolean started) {
		this.started = started;
	}
	
	public Point getLastPoint() {
		return lastPoint;
	}
}
