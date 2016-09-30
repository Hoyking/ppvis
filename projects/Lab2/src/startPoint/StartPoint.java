package startPoint;

import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;

import mvc.*;

public class StartPoint 
{
	private static Map<JFrame, Controller> map;
	
	public static void main(String[] args)
	{
		Model model = new Model();
		View view = new View(model);
		Controller controller = new Controller(model, view);
		map = new HashMap<JFrame, Controller>();
		map.put(view.getFrame(), controller);
	}
	
	public static Map<JFrame, Controller> getMap()
	{
		return map;
	}
}
