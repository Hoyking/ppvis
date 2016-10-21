package gui;

import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import action.ButtonAction;

public class Button {
	private Image buttonPressed;
	private Image buttonUnpressed;
	private List <Image> textures;
	private String description;
	public static final int UNPRESSED = 0;
	public static final int PRESSED = 1;
	private int mod = UNPRESSED;
	private Point position;
	private Point descriptionPosition;
	private ButtonAction ba;
	
	public Button(String text, int x, int y, ButtonAction ba) {
		try {
			buttonPressed = (Image) ImageIO.read(new File("./resources/textures/pressed.png"));
			buttonUnpressed = (Image) ImageIO.read(new File("./resources/textures/unpressed.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.ba = ba;
		textures = new ArrayList <Image> ();
		textures.add(buttonUnpressed);
		textures.add(buttonPressed);
		this.description = text;
		position = new Point(x, y);
		locateDescription();
	}
	
	public void performAction() {
		ba.action();
	}
	
	private void locateDescription() {
		int buttonWidth = textures.get(mod).getWidth(null);
		int buttonHeight = textures.get(mod).getHeight(null);
		int width = description.length();
		descriptionPosition = new Point(position.x + (buttonWidth - width * 7) / 2, position.y + buttonHeight / 2 + 5);
	}
	
	public Image getImage() {
		return textures.get(mod);
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setMod(int mod) {
		this.mod = mod;
		locateDescription();
	}
	
	public int getMod() {
		return mod;
	}
	
	public void setPosition(int x, int y) {
		position.setLocation(x, y);
	}
	
	public Point getPosition() {
		return position;
	}
	
	public Point getDescriptionPosition() {
		return descriptionPosition;
	}
	
	public void setDescriptionPosition(int x, int y) {
		descriptionPosition.setLocation(x, y); 
	}
	
	public int getWidth() {
		return textures.get(mod).getWidth(null);
	}
	
	public int getHeight() {
		return textures.get(mod).getHeight(null);
	}
}
