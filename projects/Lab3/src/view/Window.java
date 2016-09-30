package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import controller.BuildButtonAction;
import controller.Graphic;
import controller.ZoomMouseAdapter;

public class Window 
{
	private JFrame frame;
	private JPanel tablePanel, propertyPanel;
	private Graphic graphic;
	private JScrollPane tableScroll;
	private JScrollPane graphicScroll;
	private Table table;
	private List<PropertyField> list;
	private JButton build;	
	
	public Window()
	{
		fillFrame();
	}
	
	private void fillFrame()
	{		
		frame = new JFrame("Graphic builder");
		frame.setSize(1366, 703);
		frame.setLayout(new BorderLayout());
		
		tablePanel = new JPanel();
		tablePanel.setSize(new Dimension(300, 500));
		tablePanel.setPreferredSize(new Dimension(300, 500));
		tablePanel.setLayout(new BorderLayout());
		table = new Table();
		tableScroll = new JScrollPane(table.getTable());
		tablePanel.add(tableScroll, BorderLayout.CENTER);
		
		graphic = new Graphic(1060, 675);
		graphicScroll = new JScrollPane(graphic);
		graphicProperty();
		
		propertyPanel = new JPanel();
		propertyPanel.setSize(new Dimension(300, 200));
		propertyPanel.setPreferredSize(new Dimension(300, 203));
		propertyPanel.setLayout(new BoxLayout(propertyPanel, BoxLayout.PAGE_AXIS));
		fillPropertyPanel();
		
		JPanel functionPanel = new JPanel(new BorderLayout());
		functionPanel.add(propertyPanel, BorderLayout.NORTH);
		functionPanel.add(tablePanel, BorderLayout.CENTER);
		
		frame.add(functionPanel, BorderLayout.WEST);
		frame.add(graphicScroll, BorderLayout.EAST);
		frame.setVisible(true);
		frame.setResizable(false);
	}
	
	private void graphicProperty()
	{
		graphic.addMouseWheelListener(e -> 
        {
             if (e.getWheelRotation() < 0) 
             {
                 graphic.setSize(graphic.getWidth() + 10, graphic.getHeight() + 10);
                 graphic.setPreferredSize(new Dimension(graphic.getWidth() + 10, graphic.getHeight() + 10));
                 graphic.repaint();
             } 
             else if (e.getWheelRotation() > 0) 
             {
               	 graphic.setSize(graphic.getWidth() - 10, graphic.getHeight() - 10);
                 graphic.setPreferredSize(new Dimension(graphic.getWidth() - 10, graphic.getHeight() - 10));
                 graphic.repaint();
             }
        });
		ZoomMouseAdapter adapter = new ZoomMouseAdapter(graphicScroll);
		graphic.addMouseListener(adapter);
        graphic.addMouseMotionListener(adapter);
	}
	
	private void fillPropertyPanel()
	{
		build = new JButton("Build");
		list = new ArrayList<PropertyField>();
		list.add(new PropertyField("Minimum value of the 'x' arg:"));
		list.add(new PropertyField("Maximum value of the 'x' arg:"));
		list.add(new PropertyField("Value of the 'a' param:"));
		list.add(new PropertyField("Value of the 'b' param:"));
		list.add(new PropertyField("Value of the 'c' param:"));
		build.addActionListener(new BuildButtonAction(list, table, graphic));
		propertyPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		for(int index = 0; index < list.size(); index++)
		{
			propertyPanel.add(list.get(index).getPanel());
			propertyPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		}
		propertyPanel.add(build);
	}
	
	public static void main(String[] args)
	{
		new Window();
	}
}
