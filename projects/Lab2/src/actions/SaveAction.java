package actions;

import mvc.Controller;
import mvc.View;
import startPoint.StartPoint;

public class SaveAction 
{
	Controller controller;
	String filePath;
	
	public SaveAction(View view, String filePath)
	{
		this.filePath = filePath;
		controller = StartPoint.getMap().get(view.getFrame());
	}
	
	public void saveFile()
	{
		controller.saveFile(filePath);
	}
}
