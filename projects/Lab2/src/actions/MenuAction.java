package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenuItem;

import mvc.Controller;
import mvc.View;
import startPoint.StartPoint;

public class MenuAction implements ActionListener
{
	private View view;
	
	public MenuAction(View view)
	{
		this.view = view;
	}
	
	public void actionPerformed(ActionEvent event)
	{
		JMenuItem item = (JMenuItem)event.getSource();
		JFrame frame = view.getFrame();
		Controller controller = StartPoint.getMap().get(frame);
		switch(item.getText())
		{
		case "Add record":
			controller.addAction(); break;
		case "Save":
			controller.saveAction(); break;
		case "Save as":
			controller.saveAsAction(); break;
		case "Open file":
			controller.openAction(); break;
		}
		view.refresh();
	}
}