package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import mvc.Controller;
import mvc.View;
import startPoint.StartPoint;

public class MenuAddAction implements ActionListener
{
	private View view;
	
	public MenuAddAction(View view)
	{
		this.view = view;
	}
	
	public void actionPerformed(ActionEvent event)
	{
		Controller controller = StartPoint.getMap().get(view.getFrame());
		controller.addAction();
		view.refresh();
	}
}