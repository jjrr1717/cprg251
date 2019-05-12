
public class Triangle extends Shape {
	
	private int base;
	private int height;
	
	public Triangle (String color, int base, int height) 
	{
		super(color);
		this.base = base;
		this.height = height;
	}
	
	public String toString() 
	{
		return (super.toString() + "\nBase: " + base + "\nHeight: " + height);
	}
	@Override
	public double getArea() 
	{
		// TODO Auto-generated method stub
		return base * height;
	}
	
	

}
