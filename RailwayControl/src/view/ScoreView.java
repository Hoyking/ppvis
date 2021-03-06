package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import action.MainMenuAction;
import action.RemoveStatsAction;
import gui.Button;
import listener.ButtonListener;
import model.Score;

public class ScoreView extends JPanel{
	private static final long serialVersionUID = 1L;
	private Image background;
	private Button back;
	private Button removeStats;
	
	public ScoreView(int width, int height) throws IOException {
		background = (Image) ImageIO.read(new File("./resources/textures/background.jpg"));
		back = new Button("BACK", 270, 400, new MainMenuAction());
		removeStats = new Button("REMOVE STATS", 420, 400, new RemoveStatsAction());
		this.setSize(width, height);
		this.setVisible(true);
		List <Button> buttons = new ArrayList <Button> ();
		buttons.add(back);
		buttons.add(removeStats);
		ButtonListener bl = new ButtonListener(this, buttons);
		this.addMouseListener(bl);
	}
	
	@Override
	public void paint(Graphics graphics) {
		Graphics2D graphics2d = (Graphics2D) graphics;
		BufferedImage buffer = (BufferedImage)createImage(this.getWidth(), this.getHeight());
		Graphics2D g = buffer.createGraphics();
		g.drawImage(background, 0, 0, null);
		g.drawImage(back.getImage(), back.getPosition().x, back.getPosition().y, null);
		g.drawImage(removeStats.getImage(), removeStats.getPosition().x, removeStats.getPosition().y, null);
		g.setColor(new Color(122, 73, 24));
		g.drawString(back.getDescription(), back.getDescriptionPosition().x, back.getDescriptionPosition().y);
		g.drawString(removeStats.getDescription(), removeStats.getDescriptionPosition().x, removeStats.getDescriptionPosition().y);
		g.setFont(new Font("Arial", Font.BOLD, 18));
		for(int index = 0; index < Score.size() - 1; index++) {
			g.drawString((index + 1) + " ------------------------------------------ " + Score.get(index), 255, 100 + 25 * index);
		}
		g.drawString(10 + " ---------------------------------------- " + Score.get(9), 255, 100 + 25 * 9);
		g.setColor(Color.YELLOW);
		g.setStroke(new BasicStroke(3));
		g.drawRoundRect(230, 75, 335, 265, 10, 10);
		graphics2d.drawImage(buffer, 0, 0, null);
	}
}
