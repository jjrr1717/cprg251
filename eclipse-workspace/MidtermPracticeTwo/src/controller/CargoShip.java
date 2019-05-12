package controller;

public class CargoShip extends Ship {
	
	private int tonCapacity;
	
	public CargoShip(String name, String year, int tonCapacity) 
	{
		super(name, year);
		this.tonCapacity = tonCapacity;
	}

	public int getTonCapacity() {
		return tonCapacity;
	}

	public void setTonCapacity(int tonCapacity) {
		this.tonCapacity = tonCapacity;
	}
	
	public String toString() 
	{
		return super.getName() + "\nCargo Capacity (in tons): " + getTonCapacity();
	}
	
	
	
}
