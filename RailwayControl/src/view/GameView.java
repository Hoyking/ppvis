package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import model.Station;
import model.GameObject;
import model.Train;

public class GameView {
	private JFrame frame;
	private JPanel panel;
	private Graphics2D g;
	private static final int RailwayWidth = 10;
	private static final int SleeperInterval = 15;
	private int intervalCounter = 0;

	public GameView(int width, int height) throws IOException {
		frame = new JFrame();
		frame.setSize(width, height);
		panel = new JPanel();
		frame.setContentPane(panel);
		frame.setResizable(false);
		frame.setVisible(true);
		g = (Graphics2D) panel.getGraphics();
		Image img = (Image) ImageIO.read(new File("./resources/textures/background.jpg"));
		g.drawImage(img, 0, 0, null);
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

	public void repaint(List<Point> ways, List<GameObject> objects) throws IOException {
		Image img = (Image) ImageIO.read(new File("./resources/textures/background.jpg"));
		g.drawImage(img, 0, 0, null);
		for (GameObject obj : objects) {
			drawObject(obj);
		}
		for (int index = 0; index < ways.size() - 1; index++) {
			try {
				Point fPoint = ways.get(index);
				Point lPoint = ways.get(index + 1);
				double x1 = fPoint.getX();
				double x2 = lPoint.getX();
				double y1 = fPoint.getY();
				double y2 = lPoint.getY();
				drawRailway(x1, y1, x2, y2);
			}
			catch(Exception e) {}
		}
	}

	private void drawRailway(double x1, double y1, double x2, double y2)
	{
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
	
	public JFrame getFrame() {
		return frame;
	}

	public JPanel getPanel() {
		return panel;
	}
}
