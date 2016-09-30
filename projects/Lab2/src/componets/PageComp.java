package componets;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import actions.PageAction;
import student.Student;

public class PageComp 
{
	private List <Student> students;
	private int rowNum;
	private int page = 0;
	private JButton prev;
	private JButton next;
	private JButton last;
	private JButton first;
	private JLabel pageLabel;
	private JTable table;
	private JPanel panel;
	
	public PageComp(List <Student> students, int rowNum)
	{
		this.students = students;
		this.rowNum = rowNum;
		panel = new JPanel(new BorderLayout());
		table = new JTable(new StTableModel());
		first = new JButton("<<");
		first.addActionListener(new PageAction(this));
		prev = new JButton("<");
		prev.addActionListener(new PageAction(this));
		pageLabel = new JLabel((page + 1) + "");
		pageLabel.setHorizontalAlignment(JLabel.CENTER);
		next = new JButton(">");
		next.addActionListener(new PageAction(this));
		last = new JButton(">>");
		last.addActionListener(new PageAction(this));
		draw();
	}
	
	private void draw()
	{
		JPanel pagePanel = new JPanel(new GridLayout(1, 5));
		pagePanel.add(first);
		pagePanel.add(prev);
		pagePanel.add(pageLabel);
		pagePanel.add(next);
		pagePanel.add(last);
		JPanel tablePanel = new JPanel(new BorderLayout());
		tablePanel.add(table.getTableHeader(), BorderLayout.NORTH);
		tablePanel.add(table, BorderLayout.CENTER);
		tablePanel.add(pagePanel, BorderLayout.SOUTH);
		panel.add(tablePanel, BorderLayout.SOUTH);
	}
	
	public void refresh()
	{
		pageLabel.setText((page + 1) + "");
		table.repaint();
	}
	
	public JPanel getPanel()
	{
		return panel;
	}
	
	public int getStudentCount()
	{
		return students.size();
	}
	
	public int getRowNum()
	{
		return rowNum;
	}
	
	public int getCurrentPage()
	{
		return page;
	}

	public void setCurrentPage(int page)
	{
		this.page = page;
		refresh();
	}
	
	public class StTableModel implements TableModel
	{
	private static final int NAME_COL = 0;
	private static final int ADD_COL = 1;
	private static final int MOB_COL = 2;
	private static final int HOME_COL = 3;
	private Set<TableModelListener> listeners = new HashSet<TableModelListener>();
	
	public void addTableModelListener(TableModelListener listener) 
	{
	    listeners.add(listener);
	}
	 
	public void removeTableModelListener(TableModelListener listener) 
	{
	    listeners.remove(listener);
	}
	
	public Class<String> getColumnClass(int columnIndex) 
	{
	    return String.class;
	}
	
	public int getColumnCount() 
	{
	    return 4;
	}
	
	public String getColumnName(int column) 
	{
	    switch (column) 
	    {
	        case NAME_COL:
	            return "ФИО студента";
	        case ADD_COL:
	            return "Адрес прописки";
	        case MOB_COL:
	            return "Мобильный телефон";
	        case HOME_COL:
	        	return "Городской телефон";
	    }
	    return "";
	}
	
	public int getRowCount() 
	{
	    return rowNum;
	}
	
	public Object getValueAt(int rowIndex, int columnIndex) 
	{
		Student student;
		try
		{
			student = students.get(page * rowNum + rowIndex);
			switch (columnIndex) 
			{
	        case NAME_COL:
	            return student.toString();
	        case ADD_COL:
	            return student.getAdress().toString();
	        case MOB_COL:
	            return student.getMobPhone();
	        case HOME_COL:
	        	return student.getHomePhone();
	        }
			return "";
		}
		catch(Exception e)
		{
			return "";
		}
	}
	
	public boolean isCellEditable(int rowIndex, int columnIndex) 
	{
	    return false;
	}

	public void setValueAt(Object arg0, int arg1, int arg2) {}
	}
	
}
