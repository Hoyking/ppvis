import java.awt.Dimension;
import javax.swing.*;

public class MainFrame 
{
	public static void main(String [] args)
	{
		//creating frame
		JFrame frame = new JFrame ("Editor");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setMinimumSize(new Dimension(590, 600));
		//creating panel
		JPanel mainPanel = new JPanel();
		frame.setContentPane(mainPanel);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		//crating groups
		Group1 group1 = new Group1();
		Group2 group2 = new Group2();
		Group3 group3 = new Group3();
		Group4 group4 = new Group4();
		Group5 group5 = new Group5();
		//adding groups
		group1.add(mainPanel);
		mainPanel.add(Box.createVerticalGlue());
		group2.add(mainPanel);
		mainPanel.add(Box.createVerticalGlue());
		group3.add(mainPanel);
		mainPanel.add(Box.createVerticalGlue());
		group4.add(mainPanel);
		mainPanel.add(Box.createVerticalGlue());
		group5.add(mainPanel);
		//setting visibility of the panel
		mainPanel.setVisible(true);
		//packing and setting visibility of the frame
		frame.pack();
		frame.setVisible(true);
	}	
}