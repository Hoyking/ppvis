package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import componets.PageComp;

public class PageAction implements ActionListener
{
	private PageComp comp;;
	
	public PageAction(PageComp comp)
	{
		this.comp = comp;
	}
	
	public void actionPerformed(ActionEvent event)
	{
		int last = comp.getStudentCount() / comp.getRowNum();
		if(comp.getStudentCount() % comp.getRowNum() == 0 && last != 0)
			last--;
		JButton button = (JButton)event.getSource();
		switch(button.getText())
		{
		case "<<":
		{
			comp.setCurrentPage(0);
		} break;
		case "<":
		{
			if(comp.getCurrentPage() > 0)
				comp.setCurrentPage(comp.getCurrentPage() - 1);
		} break;
		case ">":
		{
			if(comp.getCurrentPage() < last)
				comp.setCurrentPage(comp.getCurrentPage() + 1);
		} break;
		case ">>":
		{
			comp.setCurrentPage(last);
		} break;
		}
	}
}
