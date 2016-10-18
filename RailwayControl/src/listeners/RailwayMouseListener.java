package listeners;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;
import actions.RailwayPressedAction;
import actions.RailwayUnpressedAction;
import controller.ActionController;
import model.Path;
import model.Station;

public class RailwayMouseListener extends MouseAdapter implements MouseListener, MouseMotionListener {
	private ActionController ac;
	private RailwayPressedAction rPressed;
	private RailwayUnpressedAction rUnpressed;
	private boolean validWay;

	public RailwayMouseListener(JPanel panel, ActionController ac) {
		panel.addMouseListener((MouseListener) this);
		panel.addMouseMotionListener((MouseMotionListener) this);
		this.ac = ac;
	}
	
	public void setPressedAction(RailwayPressedAction action) {
		rPressed = action;
	}

	public void setUnpressedAction(RailwayUnpressedAction action) {
		rUnpressed = action;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		Point tempPoint = new Point(e.getX(), e.getY());
		Station tempStation = rPressed.checkPoint(tempPoint);
		if(tempStation != null) {
			//gc.addPathToView(new Path(tempStation));
			ac.addPath(new Path(tempStation));
			validWay = true;
		}
		else validWay = false;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		Point tempPoint = rUnpressed.checkPoint(new Point(e.getX(), e.getY()));
		if(validWay){
			if(tempPoint != null) {
				ac.refreshCurPath(tempPoint);
				ac.refreshCurPath(new Point(400, -20));
				validWay = true;
			}
			else {
				//ac.setCurStationUsing(0);
				ac.removeCurPath();
				validWay = false;
			}
		}
		ac.refreshView();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		Point tempPoint = new Point(e.getX(), e.getY());
		if (validWay) {
			ac.refreshCurPath(tempPoint);
			ac.refreshView();
		}
	}
}
