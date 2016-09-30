package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import mvc.View;

public class ExitAction implements ActionListener
{
	View view;
	
	public ExitAction(View view)
	{
		this.view = view;
	}

	public void actionPerformed(ActionEvent event) 
	{
		view.getFrame().setVisible(false);
		System.exit(0);
	}
}
