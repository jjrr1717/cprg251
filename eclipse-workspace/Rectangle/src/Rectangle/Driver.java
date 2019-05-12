package Rectangle;

public class Driver {
	public static void main (String [] args) {
		//variables
		Rectangle den = new Rectangle();
		Rectangle kitchen = new Rectangle();
		
		//set the dimensions of both rooms
		den.setWidth(10);
		den.setLength(12);
		
		kitchen.setWidth(15);
		kitchen.setLength(18);
		
		//print out area of rooms
		System.out.println("The den is " + den.getArea() + " square feet in area.");
		System.out.println("The kitchen is " + kitchen.getArea() + " square feet in area.");
	}

}
