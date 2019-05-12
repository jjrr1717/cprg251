package myPack;


//notice instead of class it says interface
public interface Shape {

	//implicitly public, static and final (because they are final make it capital and could remove the public)
	//usually use when the attribute will be the same, such as GST rates
	public String NAME = "Shape";
	
	//Cannot have a constructor
	
	//because this is an interface there cannot be a body.  It is already implicitly abstract and public - they can't be private or final
	public void draw();
	public double getArea();
}
