package actions;

import mvc.Controller;
import mvc.View;
import startPoint.StartPoint;

public class OpenAction 
{
	Controller controller;
	String filePath;
	
	public OpenAction(View view, String filePath)
	{
		this.filePath = filePath;
		controller = StartPoint.getMap().get(view.getFrame());
	}
	
	public void openFile()
	{
		controller.openFile(filePath);
	}
}
