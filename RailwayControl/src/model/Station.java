package model;

import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class Station implements GameObject {
	private int width, height, radius;
	private Point coords;
	private Color color;
	private List <Color> colors;
	private String orientation = LEFT;
	private static final String LEFT = "LEFT";
	private static final String BOTTOM = "BOTTOM";
	private static final String RIGHT = "RIGHT";
	private static final Color DISABLE = Color.RED;
	private static final Color ENABLE = Color.YELLOW;
	private static final Color USED = Color.GREEN;
	private List <Image> timerNumbers;
	private int curNumber = 0;
	
	public Station(int radius, Point coords) throws IOException {
		calcOrientation(coords);
		colors = new ArrayList <Color> ();
		colors.add(DISABLE);
		colors.add(ENABLE);
		colors.add(USED);
		setRadius(radius);
		setWidth(2 * radius);
		setHeight(2 * radius);
		setPosition(coords);
		color = DISABLE;
		fillNumbersList();
	}
	
	public Station(int radius, Point coords, Color color) throws IOException {
		calcOrientation(coords);
		colors = new ArrayList <Color> ();
		colors.add(DISABLE);
		colors.add(ENABLE);
		colors.add(USED);
		setRadius(radius);
		setWidth(2 * radius);
		setHeight(2 * radius);
		setPosition(coords);
		this.color = color;
		fillNumbersList();
	}

	private void fillNumbersList() throws IOException {
		timerNumbers = new ArrayList <Image> ();
		timerNumbers.add((Image) ImageIO.read(new File("./resources/textures/number/0.png")));
		timerNumbers.add((Image) ImageIO.read(new File("./resources/textures/number/1.png")));
		timerNumbers.add((Image) ImageIO.read(new File("./resources/textures/number/2.png")));
		timerNumbers.add((Image) ImageIO.read(new File("./resources/textures/number/3.png")));
	}
	
	public Image getNumberImage() {
		return timerNumbers.get(curNumber);
	}
	
	public void setCurNumber(int number) {
		curNumber = number;
	}
	
	private void calcOrientation(Point coords) {
		if (coords.x == 0) orientation = LEFT;
		else if (coords.y == 574) orientation = BOTTOM;
		else if (coords.x == 800) orientation = RIGHT;
	}
	
	public Color getColor() {
		return color;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public void setWidth(int width) {
		this.width = width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public Point getPosition() {
		return coords;
	}

	@Override
	public void setPosition(Point coords) {
		this.coords = coords;
	}

	public String getOrientation() {
		return orientation;
	}
	
	public void setUsingType(int mod) {
		this.color = colors.get(mod);
	}
	
	public int getUsingType() {
		if (color == colors.get(0))
			return 0;
		else if (color == colors.get(1))
			return 1;
		else if (color == colors.get(2))
			return 2;
		else return -1;
	}
}
