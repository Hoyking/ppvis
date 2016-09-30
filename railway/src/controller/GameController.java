package controller;

import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import listeners.RailwayListener;
import model.Area;
import model.GameObject;
import model.Train;
import view.GameView;

public class GameController 
{
	private GameView view;
	private GameObject train;
	private List <GameObject> areas;
	private List <Point> ways;
	private RailwayListener rListener;
	
	public GameController() throws IOException
	{
		areas = new ArrayList <GameObject> ();
		ways = new ArrayList <Point> ();
		view = new GameView(800, 600);
		
		initiateGame();
	}
	
	private void initiateGame() throws IOException
	{
		train = new Train((Image)ImageIO.read(new File("./resources/textures/train.jpg")));
		train.setPosition(new Point(50, 50));
		view.getFrame();
		areas.add(new Area(40, new Point(view.getFrame().getWidth(), 100)));
		areas.add(new Area(40, new Point(0, 200)));
		
		rListener = new RailwayListener(view.getPanel(), this);
		
		view.drawObject(train);
		for(int areaIndex = 0; areaIndex < areas.size(); areaIndex++)
		{
			view.drawObject(areas.get(areaIndex));
		}
	}
	
	public void refreshView(Point point, int pointType)
	{
		
	}
	
	public static void main(String[] args) throws IOException
	{
		new GameController();
	}
}
