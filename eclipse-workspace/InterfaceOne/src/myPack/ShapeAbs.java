package myPack;

//notice we can implement interface in abstract class.  
//Also notice we don't need to implement the methods from Shape, but we can if want
public abstract class ShapeAbs implements Shape {
	
	public void draw() 
	{
		System.out.println("Drawing ShapeAbs!");
	}
	
	

}
