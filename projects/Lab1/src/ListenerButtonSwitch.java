import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ListenerButtonSwitch implements ActionListener 
{
	private JTextField field;
	private JButton button1;
	private JButton button2;
	
	public ListenerButtonSwitch(JTextField aField, JButton aButton1, JButton aButton2)
	{
		field = aField;
		button1 = aButton1;
		button2 = aButton2;
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		String currenName = field.getText();
		String button2Name = button2.getText();
		if(currenName.length() == 0)
		{
			if(button2Name.length() != 0)
			{
				button1.setText(button2Name);
				button2.setText(" ");
			}
		}
		else
		{
			button1.setText(currenName);
			button2.setText(" ");
		}
	}
}
