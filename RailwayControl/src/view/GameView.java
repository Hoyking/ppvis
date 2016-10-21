package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import model.Station;
import model.GameObject;
import model.Path;
import model.Shedule;
import model.Train;

public class GameView extends JPanel {
	private static final long serialVersionUID = 1L;
	private Graphics2D g;
	private List <GameObject> stations;
	private List <Shedule> shedules;
	private static final int RailwayWidth = 10;
	private static final int SleeperInterval = 5;
	private int intervalCounter = 0;
	private Image background;
	private Image station;
	private int score = 0;

	public GameView(int width, int height) throws IOException {
		background = (Image) ImageIO.read(new File("./resources/textures/background.jpg"));
		station = (Image) ImageIO.read(new File("./resources/textures/station.png"));
		stations = new ArrayList <GameObject> ();
		this.setSize(width, height);
		this.setVisible(true);
	}
	
	public void scoreIncr() {
		score++;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setSheduleList(List <Shedule> shedules) {
		this.shedules = shedules;
	}
	
	public void setStationList(List <GameObject> stations) {
		this.stations = stations;
	}
	
	public void addStation(GameObject station) {
		stations.add(station);
	}

	@Override
	public void paint(Graphics graphics) {
		Graphics2D graphics2d = (Graphics2D) graphics;
		BufferedImage buffer = (BufferedImage)createImage(this.getWidth(), this.getHeight());
		g = buffer.createGraphics();
		g.drawImage(background, 0, 0, null);
		drawFinishRailway();
		if(shedules != null)
			for(Shedule shedule: shedules) {
				if(shedule.getPath() != null)
					drawPath(shedule.getPath());
				try {
					drawObject(shedule.getTrain());
				} 
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		for(GameObject station: stations) {
			try {
				drawObject(station);
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		g.drawString("SCORE: " + score, 720, 20);
		g.drawImage(station, 345, 0, null);
		graphics2d.drawImage(buffer, 0, 0, null);
	}
	
	public void drawObject(GameObject object) throws IOException {
		Train train;
		Station station;
		try {
			train = (Train) object;
			g.drawImage(train.getTexture(), train.getPosition().x, train.getPosition().y, null);
		} 
		catch (ClassCastException e) {
			station = (Station) object;
			g.setStroke(new BasicStroke(2));
			g.setColor(station.getColor());
			g.drawOval(station.getPosition().x - station.getRadius(), station.getPosition().y - station.getRadius(),
					station.getWidth(), station.getHeight());
			if(station.getOrientation().equals("LEFT")) {
				g.drawImage(station.getNumberImage(), station.getPosition().x + 10, station.getPosition().y - 13, null);
			}
			else if(station.getOrientation().equals("RIGHT")) {
				g.drawImage(station.getNumberImage(), station.getPosition().x - 26, station.getPosition().y - 13, null);
			}
			else if(station.getOrientation().equals("BOTTOM")) {
				g.drawImage(station.getNumberImage(), station.getPosition().x - 8, station.getPosition().y - 36, null);
			}
		}
	}
	
	public void drawPath(Path path) {
		intervalCounter = 0;
		for(int index = 0; index < path.size() - 1; index++) {
			drawRailway(path.get(index), path.get(index + 1));
		}
	}

	private void drawRailway(Point point1, Point point2)
	{
		double x1 = point1.getX();
		double y1 = point1.getY();
		double x2 = point2.getX();
		double y2 = point2.getY();
		double coef = RailwayWidth / (Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)));
		g.setStroke(new BasicStroke(2));
		g.setColor(Color.GRAY);
		g.drawLine((int) (coef * (y2 - y1) + x1), (int) (-coef * (x2 - x1) + y1), (int) (coef * (y2 - y1) + x2),
				(int) (-coef * (x2 - x1) + y2));
		g.drawLine((int) (-coef * (y2 - y1) + x1), (int) (coef * (x2 - x1) + y1), (int) (-coef * (y2 - y1) + x2),
				(int) (coef * (x2 - x1) + y2));
		if (intervalCounter >= SleeperInterval)
		{
			g.setColor(new Color(139, 69, 19));
			g.drawLine((int) (-coef * (y2 - y1) + x2), (int) (coef * (x2 - x1) + y2), (int) (coef * (y2 - y1) + x2),
					(int) (-coef * (x2 - x1) + y2));
			intervalCounter = 0;
		}
		else
			intervalCounter++;
	}
	
	private void drawFinishRailway() {
		drawRailway(new Point(400, 90), new Point(400, -20));
	}

	public JPanel getPanel() {
		return this;
	}
}
