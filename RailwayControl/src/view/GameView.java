package view;

import java.awt.BasicStroke;
import java.awt.Canvas;
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
import javax.swing.JFrame;
import javax.swing.JPanel;
import model.Station;
import model.GameObject;
import model.Path;
import model.Train;

public class GameView extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private Graphics2D g;
	private List <Station> stations;
	private List <Train> trains;
	private List <Path> ways;
	private List <GameObject> objects;
	private static final int RailwayWidth = 10;
	private static final int SleeperInterval = 20;
	private int intervalCounter = 0;
	private Image background;

	public GameView(int width, int height) throws IOException {
		background = (Image) ImageIO.read(new File("./resources/textures/background.jpg"));
		objects = new ArrayList <GameObject> ();
		ways = new ArrayList <Path> ();
		this.setSize(width, height);
		this.setVisible(true);
		this.setResizable(false);
	}
	
	public void addGameObject(GameObject object) {
		objects.add(object);
	}
	
	public void removeObject(GameObject object) {
		objects.remove(object);
	}
	
	public void addPath(Path path) {
		ways.add(path);
	}
	
	public Path getCurPath() {
		return ways.get(ways.size() - 1);
	}
	
	public void removeCurPath() {
		ways.remove(ways.size() - 1);
	}
	
	public void removePath(GameObject path) {
		ways.remove(path);
	}

	@Override
	public void paint(Graphics graphics) {
		Graphics2D graphics2d = (Graphics2D) graphics;
		BufferedImage buffer = (BufferedImage)createImage(this.getWidth(), this.getHeight());
		g = buffer.createGraphics();
		g.drawImage(background, 0, 0, null);
		for(GameObject object: objects) {
			try {
				drawObject(object);
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		for(Path path: ways) {
			drawPath(path);
		}
		graphics2d.drawImage(buffer, 0, 26, null);
	}
	
	public void drawObject(GameObject object) throws IOException {
		try {
			Train train = (Train) object;
			g.drawImage(train.getTexture(), train.getPosition().x, train.getPosition().y, null);
		} catch (Exception e) {
			Station station = (Station) object;
			g.setStroke(new BasicStroke(2));
			g.setColor(station.getColor());
			g.drawOval(station.getPosition().x - station.getRadius(), station.getPosition().y - station.getRadius(),
					station.getWidth(), station.getHeight());
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

	public JPanel getPanel() {
		return (JPanel)this.getContentPane();
	}
}
