package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import adress.Adress;
import mvc.View;
import startPoint.StartPoint;

public class AddAction implements ActionListener
{
	private JTextField name;
	private JTextField surname;
	private JTextField thirdname;
	private JTextField city;
	private JTextField street;
	private JTextField house;
	private JTextField corpus;
	private JTextField flat;
	private JTextField homePhone;
	private JTextField mobPhone;
	private View view;
	
	public AddAction(View view, JTextField surname, JTextField name, JTextField thirdname, JTextField city, JTextField street, 
			JTextField house, JTextField corpus, JTextField flat, JTextField mobPhone, JTextField homePhone)
	{
		this.name = name;
		this.surname = surname;
		this.thirdname = thirdname;
		this.city = city;
		this.street = street;
		this.house = house;
		this.corpus = corpus;
		this.flat = flat;
		this.homePhone = homePhone;
		this.mobPhone = mobPhone;
		this.view = view;
	}
	
	public void actionPerformed(ActionEvent event)
	{
		if(name.getText().length() == 0 || surname.getText().length() == 0 || thirdname.getText().length() == 0 ||
				city.getText().length() == 0 || street.getText().length() == 0 || house.getText().length() == 0 ||
				flat.getText().length() == 0 || mobPhone.getText().length() == 0 || homePhone.getText().length() == 0)
		    JOptionPane.showMessageDialog(new JFrame(), "Please fill all fields (corpus is not nessasary)", "Error", JOptionPane.ERROR_MESSAGE);
		else
		{
			if(corpus.getText().equals(""))
				StartPoint.getMap().get(view.getFrame()).addStudent(name.getText(), surname.getText(), thirdname.getText(), new Adress(city.getText(),
						street.getText(), Integer.parseInt(house.getText()), Integer.parseInt(flat.getText())), mobPhone.getText(), homePhone.getText());
			else
				StartPoint.getMap().get(view.getFrame()).addStudent(name.getText(), surname.getText(), thirdname.getText(), new Adress(city.getText(),
						street.getText(), Integer.parseInt(house.getText()), Integer.parseInt(corpus.getText()), Integer.parseInt(flat.getText())),
						mobPhone.getText(), homePhone.getText());
			view.refreshMainFrame();
		}
	}
}
