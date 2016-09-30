package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

import mvc.Controller;
import mvc.View;
import startPoint.StartPoint;

public class ButtonAction implements ActionListener
{
	private View view;
	
	public ButtonAction(View view)
	{
		this.view = view;
	}
	
	public void actionPerformed(ActionEvent event)
	{
		JButton button = (JButton)event.getSource();
		JFrame frame = (JFrame)button.getParent().getParent().getParent().getParent().getParent();
		Controller controller = StartPoint.getMap().get(frame);
		switch(button.getText())
		{
		case "Search":
			controller.searchAction(); break;
		case "Add":
			controller.addAction(); break;
		case "Delete":
			controller.deleteAction(); break;
		case "Save":
			controller.saveAction(); break;
		case "Open":
			controller.openAction(); break;
		}
		view.refresh();
	}
}
