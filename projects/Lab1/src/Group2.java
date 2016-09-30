import java.awt.Dimension;
import javax.swing.*;

public class Group2
{
	private JTextField field;
	private JButton button1;
	private JButton button2;
	private JPanel boxPanel;
	private JPanel BoxPanel;
	private JPanel panel;
	JLabel label;
	
	public Group2()
	{
		panel = new JPanel();
		label = new JLabel("Group 2:");
		BoxPanel = new JPanel();
		BoxPanel.setLayout(new BoxLayout(BoxPanel, BoxLayout.LINE_AXIS));
		boxPanel = new JPanel();
		boxPanel.setLayout(new BoxLayout(boxPanel, BoxLayout.PAGE_AXIS));
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		field = new JTextField(10);
		field.setMaximumSize(field.getPreferredSize());
        field.setMinimumSize(field.getPreferredSize());
		button1 = new JButton(" ");
		button1.setMaximumSize(field.getPreferredSize());
        button1.setMinimumSize(field.getPreferredSize());
		button2 = new JButton(" ");
		button2.setMaximumSize(field.getPreferredSize());
        button2.setMinimumSize(field.getPreferredSize());
		display();
	}
	
	private void display()
	{
		//button 1
		ListenerButtonSwitch listener1 = new ListenerButtonSwitch(field, button1, button2);
		button1.addActionListener(listener1);
		boxPanel.add(button1);
		boxPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		//button 2
		ListenerButtonSwitch listener2 = new ListenerButtonSwitch(field, button2, button1);
		button2.addActionListener(listener2);
		boxPanel.add(button2);
		//adding components to the BoxPanel
		BoxPanel.add(field);
		BoxPanel.add(Box.createRigidArea(new Dimension(20, 0)));
		BoxPanel.add(boxPanel);
		//adding components to the Group2 panel
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
