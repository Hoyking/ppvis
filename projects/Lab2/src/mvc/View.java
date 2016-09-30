package mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.filechooser.FileNameExtensionFilter;
import actions.AddAction;
import actions.ButtonAction;
import actions.DeleteAction;
import actions.ExitAction;
import actions.MenuAction;
import actions.MenuDeleteAction;
import actions.MenuSearchAction;
import actions.OpenAction;
import actions.SaveAction;
import actions.SearchAction;
import componets.InteractionComp;
import componets.PageComp;
import student.Student;

public class View
{
	private JFrame frame;
	private JToolBar toolBar;
	private JMenuBar menuBar;
	private JButton search;
	private JButton open;
	private JButton save;
	private JButton add;
	private JButton delete;
	private JFrame searchFrame;
	private List<Student> numOfSt;
	private int searchMod = 0;
	private int deleteMod = 0;
	private JFrame addFrame;
	private JFrame deleteFrame;
	private boolean ifSearch = false;
	private boolean ifAdd = false;
	private boolean ifDelete = false;
	private boolean ifSave = false;
	private boolean ifOpen = false;
	private int deleteNumStudents;
	private String file = "";
	private int saveMod = 0;
	private int page = 0;
	private int searchPage = 0;
	private PageComp comp;
	
	public View(Model model)
	{
		numOfSt = new ArrayList<Student>();
		frame = new JFrame("Students base");
		search = new JButton("Search");
		open = new JButton("Open");
		save = new JButton("Save");
		add = new JButton("Add");
		delete = new JButton("Delete");
		comp = new PageComp(model.getStudentsList(), 10);
		toolBar = new JToolBar();
		menuBar = new JMenuBar();
		frame.setLayout(new BorderLayout());
		frame.setSize(1000, 295);
		fillToolBar();
		fillMenuBar();
		frame.add(menuBar, BorderLayout.NORTH);
		frame.add(toolBar, BorderLayout.CENTER);
		frame.add(comp.getPanel(), BorderLayout.SOUTH);
		frame.setVisible(true);
		draw();
	}
	
	public void setSearchPageIndex(int value)
	{
		searchPage = value;
	}
	
	public int getSearchPageIndex()
	{
		return searchPage;
	}
	
	public void setPageIndex(int value)
	{
		page = value;
	}
	
	public int getPageIndex()
	{
		return page;
	}
	
	public void saveFrame()
	{
		if(saveMod == 0)
		{
			FileNameExtensionFilter filter = new FileNameExtensionFilter("*.XML", "*.*");
			JFileChooser fc = new JFileChooser();
			fc.setFileFilter(filter);
			if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
			{
				SaveAction saveAction = new SaveAction(this, fc.getSelectedFile().getPath() + ".XML");
				saveAction.saveFile();
			}
		}
		else
		{
			SaveAction saveAction = new SaveAction(this, file);
			saveAction.saveFile();
		}
	}
	
	public void openFrame()
	{
		JFileChooser fc = new JFileChooser();
		if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
		{
			OpenAction openAction = new OpenAction(this, fc.getSelectedFile().getPath());
			openAction.openFile();
		}
	}
	
