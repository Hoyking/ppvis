package mvc;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import adress.Adress;
import student.Student;

public class Controller 
{
	private Model model;
	private View view;
	
	public Controller(Model model, View view)
	{
		this.model = model;
		this.view = view;
	}
	
	public void searchAction()
	{
		view.setSearch(true);
	}
	
	public void addAction()
	{
		view.setAdd(true);
	}
	
	public void deleteAction()
	{
		view.setDelete(true);
	}
	
	public void saveAction()
	{
		view.setSave(true);
	}
	
	public void saveAsAction()
	{
		view.setSave(true);
		view.setSaveMod(0);
	}
	
	public void openAction()
	{
		view.setOpen(true);
	}
	
	public void Search(int mod, String line1, String line2)
	{
		switch(mod)
		{
		case 0:
		{
			List<Student> numOfSt = new ArrayList<Student>();
			for(int stIndex = 0; stIndex < model.getStudentCount(); stIndex++)
			{
				Student student = model.getStudent(stIndex);
				if(student.getHomePhone().equals(line1) && student.getSurname().equals(line2))
					numOfSt.add(student);
				else if(student.getMobPhone().equals(line1) && student.getSurname().equals(line2))
					numOfSt.add(student);
			}
			view.setNumStList(numOfSt);
		} break;
		case 1:
		{
			List<Student> numOfSt = new ArrayList<Student>();
			for(int stIndex = 0; stIndex < model.getStudentCount(); stIndex++)
			{
				Student student = model.getStudent(stIndex);
				if(student.getHomePhone().equals(line1) && student.getAdress().toString().contains(line2))
					numOfSt.add(student);
				else if(student.getMobPhone().equals(line1) && student.getAdress().toString().contains(line2))
					numOfSt.add(student);
			}
			view.setNumStList(numOfSt);
		} break;
		case 2:
		{
			List<Student> numOfSt = new ArrayList<Student>();
			for(int stIndex = 0; stIndex < model.getStudentCount(); stIndex++)
			{
				Student student = model.getStudent(stIndex);
				if(student.getHomePhone().contains(line1) && student.getSurname().equals(line2))
					numOfSt.add(student);
				else if(student.getMobPhone().contains(line1) && student.getSurname().equals(line2))
					numOfSt.add(student);
			}
			view.setNumStList(numOfSt);
		}
		}
	}
	
	public void changeSearchMod(int mod)
	{
		view.setSearchMod(mod);
		view.setNumStList(new ArrayList<Student>());
	}
	
	public void Delete(int mod, String line1, String line2)
	{	
		switch(mod)
		{
		case 0:
		{
			int deleteNumStudents = 0;
			for(int stIndex = 0; stIndex < model.getStudentCount(); stIndex++)
			{
				Student student = model.getStudent(stIndex);
				if(student.getHomePhone().equals(line1) && student.getSurname().equals(line2))
				{
					model.deleteStudent(student);
					deleteNumStudents++;
					stIndex--;
				}
				else if(student.getMobPhone().equals(line1) && student.getSurname().equals(line2))
				{
					model.deleteStudent(student);
					deleteNumStudents++;
					stIndex--;
				}
			}
			view.setDeleteNumStudents(deleteNumStudents);
		} break;
		case 1:
		{
			int deleteNumStudents = 0;
			for(int stIndex = 0; stIndex < model.getStudentCount(); stIndex++)
			{
				Student student = model.getStudent(stIndex);
				if(student.getHomePhone().equals(line1) && student.getAdress().toString().contains(line2))
				{
					model.deleteStudent(student);
					deleteNumStudents++;
					stIndex--;
				}
				else if(student.getMobPhone().equals(line1) && student.getAdress().toString().contains(line2))
				{
					model.deleteStudent(student);
					deleteNumStudents++;
					stIndex--;
				}
			}
			view.setDeleteNumStudents(deleteNumStudents);
		} break;
		case 2:
		{
			int deleteNumStudents = 0;
			for(int stIndex = 0; stIndex < model.getStudentCount(); stIndex++)
			{
				Student student = model.getStudent(stIndex);
				if(student.getHomePhone().contains(line1) && student.getSurname().equals(line2))
				{
					deleteNumStudents++;
					model.deleteStudent(student);
					stIndex--;
				}
				else if(student.getMobPhone().contains(line1) && student.getSurname().equals(line2))
				{
					deleteNumStudents++;
					model.deleteStudent(student);
					stIndex--;
				}
			}
			view.setDeleteNumStudents(deleteNumStudents);
		}
		}
	}
	
	public void changeDeleteMod(int mod)
	{
		view.setDeleteMod(mod);
	}
	
	public void addStudent(String name, String surname, String thirdname, Adress adress, String mobPhone, String homePhone)
	{
		model.addStudent(name, surname, thirdname, adress, mobPhone, homePhone);
	}
	
