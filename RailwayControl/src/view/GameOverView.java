package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GameOverView extends JPanel{
	private static final long serialVersionUID = 1L;
	private Image background;
	private Image gameover;
	
	public GameOverView(int width, int height) throws IOException {
		background = (Image) ImageIO.read(new File("./resources/textures/background.jpg"));
		gameover = (Image) ImageIO.read(new File("./resources/textures/gameover.png"));
		this.setSize(width, height);
		this.setVisible(true);
	}
	
	@Override
	public void paint(Graphics graphics) {
		Graphics2D graphics2d = (Graphics2D) graphics;
		BufferedImage buffer = (BufferedImage)createImage(this.getWidth(), this.getHeight());
		Graphics2D g = buffer.createGraphics();
		g.drawImage(background, 0, 0, null);
		g.drawImage(gameover, 271, 150, null);
		graphics2d.drawImage(buffer, 0, 0, null);
	}
}
