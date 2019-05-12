package excercise2;

public class Customer {

	private int customerId;
	private String address;
	private String customerName;
	private long phone;
	
	public Customer(int id, String name, String address, long phone) 
	{
		customerId = id;
		customerName = name;
		this.address = address;
		this.phone =  phone;
	}
	
	public void setCustomerId (int id) 
	{
		customerId = id;
	}
	
	public int getCustomerId() 
	{
		return customerId;
	}
	
	public void setAddress(String address) 
	{
		this.address = address;
	}
	
	public String getAddress() 
	{
		return address;
	}
	
	public void setCustomerName (String name) 
	{
		customerName = name;
	}
	
	public String getCustomerName() 
	{
		return customerName;
	}
	
	public void setPhone(long phone) 
	{
		this.phone = phone;
	}
	
	public long getPhone() 
	{
		return phone;
	}
	
	public String toString()
	{

		return ("Customer Id: " +  getCustomerId() + 
				"\nAddress: " + getAddress() +
				"\nCustomer Name: " + getCustomerName() + 
				"\nPhone: " + getPhone());
	}
}
