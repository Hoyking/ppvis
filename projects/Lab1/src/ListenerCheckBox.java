import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ListenerCheckBox implements ActionListener
{
	private JTextField field;
	private JCheckBox[] box;
	
	public ListenerCheckBox(JTextField aField, JCheckBox[] aBox)
	{
		field = aField;
		box = aBox;
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		switch (field.getText().toString())
		{
		case "1":
			box[0].setSelected(!box[0].isSelected()); break;
		case "2":
			box[1].setSelected(!box[1].isSelected()); break;
		case "3":
			box[2].setSelected(!box[2].isSelected()); break;
		default:
			JOptionPane.showMessageDialog(new JFrame(), "Incorrect name", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