	public void deleteFrame()
	{
		deleteFrame = new JFrame("Deleting records");
		deleteFrame.setLayout(new BoxLayout(deleteFrame.getContentPane(), BoxLayout.PAGE_AXIS));
		((JComponent) deleteFrame.getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		InteractionComp comp = new InteractionComp(deleteMod);
		comp.setModName("Deleting mod");
		comp.setButtonText("delete");
		comp.setLabelValues(1, "Phone number", "Phone number", "Part of phone number");
		comp.setLabelValues(2, "Surname", "Adress", "Surname");
		comp.setListener(new DeleteAction(this, comp));
		comp.draw();
		deleteFrame.add(comp.getPanel());
		deleteFrame.pack();
		deleteFrame.setVisible(true);
	}
	
	public JFrame getFrame()
	{
		return frame;
	}
	
	public void refresh()
	{
		draw();
	}
	
	public void refreshMainFrame()
	{
		frame.repaint();
	}
	
	public void refreshSearchFrame()
	{
		if(numOfSt.size() == 0)
			JOptionPane.showMessageDialog(new JFrame(), "The search has not given any results", "Warning", JOptionPane.WARNING_MESSAGE);
		searchFrame.repaint();
	}
	
	public void refreshDeleteFrame()
	{
		if(getDeleteNumStudents() == 0)
			JOptionPane.showMessageDialog(new JFrame(), "There are no records to be deleted", "Warning", JOptionPane.WARNING_MESSAGE);
		else if (getDeleteNumStudents() == 1)
			JOptionPane.showMessageDialog(new JFrame(), getDeleteNumStudents() + " record has deleted", "Warning", JOptionPane.WARNING_MESSAGE);
		else
			JOptionPane.showMessageDialog(new JFrame(), getDeleteNumStudents() + " records have deleted", "Warning", JOptionPane.WARNING_MESSAGE);
		frame.repaint();
	}
	
	private void draw()
	{
		if(getSearch())
		{
			numOfSt.clear();
			setSearch(false);
			searchFrame();
		}
		else if(getAdd())
		{
			setAdd(false);
			addFrame();
		}
		else if(getDelete())
		{
			setDelete(false);
			deleteFrame();
		}
		else if(getSave())
		{
			setSave(false);
			saveFrame();
		}
		else if(getOpen())
		{
			setOpen(false);
			openFrame();
		}
	}
	
	public void addFrame()
	{
		addFrame = new JFrame("Adding record");
		JPanel labelPanel = new JPanel(new GridLayout(4, 1));
		JTextField nameField = new JTextField(10);
		nameField.setToolTipText("name");
		JTextField surnameField = new JTextField(10);
		surnameField.setToolTipText("surname");
		JTextField thirdnameField = new JTextField(10);
		thirdnameField.setToolTipText("thirdname");
		JTextField cityField = new JTextField(10);
		cityField.setToolTipText("city");
		JTextField streetField = new JTextField(10);
		streetField.setToolTipText("street");
		JTextField houseField = new JTextField(3);
		houseField.setToolTipText("house");
		JTextField corpusField = new JTextField(10);
		corpusField.setToolTipText("corpus (not necessary)");
		corpusField.setText("");
		JTextField flatField = new JTextField(3);
		flatField.setToolTipText("flat");
		JTextField homePhoneField = new JTextField(30);
		homePhoneField.setToolTipText("home phone");
		JTextField mobPhoneField = new JTextField(30);
		mobPhoneField.setToolTipText("mobile phone");
		JPanel fieldPanel = new JPanel(new GridLayout(4, 1));
		JPanel adressPanel = new JPanel(new GridLayout(1, 5));
		adressPanel.add(cityField);
		adressPanel.add(streetField);
		adressPanel.add(houseField);
		adressPanel.add(corpusField);
		adressPanel.add(flatField);
		JPanel namePanel = new JPanel(new GridLayout(1, 3));
		namePanel.add(surnameField);
		namePanel.add(nameField);
		namePanel.add(thirdnameField);
		fieldPanel.add(namePanel);
		fieldPanel.add(adressPanel);
		fieldPanel.add(mobPhoneField);
		fieldPanel.add(homePhoneField);
		JButton addButton = new JButton("Add student");
		addButton.addActionListener(new AddAction(this, surnameField, nameField, thirdnameField, cityField, streetField, houseField, corpusField,
				flatField, mobPhoneField, homePhoneField));
		labelPanel.add(new JLabel("Full name of a student: "));
		labelPanel.add(new JLabel("Adress of a student: "));
		labelPanel.add(new JLabel("Mobile phone of a student: "));
		labelPanel.add(new JLabel("Home phone of a student: "));
		JPanel addPanel = new JPanel();
		addPanel.setLayout(new BoxLayout(addPanel, BoxLayout.LINE_AXIS));
		addPanel.add(labelPanel);
		addPanel.add(fieldPanel);
		JPanel LabelPanel = new JPanel();
		LabelPanel.setLayout(new BoxLayout(LabelPanel, BoxLayout.LINE_AXIS));
		JPanel addButtonPanel = new JPanel();
		addButtonPanel.setLayout(new BoxLayout(addButtonPanel, BoxLayout.LINE_AXIS));
		LabelPanel.add(new JLabel("Print informotion about student"), BorderLayout.CENTER);
		addButtonPanel.add(addButton, BorderLayout.CENTER);
		JPanel addFramePanel = new JPanel();
		addFramePanel.setLayout(new BoxLayout(addFramePanel, BoxLayout.PAGE_AXIS));
		addFramePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		addFrame.setContentPane(addFramePanel);
		addFramePanel.add(LabelPanel);
		addFramePanel.add(Box.createRigidArea(new Dimension(0,20)));
		addFramePanel.add(addPanel);
		addFramePanel.add(Box.createRigidArea(new Dimension(0,20)));
		addFramePanel.add(addButtonPanel);
		addFrame.pack();
		addFrame.setResizable(false);
		addFrame.setVisible(true);
	}
	
	private void searchFrame()
	{
		searchFrame = new JFrame("Searching");
		searchFrame.setLayout(new BoxLayout(searchFrame.getContentPane(), BoxLayout.PAGE_AXIS));
		((JComponent) searchFrame.getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		InteractionComp intComp = new InteractionComp(searchMod);
		intComp.setModName("Searching mod");
		intComp.setButtonText("search");
		intComp.setLabelValues(1, "Phone number", "Phone number", "Part of phone number");
		intComp.setLabelValues(2, "Surname", "Adress", "Surname");
		intComp.setListener(new SearchAction(this, intComp));
		intComp.draw();
		PageComp pageComp = new PageComp(numOfSt, 5);
		searchFrame.add(intComp.getPanel());
		searchFrame.add(Box.createRigidArea(new Dimension(0, 10)));
		searchFrame.add(pageComp.getPanel());
		searchFrame.pack();
		searchFrame.setVisible(true);
	}
	
	private void fillToolBar()
	{
		toolBar.add(save);
		save.addActionListener(new ButtonAction(this));
		toolBar.add(open);
		open.addActionListener(new ButtonAction(this));
		toolBar.add(search);
		search.addActionListener(new ButtonAction(this));
		toolBar.add(add);
		add.addActionListener(new ButtonAction(this));
		toolBar.add(delete);
		delete.addActionListener(new ButtonAction(this));
	}
	
	private void fillMenuBar()
	{
		JMenu fileMenu = new JMenu("File");
		JMenuItem save = new JMenuItem("Save");
		save.addActionListener(new MenuAction(this));
		JMenuItem saveAs = new JMenuItem("Save as");
		saveAs.addActionListener(new MenuAction(this));
		JMenuItem openFile = new JMenuItem("Open file");
		openFile.addActionListener(new MenuAction(this));
		JMenu searchMenu = new JMenu("Search");
		JMenuItem phoneSName = new JMenuItem("Searching by phone number and surname");
		phoneSName.addActionListener(new MenuSearchAction(this));
		JMenuItem phoneAdress = new JMenuItem("Searching by phone number and adress");
		phoneAdress.addActionListener(new MenuSearchAction(this));
		JMenuItem numSName = new JMenuItem("Searching by part of phone number and surname");
		numSName.addActionListener(new MenuSearchAction(this));
		searchMenu.add(phoneSName);
		searchMenu.add(phoneAdress);
		searchMenu.add(numSName);
		JMenu deleteMenu = new JMenu("Delete");
		JMenuItem delPhoneSName = new JMenuItem("Deleting by phone number and surname");
		delPhoneSName.addActionListener(new MenuDeleteAction(this));
		JMenuItem delPhoneAdress = new JMenuItem("Deleting by phone number and adress");
		delPhoneAdress.addActionListener(new MenuDeleteAction(this));
		JMenuItem delNumSName = new JMenuItem("Deleting by part of phone number and surname");
		delNumSName.addActionListener(new MenuDeleteAction(this));
		deleteMenu.add(delPhoneSName);
		deleteMenu.add(delPhoneAdress);
		deleteMenu.add(delNumSName);
		JMenuItem add = new JMenuItem("Add record");
		add.addActionListener(new MenuAction(this));
		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(new ExitAction(this));
		fileMenu.add(save);
		fileMenu.add(saveAs);
		fileMenu.add(openFile);
		fileMenu.add(searchMenu);
		fileMenu.add(deleteMenu);
		fileMenu.add(add);
		fileMenu.addSeparator();
		fileMenu.add(exit);
		menuBar.add(fileMenu);
	}
	
	public void setNumStList(List<Student> list)
	{
		numOfSt.clear();
		for(Student student: list)
		{
			numOfSt.add(student);
		}
	}
	
	public List<Student> getNumStList()
	{
		return numOfSt;
	}
	
	public void setSearch(boolean search)
	{
		this.ifSearch = search;
	}
	
	public boolean getSearch()
	{
		return ifSearch;
	}
	
	public void setAdd(boolean add)
	{
		this.ifAdd = add;
	}
	
	public boolean getAdd()
	{
		return ifAdd;
	}
	
	public int getSearchMod()
	{
		return searchMod;
	}
	
	public void setSearchMod(int mod)
	{
		searchMod = mod;
	}
	
	public int getDeleteMod()
	{
		return deleteMod;
	}
	
	public void setDeleteMod(int mod)
	{
		deleteMod = mod;
	}
	
	public void setDelete(boolean delete)
	{
		this.ifDelete = delete;
	}
	
	public boolean getDelete()
	{
		return ifDelete;
	}
	
	public void setDeleteNumStudents(int value)
	{
		deleteNumStudents = value;
	}
	
	public int getDeleteNumStudents()
	{
		return deleteNumStudents;
	}
	
	public void setSave(boolean save)
	{
		this.ifSave = save;
	}
	
	public boolean getSave()
	{
		return ifSave;
	}
	
	public void setOpen(boolean open)
	{
		this.ifOpen = open;
	}
	
	public boolean getOpen()
	{
		return ifOpen;
	}
	
	public void setSaveMod(int value)
	{
		saveMod = value;
	}
	
	public int getSaveMod()
	{
		return saveMod;
	}
	
	public void setFile(String file)
	{
		this.file = file;
	}
}
