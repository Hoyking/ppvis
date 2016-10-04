package model;

import java.awt.Image;
import java.awt.Point;

public class Train implements GameObject
{
	private int width, height;
	private Image texture;
	private Point coords;
	private static final int SPEED = 5;

	public Train(int width, int height)
	{
		setWidth(width);
		setHeight(height);
	}
	
	public Train(Image texture)
	{
		setTexture(texture);
	}
	
	public Image getTexture() 
	{
		return texture;
	}

	public void setTexture(Image texture) 
	{
		this.texture = texture;
	}

	@Override
	public int getWidth() 
	{
		return width;
	}

	@Override
	public void setWidth(int width) 
	{
		this.width = width;
	}

	@Override
	public int getHeight() 
	{
		return height;
	}

	@Override
	public void setHeight(int height) 
	{
		this.height = height;
	}

	@Override
	public Point getPosition() 
	{
		return coords;
	}

	@Override
	public void setPosition(Point coords) 
	{
		this.coords = coords;
	}

	public static int getSpeed() 
	{
		return SPEED;
	}
}
