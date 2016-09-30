package view;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Area;
import model.GameObject;
import model.Train;

public class GameView
{
	private JFrame frame;
	private JPanel panel;
	private Graphics2D g;
	
	public GameView(int width, int height) throws IOException
	{
		frame = new JFrame();
		frame.setSize(width, height);
		panel = (JPanel) frame.getContentPane();
		panel.setVisible(true);
		frame.setResizable(false);
		frame.setVisible(true);
		g = (Graphics2D)panel.getGraphics();
		Image img = (Image)ImageIO.read(new File("./resources/textures/field.jpg"));
		g.drawImage(img, 0, 0, null);
	}
	
	public void drawObject(GameObject train) throws IOException
	{
		try
		{
			Train train1 = (Train)train;
			g.drawImage(train1.getTexture(), train1.getPosition().x, train1.getPosition().y, null);
		}
		catch(Exception e)
		{
			Area area = (Area)train;
			g.setStroke(new BasicStroke(2));
			g.setColor(area.getColor());
			g.drawOval(area.getPosition().x - area.getRadius(), area.getPosition().y - area.getRadius(), area.getWidth(), area.getHeight());
		}
	}
	
	public JFrame getFrame()
	{
		return frame;
	}
	
	public JPanel getPanel()
	{
		return panel;
	}
}
