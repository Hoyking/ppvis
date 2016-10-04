package model;

import java.awt.Point;

public interface GameObject {
	public int getWidth();

	public void setWidth(int width);

	public int getHeight();

	public void setHeight(int height);

	public Point getPosition();

	public void setPosition(Point coords);
}
