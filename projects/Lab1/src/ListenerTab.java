import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ListenerTab implements ActionListener
{
	private JTable table;
	private JTextField field;
	private int mod;
	
	public ListenerTab(JTable table, JTextField field, int mod)
	{
		this.table = table;
		this.field = field;
		this.mod = mod;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		switch(mod)
		{
		case 0:
		{
			int numOfRows;
			for(numOfRows = 0; numOfRows < table.getRowCount(); numOfRows++)
			{
				if((String)table.getValueAt(numOfRows, 0) == "")
					break;
			}
			if(numOfRows == 5)
				{
					JOptionPane.showMessageDialog(new JFrame(), "The table is overfilled.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
			if(field.getText().length() != 0)
			{
				if(numOfRows != -1)
				table.getModel().setValueAt(field.getText(), numOfRows, 0);
			}
		} break;
		case 1:
		{
			int x = table.getSelectedRow();
			if(x != -1)
			{
				int y = table.getSelectedColumn();
				if(y == 1)
				{
					if((String)table.getValueAt(x, y) != "")
					{
						table.getModel().setValueAt((String)table.getValueAt(x, y), x, 0);
						table.getModel().setValueAt("", x, y);
					}
				}
			}
		} break;
		case 2:
		{
			int x = table.getSelectedRow();
			if(x != -1)
			{
				int y = table.getSelectedColumn();
				if(y == 0)
				{
					if((String)table.getValueAt(x, y) != "")
					{
						table.getModel().setValueAt((String)table.getValueAt(x, y), x, 1);
						table.getModel().setValueAt("", x, y);
					}
				}
			}
		} break;
		}
	}
}
