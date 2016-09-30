package student;

import adress.Adress;

public class Student 
{
	private String name;
	private String surname;
	private String thirdname;
	private Adress adress;
	private String mobPhone;
	private String homePhone;
	
	public Student(String name, String surname, String thirdname, Adress adress, String mobPhone, String homePhone)
	{
		this.name = name;
		this.surname = surname;
		this.thirdname = thirdname;
		this.adress = adress;
		this.mobPhone = mobPhone;
		this.homePhone = homePhone;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public String getSurname()
	{
		return this.surname;
	}
	
	public String getThirdname()
	{
		return this.thirdname;
	}
	
	public Adress getAdress()
	{
		return this.adress;
	}
	
	public String getMobPhone()
	{
		return this.mobPhone;
	}
	
	public String getHomePhone()
	{
		return this.homePhone;
	}
	
	public String toString()
	{
		return (surname + " " + name + " " + thirdname);
	}
}
