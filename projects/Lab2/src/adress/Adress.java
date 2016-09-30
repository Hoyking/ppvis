package adress;

public class Adress 
{
	private String city;
	private String street;
	private int house;
	private int corpus;
	private int flat;
	
	public Adress(String city, String street, int house, int corpus, int flat)
	{
		this.city = city;
		this.street = street;
		this.house = house;
		this.corpus = corpus;
		this.flat = flat;
	}
	
	public Adress(String city, String street, int house, int flat)
	{
		this.city = city;
		this.street = street;
		this.house = house;
		this.corpus = -1;
		this.flat = flat;
	}
	
	public String toString()
	{
		if(corpus != -1)
			return ("г. " + city + ", ул. " + street + ", д. " + house + "-" + corpus + ", кв. " + flat);
		return ("г. " + city + ", ул. " + street + ", д. " + house + ", кв. " + flat);
	}
	
	public String getCity()
	{
		return city;
	}
	
	public String getStreet()
	{
		return street;
	}
	
	public int getHouse()
	{
		return house;
	}
	
	public int getCorpus()
	{
		return corpus;
	}
	
	public int getFlat()
	{
		return flat;
	}
}
