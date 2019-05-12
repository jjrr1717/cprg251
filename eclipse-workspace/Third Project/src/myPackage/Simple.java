package myPackage;

public class Simple {
	private int number;
	private double dNum;
	private String line;
	
	//constructors (to initialize the attributes)
	public Simple () {
		
	}
	
	public Simple (int number, double num, String line) {
		this.number = number;
		dNum = num;
		this.line = line;
	}
	
	//Getters and setters
	public void setNumber(int number) {
		this.number = number;
	}
	
	public int getNumber() {
		return number;
	}
	
	public void setDNum(double num) {
		dNum = num;
	}
	
	public double getDNum() {
		return dNum;
	}
	
	public void setLine(String line) {
		this.line = line;
	}
	
	public String getLine() {
		return line;
	}
	
	//Methods
	
}
