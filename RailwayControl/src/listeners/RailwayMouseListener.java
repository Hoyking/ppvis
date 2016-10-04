package listeners;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import javax.swing.JPanel;
import controller.GameController;

public class RailwayMouseListener extends MouseAdapter implements MouseListener, MouseMotionListener {
	private GameController gc;

	public RailwayMouseListener(JPanel panel, GameController gc) {
		panel.addMouseListener((MouseListener) this);
		panel.addMouseMotionListener((MouseMotionListener) this);
		this.gc = gc;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		try {
			gc.refreshView(new Point(e.getX(), e.getY()), 0);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		try {
			gc.refreshView(new Point(e.getX(), e.getY()), 1);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		try {
			gc.refreshView(new Point(e.getX(), e.getY()), 2);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
