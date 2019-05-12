package myPack;


//note keyword implements
public class Circle implements Shape {

	
	private double radius;
	
	//can have a constructor because this is a regular class
	public Circle(double r) 
	{
		radius = r;
	}
	
	
	//MUST implement the methods in the interface Shape class
	@Override
	public void draw() {
		// TODO Auto-generated method stub
		System.out.println("Drawing the circle");
	}

	@Override
	public double getArea() {
		// TODO Auto-generated method stub
		//return Math.PI * radius*radius; or
		return Math.PI*(Math.pow(radius, 2));
	}
	
	
	
	

}
