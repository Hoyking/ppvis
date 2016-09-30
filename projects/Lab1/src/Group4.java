import java.awt.Dimension;

import javax.swing.*;

public class Group4
{
	private JCheckBox[] box = new JCheckBox[3];
	private JButton button;
	private JTextField field;
	private JPanel boxPanel;
	private JPanel BoxPanel;
	private JLabel label;
	private JPanel panel;
	
	public Group4()
	{
		panel = new JPanel();
		label = new JLabel("Group 4:");
		boxPanel = new JPanel();
		boxPanel.setLayout(new BoxLayout(boxPanel, BoxLayout.PAGE_AXIS));
		BoxPanel = new JPanel();
		BoxPanel.setLayout(new BoxLayout(BoxPanel, BoxLayout.LINE_AXIS));
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		field = new JTextField(2);
		field.setMaximumSize(field.getPreferredSize());
        field.setMinimumSize(field.getPreferredSize());
		button = new JButton("Select");
		for(int boxIndex = 0; boxIndex < 3; boxIndex++)
		{
			box[boxIndex] = new JCheckBox(" " + (boxIndex + 1));
		}
		display();
	}
	
	private void display()
	{
		//adding components to the boxPanel
		for(int boxIndex = 0; boxIndex < 3; boxIndex++)
		{
			if(boxIndex != 0)
				boxPanel.add(Box.createRigidArea(new Dimension(0, 5)));
			boxPanel.add(box[boxIndex]);
		}
		//adding components to the BoxPanel
		ListenerCheckBox listener = new ListenerCheckBox(field, box);
		button.addActionListener(listener);
		BoxPanel.add(field);
		BoxPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		BoxPanel.add(button);
		BoxPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		BoxPanel.add(boxPanel);
		//adding components to the Group4 panel
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