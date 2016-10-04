package model;

import java.awt.Color;
import java.awt.Point;

public class Area implements GameObject
{
	private int width, height, radius;
	private Point coords;
	private Color color;
	private boolean used;
	
	public Area() {}
	
	public Area(int radius, Point coords)
	{
		setRadius(radius);
		setWidth(2 * radius);
		setHeight(2 * radius);
		setPosition(coords);
		color = Color.RED;
	}
	
	public Area(int radius, Point coords, Color color)
	{
		setRadius(radius);
		setWidth(2 * radius);
		setHeight(2 * radius);
		setPosition(coords);
		setColor(color);
	}
	
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public int getRadius() 
	{
		return radius;
	}

	public void setRadius(int radius) 
	{
		this.radius = radius;
	}
	
	public int getWidth() 
	{
		return width;
	}

	public void setWidth(int width) 
	{
		this.width = width;
	}

	public int getHeight() 
	{
		return height;
	}

	public void setHeight(int height) 
	{
		this.height = height;
	}

	public Point getPosition() 
	{
		return coords;
	}

	public void setPosition(Point coords) 
	{
		this.coords = coords;
	}

	public boolean isUsed() 
	{
		return used;
	}

	public void setUsed(boolean used) 
	{
		this.used = used;
	}
}
