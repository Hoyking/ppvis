import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ListenerRadioButton implements ActionListener
{
	private JTextField field;
	private JRadioButton[] rButton;
	
	public ListenerRadioButton(JTextField aField, JRadioButton[] aRButton)
	{
		field = aField;
		rButton = aRButton;
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		switch(field.getText().toString())
		{
		case "":
			break;
		case "1":
		{
			rButton[0].setSelected(true);
			rButton[1].setSelected(false);
			rButton[2].setSelected(false);
		} break;
		case "2":
		{
			rButton[0].setSelected(false);
			rButton[1].setSelected(true);
			rButton[2].setSelected(false);
		} break;
		case "3":
		{
			rButton[0].setSelected(false);
			rButton[1].setSelected(false);
			rButton[2].setSelected(true);
		} break;
		default:
			JOptionPane.showMessageDialog(new JFrame(), "Incorrect name", "Error", JOptionPane.ERROR_MESSAGE); 
			break;
		}
	}
}
