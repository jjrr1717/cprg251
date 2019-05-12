package controller;

public class CruiseShip extends Ship {
	
	private int maxPass;
	
	public CruiseShip(String name, String year, int maxPass) 
	{
		super(name, year);
		this.maxPass = maxPass;
	}

	public int getMaxPass() {
		return maxPass;
	}

	public void setMaxPass(int maxPass) {
		this.maxPass = maxPass;
	}
	
	public String toString() 
	{
		return super.getName() + "\nMaximum Passengers: " + getMaxPass();
	}

}
