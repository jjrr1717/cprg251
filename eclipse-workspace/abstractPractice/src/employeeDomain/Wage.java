package employeeDomain;

public class Wage extends Employee {
	
	private double hours;
	private double rate;
	private final double OVER_TIME = 40;
	
	public Wage() 
	{
		
	}
	
	public Wage (int id, String name, String address, String phone, long sin, String dob, String dept, double rate, double hours) 
	{
		super(id, name, address, phone, sin, dob, dept);
		this.rate = rate;
		this.hours = hours;
	}
	
	public double calPay() 
	{
		double regularHours = getHours() * getRate();
		double overTimeHours = ((getHours() - OVER_TIME) * 0.50) * getRate(); 
		double pay;
		
		if(overTimeHours > 0) 
		{
			pay = regularHours + overTimeHours;
		}
		else 
		{
			pay = regularHours;
		}
		
		return pay;
	}
	
	public void setRate(double rate) 
	{
		this.rate = rate;
	}
	
	public double getRate() 
	{
		return rate;
	}
	
	public void setHours(double hours) 
	{
		this.hours = hours;
	}
	
	public double getHours() 
	{
		return hours;
	}
	
	public String toString() 
	{
		return super.toString() + "\nRate: " + getRate() + "\nHours: " + getHours() + "\n";
	}

}
