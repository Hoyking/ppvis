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
import action.*;
import gui.Button;
import listener.ButtonListener;

public class GameOverView extends JPanel{
	private static final long serialVersionUID = 1L;
	private Image background;
	private Image gameover;
	private Button restart;
	private Button mainMenu;
	private int score;
	
	public GameOverView(int width, int height, int score) throws IOException {
		background = (Image) ImageIO.read(new File("./resources/textures/background.jpg"));
		gameover = (Image) ImageIO.read(new File("./resources/textures/gameover.png"));
		restart = new Button("RESTART", 280, 350, new NewGameAction());
		mainMenu  = new Button("MAIN MENU", 420, 350, new MainMenuAction());
		this.setSize(width, height);
		this.setVisible(true);
		this.score = score;
		List <Button> buttons = new ArrayList <Button> ();
		buttons.add(restart);
		buttons.add(mainMenu);
		ButtonListener bl = new ButtonListener(this, buttons);
		this.addMouseListener(bl);
	}
	
	@Override
	public void paint(Graphics graphics) {
		Graphics2D graphics2d = (Graphics2D) graphics;
		BufferedImage buffer = (BufferedImage)createImage(this.getWidth(), this.getHeight());
		Graphics2D g = buffer.createGraphics();
		g.drawImage(background, 0, 0, null);
		g.drawImage(gameover, 271, 70, null);
		g.drawImage(restart.getImage(), restart.getPosition().x, restart.getPosition().y, null);
		g.drawImage(mainMenu.getImage(), mainMenu.getPosition().x, mainMenu.getPosition().y, null);
		g.setColor(new Color(122, 73, 24));
		g.drawString(restart.getDescription(), restart.getDescriptionPosition().x, restart.getDescriptionPosition().y);
		g.drawString(mainMenu.getDescription(), mainMenu.getDescriptionPosition().x, mainMenu.getDescriptionPosition().y);
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.drawString("SCORE: " + score, 255, 320);
		g.drawString("HIGH SCORE: " + score, 395, 320);
		g.setColor(Color.YELLOW);
		g.setStroke(new BasicStroke(3));
		g.drawRoundRect(240, 293, 320, 40, 10, 10);
		graphics2d.drawImage(buffer, 0, 0, null);
	}
}
