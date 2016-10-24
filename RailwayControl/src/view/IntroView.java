package view;

import java.awt.Color;
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

public class IntroView extends JPanel{
	private static final long serialVersionUID = 1L;
	private Image background;
	private Image name;
	private Button newGame;
	private Button score;
	private Button exit;
	
	public IntroView(int width, int height) throws IOException {
		background = (Image) ImageIO.read(new File("./resources/textures/background.jpg"));
		name = (Image) ImageIO.read(new File("./resources/textures/name.png"));
		newGame = new Button("NEW GAME", 350, 350, new NewGameAction());
		score  = new Button("SCORE", 350, 400, new ScoreAction());
		exit = new Button("EXIT", 350, 450, new ExitAction());
		this.setSize(width, height);
		this.setVisible(true);
		List <Button> buttons = new ArrayList <Button> ();
		buttons.add(newGame);
		buttons.add(score);
		buttons.add(exit);
		ButtonListener bl = new ButtonListener(this, buttons);
		this.addMouseListener(bl);
	}
	
	@Override
	public void paint(Graphics graphics) {
		Graphics2D graphics2d = (Graphics2D) graphics;
		BufferedImage buffer = (BufferedImage)createImage(this.getWidth(), this.getHeight());
		Graphics2D g = buffer.createGraphics();
		g.drawImage(background, 0, 0, null);
		g.drawImage(name, 212, 50, null);
		g.drawImage(newGame.getImage(), newGame.getPosition().x, newGame.getPosition().y, null);
		g.drawImage(score.getImage(), score.getPosition().x, score.getPosition().y, null);
		g.drawImage(exit.getImage(), exit.getPosition().x, exit.getPosition().y, null);
		g.setColor(new Color(122, 73, 24));
		g.drawString(newGame.getDescription(), newGame.getDescriptionPosition().x, newGame.getDescriptionPosition().y);
		g.drawString(score.getDescription(), score.getDescriptionPosition().x, score.getDescriptionPosition().y);
		g.drawString(exit.getDescription(), exit.getDescriptionPosition().x, exit.getDescriptionPosition().y);
		graphics2d.drawImage(buffer, 0, 0, null);
	}
}
