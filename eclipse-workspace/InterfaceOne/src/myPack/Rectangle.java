package myPack;

public class Rectangle implements Shape {

	private double height;
	private double width;
	
	public Rectangle(double w, double h) 
	{
		height = h;
		width = w;
	}
	
	@Override
	public void draw() {
		System.out.println("Drawing rectangle!");
		
	}

	@Override
	public double getArea() {
		// TODO Auto-generated method stub
		return height * width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

}
