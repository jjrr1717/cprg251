package serializationFour;

public class AppDriver {
	public static void main(String[] args) {
		Location locationOne = new Location("Y2J 8U8", "Somewhere in Calgary");
		Event eventOne = new Event("At Stampede", locationOne);
		Competitor personOne = new Competitor("Jason", eventOne, "1988");

		ReadAndWrite rw = new ReadAndWrite();

		rw.write(personOne);
		System.out.println("Before: " + personOne);
		System.out.println("***********************");
		personOne = null;
		System.out.println("After Null: " + personOne);
		System.out.println("***********************");
		personOne = rw.read();
		System.out.println("After Read: " + personOne);
		System.out.println("***********************");
	}

}
