
public class Rectangle extends Shape {
	
	private int length;
	private int width;
	
	public Rectangle (String color, int length, int width) 
	{
		super(color);
		this.length = length;
		this.width = width;
	}
	
	
	public String toString() 
	{
		return (super.toString() + "\nLength: " + length + "\nWidth: " + width);
	}

	@Override
	public double getArea() 
	{
		// TODO Auto-generated method stub
		return length * width;
	}

}
