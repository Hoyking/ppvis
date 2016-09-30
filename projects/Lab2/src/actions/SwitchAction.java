package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import componets.InteractionComp;

public class SwitchAction implements ActionListener
{
	InteractionComp comp;
	
	public SwitchAction(InteractionComp comp)
	{
		this.comp = comp;
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		JButton button = (JButton)e.getSource();
		comp.setMod(comp.getMod(button));
		comp.refresh();
	}
}
