import java.awt.Dimension;
import javax.swing.*;

public class Group5
{
	private JTextField field;
	private JButton load;
	private JButton switch1;
	private JButton switch2;
	private JTable tab;
	private JLabel label;
	private JPanel boxPanel1;
	private JPanel boxPanel2;
	private JPanel BoxPanel;
	private JPanel panel;
	
	public Group5()
	{
		panel = new JPanel();
		boxPanel1 = new JPanel();
		boxPanel1.setLayout(new BoxLayout(boxPanel1, BoxLayout.LINE_AXIS));
		boxPanel2 = new JPanel();
		boxPanel2.setLayout(new BoxLayout(boxPanel2, BoxLayout.PAGE_AXIS));
		BoxPanel = new JPanel();
		BoxPanel.setLayout(new BoxLayout(BoxPanel, BoxLayout.LINE_AXIS));
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		label = new JLabel("Group 5:");
		field = new JTextField(15);
		field.setMaximumSize(field.getPreferredSize());
        field.setMinimumSize(field.getPreferredSize());
		load = new JButton("Load");
		switch1 = new JButton("Select");
		switch1.setMaximumSize(new Dimension(150, 20));
        switch1.setMinimumSize(new Dimension(150, 20));
		switch2 = new JButton("Select");
		switch2.setMaximumSize(new Dimension(150, 20));
        switch2.setMinimumSize(new Dimension(150, 20));
		tab = new JTable(5, 2);
		tab.setMaximumSize(new Dimension(300, 100));
        tab.setMinimumSize(new Dimension(300, 100));
		display();
	}
	
	private void display()
	{
		//adding components to the boxPanel1
		boxPanel1.add(switch1);
		ListenerTab listener1 = new ListenerTab(tab, field, 1);
		switch1.addActionListener(listener1);
		boxPanel1.add(switch2);
		ListenerTab listener2 = new ListenerTab(tab, field, 2);
		switch2.addActionListener(listener2);
		//adding components to the boxPanel2
		boxPanel2.add(boxPanel1);
		tab.setRowHeight(20);
		tab.setBounds(200, 440, 200, 100);
		for(int i = 0; i < 5; i++)
			for(int j = 0; j < 2; j++)
				tab.getModel().setValueAt("", i, j);
		boxPanel2.add(tab);
		//adding components to the BoxPanel
		ListenerTab listener = new ListenerTab(tab, field, 0);
		load.addActionListener(listener);
		BoxPanel.add(field);
		BoxPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		BoxPanel.add(load);
		BoxPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		BoxPanel.add(boxPanel2);
		//adding components to the Group5 panel
		panel.add(label);
		panel.add(BoxPanel);
		//setting visibility
		panel.setVisible(true);
	}
	
	public void add(JPanel panel)
	{
		panel.add(this.panel);
	}
}
