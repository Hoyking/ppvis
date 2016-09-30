package controller;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import view.Table;

public class GraphicThread extends Thread
{
	private Table table;
	private Graphic graphic;
	private double minX, maxX, a, b, c;
	
	public GraphicThread(double minX, double maxX, double a, double b, double c, Table table, Graphic graphic)
	{
		this.table = table;
		this.graphic = graphic;
		this.minX = minX;
		this.maxX = maxX;
		this.a = a;
		this.b = b;
		this.c = c;
	}
	
	@Override
	public void run()
	{
		try
		{
			table.clear();
			graphic.clear();
			double h = 0.1;
			double e = Math.E;
			double x = minX;
			double y;
			while(x < maxX)
			{
				y = (Math.pow(e, -(a * x + b)))/(x * x * x - c);
				if (x > 0)
					x = (double)((int)((x + 0.05) * 10)) / 10;
				else
					x = (double)((int)((x - 0.05) * 10)) / 10;
				
				try 
				{
		            Thread.sleep(5);
		        } 
				catch (InterruptedException exception) 
				{
		            exception.printStackTrace();
		        }
				
				table.addValue(x, y);
				graphic.addValue(x, y);
				x += h;
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(new JFrame(), "Incorrect values !", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
