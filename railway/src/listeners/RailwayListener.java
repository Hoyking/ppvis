package listeners;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import controller.GameController;

public class RailwayListener implements MouseListener, MouseMotionListener
{
	private Graphics2D g;
	private List <Point> path;
	private int x1, y1, x, y;
	private GameController gc;
	
	public RailwayListener(JPanel panel, GameController gc)
	{
		panel.addMouseListener((MouseListener)this);
		panel.addMouseMotionListener((MouseMotionListener)this);
		g = (Graphics2D)panel.getGraphics();
		path = new ArrayList <Point> ();
		this.gc = gc;
	}

	@Override
	public void mousePressed(MouseEvent e) 
	{
		gc.refreshView(new Point(e.getX(), e.getY()), 0);
	}
	
	@Override
	public void mouseDragged(MouseEvent e) 
	{
		gc.refreshView(new Point(e.getX(), e.getY()), 1);
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

}
