import java.awt.Dimension;
import javax.swing.*;

public class Group1
{
	private JTextField field;
	private JButton button;
	private JComboBox <String> box;
	private JPanel boxPanel;
	private JLabel label1;
	private JPanel panel;
	
	public Group1()
	{
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		boxPanel = new JPanel();
		boxPanel.setLayout(new BoxLayout(boxPanel, BoxLayout.LINE_AXIS));
		label1 = new JLabel("Group 1:");
		field = new JTextField(20);
		field.setMaximumSize(field.getPreferredSize());
        field.setMinimumSize(field.getPreferredSize());
		button = new JButton("Add");
		box = new JComboBox <String>();
		box.setMaximumSize(field.getPreferredSize());
        box.setMinimumSize(field.getPreferredSize());
		display();
	}
	
	private void display()
	{
		boxPanel.add(field);
		boxPanel.add(Box.createRigidArea(new Dimension(20, 0)));
		boxPanel.add(button);
		boxPanel.add(Box.createRigidArea(new Dimension(20, 0)));
		boxPanel.add(box);
		ListenerFieldToBox listener = new ListenerFieldToBox(field, box);
		button.addActionListener(listener);
		panel.add(label1);
		panel.add(Box.createRigidArea(new Dimension(0, 2)));
		panel.add(boxPanel);
		panel.setVisible(true);
	}
	
	public void add(JPanel panel)
	{
		panel.add(this.panel);
	}
}
