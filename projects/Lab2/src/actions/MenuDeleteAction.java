package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;

import mvc.Controller;
import mvc.View;
import startPoint.StartPoint;

public class MenuDeleteAction implements ActionListener
{
	private View view;
	
	public MenuDeleteAction(View view)
	{
		this.view = view;
	}
	
	public void actionPerformed(ActionEvent event)
	{
		JMenuItem item = (JMenuItem)event.getSource();
		Controller controller = StartPoint.getMap().get(view.getFrame());
		switch(item.getText())
		{
		case "Deleting by phone number and surname":
		{
			controller.changeDeleteMod(0);
			controller.deleteAction();
			view.refresh();
		} break;
		case "Deleting by phone number and adress":
		{
			controller.changeDeleteMod(1);
			controller.deleteAction();
			view.refresh();
		} break;
		case "Deleting by part of phone number and surname":
		{
			controller.changeDeleteMod(2);
			controller.deleteAction();
			view.refresh();
		} break;
		}
	}
}