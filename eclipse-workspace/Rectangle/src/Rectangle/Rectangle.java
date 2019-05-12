/**
 * @author Jocelyn
 * @version January something,2019
 * Class for creating rectangles 
 * Contains width and length
 * will calculate area of rectangle 
 */
package Rectangle;

public class Rectangle {

	//attributes
	private double width;
	private double length;
	
	//constructors
	public Rectangle () {} //to make an empty object
	
	public Rectangle (double w, double l) {
		width = w;
		length = l;
	}
	
	//methods
	public void setWidth(double w) {
		width = w;
	}
	
	public void setLength(double len) {
		length = len;
	}
	
	public double getWidth() {
		return width;
	}
	
	public double getLength() {
		return length;
	}
	
	/** 
	 * Method to return the area of a rectangle
	 * @return is a double that returns the area of a rectangle
	 */
	public double getArea() {
		return length * width;
		
	}
}
