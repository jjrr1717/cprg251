package employeeDomain;

public abstract class Employee {
	
	private int id;
	private String name;
	private String address;
	private String phone;
	private long sin;
	private String dob;
	private String dept;
	
	public Employee() 
	{
		
	}
	
	public Employee(int id, String name, String address, String phone, long sin, String dob, String dept) 
	{
		this.id = id;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.sin = sin;
		this.dob = dob;
		this.dept = dept;
	}
	
	public void setId(int id) 
	{
		this.id = id;
	}

	public int getId() 
	{
		return id;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}

	public String getName() 
	{
		return name;
	}
	
	public void setAddress (String addres) 
	{
		this.address = address;
	}

	public String getAddress() 
	{
		return address;
	}
	
	public void setPhone(String phone) 
	{
		this.phone= phone;
	}

	public String getPhone() 
	{
		return phone;
	}
	
	public void setSin(long sin) 
	{
		this.sin = sin;
	}

	public long getSin() 
	{
		return sin;
	}
	
	public void setDob(String dob) 
	{
		this.dob = dob;
	}

	public String getDob() 
	{
		return dob;
	}
	
	public void setDept(String dept) 
	{
		this.dept = dept;
	}

	public String getDept() 
	{
		return dept;
	}
	
	public String toString() 
	{
		return ("Id: " + getId() + "\nName: " + getName() + 
				"\nAddress: " + getAddress() + "\nPhone: " +
				getPhone() + "\nSIN: " + getSin() + "\nDOB: " +
				getDob() + "\nDept: " + getDept());
	}
	
	public abstract double calPay();
}
