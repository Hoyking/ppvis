package view;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PropertyField 
{
	private JLabel label;
	private JTextField field;
	private JPanel panel;
	
	public PropertyField(String text)
	{
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
		panel.setSize(new Dimension(280, 5));
		panel.setPreferredSize(new Dimension(280, 5));
		label = new JLabel(text);
		field = new JTextField(3);
		field.setMaximumSize(field.getPreferredSize());
        field.setMinimumSize(field.getPreferredSize());
		panel.add(label);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));
		panel.add(field);
	}
	
	public JPanel getPanel()
	{
		return panel;
	}
	
	public String getFieldValue()
	{
		return field.getText();
	}
}
