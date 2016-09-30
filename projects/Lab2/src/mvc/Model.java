package mvc;

import java.util.ArrayList;
import java.util.List;

import adress.Adress;
import student.Student;

public class Model 
{
	private List<Student> students;
	
	public Model()
	{
		students = new ArrayList<Student>();
	}
	
	public void addStudent(String name, String surname, String thirdname, Adress adress, String mobPhone, String homePhone)
	{
		students.add(new Student(name, surname, thirdname, adress, mobPhone, homePhone));
	}
	
	public void addStudent(Student student)
	{
		students.add(student);
	}
	
	public Student getStudent(int index)
	{
		return students.get(index);
	}
	
	public void deleteStudent(int index)
	{
		students.remove(index);
	}
	
	public void deleteStudent(Student student)
	{
		students.remove(student);
	}
	
	public int getStudentCount()
	{
		return students.size();
	}
	
	public List <Student> getStudentsList()
	{
		return students;
	}
	
	public void clear()
	{
		students.clear();
	}
}
