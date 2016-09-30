import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ListenerFieldToBox implements ActionListener
{
	private JTextField text;
	private JComboBox <String> box;
	
	public ListenerFieldToBox(JTextField aText, JComboBox <String> aBox) 
	{
		text = aText;
		box = aBox;
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		int t = 0;
		if(text.getText().length() != 0)
		{
			for(int i = 0; i < box.getItemCount(); i++)
			{
				if (text.getText().equals(box.getItemAt(i).toString()))
				{
					JOptionPane.showMessageDialog(new JFrame(), "This item already exists", "Error", JOptionPane.ERROR_MESSAGE);
					t = 1;
					break;
				}
			}
			if(t == 0)
			{
				box.addItem(text.getText());
			}
		}
	}
}
