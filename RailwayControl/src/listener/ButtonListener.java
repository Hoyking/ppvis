package listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JPanel;
import gui.Button;

public class ButtonListener extends MouseAdapter implements MouseListener {
	private List <Button> buttons;
	private JPanel panel;
	
	public ButtonListener(JPanel panel, List <Button> buttons) {
		this.buttons = buttons;
		this.panel = panel;
	}
	
	private boolean checkPressed(Button button, int x, int y) {
		int xPos = button.getPosition().x;
		int yPos = button.getPosition().y;
		int width = button.getWidth();
		int height = button.getHeight();
		if(x > xPos && x < xPos + width && y > yPos && y < yPos + height) 
			return true;
		return false;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		for(Button button: buttons) {
			if(checkPressed(button, e.getX(), e.getY())) {
				button.setMod(Button.PRESSED);
				panel.repaint();
			}
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		for(Button button: buttons) {
			if(button.getMod() == Button.PRESSED) {
				button.setMod(Button.UNPRESSED);
				panel.repaint();
				button.performAction();
			}
		}
	}
}
