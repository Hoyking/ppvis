import java.awt.Dimension;
import javax.swing.*;

public class Group3
{
	private JRadioButton[] rButton;
	private JButton button;
	private JTextField field;
	private JPanel BoxPanel;
	private JLabel label;
	private JPanel panel;
	
	public Group3()
	{
		panel = new JPanel();
		label = new JLabel("Group 3:");
		BoxPanel = new JPanel();
		BoxPanel.setLayout(new BoxLayout(BoxPanel, BoxLayout.LINE_AXIS));
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		field = new JTextField(2);
		field.setMaximumSize(field.getPreferredSize());
        field.setMinimumSize(field.getPreferredSize());
		button = new JButton("Select");
		rButton = new JRadioButton[3];
		for(int rButtonIndex = 0; rButtonIndex < 3; rButtonIndex++)
		{
			rButton[rButtonIndex] = new JRadioButton(" " + (rButtonIndex + 1));
		}
		display();
	}
	
	private void display()
	{		
		//adding components to the BoxPanel
		BoxPanel.add(field);
		BoxPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		ListenerRadioButton listener = new ListenerRadioButton(field, rButton);
		button.addActionListener(listener);
		BoxPanel.add(button);
		for(int rButtonIndex = 0; rButtonIndex < 3; rButtonIndex++)
		{
			BoxPanel.add(Box.createRigidArea(new Dimension(10, 0)));
			BoxPanel.add(rButton[rButtonIndex]);
		}
		//adding components to the Group3 panel
		panel.add(label);
		panel.add(Box.createRigidArea(new Dimension(0, 2)));
		panel.add(BoxPanel);
		//setting visibility
		panel.setVisible(true);
	}
	
	public void add(JPanel panel)
	{
		panel.add(this.panel);
	}
}
