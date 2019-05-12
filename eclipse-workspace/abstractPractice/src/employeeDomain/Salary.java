package employeeDomain;

public class Salary extends Employee {
	
	private double salary;
	
	public Salary() 
	{
		
	}
	
	public Salary(int id, String name, String address, String phone, long sin, String dob, String dept, double salary) 
	{
		super(id, name, address, phone, sin, dob, dept);
		this.salary = salary;
	}
	
	public void setSalary(double salary) 
	{
		this.salary = salary;
	}
	
	
	public double getSalary() 
	{
		return salary;
		
	}
	
	@Override
	public double calPay() 
	{
		return salary;
		
	}
	
	public String toString() 
	{
		return super.toString() + "\nSalary: " + getSalary() + "\n";
	}

}
