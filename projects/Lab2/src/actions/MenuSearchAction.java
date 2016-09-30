package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;

import mvc.Controller;
import mvc.View;
import startPoint.StartPoint;

public class MenuSearchAction implements ActionListener
{
	private View view;
	
	public MenuSearchAction(View view)
	{
		this.view = view;
	}
	
	public void actionPerformed(ActionEvent event)
	{
		JMenuItem item = (JMenuItem)event.getSource();
		Controller controller = StartPoint.getMap().get(view.getFrame());
		switch(item.getText())
		{
		case "Searching by phone number and surname":
		{
			controller.searchAction();
			controller.changeSearchMod(0);
			view.refresh();
			//view.refreshSearchMod();
		}break;
		case "Searching by phone number and adress":
		{
			controller.searchAction();
			controller.changeSearchMod(1);
			view.refresh();
			//view.refreshSearchMod();
		}break;
		case "Searching by part of phone number and surname":
		{
			controller.searchAction();
			controller.changeSearchMod(2);
			view.refresh();
			//view.refreshSearchMod();
		}break;
		}
	}
}