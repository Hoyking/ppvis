package model;

import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class Train implements GameObject {
	private int width, height;
	private List <Image> textures;
	private List <Point> offset;
	private int curTextureNum = 0;
	private Point coords;
	private static final double STEP = 5;

	public Train(int width, int height) {
		setWidth(width);
		setHeight(height);
	}

	public Train() throws IOException {
		fillTrainTexturePack();
		fillOffsetPack();
	}

	public void setTexture(Point p1, Point p2) {
		int coef = 0;
		double tg = (p2.getY() - p1.getY()) / (p2.getX() - p1.getX());
		if (p2.getX() < p1.getX())
			coef = 1;
		double angle = Math.atan(tg);
		curTextureNum = (int) (((angle * 180 / Math.PI) + 90 + 11.25) * 100) / 2250 + 8 * coef;
		if(curTextureNum == 16)
			curTextureNum--;
	}
	
	public void setTexture(int textureNum) {
		curTextureNum = textureNum;
	}
	
	public Image getTexture() {
		return textures.get(curTextureNum);
	}
	
	private void fillOffsetPack() {
		offset = new ArrayList <Point> ();
		offset.add(new Point(17, 0));
		offset.add(new Point(44, 6));
		offset.add(new Point(64, 11));
		offset.add(new Point(74, 14));
		offset.add(new Point(74, 17));
		offset.add(new Point(75, 43));
		offset.add(new Point(66, 64));
		offset.add(new Point(45, 74));
		offset.add(new Point(17, 74));
		offset.add(new Point(16, 75));
		offset.add(new Point(12, 66));
		offset.add(new Point(7, 45));
		offset.add(new Point(0, 17));
		offset.add(new Point(6, 15));
		offset.add(new Point(11, 12));
		offset.add(new Point(14, 6));
	}
	
	private void fillTrainTexturePack() throws IOException {
		textures = new ArrayList <Image> ();
		textures.add((Image) ImageIO.read(new File("./resources/textures/train0.png")));
		textures.add((Image) ImageIO.read(new File("./resources/textures/train1.png")));
		textures.add((Image) ImageIO.read(new File("./resources/textures/train2.png")));
		textures.add((Image) ImageIO.read(new File("./resources/textures/train3.png")));
		textures.add((Image) ImageIO.read(new File("./resources/textures/train4.png")));
		textures.add((Image) ImageIO.read(new File("./resources/textures/train5.png")));
		textures.add((Image) ImageIO.read(new File("./resources/textures/train6.png")));
		textures.add((Image) ImageIO.read(new File("./resources/textures/train7.png")));
		textures.add((Image) ImageIO.read(new File("./resources/textures/train8.png")));
		textures.add((Image) ImageIO.read(new File("./resources/textures/train9.png")));
		textures.add((Image) ImageIO.read(new File("./resources/textures/train10.png")));
		textures.add((Image) ImageIO.read(new File("./resources/textures/train11.png")));
		textures.add((Image) ImageIO.read(new File("./resources/textures/train12.png")));
		textures.add((Image) ImageIO.read(new File("./resources/textures/train13.png")));
		textures.add((Image) ImageIO.read(new File("./resources/textures/train14.png")));
		textures.add((Image) ImageIO.read(new File("./resources/textures/train15.png")));
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
		//return new Point(coords.x + offset.get(curTextureNum).x, coords.y + offset.get(curTextureNum).y);
		return coords;
	}
	
	public Point getOrientedPosition() {
		return new Point(coords.x + offset.get(curTextureNum).x, coords.y + offset.get(curTextureNum).y);
	}

	@Override
	public void setPosition(Point coords) {
		this.coords = new Point(coords.x - offset.get(curTextureNum).x, coords.y - offset.get(curTextureNum).y);
	}

	public static double getStep() {
		return STEP;
	}
}
