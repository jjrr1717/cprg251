
public abstract class Shape {
	
	private String color;
	
	public Shape(String color) 
	{
		this.color = color;
	}
	
	
	public String toString() 
	{
		return ("Color: " + color);
	}
	
	public abstract double getArea();
	
	

}
