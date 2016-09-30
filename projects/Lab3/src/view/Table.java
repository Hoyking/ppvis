package view;

import java.util.List;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import model.Value;

public class Table 
{
	private JTable table;
	private TabModel tableModel;
	private List<Value> values;
	
	public Table()
	{
		tableModel = new TabModel();
		table = new JTable(tableModel);
		values = new ArrayList<Value>();
	}
	
	public JTable getTable()
	{
		return table;
	}
	
	public void addValue(double x, double y)
	{
		if((int)y == Integer.MAX_VALUE || (int)y == Integer.MIN_VALUE)
			values.add(new Value(x, y));
		else values.add(new Value(x, (int)y));
		tableModel.fireTableDataChanged();
		table.repaint();
	}
	
	public Value getValue(int index)
	{
		return values.get(index);
	}
	
	public int size()
	{
		return values.size();
	}
	
	public void clear()
	{
		values.clear();
		tableModel.fireTableDataChanged();
		table.repaint();
	}
	
	private class TabModel extends AbstractTableModel
	{
		private static final long serialVersionUID = 1L;
		private static final int X_COL = 0;
		private static final int Y_COL = 1;
		private static final int MIN_ROW_NUM = 28;
	
		@Override
		public Class<String> getColumnClass(int columnIndex) 
		{
			return String.class;
		}
	
		@Override
		public int getColumnCount() 
		{
			return 2;
		}
	
		@Override
		public String getColumnName(int column) 
		{
			switch (column) 
			{
	        	case X_COL:
	        		return "X value";
	        	case Y_COL:
	        		return "Y value";
			}
			return "";
		}
	
		@Override
		public int getRowCount() 
		{
			if(values.size() > MIN_ROW_NUM)
				return values.size();
			return MIN_ROW_NUM;
		}
	
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) 
		{
			Value value;
			try
			{
				value = values.get(rowIndex);
				switch (columnIndex) 
				{
				case X_COL:
					return ((Double)value.getX()).toString();
				case Y_COL:
					return ((Double)value.getY()).toString();
				}
				return "";
			}
			catch(Exception e)
			{
				return "";
			}
		}
	}
}
