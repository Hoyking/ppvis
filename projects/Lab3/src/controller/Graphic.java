package controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import model.Value;

public class Graphic extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	private int midY, midX, lenY , lenX;
	private double step, coef;
	private List<Value> values;
	private Color color;
	 
	public Graphic(int width, int height)
	{
		super.setSize(width, height);
		super.setPreferredSize(new Dimension(width, height));
		color = new Color(255, 0, 0);
		values = new ArrayList<Value>();
		step = this.getWidth() / 106;
		coef = step / 4;
		lenX = this.getWidth() - 10;
		lenY = this.getWidth() - 10;
		midX = this.getWidth() / 2 + 5;
		midY = this.getHeight() / 2 + 5;
	}
	
	@Override
	public void repaint()
	{
		step = this.getWidth() / 106;
		coef = step / 4;
		lenX = this.getWidth() - 10;
		lenY = this.getWidth() - 10;
		midX = this.getWidth() / 2 + 5;
		midY = this.getHeight() / 2 + 5;
		super.repaint();
	}
	
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		
		g.drawLine(midX, 5, midX, 5 + lenY);
		g.drawLine(5, midY, 5 + lenX, midY);
		
		g.drawLine(midX, 5, midX - 3, 15);
		g.drawLine(midX, 5, midX + 3, 15);
		g.drawLine(5 + lenX, midY, lenX - 10, midY + 3);
		g.drawLine(5 + lenX, midY, lenX - 10, midY - 3);
		
		g.drawString("Y", midX - 15, 15);
		g.drawString("0", midX - 10, midY + 15);
		g.drawString("X", lenX - 10, midY - 15);
		
		for(int index = -(lenX / (int)step / 2) + 3; index < lenX / step / 2; index ++)
		{
			if(index % 5 == 0 && index != 0)
			{
				g.drawLine(midX - (int)step * index, midY - 4, midX - (int)step * index, midY + 4);
				g.drawString("" + (- index * 4), midX - (int)step * index, midY - 5);
			}
			else g.drawLine(midX - (int)step * index, midY - 2, midX - (int)step * index, midY + 2);
		}
		
		for(int index = -(lenY / (int)step / 2); index < lenY / (int)step / 2; index ++)
		{
			if(index % 5 == 0 && index != 0)
			{
				g.drawLine(midX - 4, midY - (int)step * index, midX + 4, midY - (int)step * index);
				g.drawString("" + (index * 4), midX - 23, midY - (int)step * index);
			}
			else g.drawLine(midX - 2, midY - (int)step * index, midX + 2, midY - (int)step * index);
		}
		
		for(int index = 1; index < values.size(); index++)
		{
			drawLine(values.get(index).getX(), values.get(index).getY(), values.get(index - 1).getX(), values.get(index - 1).getY(), g);
		}
	}
	
	public void drawLine(double x1, double y1, double x2, double y2, Graphics g)
	{
		if(Math.abs(x1) < lenX && Math.abs(x2) < lenX && Math.abs(y1) < lenY && Math.abs(y2) < lenY)
		{
			g.setColor(color);
			g.drawLine(midX + (int)(x1 * coef), midY - (int)(y1 * coef), midX + (int)(x2 * coef), midY - (int)(y2 * coef));
		}
	}
	
	public void addValue(Value value)
	{
		values.add(value);
		repaint();
	}
	
	public void addValue(double x, double y)
	{
		values.add(new Value(x, y));
		repaint();
	}
	
	public void clear()
	{
		values.clear();
		repaint();
	}
}