	public void saveFile(String filePath)
	{
		try 
		{
            DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = f.newDocumentBuilder();
            File file = new File(filePath);
            Document doc = builder.newDocument();
            Element studentsEl = doc.createElement("students");
            doc.appendChild(studentsEl);
            for (int stIndex = 0; stIndex < model.getStudentCount(); stIndex++) 
            {
            	Student student = model.getStudent(stIndex);
            	Adress adress = student.getAdress();
            	//создание эл-та studentEl
                Element studentEl = doc.createElement("student");
                studentsEl.appendChild(studentEl);
                String name = student.getName();
                String surname = student.getSurname();
                String thirdname = student.getThirdname();
                String homePhone = student.getHomePhone();
                String mobPhone = student.getMobPhone();
                //создание эл-та adressEl
                Element adressEl = doc.createElement("adress");
                studentEl.appendChild(adressEl);
                String city = adress.getCity();
                String street = adress.getStreet();
                int house = adress.getHouse();
                int corpus = adress.getCorpus();
                int flat = adress.getFlat();
                //заполнение элемента adressEl
                Element cityEl = doc.createElement("city");
                cityEl.appendChild(doc.createTextNode(city));
                adressEl.appendChild(cityEl);
                Element streetEl = doc.createElement("street");
                streetEl.appendChild(doc.createTextNode(street));
                adressEl.appendChild(streetEl);
                Element houseEl = doc.createElement("house");
                houseEl.appendChild(doc.createTextNode(house + ""));
                adressEl.appendChild(houseEl);
                Element corpusEl = doc.createElement("corpus");
                corpusEl.appendChild(doc.createTextNode(corpus + ""));
                adressEl.appendChild(corpusEl);
                Element flatEl = doc.createElement("flat");
                flatEl.appendChild(doc.createTextNode(flat + ""));
                adressEl.appendChild(flatEl);
                //заполнение элемента studentEl
                Element nameEl = doc.createElement("name");
                nameEl.appendChild(doc.createTextNode(name));
                studentEl.appendChild(nameEl);
                Element surnameEl = doc.createElement("surname");
                surnameEl.appendChild(doc.createTextNode(surname));
                studentEl.appendChild(surnameEl);
                Element thirdnameEl = doc.createElement("thirdname");
                thirdnameEl.appendChild(doc.createTextNode(thirdname));
                studentEl.appendChild(thirdnameEl);
                Element homephoneEl = doc.createElement("homePhone");
                homephoneEl.appendChild(doc.createTextNode(homePhone));
                studentEl.appendChild(homephoneEl);
                Element mobphoneEl = doc.createElement("mobilePhone");
                mobphoneEl.appendChild(doc.createTextNode(mobPhone));
                studentEl.appendChild(mobphoneEl);
            }
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(file);
            transformer.transform(source, result);
            view.setSaveMod(1);
            view.setFile(filePath);
        } 
		catch (Exception exception) 
		{
            exception.printStackTrace();
        }		
	}
	
	public void openFile(String filePath)
	{
		try
		{
			model.clear();
			File file = new File(filePath);
			DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
			f.setValidating(false);
			DocumentBuilder builder = f.newDocumentBuilder();
			Document doc = builder.parse(file);
	        NodeList nodes = doc.getChildNodes();
	        for(int nodeIndex = 0; nodeIndex < nodes.getLength(); nodeIndex++)
	        {
	        	Node node = nodes.item(nodeIndex);
	        	NodeList stNodes = node.getChildNodes();
	        	if(stNodes.getLength() != 0)
	        	{
	        		for(int stNodeIndex = 0; stNodeIndex < stNodes.getLength(); stNodeIndex++)
	        		{
	        			Node stNode = stNodes.item(stNodeIndex);
	        			NodeList stFields = stNode.getChildNodes();
	        			Node adrNode = stFields.item(0);
	        			NodeList adrFields = adrNode.getChildNodes();
	        			String city = adrFields.item(0).getTextContent();
	        			String street = adrFields.item(1).getTextContent();
	        			int house = Integer.parseInt(adrFields.item(2).getTextContent());
	        			int corpus = Integer.parseInt(adrFields.item(3).getTextContent());
	        			int flat = Integer.parseInt(adrFields.item(4).getTextContent());
	        			String name = stFields.item(1).getTextContent();
	        			String surname = stFields.item(2).getTextContent();
	        			String thirdname = stFields.item(3).getTextContent();
	        			String homePhone = stFields.item(4).getTextContent();
	        			String mobPhone = stFields.item(5).getTextContent();
	        			model.addStudent(name, surname, thirdname, new Adress(city, street, house, corpus, flat), mobPhone, homePhone);
	        		}
	        	}
	        }
	        view.setSaveMod(1);
	        view.setFile(file.getPath());
	        view.refreshMainFrame();
		}
		catch (Exception exception) 
		{
            exception.printStackTrace();
        }		
	}
}
