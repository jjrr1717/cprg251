package randomAccessFileExcercise6;

public class Customer {
	
	private boolean active; //used for an active record
	private int id;
	private String name;
	private String city;
	private long phone;
	
	public Customer(boolean a, int i, String n, String c, long p) 
	{
		active = a;
		id = i;
		name = n;
		city = c;
		phone = p;
	}
	
	public String toString() 
	{
		return "Id: " + getId() +
				" Name: " + getName() + 
				" City: " + getCity() + 
				" Phone: " + getPhone();
	}
	
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	
	
	

}
