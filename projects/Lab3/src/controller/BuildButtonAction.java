package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import view.PropertyField;
import view.Table;

public class BuildButtonAction implements ActionListener
{
	private Table table;
	private Graphic graphic;
	private List<PropertyField> list;
	
	public BuildButtonAction(List<PropertyField> list, Table table, Graphic graphic)
	{
		this.table = table;
		this.graphic = graphic;
		this.list = list;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) 
	{
		table.clear();
		graphic.clear();
		double minX = Double.parseDouble(list.get(0).getFieldValue());
		double maxX = Double.parseDouble(list.get(1).getFieldValue());
		double a = Double.parseDouble(list.get(2).getFieldValue());
		double b = Double.parseDouble(list.get(3).getFieldValue());
		double c = Double.parseDouble(list.get(4).getFieldValue());
		GraphicThread gThread = new GraphicThread(minX, maxX, a, b, c, table, graphic);
		gThread.start();
	}
}
