package fractions;

public class WholeFraction extends Fraction {
	
	private int whole; //only access to WholeFraction - Fraction does not have access to this
	
	public WholeFraction(int n, int d, int w) 
	{
		super(n,d);
		whole = w;
	}
	
	public void setWhole(int w) 
	{
		whole = w;
	}
	
	public int getWhole() 
	{
		return whole;
	}
	
	public String toString() 
	{
		return (getWhole() + " " + super.toString());
	}

}
