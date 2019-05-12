package employees;

public class Employee {
	
	//Attributes
	private String name;
	private int id;
	private double hours;
	private double hourlyWage;
	
	//Constructors
	public Employee() {
		name = "";
		id = -1;
		hours = -1;
		hourlyWage = -1;
	}
	public Employee (String name, int id, double hours, double wage) {
		this.name = name;
		this.id = id;
		this.hours = hours;
		hourlyWage = wage;
		
	}
	
	//Getters and Setters
	public void setName (String name) {
		this.name = name;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setHours (double hours) {
		this.hours = hours;
	}
	
	public void setHourlyWage(double wage) {
		hourlyWage = wage;
	}
	
	public String getName() {
		return name;
	}
	
	public int getId() {
		return id;
	}
	
	public double getHours() {
		return hours;
	}
	
	public double getHourlyWage() {
		return hourlyWage;
	}
	
	//Operational methods
	
	public double getPay() {
		return getHours() * getHourlyWage();
	}
	
	public String toString() {
		String theString = String.format("Employee Name: %s\nEmployee ID: %d\nEmployee Hours: %.2f\nEmployee Hourly Wage: %.2f\nEmployee Pay: %.2f\n", 
				getName(), getId(), getHours(), getHourlyWage(), getPay());
		
		return theString;
	}
}
