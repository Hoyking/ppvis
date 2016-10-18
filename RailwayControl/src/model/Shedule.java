package model;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Shedule {
	private Path path;
	private Train train;
	private Station station;
	private Point prevPos;
	
	public Shedule(Train train, Path path, Station station) {
		this.train = train;
		this.path = path;
	}
	
	public Shedule(Station station, int textureNum) throws IOException {
		train = new Train();
		this.station = station;
		train.setTexture(textureNum);
		train.setPosition(station.getPosition());
		prevPos = station.getPosition();
	}
	
	public void removePath() {
		path.clearPath();
		this.path = null;
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
}
