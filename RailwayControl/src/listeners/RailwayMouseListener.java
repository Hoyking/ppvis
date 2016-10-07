package listeners;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import javax.swing.JPanel;

import actions.RailwayPressedAction;
import actions.RailwayUnpressedAction;
import controller.GameController;
import model.Path;
import model.Station;

public class RailwayMouseListener extends MouseAdapter implements MouseListener, MouseMotionListener {
	private GameController gc;
	private RailwayPressedAction rPressed;
	private RailwayUnpressedAction rUnpressed;
	private boolean validWay;

	public RailwayMouseListener(JPanel panel, GameController gc) {
		panel.addMouseListener((MouseListener) this);
		panel.addMouseMotionListener((MouseMotionListener) this);
		this.gc = gc;
	}
	
	public void setPressedAction(RailwayPressedAction action) {
		rPressed = action;
	}

	public void setUnpressedAction(RailwayUnpressedAction action) {
		rUnpressed = action;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		/*try {
			gc.refreshView(new Point(e.getX(), e.getY()), 0);
		} catch (IOException e1) {
			e1.printStackTrace();
		}*/
		Point tempPoint = new Point(e.getX(), e.getY());
		Station tempStation = rPressed.checkPoint(tempPoint);
		if(tempStation != null) {
			gc.addPathToView(new Path(tempStation));
			validWay = true;
		}
		else validWay = false;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		/*try {
			gc.refreshView(new Point(e.getX(), e.getY()), 1);
		} catch (IOException e1) {
			e1.printStackTrace();
		}*/
		Point tempPoint = rUnpressed.checkPoint(new Point(e.getX(), e.getY()));
		if(validWay){
			if(tempPoint != null) {
				gc.refreshCurPath(tempPoint);
				validWay = true;
			}
			else {
				gc.setCurStationUsing(false);
				gc.removeCurPath();
				validWay = false;
			}
		}
		gc.refreshView();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		/*try {
			gc.refreshView(new Point(e.getX(), e.getY()), 2);
		} catch (IOException e1) {
			e1.printStackTrace();
		}*/
		Point tempPoint = new Point(e.getX(), e.getY());
		if (validWay) {
			gc.refreshCurPath(tempPoint);
			gc.refreshView();
		}
	}
}
