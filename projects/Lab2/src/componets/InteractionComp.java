package componets;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import actions.SwitchAction;

public class InteractionComp 
{
	private JButton modButton1;
	private JButton modButton2;
	private JButton modButton3;
	private JButton button;
	private Map <JButton, Integer> map;
	private JLabel label1;
	private JLabel label2;
	private JTextField field1;
	private JTextField field2;
	private List <String> list;
	private JPanel panel;
	private int mod = 0;
	
	public InteractionComp(int mod)
	{
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		modButton1 = new JButton();
		modButton2 = new JButton();
		modButton3 = new JButton();
		modButton1.addActionListener(new SwitchAction(this));
		modButton2.addActionListener(new SwitchAction(this));
		modButton3.addActionListener(new SwitchAction(this));
		button = new JButton();
		label1 = new JLabel();
		label2 = new JLabel();
		field1 = new JTextField();
		field2 = new JTextField();
		list = new ArrayList <String> (6);
		list.add("");
		list.add("");
		list.add("");
		list.add("");
		list.add("");
		list.add("");
		map = new HashMap <JButton, Integer> ();
		map.put(modButton1, 0);
		map.put(modButton2, 1);
		map.put(modButton3, 2);
		this.mod = mod;
	}
	
	public void draw()
	{
		//panel with modButtons
		JPanel modButtonPanel = new JPanel();
		modButtonPanel.setLayout(new BoxLayout(modButtonPanel, BoxLayout.LINE_AXIS));
		modButtonPanel.add(modButton1);
		modButtonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		modButtonPanel.add(modButton2);
		modButtonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		modButtonPanel.add(modButton3);
		//panel with fields and labels
		JPanel fieldPanel = new JPanel();
		fieldPanel.setLayout(new GridLayout(2, 1));
		label1.setText(list.get(mod));
		label2.setText(list.get(mod + 3));
		fieldPanel.add(label1);
		fieldPanel.add(label2);
		fieldPanel.add(field1);
		fieldPanel.add(field2);
		//panel with button
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
		buttonPanel.add(button, BorderLayout.CENTER);
		//adding components to the main panel
		panel.add(modButtonPanel);
		panel.add(Box.createRigidArea(new Dimension(0, 20)));
		panel.add(fieldPanel);
		panel.add(Box.createRigidArea(new Dimension(0, 10)));
		panel.add(buttonPanel);
	}
	
	public void refresh()
	{
		label1.setText(list.get(mod));
		label2.setText(list.get(mod + 3));
	}
	
	public JTextField getField(int numField)
	{
		if(numField == 1)
			return field1;
		else return field2;
	}
	
	public void setModName(String name)
	{
		modButton1.setText(name + " 1");
		modButton2.setText(name + " 2");
		modButton3.setText(name + " 3");
	}
	
	public void setButtonText(String name)
	{
		button.setText(name);
	}
	
	public void setLabelValues(int numLabel, String value1, String value2, String value3)
	{
		list.set((numLabel - 1) * 3, value1);
		list.set((numLabel - 1) * 3 + 1, value2);
		list.set((numLabel - 1) * 3 + 2, value3);
	}
	
	public void setMod(int mod)
	{
		this.mod = mod;
	}
	
	public int getMod()
	{
		return mod;
	}
	
	public int getMod(JButton button)
	{
		try
		{
			return map.get(button);
		}
		catch (Exception e)
		{
			System.out.println("Wrong button");
			return 0;
		}
	}
	
	public void setListener(ActionListener listener)
	{
		button.addActionListener(listener);
	}
	
	public JPanel getPanel()
	{
		return panel;
	}
}
