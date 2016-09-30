package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import componets.InteractionComp;
import mvc.Controller;
import mvc.View;
import startPoint.StartPoint;

public class SearchAction implements ActionListener
{
	private View view;
	private InteractionComp comp;
	
	public SearchAction(View view, InteractionComp comp)
	{
		this.view = view;
		this.comp = comp;
	}
	
	public void actionPerformed(ActionEvent event)
	{
		int mod = comp.getMod();
		JTextField field1 = comp.getField(1);
		JTextField field2 = comp.getField(2);
		Controller controller = StartPoint.getMap().get(view.getFrame());
		switch(mod)
		{
		case 0:
		{
			if(field1.getText().length() < 3 || field2.getText().length() < 3)
				JOptionPane.showMessageDialog(new JFrame(), "Each field must contain minimum 3 symbols", "Warning", JOptionPane.WARNING_MESSAGE);
			else
			{
				controller.Search(0, field1.getText(), field2.getText());
				view.refreshSearchFrame();
			}
		}break;
		case 1:
		{
			if(field1.getText().length() < 3 || field2.getText().length() < 3)
				JOptionPane.showMessageDialog(new JFrame(), "Each field must contain minimum 3 symbols", "Warning", JOptionPane.WARNING_MESSAGE);
			else
			{
				controller.Search(1, field1.getText(), field2.getText());
				view.refreshSearchFrame();
			}
		}break;
		case 2:
		{
			if(field1.getText().length() < 3 || field2.getText().length() < 3)
				JOptionPane.showMessageDialog(new JFrame(), "Each field must contain minimum 3 symbols", "Warning", JOptionPane.WARNING_MESSAGE);
			else
			{
				controller.Search(2, field1.getText(), field2.getText());
				view.refreshSearchFrame();
			}
		}break;
		}
	}
}