package controller;

import java.awt.Color;
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
	private GameObject dest;
	private List <Point> ways;
	private List <Point> tempWay;
	private RailwayListener rListener;
	private boolean validWay;
	private static final long DELAY = 50;
	private long lastTime;
	
	public GameController() throws IOException
	{
		view = new GameView(800, 600);
		initiateGame();
	}
	
	private void initiateGame() throws IOException
	{
		lastTime = System.currentTimeMillis();
		
		areas = new ArrayList <GameObject> ();
		ways = new ArrayList <Point> ();
		tempWay = new ArrayList <Point> ();
		
		dest = new Area(40, new Point(400, 500), Color.blue);
		
		train = new Train((Image)ImageIO.read(new File("./resources/textures/train.jpg")));
		train.setPosition(new Point(50, 50));
		
		areas.add(new Area(40, new Point(view.getFrame().getWidth(), 100)));
		areas.add(new Area(40, new Point(0, 200)));
		
		rListener = new RailwayListener(view.getPanel(), this);
		
		view.drawObject(train);
		view.drawObject(dest);
		for(int areaIndex = 0; areaIndex < areas.size(); areaIndex++)
		{
			view.drawObject(areas.get(areaIndex));
		}
	}
	
	public void refreshView(Point point, int pointType) throws IOException
	{
		switch(pointType)
		{
		case 0:
		{
			Area area = new Area();
			for(int index = 0; index < areas.size(); index++)
			{
				area = (Area)areas.get(index);
				if(Math.pow((double)(point.getX() - area.getPosition().getX()), 2) + Math.pow((double)(point.getY() - area.getPosition().getY()), 2) <= 
						(area.getRadius()) * (area.getRadius()))
				{
					if(!area.isUsed())
					{
						validWay = true;
						area.setUsed(true);
					}
					break;
				}
			}
			if(validWay)
			{
				tempWay.clear();
				tempWay.add(null);
				tempWay.add(area.getPosition());
				repaintView();
			}
		} break;
		case 1:
		{
			if(Math.pow((double)(point.getX() - dest.getPosition().getX()), 2) + Math.pow((double)(point.getY() - dest.getPosition().getY()), 2) > 
					(dest.getWidth() / 2) * (dest.getWidth() / 2))
				validWay = false;
			if(validWay)
			{
				tempWay.add(dest.getPosition());
				tempWay.add(null);
				validWay = false;
				ways.addAll(tempWay);
				repaintView();
			}
			else
			{
				tempWay.clear();
				lastTime = System.currentTimeMillis() - DELAY - 1;
				repaintView();
			}
		} break;
		case 2:
		{
			if(validWay)
			{
				tempWay.add(point);
				repaintView();
			}
		}
		}
	}
	
	private void repaintView() throws IOException
	{
		long curTime = System.currentTimeMillis();
		if(curTime - lastTime > DELAY)
		{
			List <Point> allWays = new ArrayList <Point> (ways);
			allWays.addAll(tempWay);
			List <GameObject> objects = new ArrayList <GameObject> (areas);
			objects.add(dest);
			objects.add(train);
			view.repaint(allWays, objects);
			lastTime = curTime;
		}
	}
	
	public static void main(String[] args) throws IOException
	{
		new GameController();
	}
}
