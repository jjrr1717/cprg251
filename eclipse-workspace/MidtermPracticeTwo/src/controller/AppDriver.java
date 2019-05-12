package controller;

public class AppDriver {
	public static void main (String [] args) 
	{
		Ship [] ships = new Ship[3];
		
		CruiseShip cruiseOne = new CruiseShip("Cruise One", "1998", 20000);
		CargoShip cargoOne = new CargoShip("Cargo One", "2008", 100000);
		CruiseShip cruiseTwo = new CruiseShip("Cruise Two", "2012", 300000);
		
		ships[0] = cruiseOne;
		ships[1] = cargoOne;
		ships[2] = cruiseTwo;
		
		for(int i = 0; i<ships.length; i++) 
		{
			System.out.println(ships[i] + "\n");
		}
		
		
	}

}
